package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "鲟鱼子酱表单对象")
public class SturgeonForm {

    @ApiModelProperty(value = "主键")
    private String id;
//    @NotBlank(message = "进出口证书必须上传")
    @ApiModelProperty(value = "证书图片id(支撑平台文件id)")
    private String fileId;
    @Size(max = 20, message = "证书编号不能超过20位")
//    @NotBlank(message = "证书编号不能为空")
    @ApiModelProperty(value = "证书编号", example = "2020CN/EC000331/SH")
    private String credentials;
//    @NotBlank(message = "贸易类型不能为空")
    @ApiModelProperty(value = "贸易类型", example = "出口")
    private String trade;
    @Size(max = 10, message = "附录不能超过10位")
//    @NotBlank(message = "附录不能为空")
    @ApiModelProperty(value = "附录", example = "II")
    private String appendix;
    @Size(max = 10, message = "标本类型不能超过10位")
//    @NotBlank(message = "标本类型不能为空")
    @ApiModelProperty(value = "标本类型", example = "CAVIAR")
    private String sample;
    @Size(max = 10, message = "来源代码不能超过10位")
//    @NotBlank(message = "来源代码不能为空")
    @ApiModelProperty(value = "来源代码", example = "C")
    private String source;
    @Size(max = 10, message = "加工厂代码不能超过10位")
//    @NotBlank(message = "加工厂代码不能为空")
    @ApiModelProperty(value = "加工厂代码", example = "P009")
    private String handle;
//    @Max(value = 100000000,message = "数量或净重必须小于10亿")
//    @Min(value = 0, message = "数量或净重必须大小0")
    @ApiModelProperty(value = "数量或净重", example = "211.60")
    private Double sum;
    @Size(max = 10, message = "原产国代码不能超过10位")
//    @NotBlank(message = "原产国代码不能为空")
    @ApiModelProperty(value = "原产国代码", example = "CN")
    private String origin;
    @Size(max = 10, message = "出口国不能超过10位")
//    @NotBlank(message = "出口国不能为空")
    @ApiModelProperty(value = "出口国", example = "CN")
    private String export;
    @Size(max = 10, message = "分装国不能超过10位")
    @ApiModelProperty(value = "分装国", example = "JO")
    private String split;
//    @NotNull(message = "审批时间审批时间不能为空")
    @ApiModelProperty(value = "审批时间")
    private Date auditTime;
    @Size(max = 20, message = "证书核发地不能超过20位")
//    @NotNull(message = "证书核发地不能为空")
    @ApiModelProperty(value = "证书核发地", example = "SH")
    private String issueAddr;
    @ApiModelProperty(value = "状态 1未上报 2已上报(待审核) 3已退回 4已通过", example = "1")
    private String status;
    @ApiModelProperty(value = "文件表单列表")
    private List<SturgeonSubForm> sturgeonSubForms;
    @ApiModelProperty("申请单号")
    private String applyCode;
    @ApiModelProperty("申请类型1国外2国内")
    private String applyType;

}
