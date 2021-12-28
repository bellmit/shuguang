package com.sofn.ducss.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消息通知返回Vo
 *
 * @author jiangtao
 * @date 2020/10/28
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="消息通知返回Vo")
public class MessageVo {

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id",value = "主键id")
    private String id;

    /**
     * text
     */
    @ApiModelProperty( name = "text",value = "内容")
    private String text;

    /**
     * 发送对象
     */
    @ApiModelProperty(name = "sendObject",value = "发送对象,部级通知")
    private String sendObject;

    /**
     * 下发人
     */
    @ApiModelProperty(name = "issuedPerson",value = "下发人,部级通知")
    private String issuedPerson;

    /**
     * 消息类型（通知，上报消息，审核消息）消息类型
     */
    @ApiModelProperty(name = "message",value = "消息类型（通知，上报消息，审核消息）消息类型")
    private String message;

    /**
     * 消息状态（已读，未读）
     */
    @ApiModelProperty(name = "status",value = "消息状态（已读，未读）")
    private String status;

    /**
     * 下发状态
     */
    @ApiModelProperty(name = "sendStatus",value = "下发状态")
    private String sendStatus;

    /**
     * 下发日期
     */
    @ApiModelProperty( name = "sendTime",value = "下发日期")
    private Date sendTime;

    /**
     * 用户id
     */
    @ApiModelProperty(name = "userId",value = "用户id")
    private String userId;

    /**
     * 用户信息
     */
    @ApiModelProperty(name = "userName",value = "用户信息")
    private String userName;

    /**
     * 区域id
     */
    @ApiModelProperty(name = "areaId",value = "区域id")
    private String areaId;

    /**
     * 审核人
     */
    @ApiModelProperty(name = "auditPerson",value = "审核人")
    private String auditPerson;

    /**
     * 发送用户等级
     */
    @ApiModelProperty(name = "userLevel",value = "发送用户等级")
    private String userLevel;

    /**
     * 发送用户范围
     */
    @ApiModelProperty(name = "userAreaId",value = "发送用户范围")
    private String userAreaId;

    /**
     * 操作状态
     */
    @ApiModelProperty(name = "auditStatus", value = "操作状态")
    private String auditStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private Date createTime;

    /**
     * 创建人*/

    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime",value = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(name = "updateBy",value = "更新人")
    private String updateBy;
}