package com.sofn.ducss.model;

import com.sofn.common.utils.IdUtil;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import lombok.Data;

import java.util.Date;

/**
 * 从支撑平台复制的行政区划代码
 */
@Data
public class DucssRegionCopySys {

    private String id;

    /**
     * 区划代码
     */
    private String regionCode;

    /**
     * 区划名字
     */
    private String regionName;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 年度
     */
    private String year;

    /**
     * 父ID
     */
    private String parentIds;

    /**
     * 完整名字
     */
    private String parentNames;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 机构级别
     */
    private String level;



}
