package com.sofn.sys.config;

import com.sofn.common.config.BaseShiroConfig;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by sofn
 */
@Configuration
public class ShiroConfiguration extends BaseShiroConfig {

    @Override
    protected void filter(Map<String, String> filterMap) {
        filterMap.put("/user/login", "anon");
        filterMap.put("/app/user/login", "anon");
        filterMap.put("/user/captcha", "anon");
        filterMap.put("/user/checkUserNameExist", "anon");
        filterMap.put("/appPackageManage/getTheLatestVersion", "anon");
        filterMap.put("/fileManage/**", "anon");
        filterMap.put("/sofn-sys-web/**", "anon");
        filterMap.put("/sysRegion/getSysRegionTree", "anon");
        filterMap.put("/sysOrganization/checkOrgNameIsExits", "anon");
        filterMap.put("/druid/**","anon");
        filterMap.put("/dict/getDictListByType", "anon");
    }
}
