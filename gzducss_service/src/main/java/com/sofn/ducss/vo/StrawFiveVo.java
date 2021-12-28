package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/12/10 11:35
 * @Version 1.0
 * 秸秆五料化（分散+农户）
 */
@Data
@ApiModel(value = "秸秆五料化")
public class StrawFiveVo {

	@ApiModelProperty(name = "name",value = "五料化名字")
	private String  name;
	@ApiModelProperty(name = "count",value = "五料化汇总(万吨)")
	private BigDecimal  count;


}
