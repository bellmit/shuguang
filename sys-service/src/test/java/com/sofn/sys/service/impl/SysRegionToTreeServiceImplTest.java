package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.service.SysRegionToTreeService;
import com.sofn.sys.vo.SysRegionTreeVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by heyongjie on 2019/5/29 15:44
 */
public class SysRegionToTreeServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysRegionToTreeService sysRegionToTreeService;

    @Test
    public void getSysRegionTree() {
        sysRegionToTreeService.getSysRegionTree("pmpps",0);
    }


    @Test
    public void getSysRegionById() {
        List<SysRegionTreeVo> sysRegionTreeVos = sysRegionToTreeService.getSysRegionTreeById("pmpps", SysManageEnum.ROOT_LEVEL.getCode(),0);
        System.out.println(sysRegionTreeVos.size());
    }

    @Test
    public void testGetSysRegionTreeByIdAndCache(){
        List<SysRegionTreeVo> sysRegionTreeVos = sysRegionToTreeService.getSysRegionTreeByIdAndCache("pmpps", SysManageEnum.ROOT_LEVEL.getCode(),0);
        System.out.println(sysRegionTreeVos.size());

    }

    @Test
    public void test(){
        List<SysRegionTreeVo> parentNode = sysRegionToTreeService.getParentNode("510108",0);
        System.out.println(parentNode);

    }
}
