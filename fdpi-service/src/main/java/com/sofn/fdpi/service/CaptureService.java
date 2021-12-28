package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Capture;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.vo.CaptureExcel;
import com.sofn.fdpi.vo.CaptureForm;
import com.sofn.fdpi.vo.SpeExcel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 10:05
 */
public interface CaptureService extends IService<Capture> {
    /**
     * 插入特许捕获证书
     * @param capture
     * @return
     */
    int addCapture(Capture capture);
    /**
     * 通过证书编号查看特许捕获证书详细信息
     * @param id
     * @return
     */
    Capture selectCaptureInfoByPapersNumber(String id);

    /**
     * 获取捕获证书列表（分页）
     * @return
     */
    PageUtils<Capture> getCaptureList(Map<String, Object> map, int pageNo, int pageSize);

    /**
     * 删除特许捕获证
     * @param id
     * @return
     */
    int delCapture(String id, String proLevel);

    /**
     * 修改特许捕获证信息
     * @param capture
     * @return
     */
    int updateCapture(Capture capture);
    String add(List<CaptureExcel> importList);
    Capture print(String id);
    String importData(MultipartFile file, CaptureService captureService);

    /**
     *重新设置缓存 特许捕获证的id和证书编号
     */
    void setCache();

    void downDataCountTemplate(HttpServletResponse response);

    void export(Map<String, Object> params, HttpServletResponse hsr);
}
