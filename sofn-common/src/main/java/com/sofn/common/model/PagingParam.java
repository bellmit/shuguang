/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-03-13 10:46
 */
package com.sofn.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数类
 *
 * @author lijiang
 * @version 1.0
 **/
@ApiModel
@Data
public class PagingParam implements Serializable {
    private static final long serialVersionUID = -8623867767925079015L;
    /**
     * 当前页号
     */
    @ApiModelProperty(value = "当前页号", example = "1")
    private Integer pageNum = 1;

    /**
     * 每一页记录条数
     */
    @ApiModelProperty(value = "每一页记录条数", example = "10")
    private Integer pageSize = 10;
}
