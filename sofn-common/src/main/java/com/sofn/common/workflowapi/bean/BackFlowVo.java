/**
 * @Author 文俊云
 * @Date 2021/1/4 14:11
 * @Version 1.0
 */
package com.sofn.common.workflowapi.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2021/1/4 14:11
 * @Version 1.0
 */

@Data
public class BackFlowVo {

    @ApiModelProperty("属性")
    private Object attrs;
    @ApiModelProperty("流程定义的ID")
    private String defId;
    @ApiModelProperty("业务数据的ID")
    private String idAttrName;
    @ApiModelProperty("业务数据的值")
    private String idAttrValue;
    @ApiModelProperty("回退目标活动标识[为空：返回上一节点，不为空：返回到指定节点]")
    private String targetActDefId;
    public BackFlowVo(){

    }
}
