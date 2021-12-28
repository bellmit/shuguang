package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-20 17:29
 */
@ApiModel("名录管理对象")
@TableName("DIRECTORIES")
@Data
public class Directories {

    @ApiModelProperty(value = "主键")
    private String   id;
    @ApiModelProperty(value = "编号")
    private String  code;
    @ApiModelProperty(value = "品种名称")
    private String   oldName;
    @ApiModelProperty(value = "品种类别")
    private String  category;
    @ApiModelProperty(value = "所属地区")
    private String  oldRegion;
    @ApiModelProperty(value = "所属地区code")
    private String  oldRegionCode;
    @ApiModelProperty(value = "操作人")
    private String operator;
    @ApiModelProperty(value = "变更时间")
    private Date changeTime;
    @ApiModelProperty(value = "导入时间")
    private Date importTime;
}
