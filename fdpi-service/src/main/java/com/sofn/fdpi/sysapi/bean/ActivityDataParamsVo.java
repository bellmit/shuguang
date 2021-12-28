package com.sofn.fdpi.sysapi.bean;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author liling
 * @date 2021/1/13 18:50
 */
@Data
@ApiModel(value = "流程活动查询参数")
public class ActivityDataParamsVo {

    @ApiModelProperty(value = "流程定义文件ID")
    private String defId;

    @ApiModelProperty(value = "主键名称")
    String idAttrName;

    @ApiModelProperty(value = "操作人")
    String activityMaker;

    @ApiModelProperty(value = "主键值")
    List<String> idAttrValues;

    @ApiModelProperty(value = "其他参数")
    private List<InstanceParamTwoVo> instanceParamVos;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "从哪开始查询")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示多少条")
    private Integer pageSize;

    /**
     * 获取实例
     */
    public static ActivityDataParamsVo getInstance(String defId, String idAttrName, List<String> idAttrValues,
                                                   Map<String, Object> params, Integer pageNo, Integer pageSize,
                                                   String startTime, String endTime) {
        ActivityDataParamsVo activityDataParamsVo =
                getInstance(defId, idAttrName, idAttrValues, params, pageNo, pageSize);
        if (StringUtils.hasText(startTime))
            activityDataParamsVo.setStartTime(startTime);
        if (StringUtils.hasText(endTime))
            activityDataParamsVo.setEndTime(endTime);
        return activityDataParamsVo;
    }

    /**
     * 获取实例
     */
    public static ActivityDataParamsVo getInstance(String defId, String idAttrName, List<String> idAttrValues,
                                                   Map<String, Object> params, Integer pageNo,
                                                   Integer pageSize) {
        ActivityDataParamsVo activityDataParamsVo = new ActivityDataParamsVo();
        activityDataParamsVo.setDefId(defId);
        activityDataParamsVo.setIdAttrName(idAttrName);
        activityDataParamsVo.setPageNo(pageNo);
        activityDataParamsVo.setPageSize(pageSize);
        activityDataParamsVo.setIdAttrValues(idAttrValues);
        List<InstanceParamTwoVo> instanceParamVos = Collections.EMPTY_LIST;
        if (Objects.nonNull(params.get("status"))) {
            instanceParamVos = Lists.newArrayList();
            InstanceParamTwoVo instanceParamTwoVo = new InstanceParamTwoVo();
            instanceParamTwoVo.setKey("status");
            instanceParamTwoVo.setValues(Arrays.asList(params.get("status").toString()));
            instanceParamVos.add(instanceParamTwoVo);
        }
        if (!CollectionUtils.isEmpty(instanceParamVos))
            activityDataParamsVo.setInstanceParamVos(instanceParamVos);
        return activityDataParamsVo;
    }

}
