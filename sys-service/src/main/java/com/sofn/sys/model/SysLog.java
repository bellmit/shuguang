package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.log.Log;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sofn
 */
@TableName("sys_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog  extends BaseModel<SysLog>{

    /**
     * 编号
     */
    protected String id;
    /**
     * 用户名
     */
    protected String username;

    /**
     * 用户ip
     */
    protected String ip;

    /**
     * 请求方法
     */
    protected String reqMethod;

    /**
     * 执行方法
     */
    protected String execMethod;

    /**
     * 响应时间
     */
    protected long execTime;

    /**
     * 日志描述
     */
    protected String execDesc;

    /**
     * 日志类型
     */
    protected String type;

//    /**
//     * 日志描述
//     */
//    protected String logValue;
//
//    /**
//     * 日志类型
//     */
//    protected String logType;
//
//    /**
//     * 日志标签
//     */
//    protected String logTag;

    /**
     * 返回状态
     */
    protected String status;

    /**
     * 返回信息
     */
    protected String msg;

    /**
     * 创建时间
     */
    protected Date createTime;


    /**
     * 请求URL
     */
    protected String reqUri;

    /**
     * 参数
     */
    protected String args;

    /**
     * 返回值
     */
    protected String returnVal;

    public SysLog(){

    }

    public static SysLog getSysLog(Log log){
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(log,sysLog);
        return sysLog;
    }


}
