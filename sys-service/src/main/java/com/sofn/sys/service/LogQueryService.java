package com.sofn.sys.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysLog;
import com.sofn.sys.vo.SysStatusVo;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/9/16 17:26
 */
public interface LogQueryService {

    /**
     * 按条件查询日志信息
     * @param date   日期    无效
     * @param keyword  关键字 可以是操作人 也可以是操作内容
     * @param appId   系统代码
     * @param startDate  开始时间
     * @param endDate  结束时间
     * @param pageNo   开始页数
     * @param pageSize  结束页数
     * @return   es的响应内容
     */
    String getLogInfo(String date, String keyword, String appId, String startDate,String endDate, Integer pageNo, Integer pageSize);


    /**
     * 获取系统的使用情况
     * @return List<String>   0-> CPU   1-> memory 2-> filesystem
     */
    List<Map<String,Object>> getSysLogInfo();

    /**
     * 查询系统内存CP磁盘状况
     * @return SysStatusVo
     * @param pageNo   开始页数
     * @param pageSize  结束页数
     */
    PageUtils<SysStatusVo> getSysInfo(Integer pageNo, Integer pageSize);

    /**
     * 查询系统log
     * @param pageNo   开始页数
     * @param pageSize  结束页数
     * @param keyword
     * @param startDate
     * @param endDate
     * @return SysLog
     */
    PageUtils<SysLog> getSysLogPage(Integer pageNo, Integer pageSize, String keyword, String startDate, String endDate);
}
