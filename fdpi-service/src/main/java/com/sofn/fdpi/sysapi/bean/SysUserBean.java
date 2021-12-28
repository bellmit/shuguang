package com.sofn.fdpi.sysapi.bean;

import lombok.Data;

import java.util.List;

/**
 * 调用支撑平台接口返回的信息
 * @author heyongjie
 * @date 2019/9/5 10:57
 */
@Data
public class SysUserBean {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 机构ID
     */
    private String organizationId;

    /**
     * 机构名称
     */
    private String organizationName;

    /**
     * 是否第三方机构
     */
    private String isThirdParty;

    /**
     * 机构级别
     */
    private String organizationLevel;

    /**
     * 角色ID
     */
    private List<String> roles;


    public enum IsThirdPartyEnum {
        /**
         * 是第三方机构
         */
        Y,
        /**
         * 不是第三方机构
         */
        N

    }

    public SysUserBean(){}

    public SysUserBean(String userId, String userName, String organizationId, String organizationName, String isThirdParty, String organizationLevel, List<String> roles) {
        this.userId = userId;
        this.userName = userName;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.isThirdParty = isThirdParty;
        this.organizationLevel = organizationLevel;
        this.roles = roles;
    }
}
