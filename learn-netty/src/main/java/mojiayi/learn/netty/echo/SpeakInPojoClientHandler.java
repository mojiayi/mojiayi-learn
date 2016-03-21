package mojiayi.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import mojiayi.learn.base.BaseRsp;
import mojiayi.learn.base.converter.RspConverter;

public class SpeakInPojoClientHandler extends ByteToMessageDecoder {
	private RspConverter converter = new RspConverter();

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf,
			List<Object> output) throws Exception {
		byte[] bytes = new byte[buf.readableBytes()];
		BaseRsp rsp = converter.toObject(bytes);
		System.out.println("rsp.code=" + rsp.getRespCode());
		System.out.println("rsp.desc=" + rsp.getRespDesc());
	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
       ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
