package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjyz.mapper.PlantUtilizationPurposeMapper;
import com.sofn.agpjyz.model.HabitatType;
import com.sofn.agpjyz.model.PlantUtilizationPurpose;
import com.sofn.agpjyz.service.PlantUtilizationPurposeService;
import com.sofn.agpjyz.vo.PlantUtilizationPurposeForm;
import com.sofn.agpjyz.vo.PlantUtilizationPurposeVo;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 植物利用模块用途服务类
 **/
@Service(value = "plantUtilizationPurposeService")
public class PlantUtilizationPurposeServiceImpl implements PlantUtilizationPurposeService {

    @Autowired
    private PlantUtilizationPurposeMapper pupMapper;

    @Override
    public PlantUtilizationPurposeVo save(PlantUtilizationPurposeForm form) {
        PlantUtilizationPurpose entity = new PlantUtilizationPurpose();
        BeanUtils.copyProperties(form, entity);
        entity.setId(IdUtil.getUUId());
        pupMapper.insert(entity);
        return PlantUtilizationPurposeVo.entity2Vo(entity);
    }

    @Override
    public void deleteByUtilizationId(String utilizationId) {
        QueryWrapper<PlantUtilizationPurpose> wrapper = new QueryWrapper<>();
        wrapper.eq("UTILIZATION_ID", utilizationId);
        pupMapper.delete(wrapper);
    }

    @Override
    public List<PlantUtilizationPurposeVo> listByUtilizationId(String utilizationId) {
        QueryWrapper<PlantUtilizationPurpose> wrapper = new QueryWrapper<>();
        wrapper.eq("UTILIZATION_ID", utilizationId);
        List<PlantUtilizationPurpose> plantUtilizationPurposes = pupMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(plantUtilizationPurposes)) {
            List<PlantUtilizationPurposeVo> plantUtilizationPurposeVos = new ArrayList<>(plantUtilizationPurposes.size());
            for (PlantUtilizationPurpose pup : plantUtilizationPurposes) {
                plantUtilizationPurposeVos.add(PlantUtilizationPurposeVo.entity2Vo(pup));
            }
            return plantUtilizationPurposeVos;
        }
        return null;
    }
}
