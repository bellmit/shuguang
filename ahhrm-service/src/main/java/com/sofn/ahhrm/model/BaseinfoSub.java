package com.sofn.ahhrm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 基础信息子表
 **/
@Data
@TableName("baseinfo_sub")
public class BaseinfoSub {

    /**
     * 主键
     */
    private String id;
    /**
     * 基础信息ID
     */
    private String baseId;
    /**
     * 品种
     */
    private String variety;
    /**
     * 群体数量(个)
     */
    private Integer amount;
    /**
     * 产地与分布
     */
    private String originDistribution;
    /**
     * 公母比例
     */
    private String proportion;
    /**
     * 有效群体含量
     */
    private Double effectiveGroup;
    /**
     * 性能指标
     */
    private String perIndex;
    /**
     * 排序
     */
    private Integer sort;

}
