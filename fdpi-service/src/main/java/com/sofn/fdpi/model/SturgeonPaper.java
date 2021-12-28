package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 鲟鱼子酱标签纸
 */
@Data
@TableName("STURGEON_PAPER")
public class SturgeonPaper extends BaseModel<SturgeonPaper> {

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
     * A规格
     */
    private Integer paperA;
    /**
     * A单价
     */
    private Double priceA;
    /**
     * B规格
     */
    private Integer paperB;
    /**
     * B单价
     */
    private Double priceB;
    /**
     * 箱贴
     */
    private Integer paperS;
    /**
     * 箱贴单价
     */
    private Double priceS;
    /**
     * 总金额
     */
    private BigDecimal total;
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
     * 快递信息
     */
    private String express;
    /**
     * 申请编号
     */
    private String applyCode;
    /**
     * 申请类型1国外2国内
     */
    private String applyType;

}
