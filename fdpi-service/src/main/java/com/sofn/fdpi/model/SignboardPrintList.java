package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 标识打印列表类
 *
 * @Author yumao
 * @Date 2019/1/6 15:29
 **/
@Data
@TableName("SIGNBOARD_PRINT_LIST")
public class SignboardPrintList extends BaseModel<SignboardPrintList> {

    /**
     * 主键
     */
    private String id;
    /**
     * 打印ID
     */
    private String printId;
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
     * 物种名称
     */
    @TableField(exist = false)
    private String speName;

}
