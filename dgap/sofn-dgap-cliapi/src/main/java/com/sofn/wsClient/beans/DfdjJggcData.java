package com.sofn.wsClient.beans;

import java.math.BigDecimal;
import java.util.Arrays;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class DfdjJggcData {
    @Size(max=64)
    private String id;

    @Size(max=500)
    private String ztmc;

    @Size(max=500)
    private String cppcm;

    @Size(max=500)
    private String jgfs;

    @Size(max=500)
    private String jgnr;

    @Digits(integer=34,fraction=4)
    private BigDecimal jgsl;

    @Size(max=20)
    private String sldw;

    @Size(max=500)
    private String jgsj;

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

    public String getJgfs() {
        return jgfs;
    }

    public void setJgfs(String jgfs) {
        this.jgfs = jgfs == null ? null : jgfs.trim();
    }

    public String getJgnr() {
        return jgnr;
    }

    public void setJgnr(String jgnr) {
        this.jgnr = jgnr == null ? null : jgnr.trim();
    }

    public BigDecimal getJgsl() {
        return jgsl;
    }

    public void setJgsl(BigDecimal jgsl) {
        this.jgsl = jgsl;
    }

    public String getSldw() {
        return sldw;
    }

    public void setSldw(String sldw) {
        this.sldw = sldw == null ? null : sldw.trim();
    }

    public String getJgsj() {
        return jgsj;
    }

    public void setJgsj(String jgsj) {
        this.jgsj = jgsj == null ? null : jgsj.trim();
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
        sb.append(", jgfs=").append(jgfs);
        sb.append(", jgnr=").append(jgnr);
        sb.append(", jgsl=").append(jgsl);
        sb.append(", sldw=").append(sldw);
        sb.append(", jgsj=").append(jgsj);
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
        DfdjJggcData other = (DfdjJggcData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getZtmc() == null ? other.getZtmc() == null : this.getZtmc().equals(other.getZtmc()))
            && (this.getCppcm() == null ? other.getCppcm() == null : this.getCppcm().equals(other.getCppcm()))
            && (this.getJgfs() == null ? other.getJgfs() == null : this.getJgfs().equals(other.getJgfs()))
            && (this.getJgnr() == null ? other.getJgnr() == null : this.getJgnr().equals(other.getJgnr()))
            && (this.getJgsl() == null ? other.getJgsl() == null : this.getJgsl().equals(other.getJgsl()))
            && (this.getSldw() == null ? other.getSldw() == null : this.getSldw().equals(other.getSldw()))
            && (this.getJgsj() == null ? other.getJgsj() == null : this.getJgsj().equals(other.getJgsj()))
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
        result = prime * result + ((getJgfs() == null) ? 0 : getJgfs().hashCode());
        result = prime * result + ((getJgnr() == null) ? 0 : getJgnr().hashCode());
        result = prime * result + ((getJgsl() == null) ? 0 : getJgsl().hashCode());
        result = prime * result + ((getSldw() == null) ? 0 : getSldw().hashCode());
        result = prime * result + ((getJgsj() == null) ? 0 : getJgsj().hashCode());
        result = prime * result + ((getJlr() == null) ? 0 : getJlr().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        result = prime * result + (Arrays.hashCode(getZttp1()));
        result = prime * result + (Arrays.hashCode(getZttp2()));
        result = prime * result + (Arrays.hashCode(getZttp3()));
        return result;
    }
}