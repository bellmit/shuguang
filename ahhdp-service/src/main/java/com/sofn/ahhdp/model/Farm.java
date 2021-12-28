package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 13:59
 */
@ApiModel("保护场对象")
@Data
@TableName("FARM")
public class Farm {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "保护场名称")
    private String oldName;
    @ApiModelProperty(value = "建设单位")
    private String oldCompany;
    @ApiModelProperty(value = "操作人")
    private String operator;
    @ApiModelProperty(value = "变更时间")
    private Date changeTime;
    @ApiModelProperty(value = "导入时间")
    private Date importTime;
}
