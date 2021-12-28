package com.sofn.sys.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.GetAllInfoByPageUtil;
import com.sofn.sys.mapper.SysOrgMapper;
import com.sofn.sys.mapper.SysRegionMapper;
import com.sofn.sys.model.SysRegion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/7/14 9:47
 */
public class TestGetAllInfoByPageUtil extends SysServiceApplicationTests {

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Test
    public void testGetInfo(){
        GetAllInfoByPageUtil<Map<String,String>> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();

        List<Map<String,String>> sysOrgList = getAllInfoByPageUtil.getAllInfo((params)->{
            List<Map<String, String>> infoByCondition = sysOrgMapper.getInfoByCondition("", (List<String>) params[1]);
            return infoByCondition;
        },1,false,"","",Lists.newArrayList("10ae51c9b2c040ebbe5cfecd05e79736",
                "d3916f2c873b40b6a62b7f8a73c1afc8",
                "a7887a18562545c5ab4b13d7b21b4851"));

        System.out.println(sysOrgList);

    }

    @Test
    public void testGetInfoByParamIsMap(){
        GetAllInfoByPageUtil<SysRegion> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();

        Map<String,Object> queryParams = Maps.newHashMap();

        queryParams.put("ids",Lists.newArrayList("510000","511525"));


        List<SysRegion> sysOrgList = getAllInfoByPageUtil.getAllInfo((params)-> sysRegionMapper.getSysRegionByContion((Map<String,Object>)params[0]),
                0,true,"ids",queryParams);

        System.out.println(sysOrgList);

    }





}
