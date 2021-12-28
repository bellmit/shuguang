package com.sofn.sys.enums;

import lombok.Getter;

/**
 * App包管理状态
 *
 * @author heyongjie
 * @date 2019/11/18 11:10
 */
@Getter
public enum AppPackageManageEnum {

    /**
     * 最新版本
     */
    STATE_LATEST_EDITION("1", "最新版本"),

    /**
     * 提示升级
     */
    STATE_PROMPT_UPGRADE("2", "提示升级"),

    /**
     * 强制升级
     */
    STATE_COMPULSORY_UPGRADING("3", "强制升级"),

    /**
     * 不提示升级
     */
    STATE_DO_NOT_PROMPT_FOR_UPGRADE("4", "不提示升级"),

    /**
     * 安卓平台
     */
    PLATFORM_ANDROID("android", "安卓"),

    /**
     * 苹果平台
     */
    PLATFORM_IOS("ios", "苹果"),
    ;

    /**
     * 代码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    AppPackageManageEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
