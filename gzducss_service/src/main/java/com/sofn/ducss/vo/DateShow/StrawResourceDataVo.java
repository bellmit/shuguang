package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 数据展示首页(秸秆资源数据)
 *
 * @author liuqiang
 * @date 2020/11/5
 */
@Data
@ApiModel(value = "数据展示首页(秸秆资源数据Vo)")
@NoArgsConstructor
@AllArgsConstructor
public class StrawResourceDataVo {
    /**
    *
    产生量=粮食产量*草谷比
     */
    @ApiModelProperty(name = "theoryResource", value = "产生量")
    private BigDecimal theoryResource;

    /**
     *
     可收集量=产生量*收集系数
     */
    @ApiModelProperty(name = "collectResource", value = "可收集量")
    private BigDecimal collectResource;

    @ApiModelProperty(name = "proStrawUtilize", value = "利用量")
    private BigDecimal proStrawUtilize;

    @ApiModelProperty(name = "comprehensive", value = "利用率")
    private BigDecimal comprehensive;

    @ApiModelProperty(name = "theoryResourceChange", value = "产生量变化")
    private BigDecimal theoryResourceChange;

    @ApiModelProperty(name = "collectResourceChange", value = "可收集量变化")
    private BigDecimal collectResourceChange;

    @ApiModelProperty(name = "proStrawUtilizeChange", value = "利用量变化")
    private BigDecimal proStrawUtilizeChange;

    @ApiModelProperty(name = "comprehensiveChange", value = "利用率变化")
    private BigDecimal comprehensiveChange;
}