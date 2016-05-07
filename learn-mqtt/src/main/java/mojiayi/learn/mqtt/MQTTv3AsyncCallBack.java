/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution. 
 *
 * The Eclipse Public License is available at 
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *    Dave Locke - initial API and implementation and/or initial documentation
 */

package mojiayi.learn.mqtt;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author mojiayi Push message with the Paho MQTT v3.1 Client API in
 *         non-blocking callback/notification mode,use the asynchronous API
 *         where events are used to notify the application when an action
 *         completes
 */
public class MQTTv3AsyncCallBack implements MqttCallback {
	// Private static variables
	private static final Logger logger = Logger.getLogger(MQTTv3AsyncCallBack.class);
	private static final int BEGIN = 0;
	private static final int CONNECTED = 1;
	private static final int PUBLISHED = 2;
	private static final int SUBSCRIBED = 3;
	private static final int DISCONNECTED = 4;
	private static final int FINISH = 5;
	private static final int ERROR = 6;
	private static final int DISCONNECT = 7;

	// Private instance variables
	@Value("${mqtt.host.url}")
	private String brokerUrl;
	@Value("${mqtt.username}")
	private String username;
	@Value("${mqtt.password}")
	private String password;
	@Value("${mqtt.clientId}")
	private String clientId;
	@Value("${mqtt.clean.session}")
	private boolean cleanSession;
	@Value("${mqtt.tempDir}")
	private String tempDir;
	@Value("${mqtt.qos}")
	private int publishQoS;

	private int state = BEGIN;
	private MqttAsyncClient client;
	private MqttConnectOptions conOpt;
	private Object waiter = new Object();
	private boolean donext = false;
	private Throwable ex;
	private boolean publishSuccess = false;

