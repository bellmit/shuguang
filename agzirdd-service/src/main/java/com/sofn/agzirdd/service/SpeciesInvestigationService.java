package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.SpeciesInvestigationExcel;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import com.sofn.agzirdd.vo.BatchAuditItemVo;
import com.sofn.agzirdd.vo.SpeciesInvestigationForm;
import com.sofn.agzirdd.vo.SpeciesInvestigationVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-调查基本信息
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
public interface SpeciesInvestigationService extends IService<SpeciesInvestigation> {

    /**
     * 根据查询条件获取物种调查模块-调查基本信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<SpeciesInvestigation>
     */
    PageUtils<SpeciesInvestigation> getSpeciesInvestigationByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取物种调查模块-调查基本信息List(不分页)
     * @param params 查询条件
     * @return PageUtils<SpeciesInvestigation>
     */
    List<SpeciesInvestigation> getSpeciesInvestigationListByParam(Map<String,Object> params);


    /**
     * 根据查询条件获取物种调查模块-调查基本信息FormList(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<SpeciesInvestigationForm>
     */
    PageUtils<SpeciesInvestigationForm> getSpeciesInvestigationForm(Map<String,Object> params, int pageNo,
                                                                    int pageSize);

    /**
     * 获取需要导出的list
     * @param params 查询条件
     * @return List<SpeciesInvestigationForm>
     */
    List<SpeciesInvestigationForm> getSpeciesInvestigationList(Map<String,Object> params);


    /**
     * 获取指定id的物种调查模块-调查基本信息
     * @param id id
     * @return SpeciesInvestigation
     */
    SpeciesInvestigation getSpeciesInvestigationById(String id);

    /**
     * 获取指定id的物种调查模块-调查信息
     * @param id id
     * @return SpeciesInvestigationVo
     */
    SpeciesInvestigationVo getSpeciesInvestigationAllById(String id);

    /**
     * 获取需要导出的调查信息详情
     * @param id id
     * @return SpeciesInvestigationExcel
     */
    SpeciesInvestigationExcel getSpeciesInvestigationExcel(String id);

    /**
     * 修改物种物种调查模块-调查基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 新增入物种物种调查模块-调查信息
     * @param speciesInvestigationVo speciesInvestigationVo
     */
    void addSpeciesInvestigation(SpeciesInvestigationVo speciesInvestigationVo);

    /**
     * 修改物种调查模块-调查信息
     * @param speciesInvestigationVo speciesInvestigationVo
     */
    void updateSpeciesInvestigation(SpeciesInvestigationVo speciesInvestigationVo);

    /**
     * 删除指定id的物种调查模块-调查信息
     * @param id id
     */
    void removeSpeciesInvestigation(String id);

    /**
     * 批量审核物种调查信息-总站
     * @param remark
     * @param items
     */
    void batchAudit(String remark, List<BatchAuditItemVo> items);

}
