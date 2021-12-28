package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.ProliferationReleaseStatistics;
import com.sofn.fyem.vo.ReleaseStatisticsCountVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 中央财政农业资源及生态保护补助项目增殖放流情况统计表Mapper
 * @Author: mcc
 */
@Mapper
public interface ProliferationReleaseStatisticsMapper extends BaseMapper<ProliferationReleaseStatistics> {

    /**
     * 获取增殖放流情况信息
     * @param params params
     * @return List<ReporManagement>
     */
    List<ProliferationReleaseStatistics> getProliferationReleaseStatisticsList(Map<String,Object> params);

    /**
     * 获取满足条件的增殖放流情况统计表信息
     * @param params 查询条件
     * @return ProliferationReleaseStatistics
     */
    ProliferationReleaseStatistics getProliferationReleaseStatistics(Map<String,Object> params);


    /**
     * 修改增殖放流情况统计表信息状态
     * @param params params
     * @return int
     */
    int updateStatus(Map<String,Object> params);

    /**
     * 删除增殖放流情况统计表信息
     * @param params params
     * @return true or false
     */
    boolean deleteProliferationReleaseStatistics(Map<String,Object> params);

    /**
     * 统计满足条件的增殖放流数据数量
     * @param countParams
     * @return
     */
    int countProliferationReleaseStatistics(Map<String, Object> countParams);

    /**
     * 获取增殖放流情况地区统计数据
     * @param params params
     * @return ReleaseStatisticsCountVo
     */
    ProliferationReleaseStatistics getReleaseStatisticsCount(Map<String,Object> params);

    /**
     * 获取增殖放流情况地区统计数据(查看省)
     * @param params params
     * @return ReleaseStatisticsCountVo
     */
    List<ProliferationReleaseStatistics> getReleaseStatisticsCountByProvince(Map<String,Object> params);

    /**
     * 获取增殖放流情况地区统计数据(查看省下面的市)
     * @param params params
     * @return ReleaseStatisticsCountVo
     */
    List<ProliferationReleaseStatistics> getReleaseStatisticsCountByCity(Map<String,Object> params);

    /**
     * 获取增殖放流情况地区统计数据(查看市下面的区县)
     * @param params params
     * @return ReleaseStatisticsCountVo
     */
    List<ProliferationReleaseStatistics> getReleaseStatisticsCountByCounty(Map<String,Object> params);
}