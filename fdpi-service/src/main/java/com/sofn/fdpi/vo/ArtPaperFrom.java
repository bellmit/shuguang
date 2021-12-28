package com.sofn.fdpi.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-27 10:35
 */
@Data
@ApiModel(value = "人工繁育许可证对象")
public class ArtPaperFrom {
    //企业id，主键
    @ApiModelProperty(value = "主键")
    private String id;
    @NotBlank(message = "证书类型必选")
    @ApiModelProperty(value = "证书类型 1:人工繁育许可证 2驯养繁殖许可证")
    private String papersType;
    @Size(max = 30, message = "证书编号不能超过30")
    @NotBlank(message = "证书编号必填")
    @ApiModelProperty(value = "证书编号")
    private String papersNumber;
//    @Size(max = 20, message = "企业名称不能超过20")
    @NotBlank(message = "企业名称必填")
    @ApiModelProperty(value = "企业名称")
    private String compName;
//    @Size(max = 10, message = "法人不能超过10")
    @NotBlank(message = "法人必填")
    @ApiModelProperty(value = "法人")
    private String legal;

    @Size(max = 50, message = "企业地址长度不能超过50位")
    @NotBlank(message = "企业地址必填")
    @ApiModelProperty("企业地址")
    private String compAddress;

    @ApiModelProperty(value = "有效日期结束")
    private Date dataClos;
    @Size(max = 20, message = "发证机关不能超过20")
    @NotBlank(message = "发证机关必填")
    @ApiModelProperty(value = "发证机关")
    private String issueUnit;
    @ApiModelProperty("人工繁育目的，物种保护1经营利用2人工繁育3科学研究4其他5")
    @Size(max = 50, message = "人工繁育目的不能超过50位")
    private String purpose;

    @ApiModelProperty(value = "核发日期")
    private Date issueDate;

//    @Size(max = 10, message = "技术负责人长度不能超过10")
    @NotBlank(message = "技术负责人必填")
    @ApiModelProperty("技术负责人,业主回复后,人工繁育许可证新增字段")
    private String technicalDirector;
    @Valid
    @ApiModelProperty("证书物种")
    private List<PapersSpecForm> papersSpecs;
}
