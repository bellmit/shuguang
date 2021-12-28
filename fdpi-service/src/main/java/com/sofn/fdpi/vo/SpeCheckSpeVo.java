package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther:
 * @Date: 2019/12/2 14:13
 * @Description:
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpeCheckSpeVo {

    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(name = "speName", value = "物种名")
    private String speName;

    @ApiModelProperty(name = "identify", value = "是否使用标识写死，0:不使用,1 :全程使用,2:销售使用")
    private String identify;

    @ApiModelProperty(name = "pedigree", value = "是否进行谱系写死，否：0是：1")
    private String pedigree;

    @ApiModelProperty(name = "proLevel", value = "中国保护水平写死 一级:1 ，2级：2 ，特殊管理要求：3")
    private String proLevel;
    @ApiModelProperty(name = "cites", value = "CITES级别，1级：1,2级：2,3级：3")
    private String cites;

}