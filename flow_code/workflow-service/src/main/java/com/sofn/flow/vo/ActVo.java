package com.sofn.flow.vo;

import lombok.Data;

import java.util.List;

@Data
public class ActVo {

    private long id;

    private String name;

    private Integer procModelId;

    private String createTime;

    private String startTime;

    private String startPerformer;

    private String lastStateChangeTime;

    private String completeTime;

    private String previousState;

    private String curState;

    private String actDefId;

    private String isBack;

    private String activityType;

    private String actorType;

    private String actor;

    private String fromNodes;

    private String activityStarter;

    private Integer procInstId;

    private String procDefId;

    private String activityLevel;

    private List<ActContextVo> actContextList;
}
