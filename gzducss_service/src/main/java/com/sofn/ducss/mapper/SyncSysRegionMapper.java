package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DucssRegionCopySys;
import com.sofn.ducss.model.SysRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SyncSysRegionMapper extends BaseMapper<DucssRegionCopySys> {

    /**
     * 获取最大的年度
     *
     * @return 年度
     */
    // Integer getMaxYear();


    /**
     * 获取年度数据是否存在
     *
     * @param year 年度
     * @return Integer
     */
    // Integer getYearDataNumber(@Param("year") String year);

    /**
     * 获取下级的区划代码
     *
     * @param parentId 父级ID
     * @param year     年度
     * @return 下级区划的ID
     */
    // List<String> getChildrenRegion(@Param("parentId") String parentId, @Param("year") String year);

    /**
     * 获取某个区划的级别
     *
     * @param areaId 区划ID
     * @param year   年度
     * @return String
     */
    // String getLevel(@Param("areaId") String areaId, @Param("year") String year);

    /**
     * 高州秸秆项目，暂时就针对高州市下面所有的乡镇，所以查询所有的乡镇就查父id为高州的数据
     *
     * @param level      级别 参考RegionLevel
     * @param regionYear 区划年度
     * @return List<String>
     */
    // List<DucssRegionCopySys> getAreaIdByLevel(@Param("level") String level, @Param("regionYear") String regionYear);

    List<SysRegion> getAreaIdByParentId(@Param("parentId") String parentId);

    /**
     * 获取名字
     *
     * @param areaId 区划ID
     * @return 区划名字
     */
    // String getName(@Param("areaId") String areaId, @Param("year") String year);

    /**
     * 查找通过审核的下一级
     *
     * @param countyAreaIds 父ID
     * @param year          年度
     * @return List<String>
     */
    List<Map<String, String>> getAreaId(@Param("year") String year, @Param("countyAreaIds") List<String> countyAreaIds);

    /**
     * 查找通过审核的下一级
     *
     * @param countyAreaIds 县级id
     * @param year          年度
     * @param regionYear    区划年度
     * @return List<String>
     */
    List<Map<String, String>> getAreaIdByState(@Param("year") String year, @Param("regionYear") String regionYear,
                                               @Param("countyAreaIds") List<String> countyAreaIds, @Param("status") List<String> status);

    /**
     * 获取区划名字
     *
     * @param areaIds    区划ID
     * @param regionYear 年度
     * @return Map<String, String>
     */
    //List<Map<String, String>> getNameMap(@Param("areaIds") List<String> areaIds,@Param("regionYear") String regionYear);

}
