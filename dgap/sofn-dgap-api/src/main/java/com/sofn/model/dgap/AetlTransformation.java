package com.sofn.model.dgap;

import java.awt.print.PrinterAbortException;
import java.util.Date;

/**
 * Created by helong on 17-5-23.
 */
public class AetlTransformation {
    //资源ID
    private String resourceID;
    //资源名字
    private  String resourceName;
    //转换名字
    private String transName;
    //部署状态
    private String deloyStatus;
    //部署路径
    private String deloyPath;
    //版本号
    private String transVersion;
    //修改人
    private String modifiedUser;
    //修改时间
    private Date modifiedDate;
    //调度状态
    private String dispatcher;

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getDeloyStatus() {
        return deloyStatus;
    }

    public void setDeloyStatus(String deloyStatus) {
        this.deloyStatus = deloyStatus;
    }

    public String getDeloyPath() {
        return deloyPath;
    }

    public void setDeloyPath(String deloyPath) {
        this.deloyPath = deloyPath;
    }

    public String getTransVersion() {
        return transVersion;
    }

    public void setTransVersion(String transVersion) {
        this.transVersion = transVersion;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public String toString() {
        return "AetlTransformation{" +
                "resourceID='" + resourceID + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", transName='" + transName + '\'' +
                ", deloyStatus='" + deloyStatus + '\'' +
                ", deloyPath='" + deloyPath + '\'' +
                ", transVersion='" + transVersion + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", dispatcher='" + dispatcher + '\'' +
                '}';
    }
}
