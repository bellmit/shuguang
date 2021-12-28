package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.model.CharactModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 16:08
 */
@Data
public class CharacterVo {
    @ApiModelProperty(value = "主键")
    private String id;
    //            性状ID
    @ApiModelProperty(value = "性状ID")
    private String characterId;
    //           性状VALUE
    @ApiModelProperty(value = "性状值")
    private String characterValue;
    //          标本ID
    @ApiModelProperty(value = "标本ID")
    private String specimenId;
    @ApiModelProperty(value = "类型 1:乔木 2:宜立")
    private String type;

    /**
     * 实体类转VO类
     */
    public static CharacterVo entity2Vo(CharactModel entity) {
        CharacterVo vo = new CharacterVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
