package com.sofn.sys.service.impl;

import com.google.common.collect.Maps;
import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.service.SysDictTypeService;
import com.sofn.sys.vo.SysDictTypeAndValueVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/4/7 10:50
 */
public class SysDictTypeServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysDictTypeService sysDictTypeService;


    @Test
    public void getDictTypeAndValueByPage() {
        Map<String,Object> params = Maps.newHashMap();
        PageUtils<SysDictTypeAndValueVo> dictTypeAndValueByPage = sysDictTypeService.getDictTypeAndValueByPage(params, 0, 10);
        System.out.println(dictTypeAndValueByPage);

    }
}