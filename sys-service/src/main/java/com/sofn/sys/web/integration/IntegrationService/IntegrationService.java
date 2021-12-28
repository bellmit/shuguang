package com.sofn.sys.web.integration.IntegrationService;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.*;



public interface  IntegrationService extends IService<SysUser> {
    public String saveSysUserNew(SysUser sysUser);


    void updateSysUser(SysUser sysUser);


    void deleteSysUser(String id);


}
