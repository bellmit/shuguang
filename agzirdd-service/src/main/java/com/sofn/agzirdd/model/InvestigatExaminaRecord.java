package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 物种调查模块-审核记录
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
@TableName("INVESTIGAT_EXAMINA_RECORD")
@Data
public class InvestigatExaminaRecord extends Model<InvestigatExaminaRecord> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "speciesInvestigationId", value = "物种调查表id")
    private String speciesInvestigationId;

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