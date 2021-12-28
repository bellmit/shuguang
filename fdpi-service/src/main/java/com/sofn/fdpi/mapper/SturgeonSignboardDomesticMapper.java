package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SturgeonSignboard;
import com.sofn.fdpi.model.SturgeonSignboardDomestic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SturgeonSignboardDomesticMapper extends BaseMapper<SturgeonSignboardDomestic> {

    List<SturgeonSignboardDomestic> listByParams(Map<String, Object> params);

    void print(Map<String, Object> params);

    Integer getPrintNum(String signboardId);

    void updatePrintStatusBySturgeonSubIdAB(String sturgeonSubId);

    void updatePrintStatusBySturgeonSubIdS(String sturgeonSubId);

    List<String> getCaseNum(Map<String, Object> params);
}
