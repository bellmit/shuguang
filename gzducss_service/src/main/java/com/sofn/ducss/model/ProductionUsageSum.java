package com.sofn.ducss.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/11/5 11:01
 * @Version 1.0
 * 新-产生量与利用量汇总
 */
@Data
@ApiModel("production_usage_sum")
public class ProductionUsageSum {

	@ApiModelProperty("id")
	private String id;

	@ApiModelProperty("所属年度")
	private String year;

	@ApiModelProperty("所属区域")
	private String areaId;

	@ApiModelProperty("产生量=粮食产量 吨*草谷比")
	private BigDecimal produce;

	@ApiModelProperty("可收集量=粮食产量（吨）*草谷比*收集系数")
	private BigDecimal collect;

	@ApiModelProperty("秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)")
	private BigDecimal strawUsage;

	@ApiModelProperty("综合利用率（%）：秸秆利用量/可收集量 *100%")
	private BigDecimal comprehensiveRate;

	@ApiModelProperty("五料化合计=（分散五料主体五料）")
	private BigDecimal allTotal;

	@ApiModelProperty("肥料：市场主体利用量（肥料）+农户分散利用量+（肥料）直接还田量")
	private BigDecimal fertilizer;

	@ApiModelProperty("燃料化利用量（吨）：市场主体利用量（燃料）+农户分散利用量（燃料")
	private BigDecimal fuel;

	@ApiModelProperty("基料化利用量（吨）：市场主体利用量（基料）+农户分散利用量（基料）")
	private BigDecimal basic;

	@ApiModelProperty("原料化利用量（吨）：市场主体利用量（原料）+农户分散利用量（原料）")
	private BigDecimal rawMaterial;

	@ApiModelProperty("饲料：市场主体利用量（饲料）+农户分散利用量（饲料）")
	private BigDecimal feed;

	@ApiModelProperty("综合利用能力指数：市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100")
	private BigDecimal comprehensiveIndex;

	@ApiModelProperty("产业化利用能力指数：市场主体规模化利用量/可收集量*100")
	private BigDecimal industrializationIndex;

	@ApiModelProperty("市场主体合计")
	private BigDecimal mainTotal;



	public ProductionUsageSum() {
		super();
		BigDecimal zero = BigDecimal.ZERO;
		this.produce = zero;
		this.collect = zero;
		this.strawUsage = zero;
		this.comprehensiveRate = zero;
		this.allTotal = zero;
		this.fertilizer = zero;
		this.fuel = zero;
		this.basic = zero;
		this.rawMaterial = zero;
		this.feed = zero;
		this.comprehensiveIndex = zero;
		this.industrializationIndex = zero;
		this.mainTotal = zero;
	}
}
