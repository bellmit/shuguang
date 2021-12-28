package com.sofn.fdpi.vo;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class ChangeRecordVO  {

    @ApiModelProperty("变更记录ID")
    private String id;
    @ApiModelProperty("物种ID")
    private String speciesId;
    //1：进口；2：出口；3：自繁；4：死亡；5：救护；6：放生;7，购买，8：销售；9：借入；10:借出；11：捐赠
    @ApiModelProperty("变更原因")
    private String changeReason;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("变更数量")
    private String changeNum;
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
    @ApiModelProperty("变更时间")
    private Date changeDate;
    @ApiModelProperty("申请报告")
    private String requestReport;
    @ApiModelProperty("新加字段，历史物种数量，请将物种数量的值赋予此字段")
    private BigDecimal hisSpeNum;
    @ApiModelProperty("申请编号")
    private String applyCode;

    @ApiModelProperty(value = "来源地")
    private String source;
    @ApiModelProperty(value = "方式")
    private String mode;
}
