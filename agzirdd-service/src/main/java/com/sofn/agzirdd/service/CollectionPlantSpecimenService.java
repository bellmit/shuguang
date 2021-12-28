package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.CollectionPlantSpecimen;
import com.sofn.agzirdd.vo.CollectionPlantSpecimenVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-植物标本采集
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
public interface CollectionPlantSpecimenService extends IService<CollectionPlantSpecimen> {


    /**
     * 获取满足条件的植物标本内容数据
     * @param params params
     * @return List
     */
    List<CollectionPlantSpecimen> getCollectionPlantSpecimenByQuery(Map<String,Object> params);

    /**
     * 获取指定specimenCollectionId物种采集模块-植物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return CollectionAnimalSpecimen
     */
    CollectionPlantSpecimen getCollectionPlantSpecimenBySpecimenCollectionId(String specimenCollectionId);

    /**
     * 获取指定specimenCollectionId物种采集模块-植物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return CollectionPlantSpecimenVo
     */
    CollectionPlantSpecimenVo getCollectionPlantSpecimenVo(String specimenCollectionId);


    /**
     * 新增物种采集模块-植物标本采集
     * @param collectionPlantSpecimen collectionPlantSpecimen
     */
    void addCollectionPlantSpecimen(CollectionPlantSpecimen collectionPlantSpecimen);

    /**
     * 修改物种采集模块-植物标本采集
     * @param collectionPlantSpecimen collectionPlantSpecimen
     */
    void updateCollectionPlantSpecimen(CollectionPlantSpecimen collectionPlantSpecimen);

    /**
     * 删除指定specimenCollectionId物种采集模块-植物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean removeCollectionPlantSpecimen(String specimenCollectionId);
}
