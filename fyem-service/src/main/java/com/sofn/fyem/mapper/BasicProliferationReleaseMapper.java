package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.vo.BasicProliferationReleaseForm;
import com.sofn.fyem.vo.BasicProliferationReleaseVO;
import com.sofn.fyem.vo.ProliferationReleaseInfosVo;
import com.sofn.fyem.vo.ProliferationReleaseLocationDistributionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface BasicProliferationReleaseMapper extends BaseMapper<BasicProliferationRelease> {
    List<BasicProliferationReleaseVO> listBPRByBelongYear(Map map);

    BasicProliferationRelease selectByPrimaryKey(String id);

    int updateStatus(Map map);

    List<BasicProliferationReleaseForm> getBasicProliferationReleaseList(Map<String, Object> params);

    BasicProliferationRelease getBPRById(Map params);

    /**
     * 获取满足条件的信息数据list
     * @param params params
     * @return BasicProliferationRelease
     */
    List<BasicProliferationRelease> getBasicProliferationReleaseListByQuery(Map<String, Object> params);


    /**
     * 获取满足条件的信息
     * @param id id
     * @return BasicProliferationRelease
     */
    BasicProliferationRelease getBasicProliferationReleaseById(String id);

    /**
     * 修改效果评价数据
     * @param params
     */
    void updateReleaseEvaluate(Map<String, Object> params);


    List<BasicProliferationRelease> selectByBelongYearAndCountyId(Map<String, Object> params);

    /**
     * 获取增殖放流关键数据(全国)
     * @param params
     * @return
     */
    List<ProliferationReleaseInfosVo> getProliferationReleaseInfos(Map<String, Object> params);

    /**
     * 获取增殖放流关键数据（根据省id）
     * @param params
     * @return
     */
    List<ProliferationReleaseInfosVo> getProliferationReleaseInfosByProvinceId(Map<String, Object> params);

    /**
     * 获取增殖放流关键数据（根据市id）
     * @param params
     * @return
     */
    List<ProliferationReleaseInfosVo> getProliferationReleaseInfosByCityId(Map<String, Object> params);

    /**
     * 获取增殖放流关键数据（根据区县id）
     * @param params
     * @return
     */
    List<ProliferationReleaseInfosVo> getProliferationReleaseInfosByCountyId(Map<String, Object> params);

    /**
     * 统计满足条件的增殖放流数据数量
     * @param countParams
     * @return
     */
    int countBasicProliferationRelease(Map<String, Object> countParams);

    /**
     * 获取满足条件的分布信息数据(根据区县id)
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseLocationDistributionByCountyId(Map<String, Object> params);

    /**
     * 获取满足条件的分布信息数据(根据市id)
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseLocationDistributionByCityId(Map<String, Object> params);

    /**
     * 获取满足条件的分布信息数据(根据省id)
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseLocationDistributionByProvinceId(Map<String, Object> params);

    /**
     * 获取满足条件的分布信息数据
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseLocationDistribution(Map<String, Object> params);

    /**
     * 获取满足条件的点位分布数据
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleasePoints(Map params);

    /**
     * 获取满足条件的放流数量数据
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseCount(Map params);

    /**
     * 获取满足条件的投入资金数据
     * @param params
     * @return
     */
    List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseInvestFunds(Map params);
}