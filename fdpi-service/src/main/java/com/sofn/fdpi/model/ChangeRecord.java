package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 09:10
 */
@TableName("CHANGE_REC")
@Data
public class ChangeRecord extends BaseModel<ChangeRecord> {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("物种ID")
    private String speciesId;
    @ApiModelProperty("变更日期")
    private Date changeDate;
    @ApiModelProperty("变更原因")
    private String changeReason;
    @ApiModelProperty("变更企业ID")
    private String changeCompany;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("变更数量")
    private String changeNum;
    @ApiModelProperty("变更申请状态0:保存；1：上报；2：初审退回；3初审通过；4：撤回")
    private String changeStatus;
    @ApiModelProperty("合同文件在支撑平台ID")
    private String fileId;
    @ApiModelProperty("合同文件在支撑平台路径")
    private String filePath;
    @ApiModelProperty("合同文件在支撑平台名称")
    private String fileName;
    @ApiModelProperty("初审意见")
    private String firstOpnion;
    @ApiModelProperty("复审意见")
    private String secondOpnion;
    @ApiModelProperty("申请报告")
    private String requestReport;
    @ApiModelProperty("物种数量")
    private BigDecimal hisSpeNum;

    private String isReport;
    @ApiModelProperty("申请单号")
    private String applyCode;

    @ApiModelProperty(value = "来源地")
    private String source;
    @ApiModelProperty(value = "方式")
    private String mode;
}
