package com.sofn.ducss.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 通知消息新增实体
 *
 * @author liuqiang
 * @date 2020/10/28
 */
@Data
@ApiModel(value="通知消息新增实体")
public class MessageParam {

    /**
     * id
     */
    @ApiModelProperty( value = "主键id,新增时不填,修改时填")
    private String id;

    /**
     * 内容
     */
    @ApiModelProperty( value = "通知（保存或下发）",required = true)
    @NotBlank(message = "通知（保存或下发不能为空")
    @Length(min = 1,max = 250)
    private String OperationType;

    /**
     * 内容
     */
    @ApiModelProperty( value = "内容",required = true)
    @NotBlank(message = "内容不能为空")
    @Length(min = 1,max = 250, message = "长度不能超过250个字符")
    private String text;

    /**
     * 发送对象
     */
    @ApiModelProperty( value = "发送对象",required = true)
    @NotBlank(message = "发送对象不能为空")
    @Length(min = 1,max = 250,message = "长度不能超过250个字符")
    private String sendObject;

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
     * 下发人
     */

    private String issuedPerson;


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
     * 操作状态
     */
    @ApiModelProperty( value = "操作状态")
    private String auditStatus;

}