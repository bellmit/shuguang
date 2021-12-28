/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-15 17:03
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体根据实体某一属性排序转换vo
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "实体根据实体某一属性排序转换vo")
@NoArgsConstructor
@AllArgsConstructor
public class EntitySortVo<T> {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "对应大区数据")
    private T  t;
}
