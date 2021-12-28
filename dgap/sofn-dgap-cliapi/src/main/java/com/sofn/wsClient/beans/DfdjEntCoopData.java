package com.sofn.wsClient.beans;

import java.util.Arrays;
import java.util.Date;
import javax.validation.constraints.Size;

public class DfdjEntCoopData {
    @Size(max=64)
    private String id;

    @Size(max=60)
    private String account;

    @Size(max=6)
    private String accountResource;

    @Size(max=600)
    private String address;

    @Size(max=60)
    private String annualOutput;

    @Size(max=60)
    private String annualOutputS;

    @Size(max=60)
    private String annualOutputX;

    @Size(max=300)
    private String approveName;

    @Size(max=300)
    private String approveOpinion;

    @Size(max=1)
    private String approveStatus;

    @Size(max=500)
    private String approveTime;

    @Size(max=300)
    private String approveUserIdcode;

    @Size(max=60)
    private String area;

    @Size(max=200)
    private String businessLicenceimg;

    private Date businessOperationS;

    private Date businessOperationE;

    @Size(max=64)
    private String cardType;

    @Size(max=120)
    private String contactEmail;

    @Size(max=300)
    private String contactName;

    @Size(max=15)
    private String contactPhone;

    @Size(max=10)
    private String contactidcardtype;

    @Size(max=20)
    private String creditCode;

    @Size(max=1)
    private String delFlag;

    @Size(max=600)
    private String documentImages;

    @Size(max=300)
    private String enterpriseName;

    @Size(max=700)
    private String entityIndustry;

    @Size(max=200)
    private String entityIndustryName;

    @Size(max=700)
    private String entityProperty;

    @Size(max=200)
    private String entityPropertyName;

    @Size(max=700)
    private String entityScale;

    @Size(max=200)
    private String entityScaleName;

    @Size(max=700)
    private String entityType;

    @Size(max=200)
    private String entityTypeName;

    @Size(max=15)
    private String faxNumber;

    @Size(max=18)
    private String idcode;

    @Size(max=200)
    private String infoUnique;

    @Size(max=20)
    private String isLong;

    @Size(max=20)
    private String latitude;

    @Size(max=20)
    private String legalIdnumber;

    @Size(max=600)
    private String legalImages;

    @Size(max=300)
    private String legalName;

    @Size(max=15)
    private String legalPhone;

    @Size(max=10)
    private String legalidcardtype;

    @Size(max=20)
    private String longitude;

    @Size(max=200)
    private String organizationCertifi;

    @Size(max=200)
    private String organizationCode;

    @Size(max=300)
    private String realName;

    @Size(max=100)
    private String registerTime;

    @Size(max=18)
    private String spybLicType;

    @Size(max=100)
    private String unit;

    @Size(max=100)
    private String unitName;

    @Size(max=100)
    private String unitNameS;

    @Size(max=100)
    private String unitNameX;

    @Size(max=100)
    private String unitS;

    @Size(max=100)
    private String unitX;

    @Size(max=128)
    private String updateBy;

    private Date updateTime;

    @Size(max=64)
    private String userIdcode;

    private byte[] handIdcardimg;

    private byte[] negativeIdcardimg;

    private byte[] positiveIdcardeimg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAccountResource() {
        return accountResource;
    }

    public void setAccountResource(String accountResource) {
        this.accountResource = accountResource == null ? null : accountResource.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAnnualOutput() {
        return annualOutput;
    }

    public void setAnnualOutput(String annualOutput) {
        this.annualOutput = annualOutput == null ? null : annualOutput.trim();
    }

    public String getAnnualOutputS() {
        return annualOutputS;
    }

    public void setAnnualOutputS(String annualOutputS) {
        this.annualOutputS = annualOutputS == null ? null : annualOutputS.trim();
    }

    public String getAnnualOutputX() {
        return annualOutputX;
    }

    public void setAnnualOutputX(String annualOutputX) {
        this.annualOutputX = annualOutputX == null ? null : annualOutputX.trim();
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName == null ? null : approveName.trim();
    }

    public String getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion == null ? null : approveOpinion.trim();
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime == null ? null : approveTime.trim();
    }

    public String getApproveUserIdcode() {
        return approveUserIdcode;
    }

