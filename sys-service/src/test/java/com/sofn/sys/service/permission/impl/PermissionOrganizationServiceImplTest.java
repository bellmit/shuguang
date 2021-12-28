package com.sofn.sys.service.permission.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.treeutil.basic.TreeGenerate;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.service.permission.PermissionOrganizationService;
import com.sofn.sys.vo.SysOrganizationTreeVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2019/12/17 17:41
 */
public class PermissionOrganizationServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private PermissionOrganizationService permissionOrganizationService;

    @Test
    public void updateDataParentIds() {
        TreeGenerate<SysOrganizationTreeVo> treeVoTreeGenerate = new TreeGenerate<>(SysManageEnum.TREE_NODE_SPLIT_STR.getCode(),
                SysManageEnum.ROOT_SYS_ORG.getCode());
        treeVoTreeGenerate.updateDatabaseParentIdsColumn(permissionOrganizationService);
    }

    @Test
    public void getPermissionOrganizationTree(){

//        SysOrganizationTreeVo permissionOrganizationTree = permissionOrganizationService.getPermissionOrganizationTree("20e6b84df47344ca96e3389933f4d679","000000");
//        System.out.println(permissionOrganizationTree);
    }
}