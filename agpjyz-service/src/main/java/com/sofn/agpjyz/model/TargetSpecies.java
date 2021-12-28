package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 目标物种基础信息类
 *
 * @Author yumao
 * @Date 2020/2/28 15:29
 **/
@Data
@TableName("TARGET_SPECIES")
public class TargetSpecies {

    /**
     * 主键
     */
    private String id;
    /**
     * 保护点ID
     */
    private String protectId;
    /**
     * 保护点名称
     */
    private String protectValue;
    /**
     * 目标物种ID
     */
    private String specId;
    /**
     * 目标物种名称
     */
    private String specValue;
    /**
     * 拉丁学名
     */
    private String latinName;
    /**
     * 数量
     */
    private Double amount;
    /**
     * 生长状况
     */
    private String grow;
    /**
     * 物种丰富度
     */
    private String richness;
    /**
     * 操作人
     */
    private String inputer;
    /**
     * 操作时间
     */
    private Date inputerTime;

}
