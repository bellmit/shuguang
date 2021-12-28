package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DisperseUtilize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DisperseUtilizeMapper extends BaseMapper<DisperseUtilize> {

    List<DisperseUtilize> getDisperseUtilizeByPage(Map<String, Object> params);

    List<DisperseUtilize> getDisperseUtilize(Map<String, Object> params);

    DisperseUtilize selectDisperseUtilizeById(@Param(value = "id") String id);

    String selectFarmerNo(@org.apache.ibatis.annotations.Param("year") String year, @org.apache.ibatis.annotations.Param("areaId") String areaId, @org.apache.ibatis.annotations.Param("type") String type);

    Integer insertDisperseUtilize(DisperseUtilize disperseUtilize);

    Integer updateDisperseUtilize(DisperseUtilize disperseUtilize);

    Integer deleteById(String id);

    String selectDisperseUtilizeIdByYear(Map<String, Object> params);

    Integer getDisperseUtilizeTotalCount(Map<String, Object> params);

    /**
     * 根据年份,区域集合获取农户分散户数量
     *
     * @param   map 参数map
     *
     * @return  Integer 布尔类型
     */
    Integer getDisperseUtilizeByIdList(Map<String, Object> map);

    /**
     * 根据年份,区域集合获取农户分散户数量然后根据区域分组
     *
     * @param   map 参数map
     *
     * @return  Integer 布尔类型
     */
    List<Integer> getDisperseUtilizeCountGroupByArea(Map<String, Object> map);

    List<String> getFarmerNames(String countyId);

    /***
     *  新增判重
     * @param year  年度
     * @param farmerName  农户姓名
     * @param farmerPhone  电话
     * @param areaId 区域编码
     * @return
     */
    Integer isDisperseExists(@Param("year") String year, @Param("farmerName")String farmerName, @Param("farmerPhone")String farmerPhone, @Param("areaId")String areaId);

    /***
     * 批量新增
     * @param list  集合
     * @return
     */
    Integer insertBatch(@Param("list") List<DisperseUtilize> list);

    /***
     *  批量更新 农户编码
     * @param list
     * @return
     */
    Integer  updateBatch(@Param("list") List<DisperseUtilize> list);
}