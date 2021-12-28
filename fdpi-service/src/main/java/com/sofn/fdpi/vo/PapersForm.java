
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
 * @Date: 2020-01-02 11:33
 */
@Data
@ApiModel(value = "经营可证书表单对象")
public class PapersForm {
    //企业id，主键
    @ApiModelProperty(value = "主键")
    private String id;
    @NotBlank(message = "证书类型必选")
    @ApiModelProperty(value = "证书类型 3经营利用许可证")
    private String papersType;
    @Size(max = 30, message = "证书编号不能超过30位")
    @NotBlank(message = "证书编号必填")
    @ApiModelProperty(value = "证书编号")
    private String papersNumber;
//    @Size(max = 20, message = "企业名称不能超过20位")
    @NotBlank(message = "企业名称必填")
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @Size(max = 50, message = "企业地址长度不能超过50位")
    @NotBlank(message = "企业地址必填")
    @ApiModelProperty("企业地址")
    private String compAddress;
//    @Size(max = 10, message = "法人不能超过10位")
    @NotBlank(message = "法人必填")
    @ApiModelProperty(value = "法人")
    private String legal;
//    @Size(max = 30,message = "核定物种不能超过30位")
//    @NotBlank(message="物种学名必填")
//    @ApiModelProperty(value = "核定物种")
//    private String issueSpe;
//    @Size(max = 300,message = "来源不能超过30位0")
//    @ApiModelProperty("来源")
//    private String sourceForm;
//    @ApiModelProperty(value = "有效日期开始")
//    private Date dataStart;

    @ApiModelProperty(value = "有效日期结束")
    private Date dataClos;
    @Size(max = 20, message = "发证机关不能超过20位")
    @NotBlank(message = "发证机关必填")
    @ApiModelProperty(value = "发证机关")
    private String issueUnit;
    @Size(max = 10, message = "经营方式不能超过10位")
    @NotBlank(message = "经营方式必填")
    @ApiModelProperty("经营方式（业主回复后,经营许可证新增字段）")
    private String modeOperation;
    @Size(max = 10, message = "销售去向不能超过10位")
    @NotBlank(message = "销售去向必填")
    @ApiModelProperty("销售去向（业主回复后,经营许可证新增字段）")
    private String salesDestination;
    @ApiModelProperty(value = "核发日期")
    private Date issueDate;
    @Valid
    @ApiModelProperty("证书物种")
    private List<PapersSpecForm> papersSpecs;


}