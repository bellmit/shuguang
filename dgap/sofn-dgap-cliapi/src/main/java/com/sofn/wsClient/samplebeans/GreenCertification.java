package com.sofn.wsClient.samplebeans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class GreenCertification {
    @NotNull
    private String id;

    @Size(min = 1,max = 100)
    private String productName;

    @Digits(integer = 33, fraction = 0)
    private BigDecimal approvedAmount;

    private Date certificationDate;

    @Size(max = 4000)
    private byte[] picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Date getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(Date certificationDate) {
        this.certificationDate = certificationDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", approvedAmount=").append(approvedAmount);
        sb.append(", certificationDate=").append(certificationDate);
        sb.append(", picture=").append(picture);
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
        GreenCertification other = (GreenCertification) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getApprovedAmount() == null ? other.getApprovedAmount() == null : this.getApprovedAmount().equals(other.getApprovedAmount()))
            && (this.getCertificationDate() == null ? other.getCertificationDate() == null : this.getCertificationDate().equals(other.getCertificationDate()))
            && (Arrays.equals(this.getPicture(), other.getPicture()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getApprovedAmount() == null) ? 0 : getApprovedAmount().hashCode());
        result = prime * result + ((getCertificationDate() == null) ? 0 : getCertificationDate().hashCode());
        result = prime * result + (Arrays.hashCode(getPicture()));
        return result;
    }
}