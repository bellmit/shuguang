package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PapersYearInspectHistory;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.vo.SignboardVoForInspect;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/27 17:30
 **/
public interface SignboardMapper extends BaseMapper<Signboard> {

    List<Signboard> listByParams(Map<String, Object> params);

    List<String> listSpeSourceDistinct(@Param("speId") String speId, @Param("compId") String compId);

    List<String> listUtilizeTypeDistinct(@Param("speId") String speId, @Param("compId") String compId);

    //wuXY 证书年审中使用
    List<SignboardVoForInspect> listForSignboard(Map<String, Object> map);

    Signboard getSignboard(@Param("id") String id);

    //记录标识变更记录表
    void recordAllotment(@Param("createTime")Long createTime);

    //记录标识申请list
    void recordApplyList(@Param("applyId") String applyId, @Param("createTime") Long createTime);

    //获取标识信息年审新增使用
    List<PapersYearInspectHistory> listForInspectHistory(Map<String, Object> signMap);

    //证书年审详情中使用
    List<SignboardVoForInspect> listForSignboardInspectHistory(Map<String, Object> map);

    List<Signboard> listSignboardByCode(@Param("code") String code);

    Long countTotal(Map<String, Object> params);

    List<Signboard> listPage(Map<String, Object> params);
}
