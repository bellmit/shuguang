package com.sofn.common.fileutil;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 文件对象
 *
 * @author heyongjie
 * @date 2019/6/18 16:15
 */
@Data
public class SysFileManageVo {

    private String id;

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
     * 操作者ID
     */
    private String operator;

    /**
     * 操作者名字
     */
    private String operatorName;

    /**
     * 最后一次操作时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /**
     * 系统ID
     */
    private String systemId;

    /**
     * 系统名称   column ambiguously defined
     */
    private String systemName;

    /**
     * 下载次数
     */
    private Integer downloadTimes;

    /**
     * 备注
     */
    private String remark;


}
