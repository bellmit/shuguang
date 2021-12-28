package com.sofn.ducss.mapper;

import com.sofn.ducss.vo.MainBodyUsageSummaryVo;
import com.sofn.ducss.vo.StrawUtilizeInfoAndDetailInfoVo;
import com.sofn.ducss.vo.excelVo.MainBodyUsageSummaryExcelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MainBodyUsageSummaryMapper {

    /**
     * 从汇总表中获取市场主体规模化利用汇总数据
     *
     * @param year       年度
     * @param areaIds  区划ID
     * @param orderBy    排序字段
     * @param isDesc     是否倒序排序  Y 倒序排序
     * @return List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getList(@Param("year") String year,
                                         @Param("areaIds") List<String> areaIds,
                                         @Param("statusList") List<String> statusList,
                                         @Param("orderBy") String orderBy,
                                         @Param("isDesc") String isDesc);


    /**
     * 查询县级市场主体规模化利用填报列表
     * 县级无法选择其他县 只能查看当前县
     *
     * @param year       年
     * @param regionYear 区划年度
     * @param areaId     区划ID
     * @param orderBy    排序字段
     * @param isDesc     是否倒序排序  Y 倒序排序
     * @return List<MainBodyUsageSummaryMapper>
     */
    List<MainBodyUsageSummaryVo> getListByCounty2(@Param("year") String year,
                                                  @Param("regionYear") String regionYear,
                                                  @Param("areaId") String areaId, @Param("orderBy") String orderBy, @Param("isDesc") String isDesc);

    /**
     * 查询县级市场主体规模化利用填报列表
     * 县级无法选择其他县 只能查看当前县
     *
     * @param year   年
     * @param areaId 区划ID
     * @return List<MainBodyUsageSummaryMapper>
     */
    List<MainBodyUsageSummaryVo> getListByCounty(@Param("year") String year,
                                                 @Param("areaId") String areaId);

    /**
     * 处理特殊的行政区划  如果是特殊的行政区划  直接查询县
     *
     * @param year   年度
     * @param areaId 特殊区划的ID
     * @return List<MainBodyUsageSummaryMapper>
     */
    List<MainBodyUsageSummaryVo> getListByVerb(@Param("year") String year,
                                               @Param("areaId") String areaId);

    /**
     * 根据市级ID查询当前市的各指标的汇总 个体工商户个数按照地址来做统计
     *
     * @param year   年份
     * @param areaId 市级ID
     * @return List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByCityCount(@Param("year") String year,
                                                    @Param("areaId") String areaId);


    /**
     * 根据省级ID查询当前省的各个市的各指标的汇总， 个体工商户个数按照地址来做统计
     *
     * @param year   年份
     * @param areaId 省级ID
     * @return List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByProvinceCount(@Param("year") String year,
                                                        @Param("areaId") String areaId);


    /**
     * 查询当前省的各个市的各指标的汇总， 个体工商户个数按照地址来做统计
     *
     * @param year 年份
     * @return List<MainBodyUsageSummaryVo>
     */
    List<MainBodyUsageSummaryVo> getListByChinaCount(@Param("year") String year);


    /**
     * 根据主体ID查询主体的填报信息
     *
     * @param utilizeId 填报主体ID
     * @return List<StrawUtilizeInfoAndDetailInfoVo>
     */
    StrawUtilizeInfoAndDetailInfoVo getStrawUtilizeInfoAndDetailInfo(@Param("utilizeId") String utilizeId);


    /**
     * 获取导出数据
     *
     * @param year       年份
     * @param regionYear 区划年度
     * @param areaIds    当前用户能够查看的区划
     * @return List<MainBodyUsageSummaryExcelVo>
     */
    List<MainBodyUsageSummaryExcelVo> getExportInfo(@Param("year") String year,
                                                    @Param("regionYear") String regionYear,
                                                    @Param("areaIds") List<String> areaIds);


}
