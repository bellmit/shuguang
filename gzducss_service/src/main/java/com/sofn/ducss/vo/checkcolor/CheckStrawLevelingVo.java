package com.sofn.ducss.vo.checkcolor;

import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.enums.CheckColorEnum;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.vo.ReturnLeaveSumVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 计算还田离田量颜色标识
 */
@Data
@ApiModel(value = "还田离田量, 颜色标识，1超过阈值 2 低于阈值 0 没超过阈值")
public class CheckStrawLevelingVo extends CheckStrawVo {

    /**
     * 农户分散利用量
     */
    @ApiModelProperty(value = "农户分散利用量")
    private BigDecimal disperseTotal;

    /**
     * 农户分散利用量颜色标识
     */
    @ApiModelProperty(value = "农户分散利用量颜色标识")
    private String disperseTotalColorState;

    private String disperseTotalMessage;



    /**
     * 主体规模化利用量
     */
    @ApiModelProperty(value = "主体规模化利用量")
    private BigDecimal mainTotal;

    /**
     * 主体规模化利用量颜色标识
     */
    @ApiModelProperty(value = "主体规模化利用量颜色标识")
    private String mainTotalColorState;

    private String mainTotalMessage;

    /**
     * 直接还田率
     */
    @ApiModelProperty(value = "直接还田率")
    private BigDecimal  returnRatio;

    /**
     * 直接还田率颜色标识
     */
    @ApiModelProperty(value = "直接还田率颜色标识")
    private String returnRatioColorState;

    private String returnRatioMessage;

    /**
     * 直接还田量
     */
    @ApiModelProperty(value = "直接还田量")
    private BigDecimal proStillField;

    /**
     * 直接还田量颜色标识
     */
    @ApiModelProperty(value = "直接还田量颜色标识")
    private String proStillFieldColorState;

    private String proStillFieldMessage;

    /**
     * 合计
     */
    @ApiModelProperty(value = "合计")
    private BigDecimal count;

    /**
     * 合计颜色标识
     */
    @ApiModelProperty(value = "合计颜色标识")
    private String countColorState;

    private String countMessage;


    /**
     * 将StrawUtilizeSum适配成 CheckStrawLevelingVo
     * @param strawUtilizeSum  StrawUtilizeSum
     * @return  CheckStrawLevelingVo
     */
    public static CheckStrawLevelingVo getCheckStrawLevelingVo(StrawUtilizeSum strawUtilizeSum){
        if(strawUtilizeSum == null){
            throw new SofnException("StrawUtilizeSum不能为空");
        }
        CheckStrawLevelingVo checkStrawLevelingVo = new CheckStrawLevelingVo();
        checkStrawLevelingVo.setStrawType(strawUtilizeSum.getStrawType());
        checkStrawLevelingVo.setStrawTypeName(strawUtilizeSum.getStrawName());
        // 颜色初始化为0 表示没有发生变化
        checkStrawLevelingVo.setDisperseTotal(BigDecimalUtil.valueIsNull(strawUtilizeSum.getDisperseTotal()) );
        checkStrawLevelingVo.setDisperseTotalColorState(CheckColorEnum.COLOR_UNCHANGE.getCode());
        checkStrawLevelingVo.setMainTotal(BigDecimalUtil.valueIsNull(strawUtilizeSum.getMainTotal()));
        checkStrawLevelingVo.setMainTotalColorState(CheckColorEnum.COLOR_UNCHANGE.getCode());
        checkStrawLevelingVo.setReturnRatio(strawUtilizeSum.getReturnRatio());
        checkStrawLevelingVo.setReturnRatioColorState(CheckColorEnum.COLOR_UNCHANGE.getCode());
        checkStrawLevelingVo.setProStillField(strawUtilizeSum.getProStillField());
        checkStrawLevelingVo.setProStillFieldColorState(CheckColorEnum.COLOR_UNCHANGE.getCode());
        BigDecimal count = checkStrawLevelingVo.getDisperseTotal().add(checkStrawLevelingVo.getMainTotal());
        checkStrawLevelingVo.setCount(count);
        checkStrawLevelingVo.setCountColorState(CheckColorEnum.COLOR_UNCHANGE.getCode());
        return checkStrawLevelingVo;
    }

    /**
     * 将ReturnLeaveSumVo适配成CheckStrawLevelingVo
     * @param returnLeaveSumVo  ReturnLeaveSumVo
     * @return  CheckStrawLevelingVo
     */
    public static CheckStrawLevelingVo getCheckStrawLevelingVo(ReturnLeaveSumVo returnLeaveSumVo) {
        CheckStrawLevelingVo checkStrawLevelingVo = new CheckStrawLevelingVo();
        if (returnLeaveSumVo != null) {
            checkStrawLevelingVo.setStrawType(returnLeaveSumVo.getStrawType());
            checkStrawLevelingVo.setStrawTypeName(returnLeaveSumVo.getStrawName());
            checkStrawLevelingVo.setDisperseTotal(returnLeaveSumVo.getDisperseTotal());
            checkStrawLevelingVo.setMainTotal(returnLeaveSumVo.getMainTotal());
            checkStrawLevelingVo.setReturnRatio(returnLeaveSumVo.getReturnRatio());
            checkStrawLevelingVo.setProStillField(returnLeaveSumVo.getProStillField());
            checkStrawLevelingVo.setCount(returnLeaveSumVo.getAllTotal());
        }
        return checkStrawLevelingVo;
    }



}
