package com.sofn.ducss.mapper;


import com.sofn.ducss.model.CheckInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 审核相关Mapper
 */
public interface CheckInfoMapper {

    /**
     * ======================================拉取审核时的统计信息    部省市
     * 获取某个市下所有县的各个信息
     *
     * @param year    数据年度
     * @param childId 市ID
     * @return List<CheckInfo>
     */
    List<CheckInfo> getCheckInfoCity(@Param("year") String year, @Param("childId") String childId);

    /**
     * 获取某个省的各个市的统计情况， 只统计市级审核通过了的县
     *
     * @param year       数据年度
     * @param provinceId 省ID
     * @return List<CheckInfo>
     */
    List<CheckInfo> getCheckInfoProvince(@Param("year") String year,
                                         @Param("provinceId") String provinceId);


    /**
     * 获取全国的统计数据
     *
     * @param year 年份
     * @return List<CheckInfo>
     */
    List<CheckInfo> getCheckInfoChina(@Param("year") String year);


    /**
     * 根据不同级别获取数据
     *
     * @param year       年份
     * @param areaId     区划ID
     * @param regionYear 区域年度
     * @param status     状态
     * @return List<CheckInfo>
     */
    List<CheckInfo> getCheckInfoByLevel(@Param("year") String year,
                                        @Param("areaId") String areaId,
                                        @Param("regionYear") String regionYear,
                                        @Param("status") String status);

    /**
     * 根据不同级别获取数据
     *
     * @param year    年份
     * @param areaIds 区划IDs
     * @param status  状态
     * @return List<CheckInfo>
     */
    List<CheckInfo> getCheckInfoByLevelV2(@Param("year") String year,
                                          @Param("areaIds") List<String> areaIds,
                                          @Param("status") String status);

    /**
     * ============================================获取抽样分散户数  部省市
     * 获取某个市的抽样分散户数  按照县分组
     *
     * @param year   年份
     * @param cityId 某个市的ID
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getScatteredHouseNumByCityId(@Param("year") String year,
                                                           @Param("cityId") String cityId,
                                                           @Param("regionYear") String regionYear);

    /**
     * 获取某个省的抽样分散户数  按照市分组
     *
     * @param year       年份
     * @param provinceId 某个省的ID
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getScatteredHouseNumByProvinceId(@Param("year") String year,
                                                               @Param("provinceId") String provinceId,
                                                               @Param("regionYear") String regionYear);


    /**
     * 获取全国的抽样分散户数  按照市分组
     *
     * @param year       年份
     * @param provinceId 某个市的ID
     * @param regionYear 区划年度
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getScatteredHouseNumByChina(@Param("year") String year,
                                                          @Param("provinceId") String provinceId,
                                                          @Param("regionYear") String regionYear);


    /**
     * 获取市场主体的数量
     *
     * @param columnName 列名称
     * @param year       年度
     * @param areaIds    父节点ID
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getScatteredHouseNum(@Param("columnName") String columnName, @Param("year") String year,
                                                   @Param("areaIds") List<String> areaIds);


    /**
     * ============================================获取市场主体规模化数量  部省市
     * 获取某个市的抽样分散户数  按照县分组   如果是直辖市可能会跳过，所以这里city传入集合
     *
     * @param year       年份
     * @param cityIds    某个市的ID
     * @param regionYear 区划年度
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getMainBodyNumByCityId(@Param("year") String year,
                                                     @Param("cityIds") List<String> cityIds,
                                                     @Param("regionYear") String regionYear);

    /**
     * 获取某个市的抽样分散户数  按照市分组
     *
     * @param year       年份
     * @param provinceId 某个省的ID
     * @param regionYear 区划年度
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getMainBodyNumByProvinceId(@Param("year") String year,
                                                         @Param("provinceId") String provinceId,
                                                         @Param("regionYear") String regionYear);


    /**
     * 获取某个市的抽样分散户数  按照市分组
     *
     * @param year       年份
     * @param provinceId 某个省的ID
     * @param regionYear 区划年度
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getMainBodyNumByChina(@Param("year") String year,
                                                    @Param("provinceId") String provinceId,
                                                    @Param("regionYear") String regionYear);

    /**
     * 获取市场主体的数量
     *
     * @param columnName 列名称
     * @param year       年度
     * @param areaIds    父节点ID
     * @return List<Map < String, Integer>>
     */
    List<Map<String, Object>> getMainBody(@Param("columnName") String columnName, @Param("year") String year, @Param("areaIds") List<String> areaIds);

    /**
     * 获取字段的顺序
     *
     * @return 各个农作物的顺序
     */
    List<String> getStrawTypeList();


}
