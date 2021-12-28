/**
 * @Author 文俊云
 * @Date 2019/12/27 14:39
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2019/12/27 14:39
 * @Version 1.0
 */

@Data
public class ChangeRecordDetailVO {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("企业ID")
    private String companyId;
    @ApiModelProperty("企业名称")
    private String compName;
    @ApiModelProperty("企业所属省")
    private String compProvince;
    @ApiModelProperty("企业所属市")
    private String compCity;
    @ApiModelProperty("企业所属区县")
    private String compDistrict;
    @ApiModelProperty("企业通讯地址")
    private String contactAddress;
    @ApiModelProperty("企业邮政编码")
    private String postAddress;
    @ApiModelProperty("企业法人")
    private String legal;
    @ApiModelProperty("企业联系人")
    private String linkman;
    @ApiModelProperty("企业电话")
    private String phone;
    @ApiModelProperty("企业邮箱")
    private String email;
    @ApiModelProperty("物种ID")
    private String speciesId;
    @ApiModelProperty("物种名称")
    private String speciesName;
    @ApiModelProperty("物种数目")
    private int speciesNum;
    @ApiModelProperty("变更原因")
    private String changeReason;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("变更数目")
    private int changeNum;
    @ApiModelProperty("合同ID")
    private String fileId;
    @ApiModelProperty("合同路径")
    private String filePath;
    @ApiModelProperty("合同名称")
    private String fileName;
    @ApiModelProperty("初审意见")
    private String firstOpnion;
    @ApiModelProperty("复审意见")
    private String secondOpnion;
    @ApiModelProperty("变更申请状态0:保存；1：上报；2：初审退回；3初审通过；4、撤回")
    private String changeStatus;
    @ApiModelProperty("创建人员ID")
    private String createUserId;
    @ApiModelProperty("变更日期")
    private String changeDate;
    @ApiModelProperty("申请报告")
    private String requestReport;
    @ApiModelProperty("申请单号")
    private String applyCode;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("是否显示撤回按钮")
    private Boolean isShowCancel;
    @ApiModelProperty(value = "是否能审核（通过/退回）")
    private boolean canAudit;
    @ApiModelProperty(value = "来源地")
    private String source;
    @ApiModelProperty(value = "方式")
    private String mode;
}
