package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 鲟鱼子酱标识补打
 */
@Data
@TableName("STURGEON_REPRINT")
public class SturgeonReprint extends BaseModel<SturgeonReprint> {

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
     * 鲟鱼子酱ID
     */
    private String sturgeonId;
    /**
     * 证书编号
     */
    @TableField(exist = false)
    private String credentials;
    /**
     * 标识数量
     */
    private Integer labelSum;
    /**
     * 申请单号
     */
    private String applyCode;
    /**
     * 补打数量
     */
    private Integer reprintSum;
    /**
     * 图片
     */
    private String imgIds;
    /**
     * 文件
     */
    private String fileId;
    /**
     * 状态
     */
    private String status;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 审核意见
     */
    private String opinion;
    /**
     * 申请类型1国外2国内
     */
    private String applyType;
    /**
     * 是否三方打印
     */
    private String thirdPrint;
    /**
     * 制单日期
     */
    private Date makeTime;
    /**
     * 合同号
     */
    private String contractNum;


}
