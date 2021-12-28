package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.TargetSpecies;
import com.sofn.agpjyz.vo.AnalysisVo;
import com.sofn.agpjyz.vo.TargetSpeciesForm;
import com.sofn.agpjyz.vo.TargetSpeciesVo;
import com.sofn.agpjyz.vo.YearVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 目标物种基础信息收集模块服务接口
 *
 * @Author yumao
 * @Date 2020/2/25 17:19
 **/
public interface TargetSpeciesCollectService {

    /**
     * 新增
     */
    TargetSpeciesVo save(TargetSpeciesForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(TargetSpeciesForm form);

    /**
     * 详情
     */
    TargetSpeciesVo get(String id);

    /**
     * 分页查询
     */
    PageUtils<TargetSpeciesVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    /**
     * 根据植物名模糊搜索
     * @param Name
     * @return
     */
    List<TargetSpeciesVo> listByName(String Name);

    /**
     * 获取年份
     * @return
     */
    List<YearVo> getYear();
    /**
     *
     */
    AnalysisVo getResult(Map map);
}
