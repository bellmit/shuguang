package com.sofn.agpjyz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("农业植物物种信息表")
public class AgricultureSpeciesProcessVo implements Serializable {
    @ApiModelProperty(value="主键")
    private String id;
//    @ApiModelProperty(value="农业物种信息表id")
//    private String AgricultureSpeciesId;
    @ApiModelProperty(value="审核意见：长度不能超过200！")
    private String advice;
    @ApiModelProperty(value="审核状态名称")
    private String statusName;
    @ApiModelProperty(value="审核人")
    private String personName;
    @ApiModelProperty(value="审核时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
