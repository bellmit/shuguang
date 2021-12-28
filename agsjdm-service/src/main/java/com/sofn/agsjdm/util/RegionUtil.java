package com.sofn.agsjdm.util;

import com.sofn.agsjdm.vo.OrganizationInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.util.StringUtils;

import java.util.Map;

public class RegionUtil {

    /**
     * 获取当前用户所属组织机构
     */
    public static OrganizationInfo getOrgInfo() {
        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
        if (StringUtils.isEmpty(orgInfoJosn)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        return JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
    }


    public static void checkAddLevel() {
        if (!"county".equals(getOrgInfo().getOrganizationLevel())) {
            throw new SofnException("只能由县级填报数据");
        }
    }

    public static String[] getRegions() {
        String regionLastCode = getRegionLastCode();
        String[] res = {regionLastCode.substring(0, 2) + "0000",
                regionLastCode.substring(0, 4) + "00", regionLastCode};
        return res;
    }

    public static String getRegionLastCode() {
        String regionLastCode = getOrgInfo().getRegionLastCode();
        return "100000".equals(regionLastCode) ? "" : regionLastCode;
    }

    public static void regionParams(Map<String, Object> params) {
        String level = getOrgInfo().getOrganizationLevel();
        if ("county".equals(level)) {
            params.put("county", getOrgInfo().getRegionLastCode());
        } else if ("city".equals(level)) {
            params.put("city", getOrgInfo().getRegionLastCode());
        } else if ("province".equals(level)) {
            params.put("province", getOrgInfo().getRegionLastCode());
        }
    }
}
