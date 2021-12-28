package com.sofn.common.excel.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 国控点，备选点位导入 bean
 * @Author: zhk
 * @date: Created in 2019/12/9 9:43
 */
@Data
@TableName(value = "PMLCSC_MASTER_SITE")
@ApiModel(value = "SiteImport", description = "点位导入")
public class SiteImport extends BaseModel<SiteImport> {


    /**
     * 当前点位编号
     */
    @ExcelField(title = "当前点位编号")
    @ApiModelProperty(name = "presentSiteCode", value = "当前点位编号")
    private String presentSiteCode;

    /**
     * 初始点位编号
     */
    @ExcelField(title = "初始点位编号")
    @ApiModelProperty(name = "initialSiteCode", value = "初始点位编号")
    private String initialSiteCode;


    /**
     * 省
     */
    @ExcelField(title = "省")
    @ApiModelProperty(name = "province", value = "省")
    private String province;

    /**
     * 市
     */
    @ExcelField(title = "市")
    @ApiModelProperty(name = "city", value = "市")
    private String city;

    /**
     * 县
     */
    @ExcelField(title = "县")
    @ApiModelProperty(name = "county", value = "县")
    private String county;


    /**
     * 行政区划代码
     */
    @ExcelField(title = "行政区划代码")
    @ApiModelProperty(name = "regionCode", value = "行政区划代码")
    private String regionCode;

    /**
     * 乡
     */
    @ExcelField(title = "乡")
    @ApiModelProperty(name = "countryside", value = "乡")
    private String countryside;


    /**
     * 村
     */
    @ExcelField(title = "村")
    @ApiModelProperty(name = "village", value = "村")
    private String village;

    /**
     * 组
     */
    @ExcelField(title = "组")
    @ApiModelProperty(name = "groups", value = "组")
    private String groups;

    /**
     * 东经
     */
    @ExcelField(title = "东经")
    @ApiModelProperty(name = "eastLogitude", value = "东经")
    private Double eastLongitude;

    /**
     * 北纬
     */
    @ExcelField(title = "北纬")
    @ApiModelProperty(name = "northLatitude", value = "北纬")
    private Double northLatitude;

    /**
     * 海拔高度
     */
    @ExcelField(title = "海拔高度")
    @ApiModelProperty(name = "altitude", value = "海拔高度")
    private Double altitude;

    /**
     * 土地利用方式
     */
    @ExcelField(title = "土地利用方式")
    @ApiModelProperty(name = "soilUseType", value = "土地利用方式")
    private String soilUseType;

    /**
     * 土壤类型
     */
    @ExcelField(title = "土壤类型")
    @ApiModelProperty(name = "soilType", value = "土壤类型")
    private String soilType;

    /**
     * 土壤亚类
     */
    @ExcelField(title = "土壤亚类")
    @ApiModelProperty(name = "soilSubgenus", value = "土壤亚类")
    private String soilSubgenus;

    /**
     * 成土母质
     */
    @ExcelField(title = "成土母质")
    @ApiModelProperty(name = "parentMaterial", value = "成土母质")
    private String parentMaterial;

    /**
     * 主要农作物类别1
     */
    @ExcelField(title = "主要农作物类别1")
    @ApiModelProperty(name = "cropTypeFirst", value = "主要农作物类别1")
    private String cropTypeFirst;

    /**
     * 主要农作物类别2
     */
    @ExcelField(title = "主要农作物类别2")
    @ApiModelProperty(name = "cropTypeSecond", value = "主要农作物类别2")
    private String cropTypeSecond;

    /**
     * 国控点编码
     */
    @ExcelField(title = "国控点编码")
    @ApiModelProperty(name = "masterSiteCode", value = "备选点必填，关联国控点的PresentSiteCode")
    private String masterSiteCode;


    /**
     * 状态
     */
    @ApiModelProperty(name = "state", value = "状态：1禁用、2启用")
    private String state;


    /**
     * 任务期数
     */
    @ApiModelProperty(name = "taskCategory", value = "任务期数")
    private String taskCategory;

    /**
     * 监测点类型
     */
    @ApiModelProperty(name = "monitorType", value = "监测点类型: 1国控点、2备选点")
    private String monitorType;

    /**
     * 只要农作物类别1Code
     */
    @ApiModelProperty(name = "cropTypeFirstCode", value = "只要农作物类别1Code")
    private String cropTypeFirstCode;

    /**
     * 只要农作物类别2Code
     */
    @ApiModelProperty(name = "cropTypeSecondCode", value = "只要农作物类别2Code")
    private String cropTypeSecondCode;
}
