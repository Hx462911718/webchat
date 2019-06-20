package com.hexin.netty.webchat.common.dto.enums;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018/3/26 21:31
 */
public enum ResponseCode {
    /**
     * 操作成功
     */
    SUCCESS(1, "操作成功"),
    /**
     * 操作失败
     */
    ERROR(0, "操作失败"),
    /**
     * 数据已存在
     */
    EXIST(ERROR.getCode(), "数据已存在"),
    /**
     * 数据为空
     */
    NOT_EXIST(ERROR.getCode(), "数据为空"),
    /**
     * 查询成功
     */
    GET_SUCCESS(SUCCESS.getCode(), "查询成功"),
    /**
     * 查询失败
     */
    GET_FAIL(ERROR.getCode(), "查询失败"),
    /**
     * 参数为空
     */
    PARAM_NULL(ERROR.getCode(), "参数为空"),
    REDIRECT(302, "重定向"),;


    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
