package com.sofn.fyem.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 放流活动级别Vo
 * @Author: DJH
 * @Date: 2020/4/27 17:03
 */
@ApiModel(value = "ReleaseLevelDropDownVo", description = "放流活动级别Vo")
@EqualsAndHashCode(callSuper = false)
@Data
public class ReleaseLevelDropDownVo extends BaseVo<ReleaseLevelDropDownVo> {

    @ApiModelProperty(name = "status", value = "级别码")
    private String status;

    @ApiModelProperty(name = "describe", value = "描述")
    private String describe;

    public ReleaseLevelDropDownVo() {
    }

    public ReleaseLevelDropDownVo(String status, String describe) {
        this.status = status;
        this.describe = describe;
    }
}
