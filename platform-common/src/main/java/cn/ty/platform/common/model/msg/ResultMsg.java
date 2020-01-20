package cn.ty.platform.common.model.msg;

import cn.ty.platform.common.exception.status.BaseStatusCode;

import java.io.Serializable;

public class ResultMsg<T> implements Serializable {

	private static final long serialVersionUID = -4614288271934555042L;
	
	public ResultMsg() {}
	
	public ResultMsg(BaseStatusCode status) {
		this.code = status.getCode();
		this.msg = status.getMsg();
	}
	
	public ResultMsg(BaseStatusCode status, T data) {
		this.code = status.getCode();
		this.msg = status.getMsg();
		this.data = data;
	}
 
	/**
	 * 状态码
	 */
	private int code;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 返回结果
	 */
	private T data;
	
	/*****************************自定义方法开始**********************************/
	
	/**
	 * 默认成功方法
	 * @param data
	 * @return
	 */
	public static<T> ResultMsg success(T data){
		return new ResultMsg(BaseStatusCode.SUCESS,data);
	}
	
	/**
	 * 默认失败方法
	 * @return
	 */
	public static<T> ResultMsg<T> error(){
		return new ResultMsg<T>(BaseStatusCode.ERROR);
	}
	
	/**
	 * 自定义错误消息失败
	 * @param errorMsg
	 * @return
	 */
	public static<T> ResultMsg<T> error(int code,String errorMsg){
		BaseStatusCode errorStatus = BaseStatusCode.ERROR;
		errorStatus.setCode(code);
		errorStatus.setMsg(errorMsg);
		return new ResultMsg<T>(errorStatus);
	}
	
	/*****************************自定义方法结束*********************************/
	
	/****************************get set***********************************/

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

 
	
	
	
	

}
