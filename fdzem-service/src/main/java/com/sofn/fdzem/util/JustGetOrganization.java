package com.sofn.fdzem.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JwtUtils;
import com.sofn.fdzem.feign.OrgFeign;
import com.sofn.fdzem.feign.UserFeign;
import com.sofn.fdzem.vo.SysOrganizationForm;
import com.sofn.fdzem.vo.SysUserForm;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class JustGetOrganization {

    //通过登陆人获取大登陆人的单位信息
    public static String getOrganizationId(UserFeign userFeign){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            String token = request.getHeader("Authorization");
            String operator = JwtUtils.getUsername(token);
            if (operator == null || operator.equals("")) {
                throw new SofnException("请登录再进行操作！");
            }
            //这里需要调用支撑平台获取用户信息，接口需要创
            Result<SysUserForm> userInfoResult = userFeign.getUserInfoByUsername(operator);
            if (userInfoResult.getCode() != 200 || userInfoResult.getData() == null) {
                throw new SofnException("获取用户信息失败");
            }
            String organizationId = userInfoResult.getData().getOrganizationId();
            if (organizationId == null || organizationId.equals("")) {
                throw new SofnException("非当前机构人员无法操作");
            }
            return organizationId;
        } else {
            throw new SofnException("未获取到request对象");
        }
    }

    //获取机构信息
    public static SysOrganizationForm getSysOrganizationForm(String organizationId, OrgFeign orgFeign){
        Result<SysOrganizationForm> organizationFormResult = orgFeign.getOrgInfoById(organizationId);
        if(organizationFormResult.getCode() != 200 || organizationFormResult.getData() == null){
            throw new SofnException("获取机构信息失败");
        }
        SysOrganizationForm sysOrganizationForm = organizationFormResult.getData();
        return sysOrganizationForm;
    }
}
