package com.sofn.ducss.vo.checkcolor;

import com.sofn.ducss.vo.StrawProduceResVo;
import com.sofn.ducss.vo.StrawProduceResVo2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 检查秸秆产生量
 * ColorState为颜色标识
 */
@Data
@ApiModel(value = "秸秆产生量 , 颜色标识，1超过阈值 2 低于阈值 0 没超过阈值")
public class CheckStrawProduceVo extends CheckStrawVo {

    /**
     * 产生量
     */
    @ApiModelProperty(value = "产生量")
    private BigDecimal theoryResource;

    @ApiModelProperty(value = "产生量颜色标识")
    private String theoryResourceColorState;

    private String theoryResourceMessage;

    /**
     * 产生量占比
     */
    @ApiModelProperty(value = "产生量占比")
    private BigDecimal theoryResourceNoun;

    @ApiModelProperty(value = "产生量占比颜色标识")
    private String theoryResourceNounColorState;

    private String theoryResourceNounMessage;

    /**
     * 可收集量
     */
    @ApiModelProperty(value = "可收集量")
    private BigDecimal collectResource;

    @ApiModelProperty(value = "可收集量颜色标识")
    private String collectResourceColorState;

    private String collectResourceMessage;

    /**
     * 可收集量占比
     */
    @ApiModelProperty(value = "可收集量占比")
    private BigDecimal collectResourceNoun;

    @ApiModelProperty(value = "可收集量占比颜色标识")
    private String collectResourceNounColorState;

    private String collectResourceNounMessage;

    /**
     * 粮食产量
     */
    @ApiModelProperty(value = " 粮食产量")
    private BigDecimal grainYield;

    @ApiModelProperty(value = "粮食产量颜色标识")
    private String grainYieldColorState;

    private String grainYieldMessage;

    /**
     * 播种面积
     */
    @ApiModelProperty(value = " 播种面积")
    private BigDecimal seedArea;

    @ApiModelProperty(value = " 播种面积颜色标识")
    private String seedAreaColorState;

    private String seedAreaMessage;

    /**
     * 将 StrawProduceResVo 适配为   CheckStrawProduceVo
     * @param strawProduceResVo StrawProduceResVo
     * @return CheckStrawProduceVo
     */
    public static CheckStrawProduceVo getCheckStrawProduceVo(StrawProduceResVo strawProduceResVo){
        CheckStrawProduceVo checkStrawProduceVo = new CheckStrawProduceVo();
        if(strawProduceResVo != null){
            checkStrawProduceVo.setStrawType(strawProduceResVo.getStrawType());
            checkStrawProduceVo.setStrawTypeName(strawProduceResVo.getStrawName());
            checkStrawProduceVo.setTheoryResource(strawProduceResVo.getTheoryResource());
            checkStrawProduceVo.setCollectResource(strawProduceResVo.getCollectResource());
            checkStrawProduceVo.setGrainYield(strawProduceResVo.getGrainYield());
            checkStrawProduceVo.setSeedArea(strawProduceResVo.getSeedArea());
        }
        return checkStrawProduceVo;
    }


    /**
     * 将StrawProduceResVo2适配成CheckStrawProduceVo
     * @param strawProduceResVo2 StrawProduceResVo2
     * @return  CheckStrawProduceVo
     */
    public static CheckStrawProduceVo getCheckStrawProduceVo(StrawProduceResVo2 strawProduceResVo2){
        CheckStrawProduceVo checkStrawProduceVo = new CheckStrawProduceVo();
        if(strawProduceResVo2 != null){
            checkStrawProduceVo.setStrawType(strawProduceResVo2.getStrawType());
            checkStrawProduceVo.setStrawTypeName(strawProduceResVo2.getStrawName());
            checkStrawProduceVo.setTheoryResource(strawProduceResVo2.getTheoryResource());
            checkStrawProduceVo.setTheoryResourceNoun(strawProduceResVo2.getTheoryResourceRate());
            checkStrawProduceVo.setCollectResource(strawProduceResVo2.getCollectResource());
            checkStrawProduceVo.setCollectResourceNoun(strawProduceResVo2.getCollectResourceRate());
            checkStrawProduceVo.setGrainYield(strawProduceResVo2.getGrainYield());
            checkStrawProduceVo.setSeedArea(strawProduceResVo2.getSeedArea());
        }
        return checkStrawProduceVo;

    }

}
