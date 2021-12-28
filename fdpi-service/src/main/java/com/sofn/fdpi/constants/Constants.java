package com.sofn.fdpi.constants;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/16 17:07
 */
@Component
public class Constants {
    /**
     * 系统ID
     */
    public static final String SYSTEM_ID = "fdpi";
    /**
     * 部
     */
    public static final String REGION_TYPE_MINISTRY = "ministry";
    /**
     * 省
     */
    public static final String REGION_TYPE_PROVINCE = "province";
    /**
     * 市
     */
    public static final String REGION_TYPE_CITY = "city";
    /**
     * 县
     */
    public static final String REGION_TYPE_COUNTY = "county";
    /**
     * 乡
     */
    public static final String REGION_TYPE_COUNTRY = "country";

    /**
     * 企业用户角色码
     */
    public static final String COMP_USER_ROLE_CODE = "fdpi_company";
    /**
     * 打印企业用户角色码
     */
    public static final String PRINT_USER_ROLE_CODE = "fdpi_print";
    /**
     * 机构职能：执法机构
     */
    public static final String ORG_ZHIFA_FUNC_CODE = "zhifa";

    /**
     * 物种下拉列表保存在redis中的key
     */
    public static final String REDIS_KEY_ALL_SPECIES_FOR_SELECT = "FDPI_ALL_SPECIES_For_SELECT";
    /**
     * 当前数据库所有存在的物种 保护等级 主键id 存放在redis中的key
     */
    public static final String REDIS_KEY_ALL_SPECIES = "FDPI_REDIS_KEY_ALL_SPECIES";
    /**
     * 当前数据库所有存在的 审批编号 和主键id  存放在redis中的key
     */
    public static final String REDIS_KEY_ALL_IES = "FDPI_REDIS_KEY_ALL_IES";
    /**
     * 当前数据库所有存在的 证书编号 和 主键id  存放在redis中的key
     */
    public static final String REDIS_KEY_ALL_CAP = "FDPI_REDIS_KEY_ALL_CAP";

    public static final String REDIS_KEY_ALL_PAPERS = "FDPI_REDIS_KEY_ALL_PAPERS";

    public  static final String FDPI_INSERT_SIGNBOARD = "FDPI_INSERT_SIGNBOARD";
    /**
     * 是否操作流程组件
     */
    public static String WORKFLOW = "N";
}
