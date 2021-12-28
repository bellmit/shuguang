package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysLog;

import java.util.List;
import java.util.Map;

/**
 * Created by sofn
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * 日志分页
     * @param params 参数
     * @return SysLog
     */
    List<SysLog> getSysLogPage(Map<String, Object> params);
}
