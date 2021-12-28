package com.sofn.dgap.scheduler;

import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class Xinyouji {
    @Size(max=64)
    private String id;

    @Size(max=500)
    private String startdate;

    @Size(max=64)
    private String entBaseId;

    @Size(max=1000)
    private String entName;

    @Size(max=1000)
    private String orgCode;

    @Size(max=1000)
    private String entOldname;

    @Size(max=1000)
    private String entAddr;

    @Size(max=20)
    private String entPostCode;

    @Size(max=500)
    private String entTel;

    @Size(max=500)
    private String fax;

    @Size(max=500)
    private String entEmail;

    @Size(max=500)
    private String website;

    @Digits(integer=26,fraction=4)
    private BigDecimal regCap;

    @Digits(integer=30,fraction=0)
    private BigDecimal staffNum;

    @Digits(integer=30,fraction=0)
    private BigDecimal technicistNum;

    @Size(max=500)
    private String legRep;

    @Size(max=500)
    private String legRepMobile;

    @Size(max=500)
    private String legRepTel;

    @Size(max=500)
    private String linkman;

    @Size(max=500)
    private String linkmanMobile;

    @Size(max=500)
    private String linkmanTel;

    @Size(max=500)
    private String mailer;

    @Size(max=500)
    private String mailerMobile;

    @Size(max=500)
    private String mailerTel;

    @Size(max=1000)
    private String mailAddr;

    @Size(max=20)
    private String postCode;

    @Digits(integer=26,fraction=4)
    private BigDecimal sales;

    @Digits(integer=26,fraction=4)
    private BigDecimal exportAm;

    @Size(max=500)
    private String xzqhId;

    @Size(max=20)
    private String bcName;

    @Size(max=64)
    private String prodName;

    @Size(max=1000)
    private String prodDesc;

    @Size(max=20)
    private String certNo;

    @Size(max=1000)
    private String prodId;

    @Size(max=20)
    private String output;

    @Size(max=500)
    private String enddate;

    @Size(max=500)
    private String area;

    @Size(max=500)
    private String approveDate;

    @Size(max=10)
    private String licStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    public String getEntBaseId() {
        return entBaseId;
    }

    public void setEntBaseId(String entBaseId) {
        this.entBaseId = entBaseId == null ? null : entBaseId.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getEntOldname() {
        return entOldname;
    }

    public void setEntOldname(String entOldname) {
        this.entOldname = entOldname == null ? null : entOldname.trim();
    }

    public String getEntAddr() {
        return entAddr;
    }

    public void setEntAddr(String entAddr) {
        this.entAddr = entAddr == null ? null : entAddr.trim();
    }

    public String getEntPostCode() {
        return entPostCode;
    }

    public void setEntPostCode(String entPostCode) {
        this.entPostCode = entPostCode == null ? null : entPostCode.trim();
    }

    public String getEntTel() {
        return entTel;
    }

    public void setEntTel(String entTel) {
        this.entTel = entTel == null ? null : entTel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEntEmail() {
        return entEmail;
    }

    public void setEntEmail(String entEmail) {
        this.entEmail = entEmail == null ? null : entEmail.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public BigDecimal getRegCap() {
        return regCap;
    }

    public void setRegCap(BigDecimal regCap) {
        this.regCap = regCap;
    }

    public BigDecimal getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(BigDecimal staffNum) {
        this.staffNum = staffNum;
    }

    public BigDecimal getTechnicistNum() {
        return technicistNum;
    }

    public void setTechnicistNum(BigDecimal technicistNum) {
        this.technicistNum = technicistNum;
    }

    public String getLegRep() {
        return legRep;
    }

    public void setLegRep(String legRep) {
        this.legRep = legRep == null ? null : legRep.trim();
    }

    public String getLegRepMobile() {
        return legRepMobile;
    }

    public void setLegRepMobile(String legRepMobile) {
        this.legRepMobile = legRepMobile == null ? null : legRepMobile.trim();
    }

    public String getLegRepTel() {
        return legRepTel;
    }

    public void setLegRepTel(String legRepTel) {
        this.legRepTel = legRepTel == null ? null : legRepTel.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getLinkmanMobile() {
        return linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile == null ? null : linkmanMobile.trim();
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel == null ? null : linkmanTel.trim();
    }

    public String getMailer() {
        return mailer;
    }

    public void setMailer(String mailer) {
        this.mailer = mailer == null ? null : mailer.trim();
    }

    public String getMailerMobile() {
        return mailerMobile;
    }

    public void setMailerMobile(String mailerMobile) {
        this.mailerMobile = mailerMobile == null ? null : mailerMobile.trim();
    }

    public String getMailerTel() {
        return mailerTel;
    }

    public void setMailerTel(String mailerTel) {
        this.mailerTel = mailerTel == null ? null : mailerTel.trim();
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr == null ? null : mailAddr.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public BigDecimal getExportAm() {
        return exportAm;
    }

    public void setExportAm(BigDecimal exportAm) {
        this.exportAm = exportAm;
    }

    public String getXzqhId() {
        return xzqhId;
    }

    public void setXzqhId(String xzqhId) {
        this.xzqhId = xzqhId == null ? null : xzqhId.trim();
    }

    public String getBcName() {
        return bcName;
    }

    public void setBcName(String bcName) {
        this.bcName = bcName == null ? null : bcName.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc == null ? null : prodDesc.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate == null ? null : approveDate.trim();
    }

    public String getLicStatus() {
        return licStatus;
    }

    public void setLicStatus(String licStatus) {
        this.licStatus = licStatus == null ? null : licStatus.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", startdate=").append(startdate);
        sb.append(", entBaseId=").append(entBaseId);
        sb.append(", entName=").append(entName);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", entOldname=").append(entOldname);
        sb.append(", entAddr=").append(entAddr);
        sb.append(", entPostCode=").append(entPostCode);
        sb.append(", entTel=").append(entTel);
        sb.append(", fax=").append(fax);
        sb.append(", entEmail=").append(entEmail);
        sb.append(", website=").append(website);
        sb.append(", regCap=").append(regCap);
        sb.append(", staffNum=").append(staffNum);
        sb.append(", technicistNum=").append(technicistNum);
        sb.append(", legRep=").append(legRep);
        sb.append(", legRepMobile=").append(legRepMobile);
        sb.append(", legRepTel=").append(legRepTel);
        sb.append(", linkman=").append(linkman);
        sb.append(", linkmanMobile=").append(linkmanMobile);
        sb.append(", linkmanTel=").append(linkmanTel);
        sb.append(", mailer=").append(mailer);
        sb.append(", mailerMobile=").append(mailerMobile);
        sb.append(", mailerTel=").append(mailerTel);
        sb.append(", mailAddr=").append(mailAddr);
        sb.append(", postCode=").append(postCode);
        sb.append(", sales=").append(sales);
        sb.append(", exportAm=").append(exportAm);
        sb.append(", xzqhId=").append(xzqhId);
        sb.append(", bcName=").append(bcName);
        sb.append(", prodName=").append(prodName);
        sb.append(", prodDesc=").append(prodDesc);
        sb.append(", certNo=").append(certNo);
        sb.append(", prodId=").append(prodId);
        sb.append(", output=").append(output);
        sb.append(", enddate=").append(enddate);
        sb.append(", area=").append(area);
        sb.append(", approveDate=").append(approveDate);
        sb.append(", licStatus=").append(licStatus);
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
        Xinyouji other = (Xinyouji) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStartdate() == null ? other.getStartdate() == null : this.getStartdate().equals(other.getStartdate()))
            && (this.getEntBaseId() == null ? other.getEntBaseId() == null : this.getEntBaseId().equals(other.getEntBaseId()))
            && (this.getEntName() == null ? other.getEntName() == null : this.getEntName().equals(other.getEntName()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getEntOldname() == null ? other.getEntOldname() == null : this.getEntOldname().equals(other.getEntOldname()))
            && (this.getEntAddr() == null ? other.getEntAddr() == null : this.getEntAddr().equals(other.getEntAddr()))
            && (this.getEntPostCode() == null ? other.getEntPostCode() == null : this.getEntPostCode().equals(other.getEntPostCode()))
            && (this.getEntTel() == null ? other.getEntTel() == null : this.getEntTel().equals(other.getEntTel()))
            && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
            && (this.getEntEmail() == null ? other.getEntEmail() == null : this.getEntEmail().equals(other.getEntEmail()))
            && (this.getWebsite() == null ? other.getWebsite() == null : this.getWebsite().equals(other.getWebsite()))
            && (this.getRegCap() == null ? other.getRegCap() == null : this.getRegCap().equals(other.getRegCap()))
            && (this.getStaffNum() == null ? other.getStaffNum() == null : this.getStaffNum().equals(other.getStaffNum()))
            && (this.getTechnicistNum() == null ? other.getTechnicistNum() == null : this.getTechnicistNum().equals(other.getTechnicistNum()))
            && (this.getLegRep() == null ? other.getLegRep() == null : this.getLegRep().equals(other.getLegRep()))
            && (this.getLegRepMobile() == null ? other.getLegRepMobile() == null : this.getLegRepMobile().equals(other.getLegRepMobile()))
            && (this.getLegRepTel() == null ? other.getLegRepTel() == null : this.getLegRepTel().equals(other.getLegRepTel()))
            && (this.getLinkman() == null ? other.getLinkman() == null : this.getLinkman().equals(other.getLinkman()))
            && (this.getLinkmanMobile() == null ? other.getLinkmanMobile() == null : this.getLinkmanMobile().equals(other.getLinkmanMobile()))
            && (this.getLinkmanTel() == null ? other.getLinkmanTel() == null : this.getLinkmanTel().equals(other.getLinkmanTel()))
            && (this.getMailer() == null ? other.getMailer() == null : this.getMailer().equals(other.getMailer()))
            && (this.getMailerMobile() == null ? other.getMailerMobile() == null : this.getMailerMobile().equals(other.getMailerMobile()))
            && (this.getMailerTel() == null ? other.getMailerTel() == null : this.getMailerTel().equals(other.getMailerTel()))
            && (this.getMailAddr() == null ? other.getMailAddr() == null : this.getMailAddr().equals(other.getMailAddr()))
            && (this.getPostCode() == null ? other.getPostCode() == null : this.getPostCode().equals(other.getPostCode()))
            && (this.getSales() == null ? other.getSales() == null : this.getSales().equals(other.getSales()))
            && (this.getExportAm() == null ? other.getExportAm() == null : this.getExportAm().equals(other.getExportAm()))
            && (this.getXzqhId() == null ? other.getXzqhId() == null : this.getXzqhId().equals(other.getXzqhId()))
            && (this.getBcName() == null ? other.getBcName() == null : this.getBcName().equals(other.getBcName()))
            && (this.getProdName() == null ? other.getProdName() == null : this.getProdName().equals(other.getProdName()))
            && (this.getProdDesc() == null ? other.getProdDesc() == null : this.getProdDesc().equals(other.getProdDesc()))
            && (this.getCertNo() == null ? other.getCertNo() == null : this.getCertNo().equals(other.getCertNo()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getOutput() == null ? other.getOutput() == null : this.getOutput().equals(other.getOutput()))
            && (this.getEnddate() == null ? other.getEnddate() == null : this.getEnddate().equals(other.getEnddate()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getApproveDate() == null ? other.getApproveDate() == null : this.getApproveDate().equals(other.getApproveDate()))
            && (this.getLicStatus() == null ? other.getLicStatus() == null : this.getLicStatus().equals(other.getLicStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStartdate() == null) ? 0 : getStartdate().hashCode());
        result = prime * result + ((getEntBaseId() == null) ? 0 : getEntBaseId().hashCode());
        result = prime * result + ((getEntName() == null) ? 0 : getEntName().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getEntOldname() == null) ? 0 : getEntOldname().hashCode());
        result = prime * result + ((getEntAddr() == null) ? 0 : getEntAddr().hashCode());
        result = prime * result + ((getEntPostCode() == null) ? 0 : getEntPostCode().hashCode());
        result = prime * result + ((getEntTel() == null) ? 0 : getEntTel().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
        result = prime * result + ((getEntEmail() == null) ? 0 : getEntEmail().hashCode());
        result = prime * result + ((getWebsite() == null) ? 0 : getWebsite().hashCode());
        result = prime * result + ((getRegCap() == null) ? 0 : getRegCap().hashCode());
        result = prime * result + ((getStaffNum() == null) ? 0 : getStaffNum().hashCode());
        result = prime * result + ((getTechnicistNum() == null) ? 0 : getTechnicistNum().hashCode());
        result = prime * result + ((getLegRep() == null) ? 0 : getLegRep().hashCode());
        result = prime * result + ((getLegRepMobile() == null) ? 0 : getLegRepMobile().hashCode());
        result = prime * result + ((getLegRepTel() == null) ? 0 : getLegRepTel().hashCode());
        result = prime * result + ((getLinkman() == null) ? 0 : getLinkman().hashCode());
        result = prime * result + ((getLinkmanMobile() == null) ? 0 : getLinkmanMobile().hashCode());
        result = prime * result + ((getLinkmanTel() == null) ? 0 : getLinkmanTel().hashCode());
        result = prime * result + ((getMailer() == null) ? 0 : getMailer().hashCode());
        result = prime * result + ((getMailerMobile() == null) ? 0 : getMailerMobile().hashCode());
        result = prime * result + ((getMailerTel() == null) ? 0 : getMailerTel().hashCode());
        result = prime * result + ((getMailAddr() == null) ? 0 : getMailAddr().hashCode());
        result = prime * result + ((getPostCode() == null) ? 0 : getPostCode().hashCode());
        result = prime * result + ((getSales() == null) ? 0 : getSales().hashCode());
        result = prime * result + ((getExportAm() == null) ? 0 : getExportAm().hashCode());
        result = prime * result + ((getXzqhId() == null) ? 0 : getXzqhId().hashCode());
        result = prime * result + ((getBcName() == null) ? 0 : getBcName().hashCode());
        result = prime * result + ((getProdName() == null) ? 0 : getProdName().hashCode());
        result = prime * result + ((getProdDesc() == null) ? 0 : getProdDesc().hashCode());
        result = prime * result + ((getCertNo() == null) ? 0 : getCertNo().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getOutput() == null) ? 0 : getOutput().hashCode());
        result = prime * result + ((getEnddate() == null) ? 0 : getEnddate().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getApproveDate() == null) ? 0 : getApproveDate().hashCode());
        result = prime * result + ((getLicStatus() == null) ? 0 : getLicStatus().hashCode());
        return result;
    }
}