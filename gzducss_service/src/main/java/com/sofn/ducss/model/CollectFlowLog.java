package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("collect_flow_log")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CollectFlowLog extends Model<CollectFlowLog> {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "areaId", value = "区县id")
    private String areaId;

    @ApiModelProperty(name = "areaName", value = "区县名称")
    private String areaName;

    @ApiModelProperty(name = "operation", value = "操作内容")
    private String operation;

    @ApiModelProperty(name = "minhour", value = "再次上报时间限制")
    private String minhour;

    @ApiModelProperty(name = "remark", value = "操作内容描述")
    private String remark;

    @ApiModelProperty(name = "createUserId", value = "创建者id", hidden = true)
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建者昵称")
    private String createUserName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "createDate", value = "创建时间")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "reportTime", value = "上报时间")
    private Date reportTime;

}