package com.jwt.netty;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 说明：处理器
 * 
 */
public class ProtocolClientHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {
		Channel incoming = ctx.channel();
		if (obj instanceof ProtocolMsg) {
			ProtocolMsg msg = (ProtocolMsg) obj;
			MessageMapper.addProtocolMsg(msg);
			System.out.println("Server->Client:" + incoming.remoteAddress() + ZipUtil.unGzip(msg.getBody()));
		}
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
