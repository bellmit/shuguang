package com.sofn.sys.service.impl;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.service.IntegrationService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.vo.SysUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

public class IntegrationServiceImpl implements IntegrationService {

    @Autowired
    private SysUserService userService;


    @Override
    public PageUtils<SysUserForm> findAllUserList(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageUtils<SysUserForm> pageUtils = userService.findAllUserList(params, pageNo, pageSize);


        return pageUtils;
    }


}