    public void setApproveUserIdcode(String approveUserIdcode) {
        this.approveUserIdcode = approveUserIdcode == null ? null : approveUserIdcode.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getBusinessLicenceimg() {
        return businessLicenceimg;
    }

    public void setBusinessLicenceimg(String businessLicenceimg) {
        this.businessLicenceimg = businessLicenceimg == null ? null : businessLicenceimg.trim();
    }

    public Date getBusinessOperationS() {
        return businessOperationS;
    }

    public void setBusinessOperationS(Date businessOperationS) {
        this.businessOperationS = businessOperationS;
    }

    public Date getBusinessOperationE() {
        return businessOperationE;
    }

    public void setBusinessOperationE(Date businessOperationE) {
        this.businessOperationE = businessOperationE;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactidcardtype() {
        return contactidcardtype;
    }

    public void setContactidcardtype(String contactidcardtype) {
        this.contactidcardtype = contactidcardtype == null ? null : contactidcardtype.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getDocumentImages() {
        return documentImages;
    }

    public void setDocumentImages(String documentImages) {
        this.documentImages = documentImages == null ? null : documentImages.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getEntityIndustry() {
        return entityIndustry;
    }

    public void setEntityIndustry(String entityIndustry) {
        this.entityIndustry = entityIndustry == null ? null : entityIndustry.trim();
    }

    public String getEntityIndustryName() {
        return entityIndustryName;
    }

    public void setEntityIndustryName(String entityIndustryName) {
        this.entityIndustryName = entityIndustryName == null ? null : entityIndustryName.trim();
    }

    public String getEntityProperty() {
        return entityProperty;
    }

    public void setEntityProperty(String entityProperty) {
        this.entityProperty = entityProperty == null ? null : entityProperty.trim();
    }

    public String getEntityPropertyName() {
        return entityPropertyName;
    }

    public void setEntityPropertyName(String entityPropertyName) {
        this.entityPropertyName = entityPropertyName == null ? null : entityPropertyName.trim();
    }

    public String getEntityScale() {
        return entityScale;
    }

    public void setEntityScale(String entityScale) {
        this.entityScale = entityScale == null ? null : entityScale.trim();
    }

    public String getEntityScaleName() {
        return entityScaleName;
    }

    public void setEntityScaleName(String entityScaleName) {
        this.entityScaleName = entityScaleName == null ? null : entityScaleName.trim();
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType == null ? null : entityType.trim();
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName == null ? null : entityTypeName.trim();
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber == null ? null : faxNumber.trim();
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode == null ? null : idcode.trim();
    }

    public String getInfoUnique() {
        return infoUnique;
    }

    public void setInfoUnique(String infoUnique) {
        this.infoUnique = infoUnique == null ? null : infoUnique.trim();
    }

    public String getIsLong() {
        return isLong;
    }

    public void setIsLong(String isLong) {
        this.isLong = isLong == null ? null : isLong.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLegalIdnumber() {
        return legalIdnumber;
    }

    public void setLegalIdnumber(String legalIdnumber) {
        this.legalIdnumber = legalIdnumber == null ? null : legalIdnumber.trim();
    }

    public String getLegalImages() {
        return legalImages;
    }

    public void setLegalImages(String legalImages) {
        this.legalImages = legalImages == null ? null : legalImages.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone == null ? null : legalPhone.trim();
    }

    public String getLegalidcardtype() {
        return legalidcardtype;
    }

    public void setLegalidcardtype(String legalidcardtype) {
        this.legalidcardtype = legalidcardtype == null ? null : legalidcardtype.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getOrganizationCertifi() {
        return organizationCertifi;
    }

    public void setOrganizationCertifi(String organizationCertifi) {
        this.organizationCertifi = organizationCertifi == null ? null : organizationCertifi.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime == null ? null : registerTime.trim();
    }

    public String getSpybLicType() {
        return spybLicType;
    }

    public void setSpybLicType(String spybLicType) {
        this.spybLicType = spybLicType == null ? null : spybLicType.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getUnitNameS() {
        return unitNameS;
    }

    public void setUnitNameS(String unitNameS) {
        this.unitNameS = unitNameS == null ? null : unitNameS.trim();
    }

    public String getUnitNameX() {
        return unitNameX;
    }

    public void setUnitNameX(String unitNameX) {
        this.unitNameX = unitNameX == null ? null : unitNameX.trim();
    }

    public String getUnitS() {
        return unitS;
    }

    public void setUnitS(String unitS) {
        this.unitS = unitS == null ? null : unitS.trim();
    }

    public String getUnitX() {
        return unitX;
    }

    public void setUnitX(String unitX) {
        this.unitX = unitX == null ? null : unitX.trim();
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

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode == null ? null : userIdcode.trim();
    }

    public byte[] getHandIdcardimg() {
        return handIdcardimg;
    }

    public void setHandIdcardimg(byte[] handIdcardimg) {
        this.handIdcardimg = handIdcardimg;
    }

    public byte[] getNegativeIdcardimg() {
        return negativeIdcardimg;
    }

    public void setNegativeIdcardimg(byte[] negativeIdcardimg) {
        this.negativeIdcardimg = negativeIdcardimg;
    }

    public byte[] getPositiveIdcardeimg() {
        return positiveIdcardeimg;
    }

    public void setPositiveIdcardeimg(byte[] positiveIdcardeimg) {
        this.positiveIdcardeimg = positiveIdcardeimg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", accountResource=").append(accountResource);
        sb.append(", address=").append(address);
        sb.append(", annualOutput=").append(annualOutput);
        sb.append(", annualOutputS=").append(annualOutputS);
        sb.append(", annualOutputX=").append(annualOutputX);
        sb.append(", approveName=").append(approveName);
        sb.append(", approveOpinion=").append(approveOpinion);
        sb.append(", approveStatus=").append(approveStatus);
        sb.append(", approveTime=").append(approveTime);
        sb.append(", approveUserIdcode=").append(approveUserIdcode);
        sb.append(", area=").append(area);
        sb.append(", businessLicenceimg=").append(businessLicenceimg);
        sb.append(", businessOperationS=").append(businessOperationS);
        sb.append(", businessOperationE=").append(businessOperationE);
        sb.append(", cardType=").append(cardType);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", contactidcardtype=").append(contactidcardtype);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", documentImages=").append(documentImages);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", entityIndustry=").append(entityIndustry);
        sb.append(", entityIndustryName=").append(entityIndustryName);
        sb.append(", entityProperty=").append(entityProperty);
        sb.append(", entityPropertyName=").append(entityPropertyName);
        sb.append(", entityScale=").append(entityScale);
        sb.append(", entityScaleName=").append(entityScaleName);
        sb.append(", entityType=").append(entityType);
        sb.append(", entityTypeName=").append(entityTypeName);
        sb.append(", faxNumber=").append(faxNumber);
        sb.append(", idcode=").append(idcode);
        sb.append(", infoUnique=").append(infoUnique);
        sb.append(", isLong=").append(isLong);
        sb.append(", latitude=").append(latitude);
        sb.append(", legalIdnumber=").append(legalIdnumber);
        sb.append(", legalImages=").append(legalImages);
        sb.append(", legalName=").append(legalName);
        sb.append(", legalPhone=").append(legalPhone);
        sb.append(", legalidcardtype=").append(legalidcardtype);
        sb.append(", longitude=").append(longitude);
        sb.append(", organizationCertifi=").append(organizationCertifi);
        sb.append(", organizationCode=").append(organizationCode);
        sb.append(", realName=").append(realName);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", spybLicType=").append(spybLicType);
        sb.append(", unit=").append(unit);
        sb.append(", unitName=").append(unitName);
        sb.append(", unitNameS=").append(unitNameS);
        sb.append(", unitNameX=").append(unitNameX);
        sb.append(", unitS=").append(unitS);
        sb.append(", unitX=").append(unitX);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userIdcode=").append(userIdcode);
        sb.append(", handIdcardimg=").append(handIdcardimg);
        sb.append(", negativeIdcardimg=").append(negativeIdcardimg);
        sb.append(", positiveIdcardeimg=").append(positiveIdcardeimg);
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
        DfdjEntCoopData other = (DfdjEntCoopData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getAccountResource() == null ? other.getAccountResource() == null : this.getAccountResource().equals(other.getAccountResource()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getAnnualOutput() == null ? other.getAnnualOutput() == null : this.getAnnualOutput().equals(other.getAnnualOutput()))
            && (this.getAnnualOutputS() == null ? other.getAnnualOutputS() == null : this.getAnnualOutputS().equals(other.getAnnualOutputS()))
            && (this.getAnnualOutputX() == null ? other.getAnnualOutputX() == null : this.getAnnualOutputX().equals(other.getAnnualOutputX()))
            && (this.getApproveName() == null ? other.getApproveName() == null : this.getApproveName().equals(other.getApproveName()))
            && (this.getApproveOpinion() == null ? other.getApproveOpinion() == null : this.getApproveOpinion().equals(other.getApproveOpinion()))
            && (this.getApproveStatus() == null ? other.getApproveStatus() == null : this.getApproveStatus().equals(other.getApproveStatus()))
            && (this.getApproveTime() == null ? other.getApproveTime() == null : this.getApproveTime().equals(other.getApproveTime()))
            && (this.getApproveUserIdcode() == null ? other.getApproveUserIdcode() == null : this.getApproveUserIdcode().equals(other.getApproveUserIdcode()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getBusinessLicenceimg() == null ? other.getBusinessLicenceimg() == null : this.getBusinessLicenceimg().equals(other.getBusinessLicenceimg()))
            && (this.getBusinessOperationS() == null ? other.getBusinessOperationS() == null : this.getBusinessOperationS().equals(other.getBusinessOperationS()))
            && (this.getBusinessOperationE() == null ? other.getBusinessOperationE() == null : this.getBusinessOperationE().equals(other.getBusinessOperationE()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getContactidcardtype() == null ? other.getContactidcardtype() == null : this.getContactidcardtype().equals(other.getContactidcardtype()))
            && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getDocumentImages() == null ? other.getDocumentImages() == null : this.getDocumentImages().equals(other.getDocumentImages()))
            && (this.getEnterpriseName() == null ? other.getEnterpriseName() == null : this.getEnterpriseName().equals(other.getEnterpriseName()))
            && (this.getEntityIndustry() == null ? other.getEntityIndustry() == null : this.getEntityIndustry().equals(other.getEntityIndustry()))
            && (this.getEntityIndustryName() == null ? other.getEntityIndustryName() == null : this.getEntityIndustryName().equals(other.getEntityIndustryName()))
            && (this.getEntityProperty() == null ? other.getEntityProperty() == null : this.getEntityProperty().equals(other.getEntityProperty()))
            && (this.getEntityPropertyName() == null ? other.getEntityPropertyName() == null : this.getEntityPropertyName().equals(other.getEntityPropertyName()))
            && (this.getEntityScale() == null ? other.getEntityScale() == null : this.getEntityScale().equals(other.getEntityScale()))
            && (this.getEntityScaleName() == null ? other.getEntityScaleName() == null : this.getEntityScaleName().equals(other.getEntityScaleName()))
            && (this.getEntityType() == null ? other.getEntityType() == null : this.getEntityType().equals(other.getEntityType()))
            && (this.getEntityTypeName() == null ? other.getEntityTypeName() == null : this.getEntityTypeName().equals(other.getEntityTypeName()))
            && (this.getFaxNumber() == null ? other.getFaxNumber() == null : this.getFaxNumber().equals(other.getFaxNumber()))
            && (this.getIdcode() == null ? other.getIdcode() == null : this.getIdcode().equals(other.getIdcode()))
            && (this.getInfoUnique() == null ? other.getInfoUnique() == null : this.getInfoUnique().equals(other.getInfoUnique()))
            && (this.getIsLong() == null ? other.getIsLong() == null : this.getIsLong().equals(other.getIsLong()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getLegalIdnumber() == null ? other.getLegalIdnumber() == null : this.getLegalIdnumber().equals(other.getLegalIdnumber()))
            && (this.getLegalImages() == null ? other.getLegalImages() == null : this.getLegalImages().equals(other.getLegalImages()))
            && (this.getLegalName() == null ? other.getLegalName() == null : this.getLegalName().equals(other.getLegalName()))
            && (this.getLegalPhone() == null ? other.getLegalPhone() == null : this.getLegalPhone().equals(other.getLegalPhone()))
            && (this.getLegalidcardtype() == null ? other.getLegalidcardtype() == null : this.getLegalidcardtype().equals(other.getLegalidcardtype()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getOrganizationCertifi() == null ? other.getOrganizationCertifi() == null : this.getOrganizationCertifi().equals(other.getOrganizationCertifi()))
            && (this.getOrganizationCode() == null ? other.getOrganizationCode() == null : this.getOrganizationCode().equals(other.getOrganizationCode()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getSpybLicType() == null ? other.getSpybLicType() == null : this.getSpybLicType().equals(other.getSpybLicType()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getUnitName() == null ? other.getUnitName() == null : this.getUnitName().equals(other.getUnitName()))
            && (this.getUnitNameS() == null ? other.getUnitNameS() == null : this.getUnitNameS().equals(other.getUnitNameS()))
            && (this.getUnitNameX() == null ? other.getUnitNameX() == null : this.getUnitNameX().equals(other.getUnitNameX()))
            && (this.getUnitS() == null ? other.getUnitS() == null : this.getUnitS().equals(other.getUnitS()))
            && (this.getUnitX() == null ? other.getUnitX() == null : this.getUnitX().equals(other.getUnitX()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUserIdcode() == null ? other.getUserIdcode() == null : this.getUserIdcode().equals(other.getUserIdcode()))
            && (Arrays.equals(this.getHandIdcardimg(), other.getHandIdcardimg()))
            && (Arrays.equals(this.getNegativeIdcardimg(), other.getNegativeIdcardimg()))
            && (Arrays.equals(this.getPositiveIdcardeimg(), other.getPositiveIdcardeimg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getAccountResource() == null) ? 0 : getAccountResource().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getAnnualOutput() == null) ? 0 : getAnnualOutput().hashCode());
        result = prime * result + ((getAnnualOutputS() == null) ? 0 : getAnnualOutputS().hashCode());
        result = prime * result + ((getAnnualOutputX() == null) ? 0 : getAnnualOutputX().hashCode());
        result = prime * result + ((getApproveName() == null) ? 0 : getApproveName().hashCode());
        result = prime * result + ((getApproveOpinion() == null) ? 0 : getApproveOpinion().hashCode());
        result = prime * result + ((getApproveStatus() == null) ? 0 : getApproveStatus().hashCode());
        result = prime * result + ((getApproveTime() == null) ? 0 : getApproveTime().hashCode());
        result = prime * result + ((getApproveUserIdcode() == null) ? 0 : getApproveUserIdcode().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getBusinessLicenceimg() == null) ? 0 : getBusinessLicenceimg().hashCode());
        result = prime * result + ((getBusinessOperationS() == null) ? 0 : getBusinessOperationS().hashCode());
        result = prime * result + ((getBusinessOperationE() == null) ? 0 : getBusinessOperationE().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getContactidcardtype() == null) ? 0 : getContactidcardtype().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getDocumentImages() == null) ? 0 : getDocumentImages().hashCode());
        result = prime * result + ((getEnterpriseName() == null) ? 0 : getEnterpriseName().hashCode());
        result = prime * result + ((getEntityIndustry() == null) ? 0 : getEntityIndustry().hashCode());
        result = prime * result + ((getEntityIndustryName() == null) ? 0 : getEntityIndustryName().hashCode());
        result = prime * result + ((getEntityProperty() == null) ? 0 : getEntityProperty().hashCode());
        result = prime * result + ((getEntityPropertyName() == null) ? 0 : getEntityPropertyName().hashCode());
        result = prime * result + ((getEntityScale() == null) ? 0 : getEntityScale().hashCode());
        result = prime * result + ((getEntityScaleName() == null) ? 0 : getEntityScaleName().hashCode());
        result = prime * result + ((getEntityType() == null) ? 0 : getEntityType().hashCode());
        result = prime * result + ((getEntityTypeName() == null) ? 0 : getEntityTypeName().hashCode());
        result = prime * result + ((getFaxNumber() == null) ? 0 : getFaxNumber().hashCode());
        result = prime * result + ((getIdcode() == null) ? 0 : getIdcode().hashCode());
        result = prime * result + ((getInfoUnique() == null) ? 0 : getInfoUnique().hashCode());
        result = prime * result + ((getIsLong() == null) ? 0 : getIsLong().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getLegalIdnumber() == null) ? 0 : getLegalIdnumber().hashCode());
        result = prime * result + ((getLegalImages() == null) ? 0 : getLegalImages().hashCode());
        result = prime * result + ((getLegalName() == null) ? 0 : getLegalName().hashCode());
        result = prime * result + ((getLegalPhone() == null) ? 0 : getLegalPhone().hashCode());
        result = prime * result + ((getLegalidcardtype() == null) ? 0 : getLegalidcardtype().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getOrganizationCertifi() == null) ? 0 : getOrganizationCertifi().hashCode());
        result = prime * result + ((getOrganizationCode() == null) ? 0 : getOrganizationCode().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getSpybLicType() == null) ? 0 : getSpybLicType().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getUnitName() == null) ? 0 : getUnitName().hashCode());
        result = prime * result + ((getUnitNameS() == null) ? 0 : getUnitNameS().hashCode());
        result = prime * result + ((getUnitNameX() == null) ? 0 : getUnitNameX().hashCode());
        result = prime * result + ((getUnitS() == null) ? 0 : getUnitS().hashCode());
        result = prime * result + ((getUnitX() == null) ? 0 : getUnitX().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUserIdcode() == null) ? 0 : getUserIdcode().hashCode());
        result = prime * result + (Arrays.hashCode(getHandIdcardimg()));
        result = prime * result + (Arrays.hashCode(getNegativeIdcardimg()));
        result = prime * result + (Arrays.hashCode(getPositiveIdcardeimg()));
        return result;
    }
}