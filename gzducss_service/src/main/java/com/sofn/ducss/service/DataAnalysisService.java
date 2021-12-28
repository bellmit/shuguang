package com.sofn.ducss.service;

import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.DataKingDto;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/8/3 0003 10:00
 *  数据分析serivce
 **/
public interface DataAnalysisService {
    /***
     * 分析数据展示分页列表
     * @param paramMap
     * @return
     */
    Result<PageUtils<DataKingDto>> getDataList(HashMap<String, String> paramMap);

    /***
     * 导出分析数据
     * @param paramMap
     * @return
     */
    void datasAnalysisExport(HashMap<String, String> paramMap, HttpServletResponse response) throws Exception;
}
