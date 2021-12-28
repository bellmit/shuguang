package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/10/29 0:00
 * @Version 1.0
 * 标准值表
 */
@Data
@ApiModel("standard_value")
@AllArgsConstructor
public class StandardValue {

	@TableId(value = "ID", type = IdType.UUID)
	@ApiModelProperty("作物类型标准值id")
	private String id;

	@ApiModelProperty("作物类型=早稻:early_rice,中稻和一季晚稻:middle_rice," +
			"双季晚稻:double_late_rice,小麦:wheat,玉米:corn,马铃薯:potato," +
			"甘薯:sweet_potato,木薯:cassava,花生:peanut,油菜:rape,大豆:soybean," +
			"棉花:cotton,甘蔗:sugar_cane,其他:other")
	private String strawType;

	@ApiModelProperty("草谷比值")
	private BigDecimal grassValley;

	@ApiModelProperty("可收集系数")
	private BigDecimal collectCoefficient;

	@ApiModelProperty("作物类型中文名")
	private String strawName;

	@ApiModelProperty("所属年度")
	private String year;

	@ApiModelProperty("所属区域")
	private String area;

	@ApiModelProperty("所属区域id")
	private String areaId;


	public StandardValue() {
		super();
		BigDecimal zero = BigDecimal.ZERO;
		this.grassValley = zero;
		this.collectCoefficient = zero;
	}
}
