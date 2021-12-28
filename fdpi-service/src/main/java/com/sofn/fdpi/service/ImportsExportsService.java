package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.vo.ImportExcel;
import com.sofn.fdpi.vo.ImportsExportsForm;
import com.sofn.fdpi.vo.SpeExcel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 16:10
 */
public interface ImportsExportsService extends IService<ImportsExports> {
    /**
     * 插入进出口记录
     * @param
     * @return
     */
        int insertImportsExports(ImportsExports importsExports);

    /**
     * 通过id获取出入口信息
     * @param id
     * @return
     */
    ImportsExports getImportsExportsById(String id);

    PageUtils<ImportsExports> getImportsExportsListPage(Map<String, Object> map, int pageNo, int pageSize);

    void updateImportsExports(ImportsExportsForm importsExports);
    int delImportsExports(String id);
    String add(List<ImportExcel> importList);
    ImportsExports print(String id);

    String importData(MultipartFile file, ImportsExportsService importsexportsservice);

    void importsExportsWirte(List<ImportsExports> list);

    /**
     * 重设缓存
     */
    void saveCache();

    void downDataCountTemplate(HttpServletResponse response);
}
