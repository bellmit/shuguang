package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 标识申请详细列表类
 *
 * @Author yumao
 * @Date 2019/1/2 17:29
 **/
@Data
@TableName("SIGNBOARD_APPLY_LIST")
public class SignboardApplyList extends BaseModel<SignboardApplyList> {

    private String id;
    /**
     * 申请ID
     */
    private String applyId;
    /**
     * 标识ID
     */
    private String signboardId;
    /**
     * 标识编码
     */
    @TableField(exist = false)
    private String code;
    /**
     * 标识状态
     */
    @TableField(exist = false)
    private String status;
    /**
     * 企业名称
     */
    @TableField(exist = false)
    private String compName;
    /**
     * 物种名称
     */
    @TableField(exist = false)
    private String speName;


}
