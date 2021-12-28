package com.sofn.sys.vo;

import com.sofn.common.treeutil.basic.BasicTreeBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *  组织机构树节点
 * @author heyongjie
 * @date 2019/12/17 11:20
 */
@Data
public class SysOrganizationTreeVo  extends BasicTreeBean<SysOrganizationTreeVo> {

    @ApiModelProperty(value = "组织机构名称")

    private String organizationName;

    @ApiModelProperty(value = "机构类型（行政机构/非行政机构）")
    private String thirdOrg;

    @ApiModelProperty(value = "级别")
    private String organizationLevel;

    @ApiModelProperty(value = "级别名称")
    private String organizationLevelName;

    @ApiModelProperty(value = "区域码")
    private String regioncode;

    @ApiModelProperty(value = "区域码名称")
    private String regioncodeName;

    @ApiModelProperty(value = "所在地址")
    private String address;

    @ApiModelProperty(value = "所在地址名称")
    private String addressName;

    @ApiModelProperty(value = "资质证书信息，KEY为证书类型ID，value为证书所对应的文件信息，为List，查询时会返回")
    private Map<String, List<SysFileManageVo>> cTypeAndFileInfo;

    @ApiModelProperty(value = "负责人")
    private String principal;

    @ApiModelProperty(value = "负责人电话")
    private String phone;

    @ApiModelProperty(value = "叶子节点")
    private String leaf;

    @ApiModelProperty(value = "机构职能")
    private String orgFunction;

    @ApiModelProperty(value = "系统代码")
    private List<String> appId;


    /**
     * 将SysOrgVo适配为SysOrganizationTreeVo
     * @param sysOrgVo SysOrgVo
     * @return SysOrganizationTreeVo
     */
    public static SysOrganizationTreeVo getSysOrganizationTreeVo(SysOrgVo sysOrgVo){
        SysOrganizationTreeVo sysOrganizationTreeVo = new SysOrganizationTreeVo();
        sysOrganizationTreeVo.setId(sysOrgVo.getId());
        sysOrganizationTreeVo.setParentId(sysOrgVo.getParentId());
        sysOrganizationTreeVo.setOrganizationName(sysOrgVo.getName());
        sysOrganizationTreeVo.setSort(0);
        sysOrganizationTreeVo.setAppId(sysOrgVo.getAppIds());
        sysOrganizationTreeVo.setThirdOrg(sysOrgVo.getThirdOrg());
        return sysOrganizationTreeVo;
    }
}
