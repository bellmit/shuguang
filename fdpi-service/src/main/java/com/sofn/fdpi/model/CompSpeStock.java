package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 企业物种库存
 *
 * @Author yumao
 * @Date 2020/2/19 11:13
 **/
@Data
@TableName("COMP_SPE_STOCK")
public class CompSpeStock {

    /**
     * 主键
     */
    private String id;
    /**
     * 企业ID
     */
    private String compId;
    /**
     * 动物物种ID
     */
    private String speciesId;
    /**
     * 物种数量
     */
    private Integer speNum;
    /**
     * 最后一次变更时间
     */
    private Date lastChangeTime;
    /**
     * 变更人ID
     */
    private String lastChangeUserId;

}
