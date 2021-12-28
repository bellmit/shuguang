package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 物种监测模块-监测审核记录
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@TableName("MONITOR_EXAMINA_RECORD")
@Data
public class MonitorExaminaRecord extends Model<MonitorExaminaRecord> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "speciesMonitorId", value = "物种监测表id")
    private String speciesMonitorId;

    @ApiModelProperty(name = "status", value = "审核状态")
    private String status;

    @ApiModelProperty(name = "auditor", value = "审核人")
    private String auditor;

    @ApiModelProperty(name = "opinion", value = "审核意见")
    private String opinion;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "审核时间")
    private Date createTime;

}