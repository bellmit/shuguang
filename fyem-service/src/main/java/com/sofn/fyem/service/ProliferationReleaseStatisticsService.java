package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fyem.model.ProliferationReleaseStatistics;
import com.sofn.fyem.vo.ReleaseStatisticsCountVo;
import com.sofn.fyem.vo.ReleaseStatisticsForm;
import com.sofn.fyem.vo.ReleaseStatisticsSpeciesVo;

import java.util.List;
import java.util.Map;

/**
 * 中央财政农业资源及生态保护补助项目增殖放流情况统计表模块
 * @author Administrator
 */
public interface ProliferationReleaseStatisticsService extends IService<ProliferationReleaseStatistics> {

    /**
     * 获取增殖放流情况统计列表
     * @param params params
     * @return ReleaseStatisticsSpeciesVo
     */
    List<ReleaseStatisticsForm> getReleaseStatisticsForm(Map<String,Object> params);

    /**
     * 获取增殖放流情况信息
     * @param params params
     * @return List<ReporManagement>
     */
    List<ProliferationReleaseStatistics> getProliferationReleaseStatisticsList(Map<String,Object> params);


    /**
     * 获取增殖放流情况统计详情
     * @param params params
     * @return ReleaseStatisticsSpeciesVo
     */
    ReleaseStatisticsSpeciesVo getReleaseStatisticsSpeciesVo(Map<String,Object> params);

    /**
     * 修改增殖放流情况信息状态
     * @param params params
     */
    int updateStatus(Map<String,Object> params);

    /**
     * 新增增殖放流情况信息
     * @param releaseStatisticsSpeciesVo releaseStatisticsSpeciesVo
     */
    void addReleaseStatisticsSpeciesVo(ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo);

    /**
     * 修改增殖放流情况信息
     * @param releaseStatisticsSpeciesVo releaseStatisticsSpeciesVo
     */
    void updateReleaseStatisticsSpeciesVo(ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo);

    /**
     * 删除增殖放流情况信息
     * @param params params
     */
    void removeReleaseStatisticsSpeciesVo(Map<String,Object> params);


    /**
     * 获取增殖放流情况地区统计数据
     * @param params params
     * @return ReleaseStatisticsCountVo
     */
    ReleaseStatisticsCountVo getReleaseStatisticsCount(Map<String,Object> params);

    /**
     * 获取增殖放流情况全地区统计数据
     * @param params params
     * @return ReleaseStatisticsCountVo
     */
    List<ReleaseStatisticsCountVo> getReleaseStatisticsAllCount(Map<String,Object> params);

    /**
     * 获取增殖放流情况统计列表当前机构及其父级结构增殖放流列表
     * @return
     */
    List<ProliferationReleaseStatistics> getProliferationReleaseStatisticsByBelongYearAndOrgId(String belongYear, String orgId);
}
