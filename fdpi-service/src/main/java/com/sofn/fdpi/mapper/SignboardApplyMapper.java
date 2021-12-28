package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SignboardApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/27 17:30
 **/
public interface SignboardApplyMapper extends BaseMapper<SignboardApply> {

    List<SignboardApply> listByParams(Map<String, Object> params);

    SignboardApply getSignboardApply(@Param("id") String id);

    String validAppyingCode(@Param("signboardId") String signboardId);

    String getTodayMaxApplyNum(String todayStr);

    List<SignboardApply> countApplyNum(@Param("compId")String compId, @Param("speId")String speId, @Param("signboardType")String signboardType);
}
