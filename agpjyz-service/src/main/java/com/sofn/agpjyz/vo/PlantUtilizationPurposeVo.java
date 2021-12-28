package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.model.PlantUtilizationPurpose;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@ApiModel(value = "植物利用用途VO对象")
public class PlantUtilizationPurposeVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "利用ID")
    private String utilizationId;
    @ApiModelProperty(value = "用途ID")
    private String purposeId;
    @ApiModelProperty(value = "用途名称")
    private String purposeValue;

    /**
     * 实体类转VO类
     */
    public static PlantUtilizationPurposeVo entity2Vo(PlantUtilizationPurpose entity) {
        PlantUtilizationPurposeVo vo = new PlantUtilizationPurposeVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
