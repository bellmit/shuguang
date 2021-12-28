package com.sofn.fdpi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:20
 */
@Data
@ApiModel("救护信息VO对象")
public class ManageResInfoVo extends BaseVo<ManageResInfoVo> {
    /**
     * 收容ID
     */
    @ApiModelProperty(value = "收容ID")
    private String id;

//    /**
//     * 收容时间
//     */
//
//    @ApiModelProperty(value = "收容时间")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @JsonFormat(
//            pattern = "yyyy-MM-dd",
//            timezone = "GMT+8")
//    private Date asylumDate;
    @Size(max = 20,message = "标识编码不能超过50")
    @ApiModelProperty(value = "标识编码")
    private String code;

    /**
     * 救护地点
     */
    @NotBlank(message = "救护地点不能为空")
    @Size(max = 50,message = "救护地点不能超过50为位")
    @ApiModelProperty(value = "救护地点")
    private String rescueSite;

    /**
     * 处置单位
     */
    @NotBlank(message = "处置单位不能为空")
    @Size(max = 20,message = "处置单位不能超过50位")
    @ApiModelProperty(value = "处置单位")
    private String disposalUnit;

    /**
     * 处置时间
     */
    @NotNull(message = "处置时间不能为空")
    @ApiModelProperty(value = "处置时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8")
    private Date disposalTime;

    /**
     * 救护物种
     */
    @NotBlank(message = "救护物种不能为空")
    @ApiModelProperty(value = "救护物种")
    private String rescueSpe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 1保存，6上报")
    private int status;

    /**
     * 操作
     */
    @Size(max = 50,message = "操作不能超过50位")
    @ApiModelProperty(value = "操作")
    private String operation;

    /**
     * 救护时间
     */
    @NotNull(message = "救护时间不能为空")
    @ApiModelProperty(value = "救护时间")
    private Date resTime;

    /**
     * 救护过程
     */
    @NotBlank(message = "救护过程不能为空")
    @Size(max = 500,message = "救护过程不能超过200位")
    @ApiModelProperty(value = "救护过程")
    private String resProcess;

    /**
     * 救护结果
     */
    @NotBlank(message = "救护结果不能为空")
    @Size(max = 500,message = "救护结果不能超过30位")
    @ApiModelProperty(value = "救护结果")
    private String resFruit;

    /**
     * 处置意见
     */
    @NotBlank(message = "处置意见不能为空")
    @Size(max = 100,message = "处置意见不能超过100位")
    @ApiModelProperty(value = "处置意见")
    private String disView;

    /**
     * 处置方案
     */
    @NotBlank(message = "处置方案不能为空")
    @Size(max = 200,message = "处置方案不能超过200位")
    @ApiModelProperty(value = "处置方案")
    private String disPlan;
    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "发现人员")
    private String discoverer;

}
