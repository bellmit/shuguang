package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.model.sys.OperateLogQueryParam;
import com.sofn.model.sys.OperateLogStats;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/8.
 */
@MyBatisDao
public interface SysOperateLogExpandMapper extends BaseExpandMapper {

    /**
     *  获取模板管理列表
     */
    List<SysOperateLog> getPageInfo(Map<String, Object> map);


    /**
     *  获取模板管理列表
     */
    List<SysOperateLog> getDataPageInfo(Map<String, Object> map);


    /**
     *  获取模板管理数据条数
     */
    long getCount(Map<String, Object> map);


    long getDataCount(Map<String, Object> map);


    List<SysOperateLog> findByIp(@Param(value = "operateIp") String operateIp);
    // 获取系统操作日志统计数据
    List<OperateLogStats> getOperateLogStats(@Param("startDate") String startDate, @Param("endDate") String endDate);
    // 获取记录中的最小日期
    Date getMinDate();

    List<SysOperateLog> queryOperateLog(OperateLogQueryParam operateLogQueryParam);
    List<String> queryOperateTypes();
}
