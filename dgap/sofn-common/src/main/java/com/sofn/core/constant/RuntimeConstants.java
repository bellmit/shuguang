package com.sofn.core.constant;

import com.sofn.core.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzg on 2016/12/15.
 */
public class RuntimeConstants implements Constants {
    /** 菜单类型 */
    public final static Map menuTypeMap = new HashMap();
    static {
        menuTypeMap.put("1", "系统");
        menuTypeMap.put("2", "菜单");
        menuTypeMap.put("3", "操作");
    }
    /** 权限标识 */
    public final static Map menuPrivilegeMap = new HashMap();
    static {
        menuPrivilegeMap.put("0", "禁用");
        menuPrivilegeMap.put("1", "只读");
        menuPrivilegeMap.put("2", "读写");
    }

    /**  状态 */
    public final static Map statusMap = new HashMap();
    static {
        statusMap.put("0", "正常");
        statusMap.put("1", "禁用");
    }

    /**  状态 */
    public final static Map enableMap = new HashMap();
    static {
        enableMap.put("0", "有效");
        enableMap.put("1", "无效");
    }

    /**  专业领域 */
    public final static Map professionalFieldMap = new HashMap();
    static {
        professionalFieldMap.put("1", "检测");
        professionalFieldMap.put("2", "农药");
        professionalFieldMap.put("3", "检验检疫");
    }

    /** 是否固化 */
    public final static Map fixedMap = new HashMap();
    static {
        fixedMap.put("1", "禁止更改");
        fixedMap.put("2", "允许更改");
    }

    /** 日志类型 */
    public final static Map logTypeMap = new HashMap();
    static {
        logTypeMap.put("login", "登入");
        logTypeMap.put("logout", "登出");
        logTypeMap.put("del", "删除");
        logTypeMap.put("update", "修改");
        logTypeMap.put("add", "增加");
    }

    /** 行业分类 */
    public final static Map industryTypeMap = new HashMap();
    static {
        industryTypeMap.put("01", "种植业");
        industryTypeMap.put("02", "畜牧业");
        industryTypeMap.put("03", "渔业");
    }



}
