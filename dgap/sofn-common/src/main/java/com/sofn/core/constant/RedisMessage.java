/**
 * Copyright (c) 1998-2018 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.constant;

import java.io.Serializable;

/**
 * @author lijiang
 * @desc 自定义Redis发布订阅模式收发的消息体
 * @create 2018-11-23 10:49
 **/
public class RedisMessage implements Serializable {
    private String nodeId; // 节点ID
    private String message; // 消息内容

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RedisMessage{");
        sb.append("nodeId='").append(nodeId).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
