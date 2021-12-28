package com.sofn.flow.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("wr_activity")
@Data
public class Act {
    private long id;
    private String name;
    @TableField(value = "procModelId")
    private Integer procModelId;
    @TableField(value = "createTime")
    private String createTime;
    @TableField(value = "startTime")
    private String startTime;
    @TableField(value = "startPerformer")
    private String startPerformer;
    @TableField(value = "lastStateChangeTime")
    private String lastStateChangeTime;
    @TableField(value = "completeTime")
    private String completeTime;
    @TableField(value = "previousState")
    private String previousState;
    @TableField(value = "curState")
    private String curState;
    @TableField(value = "actDefId")
    private String actDefId;
    @TableField(value = "isBack")
    private String isBack;
    @TableField(value = "activityStarter")
    private String activityStarter;
}
