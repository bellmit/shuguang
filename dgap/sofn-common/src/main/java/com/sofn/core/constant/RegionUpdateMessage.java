/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.constant;

import java.io.Serializable;

/**
 * @author lijiang
 * @desc 行政区划更新消息
 * @create 2019-02-26 14:52
 **/
public class RegionUpdateMessage implements Serializable {
    private String messageId; // 消息ID
    private String oldRegionName; // 修改前的行政区划名称
    private String oldRegionCode; // 修改前的行政区划编码
    private String newRegionCode; // 修改后的行政区划编码
    private String newRegionName; // 修改后的行政区划名称

    public String getOldRegionName() {
        return oldRegionName;
    }

    public void setOldRegionName(String oldRegionName) {
        this.oldRegionName = oldRegionName;
    }

    public String getOldRegionCode() {
        return oldRegionCode;
    }

    public void setOldRegionCode(String oldRegionCode) {
        this.oldRegionCode = oldRegionCode;
    }

    public String getNewRegionCode() {
        return newRegionCode;
    }

    public void setNewRegionCode(String newRegionCode) {
        this.newRegionCode = newRegionCode;
    }

    public String getNewRegionName() {
        return newRegionName;
    }

    public void setNewRegionName(String newRegionName) {
        this.newRegionName = newRegionName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegionUpdateMessage{");
        sb.append("messageId='").append(messageId).append('\'');
        sb.append(", oldRegionName='").append(oldRegionName).append('\'');
        sb.append(", oldRegionCode='").append(oldRegionCode).append('\'');
        sb.append(", newRegionCode='").append(newRegionCode).append('\'');
        sb.append(", newRegionName='").append(newRegionName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
