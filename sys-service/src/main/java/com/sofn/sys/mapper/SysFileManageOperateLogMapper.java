package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysFileManageOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Created by heyongjie on 2019/6/5 15:56
 */
public interface SysFileManageOperateLogMapper extends BaseMapper<SysFileManageOperateLog> {


    List<SysFileManageOperateLog> getSysFileOperateLogByFileId(String fileId);

    /**
     * 批量保存文件操作记录
     * @param sysFileManageOperateLogs    操作日志集合
     */
    void batchSaveLog(@Param("sysFileManageOperateLogs") List<SysFileManageOperateLog> sysFileManageOperateLogs);

    /**
     * 根据文件ID删除操作日志
     * @param fileIds 文件ID
     * @param updateUserId   用户ID
     */
    void deleteByFileIds(@Param("fileIds") List<String> fileIds,@Param("updateUserId") String updateUserId);

}
