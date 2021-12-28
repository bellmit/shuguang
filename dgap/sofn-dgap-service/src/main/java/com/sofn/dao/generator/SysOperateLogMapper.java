package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysOperateLog;

import java.util.List;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysOperateLogMapper extends BaseMapper<SysOperateLog> {
    int deleteByPrimaryKey(String id);

    int insert(SysOperateLog sysOperateLog);

    int updateByPrimaryKey(SysOperateLog sysOperateLog);

    SysOperateLog selectByPrimaryKey(String id);

    List<SysOperateLog> selectAll();


}