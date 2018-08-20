package com.yuuki.cooky.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo implements Serializable {

    private static final long serialVersionUID = 3691995449556885041L;

    public static final int SUCCECC_CODE  = 200;
    public static final int FAILD_CODE    = 500;
    public static final int UNAUTH_CODE   = 403;


    private int code;

    private String msg;

    private Object data;

    private Map<String ,Object> extraData;

    public static ResponseVo ok(String msg){
        return new ResponseVo(SUCCECC_CODE,msg);
    }

    public static ResponseVo ok(Object data){
        return new ResponseVo(SUCCECC_CODE,"success",data);
    }

    public static ResponseVo ok(String msg,Object data){
        return new ResponseVo(SUCCECC_CODE,msg,data);
    }

    public static ResponseVo ok(String msg,Object data,Map<String,Object> extraData){
        return new ResponseVo(SUCCECC_CODE,msg,data,extraData);
    }

    public static ResponseVo error(String msg){
        return new ResponseVo(FAILD_CODE,msg);
    }

    public static ResponseVo error(String msg,Map<String,Object> extraData){
        return new ResponseVo(FAILD_CODE,msg,null,extraData);
    }

    public static ResponseVo unAuth(String msg){
        return new ResponseVo(UNAUTH_CODE,msg);
    }

    public ResponseVo() {
    }

    public ResponseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVo(int code, String msg, Object data, Map<String, Object> extraData) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.extraData = extraData;
    }
}
