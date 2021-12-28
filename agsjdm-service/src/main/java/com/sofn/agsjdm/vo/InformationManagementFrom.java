package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 9:53
 **/
@Data
@ApiModel("农业湿地信息管理表单对象")
public class InformationManagementFrom {
    @ApiModelProperty(value = "主键,新增不传,更新传")
    @Length(max = 60, message = "主键超长")
    private String id;
    @ApiModelProperty(value = "湿地区名称")
    @Length(max = 60, message = "湿地区名称超长")
    @NotBlank(message = "湿地区名称不能为空")
    private String wetAreasName;
    @ApiModelProperty(value = "湿地区编码")
    @Length(max = 60, message = "湿地区编码超长")
    @NotBlank(message = "湿地区编码不能为空")
    private String wetAreasCode;
    @ApiModelProperty(value = "湿地总面积")
    @NotNull(message = "湿地总面积不能为空")
    private Double totalWetlandArea;
    @ApiModelProperty(value = "湿地斑块数量")
    private String numberOfWetlandPatches;
    @ApiModelProperty(value = "湿地类1")
    @Length(max = 60, message = "湿地类1超长")
    private String wetlandClass1;
    @ApiModelProperty(value = "湿地类面积1")
    private Double wetlandClassArea1;
    @ApiModelProperty(value = "主要湿地型1")
    private String mainWetlandType1;
    @ApiModelProperty(value = "主要湿地型面积1")
    private Double mainWetlandTypeArea1;
    @ApiModelProperty(value = "湿地类2")
    @Length(max = 60, message = "湿地类2超长")
    private String wetlandClass2;
    @ApiModelProperty(value = "湿地类面积2")
    private Double wetlandClassArea2;
    @ApiModelProperty(value = "主要湿地型2")
    @Length(max = 60, message = "主要湿地型2超长")
    private String mainWetlandType2;
    @ApiModelProperty(value = "主要湿地型面积2")
    private Double mainWetlandTypeArea2;
    //湿地区分布-区域中文
    @NotBlank(message = "湿地区分布必填！")
    @Length(max = 100, message = "长度不能超过100！")
    @ApiModelProperty(value = "湿地区分布-区域中文", example = "广东省-深圳市-罗湖区")
    private String regionInCh;
    //湿地区分布-省
    @Length(max = 32, message = "长度不能超过32！")
    @ApiModelProperty(value = "湿地区分布-省", example = "440000")
    private String provinceCode;
    //湿地区分布-市
    @Length(max = 32, message = "长度不能超过32！")
    @ApiModelProperty(value = "湿地区分布-市", example = "440300")
    private String cityCode;
    //湿地区分布-区
    @Length(max = 32, message = "长度不能超过32！")
    @ApiModelProperty(value = "湿地区分布-区", example = "440303")
    private String areaCode;
    @ApiModelProperty(value = "中心点坐标经度")
    @NotNull(message = "中心点坐标经度为空")
    private Double longitude;
    @ApiModelProperty(value = "中心点坐标纬度")
    @NotNull(message = "中心点坐标纬度为空")
    private Double latitude;
    @ApiModelProperty(value = "所属二级流域")
    private String theSecondaryBasin;
    @ApiModelProperty(value = "河流级别")
    private String theRiverLevel;
    @ApiModelProperty(value = "平均海拔")
    private String meanSeaLevel;
    @ApiModelProperty(value = "水源补给情况")
    @Length(max = 60, message = "水源补给情况超长")
    private String waterSupply;
    @ApiModelProperty(value = "近海域海岸湿地")
    @Length(max = 60, message = "近海域海岸湿地超长")
    @NotBlank(message = "近海域海岸湿地不能为空")
    private String seaWetlands;
    @ApiModelProperty(value = "盐度%")
    private Double salinity;
    @ApiModelProperty(value = "水温")
    private String waterTemperature;
    @ApiModelProperty(value = "土地所有权")
    @Length(max = 60, message = "土地所有权超长")
    @NotBlank(message = "土地所有权不能为空")
    private String propertyInLand;
    @ApiModelProperty(value = "植被类型")
    @Length(max = 60, message = "植被类型超长")
    private String vegetationalForm;
    @ApiModelProperty(value = "植被类型面积")
    private Double vegetationalFormArea;
    @ApiModelProperty(value = "优势植物中文学名")
    @Length(max = 60, message = "优势植物中文学名超长")
    private String dominantPlantChineseName;
    @ApiModelProperty(value = "优势植物拉丁学名")
    @Length(max = 60, message = "优势植物拉丁学名超长")
    private String dominantPlantLatinName;
    @ApiModelProperty(value = "科名")
    @Length(max = 60, message = "科名超长")
    private String familyName;
}
