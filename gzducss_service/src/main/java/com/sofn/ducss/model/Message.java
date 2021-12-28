package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 消息实体类
 *
 * @author jiangtao
 * @date 2020/10/28
 */
@Data
@TableName("message")
@ApiModel("消息实体类")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * text
     */
    @ApiModelProperty( value = "内容")
    private String text;

    /**
     * 发送对象
     */
    @ApiModelProperty(value = "发送对象")
    private String sendObject;

    /**
     * 下发人
     */
    @ApiModelProperty(value = "下发人")
    private String issuedPerson;

    /**
     * 消息类型（通知，上报消息，审核消息）消息类型
     */
    @ApiModelProperty(value = "消息类型（通知，上报消息，审核消息）消息类型")
    private String messageType;

    /**
     * 消息状态（已读，未读）
     */
    @ApiModelProperty(value = "消息状态（已读，未读）")
    private String status;

    /**
     * 下发状态
     */
    @ApiModelProperty(value = "下发状态")
    private String sendStatus;

    /**
     * 下发日期
     */
    @ApiModelProperty( value = "下发日期")
    private Date sendTime;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private String userName;

    /**
     * 区域id
     */
    @ApiModelProperty(value = "区域id")
    private String areaId;

    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private String auditPerson;

    /**
     * 退回意见
     */
    @ApiModelProperty(value = "退回意见")
    private String auditOpinion;

    /**
     * 发送用户等级
     */
    @ApiModelProperty(value = "发送用户等级")
    private String userLevel;

    /**
     * 发送用户范围
     */
    @ApiModelProperty(value = "发送用户范围")
    private String userAreaId;

    /**
     * 操作状态
     */
    @ApiModelProperty( value = "操作状态")
    private String auditStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}