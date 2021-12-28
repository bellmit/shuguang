package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.SysRegion;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 行政区划Mapper
 * Created by heyongjie on 2019/5/29 11:14
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    /**
     * 按条件查询结果
     *
     * @param params 查询参数
     * @return List<SysRegion>
     */
    List<SysRegion> getSysRegionByContion(Map<String, Object> params);

    /**
     * 批量删除
     *
     * @param ids          待删除ID
     * @param updateUserId 操作用户
     * @param updateTime   操作时间
     */
    void batchDelete(@Param("ids") List<String> ids, @Param("updateUserId") String updateUserId, @Param("updateTime") Date updateTime);

    List<Map<String, Object>> getProvinceList();

    List<Map<String, Object>> getCityList();

    List<Map<String, Object>> getCountryList();

    /**
     * 根据parentIdStrs查询区划信息
     *
     * @param parentIdStrs 100000/51100.。。。
     * @return List<SysRegion>
     */
//    List<SysRegion> getInfoByParentIdStrs(@Param("parentIdStrs") List<String> parentIdStrs);
    List<SysRegion> getInfoByParentIdStrs(@Param("parentIdStrs") List<String> parentIdStrs, @Param("versionCode") String versionCode);


    /**
     * 根据IDS更新子节点
     *
     * @param ids               待更新的子节点ID
     * @param newParentIdPrefix 新的parentIds前缀
     * @param oldParentIdPrefix 旧的parentIds前缀
     */
    void updateParentIdsByIds(@Param("ids") List<String> ids, @Param("newParentIdPrefix") String newParentIdPrefix,
                              @Param("oldParentIdPrefix") String oldParentIdPrefix);


    /**
     * 根据IDS更新子节点
     *
     * @param ids                 待更新的子节点ID
     * @param newParentNamePrefix 新的parentIds前缀
     * @param oldParentNamePrefix 旧的parentIds前缀
     */
    void updateParentNamesByIds(@Param("ids") List<String> ids, @Param("newParentNamePrefix") String newParentNamePrefix,
                                @Param("oldParentNamePrefix") String oldParentNamePrefix);

    /**
     * 获取版本年份列表
     */
    List<Integer> getVersionYearList();

    /**
     * 获取最大版本年份
     */
    Integer getMaxVersionYear(@Param("versionYear") Integer versionYear);

    /**
     * 获取年份下最大版本代码
     *
     * @param versionYear
     * @return
     */
    Long getMaxVersionCodeByYear(@Param("versionYear") Integer versionYear);

    List<SysRegion> selectFList(Map<String, Object> paramsF);

    /**
     * 根据regioncode查询节点信息
     *
     * @param parentIdList
     * @return
     */
    List<SysRegion> selectByRegioncode(@Param("parentIdList") List<String> parentIdList, @Param("versionCode") String versioncode);

    /**
     * 获取ParentId
     *
     * @param parentId 父节点
     * @return 父节点信息
     */
    SysRegion getParentRegion(@Param("parentId") String parentId, @Param("versionCode") String versioncode);

    /**
     * 查询18最小的versioncode
     *
     * @param versionYear
     * @return
     */
    BigDecimal selectMinVersionCodeByyear(String versionYear);

    /**
     * 根据ParentIds查询子节点
     * @param parentIds   /100000/110000...
     * @param versioncode    最大版本号
     * @return    List<SysRegion>
     */
    List<SysRegion> getSysRegionByParentIds(@Param("parentIds") String parentIds, @Param("versionCode") Long versioncode);

    /**
     * 查询当前年度版本号最大的那个  包括已经删除的
     * @param regionCode   区划代码
     * @param year  年度
     * @return  区划信息
     */
    SysRegion getSysRegionByRegionCode(@Param("regionCode") String regionCode, @Param("year") String year);


    /**
     * 支撑区域代码批量查询
     * @param params
     * @return
     */
    List<SysRegion> getSysRegionByContionBy(Map<String, Object> params);

    /***
     * 是否存在
     * @param regionName
     * @return
     */
    Integer isExsitsRegion(@Param("regionName")String regionName,@Param("parentId")String parentId,@Param("year")Integer year, @Param("regionCode")String regionCode);

}
