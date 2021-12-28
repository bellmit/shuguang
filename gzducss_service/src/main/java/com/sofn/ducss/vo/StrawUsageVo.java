package com.sofn.ducss.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sofn.ducss.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Zzx
 * @Date 2021/6/16 9:40
 */
@Data
public class StrawUsageVo implements Serializable {

    private static final long serialVersionUID = -8834176274204383683L;

    @ApiModelProperty("作物类型")
    @ExcelField(title = "", isShow = false)
    private String strawType;

    @ApiModelProperty("作物名称")
    @ExcelField(title = "秸秆类型")
    private String strawName;

    @ApiModelProperty("播种面积")
    @ExcelField(title = "播种面积(亩)")
    private BigDecimal seedArea;

    @ApiModelProperty("产生量")
    @ExcelField(title = "秸秆产生量(吨)")
    private BigDecimal theoryResource;

    @ApiModelProperty("直接还田量")
    @ExcelField(title = "直接还田量(吨)")
    private BigDecimal returnResource;

    @ApiModelProperty("还田方式")
    @ExcelField(title = "还田方式")
    private String returnType;

    @ApiModelProperty("离田利用量")
    @ExcelField(title = "离田利用量(吨)")
    private BigDecimal leaveNumber;

    @ApiModelProperty("离田运输方式")
    @ExcelField(title = "离田运输方式")
    private String leavingType;

    @ApiModelProperty("运输补贴")
    @ExcelField(title = "运输补贴")
    private BigDecimal transportAmount;

    @ApiModelProperty("离田利用方式")
    @ExcelField(title = "离田利用方式")
    private String leaveType;

    @ApiModelProperty("综合利用率")
    @ExcelField(title = "综合利用率(%)")
    private BigDecimal totolRate;


    /**
     * JsonIgnore
     */
    @ApiModelProperty("可收集量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal collectResource;

    @ApiModelProperty("秸秆利用量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal strawUtilization;

    @ApiModelProperty("肥料化利用量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal fertilize;

    @ApiModelProperty("饲料化利用量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal feed;

    @ApiModelProperty("燃料化利用量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal fuelled;

    @ApiModelProperty("基料化利用量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal baseMat;

    @ApiModelProperty("原料化利用量")
    @JsonIgnore
    @ExcelField(title = "", isShow = false)
    private BigDecimal materialization;

    public StrawUsageVo() {
        this.seedArea = BigDecimal.ZERO;
        this.theoryResource = BigDecimal.ZERO;
        this.returnResource = BigDecimal.ZERO;
        this.leaveNumber = BigDecimal.ZERO;
        this.totolRate = BigDecimal.ZERO;
        this.transportAmount = BigDecimal.ZERO;
        this.collectResource = BigDecimal.ZERO;
        this.strawUtilization = BigDecimal.ZERO;
        this.fertilize = BigDecimal.ZERO;
        this.feed = BigDecimal.ZERO;
        this.fuelled = BigDecimal.ZERO;
        this.baseMat = BigDecimal.ZERO;
        this.materialization = BigDecimal.ZERO;
    }
}
