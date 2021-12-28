package com.sofn.wsClient.beans;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class DfdjBaseEnvData {
    @Size(max=64)
    private String id;

    @Size(max=20)
    private String mjdw;

    @Size(max=20)
    private String zwsy;

    @Size(max=20)
    private String fzr;

    @Size(max=20)
    private String fzrdh;

    @Size(max=500)
    private String hjjcdw;

    @Size(max=20)
    private String hjjcdwdh;

    @Size(max=500)
    private String jcyjbzbhjmc;

    @Size(max=20)
    private String jcsj;

    @Size(max=500)
    private String jcjl;

    @Size(max=4000)
    private String productNames;

    @Size(max=32)
    private String longitude;

    @Size(max=32)
    private String latitude;

    @Size(max=2014)
    private String picture;

    @Size(max=32)
    private String pictureTwo;

    @Size(max=140)
    private String ip;

    @Size(max=100)
    private String createBy;

    private Date createTime;

    @Size(max=100)
    private String updateBy;

    private Date updateTime;

    @Size(max=1)
    private String delFlag;

    @Size(max=64)
    private String addressCode;

    @Size(max=100)
    private String addressName;

    @Size(max=64)
    private String userIdcode;

    @Size(max=500)
    private String ztmc;

    @Size(max=20)
    private String hymc;

    @Size(max=500)
    private String jdmc;

    @Size(max=20)
    private String jdbh;

    @Size(max=500)
    private String jddz;

    @Size(max=20)
    private String zxbz;

    @Digits(integer=34,fraction=4)
    private BigDecimal jdmj;

    private byte[] jcbg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMjdw() {
        return mjdw;
    }

    public void setMjdw(String mjdw) {
        this.mjdw = mjdw == null ? null : mjdw.trim();
    }

    public String getZwsy() {
        return zwsy;
    }

    public void setZwsy(String zwsy) {
        this.zwsy = zwsy == null ? null : zwsy.trim();
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr == null ? null : fzr.trim();
    }

    public String getFzrdh() {
        return fzrdh;
    }

    public void setFzrdh(String fzrdh) {
        this.fzrdh = fzrdh == null ? null : fzrdh.trim();
    }

    public String getHjjcdw() {
        return hjjcdw;
    }

    public void setHjjcdw(String hjjcdw) {
        this.hjjcdw = hjjcdw == null ? null : hjjcdw.trim();
    }

    public String getHjjcdwdh() {
        return hjjcdwdh;
    }

    public void setHjjcdwdh(String hjjcdwdh) {
        this.hjjcdwdh = hjjcdwdh == null ? null : hjjcdwdh.trim();
    }

    public String getJcyjbzbhjmc() {
        return jcyjbzbhjmc;
    }

    public void setJcyjbzbhjmc(String jcyjbzbhjmc) {
        this.jcyjbzbhjmc = jcyjbzbhjmc == null ? null : jcyjbzbhjmc.trim();
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj == null ? null : jcsj.trim();
    }

    public String getJcjl() {
        return jcjl;
    }

    public void setJcjl(String jcjl) {
        this.jcjl = jcjl == null ? null : jcjl.trim();
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames == null ? null : productNames.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getPictureTwo() {
        return pictureTwo;
    }

    public void setPictureTwo(String pictureTwo) {
        this.pictureTwo = pictureTwo == null ? null : pictureTwo.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode == null ? null : userIdcode.trim();
    }

    public String getZtmc() {
        return ztmc;
    }

    public void setZtmc(String ztmc) {
        this.ztmc = ztmc == null ? null : ztmc.trim();
    }

    public String getHymc() {
        return hymc;
    }

    public void setHymc(String hymc) {
        this.hymc = hymc == null ? null : hymc.trim();
    }

    public String getJdmc() {
        return jdmc;
    }

    public void setJdmc(String jdmc) {
        this.jdmc = jdmc == null ? null : jdmc.trim();
    }

    public String getJdbh() {
        return jdbh;
    }

    public void setJdbh(String jdbh) {
        this.jdbh = jdbh == null ? null : jdbh.trim();
    }

    public String getJddz() {
        return jddz;
    }

    public void setJddz(String jddz) {
        this.jddz = jddz == null ? null : jddz.trim();
    }

    public String getZxbz() {
        return zxbz;
    }

    public void setZxbz(String zxbz) {
        this.zxbz = zxbz == null ? null : zxbz.trim();
    }

    public BigDecimal getJdmj() {
        return jdmj;
    }

    public void setJdmj(BigDecimal jdmj) {
        this.jdmj = jdmj;
    }

    public byte[] getJcbg() {
        return jcbg;
    }

    public void setJcbg(byte[] jcbg) {
        this.jcbg = jcbg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mjdw=").append(mjdw);
        sb.append(", zwsy=").append(zwsy);
        sb.append(", fzr=").append(fzr);
        sb.append(", fzrdh=").append(fzrdh);
        sb.append(", hjjcdw=").append(hjjcdw);
        sb.append(", hjjcdwdh=").append(hjjcdwdh);
        sb.append(", jcyjbzbhjmc=").append(jcyjbzbhjmc);
        sb.append(", jcsj=").append(jcsj);
        sb.append(", jcjl=").append(jcjl);
        sb.append(", productNames=").append(productNames);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", picture=").append(picture);
        sb.append(", pictureTwo=").append(pictureTwo);
        sb.append(", ip=").append(ip);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", addressCode=").append(addressCode);
        sb.append(", addressName=").append(addressName);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", ztmc=").append(ztmc);
        sb.append(", hymc=").append(hymc);
        sb.append(", jdmc=").append(jdmc);
        sb.append(", jdbh=").append(jdbh);
        sb.append(", jddz=").append(jddz);
        sb.append(", zxbz=").append(zxbz);
        sb.append(", jdmj=").append(jdmj);
        sb.append(", jcbg=").append(jcbg);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DfdjBaseEnvData other = (DfdjBaseEnvData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMjdw() == null ? other.getMjdw() == null : this.getMjdw().equals(other.getMjdw()))
            && (this.getZwsy() == null ? other.getZwsy() == null : this.getZwsy().equals(other.getZwsy()))
            && (this.getFzr() == null ? other.getFzr() == null : this.getFzr().equals(other.getFzr()))
            && (this.getFzrdh() == null ? other.getFzrdh() == null : this.getFzrdh().equals(other.getFzrdh()))
            && (this.getHjjcdw() == null ? other.getHjjcdw() == null : this.getHjjcdw().equals(other.getHjjcdw()))
            && (this.getHjjcdwdh() == null ? other.getHjjcdwdh() == null : this.getHjjcdwdh().equals(other.getHjjcdwdh()))
            && (this.getJcyjbzbhjmc() == null ? other.getJcyjbzbhjmc() == null : this.getJcyjbzbhjmc().equals(other.getJcyjbzbhjmc()))
            && (this.getJcsj() == null ? other.getJcsj() == null : this.getJcsj().equals(other.getJcsj()))
            && (this.getJcjl() == null ? other.getJcjl() == null : this.getJcjl().equals(other.getJcjl()))
            && (this.getProductNames() == null ? other.getProductNames() == null : this.getProductNames().equals(other.getProductNames()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getPictureTwo() == null ? other.getPictureTwo() == null : this.getPictureTwo().equals(other.getPictureTwo()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getAddressCode() == null ? other.getAddressCode() == null : this.getAddressCode().equals(other.getAddressCode()))
            && (this.getAddressName() == null ? other.getAddressName() == null : this.getAddressName().equals(other.getAddressName()))
            && (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode()))
            && (this.getZtmc() == null ? other.getZtmc() == null : this.getZtmc().equals(other.getZtmc()))
            && (this.getHymc() == null ? other.getHymc() == null : this.getHymc().equals(other.getHymc()))
            && (this.getJdmc() == null ? other.getJdmc() == null : this.getJdmc().equals(other.getJdmc()))
            && (this.getJdbh() == null ? other.getJdbh() == null : this.getJdbh().equals(other.getJdbh()))
            && (this.getJddz() == null ? other.getJddz() == null : this.getJddz().equals(other.getJddz()))
            && (this.getZxbz() == null ? other.getZxbz() == null : this.getZxbz().equals(other.getZxbz()))
            && (this.getJdmj() == null ? other.getJdmj() == null : this.getJdmj().equals(other.getJdmj()))
            && (Arrays.equals(this.getJcbg(), other.getJcbg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMjdw() == null) ? 0 : getMjdw().hashCode());
        result = prime * result + ((getZwsy() == null) ? 0 : getZwsy().hashCode());
        result = prime * result + ((getFzr() == null) ? 0 : getFzr().hashCode());
        result = prime * result + ((getFzrdh() == null) ? 0 : getFzrdh().hashCode());
        result = prime * result + ((getHjjcdw() == null) ? 0 : getHjjcdw().hashCode());
        result = prime * result + ((getHjjcdwdh() == null) ? 0 : getHjjcdwdh().hashCode());
        result = prime * result + ((getJcyjbzbhjmc() == null) ? 0 : getJcyjbzbhjmc().hashCode());
        result = prime * result + ((getJcsj() == null) ? 0 : getJcsj().hashCode());
        result = prime * result + ((getJcjl() == null) ? 0 : getJcjl().hashCode());
        result = prime * result + ((getProductNames() == null) ? 0 : getProductNames().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getPictureTwo() == null) ? 0 : getPictureTwo().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getAddressCode() == null) ? 0 : getAddressCode().hashCode());
        result = prime * result + ((getAddressName() == null) ? 0 : getAddressName().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + ((getZtmc() == null) ? 0 : getZtmc().hashCode());
        result = prime * result + ((getHymc() == null) ? 0 : getHymc().hashCode());
        result = prime * result + ((getJdmc() == null) ? 0 : getJdmc().hashCode());
        result = prime * result + ((getJdbh() == null) ? 0 : getJdbh().hashCode());
        result = prime * result + ((getJddz() == null) ? 0 : getJddz().hashCode());
        result = prime * result + ((getZxbz() == null) ? 0 : getZxbz().hashCode());
        result = prime * result + ((getJdmj() == null) ? 0 : getJdmj().hashCode());
        result = prime * result + (Arrays.hashCode(getJcbg()));
        return result;
    }
}