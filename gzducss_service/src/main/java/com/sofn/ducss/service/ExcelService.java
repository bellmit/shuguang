package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.basemodel.Result;

import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.vo.excelVo.DataAnalysisExcelVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName ExcelService
 * @Description TODO
 * @Author Chlf
 * @Date2020/7/1 10:11
 * Version1.0
 **/
public interface ExcelService extends IService<ProStillDetail> {
    /**
     * 下载产生量与直接还田量模板
     */
    void downloadTemplate(HttpServletResponse response);

    /**
     * 导入产生量与直接还田量
     */
    Result importExcel(MultipartFile file, String year);

    /**
     * 下载农户分散利用量模板
     */
    void downloadDisperseUtilizeTemplate(HttpServletResponse response) throws IOException;

    /**
     * 导入农户分散利用量
     */
    Result importDisperseUtilize(MultipartFile file, String year);

    /***
     * 导入农户分散利用量
     * @param file
     * @param year
     * @return
     */
    Result importDisperseUtilizeV2(MultipartFile file, String year);

    /**
     * 下载市场主体规模化秸秆利用量模板
     */
    void downloadStrawUtilizeTemplate(HttpServletResponse response) throws IOException;

    /**
     * 导入市场主体规模化秸秆利用量
     */
    Result importStrawUtilizeExcel(MultipartFile file, String year);


    void dataAnalysisExport(List<DataAnalysisExcelVo> list, HttpServletResponse response);

    /***
     * zip 导出
     * @param response
     * @param year
     */
    void zipExport(HttpServletResponse response,String year) throws IOException;
}
