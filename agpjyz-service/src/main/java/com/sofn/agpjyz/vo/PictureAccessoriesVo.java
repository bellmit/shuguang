package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.model.PictureAccessories;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@ApiModel(value = "图片附件VO对象6类,分别为root根stem茎leaf叶flower花fruit果seed种子")
public class PictureAccessoriesVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "文件ID(支撑平台文件ID)")
    private String fileId;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件用途 root根stem茎leaf叶flower花fruit果seed种子")
    private String fileUse;
    @ApiModelProperty(value = "文件描述")
    private String fileDescribe;
    @ApiModelProperty(value = "来源ID")
    private String sourceId;


    /**
     * 实体类转VO类
     */
    public static PictureAccessoriesVo entity2Vo(PictureAccessories entity) {
        PictureAccessoriesVo vo = new PictureAccessoriesVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
