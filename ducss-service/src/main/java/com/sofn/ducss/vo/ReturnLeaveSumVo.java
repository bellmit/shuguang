package com.sofn.ducss.vo;

import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/11/9 9:16
 * @Version 1.0
 * 还田离田汇总
 */
@Data
@ApiModel("还田离田汇总")
public class ReturnLeaveSumVo {

    @ApiModelProperty("id")
    @ExcelField(title = "", isShow = false)
    private String id;

    @ApiModelProperty("年份")
    @ExcelField(title = "", isShow = false)
    private String year;

    @ApiModelProperty("区域id")
    @ExcelField(title = "", isShow = false)
    private String areaId;

    @ApiModelProperty("秸秆类型")
    @ExcelField(title = "", isShow = false)
    private String strawType;

    @ApiModelProperty("秸秆名称")
    @ExcelField(title = "作物种类")
    private String strawName;

    @ApiModelProperty("直接还田量")
    @ExcelField(title = "直接还田量（吨）")
    private BigDecimal proStillField;

    @ApiModelProperty("还田比例or直接还田率")
    @ExcelField(title = "直接还田率（%）")
    private BigDecimal returnRatio;

    @ApiModelProperty("合计=主体合计+分散合计")
    @ExcelField(title = "合计",merge = "离田利用量")
    private BigDecimal allTotal;

    @ApiModelProperty("农户分散利用量合计")
    @ExcelField(title = "农户分散离田利用量",merge = "离田利用量")
    private BigDecimal disperseTotal;

    @ApiModelProperty("主体利用量合计")
    @ExcelField(title = "市场化主体利用量",merge = "离田利用量")
    private BigDecimal mainTotal;

    @ApiModelProperty(value = "可收集资源量", hidden = true)
    @ExcelField(title = "", isShow = false)
    private BigDecimal collectResource;

    @ApiModelProperty(name = "returnArea", value = "还田面积")
    @ExcelField(title = "", isShow = false)
    private BigDecimal returnArea;//还田面积

    @ApiModelProperty(name = "seedArea", value = "播种面积")
    @ExcelField(title = "", isShow = false)
    private BigDecimal seedArea;//播种面积


    public ReturnLeaveSumVo() {
        super();
        BigDecimal zero = BigDecimal.ZERO;

        this.proStillField = zero;
        this.returnRatio = zero;
        this.allTotal = zero;
        this.disperseTotal = zero;
        this.mainTotal = zero;
        this.collectResource = zero;
        this.returnArea = zero;
        this.seedArea = zero;
    }
}


