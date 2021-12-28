package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 标识申请类
 *
 * @Author yumao
 * @Date 2019/12/26 17:29
 **/
@Data
@TableName("SIGNBOARD_APPLY")
public class SignboardApply extends BaseModel<SignboardApply> {

    /**
     * 主键
     */
    private String id;
    /**
     * 物种ID
     */
    private String speId;
    /**
     * 物种名称
     */
    @TableField(exist = false)
    private String speName;
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
     * 联系人
     */
    @TableField(exist = false)
    private String linkman;
    /**
     * 区域-省
     */
    @TableField(exist = false)
    private String compProvince;
    /**
     * 区域-市
     */
    @TableField(exist = false)
    private String compCity;
    /**
     * 区域-县
     */
    @TableField(exist = false)
    private String compDistrict;
    /**
     * 通讯地址
     */
    @TableField(exist = false)
    private String contactAddress;
    /**
     * 邮政编码
     */
    @TableField(exist = false)
    private String postAddress;
    /**
     * 法人代表
     */
    @TableField(exist = false)
    private String legal;
    /**
     * 核发数量
     */
    @TableField(exist = false)
    private Integer allotmentNum;
    /**
     * 联系电话
     */
    @TableField(exist = false)
    private String phone;
    /**
     * 电子邮箱
     */
    @TableField(exist = false)
    private String email;
    /**
     * 申请类型
     */
    private String applyType;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 申请数量
     */
    private Integer applyNum;
    /**
     * 物种利用类型
     */
    private String speUtilizeType;
    /**
     * 物种来源
     */
    private String speSource;
    /**
     * 流程状态 1未上报2已上报3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过
     */
    private String processStatus;
    /**
     * 最后一次审核意见
     */
    private String lastAdvice;
    /**
     * 申请编号
     */
    private String applyCode;
    /**
     * 企业类型
     */
    @TableField(exist = false)
    private String compType;
    /**
     * 直属审核人员
     */
    @TableField(exist = false)
    private String auditer;
    /**
     * 物种编码
     */
    @TableField(exist = false)
    private String speCode;
    /**
     * 拟销售省份
     */
    private String saleProvince;
    /***
     * 制品大鲵含量
     */
    private String andriasContent;
    /**
     * 产品介绍
     */
    private String introduction;
    /**
     * 标识类型
     */
    private String type;
    /**
     * 鲟鱼子酱编码
     */
    private String citesCode;


}
