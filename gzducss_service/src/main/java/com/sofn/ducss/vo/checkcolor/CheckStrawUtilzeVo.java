package com.sofn.ducss.vo.checkcolor;

import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.vo.StrawUtilizeResVo3;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 检查秸秆利用量颜色标识
 * 数据来自StrawUtilizeSum
 */
@Data
@ApiModel(value = "秸秆利用量 , 颜色标识，1超过阈值 2 低于阈值 0 没超过阈值")
public class CheckStrawUtilzeVo extends CheckStrawVo{

    /**
     * 秸秆利用量
     */
    @ApiModelProperty(value = "秸秆利用量")
    private BigDecimal proStrawUtilize;

    @ApiModelProperty(value = "秸秆利用量颜色标识")
    private String proStrawUtilizeColorState;

    private String proStrawUtilizeMessage;

    /**
     * 综合利用率
     */
    @ApiModelProperty(value = "综合利用率")
    private BigDecimal comprehensive;

    @ApiModelProperty(value = "综合利用率颜色标识")
    private String comprehensiveColorState;

    private String comprehensiveMessage;

    /**
     * 合计
     */
    @ApiModelProperty(value = "合计")
    private BigDecimal count;

    @ApiModelProperty(value = "合计颜色标识")
    private String countColorState;

    private String countMessage;

    /**
     * 肥料化
     */
    @ApiModelProperty(value = "肥料化")
    private BigDecimal newFertilising;

    @ApiModelProperty(value = "肥料化颜色标识")
    private String newFertilisingColorState;

    private String newFertilisingMessage;

    /**
     * 饲料化
     */
    @ApiModelProperty(value = "饲料化")
    private BigDecimal newForage;

    @ApiModelProperty(value = "饲料化颜色标识")
    private String newForageColorState;

    private String newForageMessage;

    /**
     * 燃料化
     */
    @ApiModelProperty(value = "燃料化")
    private BigDecimal newFuel;

    @ApiModelProperty(value = "燃料化颜色标识")
    private String newFuelColorState;

    private String newFuelMessage;

    /**
     * 基料化
     */
    @ApiModelProperty(value = "基料化")
    private BigDecimal newBase;

    @ApiModelProperty(value = "基料化颜色标识")
    private String newBaseColorState;

    private String newBaseMessage;

    /**
     * 原料化
     */
    @ApiModelProperty(value = "原料化")
    private BigDecimal newMaterial;

    @ApiModelProperty(value = "原料化颜色标识")
    private String newMaterialColorState;

    private String newMaterialMessage;

    /**
     * 综合利用指数
     */
    @ApiModelProperty(value = "综合利用指数")
    private BigDecimal comprehensiveIndex;

    @ApiModelProperty(value = "综合利用指数颜色标识")
    private String comprehensiveIndexColorState;

    private String comprehensiveIndexMessage;

    /**
     * 产业化利用能力指数
     */
    @ApiModelProperty(value = "产业化利用能力指数")
    private BigDecimal industrializationIndex;

    @ApiModelProperty(value = "产业化利用能力指数颜色标识")
    private String industrializationIndexColorState;

    private String industrializationIndexMessage;

