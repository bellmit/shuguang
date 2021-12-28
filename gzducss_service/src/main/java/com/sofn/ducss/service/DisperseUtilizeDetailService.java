package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExportExcel;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.DisperseUtilizeVo;

import java.util.List;

public interface DisperseUtilizeDetailService extends IService<DisperseUtilizeDetail> {

    List<DisperseUtilizeDetail> getDisperseUtilizeDetail(String disperseUtilizeId);

    String addOrUpdateDisperseUtilizeDetail(DisperseUtilizeVo disperseUtilizeVo, String userId);


    void deleteDisperseUtilizeById(String disperseUtilizeId);

    void batchDeleteByUtilizeIds(List<String> utilizeIds);

    //县级导出农户分散利用量（废弃）
    PageUtils<DisperseUtilizeExportExcel> getDisperseUtilizeDetailByPage(Integer pageNo, Integer pageSize, String year, String userName, String countyId, String dateBegin, String dateEnd);

    List<DisperseUtilizeExportExcel> getDisperseUtilizeDetailExl(String year, String userName, String countyId, String dateBegin, String dateEnd);

    void statisticalAssignment();

    String createFillNumber(String areaId);

    void deleteByUtilizeIds(List<String> utilizeIds);

}
