package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.CollectionPlantSpecimen;
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
public class CollectionPlantSpecimenVo extends CollectionPlantSpecimen {


    @ApiModelProperty(name = "rootImgUrl", value = "根图片Url")
    private String rootImgUrl;

    @ApiModelProperty(name = "stemImgUrl", value = "茎图片Url")
    private String stemImgUrl;

    @ApiModelProperty(name = "leafImgUrl", value = "叶图片Url")
    private String leafImgUrl;

    @ApiModelProperty(name = "flowerImgUrl", value = "花图片Url")
    private String flowerImgUrl;

    @ApiModelProperty(name = "fruitImgUrl", value = "果图片Url")
    private String fruitImgUrl;

    @ApiModelProperty(name = "seedImgUrl", value = "种子图片Url")
    private String seedImgUrl;

    public CollectionPlantSpecimenVo(){}
    /**
     * 将vo转换为po对象
     */
    public static CollectionPlantSpecimen getCollectionPlantSpecimen(CollectionPlantSpecimenVo collectionPlantSpecimenVo){
        CollectionPlantSpecimen monitorContent = new CollectionPlantSpecimen();
        BeanUtils.copyProperties(collectionPlantSpecimenVo,monitorContent);
        return monitorContent;
    }
    /**
     * po转换为vo
     */
    public static CollectionPlantSpecimenVo getCollectionPlantSpecimenVo(CollectionPlantSpecimen monitorContent){
        CollectionPlantSpecimenVo collectionPlantSpecimenVo = new CollectionPlantSpecimenVo();
        BeanUtils.copyProperties(monitorContent,collectionPlantSpecimenVo);
        return collectionPlantSpecimenVo;
    }
}
