package com.sofn.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 定时任务
 * Created by heyongjie on 2019/6/27 15:20
 */
@Data
@ApiModel(value="定时任务对象 ")
public class SysJob {

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String jobName;

    /**
     * 任务所属组
     */
    @ApiModelProperty(value = "任务所属组")
    private String jobGroup;

    /**
     * 任务描述
     */
    @ApiModelProperty(value = "任务描述")
    private String description;

    /**
     * 任务Class  必须是全路径
     */
    @ApiModelProperty(value = "任务Class")
    private String jobClass;

    /**
     * 上次执行时间
     */
    @ApiModelProperty(value = "上次执行时间")
    private String prevFireTime;

    /**
     * 下次执行时间
     */
    @ApiModelProperty(value = "下次执行时间")
    private String nextFireTime;

    /**
     * 任务状态  运行 停止
     * WAITING:等待
     *
     * PAUSED:暂停
     *
     * ACQUIRED:正常执行
     *
     * BLOCKED：阻塞
     *
     * ERROR：错误
     */
    @ApiModelProperty(value = "任务状态,WAITING:等待 PAUSED:暂停  ACQUIRED:正常执行  BLOCKED：阻塞  ERROR：错误")
    private String state;

    /**
     * 任务开始时间 如果为空默认为当前时间
     */
    @ApiModelProperty(value = "任务开始时间")
    private String startDate;

    /**
     * 任务截止时间，如果为空就表示当前任务不会结束，会永远循环
     */
    @ApiModelProperty(value = "任务截止时间")
    private String endDate;

    /**
     * 任务Cron表达式
     */
    @ApiModelProperty(value = "任务Cron表达式")
    private String cronExpression;


}
