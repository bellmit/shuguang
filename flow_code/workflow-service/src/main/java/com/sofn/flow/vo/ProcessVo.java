package com.sofn.flow.vo;

import com.sofn.flow.model.Act;
import lombok.Data;

import java.util.List;

@Data
public class ProcessVo {
    private long id;

    private String name;

    private Integer procModelId;

    private String createTime;

    private String startTime;

    private String startPerformer;

    private String lastStateChangeTime;

    private String curState;

    private String procDefId;

    private String creator;

    private List<ProcessContextVo> processContextList;

    private List<ActVo> actList;



    private List<WorkItemVo> workItemList;



 }
