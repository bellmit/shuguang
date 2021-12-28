package com.sofn.ouman.service.impl;

import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.ouman.service.RoleService;
import com.sofn.ouman.sysapi.SysRoleApi;
import com.sofn.ouman.sysapi.SysSubsystemApi;
import com.sofn.ouman.vo.SelectVo;
import com.sofn.ouman.vo.apivo.SysRole;
import com.sofn.ouman.vo.apivo.SysSubsystemForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    private final String appId = "ouman";

    @Resource
    private SysSubsystemApi sysSubsystemApi;

    @Resource
    private SysRoleApi sysRoleApi;

    @Override

    public List<SelectVo> listCompType() {
        Result<SysSubsystemForm> resultSubsystem = sysSubsystemApi.getSysSubsystemOneByAppId(appId);
        SysSubsystemForm sysSubsystemForm = resultSubsystem.getData();
        String subsystemId = sysSubsystemForm.getId();
        Result<List<SysRole>> resultRole = sysRoleApi.getUserListBySubsystemId(subsystemId);
        List<SysRole> sysRoles = resultRole.getData();
        List<SelectVo> result = Lists.newArrayListWithCapacity(sysRoles.size());
        for (SysRole sysRole : sysRoles) {
            String roleCode = sysRole.getRoleCode();
            if (!roleCode.endsWith("ministry") && !roleCode.endsWith("province")) {
                result.add(new SelectVo(sysRole.getId(), sysRole.getRoleName()));
            }
        }
        return result;
    }

    @Override
    public Map<String, String> mapComeType() {
        return listCompType().stream().collect(Collectors.toMap(SelectVo::getKey, SelectVo::getVal));
    }
}
