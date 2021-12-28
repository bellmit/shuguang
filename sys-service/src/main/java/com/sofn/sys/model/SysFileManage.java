package com.sofn.sys.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 文件管理相关表
 * @author heyongjie
 * @date 2019/6/5 15:52
 */
@Data
@TableName("sys_file_manage")
public class SysFileManage extends BaseModel<SysFileManage> {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 所属系统
     */
    private String system;

    /**
     * 下载次数
     */
    private Integer downloadTimes;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人员
     */
    private String operator;

    /**
     * 操作时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /**
     * 接口号  使用接口的时候会分配
     */
    private String interfaceNum;

    /**
     * 激活状态
     */
    private String activationState;

    /**
     *  文件编号
     */
    private String fileNumber;

    /**
     * 文件状态  Y 启用  N 停用
     */
    private String fileState;

    /**
     * 业务文件类型
     */
    private String businessFileType;


}
