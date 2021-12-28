package com.sofn.ducss.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 还田离田情况汇总
 */
@Component
public interface LeavingMapMapper {

    /**
     * 获取还田离田情况
     * @param areaIds   区划IDS
     * @param year   年度
     * @return   List<Map<String,Object>>
     */
    List<Map<String,Object>> getReturnLeave(@Param("areaIds") List<String> areaIds,
                                            @Param("year") String year,
                                            @Param("strawType") String strawType);


    /**
     * 获取农户分散利用比例
     * @param areaIds     区划IDS
     * @param year 年度
     * @param strawType  作物类型
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getDisperseTotalRatio(@Param("areaIds") List<String> areaIds,
                                                   @Param("year") String year,
                                                   @Param("strawType") String strawType);

    /**
     * 获取市场化主体利用比例
     * @param areaIds       区划IDS
     * @param year 年度
     * @param strawType  作物类型
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getMainTotalRatio(@Param("areaIds") List<String> areaIds,
                                               @Param("year") String year,
                                               @Param("strawType") String strawType);


    /**
     * 显示14种作物的还田离田比率
     * 动态拼凑Sql
     * @param areaIds  区划IDS   除了全国传入各省市的 其余级别只能传入一个
     * @param year   年度
     * @param tableName   查询的表名
     * @param value1   除数是哪个
     * @param value2  被除数是哪个字段
     * @return   List<Map<String,Object>>
     */
    List<Map<String,Object>> getLeavingPieChart(@Param("areaIds") List<String> areaIds,
                                                @Param("year") String year,
                                                @Param("tableName") String tableName,
                                                @Param("value1") String value1,
                                                @Param("value2")String value2);

}
