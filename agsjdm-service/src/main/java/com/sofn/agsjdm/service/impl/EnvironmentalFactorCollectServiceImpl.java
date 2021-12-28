package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.mapper.EnvironmentalFactorCollectMapper;
import com.sofn.agsjdm.model.EnvironmentalFactor;
import com.sofn.agsjdm.service.EnvironmentalFactorCollectService;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.AgsjdmRedisUtils;
import com.sofn.agsjdm.util.ExportUtil;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.exportBean.ExportEfBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.xiaoleilu.hutool.date.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 环境因子监测信息采集模块服务类
 **/
@Service(value = "environmentalFactorCollectService")
public class EnvironmentalFactorCollectServiceImpl implements EnvironmentalFactorCollectService {

    @Resource
    private EnvironmentalFactorCollectMapper efcMapper;
    @Resource
    private InformationManagementService informationManagementService;

    @Override
    public EnvironmentalFactor save(EnvironmentalFactor entity) {
        //防止重复提交
        AgsjdmRedisUtils.checkReSubmit("EnvironmentalFactorSave", 5L);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        //防止重复录入
        String wetlandId = entity.getWetlandId();
        QueryWrapper<EnvironmentalFactor> wrapper = new QueryWrapper<>();
        wrapper.eq("wetland_id", wetlandId);
        if (efcMapper.selectCount(wrapper) != 0) {
            throw new SofnException("湿地区信息已经录入");
        }

        entity.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        entity.setOperatorTime(new Date());
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setOperator(inputer);
        String[] regions = RegionUtil.getRegions();
        entity.setProvince(regions[0]);
        entity.setCity(regions[1]);
        entity.setCounty(regions[2]);
        efcMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        efcMapper.deleteById(id);
    }

    @Override
    public void update(EnvironmentalFactor entity) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        String id = entity.getId();
        if (Objects.isNull(efcMapper.selectById(id))) {
            throw new SofnException("待修改数据不存在");
        }
        QueryWrapper<EnvironmentalFactor> wrapper = new QueryWrapper<>();
        wrapper.eq("wetland_id", entity.getWetlandId());
        wrapper.ne("id", entity.getId());
        if (efcMapper.selectCount(wrapper) != 0) {
            throw new SofnException("湿地区信息已经录入");
        }
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setOperatorTime(new DateTime());
        entity.setOperator(inputer);
        String[] regions = RegionUtil.getRegions();
        entity.setProvince(regions[0]);
        entity.setCity(regions[1]);
        entity.setCounty(regions[2]);
        efcMapper.updateById(entity);
    }

    @Override
    public EnvironmentalFactor get(String id) {
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        EnvironmentalFactor ef = efcMapper.selectById(id);
        ef.setWetlandName(wetlandNameMap.get(ef.getWetlandId()));
        return ef;
    }

    @Override
    public PageUtils<EnvironmentalFactor> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<EnvironmentalFactor> efList = efcMapper.listByParams(params);
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        for (EnvironmentalFactor ef : efList) {
            ef.setWetlandName(wetlandNameMap.get(ef.getWetlandId()));
        }

        return PageUtils.getPageUtils(new PageInfo<>(efList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        List<EnvironmentalFactor> tfList = efcMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(tfList)) {
            List<ExportEfBean> exportList = new ArrayList<>(tfList.size());
            Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
            tfList.forEach(o -> {
                ExportEfBean efb = new ExportEfBean();
                BeanUtils.copyProperties(o, efb);
                efb.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
                exportList.add(efb);
            });
            try {
                ExportUtil.createExcel(ExportEfBean.class, exportList, response, "环境因子监测信息采集.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
