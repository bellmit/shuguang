package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/2 17:09
 */
@ApiModel(value="罚没查询VO对象")
@Data
public class ForfeiVo extends BaseVo<ForfeiVo> {
    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;
    /**
     * 罚没类型
     */
    @ApiModelProperty(value = "罚没类型")
    private String ffType;
    /**
     * 物种名
     */
    @ApiModelProperty(value = "物种名")
    private String speName;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
