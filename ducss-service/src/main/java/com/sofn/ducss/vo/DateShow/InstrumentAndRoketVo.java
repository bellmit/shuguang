/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 17:49
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 仪表图和火箭图vo
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "仪表图和火箭图vo")
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentAndRoketVo {

    @ApiModelProperty(value = "占比")
    private String instrumentNum;

    @ApiModelProperty(value = "历年利用率")
    private List<RocketVo> list;
}
