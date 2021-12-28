package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.elasticsearch.search.DocValueFormat;

import java.math.BigDecimal;

@Data
@TableName("straw_produce.sql")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

public class StrawProduce extends Model<StrawProduce> {
    private String id;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "areaId", value = "县ID")
    private String areaId;

    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "theoryResource", value = "理论资源量,等同于秸秆产生量")
    private BigDecimal theoryResource;

    @ApiModelProperty(name = "collectResource", value = "可收集资源量")
    private BigDecimal collectResource;

    /**
     * zy扩展 增加播种面积，粮食产量字段
     */

    @ApiModelProperty(name = "grainYield", value = "粮食产量")
    private BigDecimal grainYield;// 粮食产量


    @ApiModelProperty(name = "seedArea", value = "播种面积")
    private BigDecimal seedArea;//播种面积


    public StrawProduce() {
        super();
        BigDecimal zero = BigDecimal.ZERO;
        this.theoryResource = zero;
        this.collectResource = zero;
        this.grainYield = zero;
        this.seedArea = zero;
    }
}