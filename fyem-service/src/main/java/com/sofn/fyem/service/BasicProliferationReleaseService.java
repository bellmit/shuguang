package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.map.MapComponentImpl;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.excelmodel.BasicProliferationReleaseExcel;
import com.sofn.fyem.excelmodel.ProliferationReleaseDistributionExcel;
import com.sofn.fyem.excelmodel.ProliferationReleaseExcel;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.vo.*;

import java.util.List;
import java.util.Map;

public interface BasicProliferationReleaseService extends IService<BasicProliferationRelease>, MapComponentImpl {
    /**
     * 水生生物增殖放流基础数据展示（所属年度）
     * @param params
     * @return
     */
    List<BasicProliferationReleaseVO> listBPRByBelongYear(Map<String, Object> params);

    /**
     * 水生生物增殖放流基础数据展示（分页）
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<BasicProliferationReleaseVO> getBasicProliferationReleaseListByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 水生生物增殖放流基础数据新增
     * @param form 水生生物增殖放流基础数据表单
     */
    String insert(BasicProliferationReleaseForm form, String token);

    /**
     * 水生生物增殖放流基础数据修改
     * @param form 水生生物增殖放流基础数据表单
     * @param token
     * @return
     */
    String update(BasicProliferationReleaseForm form,String token);

    /**
     * 水生生物增殖放流基础数据删除
     * @param id
     * @return
     */
    String delete(String id);

    /**
     * 水生生物增殖放流基础数据获取
     * @param id
     * @return
     */
    BasicProliferationReleaseForm getBPRById(String id);

    /**
     * 水生生物增殖放流基础数据列表获取
     * @param params
     * @return
     */
    List<BasicProliferationReleaseForm> getBasicProliferationReleaseList(Map<String, Object> params);

    /**
     * 获取增殖放流关键数据
     * @param params
     * @return
     */
    List<ProliferationReleaseInfosVo> getProliferationReleaseInfos(Map<String, Object> params);

    /**
     * 获取增殖放流关键数据(分页)
     * @param params
     * @return
     */
    PageUtils<ProliferationReleaseInfosVo> getProliferationReleaseInfosByPage(Map<String, Object> params);


    /**
     * 获取满足条件的信息数据list
     * @param params params
     * @return BasicProliferationRelease
     */
    List<BasicProliferationReleaseVO> getBasicProliferationReleaseListByQuery(Map<String, Object> params);



    /**
     * 修改效果评价数据
     * @param params
     */
    void updateReleaseEvaluate(Map<String, Object> params);

    /**
     * 获取满足条件的信息
     * @param id id
     * @return BasicProliferationRelease
     */
    BasicProliferationRelease getBasicProliferationReleaseById(String id);

    /**
     * 获取满足条件的信息数据list(导出)
     * @param params params
     * @return BasicProliferationReleaseExcel
     */
    List<BasicProliferationReleaseExcel> getBasicProliferationReleaseExcel(Map<String, Object> params);

    /**
     * 获取满足条件的统计信息数据list(导出)
     * @param params
     * @return
     */
    List<ProliferationReleaseExcel> getProliferationReleaseExcel(Map<String, Object> params);

    /**
     * 获取满足条件的分布信息数据(分页)
     * @param params
     * @return
     */
    PageUtils<ProliferationReleaseLocationDistributionVo> getProliferationReleaseDistributionInfosByPage(Map<String, Object> params);

    /**
     * 获取满足条件的分布信息数据（废弃）
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseDistributionInfos(Map<String, Object> params);

    /**
     * 获取满足条件的分布信息数据(导出)
     * @param params
     * @return
     */
    List<ProliferationReleaseDistributionExcel> getProliferationReleaseDistributionExcel(Map<String, Object> params);

    /**
     * 获取指标参数列表
     * @param desc
     * @return
     */
    List<MapIndexs> getMapIndexs(String desc);

    /**
     * 列出指定年份指定机构及其父级的水生生物增殖放流数据
     * @param belongYear 指定年份
     * @param orgId 机构
     * @return
     */
    List<BasicProliferationRelease> listBPRByBelongYearAndOrgId(String belongYear, String orgId);
}
