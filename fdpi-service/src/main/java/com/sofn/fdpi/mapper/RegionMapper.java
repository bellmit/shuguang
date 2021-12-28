package com.sofn.fdpi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Region;
import org.apache.ibatis.annotations.Update;

public interface RegionMapper extends BaseMapper<Region> {

    @Update("truncate table sys_region")
    void deleteAll();
}
