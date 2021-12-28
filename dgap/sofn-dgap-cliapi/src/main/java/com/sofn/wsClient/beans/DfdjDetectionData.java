package com.sofn.wsClient.beans;

import java.util.Arrays;
import javax.validation.constraints.Size;

public class DfdjDetectionData {
    @Size(max=64)
    private String id;

    @Size(max=500)
    private String ztmc;

    @Size(max=500)
    private String cppcm;

    @Size(max=500)
    private String cpmc;

    @Size(max=20)
    private String jcfs;

    @Size(max=500)
    private String jcdw;

    @Size(max=20)
    private String jcjl;

    @Size(max=500)
    private String jcsj;

    @Size(max=20)
    private String jlr;

    @Size(max=500)
    private String bz;

    private byte[] zttp1;

    private byte[] zttp2;

    private byte[] zttp3;

    private byte[] jcbg;

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

    public String getJcfs() {
        return jcfs;
    }

    public void setJcfs(String jcfs) {
        this.jcfs = jcfs == null ? null : jcfs.trim();
    }

    public String getJcdw() {
        return jcdw;
    }

    public void setJcdw(String jcdw) {
        this.jcdw = jcdw == null ? null : jcdw.trim();
    }

    public String getJcjl() {
        return jcjl;
    }

    public void setJcjl(String jcjl) {
        this.jcjl = jcjl == null ? null : jcjl.trim();
    }

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
        this.jcsj = jcsj == null ? null : jcsj.trim();
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
        sb.append(", ztmc=").append(ztmc);
        sb.append(", cppcm=").append(cppcm);
        sb.append(", cpmc=").append(cpmc);
        sb.append(", jcfs=").append(jcfs);
        sb.append(", jcdw=").append(jcdw);
        sb.append(", jcjl=").append(jcjl);
        sb.append(", jcsj=").append(jcsj);
        sb.append(", jlr=").append(jlr);
        sb.append(", bz=").append(bz);
        sb.append(", zttp1=").append(zttp1);
        sb.append(", zttp2=").append(zttp2);
        sb.append(", zttp3=").append(zttp3);
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
        DfdjDetectionData other = (DfdjDetectionData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getZtmc() == null ? other.getZtmc() == null : this.getZtmc().equals(other.getZtmc()))
            && (this.getCppcm() == null ? other.getCppcm() == null : this.getCppcm().equals(other.getCppcm()))
            && (this.getCpmc() == null ? other.getCpmc() == null : this.getCpmc().equals(other.getCpmc()))
            && (this.getJcfs() == null ? other.getJcfs() == null : this.getJcfs().equals(other.getJcfs()))
            && (this.getJcdw() == null ? other.getJcdw() == null : this.getJcdw().equals(other.getJcdw()))
            && (this.getJcjl() == null ? other.getJcjl() == null : this.getJcjl().equals(other.getJcjl()))
            && (this.getJcsj() == null ? other.getJcsj() == null : this.getJcsj().equals(other.getJcsj()))
            && (this.getJlr() == null ? other.getJlr() == null : this.getJlr().equals(other.getJlr()))
            && (this.getBz() == null ? other.getBz() == null : this.getBz().equals(other.getBz()))
            && (Arrays.equals(this.getZttp1(), other.getZttp1()))
            && (Arrays.equals(this.getZttp2(), other.getZttp2()))
            && (Arrays.equals(this.getZttp3(), other.getZttp3()))
            && (Arrays.equals(this.getJcbg(), other.getJcbg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getZtmc() == null) ? 0 : getZtmc().hashCode());
        result = prime * result + ((getCppcm() == null) ? 0 : getCppcm().hashCode());
        result = prime * result + ((getCpmc() == null) ? 0 : getCpmc().hashCode());
        result = prime * result + ((getJcfs() == null) ? 0 : getJcfs().hashCode());
        result = prime * result + ((getJcdw() == null) ? 0 : getJcdw().hashCode());
        result = prime * result + ((getJcjl() == null) ? 0 : getJcjl().hashCode());
        result = prime * result + ((getJcsj() == null) ? 0 : getJcsj().hashCode());
        result = prime * result + ((getJlr() == null) ? 0 : getJlr().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        result = prime * result + (Arrays.hashCode(getZttp1()));
        result = prime * result + (Arrays.hashCode(getZttp2()));
        result = prime * result + (Arrays.hashCode(getZttp3()));
        result = prime * result + (Arrays.hashCode(getJcbg()));
        return result;
    }
}