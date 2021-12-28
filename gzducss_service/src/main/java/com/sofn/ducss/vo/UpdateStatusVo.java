package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取前端查询对象表单vo
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UpdateStatusVo {

    @ApiModelProperty(name = "status", value = "状态")
    private Byte status;

    @ApiModelProperty(name = "flowId", value = "只选一个数据操作的时候id传递")
    private String flowId;

    @ApiModelProperty(name = "minhour", value = "再次上报时间限制")
    private String minhour;

    @ApiModelProperty(name = "remark", value = "退回原因")
    private String remark;

    @ApiModelProperty(name = "flowIds", value = "多选情况下，所有的id用英文逗号拼接传递过来（去除末尾的逗号）")
    private String flowIds;

    @ApiModelProperty(name = "many", value = "是否多选")
    private boolean many;

    private String year;
    private String areaId;
    @ApiModelProperty(hidden = true)
    private String provinceId;
    @ApiModelProperty(hidden = true)
    private String cityId;

}
