package com.sofn.flow.controller;

import com.sofn.flow.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 基础的Controller
 * @author heyongjie
 * @date 2020/5/13 16:17
 */
public class BasicController {

    @ModelAttribute
    public void setToken(HttpServletRequest request){
        // 在每个请求之前设置TOKEN
        String token = request.getHeader(TokenUtil.TOKEN_HEADNAME);
        if(!StringUtils.isBlank(token)){
            TokenUtil.setToken(token);
        }
    }

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
                sBuilder.append(error.getDefaultMessage());
                sBuilder.append("\n");
            }
        }
        return sBuilder.toString();
    }

}
