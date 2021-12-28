package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author heyongjie
 * @date 2020/4/1 17:24
 */
@Data
@ApiModel(value="机构代理分配状态参数")
public class SysOrgAgentStatusVo {

    @ApiModelProperty(value = "机构ID")
    private String id;

    @ApiModelProperty(value = "状态，Y启用，N禁用")
    private String status;

    /**
     * 机构名称
     */
    private String name;

}
