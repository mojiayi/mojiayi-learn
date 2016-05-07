package mojiayi.learn.netty.protocol;

import java.io.Serializable;

public class RequestMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -312300392714553243L;
	private String clientId;
	private String condition;
	private int isList;// 0:单个查询，1:列表查询
	private String msgType;
	private String resXml;

	
	public String getResXml() {
		return resXml;
	}

	public void setResXml(String resXml) {
		this.resXml = resXml;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getIsList() {
		return isList;
	}

	public void setIsList(int isList) {
		this.isList = isList;
	}

	public RequestMsg(String clientId, String condition, int isList, String msgType) {
		this.clientId = clientId;
		this.condition = condition;
		this.isList = isList;
		this.msgType = msgType;
	}
	public RequestMsg() {
		// TODO Auto-generated constructor stub
	}

}
