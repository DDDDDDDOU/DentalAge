package com.sjtu.djw.wxcodelogin.util;

import lombok.Data;

/**
 * @author Draco
 * @className: ResultJson
 * @description:
 * @date 2022/3/24 19:48
 */
@Data
public class ResultJson {

    private final static int RESULT_OK = 200;
    private final static String RESULT_OK_MSG = "请求成功";
    private final static int RESULT_ERROR = 500;
    private final static String RESULT_ERROR_MSG = "服务器内部异常";

    private final static int LOGIN_ERROR = 250;

    private final static String LOGIN_ERROR_MSG = "登录失败或身份过期，请重新登陆";
    private int code;
    private String msg;
    private long count;
    private Object data;

    public ResultJson() {

    }

    public ResultJson(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResultJson(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultJson(int code, long count, Object data) {
        this.code = code;
        this.data = data;
        this.count = count;
    }

    public static ResultJson ok(){
        return new ResultJson(RESULT_OK,RESULT_OK_MSG);
    }

    public static ResultJson ok(String msg){
        return new ResultJson(RESULT_OK,msg);
    }

    public static ResultJson ok(Object data){
        return new ResultJson(RESULT_OK,0,data);
    }
    public static ResultJson ok(Object data,long count){
        return new ResultJson(RESULT_OK,count,data);
    }

    public static ResultJson error(String msg){
        return new ResultJson(RESULT_ERROR,msg);
    }

    public static ResultJson error(){
        return new ResultJson(RESULT_ERROR,RESULT_ERROR_MSG);
    }

    public static ResultJson loginError(String msg){
        return new ResultJson(LOGIN_ERROR,msg);
    }

    public static ResultJson loginError(){
        return new ResultJson(RESULT_ERROR,LOGIN_ERROR_MSG);
    }
}
