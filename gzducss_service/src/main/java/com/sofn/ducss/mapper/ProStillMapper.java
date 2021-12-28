package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.ProStill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProStillMapper extends BaseMapper<ProStill> {

    List<ProStill> getProStillByPage(Map<String, Object> params);

    ProStill getProStill(Map<String, Object> params);

    ProStill selectProStillById(@Param(value = "id") String id);

    String selectProStillIdByYear(@Param(value = "countyId") String id);

    Integer getProStillTotalCount(Map<String, Object> params);

    Integer insertProStill(ProStill proStill);

    Integer deleteById(String id);
}