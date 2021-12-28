package com.sofn.fdzem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_batch_management")
@ApiModel("批次管理实体类")
public class BatchManagement {

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("监测批次名")
    private String name;
    @ApiModelProperty("监测面积")
    private String area;
    @ApiModelProperty("采样时间")
    private Date samplingTime;
    @ApiModelProperty("报送时间")
    private Date submittedTime;
    @ApiModelProperty("采样人")
    private String samplingPerson;
    @ApiModelProperty("监测人")
    private String monitorPerson;
    @ApiModelProperty("录入人")
    private String entryClerk;
    @ApiModelProperty("录入时间")
    private Date entryTime;
    @ApiModelProperty("校验人")
    private String checkOne;
    @ApiModelProperty("所属监测站id")
    private String monitoringStationTaskId;

    @ApiModelProperty("监测点")
    @TableField(exist = false)
    private List<MonitoringPoint> monitoringPoints;
}
