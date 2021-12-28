package com.sofn.sys.vo;

import com.sofn.common.treeutil.basic.BasicTreeBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author heyongjie
 * @date 2019/12/19 17:14
 */
@Data
public class SysSubsystemTreeVo extends BasicTreeBean<SysSubsystemTreeVo> {

    @ApiModelProperty("子系统名称")
    private String subsystemName;

    @ApiModelProperty("子系统APPID")
    private String appId;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("前端访问路径")
    private String viewUrl;

    @ApiModelProperty("系统类型，1 平台 2 分系统 3 子系统")
    private String systemType;

}
