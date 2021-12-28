package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Description: 物种采集模块-标本采集信息Vo
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecimenCollectionVo extends SpecimenCollection {

    @ApiModelProperty(name = "areaName", value = "省市区县地址")
    private String areaName;

    @ApiModelProperty(name = "collectionPlantSpecimenVo", value = "物种采集模块-植物标本采集Vo")
    private CollectionPlantSpecimenVo collectionPlantSpecimenVo;

    @ApiModelProperty(name = "collectionAnimalSpecimenVo", value = "物种采集模块-动物标本采集Vo")
    private CollectionAnimalSpecimenVo collectionAnimalSpecimenVo;

    @ApiModelProperty(name = "collectionMicrobeSpecimenVo", value = "物种采集模块-微生物标本采集Vo")
    private CollectionMicrobeSpecimenVo collectionMicrobeSpecimenVo;

    @ApiModelProperty(name = "collectionExaminaRecordList", value = "物种采集模块-审核记录List")
    private List<CollectionExaminaRecord> collectionExaminaRecordList;

    public SpecimenCollectionVo(){}
    /**
     * 将vo转换为po对象
     */
    public static SpecimenCollection getSpecimenCollection(SpecimenCollectionVo specimenCollectionVo){
        SpecimenCollection specimenCollection = new SpecimenCollection();
        BeanUtils.copyProperties(specimenCollectionVo,specimenCollection);
        return specimenCollection;
    }
    /**
     * po转换为vo
     */
    public static SpecimenCollectionVo getSpecimenCollectionVo(SpecimenCollection specimenCollection){
        SpecimenCollectionVo specimenCollectionVo = new SpecimenCollectionVo();
        BeanUtils.copyProperties(specimenCollection,specimenCollectionVo);
        return specimenCollectionVo;
    }
}
