package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SturgeonSignboard;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SturgeonSignboardMapper extends BaseMapper<SturgeonSignboard> {

    List<SturgeonSignboard> listByParams(Map<String, Object> params);

    List<String> listSignboardsByParams(Map<String, Object> params);

    List<String> listSignboardsByParams2(Map<String, Object> params);

    void print(Map<String, Object> params);

    List<String> getCaseNum(Map<String, Object> params);

    void insertSturgeonSignboardBatch(List<SturgeonSignboard> list);

    //根据创建时间更鲟鱼子酱新标识其它字段
    void updateSturgeonSignboardInfo(@Param("sturgeonSignboard") SturgeonSignboard sturgeonSignboard, @Param("sturgeonSubId") String sturgeonSubId);
}