	/**
	 * instantiate an instance of the MQTT asynchronous callback client wrapper
	 * 
	 * 
	 * @throws MqttException
	 */
	public void initMQTTAsyncCallBack() {
		MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tempDir);
		// Construct the object that contains connection parameters
		// such as cleanSession and LWT
		conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(cleanSession);
		if (password != null) {
			conOpt.setPassword(this.password.toCharArray());
		}
		if (username != null) {
			conOpt.setUserName(this.username);
		}
		try {
			// Construct the MqttClient instance
			client = new MqttAsyncClient(this.brokerUrl, clientId, dataStore);

			// Set this wrapper as the callback handler
			client.setCallback(this);
		} catch (MqttException e) {
			logger.error("Unable to set up MQTT client", e);
			System.exit(1);
		}
	}

	/**
	 * Publish / send a message to an MQTT server
	 * 
	 * @param topicName
	 *            the name of the topic to publish to
	 * @param payload
	 *            the message content to send to the MQTT server
	 * @throws MqttException
	 */
	public boolean publish(String topicName, String payload) throws Throwable {
		// Use a state machine to decide which step to do next. State change
		// occurs
		// when a notification is received that an MQTT action has completed
		publishSuccess = false;
		while (state != FINISH) {
			switch (state) {
			case BEGIN:
				// Connect using a non-blocking connect
				MqttConnector con = new MqttConnector();
				con.doConnect();
				break;
			case CONNECTED:
				// Publish using a non-blocking publisher
				Publisher pub = new Publisher();
				pub.doPublish(topicName, publishQoS, payload.getBytes());
				break;
			case PUBLISHED:
				state = DISCONNECT;
				donext = true;
				break;
			case DISCONNECT:
				Disconnector disc = new Disconnector();
				disc.doDisconnect();
				break;
			case ERROR:
				throw ex;
			case DISCONNECTED:
				state = FINISH;
				donext = true;
				break;
			}

			// Wait until notified about a state change and then perform next
			// action
			waitForStateChange(10000);
		}
		state = BEGIN;
		return publishSuccess;
	}

	/**
	 * Wait for a maximum amount of time for a state change event to occur
	 * 
	 * @param maxTTW
	 *            maximum time to wait in milliseconds
	 * @throws MqttException
	 */
	private void waitForStateChange(int maxTTW) throws MqttException {
		synchronized (waiter) {
			if (!donext) {
				try {
					waiter.wait(maxTTW);
				} catch (InterruptedException e) {
					logger.info("waitForStateChange timed out");
				}

				if (ex != null) {
					throw (MqttException) ex;
				}
			}
			donext = false;
		}
	}

	/**
	 * Subscribe to a topic on an MQTT server Once subscribed this method waits
	 * for the messages to arrive from the server that match the subscription.
	 * It continues listening for messages until the enter key is pressed.
	 * 
	 * @param topicName
	 *            to subscribe to (can be wild carded)
	 * @param qos
	 *            the maximum quality of service to receive messages at for this
	 *            subscription
	 * @throws MqttException
	 */
	public void subscribe(String topicName, int qos) throws Throwable {
		// Use a state machine to decide which step to do next. State change
		// occurs
		// when a notification is received that an MQTT action has completed
		while (state != FINISH) {
			switch (state) {
			case BEGIN:
				// Connect using a non-blocking connect
				MqttConnector con = new MqttConnector();
				con.doConnect();
				break;
			case CONNECTED:
				// Subscribe using a non-blocking subscribe
				Subscriber sub = new Subscriber();
				sub.doSubscribe(topicName, qos);
				break;
			case SUBSCRIBED:
				// Block until Enter is pressed allowing messages to arrive
				state = DISCONNECT;
				donext = true;
				break;
			case DISCONNECT:
				Disconnector disc = new Disconnector();
				disc.doDisconnect();
				break;
			case ERROR:
				throw ex;
			case DISCONNECTED:
				state = FINISH;
				donext = true;
				break;
			}

			waitForStateChange(10000);
		}
		state = BEGIN;
	}

	/****************************************************************/
	/* Methods to implement the MqttCallback interface */
	/****************************************************************/

	/**
	 * @see MqttCallback#connectionLost(Throwable)
	 */
	public void connectionLost(Throwable cause) {
		// Called when the connection to the server has been lost.
		// An application may choose to implement reconnection
		// logic at this point.
		logger.info("Connection to " + brokerUrl + " lost!Reconnect now.", cause);
		MqttConnector con = new MqttConnector();
		con.doConnect();
	}

	/**
	 * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
	 */
	public void deliveryComplete(IMqttDeliveryToken token) {
		// Called when a message has been delivered to the
		// server. The token passed in here is the same one
		// that was returned from the original call to publish.
		// This allows applications to perform asynchronous
		// delivery without blocking until delivery completes.
		//
		// This sample demonstrates asynchronous deliver, registering
		// a callback to be notified on each call to publish.
		//
		// The deliveryComplete method will also be called if
		// the callback is set on the client
		//
		// note that token.getTopics() returns an array so we convert to a
		// string
		// before printing it on the console
		publishSuccess = true;
		logger.info("Delivery complete callback: Publish Completed " + Arrays.toString(token.getTopics()));
	}

	/**
	 * @see MqttCallback#messageArrived(String, MqttMessage)
	 */
	public void messageArrived(String topic, MqttMessage message) throws MqttException {
		// Called when a message arrives from the server that matches any
		// subscription made by the client
		StringBuilder msg = new StringBuilder();
		msg.append("Message arrived,Topic=");
		msg.append(topic);
		msg.append(",qos=");
		msg.append(message.getQos());
		msg.append(",payload=");
		msg.append(new String(message.getPayload()));
		logger.info(new String(msg));
	}

	/****************************************************************/
	/* End of MqttCallback methods */
	/****************************************************************/

	/**
	 * Connect in a non-blocking way and then sit back and wait to be notified
	 * that the action has completed.
	 */
	public class MqttConnector {

		public MqttConnector() {
		}

		public void doConnect() {
			// Connect to the server
			// Get a token and setup an asynchronous listener on the token which
			// will be notified once the connect completes
			logger.info("Connecting to " + brokerUrl + " with client ID " + client.getClientId());

			IMqttActionListener conListener = new IMqttActionListener() {
				public void onSuccess(IMqttToken asyncActionToken) {
					state = CONNECTED;
					carryOn();
				}

				public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
					ex = exception;
					state = ERROR;
					logger.error("connect failed", exception);
					carryOn();
				}

				public void carryOn() {
					synchronized (waiter) {
						donext = true;
						waiter.notifyAll();
					}
				}
			};

			try {
				// Connect using a non-blocking connect
				client.connect(conOpt, "Connect ebang MQTT context", conListener);
			} catch (MqttException e) {
				// If though it is a non-blocking connect an exception can be
				// thrown if validation of parms fails or other checks such
				// as already connected fail.
				state = ERROR;
				donext = true;
				ex = e;
				logger.error("Exception occurs while connect to MQTT", e);
			}
		}
	}

	/**
	 * Publish in a non-blocking way and then sit back and wait to be notified
	 * that the action has completed.
	 */
	public class Publisher {
		public void doPublish(String topicName, int qos, byte[] payload) {
			// Send / publish a message to the server
			// Get a token and setup an asynchronous listener on the token which
			// will be notified once the message has been delivered
			MqttMessage message = new MqttMessage(payload);
			message.setQos(qos);

			logger.info("Publishing to topic \"" + topicName + "\", qos=" + qos);

			// Setup a listener object to be notified when the publish
			// completes.
			//
			IMqttActionListener pubListener = new IMqttActionListener() {
				public void onSuccess(IMqttToken asyncActionToken) {
					state = PUBLISHED;
					carryOn();
				}

				public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
					ex = exception;
					state = ERROR;
					logger.error("Publish failed", exception);
					carryOn();
				}

				public void carryOn() {
					synchronized (waiter) {
						donext = true;
						waiter.notifyAll();
					}
				}
			};

			try {
				// Publish the message
				client.publish(topicName, message, "Pub ebang MQTT context", pubListener);
			} catch (MqttException e) {
				state = ERROR;
				donext = true;
				ex = e;
				logger.error("Exception occurs while publish to MQTT", e);
			}
		}
	}

	/**
	 * Subscribe in a non-blocking way and then sit back and wait to be notified
	 * that the action has completed.
	 */
	public class Subscriber {
		public void doSubscribe(String topicName, int qos) {
			// Make a subscription
			// Get a token and setup an asynchronous listener on the token which
			// will be notified once the subscription is in place.
			logger.info("Subscribing to topic \"" + topicName + "\", qos=" + qos);

			IMqttActionListener subListener = new IMqttActionListener() {
				public void onSuccess(IMqttToken asyncActionToken) {
					state = SUBSCRIBED;
					carryOn();
				}

				public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
					ex = exception;
					state = ERROR;
					logger.error("Subscribe failed", exception);
					carryOn();
				}

				public void carryOn() {
					synchronized (waiter) {
						donext = true;
						waiter.notifyAll();
					}
				}
			};

			try {
				client.subscribe(topicName, qos, "Subscribe ebang MQTT context", subListener);
			} catch (MqttException e) {
				state = ERROR;
				donext = true;
				ex = e;
				logger.error("Exception occurs while Subscribe to MQTT", e);
			}
		}
	}

	/**
	 * Disconnect in a non-blocking way and then sit back and wait to be
	 * notified that the action has completed.
	 */
	public class Disconnector {
		public void doDisconnect() {
			// Disconnect the client
			IMqttActionListener discListener = new IMqttActionListener() {
				public void onSuccess(IMqttToken asyncActionToken) {
					state = DISCONNECTED;
					carryOn();
				}

				public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
					ex = exception;
					state = ERROR;
					logger.error("Disconnect failed", exception);
					carryOn();
				}

				public void carryOn() {
					synchronized (waiter) {
						donext = true;
						waiter.notifyAll();
					}
				}
			};

			try {
				client.disconnect("Disconnect ebang MQTT context", discListener);
			} catch (MqttException e) {
				state = ERROR;
				donext = true;
				ex = e;
				logger.error("Exception occurs while disconnect to MQTT", e);
			}
		}
	}
}
