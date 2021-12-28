package com.sofn.flow.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 流程定义中出现的系统内置参数
 * @author heyongjie
 * @date 2020/5/29 11:08
 */
@Data
public class SysParamVo {

    /**
     * 内置参数名称
     */
    private String paramName;

    /**
     * 参数描述
     */
    private String paramDescribe;

    /**
     * 参数获取值的方法，使用全路径：如com.sofn.flow.service.impl.AutoGetUserParamValue.getValueByParam
     */
    private String paramGetValueFunction;

    /**
     * 流程期望的值
     */
    private List<Object> values;

    /**
     * 是否在界面展示
     * 如果在界面展示，那么判断Values是否有值 ，没值就使用输入框，有值就使用下拉列表
     * 如果不在界面展示，那么就由后台自动值 ，这个时候paramGetValueFunction不能为空
     */
    private boolean isShow;


    /**
     * 构建参数名称
     * @param paramName  参数名称
     * @return  ProcessDefParamVo
     */
    public SysParamVo buildParamName(String paramName){
        this.paramName = paramName;
        return this;
    }

    /**
     * 构建是否在界面显示
     * @param isShow  是否在界面显示
     * @return  ProcessDefParamVo
     */
    public SysParamVo buildIsShow(boolean isShow){
        this.isShow = isShow;
        return this;
    }

    /**
     * 构建参数描述
     * @param paramDescribe  参数描述
     * @return  ProcessDefParamVo
     */
    public SysParamVo buildParamDescribe(String paramDescribe){
        this.paramDescribe = paramDescribe;
        return this;
    }

    /**
     * 构建参数获取方法
     * @param paramGetValueFunction  参数获取方法
     * @return  ProcessDefParamVo
     */
    public SysParamVo buildParamGetValueFunction(String paramGetValueFunction){
        this.paramGetValueFunction = paramGetValueFunction;
        return this;
    }

    /**
     * 构建值列表
     * @param values   需要构建的值
     * @return  ProcessDefParamVo
     */
    public SysParamVo buildValus(Object... values){
        if(values.length > 0){
            List<Object> objs = new ArrayList<>();
            Collections.addAll(objs, values);
            this.values = objs;
        }
        return this;
    }

}
