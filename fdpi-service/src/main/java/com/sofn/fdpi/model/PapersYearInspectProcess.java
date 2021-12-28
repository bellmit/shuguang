package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Description:  证书年审记录表
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@TableName("PAPERS_YEAR_INSPECT_PROCESS")
@ApiModel("证书年审记录表")
public class PapersYearInspectProcess extends BaseModel<PapersYearInspectProcess> {
    @TableId(value="ID",type = IdType.UUID)
    @ApiModelProperty("主键id")
    private String id;
    //年审主表id
    @ApiModelProperty("年审主表id")
    private String papersYearInspectId;
    //意见
    @ApiModelProperty("意见")
    private String advice;
    //状态
    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("状态名称")
    @TableField(exist = false)
    private String statusName;
    //操作人id
    @ApiModelProperty("操作人id")
    private String person;
    //操作人/组织结构名字
    @ApiModelProperty("操作人/组织结构名字")
    private String personName;
    //操作时间
    @ApiModelProperty("操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date conTime;

    @ApiModelProperty("年度")
    @TableField(exist = false)
    private String year;

    @ApiModelProperty("申请编号")
    private String applyNum;

}
