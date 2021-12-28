package com.sofn.sys.enums;

import lombok.Getter;

/**
 * 系统管理中的常量
 *
 * @author heyongjie
 * @date  2019/5/29 11:27
 */
@Getter
public enum SysManageEnum {

    /**
     * 拆分系统字典类型
     */
    SPLIT_SYSTEM_DICT_TYPE("split_system","拆分系统字典类型"),

    /**
     * 开发者账号ID
     */
    DEVELOPER_ROLE_ID("developer-role-has-all-perms", ""),
    DEVELOPER_ROLE_CODE("dev", "开发者账号"),
    DEL_FLAG_Y("Y", "已删除"),
    DEL_FLAG_N("N", "未删除"),
    RESOURCE_MENU("MENU", "菜单"),
    RESOURCE_BUTTON("BUTTON", "按钮"),
    //STATUS_00 状态(0限制 1正常  2禁用 3注销),
    STATUS_0("0", "限制"),
    STATUS_1("1", "正常"),
    STATUS_2("2", "禁用"),
    STATUS_3("3", "注销"),
    SEX_MAN("1", "男"),
    SEX_WOMAN("2", "女"),
    SEX_OTHER("3", "未知"),
    REGION_TYPE_PROVINCE("PROVINCE", "省"),
    REGION_TYPE_CITY("CITY", "市"),
    REGION_TYPE_COUNTY("COUNTY", "县"),

    ROOT_SYS_ORG("0", "组织结构根节点"),
    ROOT_ORG("sysorgroot", "生态总站"),
    FUNCTION_DICT_TYPE("sys_org_function","机构职能字典类型key"),
    ROOT_LEVEL("100000", "行政区划"),
    SUBSYSTEM_ROOT("0", "生态系统"),
    SUBSYSTEM_ROOT_APPID("XXX", "AppID"),
    RESOURCE_ROOT("0", "菜单根节点"),
    SYS_ROLE_GROUP_CACHE_KEY("sys_role_group_cache_key", "角色组缓存键"),
    SYS_USER_GROUP_CACHE_KEY("sys_user_group_cache_key", "用戶角色组缓存键"),
    SYS_RESOURCE_CACHE_KEY("sys_resource_cache_key", "资源缓存键"),
    SYS_RESOURCE_CACHE_LIST_KEY("sys_resource_cache_list_key", "资源父级缓存键"),
    SYS_ROLE_CACHE_KEY("sys_role_cache_key", "角色缓存键"),
    SYS_SUBSYSTEM_ROLE_LIST_CACHE_KEY("sys_subsystem_role_list_cache_key", "子系统下的角色列表缓存键"),
    SYS_ORGANIZATION_CACHE_KEY("sys_resource_cache_key", "组织缓存键"),
    SYS_AREA_DICT_TYPE_KEY("areaType", "区域类型码"),

    /**
     * 报表类型
     */
    SYS_REPORT_TYPE_ORDINARA("1","普通报表"),
    SYS_REPORT_TYPE_POLICY_DECISION("2","决策报表"),

    /**
     * 行政区划树节点缓存KEY
     */
    SYS_REGION_CACHE_KEY("sys_region_cache_key", "行政区划树结构缓存键"),

    /**
     * 根据ParentId 获取列表缓存KEY
     */
    SYS_REGION_CACHE_LIST_KEY("sys_region_cache_list_key", "行政区划级联操作缓存键"),
    SYS_SUBSYSTEM_CACHE_TREE_KEY("sys_subsystem_cache_tree_key", "子系统树形结构缓存键"),
    SYS_SUBSYSTEM_CACHE_KEY("sys_subsystem_cache_key", "子系统缓存键"),
    SYS_MANAGE_CACHE_TIMEOUT((1000 * 60 * 60 * 4) + "", "系统管理超时时间"),
    /**
     * 行政区划代码和中文映射
     */
    SYS_REGION_CACHE_CODE_MAPPING("sys_region_cache_code_mapping", "行政区划代码和中文映射"),

    /**
     * 支撑平台系统ID
     */
    SYS_SYSTEM_ID("sys", "支撑平台系统ID"),

    /**
     * 树节点ID之间的分隔符
     */
    TREE_NODE_SPLIT_STR("/", "树节点之间的分隔符,如rootId/nodeId1/nodeId2/nodeId3"),


    SYS_SYSTEM_TYPE_1("1","系统类型-平台"),
    SYS_SYSTEM_TYPE_2("2","系统类型-分系统"),
    SYS_SYSTEM_TYPE_3("3","系统类型-子系统"),

    ;
    private String code;
    private String description;

    SysManageEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 模糊匹配KEY  获取描述信息
     *
     * @param enumValue 枚举值  模糊匹配
     * @param key       code
     * @return desc
     */
    public static String getEnumDes(String enumValue, String key) {
        for (SysManageEnum c : SysManageEnum.values()) {
            if (c.name().contains(enumValue)) {
                if (c.getCode().equals(key)) {
                    return c.getDescription();
                }
            }
        }
        return "";
    }

    /**
     * 模糊匹配KEY  获取描述信息
     *
     * @param enumValue 枚举值  模糊匹配
     * @param des       中文描述
     * @return desc
     */
    public static String getEnumCode(String enumValue, String des) {
        for (SysManageEnum c : SysManageEnum.values()) {
            if (c.name().contains(enumValue)) {
                if (c.getDescription().equals(des)) {
                    return c.getCode();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(SysManageEnum.getEnumDes("STATUS", "0"));
    }

}
