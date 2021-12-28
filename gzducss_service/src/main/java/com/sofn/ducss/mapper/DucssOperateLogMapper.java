package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.DucssOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志Mapper
 */
public interface DucssOperateLogMapper extends BaseMapper<DucssOperateLog> {

    /**
     * 查询操作记录
     *
     * @param startDate     开始事件
     * @param endDate       截止事件
     * @param operateType   操作类型
     * @param operateDetail 操作详情
     * @param areaId        区域ID
     * @return List<DucssOperateLog>
     */
    List<DucssOperateLog> getLogList(@Param("startDate") String startDate,
                                     @Param("endDate") String endDate,
                                     @Param("operateType") String operateType,
                                     @Param("operateDetail") String operateDetail,
                                     @Param("areaId") String areaId
    );


}
