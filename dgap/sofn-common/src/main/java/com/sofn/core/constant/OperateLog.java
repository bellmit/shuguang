package com.sofn.core.constant;

import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * Created by: dong4j.
 * Date: 2016-10-27.
 * Time: 14:53.
 * Description:
 */

public class OperateLog extends BaseModel {
    // 操作人Id
    private String operateUserid;
    // 操作人名字
    private String operateUsername;
    // 操作时间
    private Date operateTime;
    // 请求Ip
    private String operateIp;
    // token
    private String userToken;
    // url
    private String visitUrl;
    // 参数
    private String visitParam;
    // 状态 失败 Y 成功 N
    private String status;
    // 失败说明
    private String failInfo;
    // 删除标识
    private String delFlag;
    // 操作类型
    private String operateType;
    // 操作说明
    private String operateDescribe;
    private String reservedField1;
    private String reservedField2;

    public String getOperateUserid() {
        return operateUserid;
    }

    public void setOperateUserid(String operateUserid) {
        this.operateUserid = operateUserid == null ? null : operateUserid.trim();
    }

    public String getOperateUsername() {
        return operateUsername;
    }

    public void setOperateUsername(String operateUsername) {
        this.operateUsername = operateUsername == null ? null : operateUsername.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl == null ? null : visitUrl.trim();
    }

    public String getVisitParam() {
        return visitParam;
    }

    public void setVisitParam(String visitParam) {
        this.visitParam = visitParam == null ? null : visitParam.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFailInfo() {
        return failInfo;
    }

    public void setFailInfo(String failInfo) {
        this.failInfo = failInfo == null ? null : failInfo.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getOperateDescribe() {
        return operateDescribe;
    }

    public void setOperateDescribe(String operateDescribe) {
        this.operateDescribe = operateDescribe;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getReservedField1() {
        return reservedField1;
    }

    public void setReservedField1(String reservedField1) {
        this.reservedField1 = reservedField1 == null ? null : reservedField1.trim();
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2 == null ? null : reservedField2.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operateUserid=").append(operateUserid);
        sb.append(", operateUsername=").append(operateUsername);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", operateIp=").append(operateIp);
        sb.append(", userToken=").append(userToken);
        sb.append(", visitUrl=").append(visitUrl);
        sb.append(", visitParam=").append(visitParam);
        sb.append(", status=").append(status);
        sb.append(", failInfo=").append(failInfo);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateDescribe=").append(operateDescribe);
        sb.append(", reservedField1=").append(reservedField1);
        sb.append(", reservedField2=").append(reservedField2);
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
        OperateLog other = (OperateLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getOperateUserid() == null ? other.getOperateUserid() == null : this.getOperateUserid().equals(other.getOperateUserid()))
                && (this.getOperateUsername() == null ? other.getOperateUsername() == null : this.getOperateUsername().equals(other.getOperateUsername()))
                && (this.getOperateTime() == null ? other.getOperateTime() == null : this.getOperateTime().equals(other.getOperateTime()))
                && (this.getOperateIp() == null ? other.getOperateIp() == null : this.getOperateIp().equals(other.getOperateIp()))
                && (this.getUserToken() == null ? other.getUserToken() == null : this.getUserToken().equals(other.getUserToken()))
                && (this.getVisitUrl() == null ? other.getVisitUrl() == null : this.getVisitUrl().equals(other.getVisitUrl()))
                && (this.getVisitParam() == null ? other.getVisitParam() == null : this.getVisitParam().equals(other.getVisitParam()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getFailInfo() == null ? other.getFailInfo() == null : this.getFailInfo().equals(other.getFailInfo()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
                && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
                && (this.getOperateDescribe() == null ? other.getOperateDescribe() == null : this.getOperateDescribe().equals(other.getOperateDescribe()))
                && (this.getReservedField1() == null ? other.getReservedField1() == null : this.getReservedField1().equals(other.getReservedField1()))
                && (this.getReservedField2() == null ? other.getReservedField2() == null : this.getReservedField2().equals(other.getReservedField2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOperateUserid() == null) ? 0 : getOperateUserid().hashCode());
        result = prime * result + ((getOperateUsername() == null) ? 0 : getOperateUsername().hashCode());
        result = prime * result + ((getOperateTime() == null) ? 0 : getOperateTime().hashCode());
        result = prime * result + ((getOperateIp() == null) ? 0 : getOperateIp().hashCode());
        result = prime * result + ((getUserToken() == null) ? 0 : getUserToken().hashCode());
        result = prime * result + ((getVisitUrl() == null) ? 0 : getVisitUrl().hashCode());
        result = prime * result + ((getVisitParam() == null) ? 0 : getVisitParam().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getFailInfo() == null) ? 0 : getFailInfo().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getOperateDescribe() == null) ? 0 : getOperateDescribe().hashCode());
        result = prime * result + ((getReservedField1() == null) ? 0 : getReservedField1().hashCode());
        result = prime * result + ((getReservedField2() == null) ? 0 : getReservedField2().hashCode());
        return result;
    }
}
