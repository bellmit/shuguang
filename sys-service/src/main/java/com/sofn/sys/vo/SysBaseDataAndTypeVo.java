package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础数据字典值和类型VO
 *
 * @author heyongjie
 * @date 2020/9/9 15:50
 */
@Data
@ApiModel(value="基础数据字典值和类型")
public class SysBaseDataAndTypeVo {

    @ApiModelProperty(value = "数据字典类型ID")
    private String typeId;

    @ApiModelProperty(value = "数据字典值ID")
    private String dataId;

    @ApiModelProperty(value = "数据字典类型代码")
    private String typeCode;

    @ApiModelProperty(value = "数据字典类型名称")
    private String typeName;

    @ApiModelProperty(value = "数据字典类型备注")
    private String typeRemark;

    @ApiModelProperty(value = "数据字典值代码")
    private String dataCode;

    @ApiModelProperty(value = "数据字典值名称")
    private String dataName;

    @ApiModelProperty(value = "数据字典值备注")
    private String dataRemark;

}
