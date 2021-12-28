package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.Biomonitoring;
import com.sofn.agsjdm.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 生物监测信息服务接口
 */
public interface BiomonitoringService {

    /**
     * 新增
     */
    Biomonitoring save(Biomonitoring entity);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(Biomonitoring entity);

    /**
     * 详情
     */
    Biomonitoring get(String id);

    /**
     * 分页查询
     */
    PageUtils<Biomonitoring> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    List<DropDownVo> getYears();

    Biomonitoring getByParams(Map map);
}
