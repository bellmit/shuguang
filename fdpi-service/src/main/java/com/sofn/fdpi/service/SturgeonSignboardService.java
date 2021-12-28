package com.sofn.fdpi.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Sturgeon;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SturgeonSignboardVo;
import com.sofn.fdpi.vo.SturgeonSubVo;

import java.util.List;
import java.util.Map;

public interface SturgeonSignboardService {

    /**
     * 新增
     */
    void savaBatch(Sturgeon sturgeon, List<SturgeonSubVo> sturgeonSubVos, String type);

    /**
     * 分页查询
     */
    PageUtils<SturgeonSignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 查询
     */
    List<SturgeonSignboardVo> list(Map<String, Object> params);

    /**
     * 打印
     */
    void print(String label, List<String> ids);

    /**
     * 获取打印箱号下拉
     */
    List<SelectVo> getCaseNum(Map<String, Object> params);

    /**
     *  根据主表id改变打印状态
     */
    void updatePirngStatusBySturgeonId(String sturgeonId, String printStatus);


    void validRrepeat2(SturgeonSubVo sturgeonSubVo, Integer startNum, Integer endNum);

    int delByCompId(String compId);
}
