package com.sofn.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;

/**
 * 通用Controller
 * Created by sofn
 */
@Slf4j
public abstract class BaseController {

    /**
     * 根据校验结果获得校验错误信息字符串
     * @param result  校验结果
     * @return   String  校验错误信息字符串
     */
    public String getErrMsg(BindingResult result){
        StringBuilder sBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                log.info(error.getCode() + "---" + Arrays.toString(error.getArguments()) + "---" + error.getDefaultMessage());
                sBuilder.append(error.getDefaultMessage());
                sBuilder.append("\n");
            }
        }
        return sBuilder.toString();
    }
}
