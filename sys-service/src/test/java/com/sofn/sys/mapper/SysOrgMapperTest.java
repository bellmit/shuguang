package com.sofn.sys.mapper;

import com.google.common.collect.Lists;
import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.enums.SysManageEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author heyongjie
 * @date 2020/8/29 11:20
 */
public class SysOrgMapperTest extends SysServiceApplicationTests {


    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Test
    public void testGetOrgCanShowOrgIds(){
        List<String> orgCanShowOrgIds = sysOrgMapper.getOrgCanShowOrgIds(SysManageEnum.ROOT_ORG.getCode(),
                Lists.newArrayList("ducss"), "Y", "Y");

        System.out.println(orgCanShowOrgIds);


    }

}