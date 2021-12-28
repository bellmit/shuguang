package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.CountryTask;
import com.sofn.ducss.model.ProStill;
import feign.Param;

import java.util.List;
import java.util.Map;

public interface ProStillMapper extends BaseMapper<ProStill> {

    List<ProStill> getProStillByPage(Map<String, Object> params);

    ProStill getProStill(Map<String, Object> params);

    ProStill selectProStillById(@Param(value = "id") String id);

    String selectProStillIdByYear(@Param(value = "id") String id);

    Integer getProStillTotalCount(Map<String, Object> params);

    Integer insertProStill(ProStill proStill);

    Integer deleteById(String id);

    List<ProStill> listGroupByYearAndAreaId(Map<String, Object> queryMap);
}