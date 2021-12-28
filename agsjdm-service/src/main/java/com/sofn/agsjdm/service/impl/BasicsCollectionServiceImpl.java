package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.mapper.BasicsCollectionMapper;
import com.sofn.agsjdm.model.BasicsCollection;
import com.sofn.agsjdm.service.BasicsCollectionService;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.AgsjdmRedisUtils;
import com.sofn.agsjdm.util.ExportUtil;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.BasicsCollectionForm;
import com.sofn.agsjdm.vo.exportBean.BasicsCollectionExecl;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.*;
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
 * @Date: 2020-04-13 13:04
 */
@Service(value = "basicsCollectionService")
public class BasicsCollectionServiceImpl implements BasicsCollectionService {

    @Resource
    private BasicsCollectionMapper bMapper;
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
    public void insert(BasicsCollectionForm ba) {
        //引入幂等性处理
        AgsjdmRedisUtils.checkReSubmit("BasicsCollectionFormdate", 5L);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        String wetlandId = ba.getWetlandId();
        QueryWrapper<BasicsCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("wetland_id", wetlandId);
        if (bMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区信息已录入");
        }
        BasicsCollection basicsCollection = new BasicsCollection();
        BeanUtils.copyProperties(ba, basicsCollection);
        basicsCollection.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        basicsCollection.setOperator(operator);
        basicsCollection.setOperatorTime(new Date());
        String[] regions = RegionUtil.getRegions();
        basicsCollection.setProvince(regions[0]);
        basicsCollection.setCity(regions[1]);
        basicsCollection.setCounty(regions[2]);
        bMapper.insert(basicsCollection);
    }

    @Override
    public void update(BasicsCollectionForm ba) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        BasicsCollection basicsCollection = new BasicsCollection();
        QueryWrapper<BasicsCollection> wrapper = new QueryWrapper<>();
        wrapper.ne("id", ba.getId());
        wrapper.eq("wetland_id", ba.getWetlandId());
        if (bMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区信息已录入");
        }
        User user = UserUtil.getLoginUser();
        String operator = "监测人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        basicsCollection.setOperatorTime(new Date());
        basicsCollection.setOperator(operator);
        BeanUtils.copyProperties(ba, basicsCollection);
        String[] regions = RegionUtil.getRegions();
        basicsCollection.setProvince(regions[0]);
        basicsCollection.setCity(regions[1]);
        basicsCollection.setCounty(regions[2]);
        bMapper.updateById(basicsCollection);
    }

    /**
     * @param id 主键
     * @return 基础信息
     */
    @Override
    public BasicsCollection get(String id) {
        BasicsCollection bc = bMapper.selectById(id);
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        bc.setWetlandName(wetlandNameMap.get(bc.getWetlandId()));
        return bc;

    }

    @Override
    public void delete(String id) {
        bMapper.deleteById(id);
    }

    /**
     * @param map
     * @param pageNo
     * @param pageSize
     */
    @Override
    public PageUtils<BasicsCollection> list(Map map, int pageNo, int pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<BasicsCollection> basicsCollections = bMapper.listPage(map);
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        for (BasicsCollection bc : basicsCollections) {
            bc.setWetlandName(wetlandNameMap.get(bc.getWetlandId()));
        }
        PageInfo<BasicsCollection> basicsCollectionsPageInfo = new PageInfo<>(basicsCollections);
        return PageUtils.getPageUtils(basicsCollectionsPageInfo);
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        List<BasicsCollection> tsList = bMapper.listPage(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<BasicsCollectionExecl> exportList = new ArrayList<>(tsList.size());
            Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
            tsList.forEach(o -> {
                BasicsCollectionExecl etb = new BasicsCollectionExecl();
                BeanUtils.copyProperties(o, etb);
                etb.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(BasicsCollectionExecl.class, exportList, response, "基础信息收集.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
