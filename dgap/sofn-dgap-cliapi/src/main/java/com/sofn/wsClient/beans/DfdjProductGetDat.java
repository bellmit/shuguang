package com.sofn.wsClient.beans;

import java.math.BigDecimal;
import java.util.Arrays;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class DfdjProductGetDat {
    @Size(max=64)
    private String id;

    @Size(max=500)
    private String ztmc;

    @Size(max=500)
    private String cppcm;

    @Size(max=500)
    private String cpmc;

    @Size(max=20)
    private String shfs;

    @Digits(integer=34,fraction=4)
    private BigDecimal shsl;

    @Size(max=20)
    private String sldw;

    @Size(max=500)
    private String shsj;

    @Size(max=20)
    private String jlr;

    @Size(max=500)
    private String bz;

    private byte[] zttp1;

    private byte[] zttp2;

    private byte[] zttp3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getZtmc() {
        return ztmc;
    }

    public void setZtmc(String ztmc) {
        this.ztmc = ztmc == null ? null : ztmc.trim();
    }

    public String getCppcm() {
        return cppcm;
    }

    public void setCppcm(String cppcm) {
        this.cppcm = cppcm == null ? null : cppcm.trim();
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc == null ? null : cpmc.trim();
    }

    public String getShfs() {
        return shfs;
    }

    public void setShfs(String shfs) {
        this.shfs = shfs == null ? null : shfs.trim();
    }

    public BigDecimal getShsl() {
        return shsl;
    }

    public void setShsl(BigDecimal shsl) {
        this.shsl = shsl;
    }

    public String getSldw() {
        return sldw;
    }

    public void setSldw(String sldw) {
        this.sldw = sldw == null ? null : sldw.trim();
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj == null ? null : shsj.trim();
    }

    public String getJlr() {
        return jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr == null ? null : jlr.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public byte[] getZttp1() {
        return zttp1;
    }

    public void setZttp1(byte[] zttp1) {
        this.zttp1 = zttp1;
    }

    public byte[] getZttp2() {
        return zttp2;
    }

    public void setZttp2(byte[] zttp2) {
        this.zttp2 = zttp2;
    }

    public byte[] getZttp3() {
        return zttp3;
    }

    public void setZttp3(byte[] zttp3) {
        this.zttp3 = zttp3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ztmc=").append(ztmc);
        sb.append(", cppcm=").append(cppcm);
        sb.append(", cpmc=").append(cpmc);
        sb.append(", shfs=").append(shfs);
        sb.append(", shsl=").append(shsl);
        sb.append(", sldw=").append(sldw);
        sb.append(", shsj=").append(shsj);
        sb.append(", jlr=").append(jlr);
        sb.append(", bz=").append(bz);
        sb.append(", zttp1=").append(zttp1);
        sb.append(", zttp2=").append(zttp2);
        sb.append(", zttp3=").append(zttp3);
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
        DfdjProductGetDat other = (DfdjProductGetDat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getZtmc() == null ? other.getZtmc() == null : this.getZtmc().equals(other.getZtmc()))
            && (this.getCppcm() == null ? other.getCppcm() == null : this.getCppcm().equals(other.getCppcm()))
            && (this.getCpmc() == null ? other.getCpmc() == null : this.getCpmc().equals(other.getCpmc()))
            && (this.getShfs() == null ? other.getShfs() == null : this.getShfs().equals(other.getShfs()))
            && (this.getShsl() == null ? other.getShsl() == null : this.getShsl().equals(other.getShsl()))
            && (this.getSldw() == null ? other.getSldw() == null : this.getSldw().equals(other.getSldw()))
            && (this.getShsj() == null ? other.getShsj() == null : this.getShsj().equals(other.getShsj()))
            && (this.getJlr() == null ? other.getJlr() == null : this.getJlr().equals(other.getJlr()))
            && (this.getBz() == null ? other.getBz() == null : this.getBz().equals(other.getBz()))
            && (Arrays.equals(this.getZttp1(), other.getZttp1()))
            && (Arrays.equals(this.getZttp2(), other.getZttp2()))
            && (Arrays.equals(this.getZttp3(), other.getZttp3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getZtmc() == null) ? 0 : getZtmc().hashCode());
        result = prime * result + ((getCppcm() == null) ? 0 : getCppcm().hashCode());
        result = prime * result + ((getCpmc() == null) ? 0 : getCpmc().hashCode());
        result = prime * result + ((getShfs() == null) ? 0 : getShfs().hashCode());
        result = prime * result + ((getShsl() == null) ? 0 : getShsl().hashCode());
        result = prime * result + ((getSldw() == null) ? 0 : getSldw().hashCode());
        result = prime * result + ((getShsj() == null) ? 0 : getShsj().hashCode());
        result = prime * result + ((getJlr() == null) ? 0 : getJlr().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        result = prime * result + (Arrays.hashCode(getZttp1()));
        result = prime * result + (Arrays.hashCode(getZttp2()));
        result = prime * result + (Arrays.hashCode(getZttp3()));
        return result;
    }
}