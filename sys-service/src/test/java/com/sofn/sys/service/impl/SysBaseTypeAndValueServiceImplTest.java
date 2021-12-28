package com.sofn.sys.service.impl;

import com.sofn.sys.service.SysBaseTypeAndValueService;
import com.sofn.sys.vo.SysBaseTypeAndValueVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author heyongjie
 * @date 2020/2/28 16:27
 */
public class SysBaseTypeAndValueServiceImplTest {

    @Autowired
    private SysBaseTypeAndValueService sysBaseTypeAndValueService;

    @Test
    public void getAllBaseTypeAndValue() {
        List<SysBaseTypeAndValueVo> allBaseTypeAndValue = sysBaseTypeAndValueService.getAllBaseTypeAndValue();

        System.out.println(allBaseTypeAndValue);
    }

    @Test
    public void getSubSystemHaveBaseTypeAndValueId() {
    }

    @Test
    public void saveSubSystemAndBaseDataRelationship() {
    }
}