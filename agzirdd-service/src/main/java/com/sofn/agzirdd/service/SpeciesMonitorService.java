package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.SpeciesMonitorExcel;
import com.sofn.agzirdd.model.SpeciesMonitor;
import com.sofn.agzirdd.vo.AuditDataVo;
import com.sofn.agzirdd.vo.BackObjVo;
import com.sofn.agzirdd.vo.SpeciesMonitorForm;
import com.sofn.agzirdd.vo.SpeciesMonitorVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-监测基本信息Service
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
public interface SpeciesMonitorService extends IService<SpeciesMonitor> {

    /**
     * 根据查询条件获取物种监测模块-监测基本信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<SpeciesMonitor>
     */
    PageUtils<SpeciesMonitor> getSpeciesMonitorByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取物种监测模块-监测基本信息List(不分页)
     * @param params 查询条件

     * @return PageUtils<SpeciesMonitor>
     */
    List<SpeciesMonitor> getSpeciesMonitorListByParam(Map<String,Object> params);


    /**
     * 根据查询条件获取物种监测模块-监测基本信息FromList(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<SpeciesMonitorForm>
     */
    PageUtils<SpeciesMonitorForm> getSpeciesMonitorForm(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 获取物种监测模块-监测表单信息(不分页)
     * @param params 查询条件
     * @return PageUtils<SpeciesMonitorForm>
     */
    List<SpeciesMonitorForm> getSpeciesMonitorList(Map<String,Object> params);

    /**
     * 获取指定id的物种监测模块-监测基本信息
     * @param id id
     * @return SpeciesMonitor
     */
    SpeciesMonitor getSpeciesMonitorById(String id);

    /**
     * 获取指定id的物种监测模块-监测相关所有信息
     * @param id id
     * @return SpeciesMonitorVo
     */
    SpeciesMonitorVo getSpeciesMonitorAllById(String id);

    /**
     * 获取需要导出的监测信息详情
     * @param id id
     * @return SpeciesMonitorExcel
     */
    SpeciesMonitorExcel getSpeciesMonitorExcel(String id);

    /**
     * 修改物种监测模块-监测相关信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 新增入物种监测模块-监测相关信息
     * @param speciesMonitorVo speciesMonitorVo
     */
    void addSpeciesMonitor(SpeciesMonitorVo speciesMonitorVo);

    /**
     * 修改物种监测模块-监测相关信息
     * @param speciesMonitorVo speciesMonitorVo
     */
    void updateSpeciesMonitor(SpeciesMonitorVo speciesMonitorVo);

    /**
     * 删除指定id的物种监测模块-监测相关信息
     * @param id id
     */
    void removeSpeciesMonitor(String id);


    /**
     * 更新审核
     */
    void updateAudit(AuditDataVo vo);

}
