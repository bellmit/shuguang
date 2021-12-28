package com.sofn.dhhrp.mapper;


import com.sofn.dhhrp.model.Baseinfo;
import com.sofn.dhhrp.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 14:08
 */
public interface AnalysisMapper {
//    查询 保护点
    List<MonitoringPointVo> getList1(Map map);

    Baseinfo getById(String id);
    //    取出当前保护点的物种的最新记录
    List<NewSpeInfo> getSpe(String pointName);
    //    得到当前查询物种 的阈值
    List<TholdResult> thresholdForSpe(String variety);
    //    得到单个物种的阈值结果集合
    List<AnalysisVo>    param(@Param("variety") String variety, @Param("pointName")  String pointName, List<TholdResult> th);
}
