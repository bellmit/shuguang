package com.sofn.ducss.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/11/8 21:11
 * @Version 1.0
 * 还田离田汇总
 */
@Data
@ApiModel("还田离田汇总")
public class ReturnLeaveSum {

	@ApiModelProperty("id")
	private String id;

	@ApiModelProperty("年份")
	private String year;

	@ApiModelProperty("区域id")
	private String areaId;

	@ApiModelProperty("秸秆类型")
	private String strawType;

	@ApiModelProperty("直接还田量")
	private BigDecimal proStillField;

	@ApiModelProperty("还田比例or直接还田率")
	private BigDecimal returnRatio;

	@ApiModelProperty("主体合计分散合计")
	private BigDecimal allTotal;

	@ApiModelProperty("农户分散利用量合计")
	private BigDecimal disperseTotal;

	@ApiModelProperty("主体合计")
	private BigDecimal mainTotal;

	@ApiModelProperty("可收集资源量")
	private BigDecimal collectResource;

	@ApiModelProperty(name = "returnArea", value = "还田面积")
	private BigDecimal returnArea;//还田面积

	@ApiModelProperty(name = "seedArea", value = "播种面积")
	private BigDecimal seedArea;//播种面积

	public ReturnLeaveSum() {
		BigDecimal zero = BigDecimal.ZERO;
		this.proStillField = zero;
		this.returnRatio = zero;
		this.allTotal = zero;
		this.disperseTotal = zero;
		this.mainTotal = zero;
		this.collectResource = zero;
	}
}
