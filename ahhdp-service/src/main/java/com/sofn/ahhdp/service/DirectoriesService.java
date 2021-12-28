package com.sofn.ahhdp.service;



import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-20 17:55
 */
public interface DirectoriesService {
    /**
     * 新增
     */
    Directories save(Directories entity, String operator, Date now);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(Directories entity);

    /**
     * 详情
     */
    Directories get(String id);

    /**
     * 导出模版
     */
    void downTemplate(HttpServletResponse response);

    /**
     * 导入数据
     */
    void importDirectories(MultipartFile multipartFile);

    /**
     * 导出数据
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    /**
     * 分页查询保护区管理
     */
    PageUtils<Directories> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);
    List<DropDownVo> getRusult(String provinceCode);
    List<DropDownVo> getOldName();
}
