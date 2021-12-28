package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.InvestigatContent;
import com.sofn.agzirdd.model.InvestigatExaminaRecord;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpeciesInvestigationVo extends SpeciesInvestigation {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "investigatContentVo", value = "物种调查模块-调查内容Vo")
    private InvestigatContentVo investigatContentVo;

    @ApiModelProperty(name = "investigatExaminaRecordList", value = "物种调查模块-审核记录List")
    private List<InvestigatExaminaRecord> investigatExaminaRecordList;


    public SpeciesInvestigationVo(){}

    /**
     * 将vo转换为po对象
     */
    public static SpeciesInvestigation getSpeciesInvestigation(SpeciesInvestigationVo speciesInvestigationVo){
        SpeciesInvestigation speciesInvestigation = new SpeciesInvestigation();
        BeanUtils.copyProperties(speciesInvestigationVo,speciesInvestigation);
        return speciesInvestigation;
    }
    /**
     * po转换为vo
     */
    public static SpeciesInvestigationVo getSpeciesInvestigationVo(SpeciesInvestigation speciesInvestigation){
        SpeciesInvestigationVo speciesInvestigationVo = new SpeciesInvestigationVo();
        BeanUtils.copyProperties(speciesInvestigation,speciesInvestigationVo);
        return speciesInvestigationVo;
    }
}
