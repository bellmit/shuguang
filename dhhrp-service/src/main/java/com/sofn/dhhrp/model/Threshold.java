package com.sofn.dhhrp.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:14
 */
@Data
@TableName("threshold")
@ApiModel(value = "阀值对象")
public class Threshold {
    @ApiModelProperty(value = "主键")
    private String id;
    @TableField(exist = false)
    @ApiModelProperty(value = "品种名称")
    private String varietyName;
    @ApiModelProperty(value = "品种id")
    private String variety;
    @ApiModelProperty(value = "指标参数")
    private String indexPar;
    @ApiModelProperty(value = "阀值对象")
    @TableField(exist = false)
    private List<ThresholdSub> thresholdSubList;
}
