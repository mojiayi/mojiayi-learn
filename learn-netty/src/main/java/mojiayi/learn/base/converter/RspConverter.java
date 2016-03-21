package mojiayi.learn.base.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mojiayi.learn.base.BaseRsp;

public class RspConverter {
	public byte[] toByte(BaseRsp rsp) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(rsp);

		bytes = bo.toByteArray();

		bo.close();
		oo.close();
		return bytes;
	}

	public BaseRsp toObject(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(bi);

		Object obj = oi.readObject();
		bi.close();
		oi.close();
		if (obj instanceof BaseRsp) {
			return BaseRsp.class.cast(obj);
		} else {
			return null;
		}
	}
}
