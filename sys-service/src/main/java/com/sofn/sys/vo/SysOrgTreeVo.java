package com.sofn.sys.vo;

import com.sofn.sys.model.SysOrganization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@ApiModel(value="组织管理树VO对象")
public class SysOrgTreeVo {

    @ApiModelProperty(value = "组织机构管理ID")
    private String id;
    /**
     * 组织机构名称
     */

    private String organizationName;
    /**
     * 父编号
     */
    private String parentId;
    /**
     * 父编号列表，如1/2/
     */
    private String parentIds;

    private String delFlag;

    /**
     * 叶子节点
     */
    private String leaf;
    /**
     * 排序
     */
    private String priority;
    /**
     * 区域码
     */
    private String regioncode;

    /**
     * 区域码名称
     */
    private String regioncodeName;

    /**
     * 级别
     */
    private String organizationLevel;

    /**
     * 级别名称
     */
    private String organizationLevelName;

    /**
     * 是否第三方机构
     */
    private String thirdOrg;
    /**
     * 所在地址
     */
    private String address;

    /**
     * 所在地址名称
     */
    private String addressName;

    @ApiModelProperty(value = "组织机构的子组织")
    private List<SysOrgTreeVo> children;

    public static SysOrgTreeVo getSysOrgTreeVo(SysOrganization sysOrganization){
        SysOrgTreeVo sysOrgTreeVo = new SysOrgTreeVo();
        BeanUtils.copyProperties(sysOrganization,sysOrgTreeVo);
        return sysOrgTreeVo;
    }
}
