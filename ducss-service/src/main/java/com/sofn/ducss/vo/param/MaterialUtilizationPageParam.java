/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-30 18:01
 */
package com.sofn.ducss.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 材料分页参数
 *
 * @author jiangtao
 * @version 1.0
 **/
@ApiModel(value = "材料分页参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialUtilizationPageParam extends PagingParam{

    @ApiModelProperty(value = "查询字段")
    private String searchStr;

    @ApiModelProperty(value = "排序字段,1搜索字段,2调入量,3总量")
    private String orderField;

    @ApiModelProperty(value = "排序顺序,1升序,2降序")
    private String sortOrder;

    @ApiModelProperty(value = "行政等级")
    private String administrativeLevel;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "区域id")
    private String areaCode;
}
