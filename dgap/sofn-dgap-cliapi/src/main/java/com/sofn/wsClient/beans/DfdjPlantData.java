package com.sofn.wsClient.beans;

import java.math.BigDecimal;
import java.util.Arrays;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class DfdjPlantData {
    @Size(max=64)
    private String id;

    @Size(max=500)
    private String ztmc;

    @Size(max=500)
    private String cppcm;

    @Size(max=500)
    private String hymc;

    @Size(max=500)
    private String jdmc;

    @Size(max=500)
    private String zwmc;

    @Size(max=20)
    private String trplx;

    @Size(max=500)
    private String trpmc;

    @Digits(integer=34,fraction=4)
    private BigDecimal symj;

    @Size(max=20)
    private String mjdw;

    @Digits(integer=34,fraction=4)
    private BigDecimal syl;

    @Size(max=20)
    private String dw;

    @Size(max=500)
    private String syff;

    @Size(max=500)
    private String sysj;

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

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc == null ? null : zwmc.trim();
    }

    public String getTrplx() {
        return trplx;
    }

    public void setTrplx(String trplx) {
        this.trplx = trplx == null ? null : trplx.trim();
    }

    public String getTrpmc() {
        return trpmc;
    }

    public void setTrpmc(String trpmc) {
        this.trpmc = trpmc == null ? null : trpmc.trim();
    }

    public BigDecimal getSymj() {
        return symj;
    }

    public void setSymj(BigDecimal symj) {
        this.symj = symj;
    }

    public String getMjdw() {
        return mjdw;
    }

    public void setMjdw(String mjdw) {
        this.mjdw = mjdw == null ? null : mjdw.trim();
    }

    public BigDecimal getSyl() {
        return syl;
    }

    public void setSyl(BigDecimal syl) {
        this.syl = syl;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw == null ? null : dw.trim();
    }

    public String getSyff() {
        return syff;
    }

    public void setSyff(String syff) {
        this.syff = syff == null ? null : syff.trim();
    }

    public String getSysj() {
        return sysj;
    }

    public void setSysj(String sysj) {
        this.sysj = sysj == null ? null : sysj.trim();
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
        sb.append(", hymc=").append(hymc);
        sb.append(", jdmc=").append(jdmc);
        sb.append(", zwmc=").append(zwmc);
        sb.append(", trplx=").append(trplx);
        sb.append(", trpmc=").append(trpmc);
        sb.append(", symj=").append(symj);
        sb.append(", mjdw=").append(mjdw);
        sb.append(", syl=").append(syl);
        sb.append(", dw=").append(dw);
        sb.append(", syff=").append(syff);
        sb.append(", sysj=").append(sysj);
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
        DfdjPlantData other = (DfdjPlantData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getZtmc() == null ? other.getZtmc() == null : this.getZtmc().equals(other.getZtmc()))
            && (this.getCppcm() == null ? other.getCppcm() == null : this.getCppcm().equals(other.getCppcm()))
            && (this.getHymc() == null ? other.getHymc() == null : this.getHymc().equals(other.getHymc()))
            && (this.getJdmc() == null ? other.getJdmc() == null : this.getJdmc().equals(other.getJdmc()))
            && (this.getZwmc() == null ? other.getZwmc() == null : this.getZwmc().equals(other.getZwmc()))
            && (this.getTrplx() == null ? other.getTrplx() == null : this.getTrplx().equals(other.getTrplx()))
            && (this.getTrpmc() == null ? other.getTrpmc() == null : this.getTrpmc().equals(other.getTrpmc()))
            && (this.getSymj() == null ? other.getSymj() == null : this.getSymj().equals(other.getSymj()))
            && (this.getMjdw() == null ? other.getMjdw() == null : this.getMjdw().equals(other.getMjdw()))
            && (this.getSyl() == null ? other.getSyl() == null : this.getSyl().equals(other.getSyl()))
            && (this.getDw() == null ? other.getDw() == null : this.getDw().equals(other.getDw()))
            && (this.getSyff() == null ? other.getSyff() == null : this.getSyff().equals(other.getSyff()))
            && (this.getSysj() == null ? other.getSysj() == null : this.getSysj().equals(other.getSysj()))
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
        result = prime * result + ((getHymc() == null) ? 0 : getHymc().hashCode());
        result = prime * result + ((getJdmc() == null) ? 0 : getJdmc().hashCode());
        result = prime * result + ((getZwmc() == null) ? 0 : getZwmc().hashCode());
        result = prime * result + ((getTrplx() == null) ? 0 : getTrplx().hashCode());
        result = prime * result + ((getTrpmc() == null) ? 0 : getTrpmc().hashCode());
        result = prime * result + ((getSymj() == null) ? 0 : getSymj().hashCode());
        result = prime * result + ((getMjdw() == null) ? 0 : getMjdw().hashCode());
        result = prime * result + ((getSyl() == null) ? 0 : getSyl().hashCode());
        result = prime * result + ((getDw() == null) ? 0 : getDw().hashCode());
        result = prime * result + ((getSyff() == null) ? 0 : getSyff().hashCode());
        result = prime * result + ((getSysj() == null) ? 0 : getSysj().hashCode());
        result = prime * result + ((getJlr() == null) ? 0 : getJlr().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        result = prime * result + (Arrays.hashCode(getZttp1()));
        result = prime * result + (Arrays.hashCode(getZttp2()));
        result = prime * result + (Arrays.hashCode(getZttp3()));
        return result;
    }
}