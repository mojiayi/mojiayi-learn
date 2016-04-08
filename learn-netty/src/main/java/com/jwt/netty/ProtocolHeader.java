package com.jwt.netty;

/**
 * 说明：协议消息头
 * 
 */
public class ProtocolHeader {
	private byte magic; // 备用字段
	private byte msgType; // 消息类型
	private short isList; // 保留字 0:单个查询，1:列表查询
	private short sn; // 序列号
	private int len; // 长度

	public byte getMagic() {
		return magic;
	}
	public void setMagic(byte magic) {
		this.magic = magic;
	}
	public byte getMsgType() {
		return msgType;
	}
	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}

	public short getIsList() {
		return isList;
	}
	public void setIsList(short isList) {
		this.isList = isList;
	}
	public short getSn() {
		return sn;
	}
	public void setSn(short sn) {
		this.sn = sn;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public ProtocolHeader() {
	}
	/**
	 * 
	 */
	public ProtocolHeader(byte magic, byte msgType, short reserve, short sn, int len) {
		this.magic = magic;
		this.msgType = msgType;
		this.isList = reserve;
		this.sn = sn;
		this.len = len;
	}

}
