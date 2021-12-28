package com.sofn.core.authorization.interceptor;

import com.sofn.core.constant.HttpCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 110 on 2017/1/17.
 */
@Component
public class ResponseModel {

    public static final int SUCCESS = 200;

    public static final int ERROR = 100;

    private Integer status;

    private String message;

    private Object data;

    private Locale locale;

    public ResponseModel(){
        this.status = SUCCESS;
        this.locale = Locale.CHINA;
    }

    public ResponseModel put(String key, Object value){
        if(this.data == null || !(data instanceof Map)){
            this.data = new HashMap<String,Object>();
        }
        Map<String, Object> map = (Map<String,Object>)this.data;
        map.put(key,value);
        return this;
    }

    public Object getData(){
        return data;
    }

    public ResponseModel setData(Object data){
        this.data = data;
        return this;
    }

    public ResponseModel setErrorMsg(HttpCode code){
        this.setStatus(code.value());
        this.setMessage(code.msg());
        return this;
    }
    public ResponseModel setStatus(int status){
        this.status = status;
        if(ERROR == status){
            this.setMessage("系统错误");
        }
        return this;
    }
    public ResponseModel setMessage(String message){
        this.message = message;
        return this;
    }
    public int getStatus(){
        return status;
    }
    public String getMessage(){
        return message;
    }

}
