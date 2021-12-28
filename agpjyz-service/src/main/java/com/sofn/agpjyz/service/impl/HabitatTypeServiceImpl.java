package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjyz.mapper.HabitatTypeMapper;
import com.sofn.agpjyz.model.HabitatType;
import com.sofn.agpjyz.service.HabitatTypeService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.util.ApiUtil;
import com.sofn.agpjyz.vo.HabitatTypeForm;
import com.sofn.agpjyz.vo.HabitatTypeVo;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 生境类型服务类
 **/
@Service(value = "habitatTypeService")
public class HabitatTypeServiceImpl implements HabitatTypeService {

    @Autowired
    private HabitatTypeMapper habitatTypeMapper;

    @Autowired
    private JzbApi jzbApi;

    @Override
    public HabitatTypeVo save(HabitatTypeForm form) {
        HabitatType entity = new HabitatType();
        BeanUtils.copyProperties(form, entity);
        entity.setId(IdUtil.getUUId());
        habitatTypeMapper.insert(entity);
        return HabitatTypeVo.entity2Vo(entity);
    }

    @Override
    public void deleteBySourceId(String sourceId) {
        QueryWrapper<HabitatType> wrapper = new QueryWrapper<>();
        wrapper.eq("SOURCE_ID", sourceId);
        habitatTypeMapper.delete(wrapper);
    }

    @Override
    public List<HabitatTypeVo> listBySourceId(String sourceId) {
        QueryWrapper<HabitatType> wrapper = new QueryWrapper<>();
        wrapper.eq("SOURCE_ID", sourceId);
        List<HabitatType> habitatTypes = habitatTypeMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(habitatTypes)) {
            Map<String, String> habitatTypeMap = ApiUtil.getResultMap(jzbApi.listForHabitatType());
            List<HabitatTypeVo> habitatTypeVos = new ArrayList<>(habitatTypes.size());
            for (HabitatType habitatType : habitatTypes) {
                HabitatTypeVo vo = HabitatTypeVo.entity2Vo(habitatType);
                vo.setHabitatValue(habitatTypeMap.get(vo.getHabitatId()));
                habitatTypeVos.add(vo);
            }
            return habitatTypeVos;
        }
        return null;
    }

}
