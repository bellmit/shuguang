package com.sofn.agsjdm.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.mapper.ThreatFactorMapper;
import com.sofn.agsjdm.model.ThreatFactor;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.service.ThreatFactorService;
import com.sofn.agsjdm.util.AgsjdmRedisUtils;
import com.sofn.agsjdm.util.ExportUtil;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.ThreatFactorForm;
import com.sofn.agsjdm.vo.exportBean.ThreatFactorExecl;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 15:23
 */
@Service("threatFactorService")
public class ThreatFactorServiceImpl implements ThreatFactorService {
    @Resource
    private ThreatFactorMapper tMapper;
    @Resource
    private InformationManagementService informationManagementService;

    /**
     * 插入
     *
     * @param ba
     * @return
     */
    @Override
    @Transactional
    public void insert(ThreatFactorForm ba) {
        //幂等性处理
        AgsjdmRedisUtils.checkReSubmit("ThreatFactorFormdata", 5L);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        String wetlandId = ba.getWetlandId();
        if (tMapper.selectByWetlandId(wetlandId) != null) {
            throw new SofnException("当前湿地区的已录入信息");
        }
        ThreatFactor threatFactor = new ThreatFactor();
        BeanUtils.copyProperties(ba, threatFactor);

        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        threatFactor.setId(IdUtil.getUUId());
        String[] regions = RegionUtil.getRegions();
        threatFactor.setProvince(regions[0]);
        threatFactor.setCity(regions[1]);
        threatFactor.setCounty(regions[2]);
        threatFactor.setOperator(operator);
        threatFactor.setOperatorTime(new Date());
        tMapper.insert(threatFactor);
    }

    @Override
    public void update(ThreatFactorForm ba) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        String id = ba.getId();
        String wetlandId = ba.getWetlandId();
        Map map = new HashMap();
        map.put("id", id);
        map.put("wetlandId", wetlandId);
        ThreatFactor threatFactor = tMapper.selectByWetlandId1(map);
        if (threatFactor != null) {
            throw new SofnException("当前湿地区信息已存在");
        }
        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        ThreatFactor threat = new ThreatFactor();
        BeanUtils.copyProperties(ba, threat);
        String[] regions = RegionUtil.getRegions();
        threat.setProvince(regions[0]);
        threat.setCity(regions[1]);
        threat.setCounty(regions[2]);
        threat.setOperatorTime(new Date());
        threat.setOperator(operator);

        tMapper.updateById(threat);
    }

    /**
     * @param id 主键
     * @return 基础信息
     */
    @Override
    public ThreatFactor get(String id) {
        ThreatFactor threatFactor = tMapper.selectById(id);
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        threatFactor.setWetlandName(wetlandNameMap.get(threatFactor.getWetlandId()));
        return threatFactor;
    }

    /**
     * @param wetlandId
     * @return
     */
    @Override
    public ThreatFactor getThreat(String wetlandId) {

        return tMapper.selectByWetlandId(wetlandId);
    }

    @Override
    public void delete(String id) {
        tMapper.deleteById(id);
    }

    /**
     * @param map
     * @param pageNo
     * @param pageSize
     */
    @Override
    public PageUtils<ThreatFactor> list(Map map, int pageNo, int pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<ThreatFactor> ThreatFactors = tMapper.listPage(map);
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        for (ThreatFactor bc : ThreatFactors) {
            bc.setWetlandName(wetlandNameMap.get(bc.getWetlandId()));
        }
        PageInfo<ThreatFactor> ThreatFactorsPageInfo = new PageInfo<>(ThreatFactors);
        return PageUtils.getPageUtils(ThreatFactorsPageInfo);
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        List<ThreatFactor> tsList = tMapper.listPage(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<ThreatFactorExecl> exportList = new ArrayList<>(tsList.size());
            Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
            tsList.forEach(o -> {
                ThreatFactorExecl etb = new ThreatFactorExecl();
                BeanUtils.copyProperties(o, etb);
                etb.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ThreatFactorExecl.class, exportList, response, "威胁因素基础信息收集.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
