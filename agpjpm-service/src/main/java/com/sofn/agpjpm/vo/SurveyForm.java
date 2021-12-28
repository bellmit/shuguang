package com.sofn.agpjpm.vo;

import com.sofn.agpjpm.model.ClimaticType;
import com.sofn.agpjpm.model.HabitatType;
import com.sofn.agpjpm.model.LandformType;
import com.sofn.agpjpm.model.SoilType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-09 18:01
 */
@Data
@ApiModel(value = "野生植物调查save对象")
public class SurveyForm {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "调查日期")
    private Date surveyDate;
    @ApiModelProperty(value = "调查点编号")
    private String  surveyNum;

    @ApiModelProperty(value = "省")
    private String  province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "县")
    private String county;
    @Length(max = 20,message = "人名长度不超过20个字符")
    @NotBlank(message="调查人不为空")
    @ApiModelProperty(value = "调查人")
    private String surveyor;
//    @Pattern(regexp = "^((1\\d{10})|0(\\d{2,3}[\\-,]\\d{7,10}))$", message = "请输入正确手机号或固定电话")
    @ApiModelProperty(value = "电话")
    private String tel;
//    @Pattern(regexp = "^$|[1-9][0-9]{4,9}", message = "请输入正确QQ号")
    @ApiModelProperty(value = "qq号")
    private String qq;
    @ApiModelProperty(value = "海拔")
    private Double altitude;
    @ApiModelProperty(value = "生境类型")
    List<HabitatType> hab;
    @ApiModelProperty(value = "土壤类型")
    List<SoilType> soil;
    @ApiModelProperty(value = "气候类型")
    List<ClimaticType> cli;
    @ApiModelProperty(value = "地形类型")
    List<LandformType> land;
    @Valid
    @ApiModelProperty(value = "目标物种")
    List<SpeciesSurveyForm> speciesForms;

}
