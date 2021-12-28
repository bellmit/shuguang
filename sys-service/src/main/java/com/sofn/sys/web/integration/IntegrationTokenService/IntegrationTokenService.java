package com.sofn.sys.web.integration.IntegrationTokenService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.Auth;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.vo.LoginVo;

public interface IntegrationTokenService  extends IService<SysUser> {

    Auth execuLogin(LoginVo loginVo, boolean isApp, String type);
}
