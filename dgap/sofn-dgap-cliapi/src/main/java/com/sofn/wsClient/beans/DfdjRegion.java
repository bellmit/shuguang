package com.sofn.wsClient.beans;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class DfdjRegion
{

    @Size(max=64)
    private String id;

    @Size(max=64)
    private String parentId;

    @Size(max=50)
    private String regionName;

    @Size(max=64)
    private String regionCode;

    @Size(max=100)
    private String regionPinyin;

    @Size(max=64)
    private String regionType;

    @Size(max=50)
    private String regionFullname;

    @Digits(integer=22, fraction=0)
    private BigDecimal sortid;

    @Size(max=400)
    private String remark;

    @Size(max=1)
    private String delFlag;

    @Size(max=8)
    private String stat;

    @Size(max=1)
    private String isProvAdmCounty;

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

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = (regionName == null ? null : regionName.trim());
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = (regionCode == null ? null : regionCode.trim());
    }

    public String getRegionPinyin() {
        return this.regionPinyin;
    }

    public void setRegionPinyin(String regionPinyin) {
        this.regionPinyin = (regionPinyin == null ? null : regionPinyin.trim());
    }

    public String getRegionType() {
        return this.regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = (regionType == null ? null : regionType.trim());
    }

    public String getRegionFullname() {
        return this.regionFullname;
    }

    public void setRegionFullname(String regionFullname) {
        this.regionFullname = (regionFullname == null ? null : regionFullname.trim());
    }

    public BigDecimal getSortid() {
        return this.sortid;
    }

    public void setSortid(BigDecimal sortid) {
        this.sortid = sortid;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = (remark == null ? null : remark.trim());
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = (delFlag == null ? null : delFlag.trim());
    }

    public String getStat() {
        return this.stat;
    }

    public void setStat(String stat) {
        this.stat = (stat == null ? null : stat.trim());
    }

    public String getIsProvAdmCounty() {
        return this.isProvAdmCounty;
    }

    public void setIsProvAdmCounty(String isProvAdmCounty) {
        this.isProvAdmCounty = isProvAdmCounty;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;
        DfdjRegion that = (DfdjRegion)o;
        return (Objects.equals(getId(), that.getId())) &&
                (Objects.equals(getParentId(), that.getParentId())) &&
                (Objects.equals(getRegionName(), that.getRegionName())) &&
                (Objects.equals(getRegionCode(), that.getRegionCode())) &&
                (Objects.equals(getRegionPinyin(), that.getRegionPinyin())) &&
                (Objects.equals(getRegionType(), that.getRegionType())) &&
                (Objects.equals(getRegionFullname(), that.getRegionFullname())) &&
                (Objects.equals(getSortid(), that.getSortid())) &&
                (Objects.equals(getRemark(), that.getRemark())) &&
                (Objects.equals(getDelFlag(), that.getDelFlag())) &&
                (Objects.equals(getStat(), that.getStat())) &&
                (Objects.equals(getIsProvAdmCounty(), that.getIsProvAdmCounty()));
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] { getId(), getParentId(), getRegionName(), getRegionCode(), getRegionPinyin(), getRegionType(), getRegionFullname(), getSortid(), getRemark(), getDelFlag(), getStat(), getIsProvAdmCounty() });
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("DfdjRegion{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", parentId='").append(this.parentId).append('\'');
        sb.append(", regionName='").append(this.regionName).append('\'');
        sb.append(", regionCode='").append(this.regionCode).append('\'');
        sb.append(", regionPinyin='").append(this.regionPinyin).append('\'');
        sb.append(", regionType='").append(this.regionType).append('\'');
        sb.append(", regionFullname='").append(this.regionFullname).append('\'');
        sb.append(", sortid=").append(this.sortid);
        sb.append(", remark='").append(this.remark).append('\'');
        sb.append(", delFlag='").append(this.delFlag).append('\'');
        sb.append(", stat='").append(this.stat).append('\'');
        sb.append(", isProvAdmCounty='").append(this.isProvAdmCounty).append('\'');
        sb.append('}');
        return sb.toString();
    }
}