package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.TbComp;
import com.sofn.fdpi.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 企业
 * wuXY
 * 2019-12-30 11:23:36
 */
public interface TbCompService extends IService<TbComp> {
    PageUtils<TbCompVo> listByPage(Map<String,Object> map,int pageNo, int pageSize);

    /**
     * @Description: 企业查询-列表
     * @Author: wuXY
     * @Date: 2019-12-30 11:31:41
     * @param map 查询参数
     * @return  List<TbCompVo> 对象
     */
    PageUtils<TbCompVo> listForCompAndYearInspectByPage(Map<String,Object> map,int pageNo,int pageSize);

    /**
     * 根据获取企业信息
     * wuXY
     * 2019-12-30 11:31:41
     * @param id 企业id编号
     * @return TbComp对象
     */
    TbCompVo getCombById(String id);

    /**
     * 根据企业id编号修改企业信息
     * wuXY
     * 2019-12-30 11:31:41
     * @param comp 企业对象
     * @return 操作成功
     */
    String updateComById(TbComp comp);

    /**
     * 根据企业id修改企业状态
     * @param map
     * @return
     */
    String updateStatusById(Map<String,Object> map);

    /**
     * 获取当前企业的直属企业信息（id和level）
     * wuXY
     * 2020-1-9 17:23:24
     * @param compId 企业id
     * @return DepartmentLevelVo 直属企业信息
     */
    DepartmentLevelVo getDeptLevel(String compId);

    /**
     * 直属机构修改企业的行政区划
     * wuXY
     * 2020-1-15 15:33:57
     * @param tbCompRegionForm 修改的对象
     * @return 1：成功 or 其他失败
     */
    String updateCompRegionById(TbCompRegionForm tbCompRegionForm);

    /**
     * 获取所有企业名称和账号；企业名称和账号是1:1，可以写成一个，后期是1：n，则需要分开
     * @return List<CompAndUserVo>
     */
    List<CompAndUserVo> listForAllCompAndUser();

    /**
     * 加载企业和账号数据到缓存中，注册验重使用
     */
    void loadCompAndUserDataToCache();

    int getCompCount(String status);

    /**
     * 获取今天最大的申请号
     */
    String getTodayMaxApplyNum(String todayStr);

    String getMaxCompCode();

    boolean validCompIsHasRegisterAndSave(String compName);

    TbComp getCombByCode(String compCode);

    Map<String, Integer> delete(String id);

}
