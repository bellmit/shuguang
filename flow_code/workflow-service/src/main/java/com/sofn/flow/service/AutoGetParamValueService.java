package com.sofn.flow.service;

import com.sofn.flow.exception.SofnException;

/**
 * 自动获取参数值
 * @author heyongjie
 * @date 2020/5/29 9:55
 */
public interface AutoGetParamValueService {

    /**
     * 自动获取值
     * 请在方法执行前校验参数
     * @return   该方法自定义返回值
     */
    String getValueByParam(Object ... params);

    /**
     * 参数校验
     * @param params  参数
     * @throws SofnException  参数校验错误
     */
    void checkParam(Object ... params) throws SofnException;

}
