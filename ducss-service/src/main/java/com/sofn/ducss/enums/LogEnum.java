package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  LogEnum {
    LOG_TYPE_ADD("1","新增"),
    LOG_TYPE_EDIT("2","编辑"),
    LOG_TYPE_DELETE("3","删除"),
    LOG_TYPE_REPORTING("4","上报"),
    LOG_TYPE_WITHDRAW("5","撤回(县级用户撤回操作)"),
    LOG_TYPE_RETURN("6","退回"),
    LOG_TYPE_ADOPT("7","通过"),
    LOG_TYPE_TASK_DISTRIBUTION("8","年度任务下发"),
    LOG_TYPE_TASK_EDIT("9","年度任务编辑"),
    LOG_TYPE_MESSAGE_DISTRIBUTION("10","通知下发"),
    LOG_TYPE_RELEASE_DATA("11","公布数据"),
    LOG_TYPE_PARAM_ADD("12","参数新增"),
    LOG_TYPE_PARAM_EDIT("13","参数编辑"),
    LOG_TYPE_PARAM_DELETE("14","参数删除"),
    LOG_TYPE_THRESHOLD_ADD("15","阈值新增"),
    LOG_TYPE_THRESHOLD_EDIT("16","阈值编辑"),
    LOG_TYPE_THRESHOLD_DELETE("17","阈值删除"),

    ;


    /**
     * 模糊匹配KEY  获取描述信息
     *
     * @param enumValue 枚举值  模糊匹配
     * @param key       code
     * @return desc
     */
    public static String getEnumDes(String enumValue, String key) {
        for (LogEnum c : LogEnum.values()) {
            if (c.name().contains(enumValue)) {
                if (c.getCode().equals(key)) {
                    return c.getDescription();
                }
            }
        }
        return "";
    }

    private String code;
    private String description;

}
