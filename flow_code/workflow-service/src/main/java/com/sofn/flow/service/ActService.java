package com.sofn.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.flow.model.Act;
import com.sofn.flow.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActService extends IService<Act> {

    List<Act> getListByParams(String defId, String instId, Map<String,Object> attrs);

    List<ProcessVo> getProcessInstAllByAttr(List<String> defIds,
            String idAttrName, List<String> idAttrValues,
                                            List<InstanceParamVo> instanceParamVos,
                                           String startTime,
                                            String endTime);


    List<ProcessVo> getProcessInstAllByAttrByPage(@Param("defIds") List<String> defIds,
                                                  @Param("idAttrName") String idAttrName,
                                                  @Param("idAttrValues") List<String> idAttrValues,
                                                  @Param("instanceParamVos") List<InstanceParamVo> instanceParamVos,
                                                  @Param("startTime") String startTime,
                                                  @Param("endTime") String endTime
    );

    List<ActivityDataVo> getActivityAllDataByName(String defId, String idAttrName, List<String> idAttrValues, String activityMaker, List<InstanceParamTwoVo> instanceParamVos, String startTime, String endTime);

    List<ActContextVo> getAllFieldAndValue(String activityId, List<InstanceParamTwoVo> instanceParamVos);
}
