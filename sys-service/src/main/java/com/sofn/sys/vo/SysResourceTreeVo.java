package com.sofn.sys.vo;

import com.sofn.common.treeutil.basic.BasicTreeBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 资源Vo
 * @author heyongjie
 * @date 2019/12/19 15:52
 */
@Data
public class SysResourceTreeVo extends BasicTreeBean<SysResourceTreeVo> {

    @ApiModelProperty("菜单名称")
    private String resourceName;

    @ApiModelProperty("菜单类型")
    private String type;

    @ApiModelProperty("菜单url")
    private String resourceUrl;

    @ApiModelProperty("权限代码")
    private String permission;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("子系统AppId")
    private String appId;

}
