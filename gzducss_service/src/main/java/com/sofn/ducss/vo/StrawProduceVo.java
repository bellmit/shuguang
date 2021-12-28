package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Zhang Yi
 * @Date 2020/12/10 11:42
 * @Version 1.0
 * 秸秆产生-大数据
 */
@ApiModel("秸秆产生-大数据")
@Data
public class StrawProduceVo {
	@ApiModelProperty(name = "strawType", value = "秸秆类型")
	private String strawType;

	@ApiModelProperty(name = "strawName", value = "秸秆名称")
	private String strawName;

	// 产生量=粮食产量*草谷比
	@ApiModelProperty(name = "theoryResource", value = "产生量(万吨)")
	private BigDecimal theoryResource =BigDecimal.ZERO;

}
