package com.sofn.ducss.vo;

import com.sofn.ducss.util.BigDecimalUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 农作物秸秆产生量汇总数据实体
 */
@Data
@ApiModel(value = "农作物秸秆产生量汇总数据实体")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StrawProduceResVo {

    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "strawName", value = "秸秆名称")
    private String strawName;

    @ApiModelProperty(name = "theoryResource", value = "产生量")
    private BigDecimal theoryResource; // 产生量=粮食产量*草谷比

    @ApiModelProperty(name = "collectResource", value = "可收集量")
    private BigDecimal collectResource;// 可收集量=产生量*收集系数


    /**
     * 粮食产量
     */
    private BigDecimal grainYield;

    /**
     * 播种面积
     */
    private BigDecimal seedArea;

    public StrawProduceResVo() {
        super();
        BigDecimal zero = BigDecimal.ZERO;
        this.theoryResource=zero;
        this.collectResource=zero;
    }
}
