package com.sofn.infroflow.model;

import com.cvicse.workflow.api.resources.OrgnizationUnit;

/**
 * @author heyongjie
 * @date 2020年4月28日14:43:51
 */
public class SysOrg {

    /**
     * 机构唯一标识
     */
    private String id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 机构描述
     */
    private String description;

    /**
     * 将系统机构信息适配成流程机构
     * @param sysOrg  系统机构信息
     * @return   流程机构
     */
    public static OrgnizationUnit getOrg(SysOrg sysOrg){
        if(sysOrg == null){
            return null;
        }
        OrgnizationUnit orgnizationUnit = new OrgnizationUnit();
        orgnizationUnit.setId(sysOrg.getId());
        orgnizationUnit.setName(sysOrg.getName());
        orgnizationUnit.setDescription(sysOrg.getDescription());
        return orgnizationUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
