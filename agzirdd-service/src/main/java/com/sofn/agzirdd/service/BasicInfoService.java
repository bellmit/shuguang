package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.BasicInfoExcel;
import com.sofn.agzirdd.model.BasicInfo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 入侵生物监测点基础信息Service
 * @Author: mcc
 * @Date: 2020\3\5 0005
 */
public interface BasicInfoService extends IService<BasicInfo> {

    /**
     * 根据查询条件获取入侵生物监测点基础信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<BasicInfo>
     */
    PageUtils<BasicInfo> getBasicInfoListByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取入侵生物监测点基础信息List(不分页)
     * @param params 查询条件
     * @return List<BasicInfo>
     */
    List<BasicInfo> getBasicInfoListByQuery(Map<String,Object> params);


    /**
     * 获取满足条件的导出数据
     * @param params 查询条件
     * @return List<BasicInfoExcel>
     */
    List<BasicInfoExcel> getBasicInfoLisToExport(Map<String,Object> params);

    /**
     * 获取指定id的入侵生物监测点基础信息
     * @param id id
     * @return BasicInfo
     */
    BasicInfo getBasicInfoById(String id);

    /**
     * 修改监测点基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 新增入侵生物监测点基础信息
     * @param basicInfo basicInfo
     */
    void addBasicInfo(BasicInfo basicInfo);

    /**
     * 修改入侵生物监测点基础信息
     * @param basicInfo basicInfo
     */
    void updateBasicInfo(BasicInfo basicInfo);

    /**
     * 删除指定id的入侵生物监测点基础信息
     * @param id id
     */
    void removeBasicInfo(String id);

}
