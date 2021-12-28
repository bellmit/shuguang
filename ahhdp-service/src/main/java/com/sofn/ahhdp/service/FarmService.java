package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.Farm;
import com.sofn.common.utils.PageUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:22
 */
public interface FarmService {
    /**
     * 新增
     */
    Farm save(Farm entity, String operator, Date now);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(Farm entity);

    /**
     * 详情
     */
    Farm get(String id);

    /**
     * 导出模版
     */
    void downTemplate(HttpServletResponse response);

    /**
     * 导入数据
     */
    void importFarm(MultipartFile multipartFile);

    /**
     * 导出数据
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    /**
     * 分页查询保护区管理
     */
    PageUtils<Farm> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);
}
