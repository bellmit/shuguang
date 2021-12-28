package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 鲟鱼子酱标签
 */
@Data
@TableName("STURGEON_SIGNBOARD_DOMESTIC")
public class SturgeonSignboardDomestic extends BaseModel<SturgeonSignboardDomestic> {

    /**
     * 主键
     */
    private String id;
    /**
     * 鲟鱼子酱子表ID
     */
    private String sturgeonSubId;
    /**
     * 箱号
     */
    private Integer caseNum;
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
     * 证书编号
     */
    private String credentials;
    /**
     * 标签纸规格
     */
    private String label;
    /**
     * 标识号码
     */
    private String signboard;
    /**
     * 标签打印状态 Y已打印N未打印
     */
    private String labelPrintStatus;
    /**
     * 箱贴打印状态 Y已打印N未打印
     */
    private String stickerPrintStatus;
    /**
     * 顺序号
     */
    private Integer serial;
    /**
     * 类型 1标签 2箱贴
     */
    private String type;
    /**
     * 打印次数
     */
    private Integer printSum;
    /**
     * 是否第三方打印
     */
    private String thirdPrint;
    /**
     * 企业类型
     */
    @TableField(exist = false)
    private String compType;
    /**
     * 申请单号
     */
    @TableField(exist = false)
    private String applyCode;
}
