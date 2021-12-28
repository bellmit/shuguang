package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.BasicsCollection;
import com.sofn.agsjdm.model.FacilityMaintenance;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 9:19
 */
public interface FacilityMaintenanceMapper extends BaseMapper<FacilityMaintenance> {
    List<FacilityMaintenance> listPage(Map map);
}
