package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.Zone;
import com.sofn.common.utils.PageUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

public interface ZoneService {

    /**
     * 新增
     */
    Zone save(Zone entity, String operator, Date now);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(Zone entity);

    /**
     * 详情
     */
    Zone get(String id);

    /**
     * 导出模版
     */
    void downTemplate(HttpServletResponse response);

    /**
     * 导入数据
     */
    void importZone(MultipartFile multipartFile);

    /**
     * 导出数据
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    /**
     * 分页查询保护区管理
     */
    PageUtils<Zone> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

}
