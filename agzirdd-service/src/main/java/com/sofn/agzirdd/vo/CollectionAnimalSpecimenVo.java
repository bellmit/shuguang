package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.CollectionAnimalSpecimen;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @author Administrator
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CollectionAnimalSpecimenVo extends CollectionAnimalSpecimen {

    @ApiModelProperty(name = "imgUrl", value = "图片Url")
    private String imgUrl;


    public CollectionAnimalSpecimenVo(){}
    /**
     * 将vo转换为po对象
     */
    public static CollectionAnimalSpecimen getCollectionAnimalSpecimen(CollectionAnimalSpecimenVo collectionAnimalSpecimenVo){
        CollectionAnimalSpecimen monitorContent = new CollectionAnimalSpecimen();
        BeanUtils.copyProperties(collectionAnimalSpecimenVo,monitorContent);
        return monitorContent;
    }
    /**
     * po转换为vo
     */
    public static CollectionAnimalSpecimenVo getCollectionAnimalSpecimenVo(CollectionAnimalSpecimen monitorContent){
        CollectionAnimalSpecimenVo collectionAnimalSpecimenVo = new CollectionAnimalSpecimenVo();
        BeanUtils.copyProperties(monitorContent,collectionAnimalSpecimenVo);
        return collectionAnimalSpecimenVo;
    }
}
