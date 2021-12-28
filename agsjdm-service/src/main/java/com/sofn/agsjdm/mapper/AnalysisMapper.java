package com.sofn.agsjdm.mapper;

import com.sofn.agsjdm.model.Biomonitoring;
import com.sofn.agsjdm.model.ThreatFactor;
import com.sofn.agsjdm.vo.TholdDeatilResult;
import com.sofn.agsjdm.vo.TholdResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-08-07 11:26
 */
public interface AnalysisMapper {
//    获取污染面积的预警指标集合
    List<TholdResult>  pollute(Map map);
//     获取当前查询条件 生物监测信息 是否设置了基础数据
    Biomonitoring param(@Param("wetlandId") String wetlandId, @Param("year") String year, @Param("chineseName") String chineseName);
//    获取当前查询条件 威胁因素信息 是否设置了基础数据
    ThreatFactor param1(@Param("wetlandId") String wetlandId, @Param("year") String year);
//    获取污染面积的预警结果
    List<TholdDeatilResult> warning(@Param("wetlandId") String wetlandId, @Param("indexId") String indexId, @Param("testType") String testType, @Param("year") String year, List<TholdResult>  th);
//
    List<TholdDeatilResult> warningNumber(@Param("wetlandId") String wetlandId, @Param("indexId") String indexId, @Param("testType") String testType, @Param("year") String year, @Param("chineseName") String chineseName, List<TholdResult>  th);
}
