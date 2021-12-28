package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.mapper.SysRegionMapper;
import com.sofn.sys.model.SysRegion;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.vo.SysRegionForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heyongjie on 2019/5/29 14:07
 */
public class SysRegionServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Test
    @Transactional
    @Rollback
    public void addSysRegion() {
        SysRegion sysRegion = new SysRegion();
        sysRegion.setRegionName("高明区1");
        sysRegion.setRegionCode("4406001");
        sysRegionService.addSysRegion(sysRegion);
    }


    @Test
    @Transactional
    @Rollback
    public void updateSysRegion() {
        SysRegion sysRegion = new SysRegion();
        sysRegion.setId("430225");
        sysRegion.setRegionName("高明区12");
        sysRegion.setRegionCode("4406003232");
        sysRegionService.updateSysRegion(sysRegion);

    }

    @Test
    public void deleteSysRegion() {
        sysRegionService.deleteSysRegion("430225");
    }


    @Test
    public void testGetAll() {
        List<SysRegion> sysRegionList = sysRegionMapper.selectList(null);
        System.out.println(sysRegionList.size());
    }

    @Test
    public void testPageHelper() {
        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("status", SysRegionEnum.STATUS_Y.getCode());


        PageUtils<SysRegionForm> sysRegionPageInfo = sysRegionService.getSysRegionByContionPage(params, 1, 10);
        System.out.println(sysRegionPageInfo);

    }

    @Test
    public void testGetCodeMapping(){
        String regionNames = sysRegionService.getRegionNames("100000,asdfasdf,659000,419000",0);
        System.out.println(regionNames);
    }

  @Test
  public void testddd(){
   sysRegionService.getFormatIdsOrNames("/","100000","ID");
  }

}
