package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.VegetationPhysiology;
import com.sofn.agpjyz.vo.VegetationPhysiologyVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-02 16:36
 */
public interface VegetationPhysiologyService {
    int insertVegetationPhysiology(VegetationPhysiologyVo VegetationPhysiologyVo);
    int delVegetationPhysiology(String id);
    int updateVegetationPhysiology(VegetationPhysiology VegetationPhysiology);
    PageUtils<VegetationPhysiology> getVegetationPhysiology(Map map, int pageNo, int pageSize);
    VegetationPhysiology get(String id);
    void export(Map<String, Object> params, HttpServletResponse response);

}
