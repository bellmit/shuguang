package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.IdUtil;
import com.sofn.sys.mapper.SysRoleResourceMapper;
import com.sofn.sys.model.SysRoleResource;
import com.sofn.sys.service.SysResourceService;
import com.sofn.sys.vo.SysResourceForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2019/9/29 17:29
 *@Version 1.0
 **/
@Slf4j
public class AddResourceToRoleServiceTest extends SysServiceApplicationTests {

    @Autowired
    SysResourceService sysResourceService;
    @Autowired
    SysRoleResourceMapper sysRoleResourceMapper;

    @Test
    public void addResourceToRole(){
        String roleId = "2";
        List<SysResourceForm> resourceList = sysResourceService.getAllResourceByAppIdWithOutPermission("sys", null);
        addChildResourceToRole(roleId, resourceList);
    }

    private void addChildResourceToRole(String roleId, List<SysResourceForm> resourceList) {
        if (resourceList != null && resourceList.size()>0) {
            for (SysResourceForm sysResourceForm : resourceList) {
                SysRoleResource srr = new SysRoleResource(IdUtil.getUUId(),roleId,sysResourceForm.getId());
                sysRoleResourceMapper.insert(srr);
                log.info("roleId:"+roleId+", add resource:"+sysResourceForm.getResourceName());
                if (sysResourceForm.getChildList() != null && sysResourceForm.getChildList().size()>0) {
                    addChildResourceToRole(roleId, sysResourceForm.getChildList());
                }
            }
        }
    }

}
