package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 14:00
 */
@ApiModel("保护场对象")
@Data
@TableName("FARM_RECORD")
public class FarmRecord {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "保护场名称")
    private String oldName;
    @ApiModelProperty(value = "新保护场名称")
    private String newName;
    @ApiModelProperty(value = "建设单位")
    private String oldCompany;
    @ApiModelProperty(value = "新建设单位")
    private String newCompany;
    @ApiModelProperty(value = "变更人")
    private String operator;
    @ApiModelProperty(value = "变更时间")
    private Date changeTime;
}
