package com.sofn.ducss.mapper;

import com.sofn.ducss.model.wordmodel.ReportUtilizeInfo;
import com.sofn.ducss.model.wordmodel.ReportWordProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2021/1/4 14:26
 */
public interface ReportCollectionResourceValueMapper {

    /**
     * 根据省份分组获取可收集量数据
     * @param year  年度
     * @param regionYear  区划年度
     * @return  List<ReportWordProduct>
     */
    List<ReportWordProduct> getInfoGroupByProvince(@Param("year") String year,
                                                           @Param("regionYear") String regionYear);


    /**
     * 根据县分组获取可收集量数据
     * @param year   年度
     * @param regionYear  区划年度
     * @return    List<ReportWordProduct>
     */
    List<ReportWordProduct>  getInfoGroupByArea(@Param("year") String year,
                                                @Param("regionYear") String regionYear);


    /**
     * 根据县分组获取可收集量数据
     * @param year   年度
     * @param regionYear  区划年度
     * @return    List<ReportWordProduct>
     */
    List<ReportWordProduct>  getInfoGroupByStraw(@Param("year") String year,
                                                @Param("regionYear") String regionYear);


    /**
     * 获取综合利用量
     * @param year  年度
     * @param regionYear  区划年度
     * @return    List<Map<String,Object>>
     */
    Map<String,Object> getUtilzeInfo(@Param("year") String year,
                                           @Param("regionYear") String regionYear);


    /**
     * 分省获取秸秆利用率
     * @param year  年度
     * @param regionYear   区划年度
     * @return    List<ReportUtilizeInfo>
     */
    List<ReportUtilizeInfo> getUtilizeRateInfoGroupProvince(@Param("year") String year,
                                                            @Param("regionYear") String regionYear);


    /**
     * 分县获取秸秆利用率
     * @param year  年度
     * @param regionYear   区划年度
     * @return    List<ReportUtilizeInfo>
     */
    List<ReportUtilizeInfo> getUtilizeRateInfoGroupAreaId(@Param("year") String year,
                                                            @Param("regionYear") String regionYear);


    /**
     * 分县获取秸秆利用率
     * @param year  年度
     * @param regionYear   区划年度
     * @return    List<ReportUtilizeInfo>
     */
    List<ReportUtilizeInfo> getUtilizeRateInfoGroupStraw(@Param("year") String year,
                                                          @Param("regionYear") String regionYear);


    /**
     * 获取全国的离田信息
     * @param year      年度
     * @param regionYear  区划年度
     * @return    List<ReportUtilizeInfo>
     */
    Map<String,Object> getReturnInfoGroupByChina(@Param("year") String year,
                                                       @Param("regionYear") String regionYear);

    /**
     * 获取还田信息根据省分组
     * @param year  年度
     * @param regionYear  区划年度
     * @return    List<ReportUtilizeInfo>
     */
    List<ReportUtilizeInfo> getReturnInfoGroupByProvince(@Param("year") String year,
                                                         @Param("regionYear") String regionYear);


    /**
     * 获取还田信息根据省分组
     * @param year  年度
     * @param regionYear  区划年度
     * @return    List<ReportUtilizeInfo>
     */
    List<ReportUtilizeInfo> getReturnInfoGroupByStraw(@Param("year") String year,
                                                         @Param("regionYear") String regionYear);

    /**
     * 获取五料化信息
     * @param year  年度
     * @param regionYear    区划年度
     * @return   Map<String, BigDecimal>
     */
    Map<String, BigDecimal> getFiveMaterials(@Param("year") String year,
                                             @Param("regionYear") String regionYear);



}
