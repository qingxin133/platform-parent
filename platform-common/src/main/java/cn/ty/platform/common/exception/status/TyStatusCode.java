package cn.ty.platform.common.exception.status;

import cn.ty.platform.common.exception.PlatformStatusCode;

public enum TyStatusCode implements PlatformStatusCode {

    FIND_NOT_ERROR(0x004301,"查询的结果没有找到");

    private TyStatusCode(){

    }
    private TyStatusCode(int code, String msg) {
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
