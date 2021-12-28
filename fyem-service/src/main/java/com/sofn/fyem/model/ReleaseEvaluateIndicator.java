package com.sofn.fyem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Description: 流放评价指标表
 * @Author: mcc
 */
@TableName("RELEASE_EVALUATE_INDICATOR")
@Data
public class ReleaseEvaluateIndicator extends Model<ReleaseEvaluateIndicator> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度(非必填)")
    private String belongYear;

    @ApiModelProperty(name = "parentId", value = "父级id(所属一级指标id->二级指标必须选择)")
    private String parentId;

    @ApiModelProperty(name = "indicatorType", value = "指标类型(0-一级指标,1-二级指标)")
    private String indicatorType;

    @ApiModelProperty(name = "indicatorName", value = "指标名称")
    private String indicatorName;

    @ApiModelProperty(name = "referenceValue", value = "参考值(评价值)")
    private Double referenceValue;

    @ApiModelProperty(name = "totalValue", value = "分值(总分值)")
    private Double totalValue;

    @ApiModelProperty(name = "addMan", value = "添加人")
    private String addMan;

    @ApiModelProperty(name = "status", value = "状态(0-启用,1-停用)")
    private String status;

    @ApiModelProperty(name = "createUserId", value = "创建人id")
    private String createUserId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}