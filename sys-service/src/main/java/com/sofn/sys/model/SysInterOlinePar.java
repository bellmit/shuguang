package com.sofn.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@ApiModel("在线测试接收参数对象")
public class SysInterOlinePar {

	@ApiModelProperty("请求键值对")
	private Map<String,String> params;

	@ApiModelProperty("请求头")
	private Map<String,String> header;

	@ApiModelProperty("请求体")
	private String jsonData;

	@NotBlank(message = "请求url不能为空")
	@ApiModelProperty("请求地址")
	private String url;

	@NotBlank(message = "请求方式不能为空")
	@ApiModelProperty("请求方式")
	private  String methodType;

	@NotBlank(message = "请求urlId不能为空")
	@ApiModelProperty("请求方式")
	private  String requestUrlId;




}
