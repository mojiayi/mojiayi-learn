package mojiayi.learn.base;

import java.io.Serializable;

public class BaseReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3246974666379734319L;
	private String sysCode;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
}
