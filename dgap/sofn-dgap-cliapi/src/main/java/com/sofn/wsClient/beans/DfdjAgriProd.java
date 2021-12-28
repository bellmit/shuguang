package com.sofn.wsClient.beans;

import javax.validation.constraints.Size;

public class DfdjAgriProd
{

    @Size(max=64)
    private String id;

    @Size(max=64)
    private String parentId;

    @Size(max=100)
    private String productCode;

    @Size(max=100)
    private String useCode;

    @Size(max=100)
    private String name;

    @Size(max=255)
    private String alias;

    @Size(max=255)
    private String enname;

    @Size(max=255)
    private String gbCode;

    @Size(max=1000)
    private String description;

    @Size(max=64)
    private String typeId;

    @Size(max=255)
    private String parentName;

    @Size(max=1)
    private String delFlag;

    @Size(max=255)
    private String slaughterStatus;

    public String getId()
    {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null ? null : id.trim());
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = (parentId == null ? null : parentId.trim());
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = (productCode == null ? null : productCode.trim());
    }

    public String getUseCode() {
        return this.useCode;
    }

    public void setUseCode(String useCode) {
        this.useCode = (useCode == null ? null : useCode.trim());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = (name == null ? null : name.trim());
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = (alias == null ? null : alias.trim());
    }

    public String getEnname() {
        return this.enname;
    }

    public void setEnname(String enname) {
        this.enname = (enname == null ? null : enname.trim());
    }

    public String getGbCode() {
        return this.gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = (gbCode == null ? null : gbCode.trim());
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = (description == null ? null : description.trim());
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = (typeId == null ? null : typeId.trim());
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = (parentName == null ? null : parentName.trim());
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = (delFlag == null ? null : delFlag.trim());
    }

    public String getSlaughterStatus() {
        return this.slaughterStatus;
    }

    public void setSlaughterStatus(String slaughterStatus) {
        this.slaughterStatus = (slaughterStatus == null ? null : slaughterStatus.trim());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", parentId=").append(this.parentId);
        sb.append(", productCode=").append(this.productCode);
        sb.append(", useCode=").append(this.useCode);
        sb.append(", name=").append(this.name);
        sb.append(", alias=").append(this.alias);
        sb.append(", enname=").append(this.enname);
        sb.append(", gbCode=").append(this.gbCode);
        sb.append(", description=").append(this.description);
        sb.append(", typeId=").append(this.typeId);
        sb.append(", parentName=").append(this.parentName);
        sb.append(", delFlag=").append(this.delFlag);
        sb.append(", slaughterStatus=").append(this.slaughterStatus);
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object that)
    {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DfdjAgriProd other = (DfdjAgriProd)that;
        return (getId() == null ? other.getId() == null : getId().equals(other.getId())) &&
                (getParentId() == null ? other.getParentId() == null : getParentId().equals(other.getParentId())) &&
                (getProductCode() == null ? other.getProductCode() == null : getProductCode().equals(other.getProductCode())) &&
                (getUseCode() == null ? other.getUseCode() == null : getUseCode().equals(other.getUseCode())) &&
                (getName() == null ? other.getName() == null : getName().equals(other.getName())) &&
                (getAlias() == null ? other.getAlias() == null : getAlias().equals(other.getAlias())) &&
                (getEnname() == null ? other.getEnname() == null : getEnname().equals(other.getEnname())) &&
                (getGbCode() == null ? other.getGbCode() == null : getGbCode().equals(other.getGbCode())) &&
                (getDescription() == null ? other.getDescription() == null : getDescription().equals(other.getDescription())) &&
                (getTypeId() == null ? other.getTypeId() == null : getTypeId().equals(other.getTypeId())) &&
                (getParentName() == null ? other.getParentName() == null : getParentName().equals(other.getParentName())) &&
                (getDelFlag() == null ? other.getDelFlag() == null : getDelFlag().equals(other.getDelFlag())) &&
                (getSlaughterStatus() == null ? other.getSlaughterStatus() == null : getSlaughterStatus().equals(other.getSlaughterStatus()));
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (getId() == null ? 0 : getId().hashCode());
        result = 31 * result + (getParentId() == null ? 0 : getParentId().hashCode());
        result = 31 * result + (getProductCode() == null ? 0 : getProductCode().hashCode());
        result = 31 * result + (getUseCode() == null ? 0 : getUseCode().hashCode());
        result = 31 * result + (getName() == null ? 0 : getName().hashCode());
        result = 31 * result + (getAlias() == null ? 0 : getAlias().hashCode());
        result = 31 * result + (getEnname() == null ? 0 : getEnname().hashCode());
        result = 31 * result + (getGbCode() == null ? 0 : getGbCode().hashCode());
        result = 31 * result + (getDescription() == null ? 0 : getDescription().hashCode());
        result = 31 * result + (getTypeId() == null ? 0 : getTypeId().hashCode());
        result = 31 * result + (getParentName() == null ? 0 : getParentName().hashCode());
        result = 31 * result + (getDelFlag() == null ? 0 : getDelFlag().hashCode());
        result = 31 * result + (getSlaughterStatus() == null ? 0 : getSlaughterStatus().hashCode());
        return result;
    }
}