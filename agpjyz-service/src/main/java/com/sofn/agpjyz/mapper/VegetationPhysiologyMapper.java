package com.sofn.agpjyz.mapper;

import com.sofn.agpjyz.model.Halophytes;
import com.sofn.agpjyz.model.VegetationPhysiology;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-02 16:36
 */
public interface VegetationPhysiologyMapper {

    int insertVegetationPhysiology(VegetationPhysiology halophytes);
    int delVegetationPhysiology(@Param("id") String id);
    int updateVegetationPhysiology(VegetationPhysiology halophytes);
    List<VegetationPhysiology> getVegetationPhysiology(Map map);
    VegetationPhysiology get(@Param("id") String id);

    List<VegetationPhysiology> getData(Map map);
}
