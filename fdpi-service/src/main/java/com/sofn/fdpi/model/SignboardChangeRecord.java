package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 标识变更记录表类
 *
 * @Author yumao
 * @Date 2020/1/6 10:29
 **/
@Data
@TableName("SIGNBOARD_CHANGE_RECORD")
public class SignboardChangeRecord {

    /**
     * 主键
     */
    private String id;
    /**
     * 标识ID
     */
    private String signboardId;
    /**
     * 物种ID
     */
    private String speId;
    /**
     * 物种名称
     */
    @TableField(exist = false)
    private String speName;
    /**
     * 企业ID
     */
    private String compId;
    /**
     * 企业名称
     */
    @TableField(exist = false)
    private String compName;
    /**
     * 标识编码
     */
    private String code;
    /**
     * 标识变更状态状态 1配发 2在养 3转移-减少 4转移-增加 5换发 6补发 7注销
     */
    @ApiModelProperty("标识变更状态状态 1配发 2在养 3转移-减少 4转移-增加 5换发 6补发 7注销")
    private String status;
    /**
     * 变更日期
     */
    private Date changeTime;
    /**
     * 备注
     */
    private String remark;

}
