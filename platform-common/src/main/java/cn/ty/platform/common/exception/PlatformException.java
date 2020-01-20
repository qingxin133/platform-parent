package cn.ty.platform.common.exception;

import cn.ty.platform.common.exception.status.BaseStatusCode;

/**
 *  服务端自定义异常
 * @author tianyang
 * @Date 2020-01-14 14:53:00
 *
 */
public class PlatformException extends RuntimeException {

	private static final long serialVersionUID = -2265191172953377405L;

	public static final String msg = BaseStatusCode.ERROR.getMsg();

	protected int code = BaseStatusCode.ERROR.getCode();

	public PlatformException() {
		super(BaseStatusCode.ERROR.getMsg());
	}

	/**
	 * 传枚举类型
	 * 
	 * @param status
	 */
	public PlatformException(PlatformStatusCode status) {
		super(status.getMsg());
		this.code = status.getCode();
	}
	
	/**
	 * 传枚举类型
	 * 
	 * @param status
	 */
	public PlatformException(BaseStatusCode status, Throwable cause) {
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

	/**
	 * 传基本类型和抛出的原始异常
	 * @param code
	 * @param msg
	 * @param cause
	 */
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
