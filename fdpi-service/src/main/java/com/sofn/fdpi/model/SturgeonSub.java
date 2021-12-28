package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 鲟鱼子酱
 */
@Data
@TableName("STURGEON_SUB")
public class SturgeonSub extends BaseModel<SturgeonSub> {

    /**
     * 鲟鱼子酱ID
     */
    private String sturgeonId;
    /**
     * 客户
     */
    private String customer;
    /**
     * 品种
     */
    private String variety;
    /**
     * 标签纸规格
     */
    private String label;
    /**
     * 箱号
     */
    private Integer caseNum;
    /**
     * 起始号
     */
    private String startNum;
    /**
     * 终止号
     */
    private String endNum;
    /**
     * 规格(/克)
     */
    private Integer specs;
    /**
     * 听数
     */
    private Integer sum;
    /**
     * 流程状态 1待审核2已退回3已通过
     */
    private String status;


}
