package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.mapper.BiomonitoringMapper;
import com.sofn.agsjdm.model.Biomonitoring;
import com.sofn.agsjdm.service.BiomonitoringService;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.*;
import com.sofn.agsjdm.vo.DropDownVo;
import com.sofn.agsjdm.vo.exportBean.ExportBBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 生物监测信息服务类
 *
 * @Author yumao
 * @Date 2020/4/16 9:38
 **/
@Service(value = "biomonitoringService")
public class BiomonitoringServiceImpl implements BiomonitoringService {

    @Resource
    private BiomonitoringMapper biomonitoringMapper;

    @Resource
    private InformationManagementService informationManagementService;

    @Override
    public Biomonitoring save(Biomonitoring entity) {
        //防止重复提交
        AgsjdmRedisUtils.checkReSubmit("BiomonitoringSave", 5L);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        QueryWrapper<Biomonitoring> wrapper = new QueryWrapper<>();
        wrapper.eq("wetland_id", entity.getWetlandId()).
                eq("biological_axonomy", entity.getBiologicalAxonomy());
        if (biomonitoringMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区信息已录入");
        }
        entity.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        String operator = "采集人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        entity.setOperator(operator);
        entity.setOperatorTime(new Date());
        this.validRepeat(entity.getWetlandId(), entity.getBiologicalAxonomy(),
                DateUtils.format(entity.getOperatorTime(), "yyyy"), "");
        String[] regions = RegionUtil.getRegions();
        entity.setProvince(regions[0]);
        entity.setCity(regions[1]);
        entity.setCounty(regions[2]);
        biomonitoringMapper.insert(entity);
        return entity;
    }


    //验证重复
    private void validRepeat(String wetlandId, String biologicalAxonomy, String year, String id) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("wetlandId", wetlandId);
        params.put("biologicalAxonomy", biologicalAxonomy);
        params.put("year", year);
        if (StringUtils.hasText(id)) {
            params.put("id", id);
        }
        List<Biomonitoring> list = biomonitoringMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(list)) {
            throw new SofnException("该年该湿地区已存在相同数据,不可以新增/修改");
        }
    }

    @Override
    public void delete(String id) {
        biomonitoringMapper.deleteById(id);
    }

    @Override
    public void update(Biomonitoring entity) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        if (Objects.isNull(biomonitoringMapper.selectById(entity.getId()))) {
            throw new SofnException("待修改数据不存在");
        }
        QueryWrapper<Biomonitoring> wrapper = new QueryWrapper<>();
        wrapper.ne("id", entity.getId());
        wrapper.eq("wetland_id", entity.getWetlandId()).
                eq("biological_axonomy", entity.getBiologicalAxonomy());
        if (biomonitoringMapper.selectCount(wrapper) != 0) {
            throw new SofnException("当前湿地区信息已录入");
        }
        User user = UserUtil.getLoginUser();
        String operator = "采集人";
        if (!Objects.isNull(user)) {
            operator = user.getNickname();
        }
        this.validRepeat(entity.getWetlandId(), entity.getBiologicalAxonomy(),
                DateUtils.format(entity.getOperatorTime(), "yyyy"), entity.getId());
        entity.setOperator(operator);
        entity.setOperatorTime(new Date());
        String[] regions = RegionUtil.getRegions();
        entity.setProvince(regions[0]);
        entity.setCity(regions[1]);
        entity.setCounty(regions[2]);
        biomonitoringMapper.updateById(entity);
    }

    @Override
    public Biomonitoring get(String id) {
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        Biomonitoring b = biomonitoringMapper.selectById(id);
        b.setWetlandName(wetlandNameMap.get(b.getWetlandId()));
        return b;
    }

    @Override
    public PageUtils<Biomonitoring> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Biomonitoring> list = biomonitoringMapper.listByParams(params);
        Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
        for (Biomonitoring b : list) {
            b.setWetlandName(wetlandNameMap.get(b.getWetlandId()));
        }
        return PageUtils.getPageUtils(new PageInfo<>(list));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善区域查询参数
        RegionUtil.regionParams(params);
        List<Biomonitoring> list = biomonitoringMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(list)) {
            List<ExportBBean> exportList = new ArrayList<>(list.size());
            Map<String, String> wetlandNameMap = informationManagementService.getPointMap();
            list.forEach(o -> {
                ExportBBean b = new ExportBBean();
                BeanUtils.copyProperties(o, b);
                b.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
//                b.setBiologicalAxonomy("待前端定义好规则");
//                b.setProLevel("待前端定义好规则");
                exportList.add(b);
            });
            try {
                ExportUtil.createExcel(ExportBBean.class, exportList, response, "生物监测信息采集.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

    @Override
    public List<DropDownVo> getYears() {
        List<String> list = biomonitoringMapper.getYears();
        List<DropDownVo> res = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(str -> {
                DropDownVo ddv = new DropDownVo();
                ddv.setId(str);
                ddv.setName(str);
                res.add(ddv);
            });
        }
        return res;
    }

    @Override
    public Biomonitoring getByParams(Map map) {
        return biomonitoringMapper.getByParams(map);
    }

}
