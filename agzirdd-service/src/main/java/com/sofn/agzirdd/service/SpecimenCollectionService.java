package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.CollectionAnimalSpecimenExcel;
import com.sofn.agzirdd.excelmodel.CollectionMicrobeSpecimenExcel;
import com.sofn.agzirdd.excelmodel.CollectionPlantSpecimenExcel;
import com.sofn.agzirdd.model.SpecimenCollection;
import com.sofn.agzirdd.vo.SpecimenCollectionForm;
import com.sofn.agzirdd.vo.SpecimenCollectionVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-标本采集基本信息
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
public interface SpecimenCollectionService extends IService<SpecimenCollection> {
    /**
     * 根据查询条件获取物种采集模块-标本采集基本信息List(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<SpecimenCollection>
     */
    PageUtils<SpecimenCollection> getSpecimenCollectionByPage(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取物种采集模块-标本采集基本信息List(不分页)
     * @param params 查询条件

     * @return PageUtils<SpecimenCollection>
     */
    List<SpecimenCollection> getSpecimenCollectionListByParam(Map<String,Object> params);


    /**
     * 根据查询条件获取物种采集模块-标本采集基本信息FormList(分页)
     * @param params 查询条件
     * @param pageNo 页码索引
     * @param pageSize 页码条数
     * @return PageUtils<SpecimenCollectionForm>
     */
    PageUtils<SpecimenCollectionForm> getSpecimenCollectionForm(Map<String,Object> params, int pageNo, int pageSize);

    /**
     * 根据查询条件获取物种采集模块-标本采集基本信息FormList(分页)
     * @param params 查询条件

     * @return PageUtils<SpecimenCollectionForm>
     */
    List<SpecimenCollectionForm> getSpecimenCollectionList(Map<String,Object> params);


    /**
     * 获取指定id的物种采集模块-标本采集基本信息
     * @param id id
     * @return SpecimenCollection
     */
    SpecimenCollection getSpecimenCollectionById(String id);

    /**
     * 获取指定id的物种监测模块-监测相关所有信息
     * @param id id
     * @return SpecimenCollectionVo
     */
    SpecimenCollectionVo getSpecimenCollectionAllById(String id);

    /**
     * 获取指定id的物种采集-植物采集数据
     * @param id id
     * @return CollectionPlantSpecimenExcel
     */
    CollectionPlantSpecimenExcel getCollectionPlantSpecimenExcel(String id);

    /**
     * 获取指定id的物种采集-动物采集数据
     * @param id id
     * @return CollectionPlantSpecimenExcel
     */
    CollectionAnimalSpecimenExcel getCollectionAnimalSpecimenExcel(String id);

    /**
     * 获取指定id的物种采集-微生物采集数据
     * @param id id
     * @return CollectionMicrobeSpecimenExcel
     */
    CollectionMicrobeSpecimenExcel getCollectionMicrobeSpecimenExcel(String id);

    /**
     * 修改物种监测模块-监测相关信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

    /**
     * 新增入物种监测模块-监测相关信息
     * @param specimenCollectionVo specimenCollectionVo
     */
    void addSpecimenCollection(SpecimenCollectionVo specimenCollectionVo);

    /**
     * 修改物种监测模块-监测相关信息
     * @param specimenCollectionVo specimenCollectionVo
     */
    void updateSpecimenCollection(SpecimenCollectionVo specimenCollectionVo);

    /**
     * 删除指定id的物种监测模块-监测相关信息
     * @param id id
     */
    void removeSpecimenCollection(String id);

}
