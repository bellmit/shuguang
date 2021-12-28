package com.sofn.agzirdd.excelmodel;

import com.sofn.agzirdd.model.MonitorCompanionSpecies;
import com.sofn.agzirdd.model.MonitorInvasiveAlienSpecies;
import com.sofn.agzirdd.model.SpeciesMonitor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class SpeciesMonitorExcel extends SpeciesMonitor {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "hasAlienSpeciesName", value = "是否存在入侵物种")
    private String hasAlienSpeciesName;

    @ApiModelProperty(name = "speciesMonitorId", value = "物种监测表id")
    private String speciesMonitorId;

    @ApiModelProperty(name = "area", value = "发生面积")
    private String area;

    @ApiModelProperty(name = "typeName", value = "生境类型")
    private String typeName;

    @ApiModelProperty(name = "harmObject", value = "危害对象")
    private String harmObject;

    @ApiModelProperty(name = "harmMethod", value = "危害方式")
    private String harmMethod;

    @ApiModelProperty(name = "damageDegreeName", value = "危害程度:0-轻度；1-中度；2-重度")
    private String damageDegreeName;

    @ApiModelProperty(name = "economicLoss", value = "经济损失")
    private String economicLoss;

    @ApiModelProperty(name = "hasMeasuresName", value = "是否有防控措施:0-否，1-是")
    private String hasMeasuresName;

    @ApiModelProperty(name = "preventionArea", value = "防治面积")
    private double preventionArea;

    @ApiModelProperty(name = "preventionCost", value = "防治成本")
    private double preventionCost;

    @ApiModelProperty(name = "describe", value = "防治描述")
    private String describe;

    @ApiModelProperty(name = "result", value = "控制效果")
    private String result;

    @ApiModelProperty(name = "hasUtilizeName", value = "是否进行利用:0-否，1-是")
    private String hasUtilizeName;

    @ApiModelProperty(name = "utilizeDescription", value = "利用描述")
    private String utilizeDescription;

    @ApiModelProperty(name = "utilizeManner", value = "利用方式")
    private String utilizeManner;

    @ApiModelProperty(name = "summary", value = "结果概述")
    private String summary;

    @ApiModelProperty(name = "proposal", value = "问题和建议")
    private String proposal;

    @ApiModelProperty(name = "resultImgUrl", value = "效果图Url")
    private String resultImgUrl;

    @ApiModelProperty(name = "utilizeImgUrl", value = "利用方式图片Url")
    private String utilizeImgUrl;

    @ApiModelProperty(name = "workImgUrl", value = "工作照片Url")
    private String workImgUrl;

    @ApiModelProperty(name = "speciesImgUrl", value = "物种照片Url")
    private String speciesImgUrl;

    @ApiModelProperty(name = "monitorInvasiveAlienSpeciesList", value = "物种监测模块-入侵物种List")
    private List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList;

    @ApiModelProperty(name = "monitorCompanionSpeciesList", value = "物种监测模块-伴生物种List")
    private List<MonitorCompanionSpecies> monitorCompanionSpeciesList;
}
