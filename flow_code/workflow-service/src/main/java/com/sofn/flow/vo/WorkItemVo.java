package com.sofn.flow.vo;

import lombok.Data;

import java.util.List;

@Data
public class WorkItemVo {
    private long id;

    private String name;

    private Integer procModelId;

    private String createTime;

    private String startTime;

    private String receiveTime;

    private String workItemStarter;

    private String lastStateChangeTime;

    private String completeTime;

    private String participant;

    private String curState;

    private String actDefId;

    private Integer actInstId;

    private List<WorkItemContextVo> workItemContextList;

}
