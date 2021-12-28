package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 文件
 * @author heyongjie
 * @date 2019/6/5 15:52
 */
@Data
@TableName("sys_file_manage_operate_log")
public class SysFileManageOperateLog {

    private String id;
    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 所属系统
     */
    private String system;

    private String operator;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;
    private String delFlag;
}
