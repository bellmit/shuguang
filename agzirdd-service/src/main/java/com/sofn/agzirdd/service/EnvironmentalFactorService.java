package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.EnvironmentalFactorExcel;
import com.sofn.agzirdd.model.EnvironmentalFactor;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface EnvironmentalFactorService extends IService<EnvironmentalFactor> {

    /**
     * 根据查询条件获取入侵生物监测点环境因子信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<EnvironmentalFactor>
     */
    PageUtils<EnvironmentalFactor> getEnvironmentalFactorListByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取入侵生物监测点环境因子信息List(不分页)
     * @param params 查询条件
     * @return List<EnvironmentalFactor>
     */
    List<EnvironmentalFactor> getEnvironmentalFactorListByQuery(Map<String,Object> params);


    /**
     * 获取满足条件的导出数据
     * @param params 查询条件
     * @return List<EnvironmentalFactorExcel>
     */
    List<EnvironmentalFactorExcel> getEnvironmentalFactorListToExport(Map<String,Object> params);

    /**
     * 获取指定id的入侵生物监测点环境因子信息
     * @param id id
     * @return BasicInfo
     */
    EnvironmentalFactor getEnvironmentalFactorById(String id);

    /**
     * 修改监测点环境因子信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 新增入侵生物监测点环境因子信息
     * @param environmentalFactor environmentalFactor
     */
    void addEnvironmentalFactor(EnvironmentalFactor environmentalFactor);

    /**
     * 修改入侵生物监测点环境因子信息
     * @param environmentalFactor environmentalFactor
     */
    void updateEnvironmentalFactor(EnvironmentalFactor environmentalFactor);

    /**
     * 删除指定id的入侵生物监测点环境因子信息
     * @param id id
     */
    void removeEnvironmentalFactor(String id);

}
