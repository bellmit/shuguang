package com.sofn.flow.vo;

import lombok.Data;

/**
 * 流程定义属性VO
 * @author heyongjie
 * @date 2020/5/18 14:11
 */
@Data
public class ProcessDefinitionAttrVo {

    /**
     * 属性对应的标识
     */
    private String attrId;

    /**
     * 属性中文名称
     */
    private String attrName;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 描述
     */
    private String description;

}
