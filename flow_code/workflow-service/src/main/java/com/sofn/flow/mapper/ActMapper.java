package com.sofn.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.flow.model.Act;
import com.sofn.flow.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActMapper extends BaseMapper<Act> {
    List<Act> getListByParams(@Param("defId") String defId, @Param("instId")String instId, @Param("attrs") Map<String,Object> attrs);

    List<ProcessVo> getProcessInstAllByAttr(@Param("defIds") List<String> defIds,
                                            @Param("idAttrName") String idAttrName,
                                            @Param("idAttrValues") List<String> idAttrValues,
                                            @Param("instanceParamVos") List<InstanceParamVo> instanceParamVos,
                                            @Param("startTime") String startTime,
                                            @Param("endTime") String endTime
    );

    List<ProcessVo> getProcessInstAllByAttrByPage(@Param("defIds") List<String> defIds,
                                            @Param("idAttrName") String idAttrName,
                                            @Param("idAttrValues") List<String> idAttrValues,
                                            @Param("instanceParamVos") List<InstanceParamVo> instanceParamVos,
                                            @Param("startTime") String startTime,
                                            @Param("endTime") String endTime
    );

    List<ActivityDataVo> getActivityAllDataByName(@Param("defId") String defId,
                                                  @Param("idAttrName") String idAttrName,
                                                  @Param("idAttrValues") List<String> idAttrValues,
                                                  @Param("activityMaker") String activityMaker,
                                                  @Param("instanceParamVos")List<InstanceParamTwoVo> instanceParamVos,
                                                  @Param("startTime") String startTime,
                                                  @Param("endTime") String endTime);

    List<ActContextVo> getAllFieldAndValue(@Param("actId") String activityId,
                                           @Param("instanceParamVos")List<InstanceParamTwoVo> instanceParamVos);
}
