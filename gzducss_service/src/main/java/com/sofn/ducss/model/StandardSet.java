package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * standard_set
 * @author zy 2020-10-28
 */
@Data
@ApiModel("standard_set")
public class StandardSet{

	@TableId(value = "ID", type = IdType.UUID)
	@ApiModelProperty("标准表id")
	private String id;

	@ApiModelProperty("所属年度")
	private String year;

	@ApiModelProperty("操作人")
	private String operator;

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty("操作时间")
	private Date operationTime;



}