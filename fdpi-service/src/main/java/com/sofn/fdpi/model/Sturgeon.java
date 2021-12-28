package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 鲟鱼子酱
 */
@Data
@TableName("STURGEON")
public class Sturgeon extends BaseModel<Sturgeon> {

    /**
     * 企业id
     */
    private String compId;
    /**
     * 企业名称
     */
    @TableField(exist = false)
    private String compName;
    /**
     * 品种
     */
    @TableField(exist = false)
    private String variety;
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
     * 直属审核人员
     */
    @TableField(exist = false)
    private String auditer;
    /**
     * 证书编号
     */
    private String credentials;
    /**
     * 贸易类型
     */
    private String trade;
    /**
     * 附录
     */
    private String appendix;
    /**
     * 标本类型
     */
    private String sample;
    /**
     * 来源代码
     */
    private String source;
    /**
     * 加工厂代码
     */
    private String handle;
    /**
     * 数量或净重
     */
    private Double sum;
    /**
     * 原产国代码
     */
    private String origin;
    /**
     * 出口国
     */
    private String export;
    /**
     * 分装国
     */
    private String split;
    /**
     * 审批时间
     */
    private Date auditTime;
    /**
     * 证书核发地
     */
    private String issueAddr;
    /**
     * 状态
     */
    private String status;
    /**
     * 证书图片id
     */
    private String fileId;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 审核意见
     */
    private String opinion;
    /**
     * 标签数量
     */
    private Integer labelSum;
    /**
     * 申请单号
     */
    private String applyCode;
    /**
     * 申请类型1国外2国内
     */
    private String applyType;
    /**
     * 企业类型
     */
    @TableField(exist = false)
    private String compType;


}
