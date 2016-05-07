package mojiayi.learn.netty.call;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import mojiayi.learn.commons.zip.ZipUtil;
import mojiayi.learn.netty.protocol.ProtocolHeader;
import mojiayi.learn.netty.protocol.ProtocolMsg;
import mojiayi.learn.netty.protocol.RequestMsg;

/**
 * 说明：
 * 
 */
public class ProtocolClient implements Runnable {
	private static final int MAX_FRAME_LENGTH = 1024 * 1024 * 10;
	private static final int LENGTH_FIELD_LENGTH = 4;
	private static final int LENGTH_FIELD_OFFSET = 6;
	private static final int LENGTH_ADJUSTMENT = 0;
	private static final int INITIAL_BYTES_TO_STRIP = 0;

	private ProtocolMsg protocolMsg = null;
	private Channel channel = null;
	private static ProtocolClient socket = null;
	public static ProtocolClient getInstance() {
		if (socket == null) {
			return new ProtocolClient();
		}
		return socket;
	}
	private ProtocolClient() {
	}

	/**
	 * 初始化传输数据
	 * 
	 * @param msgType
	 * @param jsonString
	 */
	public void setProtocolMsg(byte msgType, String jsonString) {
		// 发送消息给服务器
		ProtocolMsg msg = new ProtocolMsg();
		ProtocolHeader protocolHeader = new ProtocolHeader();
		protocolHeader.setMagic((byte) 0x01);
		protocolHeader.setMsgType(msgType);
		protocolHeader.setSn((short) 0);
		jsonString = ZipUtil.gZip(jsonString);

		byte[] bodyBytes = jsonString.getBytes(Charset.forName("utf-8"));
		int bodySize = bodyBytes.length;

		protocolHeader.setLen(bodySize);
		msg.setProtocolHeader(protocolHeader);
		msg.setBody(jsonString);
		this.protocolMsg = msg;
	}

	/**
	 * 同步等待数据传输信息
	 * 
	 * @return
	 */
	public ProtocolMsg jwtWait() {
		long l = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - l > UserCache.WaitOut) {
				return new ProtocolMsg();
			}
			if (MessageMapper.getProtocolMsg(protocolMsg.getProtocolHeader().getMsgType()) == null)
				continue;
			break;
		}
		protocolMsg = MessageMapper.getProtocolMsg(protocolMsg.getProtocolHeader().getMsgType());
		MessageMapper.removeProtocolMsg(protocolMsg.getProtocolHeader().getMsgType());
		return protocolMsg;
	}

	public void run() {
		if (protocolMsg == null)
			return;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap(); // (1)
			b.group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true); // (4)
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("decoder", new ProtocolDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP));
					ch.pipeline().addLast("encoder", new ProtocolEncoder());
					ch.pipeline().addLast(new ProtocolClientHandler());
				}
			});
			// 启动客户端
			ChannelFuture future = b.connect("127.0.0.1", UserCache.serverPort); // (5)
			channel = future.sync().channel();
			channel.writeAndFlush(protocolMsg);
			// 等待连接关闭
			channel.closeFuture().sync();
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
	// 关闭连接
	public void disConnect() {
		if (channel != null) {
			channel.close();
		}
	}
	
	public static void main(String[] args) {
		String condition = "";
		String msgType = "";
		RequestMsg m=new RequestMsg("11111", condition, 1, msgType);
		ProtocolClient p=new ProtocolClient();
		p.setProtocolMsg((byte)1,JSON.toJSONString(m));
		p.run();
	}
}
