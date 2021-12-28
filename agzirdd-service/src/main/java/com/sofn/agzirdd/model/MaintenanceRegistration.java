package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 基础设施维护备案登记
 */
@TableName("MAINTENANCE_REGISTRATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRegistration extends Model<MaintenanceRegistration> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id,添加时候不用传值")
    private String id;
    @ApiModelProperty(value = "保护区（监测点）id")
    private String monitorId;
    @ApiModelProperty(value = "保护区（监测点）名称")
    private String monitorName;
    @ApiModelProperty(value = "设施名称")
    private String facilityName;
    @ApiModelProperty(value = "使用时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date useDate;
    @ApiModelProperty(value = "创建者id", hidden = true)
    private String createUserId;
    @ApiModelProperty(value = "创建者姓名", hidden = true)
    private String createUserName;
    @ApiModelProperty(value = "创建时间", hidden = true)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "故障原因")
    private String reason;
    @ApiModelProperty(value = "解决方案")
    private String solution;

}