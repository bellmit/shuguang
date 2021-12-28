package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.CollectionMicrobeSpecimen;
import com.sofn.agzirdd.vo.CollectionMicrobeSpecimenVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-微生物标本
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
public interface CollectionMicrobeSpecimenService extends IService<CollectionMicrobeSpecimen> {

    /**
     * 获取满足条件的微生物标本内容数据
     * @param params params
     * @return List
     */
    List<CollectionMicrobeSpecimen> getCollectionMicrobeSpecimenByQuery(Map<String,Object> params);

    /**
     * 获取指定specimenCollectionId物种采集模块-微生物标本
     * @param specimenCollectionId specimenCollectionId
     * @return CollectionMicrobeSpecimen
     */
    CollectionMicrobeSpecimen getCollectionMicrobeSpecimenBySpecimenCollectionId(String specimenCollectionId);

    /**
     * 获取指定specimenCollectionId物种采集模块-微生物标本Vo
     * @param specimenCollectionId specimenCollectionId
     * @return CollectionMicrobeSpecimenVo
     */
    CollectionMicrobeSpecimenVo getCollectionMicrobeSpecimenVo(String specimenCollectionId);


    /**
     * 新增物种采集模块-微生物标本
     * @param collectionMicrobeSpecimen collectionMicrobeSpecimen
     */
    void addCollectionMicrobeSpecimen(CollectionMicrobeSpecimen collectionMicrobeSpecimen);

    /**
     * 修改物种采集模块-微生物标本
     * @param collectionMicrobeSpecimen collectionMicrobeSpecimen
     */
    void updateCollectionMicrobeSpecimen(CollectionMicrobeSpecimen collectionMicrobeSpecimen);

    /**
     * 删除指定specimenCollectionId物种采集模块-微生物标本
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean removeCollectionMicrobeSpecimen(String specimenCollectionId);
}
