package com.sofn.fyrpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sofn.fyrpa.model.AquaticResourcesProtectionInfo;
import com.sofn.fyrpa.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AquaticResourcesProtectionInfoMapper extends BaseMapper<AquaticResourcesProtectionInfo> {

    /**
     * 保护区分页查询
     * @param page
     * @param submitTime
     * @param keyword
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectList(Page<AquaticResourcesProtectionInfoVoList> page, @Param("submitTime") String submitTime, @Param("keyword") String keyword,@Param("createUserId")String createUserId);

    /**
     * 保护区分页查询
     * @param page
     * @param submitTime
     * @param keyword
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectListByUserIds(Page<AquaticResourcesProtectionInfoVoList> page, @Param("submitTime") String submitTime, @Param("keyword") String keyword,@Param("userIds")List<String> userIds);

    /**
     * 省级分页查询
     * @param page
     * @param submitTime
     * @param keyword
     * @param areaName 省代码
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList>selectList2(Page<AquaticResourcesProtectionInfoVoList> page, @Param("submitTime") String submitTime,@Param("keyword") String keyword,@Param("regionCode") String regionCode,@Param("areaName")String areaName);

    /**
     * 专家级分页查询
     * @param page
     * @param submitTime
     * @param keyword
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList>selectList3(Page<AquaticResourcesProtectionInfoVoList> page, @Param("submitTime") String submitTime,
                                                           @Param("keyword") String keyword,@Param("regionCode") String regionCode,@Param("checker")String checker);

    /**
     * 保护区汇总信息分页查询
     * @param page
     * @param submitTime
     * @param regionCode
     * @param basinOrSeaArea
     * @param riverOrMaritimeSpace
     * @param keyword
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectGatherListByCondition(Page<AquaticResourcesProtectionInfoVoList> page,@Param("submitTime") String submitTime, @Param("regionCode")String regionCode,
                                                                          @Param("basinOrSeaArea")String basinOrSeaArea,@Param("riverOrMaritimeSpace") String riverOrMaritimeSpace,
                                                                          @Param("keyword") String keyword,@Param("name")String name,@Param("majorProtectObject")String majorProtectObject);

    /**
     * 保护区汇总信息全部查询
     * @param submitTime
     * @param regionCode
     * @param basinOrSeaArea
     * @param riverOrMaritimeSpace
     * @param keyword
     * @param name
     * @param majorProtectObject
     * @return
     */
    List<AquaticResourcesProtectionInfoVoList> selectGatherListByCondition(@Param("submitTime") String submitTime, @Param("regionCode")String regionCode,
                                                                            @Param("basinOrSeaArea")String basinOrSeaArea,@Param("riverOrMaritimeSpace") String riverOrMaritimeSpace,
                                                                            @Param("keyword") String keyword,@Param("name")String name,@Param("majorProtectObject")String majorProtectObject);


    /**
     * 保护区汇总信息按总面积倒序查询
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectGatherListByAreasDesc(Page<AquaticResourcesProtectionInfoVoList> page,@Param("submitTime") String submitTime, @Param("regionCode")String regionCode,
                                                                            @Param("basinOrSeaArea")String basinOrSeaArea,@Param("riverOrMaritimeSpace") String riverOrMaritimeSpace,
                                                                            @Param("keyword") String keyword,@Param("name")String name,@Param("majorProtectObject")String majorProtectObject);

    /**
     * 保护区汇总信息按总面积升序查询
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectGatherListByAreasAsc(Page<AquaticResourcesProtectionInfoVoList> page,@Param("submitTime") String submitTime, @Param("regionCode")String regionCode,
                                                                           @Param("basinOrSeaArea")String basinOrSeaArea,@Param("riverOrMaritimeSpace") String riverOrMaritimeSpace,
                                                                           @Param("keyword") String keyword,@Param("name")String name,@Param("majorProtectObject")String majorProtectObject);

    /**
     * 保护区汇总信息按批准时间倒序查询
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectGatherListByTimesDesc(Page<AquaticResourcesProtectionInfoVoList> page,@Param("submitTime") String submitTime, @Param("regionCode")String regionCode,
                                                                            @Param("basinOrSeaArea")String basinOrSeaArea,@Param("riverOrMaritimeSpace") String riverOrMaritimeSpace,
                                                                            @Param("keyword") String keyword,@Param("name")String name,@Param("majorProtectObject")String majorProtectObject);

    /**
     * 保护区汇总信息按批准时间升序查询
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList> selectGatherListByTimesAsc(Page<AquaticResourcesProtectionInfoVoList> page,@Param("submitTime") String submitTime, @Param("regionCode")String regionCode,
                                                                           @Param("basinOrSeaArea")String basinOrSeaArea,@Param("riverOrMaritimeSpace") String riverOrMaritimeSpace,
                                                                           @Param("keyword") String keyword,@Param("name")String name,@Param("majorProtectObject")String majorProtectObject);

    /**
     * 按保护区分布查询
     * @param submitTime
     * @param name
     * @return
     */
    List<SpatialAnalystResourcesVoList> selectSpatialAnalystByCondition(@Param("submitTime") String submitTime, @Param("name")String name);

    /**
     * 统计保护区数量
     * @param submitTime
     * @param regionCode
     * @return
     */
    int selectCounts(@Param("submitTime") String submitTime,@Param("regionCode")String regionCode);

    /**
     * 面积求和
     * @param submitTime
     * @param regionCode
     * @return
     */
    AreasVo selectSums(@Param("submitTime") String submitTime, @Param("regionCode")String regionCode);

    /**
     * 评价效果分析列表
     * @param page
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @return
     */
    IPage<ResourceAppraiseAnalyseVoList> selectResourceAnalyseList(Page<ResourceAppraiseAnalyseVoList>page,@Param("name")String name,
                                                                  @Param("submitTime")String submitTime,
                                                                  @Param("startTotalScore")Double startTotalScore,
                                                                  @Param("endTotalScore")Double endTotalScore,
                                                                  @Param("basinOrSeaArea")String basinOrSeaArea);


    /**
     * 按批准时间降序排列
     * @param page
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @return
     */
    IPage<ResourceAppraiseAnalyseVoList> selectListByTimeDesc(Page<ResourceAppraiseAnalyseVoList>page,@Param("name")String name,
                                                                   @Param("submitTime")String submitTime,
                                                                   @Param("startTotalScore")Double startTotalScore,
                                                                   @Param("endTotalScore")Double endTotalScore,
                                                                   @Param("basinOrSeaArea")String basinOrSeaArea);

    /**
     * 按批准时间升序排列
     * @param page
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @return
     */
    IPage<ResourceAppraiseAnalyseVoList> selectListByTimeAsc(Page<ResourceAppraiseAnalyseVoList>page,@Param("name")String name,
                                                              @Param("submitTime")String submitTime,
                                                              @Param("startTotalScore")Double startTotalScore,
                                                              @Param("endTotalScore")Double endTotalScore,
                                                              @Param("basinOrSeaArea")String basinOrSeaArea);

    /**
     * 按评分效果降序排列
     * @param page
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @return
     */
    IPage<ResourceAppraiseAnalyseVoList> selectListByScoreDesc(Page<ResourceAppraiseAnalyseVoList>page,@Param("name")String name,
                                                             @Param("submitTime")String submitTime,
                                                             @Param("startTotalScore")Double startTotalScore,
                                                             @Param("endTotalScore")Double endTotalScore,
                                                             @Param("basinOrSeaArea")String basinOrSeaArea);


    /**
     * 按评分效果升序排列
     * @param page
     * @param name
     * @param submitTime
     * @param startTotalScore
     * @param endTotalScore
     * @param basinOrSeaArea
     * @return
     */
    IPage<ResourceAppraiseAnalyseVoList> selectListByScoreAsc(Page<ResourceAppraiseAnalyseVoList>page,@Param("name")String name,
                                                             @Param("submitTime")String submitTime,
                                                             @Param("startTotalScore")Double startTotalScore,
                                                             @Param("endTotalScore")Double endTotalScore,
                                                             @Param("basinOrSeaArea")String basinOrSeaArea);


    /**
     * 查询上个年度的评分
     * @param submitTime
     * @return
     */
    Integer selectLastYearScore(@Param("submitTime") String submitTime,@Param("name")String name);


    /**
     * 查询保护区信息
     * @param name
     * @return
     */
    AquaticResourcesProtectionInfo selectByName(@Param("name")String name,@Param("submitTime")String submitTime);

    /**
     * 查询筛选当前省级已通过的数据
     * @param page
     * @param submitTime
     * @param keyword
     * @param regionCode
     * @param areaName
     * @return
     */
    IPage<AquaticResourcesProtectionInfoVoList>selectListIsPass(Page<AquaticResourcesProtectionInfoVoList> page, @Param("submitTime") String submitTime,@Param("keyword") String keyword,@Param("regionCode") String regionCode,@Param("areaName")String areaName);


    /**
     * 查询省级的保护区集合
     * @param submitTime
     * @param name
     * @return
     */
    List<ProvinceVo> selectProvinceProtectionList(@Param("submitTime")String submitTime,@Param("name")String name,@Param("regionCode")String regionCode);

    /**
     * 查询市级保护区集合
     * @param submitTime
     * @param name
     * @param regionCode
     * @return
     */
    List<CityVo> selectCityProtectionList(@Param("submitTime")String submitTime,@Param("name")String name,@Param("regionCode")String regionCode);

    /**
     * 查询县(区)级保护区集合
     * @param submitTime
     * @param name
     * @param regionCode
     * @return
     */
    List<CountyVo> selectCountyProtectionList(@Param("submitTime")String submitTime,@Param("name")String name,@Param("regionCode")String regionCode);


    /**
     * 查询省级区域码集合
     * @return
     */
    List<AquaticResourcesProtectionInfo> selectOneRegionCodeList(@Param("submitTime")String submitTime,@Param("name")String name);

    /**
     * 查询市级区域码集合
     * @return
     */
    List<AquaticResourcesProtectionInfo> selectTwoRegionCodeList(@Param("submitTime")String submitTime,@Param("name")String name);

    /**
     * 查询县级区域码集合
     * @return
     */
    List<AquaticResourcesProtectionInfo> selectThreeRegionCodeList(@Param("submitTime")String submitTime,@Param("name")String name);
}