package com.sofn.flow.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ActivityDataVo
 * @Description: TODO
 * @Author liling
 * @Date 2021/1/14
 * @Version V1.0
 **/
@Data
public class ActivityDataVo {

    private String  procInstId;

    private String  activityId;

    private String  activityName;

    private String  activityStarter;

    private String  activityCreateTime;

    private String  activityCompleteTime;

    private String  activityCurState;

    private String  unitKey;

    private String  unitValue;

    private List<ActContextVo> actContextVos;

}
