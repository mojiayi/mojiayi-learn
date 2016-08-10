package mojiayi.learn.netty.call;

import java.util.HashMap;
import java.util.Map;

import mojiayi.learn.netty.protocol.ProtocolMsg;

/**
 * 说明：ObjectMapper 单例。create once, reuse
 * 
 */
public class MessageMapper {
	private static Map<Byte, ProtocolMsg> map = new HashMap<Byte, ProtocolMsg>();

	public static void addProtocolMsg(ProtocolMsg msg) {
		map.put(msg.getProtocolHeader().getMsgType(), msg);
	}

	public static ProtocolMsg getProtocolMsg(byte msgType) {
		return map.get(msgType);
	}

	public static void removeProtocolMsg(byte msgType) {
		map.remove(msgType);
	}
	public static void Clear() {
		map.clear();
	}
}
