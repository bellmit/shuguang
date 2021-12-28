package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/12/10 11:42
 * @Version 1.0
 * 秸秆收集-大数据
 */
@ApiModel("秸秆收集-大数据")
@Data
public class StrawCollectVo {
	@ApiModelProperty(name = "strawType", value = "秸秆类型")
	private String strawType;

	@ApiModelProperty(name = "strawName", value = "秸秆名称")
	private String strawName;


	@ApiModelProperty(name = "collectResource", value = "可收集量(万吨)")
	private BigDecimal collectResource =BigDecimal.ZERO;

}
