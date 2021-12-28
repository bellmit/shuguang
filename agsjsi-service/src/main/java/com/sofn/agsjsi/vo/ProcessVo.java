package com.sofn.agsjsi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 审核流程记录表
 * wuXY
 */
@Data
@ApiModel("审核流程记录表")
public class ProcessVo {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("状态0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复\"")
    private String status;
    @ApiModelProperty("状态名称：0：保存；1：撤回；2：上报；3：市级退回；4：市级审核；5：省级退回；6：省级通过；7：总站退回；8：总站通过；9：专家批复\"")
    private String statusName;
    @ApiModelProperty("建议")
    private String advice;
    @ApiModelProperty("操作人")
    private String personName;
    @ApiModelProperty("操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
