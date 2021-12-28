package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Zzx
 * @Date 2021/6/17 17:48
 */
@Data
@ApiModel(value = "五料化占比")
@NoArgsConstructor
@AllArgsConstructor
public class RoundVo {

    @ApiModelProperty("五料化名称")
    private String name;

    @ApiModelProperty("五料化值")
    private BigDecimal value;
}
