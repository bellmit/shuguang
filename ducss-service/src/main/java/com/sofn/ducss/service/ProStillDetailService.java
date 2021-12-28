package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExportExcel;
import com.sofn.ducss.vo.ProStillVo;

import java.util.List;
import java.util.Map;

/**
 * 获取当前年任务
 */
public interface ProStillDetailService extends IService<ProStillDetail> {

    List<ProStillDetail> getProStillDetail(String proStillId);

    String addOrUpdateProStill(ProStillVo proStillVo, String userId);

    void deleteProStillById(String proStillId);

    List<YieldAndReturnExportExcel> getProStillExportExcelById(String proStillId);

    List<ProStillDetail> getProStillDetailListByAreaId(String areaId, String year);

    List<YieldAndReturnExportExcel> getExportExcelByAredIdAndYear(Map<String, Object> queryMap);

}
