package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 10:50
 */
@ApiModel("名录管理记录对象")
@Data
@TableName("DIRECTORIES_RECORD")
public class DirectoriesRecord {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "所属地区")
    private String oldRegion;
    @ApiModelProperty(value = "所属地区")
    private String oldRegionCode;
    @ApiModelProperty(value = "新所属地区")
    private String newRegion;
    @ApiModelProperty(value = "新所属地区code")
    private String newRegionCode;
    @ApiModelProperty(value = "品种名称")
    private String oldName;
    @ApiModelProperty(value = "新品种名称")
    private String newName;
    @ApiModelProperty(value = "品种类别")
    private String  category;
    @ApiModelProperty(value = "变更人")
    private String operator;
    @ApiModelProperty(value = "变更时间")
    private Date changeTime;
}
