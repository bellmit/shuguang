package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.MaintenanceRegistration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaintenanceRegistrationMapper  extends BaseMapper<MaintenanceRegistration> {
    int deleteByPrimaryKey(String id);

    int updateByPrimaryKey(MaintenanceRegistration record);

    List<MaintenanceRegistration> findMaintenanceRegistrationByCondition(Map<String,Object> params);
}