package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.CollectionMicrobeSpecimen;
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
public class CollectionMicrobeSpecimenVo extends CollectionMicrobeSpecimen {

    @ApiModelProperty(name = "imgUrl", value = "图片Url")
    private String imgUrl;


    public CollectionMicrobeSpecimenVo(){}
    /**
     * 将vo转换为po对象
     */
    public static CollectionMicrobeSpecimen getCollectionMicrobeSpecimen(CollectionMicrobeSpecimenVo collectionMicrobeSpecimenVo){
        CollectionMicrobeSpecimen monitorContent = new CollectionMicrobeSpecimen();
        BeanUtils.copyProperties(collectionMicrobeSpecimenVo,monitorContent);
        return monitorContent;
    }
    /**
     * po转换为vo
     */
    public static CollectionMicrobeSpecimenVo getCollectionMicrobeSpecimenVo(CollectionMicrobeSpecimen monitorContent){
        CollectionMicrobeSpecimenVo collectionMicrobeSpecimenVo = new CollectionMicrobeSpecimenVo();
        BeanUtils.copyProperties(monitorContent,collectionMicrobeSpecimenVo);
        return collectionMicrobeSpecimenVo;
    }
}
