package com.sofn.ahhrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.ahhrm.mapper.BaseinfoSubMapper;
import com.sofn.ahhrm.model.BaseinfoSub;
import com.sofn.ahhrm.service.BaseinfoSubService;
import com.sofn.ahhrm.vo.BaseinfoSubForm;
import com.sofn.ahhrm.vo.BaseinfoSubVo;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础信息子表模块服务类
 **/
@Service(value = "baseinfoSubService")
public class BaseinfoSubServiceImpl implements BaseinfoSubService {

    @Autowired
    private BaseinfoSubMapper baseinfoSubMapper;

    @Override
    public BaseinfoSubVo save(BaseinfoSubForm form, String baseId, Integer sort) {
        BaseinfoSub entity = new BaseinfoSub();
        BeanUtils.copyProperties(form, entity);
        entity.setId(IdUtil.getUUId());
        entity.setBaseId(baseId);
        entity.setSort(sort);
        entity.setProportion(this.simplifyProportion(entity.getProportion()));
        baseinfoSubMapper.insert(entity);
        return BaseinfoSubVo.entity2Vo(entity);
    }

    @Override
    public void deleteByBaseId(String baseId) {
        QueryWrapper<BaseinfoSub> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id", baseId);
        baseinfoSubMapper.delete(wrapper);
    }

    @Override
    public List<BaseinfoSubVo> listByBaseId(String baseId) {
        QueryWrapper<BaseinfoSub> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id", baseId).orderByAsc("sort");
        List<BaseinfoSub> baseinfoSubs = baseinfoSubMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(baseinfoSubs)) {
//            Map<String, String> habitatTypeMap = ApiUtil.getResultMap(jzbApi.listForHabitatType());
            List<BaseinfoSubVo> baseinfoSubVos = new ArrayList<>(baseinfoSubs.size());
            for (BaseinfoSub baseinfoSub : baseinfoSubs) {
                BaseinfoSubVo vo = BaseinfoSubVo.entity2Vo(baseinfoSub);
//                vo.setHabitatValue(habitatTypeMap.get(vo.getHabitatId()));
                baseinfoSubVos.add(vo);
            }
            return baseinfoSubVos;
        }
        return null;
    }

    private String simplifyProportion(String proportion) {
        String arr[] = new String[2];
        if (proportion.contains(":")) {
            arr = proportion.split(":");
        } else {
            arr = proportion.split("：");
        }
        Integer i1 = Integer.parseInt(arr[0]);
        Integer i2 = Integer.parseInt(arr[1]);
        int gra = this.getgra(i1, i2);
        return String.valueOf(i1 / gra) + ":" + String.valueOf(i2 / gra);
    }

    //获取公约数
    private int getgra(int m, int n) {
        for (int x = m; x >= 1; x--) {
            if (m % x == 0 && n % x == 0) {
                return x;
            }
        }
        return 1;
    }

}
