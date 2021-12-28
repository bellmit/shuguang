/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 9:56
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 利用量,利用率柱状图,折线图返回数据VO
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "利用量,利用率柱状图,折线图返回数据VO")
@NoArgsConstructor
@AllArgsConstructor
public class ColumnLineVo {

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "利用量")
    private String proStrawUtilize;

    @ApiModelProperty(value = "利用率")
    private String comprehensive;
}
