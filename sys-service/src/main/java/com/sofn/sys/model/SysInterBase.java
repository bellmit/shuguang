package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("接口参数公共基础类")
@TableName("SYS_INTER_PARAMS")
public class SysInterBase {
	@ApiModelProperty("id")
	@TableId(type= IdType.UUID)
	private String id;

	@ApiModelProperty("字段名称")
	private String  paramsName ;

	@ApiModelProperty("变量名")
	private String  variableName;

	@ApiModelProperty("是否必填 Y:是，N:否")
	private String isRequired;

	@ApiModelProperty("参数类型")
	private  String paramsType;


	@ApiModelProperty("示例值")
	private String exampleValues;


	@ApiModelProperty("参数描述")
	private String dataDes ;

	@ApiModelProperty("接口参数外键")
	private String interId;

	@ApiModelProperty("区分接口请求参数的形式 0:GET的key=value、1:请求头参数、2：请求体Body字符串、3：返回参数")
	private String differentReq;


}
