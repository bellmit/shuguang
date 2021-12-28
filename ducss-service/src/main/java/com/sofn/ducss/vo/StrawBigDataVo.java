package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/12/10 11:26
 * @Version 1.0
 * 大数据汇总返回对象
 */
@Data
@ApiModel(value ="大数据汇总返回对象" )
public class StrawBigDataVo {

	@ApiModelProperty(name = "strawProduceResVo2s",value = "秸秆产生量")
	private List<StrawProduceVo> strawProduces;

	@ApiModelProperty(name = "strawProduceResVo2s",value = "秸秆可收集量")
	private List<StrawCollectVo> strawCollectVos;

	@ApiModelProperty(name = "strawFiveVos",value = "秸秆五料化")
	private List<StrawFiveVo> strawFiveVos;

}