    /**
     * 将StrawUtilizeSum适配成CheckStrawUtilzeVo
     *
     * @param strawUtilizeSum StrawUtilizeSum
     * @return CheckStrawUtilzeVo
     */
    public static CheckStrawUtilzeVo getCheckStrawUtilzeVo(StrawUtilizeSum strawUtilizeSum) {
        CheckStrawUtilzeVo checkStrawUtilzeVo = new CheckStrawUtilzeVo();
        if (strawUtilizeSum != null) {
            checkStrawUtilzeVo.setStrawType(strawUtilizeSum.getStrawType());
            checkStrawUtilzeVo.setStrawTypeName(strawUtilizeSum.getStrawName());
            // 秸秆利用量
            checkStrawUtilzeVo.setProStrawUtilize(BigDecimalUtil.valueIsNull(strawUtilizeSum.getProStrawUtilize()));
            // 综合利用率
            checkStrawUtilzeVo.setComprehensive(BigDecimalUtil.valueIsNull(strawUtilizeSum.getComprehensive()));
            // 肥料化 =  市场主体肥料 + 分散利用肥料 +  直接还田量
            BigDecimal newFertilising = BigDecimalUtil.valueIsNull(strawUtilizeSum.getMainFertilising())
                    .add(BigDecimalUtil.valueIsNull(strawUtilizeSum.getDisperseFertilising()))
                    .add(BigDecimalUtil.valueIsNull(strawUtilizeSum.getProStillField()));
            checkStrawUtilzeVo.setNewFertilising(newFertilising);
            // 饲料化 = 市场主体饲料 +  分散利用饲料
            BigDecimal newforage = BigDecimalUtil.valueIsNull(strawUtilizeSum.getMainForage())
                    .add(BigDecimalUtil.valueIsNull(strawUtilizeSum.getDisperseForage()));
            checkStrawUtilzeVo.setNewForage(newforage);
            // 燃料化 = 市场主体燃料 + 分散利用燃料
            BigDecimal newFuel = BigDecimalUtil.valueIsNull(strawUtilizeSum.getMainFuel())
                    .add(BigDecimalUtil.valueIsNull(strawUtilizeSum.getDisperseFuel()));
            checkStrawUtilzeVo.setNewFuel(newFuel);
            // 基料化 =  市场主体基料 +  分散利用基料
            BigDecimal newBase = BigDecimalUtil.valueIsNull(strawUtilizeSum.getMainBase())
                    .add(BigDecimalUtil.valueIsNull(strawUtilizeSum.getDisperseBase()));
            checkStrawUtilzeVo.setNewBase(newBase);
            // 原料化 = 市场主体原料 + 分散利用原料
            BigDecimal newMaterial = BigDecimalUtil.valueIsNull(strawUtilizeSum.getMainMaterial())
                    .add(BigDecimalUtil.valueIsNull(strawUtilizeSum.getDisperseMaterial()));
            checkStrawUtilzeVo.setNewMaterial(newMaterial);
            // 合计=上面几个化加起来
            BigDecimal count = newFertilising.add(newforage).add(newFuel).add(newBase).add(newMaterial);
            checkStrawUtilzeVo.setCount(count);
            // 综合利用指数
            checkStrawUtilzeVo.setComprehensiveIndex(BigDecimalUtil.valueIsNull(strawUtilizeSum.getComprehensiveIndex()));
            checkStrawUtilzeVo.setIndustrializationIndex(BigDecimalUtil.valueIsNull(strawUtilizeSum.getIndustrializationIndex()));
        }
        return checkStrawUtilzeVo;
    }


    /**
     * 将StrawUtilizeResVo3转换为CheckStrawUtilzeVo
     * @param strawUtilizeResVo3   StrawUtilizeResVo3
     * @return  CheckStrawUtilzeVo
     */
    public static CheckStrawUtilzeVo getCheckStrawUtilzeVo(StrawUtilizeResVo3 strawUtilizeResVo3) {
        CheckStrawUtilzeVo checkStrawUtilzeVo = new CheckStrawUtilzeVo();
        if(strawUtilizeResVo3 != null){
            checkStrawUtilzeVo.setStrawType(strawUtilizeResVo3.getStrawType());
            checkStrawUtilzeVo.setStrawTypeName(strawUtilizeResVo3.getStrawName());
            checkStrawUtilzeVo.setProStrawUtilize(strawUtilizeResVo3.getStrawUsage());
            checkStrawUtilzeVo.setComprehensive(strawUtilizeResVo3.getComprehensiveRate());
            checkStrawUtilzeVo.setCount(strawUtilizeResVo3.getAllTotal());
            checkStrawUtilzeVo.setNewFertilising(strawUtilizeResVo3.getFertilizer());
            checkStrawUtilzeVo.setNewForage(strawUtilizeResVo3.getFeed());
            checkStrawUtilzeVo.setNewFuel(strawUtilizeResVo3.getFuel());
            checkStrawUtilzeVo.setNewBase(strawUtilizeResVo3.getBasic());
            checkStrawUtilzeVo.setNewMaterial(strawUtilizeResVo3.getRawMaterial());
            checkStrawUtilzeVo.setComprehensiveIndex(strawUtilizeResVo3.getComprehensiveIndex());
            checkStrawUtilzeVo.setIndustrializationIndex(strawUtilizeResVo3.getIndustrializationIndex());
        }
        return checkStrawUtilzeVo;

    }


}
