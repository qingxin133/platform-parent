package cn.ty.platform.common.exception.status;

import cn.ty.platform.common.exception.PlatformStatusCode;

/**
 * 返回状态基础类型
 * @author tianyang
 */
public enum BaseStatusCode implements PlatformStatusCode {
	
	SUCESS(0x00,"请求成功"),
	ERROR(0x500,"请求失败"),
	PAGE_ERROR(0x600,"分页参数不合法"),
	DB_ERROR_IMP_ENTITY(0x601,"通用方法捕获到没有注入的实体");
	
	private BaseStatusCode() {
		
	}
	private BaseStatusCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	/**
	 * 代码
	 */
	private int code;
	
	/**
	 * 消息
	 */
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
