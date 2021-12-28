/**
 * @Author 文俊云
 * @Date 2020/12/10 14:57
 * @Version 1.0
 */
package com.sofn.common.workflowapi.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/12/10 14:57
 * @Version1.0
 */

@Data
public class SubmitFlowVo {
    @ApiModelProperty("流程定义的ID")
    private String defId;
    @ApiModelProperty("业务数据的ID")
    private String idAttrName;
    @ApiModelProperty("流程实例的参数")
    private Object params;
    @ApiModelProperty("业务数据的值")
    private String idAttrValue;
    public SubmitFlowVo(){

    }
}
