/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 17:45
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 火箭图Vo
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "火箭图Vo")
@NoArgsConstructor
@AllArgsConstructor
public class RocketVo {

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "利用量")
    private String proStrawUtilize;

    @ApiModelProperty(value = "利用率")
    private String comprehensive;

    @ApiModelProperty(value = "可收集量")
    private String collectResource;
}
