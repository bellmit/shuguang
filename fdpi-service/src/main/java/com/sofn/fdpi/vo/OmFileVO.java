package com.sofn.fdpi.vo;

import com.sofn.fdpi.model.OmFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@ApiModel(value = "欧鳗文件信息VO对象")
public class OmFileVO {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "文件id")
    private String fileId;
    @ApiModelProperty(value = "文件url")
    private String fileUrl;
    @ApiModelProperty(value = "文件名字")
    private String fileName;
    @ApiModelProperty(value = "文件用途")
    private String fileUse;

    /**
     * 实体类转VO类
     */
    public static OmFileVO entity2Vo(OmFile entity) {
        OmFileVO vo = new OmFileVO();
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }

}
