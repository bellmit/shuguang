package com.sofn.wsClient.beans;

import javax.validation.constraints.Size;

public class DfdjDictData
{

    @Size(max=64)
    private String id;

    @Size(max=32)
    private String simpleName;

    @Size(max=64)
    private String typeId;

    @Size(max=100)
    private String categorieId;

    @Size(max=32)
    private String dictCode;

    @Size(max=100)
    private String dictName;

    @Size(max=100)
    private String spellName;

    @Size(max=32)
    private String dictValue;

    @Size(max=1)
    private String delFlag;

    @Size(max=1)
    private String enable;

    @Size(max=500)
    private String remark;

    public String getId()
    {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null ? null : id.trim());
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = (simpleName == null ? null : simpleName.trim());
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = (typeId == null ? null : typeId.trim());
    }

    public String getCategorieId() {
        return this.categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = (categorieId == null ? null : categorieId.trim());
    }

    public String getDictCode() {
        return this.dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = (dictCode == null ? null : dictCode.trim());
    }

    public String getDictName() {
        return this.dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = (dictName == null ? null : dictName.trim());
    }

    public String getSpellName() {
        return this.spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = (spellName == null ? null : spellName.trim());
    }

    public String getDictValue() {
        return this.dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = (dictValue == null ? null : dictValue.trim());
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = (delFlag == null ? null : delFlag.trim());
    }

    public String getEnable() {
        return this.enable;
    }

    public void setEnable(String enable) {
        this.enable = (enable == null ? null : enable.trim());
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = (remark == null ? null : remark.trim());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", simpleName=").append(this.simpleName);
        sb.append(", typeId=").append(this.typeId);
        sb.append(", categorieId=").append(this.categorieId);
        sb.append(", dictCode=").append(this.dictCode);
        sb.append(", dictName=").append(this.dictName);
        sb.append(", spellName=").append(this.spellName);
        sb.append(", dictValue=").append(this.dictValue);
        sb.append(", delFlag=").append(this.delFlag);
        sb.append(", enable=").append(this.enable);
        sb.append(", remark=").append(this.remark);
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
        DfdjDictData other = (DfdjDictData)that;
        return (getId() == null ? other.getId() == null : getId().equals(other.getId())) &&
                (getSimpleName() == null ? other.getSimpleName() == null : getSimpleName().equals(other.getSimpleName())) &&
                (getTypeId() == null ? other.getTypeId() == null : getTypeId().equals(other.getTypeId())) &&
                (getCategorieId() == null ? other.getCategorieId() == null : getCategorieId().equals(other.getCategorieId())) &&
                (getDictCode() == null ? other.getDictCode() == null : getDictCode().equals(other.getDictCode())) &&
                (getDictName() == null ? other.getDictName() == null : getDictName().equals(other.getDictName())) &&
                (getSpellName() == null ? other.getSpellName() == null : getSpellName().equals(other.getSpellName())) &&
                (getDictValue() == null ? other.getDictValue() == null : getDictValue().equals(other.getDictValue())) &&
                (getDelFlag() == null ? other.getDelFlag() == null : getDelFlag().equals(other.getDelFlag())) &&
                (getEnable() == null ? other.getEnable() == null : getEnable().equals(other.getEnable())) &&
                (getRemark() == null ? other.getRemark() == null : getRemark().equals(other.getRemark()));
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (getId() == null ? 0 : getId().hashCode());
        result = 31 * result + (getSimpleName() == null ? 0 : getSimpleName().hashCode());
        result = 31 * result + (getTypeId() == null ? 0 : getTypeId().hashCode());
        result = 31 * result + (getCategorieId() == null ? 0 : getCategorieId().hashCode());
        result = 31 * result + (getDictCode() == null ? 0 : getDictCode().hashCode());
        result = 31 * result + (getDictName() == null ? 0 : getDictName().hashCode());
        result = 31 * result + (getSpellName() == null ? 0 : getSpellName().hashCode());
        result = 31 * result + (getDictValue() == null ? 0 : getDictValue().hashCode());
        result = 31 * result + (getDelFlag() == null ? 0 : getDelFlag().hashCode());
        result = 31 * result + (getEnable() == null ? 0 : getEnable().hashCode());
        result = 31 * result + (getRemark() == null ? 0 : getRemark().hashCode());
        return result;
    }
}