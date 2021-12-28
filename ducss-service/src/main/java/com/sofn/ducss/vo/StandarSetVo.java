package com.sofn.ducss.vo;

import com.sofn.ducss.model.StandardValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author Zhang Yi
 * @Date 2020/10/29 9:17
 * @Version 1.0
 * 前端传递新增年度标准Vo
 */
@Data
@ApiModel(value = "前端传递新增年度标准Vo")
public class StandarSetVo {

	@ApiModelProperty("新增所属标准年度")
	@NotBlank( message = "新增所属标准年度字段不为空")
	private String beYear;

	@NotBlank( message = "是否新增字段不为空")
	@ApiModelProperty("是否新增 Y:是，N:否")
	private String ifAdd;
	@ApiModelProperty("参考赋值年份")
	private String copyYear;
	@ApiModelProperty("新增标准值  ")
	private ArrayList<StandardValue> standardValues;





}
