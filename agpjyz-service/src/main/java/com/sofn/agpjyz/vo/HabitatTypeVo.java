package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.model.HabitatType;
import com.sofn.agpjyz.model.Source;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;


@Data
@ApiModel(value = " 生境类型VO对象")
public class HabitatTypeVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "资源调查ID")
    private String sourceId;
    @ApiModelProperty(value = "生境类型ID")
    private String habitatId;
    @ApiModelProperty(value = "生境类型名称")
    private String habitatValue;

    /**
     * 实体类转VO类
     */
    public static HabitatTypeVo entity2Vo(HabitatType entity) {
        HabitatTypeVo vo = new HabitatTypeVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
