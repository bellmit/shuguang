package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 企业用户表
 * wuXY
 * 2019-12-27 16:04:35
 */
@TableName(value="TB_USERS")
@Data
@ApiModel(value="企业用户表")
public class TbUsers extends BaseModel<TbUsers> {
    //企业员工(支撑平台用户id)
    @TableId(type = IdType.UUID)
    private String id;
    //账号
    private String account;

    private String password;

    //用户状态 0:停用;  1:启用
    private String userStatus;
    //用户状态名称
    @TableField(exist = false)
    private String userStatusName;
    //企业id
    private String compId;
}
