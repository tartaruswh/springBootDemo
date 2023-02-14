package com.example.demo.utils;

public class JsonData {
    private int code;
    private Object data;
    private String msg;

    public JsonData() {
    }

    public JsonData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 接口请求成功，返回正确相应码与json数据
     * @param data
     * @return
     */
    public static JsonData buildSuccess(Object data){
        return new JsonData(0, data);
    }

    /**
     * 接口请求失败，返回错误的响应码与提示信息
     * @param msg
     * @return
     */
    public static JsonData buidlError(String msg) {
        return new JsonData(-1, "",msg);
    }

    /**
     * 接口请求异常，自定义响应码与提示信息
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg,int code) {
        return new JsonData(code,"",msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
