package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogEnum {
    LOG_TYPE_ADD("1", "新增"),
    LOG_TYPE_EDIT("2", "编辑"),
    LOG_TYPE_DELETE("3", "删除"),
    LOG_TYPE_REPORTING("4", "上报"),
    LOG_TYPE_WITHDRAW("5", "撤回(县级用户撤回操作)"),
    LOG_TYPE_RETURN("6", "退回"),
    LOG_TYPE_ADOPT("7", "通过"),
    LOG_TYPE_TASK_DISTRIBUTION("8", "年度任务下发"),
    LOG_TYPE_TASK_EDIT("9", "年度任务编辑"),
    LOG_TYPE_MESSAGE_DISTRIBUTION("10", "通知下发"),
    LOG_TYPE_RELEASE_DATA("11", "公布数据"),
    LOG_TYPE_PARAM_ADD("12", "参数新增"),
    LOG_TYPE_PARAM_EDIT("13", "参数编辑"),
    LOG_TYPE_PARAM_DELETE("14", "参数删除"),
    LOG_TYPE_THRESHOLD_ADD("15", "阈值新增"),
    LOG_TYPE_THRESHOLD_EDIT("16", "阈值编辑"),
    LOG_TYPE_THRESHOLD_DELETE("17", "阈值删除"),
    // 高州秸秆新增
    LOG_TYPE_STRAW_TYPE_ADD("18", "作物新增"),
    LOG_TYPE_STRAW_TYPE_EDIT("19", "作物编辑"),
    LOG_TYPE_STRAW_TYPE_DELETE("20", "作物删除"),
    LOG_TYPE_STRAW_TYPE_ENABLE("21", "作物启用"),
    LOG_TYPE_STRAW_TYPE_DISABLE("22", "作物停用"),
    LOG_TYPE_USER_ADD("23", "用户新增"),
    LOG_TYPE_USER_EDIT("24", "用户编辑"),
    LOG_TYPE_USER_DELETE("25", "用户删除"),
    LOG_TYPE_ORG_ADD("26", "组织机构新增"),
    LOG_TYPE_ORG_EDIT("27", "组织机构编辑"),
    LOG_TYPE_ORG_DELETE("28", "组织机构删除"),
    LOG_TYPE_REGION_ADD("29", "区划新增"),
    LOG_TYPE_REGION_EDIT("30", "区划编辑"),
    LOG_TYPE_REGION_DELETE("31", "区划删除"),

    LOG_TYPE_ROLE_ADD("32", "角色新增"),
    LOG_TYPE_ROLE_EDIT("33", "角色编辑"),
    LOG_TYPE_ROLE_DELETE("34","角色删除")
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
