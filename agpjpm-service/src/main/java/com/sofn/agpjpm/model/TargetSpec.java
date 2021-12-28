package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date:2020-06-10 9:11
 */
@TableName("target_spec")
@Data
public class TargetSpec extends BaseModel<TargetSpec> {
    private String id;
    /**
     * 来源id
     */
    private String sourceId;
    /**
     * 来源表
     */
    private String specId;
    /**
     * 资源调查id
     */
    private String specSource;
    /**
     * 物种名称
     */
    private String specName;
    /**
     * 拉丁名
     */
    private String latinName;
    /**
     * 属名
     */
    private String attrName;
    /**
     * 科名
     */
    private String familyName;
    /**
     * 生长状况
     */
    private String growth;
    /**
     * 分布密度
     */
    private String density;
    /**
     * 丰富度
     */
    private String richness;
    /**
     * 分布面积（亩）
     */
    private Double area;
    /**
     * 返青期开始
     */
    private Date greenS;
    /**
     * 返青期结束
     */
    private Date greenE;
    /**
     * 繁殖期开始
     */
    private Date breedS;
    /**
     * 繁殖期结束
     */
    private Date breedE;
    /**
     * 枯萎期开始
     */
    private Date witheredS;
    /**
     * 枯萎期结束
     */
    private Date witheredE;

    /**
     * 分布数量
     */
    private Double amount;
    /**
     * 省内是否新发现
     */
    private String discovery;
    /**
     * 是否为国家新发物种
     */
    private String newSpecies;
    /**
     * 创建时间戳
     */
    private Long crtTime;
    /**
     * 物种是否为本系统新创建的
     */
    private String  isSystem;

}
