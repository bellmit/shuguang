package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 物种采集模块-审核记录
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@TableName("COLLECTION_EXAMINA_RECORD")
@Data
public class CollectionExaminaRecord extends Model<CollectionExaminaRecord> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "specimenCollectionId", value = "标本采集表id")
    private String specimenCollectionId;

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