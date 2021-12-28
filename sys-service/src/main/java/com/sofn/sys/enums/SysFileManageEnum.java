package com.sofn.sys.enums;

import lombok.Getter;

/**
 * 文件管理状态
 * @author heyongjie
 * @date 2019/11/20 13:48
 */
@Getter
public enum SysFileManageEnum {

    /**
     * 已激活
     */
    ACTIVATION_STATE_YES("Y","已激活"),
    /**
     * 未激活
     */
    ACTIVATION_STATE_NO("N","未激活"),

    /**
     * 批量删除的数量
     */
    BATCH_DELETE_NUM("300","批量删除的数量"),

    ;

    /**
     * 代码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    SysFileManageEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
