package com.jwt.netty;

/**
 * 说明：消息对象
 * 
 */
public class ProtocolMsg {

	private ProtocolHeader protocolHeader = new ProtocolHeader();
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 
	 */
	public ProtocolMsg() {
		// TODO Auto-generated constructor stub
	}
	public ProtocolMsg(String body) {
		this.body = body;
	}
	public ProtocolHeader getProtocolHeader() {
		return protocolHeader;
	}

	public void setProtocolHeader(ProtocolHeader protocolHeader) {
		this.protocolHeader = protocolHeader;
	}

}
