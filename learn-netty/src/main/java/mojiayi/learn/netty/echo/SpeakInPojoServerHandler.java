package mojiayi.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import mojiayi.learn.base.BaseReq;
import mojiayi.learn.base.BaseRsp;
import mojiayi.learn.base.converter.RspConverter;

public class SpeakInPojoServerHandler extends MessageToByteEncoder<BaseReq> {
	private RspConverter converter = new RspConverter();

	@Override
	protected void encode(ChannelHandlerContext ctx, BaseReq req, ByteBuf buf)
			throws Exception {
		String sysCode = req.getSysCode();
		System.out.println("rsp.code=" + sysCode);
		BaseRsp rsp = new BaseRsp();
		if ("1001".equals(sysCode)) {
			rsp.setRespCode("200");
			rsp.setRespDesc("收到类型1001");
		} else {
			rsp.setRespCode("0");
			rsp.setRespDesc("收到其它类型");
		}
		buf.writeBytes(converter.toByte(rsp));
		ctx.writeAndFlush(buf);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
