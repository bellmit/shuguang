package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.CollectionAnimalSpecimen;
import com.sofn.agzirdd.vo.CollectionAnimalSpecimenVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-动物标本采集
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
public interface CollectionAnimalSpecimenService extends IService<CollectionAnimalSpecimen> {

    /**
     * 获取满足条件的动物标本内容数据
     * @param params params
     * @return List
     */
    List<CollectionAnimalSpecimen> getCollectionAnimalSpecimenByQuery(Map<String,Object> params);

    /**
     * 获取指定specimenCollectionId物种采集模块-动物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return CollectionAnimalSpecimen
     */
    CollectionAnimalSpecimen getCollectionAnimalSpecimenBySpecimenCollectionId(String specimenCollectionId);

    /**
     * 获取指定specimenCollectionId物种采集模块-动物标本采集Vo
     * @param specimenCollectionId specimenCollectionId
     * @return CollectionAnimalSpecimenVo
     */
    CollectionAnimalSpecimenVo getCollectionAnimalSpecimenVo(String specimenCollectionId);


    /**
     * 新增物种采集模块-动物标本采集
     * @param collectionAnimalSpecimen collectionAnimalSpecimen
     */
    void addCollectionAnimalSpecimen(CollectionAnimalSpecimen collectionAnimalSpecimen);

    /**
     * 修改物种采集模块-动物标本采集
     * @param collectionAnimalSpecimen collectionAnimalSpecimen
     */
    void updateCollectionAnimalSpecimen(CollectionAnimalSpecimen collectionAnimalSpecimen);

    /**
     * 删除指定specimenCollectionId物种采集模块-动物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean removeCollectionAnimalSpecimen(String specimenCollectionId);
}
