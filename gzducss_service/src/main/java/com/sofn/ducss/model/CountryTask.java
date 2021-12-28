package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("country_task")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CountryTask extends Model<CountryTask> {
    @ApiModelProperty(name = "id", value = "id")
    private String id;
    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;
    @ApiModelProperty(name = "provinceId", value = "省id", hidden = true)
    private String provinceId;
    @ApiModelProperty(name = "cityId", value = "市id", hidden = true)
    private String cityId;
    @ApiModelProperty(name = "areaId", value = "区县id")
    private String areaId;
    @ApiModelProperty(name = "expectNum", value = "预计填报数")
    private Integer expectNum;
    @ApiModelProperty(name = "factNum", value = "实际填报数")
    private Integer factNum;
    @ApiModelProperty(name = "mainNum", value = "主体个数")
    private Integer mainNum;
    @ApiModelProperty(name = "status", value = "状态；0-保存;1-已上报;2-已读;3-退回;4-撤回;5-已通过")
    private Byte status;
    @ApiModelProperty(name = "isReport", value = "是否生成报表；0-未生成；1-已生成", hidden = true)
    private Byte isReport;
    @ApiModelProperty(name = "taskLevel", value = "任务级别", hidden = true)
    private String taskLevel;
    @ApiModelProperty(name = "createUserId", value = "创建者id", hidden = true)
    private String createUserId;
    @ApiModelProperty(name = "createUserName", value = "创建者昵称")
    private String createUserName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "createDate", value = "创建时间")
    private Date createDate;

    //扩展字段
    @ApiModelProperty(name = "sumFactNum", value = "实际填报数统计数据")
    private Integer sumFactNum;
    @ApiModelProperty(name = "sumMainNum", value = "主体个数统计数据")
    private Integer sumMainNum;


}