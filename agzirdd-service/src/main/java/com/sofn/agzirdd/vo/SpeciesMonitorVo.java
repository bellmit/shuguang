package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Description: 物种监测模块-监测基本信息Vo
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpeciesMonitorVo extends SpeciesMonitor {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "monitorContentVo", value = "物种监测模块-监测内容Vo")
    private MonitorContentVo monitorContentVo;

    @ApiModelProperty(name = "monitorInvasiveAlienSpeciesList", value = "物种监测模块-入侵物种List")
    private List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList;

    @ApiModelProperty(name = "monitorCompanionSpeciesList", value = "物种监测模块-伴生物种List")
    private List<MonitorCompanionSpecies> monitorCompanionSpeciesList;

    @ApiModelProperty(name = "monitorExaminaRecordList", value = "物种监测模块-监测审核记录List")
    private List<MonitorExaminaRecord> monitorExaminaRecordList;


    public SpeciesMonitorVo(){}

    /**
     * 将vo转换为po对象
     */
    public static SpeciesMonitor getSpeciesMonitor(SpeciesMonitorVo speciesMonitorVo){
        SpeciesMonitor speciesMonitor = new SpeciesMonitor();
        BeanUtils.copyProperties(speciesMonitorVo,speciesMonitor);
        return speciesMonitor;
    }
    /**
     * po转换为vo
     */
    public static SpeciesMonitorVo getSpeciesMonitorVo(SpeciesMonitor speciesMonitor){
        SpeciesMonitorVo speciesMonitorVo = new SpeciesMonitorVo();
        BeanUtils.copyProperties(speciesMonitor,speciesMonitorVo);
        return speciesMonitorVo;
    }
}
