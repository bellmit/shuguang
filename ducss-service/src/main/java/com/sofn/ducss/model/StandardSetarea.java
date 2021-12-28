package com.sofn.ducss.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Zhang Yi
 * @Date 2020/10/28 23:57
 * @Version 1.0
 * 标准区域表
 */
@Data
@ApiModel("standard_setarea")
public class StandardSetarea {

	@ApiModelProperty("标准区域表id")
	private String id;

	@ApiModelProperty("所属年度")
	private String year;

	@ApiModelProperty("所属区域")
	private String area;

	@ApiModelProperty("所属区域id")
	private String areaId;

}