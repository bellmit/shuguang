package com.sofn.core.authorization.interceptor;

import com.sofn.core.constant.HttpCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 110 on 2017/1/17.
 */
@Component
public class ResponseHolder {
    private static ThreadLocal<Object> model = new ThreadLocal<>();

    public void clean(){
        model.remove();
    }

    public ResponseModel getModel(){
        Object o = model.get();
        if(o == null){
            this.setModel(new ResponseModel());
            o = this.getObject();
        }
        if(o != null && o instanceof ResponseModel){
            return (ResponseModel) o;
        }else {
            return null;
        }
    }

    public Object getObject(){
        return model.get();
    }

    public void setModel(Object o){
        model.set(o);
    }

    public ResponseModel put(String key, Object value){
        ResponseModel responseModel = this.getModel();
        Object data = responseModel.getData();
        if(data == null || !(data instanceof Map)){
            data = new HashMap<String,Object>();
            responseModel.setData(data);
        }
        Map<String,Object> map = (Map<String,Object>) data;
        map.put(key,value);
        return responseModel;
    }

    public ResponseModel setData(Object data){
        ResponseModel responseModel = this.getModel();
        responseModel.setData(data);
        return responseModel;
    }

    public ResponseModel setErrorMsg(HttpCode code){
        ResponseModel responseModel = this.getModel();

        responseModel.setStatus(code.value());
        responseModel.setMessage(code.msg());
        return responseModel;
    }
}
