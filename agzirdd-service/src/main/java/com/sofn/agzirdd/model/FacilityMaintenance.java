package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础设施维护
 */
@TableName("FACILITY_MAINTENANCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityMaintenance implements Serializable {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id,添加时候不用传值")
    private String id;
    @ApiModelProperty("保护区（监测点）id")
    private String monitorId;
    @ApiModelProperty("保护区（监测点）名称")
    private String monitorName;
    @ApiModelProperty("基础设施名称")
    private String facilityName;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty("使用日期")
    private Date useDate;
    @ApiModelProperty(value = "创建者id", hidden = true)
    private String createUserId;
    @ApiModelProperty(value = "创建者姓名", hidden = true)
    private String createUserName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

}