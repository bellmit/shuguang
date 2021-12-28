package com.sofn.agpjyz.mapper;

import com.sofn.agpjyz.model.TargetSpecies;
import com.sofn.agpjyz.model.ThreatFactor;
import com.sofn.agpjyz.vo.TholdDeatilResult;
import com.sofn.agpjyz.vo.TholdResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-08-10 14:28
 */
public interface AnalysisMapper {
    //    获取的威胁因素预警指标阈值集合
    List<TholdResult> pollute(@Param("protectId") String protectId,
                              @Param("indexId") String indexId,
                              @Param("testType") String testType);
    //    获取目标物种的指标阈值集合
    List<TholdResult> amount(@Param("protectId") String protectId,
                             @Param("indexId") String indexId,
                             @Param("testType") String testType,
                             @Param("specId") String specId);
    //    查询当前条件下 目标物种基础信息 是否有设置基础数据
    TargetSpecies param(@Param("protectId") String protectId, @Param("specId") String specId, @Param("year") String year);
    //    查询当前条件下   威胁因素基础信息 是否有设置基础数据
    ThreatFactor param1(@Param("protectId") String protectId,
                        @Param("year") String year);
    //    获取威胁因素的预警结果
    List<TholdDeatilResult> warning(@Param("protectId") String protectId,
                                    @Param("year") String year,
                                    @Param("indexId") String indexId,
                                    @Param("testType") String testType,
                                    List<TholdResult> th);
    //    获取目标物种的预警结果
    List<TholdDeatilResult> warningNumber(@Param("protectId") String protectId,
                                          @Param("year") String year,
                                          @Param("indexId") String indexId,
                                          @Param("testType") String testType,
                                          @Param("specId") String specId,
                                          List<TholdResult>  th);

}
