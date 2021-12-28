package com.sofn.ducss.service;

import com.sofn.ducss.model.DucssRegionCopySys;

import java.util.List;
import java.util.Map;

public interface SyncSysRegionService {

    /**
     * 同步支撑平台行政区划数据
     *
     * @param year 年度
     */
    // void syncSysRegion(String year);

    /**
     * 根据年度获取年度，  如果传入的年度没有区划数据，那么就返回最新的年度
     *
     * @param year 年度
     */
    // String getYearByYear(String year);

    /**
     * 获取下级的区划代码
     *
     * @param parentId 父级ID
     * @param year     年度
     * @return 下级区划的ID
     */
    // List<String> getChildrenRegion(String parentId, String year);

    /**
     * 获取某个区划的级别
     *
     * @param areaId 区划ID
     * @param year   年度
     * @return String
     */
    // String getLevel(String areaId, String year);


    /**
     * 获取名字
     *
     * @param areaId 区划ID
     * @return 区划名字
     */
    // String getName(String areaId, String year);


    /**
     * 获取通过审核的所有县级
     *
     * @param parentIds  父ID
     * @param year       年度
     * @param regionYear 区划年度
     * @return List<String>
     */
    // List<String> getAreaId(String year, String regionYear, List<String> parentIds);


    /**
     * 查找当前区划下已经通过审核所有区划
     *
     * @param currentAreaIds 需要查询的区划codes
     * @param year           年度
     * @param regionYear     区划年度
     * @param isAll          是否全部
     * @return List<String>
     */
    List<String> getAreaId(String year, String regionYear, List<String> currentAreaIds, Boolean isAll);

    /**
     * 获取通过审核的所有县级
     *
     * @param currentAreaIds 需要查询的区划codes
     * @param year           年度
     * @param status         审核状态
     * @return List<String>
     */
    List<String> getAreaIdByStatus(String year, List<String> currentAreaIds, List<String> status);

    /**
     * 判断当前用户能否查看传入的区划
     *
     * @param areaIds 检查的区划ID
     * @param year    年度
     */
    void checkUserCanShow(List<String> areaIds, String year);

    List<String> checkUserCanShow2(List<String> areaIds, String year);

    /**
     * 获取行政区划的ID ， 根据级别获取
     *
     * @param level      级别 参考RegionLevel
     * @param regionYear 区划年度
     * @return List<String>
     */
    // List<DucssRegionCopySys> getAreaIdByLevel(String level, String regionYear);

    /**
     * 获取区划名字
     *
     * @param areaIds    区划ID
     * @param regionYear 年度
     * @return Map<String, String>
     */
    // Map<String, String> getNameMap(List<String> areaIds, String regionYear);

}
