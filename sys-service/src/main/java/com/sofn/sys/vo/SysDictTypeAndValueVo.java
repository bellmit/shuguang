package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 字典类型和值VO
 *
 * @author heyongjie
 * @date 2020/4/7 9:34
 */
@Data
@ApiModel(value="字典类型管理-分页返回Bean")
public class SysDictTypeAndValueVo {

    @ApiModelProperty("字典类型ID")
    private String id;

    @ApiModelProperty("字典类型名称")
    private String typeName;

    /**
     * 字典类型值
     */
    @ApiModelProperty("字典类型（英文）")
    private String typeValue;

    /**
     * 字典值
     */
    @ApiModelProperty("字典类型值")
    private List<SysDictForm> sysDictForm;

}
