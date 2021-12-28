package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 14:43
 */
@Data
@ApiModel(value = "调查（物种）对象")
public class SpeciesSurveyForm {
    @ApiModelProperty(value = "主键,新增不传，修改必传")
    private String id;

    @ApiModelProperty(value = "物种id")
    private String specId;

    @ApiModelProperty(value = "资源调查id")
    private String sourceId;
    @Size(max = 50,message = "物种名称长度不超过50")
    @ApiModelProperty(value = "物种名称")
    private String specName;
    @Size(max = 50,message = "属名长度不超过50")
    @ApiModelProperty(value = "属名")
    private String attrName;
    @Size(max = 50,message = "拉丁名长度不超过50")
    @ApiModelProperty(value = "拉丁名")
    private String latinName;
    @Size(max = 50,message = "科名长度不超过50")
    @ApiModelProperty(value = "科名")
    private String familyName;

    @ApiModelProperty(value = "分布面积（亩）")
    private Double area;

    @ApiModelProperty(value = "返青期开始")
    private Date greenS;

    @ApiModelProperty(value = "返青期结束")
    private Date greenE;

    @ApiModelProperty(value = "繁殖期开始")
    private Date breedS;

    @ApiModelProperty(value = "繁殖期结束")
    private Date breedE;

    @ApiModelProperty(value = "枯萎期开始")
    private Date witheredS;

    @ApiModelProperty(value = "枯萎期结束")
    private Date witheredE;

    @ApiModelProperty(value = "分布数量")
    private Double amount;

    @ApiModelProperty(value = "省内是否新发现 0否 1是")
    private String discovery;

    @ApiModelProperty(value = "是否为国家新发物种 0否 1是")
    private String newSpecies;

    @ApiModelProperty(value = "植株照片")
    private List<PictureAttForm>  plant;
    @ApiModelProperty(value = "群落照片")
    private List<PictureAttForm>  community;
    @ApiModelProperty(value = "生境照片")
    private List<PictureAttForm>  habitat;
}
