package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:17
 */
@Data
@ApiModel("收容救助Vo")
public class ManageResVo extends BaseVo<ManageResVo> {
    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;
    /**
     * 救护地点
     */
    @ApiModelProperty(value = "救护地点")
    private String rescueSite;
    /**
     * 救护物种
     */
    @ApiModelProperty(value = "救护物种")
    private String rescueSpe;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
