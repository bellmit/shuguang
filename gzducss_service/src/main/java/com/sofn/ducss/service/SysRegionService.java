package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.SysRegion;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.SysDictVo;
import com.sofn.ducss.vo.SysRegionForm;
import com.sofn.ducss.vo.SysRegionInfoVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 行政区划代码服务层
 *
 * @author heyongjie
 * @date 2019/5/29 11:15
 */
public interface SysRegionService extends IService<SysRegion> {

    /**
     * 添加行政区划内容
     *
     * @param sysRegion  行政机构内容
     */
    void addSysRegion(SysRegion sysRegion);

    /**
     * 修改行政区划内容
     * @param sysRegion 行政机构内容
     */
    void updateSysRegion(SysRegion sysRegion);

    /**
     * 删除行政区划内容
     * @param id  区划ID
     */
    void deleteSysRegion(String id);

    /**
     * 批量删除
     * @param ids   要删除的IDs
     */
    void batchDeleteSysRegion(List<String> ids);

    /**
     * 按条件分页查询
     * @param params  查询条件
     * @param pageNo  从哪条开始
     * @param pageSize  显示多少条
     * @return  分页信息
     */
    PageUtils getSysRegionByContionPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 按条件分页查询
     * @param params  查询条件
     * @param pageNo  从哪条开始
     * @param pageSize  显示多少条
     * @return  分页信息
     */
    PageUtils getSysRegionByContionsPage(Map<String,Object> params, Integer pageNo, Integer pageSize);

    /**
     * 根据ID获取一个  因为使用的是软删除，使用默认的获取一个会出现错误
     * @param id  要查询的ID
     * @return  ID对应的数据
     */
    SysRegion getOneById(String id);

    /**
     * 根据code获取一个  因为使用的是软删除，使用默认的获取一个会出现错误
     * @return  ID对应的数据
     */
    SysRegion getOneByCode(String code, Integer versionYear);

    /**
     * 获取列表根据父ID查询
     * @param parentId  父ID
     * @param isAuth     是否鉴权
     * @return List<SysRegionForm>
     */
    List<SysRegionForm> getListByRegionCode(String apppId, String parentId, String isAuth, Integer versionYear);

    /**
     * 删除键
     * @param parentId  父ID
     */
    void deleteRedis(List<String> appIds, String parentId);


    /**
     * 根据区域Code获取区域名称
     * @param areaCode  区域Code
     * @return 区域名称
     */
    String getAreaName(String areaCode);

    /**
     * 根据区域类别获取所有的区域
     * @param typeCode areaType
     * @return   List<SysDictVo>
     */
    List<SysDictVo> getRegionAreaList(String typeCode);

    /**
     * 分别获取省市县的机构
     * @return  Map<String, Object>
     */
    Map<String, Object> getProvinceAndCityAndCountryList();

    /**
     * 根据Code获取列表
     * @param regionCodes   机构代码多个使用,号隔开
     * @return  机构名称1, 机构名称2
     */
    String getRegionNames(String regionCodes, Integer versionYear);

    /**
     * 根据区划名称获取区划
     * @param regionName 区划名称
     * @return 区划对象
     */
    SysRegion getRegionByName(String regionName);

  /**
   * 校验行政区划编码和级别是否对应
   * @param regionCode 编码
   * @param level 级别
   */
    boolean checkRegionLevel(String regionCode, String level);

    /**
     * 更新redis中的区划缓存
     */
    void updateRegionRedisCache();

    SysRegionInfoVo getSysRegionInfoById(String id);

    List<String> getFormatIdsOrNames(String joins, String lastStr, String idOrName);

    List<String> getFormatIdsOrNamesByCode(String code, String idOrName);


    /**
     * 按条件查询结果
     * @param params  查询参数
     * @return   List<SysRegion>
     */
    List<SysRegion> getSysRegionByContion(Map<String, Object> params);

    /**
     * 获取版本年份列表
     * @return 版本年份列表
     */
    List<Integer> getVersionYearList();

    /**
     * 获取可供选择的版本年份列表
     * @return 版本年份列表
     */
    List<Integer> getVersionYearListForAvailable();

    /**
     * 获取最大版本年份
     */
    Integer getMaxVersionYear(Integer versionYear);

    /**
     * 根据版本年获取年份下最大版本代码，允许为空
     * @param versionYear 为空获取最新年份
     */
    Long getMaxVersionCodeByYear(Integer versionYear);


    /**
     * 根据codes返回区划名字
     * @param ids   代码
     * @param year  年度
     * @return    Map<String, String>
     */
    Map<String, String> getRegionNameMaps(Set<String> ids, Integer year);



}
