package com.sofn.ducss.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import org.apache.commons.lang.StringUtils;

/**
 * 机构相关方法
 */
public class SysOrgUtil {

    /**
     * 获取当前登录用户的机构信息
     * @return SysOrganization
     */
    public static SysOrganization getSysOrgInfo(){
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        if(StringUtils.isBlank(loginUserOrganizationInfo)){
            throw new SofnException("登录用户异常，无法获取用户机构信息");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        if(sysOrganization == null){
            throw new SofnException("机构转换异常");
        }
        return sysOrganization;
    }

}
