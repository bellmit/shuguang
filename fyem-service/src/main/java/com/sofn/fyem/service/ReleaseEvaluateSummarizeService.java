package com.sofn.fyem.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.excelmodel.BasicProliferationReleaseExcel;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.vo.BasicProliferationReleaseVO;
import com.sofn.fyem.vo.EvaluateStandardValueVo;
import com.sofn.fyem.vo.ReleaseEvaluateSummarizeVo;

import java.util.List;
import java.util.Map;

/**
 * 放流评价汇总接口
 * @author Administrator
 */
public interface ReleaseEvaluateSummarizeService {

    /**
     * 获取水生物增值放流基础数据list(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<BasicProliferationRelease>
     */
    PageUtils<BasicProliferationReleaseVO> getBasicProliferationReleaseListByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 获取水生物增值放流基础数据list(不分页)
     * @param params 查询条件
     * @return List<BasicProliferationRelease>
     */
    List<BasicProliferationReleaseVO> getBasicProliferationReleaseListByQuery(Map<String,Object> params);


    /**
     * 指标评价分数结构
     * @param id id
     * @return ReleaseEvaluateSummarizeVo
     */
    ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeType(String id);

    /**
     * 获取指标评价分数信息
     * @param id id
     * @return ReleaseEvaluateSummarizeVo
     */
    ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeVo(String id,String belongYear);

    /**
     * 获取指标评价分数详情
     * @param id id
     * @return ReleaseEvaluateSummarizeVo
     */
    ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeHistory(String id,String belongYear);
    /**
     * 新增指标评价分数
     * @param evaluateStandardValueVo evaluateStandardValueVo
     */
    void addReleaseEvaluateSummarizeVo(EvaluateStandardValueVo evaluateStandardValueVo);

    /**
     * 修改指标评价分数
     * @param evaluateStandardValueVo evaluateStandardValueVo
     */
    void updateReleaseEvaluateSummarizeVo(EvaluateStandardValueVo evaluateStandardValueVo);

    /**
     * 获取满足条件的信息数据list(导出)
     * @param params params
     * @return BasicProliferationReleaseExcel
     */
    List<BasicProliferationReleaseExcel> getBasicProliferationReleaseExcel(Map<String, Object> params);

}
