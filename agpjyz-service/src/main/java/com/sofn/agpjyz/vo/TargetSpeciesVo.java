package com.sofn.agpjyz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.agpjyz.model.TargetSpecies;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ApiModel(value = "目标物种基础信息VO对象")
public class TargetSpeciesVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    @ApiModelProperty(value = "保护点名称")
    private String protectValue;
    @ApiModelProperty(value = "目标物种ID")
    private String specId;
    @ApiModelProperty(value = "目标物种名称")
    private String specValue;
    @ApiModelProperty(value = "拉丁学名")
    private String latinName;
    @ApiModelProperty(value = "数量")
    private Double amount;
    @ApiModelProperty(value = "生长状况")
    private String grow;
    @ApiModelProperty(value = "物种丰富度")
    private String richness;
    @ApiModelProperty(value = "操作人")
    private String inputer;
    @ApiModelProperty(value = "操作时间")
    private Date inputerTime;

    /**
     * 实体类转VO类
     */
    public static TargetSpeciesVo entity2Vo(TargetSpecies entity) {
        TargetSpeciesVo vo = new TargetSpeciesVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }

}
