package cn.ty.platform.common.exception;

import cn.ty.platform.common.model.msg.ResultStatusBase;

/**
 *  服务端自定义异常
 * @author tianyang
 * @Date 2020-01-14 14:53:00
 *
 */
public class PlatformException extends RuntimeException {

	private static final long serialVersionUID = -2265191172953377405L;

	public static final String msg = ResultStatusBase.ERROR.getMsg();

	protected int code = ResultStatusBase.ERROR.getCode();

	public PlatformException() {
		super(ResultStatusBase.ERROR.getMsg());
	}

	/**
	 * 传枚举类型
	 * 
	 * @param status
	 */
	public PlatformException(ResultStatusBase status) {
		super(status.getMsg());
		this.code = status.getCode();
	}
	
	/**
	 * 传枚举类型
	 * 
	 * @param status
	 */
	public PlatformException(ResultStatusBase status,Throwable cause) {
		super(status.getMsg(),cause);
		this.code = status.getCode();
	}

	/**
	 * 传基本类型
	 * @param code
	 * @param msg
	 */
	public PlatformException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public PlatformException(int code, String msg,Throwable cause) {
		super(msg,cause);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
