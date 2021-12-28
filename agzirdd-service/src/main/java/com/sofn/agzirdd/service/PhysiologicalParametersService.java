package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.PhysiologicalParametersExcel;
import com.sofn.agzirdd.model.PhysiologicalParameters;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface PhysiologicalParametersService extends IService<PhysiologicalParameters> {


    /**
     * 根据查询条件获取入侵生物监测点基础信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<PhysiologicalParameters>
     */
    PageUtils<PhysiologicalParameters> getPhysiologicalParametersListByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取入侵生物监测点基础信息List(不分页)
     * @param params 查询条件
     * @return List<PhysiologicalParameters>
     */
    List<PhysiologicalParameters> getPhysiologicalParametersListByQuery(Map<String,Object> params);


    /**
     * 获取满足条件的导出数据
     * @param params 查询条件
     * @return List<PhysiologicalParameters>
     */
    List<PhysiologicalParametersExcel> getPhysiologicalParametersListToExport(Map<String,Object> params);

    /**
     * 获取指定id的入侵生物监测点基础信息
     * @param id id
     * @return BasicInfo
     */
    PhysiologicalParameters getPhysiologicalParametersById(String id);

    /**
     * 修改监测点基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 新增入侵生物监测点基础信息
     * @param physiologicalParameters physiologicalParameters
     */
    void addPhysiologicalParameters(PhysiologicalParameters physiologicalParameters);

    /**
     * 修改入侵生物监测点基础信息
     * @param physiologicalParameters physiologicalParameters
     */
    void updatePhysiologicalParameters(PhysiologicalParameters physiologicalParameters);

    /**
     * 删除指定id的入侵生物监测点基础信息
     * @param id id
     */
    void removePhysiologicalParameters(String id);




}
