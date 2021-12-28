package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* @Author Chlf
* @Description //预警阈值设置总表 实体类
* @Date 14:09 2020/3/27
* @Param
* @Return
**/
@TableName("WARNING_THRESHOLD")
@Data
public class WarningThreshold extends Model<WarningThreshold> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(name = "id", value = "id,修改时候必须传值，新增时候为空")
    private String id;
    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;
    @ApiModelProperty(name = "classificationId", value = "指标分类id")
    private String classificationId;
    @ApiModelProperty(name = "classificationName", value = "指标分类名")
    private String classificationName;
    @ApiModelProperty(name = "createUserName", value = "创建者名", hidden = true)
    private String createUserName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)", hidden = true)
    private Date createTime;
    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;
    @ApiModelProperty(name = "pushWay", value = "推送方式,写邮箱地址")
    private String pushWay;
    @ApiModelProperty(name = "isSubmitZongZhan", value = "是否上报总站；0-否；1-是")
    private String isSubmitZongZhan;
    @ApiModelProperty(name = "speciesType", value = "生物类型，此处传数值")
    private String speciesType;
    @ApiModelProperty(name = "speciesTypeName", value = "生物类型名，此处传中文名")
    private String speciesTypeName;
    @ApiModelProperty(name = "provinceId", value = "省级ID", hidden = true)
    private String provinceId;
}