package com.sofn.fdpi.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-02 15:19
 */
@Data
@ApiModel(value = "特许捕获证表单对象")
public class CaptureForm {

    @ApiModelProperty(value = "主键")
    private String id;

    @Size(max = 20, message = "持证单位不能超过20位")
    @NotBlank(message = "持证单位必填")
    @ApiModelProperty(value = "持证单位")
    private String capUnit;

    @Size(max = 30, message = "证书编号不能超过30位")
    @NotBlank(message = "证书编号必填")
    @ApiModelProperty(value = "证书编号")
    private String papersNumber;

    @ApiModelProperty(value = "证书类型")
    private String papersType;

//    @Size(max = 20, message = "批准文号不能超过20位")
//    @NotBlank(message = "批准文号必填")
    @ApiModelProperty(value = "批准文号")
    private String appNum;

    @NotBlank(message = "捕获事由必填")
    @Size(max = 50, message = "捕获事由不能超过50位")
    @ApiModelProperty(value = "捕获事由")
    private String cause;

    @ApiModelProperty(value = "有效日期开始")
    private Date dataStart;


    @ApiModelProperty(value = "有效日期截至")
    private Date dataClos;

    @Size(max = 50, message = "具体捕猎地点不能超过50位")
    @NotBlank(message = "具体捕猎地点必填")
    @ApiModelProperty(value = "具体捕猎地点必填")
    private String capLocal;
    //省
    @ApiModelProperty(value = "省")
    private String province;
    //市
    @ApiModelProperty(value = "市")
    private String city;
    //区
    @ApiModelProperty(value = "区")
    private String area;

    @Size(max = 20, message = "捕猎方式不能超过20位")
    @NotBlank(message = "捕猎方式")
    @ApiModelProperty(value = "捕猎方式必填")
    private String capWay;

    @Size(max = 10, message = "物种名不能超过10位")
    @NotBlank(message = "捕获物种名必填")
    @ApiModelProperty(value = "捕获物种名")
    private String speName;

    @NotBlank(message = "保护级别必填，保护物种级别获取下拉得到")
//    @Size(max = 10, message = "保护级别不能超过10位")
    @ApiModelProperty(value = "保护级别")
    private String proLevel;
//    @Min(value = 0, message = "最小值不得小于0")
//    @Max(value = 10, message = "最大值不超过10位")
    @NotNull(message = "捕获数量必填")
    @ApiModelProperty(value = "捕获数量")
    private Integer capNum;

    @Size(max = 20, message = "发证机关不能超过20位")
    @NotBlank(message = "发证机关必填")
    @ApiModelProperty(value = "发证机关")
    private String issueUnit;


    @ApiModelProperty(value = "发证日期必填")
    private Date issueDate;

}
