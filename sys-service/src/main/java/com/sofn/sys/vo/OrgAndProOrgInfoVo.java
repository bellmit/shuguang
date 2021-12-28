package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行政机构信息和代理机构信息
 * @author heyongjie
 * @date 2020/1/13 16:43
 */
@Data
@ApiModel(value = "机构和代理机构信息")
public class OrgAndProOrgInfoVo {

    /**
     * 区划ID
     */
    @ApiModelProperty("区划ID")
    private String regionId;

    /**
     * 区划名称
     */
    @ApiModelProperty("区划名称")
    private String regionName;

    /**
     * 行政机构ID
     */
    @ApiModelProperty("行政机构ID")
    private String orgId;

    /**
     * 行政机构名称
     */
    @ApiModelProperty("行政机构名称")
    private String orgName;

    /**
     * 行政机构电话
     */
    @ApiModelProperty("行政机构电话")
    private String orgPhone;

    /**
     * 代理机构ID
     */
    @ApiModelProperty("代理机构ID")
    private String proxyId;

    /**
     * 代理机构名称
     */
    @ApiModelProperty("代理机构名称")
    private String proxyOrgName;

    /**
     * 代理机构电话
     */
    @ApiModelProperty("代理机构名称")
    private String proxyPhone;

    @ApiModelProperty("状态：Y启用，N未启用")
    private String status;
}
