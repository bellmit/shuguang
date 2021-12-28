package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.service.SysBaseDataTypeService;
import com.sofn.sys.vo.SysBaseTypeAndDataVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author heyongjie
 * @date 2020/4/7 15:33
 */
public class SysBaseDataTypeServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysBaseDataTypeService sysBaseDataTypeService;

    @Test
    public void getSysBaseDataByPage() {
        PageUtils<SysBaseTypeAndDataVo> sysBaseDataByPage = sysBaseDataTypeService.getSysBaseDataByPage(null, 0, 10);
        System.out.println(sysBaseDataByPage);
    }
}