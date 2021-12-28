package com.sofn.dgap.scheduler;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class Xinlvse {
    @Size(max=64)
    private String id;

    @Size(max=200)
    private String contactPersonPhone;

    @Size(max=200)
    private String grOrgName;

    @Size(max=200)
    private String legalPerson;

    @Size(max=200)
    private String legalPersonMobile;

    @Size(max=200)
    private String proNameCn;

    @Size(max=200)
    private String licNo;

    @Size(max=200)
    private String entInfoCode;

    @Digits(integer=34,fraction=4)
    private BigDecimal actAnnProSca;

    private Date licDeadlineBeg;

    private Date licDeadlineEnd;

    @Size(max=200)
    private String issueOrg;

    private Date issueDate;

    @Size(max=500)
    private String licPic;

    @Size(max=200)
    private String licStatus;

    private Date licInvalidDate;

    @Size(max=200)
    private String busiId;

    @Size(max=200)
    private String entNameCn;

    @Size(max=200)
    private String unifiedInfo;

    @Size(max=200)
    private String entRegAdd;

    @Size(max=200)
    private String entFax;

    @Size(max=200)
    private String entEmail;

    @Size(max=200)
    private String entCommAdd;

    @Size(max=200)
    private String contactPerson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone == null ? null : contactPersonPhone.trim();
    }

    public String getGrOrgName() {
        return grOrgName;
    }

    public void setGrOrgName(String grOrgName) {
        this.grOrgName = grOrgName == null ? null : grOrgName.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile == null ? null : legalPersonMobile.trim();
    }

    public String getProNameCn() {
        return proNameCn;
    }

    public void setProNameCn(String proNameCn) {
        this.proNameCn = proNameCn == null ? null : proNameCn.trim();
    }

    public String getLicNo() {
        return licNo;
    }

    public void setLicNo(String licNo) {
        this.licNo = licNo == null ? null : licNo.trim();
    }

    public String getEntInfoCode() {
        return entInfoCode;
    }

    public void setEntInfoCode(String entInfoCode) {
        this.entInfoCode = entInfoCode == null ? null : entInfoCode.trim();
    }

    public BigDecimal getActAnnProSca() {
        return actAnnProSca;
    }

    public void setActAnnProSca(BigDecimal actAnnProSca) {
        this.actAnnProSca = actAnnProSca;
    }

    public Date getLicDeadlineBeg() {
        return licDeadlineBeg;
    }

    public void setLicDeadlineBeg(Date licDeadlineBeg) {
        this.licDeadlineBeg = licDeadlineBeg;
    }

    public Date getLicDeadlineEnd() {
        return licDeadlineEnd;
    }

    public void setLicDeadlineEnd(Date licDeadlineEnd) {
        this.licDeadlineEnd = licDeadlineEnd;
    }

    public String getIssueOrg() {
        return issueOrg;
    }

    public void setIssueOrg(String issueOrg) {
        this.issueOrg = issueOrg == null ? null : issueOrg.trim();
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getLicPic() {
        return licPic;
    }

    public void setLicPic(String licPic) {
        this.licPic = licPic == null ? null : licPic.trim();
    }

    public String getLicStatus() {
        return licStatus;
    }

    public void setLicStatus(String licStatus) {
        this.licStatus = licStatus == null ? null : licStatus.trim();
    }

    public Date getLicInvalidDate() {
        return licInvalidDate;
    }

    public void setLicInvalidDate(Date licInvalidDate) {
        this.licInvalidDate = licInvalidDate;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    public String getEntNameCn() {
        return entNameCn;
    }

    public void setEntNameCn(String entNameCn) {
        this.entNameCn = entNameCn == null ? null : entNameCn.trim();
    }

    public String getUnifiedInfo() {
        return unifiedInfo;
    }

    public void setUnifiedInfo(String unifiedInfo) {
        this.unifiedInfo = unifiedInfo == null ? null : unifiedInfo.trim();
    }

    public String getEntRegAdd() {
        return entRegAdd;
    }

    public void setEntRegAdd(String entRegAdd) {
        this.entRegAdd = entRegAdd == null ? null : entRegAdd.trim();
    }

    public String getEntFax() {
        return entFax;
    }

    public void setEntFax(String entFax) {
        this.entFax = entFax == null ? null : entFax.trim();
    }

    public String getEntEmail() {
        return entEmail;
    }

    public void setEntEmail(String entEmail) {
        this.entEmail = entEmail == null ? null : entEmail.trim();
    }

    public String getEntCommAdd() {
        return entCommAdd;
    }

    public void setEntCommAdd(String entCommAdd) {
        this.entCommAdd = entCommAdd == null ? null : entCommAdd.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", contactPersonPhone=").append(contactPersonPhone);
        sb.append(", grOrgName=").append(grOrgName);
        sb.append(", legalPerson=").append(legalPerson);
        sb.append(", legalPersonMobile=").append(legalPersonMobile);
        sb.append(", proNameCn=").append(proNameCn);
        sb.append(", licNo=").append(licNo);
        sb.append(", entInfoCode=").append(entInfoCode);
        sb.append(", actAnnProSca=").append(actAnnProSca);
        sb.append(", licDeadlineBeg=").append(licDeadlineBeg);
        sb.append(", licDeadlineEnd=").append(licDeadlineEnd);
        sb.append(", issueOrg=").append(issueOrg);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", licPic=").append(licPic);
        sb.append(", licStatus=").append(licStatus);
        sb.append(", licInvalidDate=").append(licInvalidDate);
        sb.append(", busiId=").append(busiId);
        sb.append(", entNameCn=").append(entNameCn);
        sb.append(", unifiedInfo=").append(unifiedInfo);
        sb.append(", entRegAdd=").append(entRegAdd);
        sb.append(", entFax=").append(entFax);
        sb.append(", entEmail=").append(entEmail);
        sb.append(", entCommAdd=").append(entCommAdd);
        sb.append(", contactPerson=").append(contactPerson);
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
        Xinlvse other = (Xinlvse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContactPersonPhone() == null ? other.getContactPersonPhone() == null : this.getContactPersonPhone().equals(other.getContactPersonPhone()))
            && (this.getGrOrgName() == null ? other.getGrOrgName() == null : this.getGrOrgName().equals(other.getGrOrgName()))
            && (this.getLegalPerson() == null ? other.getLegalPerson() == null : this.getLegalPerson().equals(other.getLegalPerson()))
            && (this.getLegalPersonMobile() == null ? other.getLegalPersonMobile() == null : this.getLegalPersonMobile().equals(other.getLegalPersonMobile()))
            && (this.getProNameCn() == null ? other.getProNameCn() == null : this.getProNameCn().equals(other.getProNameCn()))
            && (this.getLicNo() == null ? other.getLicNo() == null : this.getLicNo().equals(other.getLicNo()))
            && (this.getEntInfoCode() == null ? other.getEntInfoCode() == null : this.getEntInfoCode().equals(other.getEntInfoCode()))
            && (this.getActAnnProSca() == null ? other.getActAnnProSca() == null : this.getActAnnProSca().equals(other.getActAnnProSca()))
            && (this.getLicDeadlineBeg() == null ? other.getLicDeadlineBeg() == null : this.getLicDeadlineBeg().equals(other.getLicDeadlineBeg()))
            && (this.getLicDeadlineEnd() == null ? other.getLicDeadlineEnd() == null : this.getLicDeadlineEnd().equals(other.getLicDeadlineEnd()))
            && (this.getIssueOrg() == null ? other.getIssueOrg() == null : this.getIssueOrg().equals(other.getIssueOrg()))
            && (this.getIssueDate() == null ? other.getIssueDate() == null : this.getIssueDate().equals(other.getIssueDate()))
            && (this.getLicPic() == null ? other.getLicPic() == null : this.getLicPic().equals(other.getLicPic()))
            && (this.getLicStatus() == null ? other.getLicStatus() == null : this.getLicStatus().equals(other.getLicStatus()))
            && (this.getLicInvalidDate() == null ? other.getLicInvalidDate() == null : this.getLicInvalidDate().equals(other.getLicInvalidDate()))
            && (this.getBusiId() == null ? other.getBusiId() == null : this.getBusiId().equals(other.getBusiId()))
            && (this.getEntNameCn() == null ? other.getEntNameCn() == null : this.getEntNameCn().equals(other.getEntNameCn()))
            && (this.getUnifiedInfo() == null ? other.getUnifiedInfo() == null : this.getUnifiedInfo().equals(other.getUnifiedInfo()))
            && (this.getEntRegAdd() == null ? other.getEntRegAdd() == null : this.getEntRegAdd().equals(other.getEntRegAdd()))
            && (this.getEntFax() == null ? other.getEntFax() == null : this.getEntFax().equals(other.getEntFax()))
            && (this.getEntEmail() == null ? other.getEntEmail() == null : this.getEntEmail().equals(other.getEntEmail()))
            && (this.getEntCommAdd() == null ? other.getEntCommAdd() == null : this.getEntCommAdd().equals(other.getEntCommAdd()))
            && (this.getContactPerson() == null ? other.getContactPerson() == null : this.getContactPerson().equals(other.getContactPerson()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContactPersonPhone() == null) ? 0 : getContactPersonPhone().hashCode());
        result = prime * result + ((getGrOrgName() == null) ? 0 : getGrOrgName().hashCode());
        result = prime * result + ((getLegalPerson() == null) ? 0 : getLegalPerson().hashCode());
        result = prime * result + ((getLegalPersonMobile() == null) ? 0 : getLegalPersonMobile().hashCode());
        result = prime * result + ((getProNameCn() == null) ? 0 : getProNameCn().hashCode());
        result = prime * result + ((getLicNo() == null) ? 0 : getLicNo().hashCode());
        result = prime * result + ((getEntInfoCode() == null) ? 0 : getEntInfoCode().hashCode());
        result = prime * result + ((getActAnnProSca() == null) ? 0 : getActAnnProSca().hashCode());
        result = prime * result + ((getLicDeadlineBeg() == null) ? 0 : getLicDeadlineBeg().hashCode());
        result = prime * result + ((getLicDeadlineEnd() == null) ? 0 : getLicDeadlineEnd().hashCode());
        result = prime * result + ((getIssueOrg() == null) ? 0 : getIssueOrg().hashCode());
        result = prime * result + ((getIssueDate() == null) ? 0 : getIssueDate().hashCode());
        result = prime * result + ((getLicPic() == null) ? 0 : getLicPic().hashCode());
        result = prime * result + ((getLicStatus() == null) ? 0 : getLicStatus().hashCode());
        result = prime * result + ((getLicInvalidDate() == null) ? 0 : getLicInvalidDate().hashCode());
        result = prime * result + ((getBusiId() == null) ? 0 : getBusiId().hashCode());
        result = prime * result + ((getEntNameCn() == null) ? 0 : getEntNameCn().hashCode());
        result = prime * result + ((getUnifiedInfo() == null) ? 0 : getUnifiedInfo().hashCode());
        result = prime * result + ((getEntRegAdd() == null) ? 0 : getEntRegAdd().hashCode());
        result = prime * result + ((getEntFax() == null) ? 0 : getEntFax().hashCode());
        result = prime * result + ((getEntEmail() == null) ? 0 : getEntEmail().hashCode());
        result = prime * result + ((getEntCommAdd() == null) ? 0 : getEntCommAdd().hashCode());
        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());
        return result;
    }
}