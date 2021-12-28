package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 标识打印类
 *
 * @Author yumao
 * @Date 2019/1/6 15:29
 **/
@Data
@TableName("SIGNBOARD_PRINT")
public class SignboardPrint extends BaseModel<SignboardPrint> {

    /**
     * 打印企业ID
     */
    private String printCompId;
    /**
     * 委托时间
     */
    private Date entrustDate;
    /**
     * 企业ID
     */
    private String compId;
    /**
     * 企业名称
     */
    @TableField(exist = false)
    private String compName;
    /**
     * 企业地址
     */
    @TableField(exist = false)
    private String contactAddress;
    /**
     * 申请单号
     */
    @TableField(exist = false)
    private String applyCode;
    /**
     * 联系人
     */
    @TableField(exist = false)
    private String linkman;
    /**
     * 联系方式
     */
    @TableField(exist = false)
    private String phone;
    /**
     * 打印数量
     */
    private Integer num;
    /**
     * 打印状态 1已打印 0未打印
     */
    private String status;
    /**
     * 企业类型
     */
    @TableField(exist = false)
    private String compType;
    /**
     * 标识类型 00活体 01制品
     */
    private String type;
    /**
     * 合同号
     */
    private String contractNum;
    /**
     * 制单日期
     */
    private Date makeTime;
    /**
     * 物种编码
     */
    private String speCode;
    /**
     * 省代码
     */
    private String provinceCode;
    /**
     * 企业代码
     */
    private String compCode;
    /**
     * 开始代码
     */
    private String codeStart;
    /**
     * 标识申请ID
     */
    private String applyId;
    /**
     * 申请类型1标识申请2国内鱼子酱申请
     */
    private String applyType;


}
