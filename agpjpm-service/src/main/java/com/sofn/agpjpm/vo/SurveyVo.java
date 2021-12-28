package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-09 18:11
 */
@Data
@ApiModel(value = "野生植物调查数据详情对象")
public class SurveyVo {
    @ApiModelProperty(value = "主键，新增不传修改必传")
    private String id;		//主键
    @ApiModelProperty(value = "调查日期")
    private Date surveyDate;		//调查日期
    @ApiModelProperty(value = "调查点编号")
    private String  surveyNum;	//调查点编号
    @ApiModelProperty(value = "省")
    private String  province;	//省
    @ApiModelProperty(value = "省名")
    private String  provinceName;
    @ApiModelProperty(value = "市")
    private String city;		//市
    @ApiModelProperty(value = "市名")
    private String cityName;	//	市名
    @ApiModelProperty(value = "县")
    private String county;		//县
    @ApiModelProperty(value = "县名")
    private String countyName;//		县名
    @ApiModelProperty(value = "调查人")
    private String surveyor;	//调查人
    @ApiModelProperty(value = "电话")
    private String  tel;	//电话
    @ApiModelProperty(value = "qq号")
    private String qq;//	qq号
    @ApiModelProperty(value = "海拔")
    private String altitude;
    @ApiModelProperty(value = "生境类型")
    List<String> hab;
    @ApiModelProperty(value = "土壤类型")
    List<String> soil;
    @ApiModelProperty(value = "气候类型")
    List<String> cli;
    @ApiModelProperty(value = "地形类型")
    List<String> land;
    @ApiModelProperty(value = "生境类型")
    String habName;
    @ApiModelProperty(value = "土壤类型")
    String soilName;
    @ApiModelProperty(value = "气候类型")
    String cliName;
    @ApiModelProperty(value = "地形类型")
    String landName;
    @ApiModelProperty(value = "物种信息")
    List<SpeciesSurveyVo> speciesVosList;
}
