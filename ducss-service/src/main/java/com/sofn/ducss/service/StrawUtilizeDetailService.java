package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.StrawUtilize;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.vo.StrawUtilizeVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StrawUtilizeDetailService extends IService<StrawUtilizeDetail> {

    List<StrawUtilizeDetail> getStrawUtilizeDetail(String strawUtilizeId);

    String addOrUpdateStrawUtilizeDetail(StrawUtilizeVo strawUtilizeVo, String userId);

    void deleteStrawUtilizeById(String strawUtilizeId);

    void batchDeleteByUtilizeIds(List<String> utilizeIds);

    void getStrawUtilizeExcelList(Map<String, Object> params, HttpServletResponse response) throws IOException;

    //根据年度、省ID，查询规模化利用量大于marketEnd值的市场主体个数
    int getCountByCondition(String year, String provinceId, int marketEnd);

    void deleteByUtilizeIds(List<String> utilizeIds);

    // 分组查询出所有的
    List<StrawUtilize> getCountByConditionV2(String year, String provinceId);
}
