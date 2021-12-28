package com.sofn.sys.service.permission.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.treeutil.basic.TreeGenerate;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.vo.SysSubsystemTreeVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2019/12/19 17:50
 */
public class PermissionSubSystemServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    TreeGenerate<SysSubsystemTreeVo> treeGenerate = new TreeGenerate(SysManageEnum.TREE_NODE_SPLIT_STR.getCode(),SysManageEnum.SUBSYSTEM_ROOT.getCode());


    @Test
    public void getUserHasSubsystem() {
        SysSubsystemTreeVo userHasSubsystem = permissionSubSystemService.getUserHasSubsystem("");
        System.out.println(userHasSubsystem);
    }

    @Test
    public void testUpdateParentIds(){

        treeGenerate.updateDatabaseParentIdsColumn(permissionSubSystemService);
    }
}