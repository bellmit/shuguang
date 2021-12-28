package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WelcomeAreaVo {

    @ApiModelProperty(value="区域代码")
    private String areaId;

    @ApiModelProperty(value="值")
    private BigDecimal val;

    public WelcomeAreaVo(String areaId, BigDecimal val) {
        this.areaId = areaId;
        this.val=val;
    }
}
