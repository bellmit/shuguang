package com.sofn.agpjyz.service;

import com.sofn.agpjyz.vo.SourceForm;
import com.sofn.agpjyz.vo.SourceLastVo;
import com.sofn.agpjyz.vo.SourceVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 资源调查模块服务接口
 *
 * @Author yumao
 * @Date 2020/3/10 15:52
 **/
public interface SourceService {


    /**
     * 新增
     */
    SourceVo save(SourceForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(SourceForm form);

    /**
     * 详情
     */
    SourceVo get(String id);

    /**
     * 获取最后一次填报基础信息
     */
    SourceLastVo getLastCommit();

    /**
     * 分页查询
     */
    PageUtils<SourceVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 撤回
     */
    void cancel(String id);

    /**
     * 审核通过
     */
    void auditPass(String ids, String auditOpinion);

    /**
     * 审核退回
     */
    void auditReturn(String ids, String auditOpinion);

    /**
     * 根据模板导出
     */
    void exportByTemplate(Map<String, Object> params, HttpServletResponse response);


    void report(String id);
}
