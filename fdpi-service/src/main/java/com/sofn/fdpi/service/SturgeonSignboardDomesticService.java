package com.sofn.fdpi.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Sturgeon;
import com.sofn.fdpi.model.SturgeonSignboardDomestic;
import com.sofn.fdpi.model.SturgeonSub;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SturgeonSignboardVo;
import com.sofn.fdpi.vo.SturgeonSubVo;

import java.util.List;
import java.util.Map;

public interface SturgeonSignboardDomesticService {

    String savaBatch(Sturgeon sturgeon, List<SturgeonSubVo> sturgeonSubVos, String thirdPrint);

    /**
     * 验证编码重复
     */
    void validSignboard(SturgeonSub entity);

    /**
     * 分页查询
     */
    PageUtils<SturgeonSignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    List<SturgeonSignboardVo> list(Map<String, Object> params);

    Integer getPrintNum(String signboardId);

    void updatePrintStatusBySturgeonSubId(String sturgeonSubId);

    List<SturgeonSignboardDomestic> listBySturgeonSubIds(List<String> sturgeonSubId, String printStatus);

    void print(String label, List<String> ids);

    List<SturgeonSignboardDomestic> listRepring(String compId, List<String> signboardIds, String delFlag);

    void updatePirngStatusBySturgeonIds(List<String> signboardIds, String thirdPrint, String printStatus);

    List<SelectVo> getCaseNum(Map<String, Object> params);

    List<SturgeonSignboardDomestic> listSignboardBySignboardIds(List<String> signboardIds);

    int delByCompId(String compId);
}
