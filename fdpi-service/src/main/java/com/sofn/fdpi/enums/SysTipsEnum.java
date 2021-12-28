package com.sofn.fdpi.enums;

import lombok.Getter;

/**
 * @description:提示消息或者日志消息统一处理的枚举
 */
@Getter
public enum SysTipsEnum {
    FILE_ACTIVE_ERROR(1001,"文件激活失败！"),
    FILE_REPLACE_ERROR(1002,"文件替换失败！"),
    FILE_DELETE_ERROR(1003,"文件删除失败！"),
    FILE_BATCHDELETE_ERROR(1004,"文件批量删除失败！");

    //消息编码
    private Integer code;
    //消息内容
    private String msg;
    SysTipsEnum(Integer code, String message){
        this.code=code;
        this.msg=message;
    }
}
