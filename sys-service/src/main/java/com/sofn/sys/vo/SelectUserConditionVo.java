package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户按条件查询接口，多用于各个子系统查询用户信息
 * @author heyongjie
 * @date 2019/12/5 11:10
 */
@Data
@ApiModel(value="基础数据类型管理")
public class SelectUserConditionVo {

    @ApiModelProperty("IDS，多个使用,隔开")
    private String strIds;

    @ApiModelProperty("昵称，模糊查询")
    private String nickName;

    @ApiModelProperty("用户名称，模糊查询")
    private String userName;

    @ApiModelProperty("子系统ID，或者子系统的APP_ID")
    private String subsystemId;

    @ApiModelProperty("角色ID或者角色代码，多个使用,隔开")
    private String roleIdStrs;

    @ApiModelProperty("机构ID")
    private String orgId;

    @ApiModelProperty("机构名称，模糊查询")
    private String orgName;

    @ApiModelProperty(hidden = true)
    private List<String> ids;

    @ApiModelProperty(hidden = true)
    private List<String> roleIds;

    @ApiModelProperty("从哪条数据开始")
    Integer pageNo;

    @ApiModelProperty("每页多少条")
    Integer pageSize;

    @ApiModelProperty("区域")
    List<String> regioncodes;

}
