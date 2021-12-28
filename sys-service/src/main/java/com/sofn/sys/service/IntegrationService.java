package com.sofn.sys.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.sys.vo.SysUserForm;
import java.util.Map;


public interface IntegrationService {

    PageUtils<SysUserForm> findAllUserList(Map<String, Object> params, Integer pageNo, Integer pageSize);


}
