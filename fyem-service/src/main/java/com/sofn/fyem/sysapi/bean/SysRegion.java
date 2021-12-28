package com.sofn.fyem.sysapi.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 行政区划持久对象
 * Created by heyongjie on 2019/5/29 11:04
 */
@Data
@TableName("SYS_REGION")
public class SysRegion extends BaseModel<SysRegion> {
    /**
     * 父ID
     */
    private String parentId;
  /**
   * 父ID路由
   */
    private String parentIds;

    /**
     * 行政区划名称
     */
    private String regionName;

    /**
     * 行政区划名称路由
     */
    private String parentNames;

    /**
     * 行政区划代码
     */
    private String regionCode;

    /**
     * 行政区划拼音
     */
    private String regionPinyin;

    /**
     * 行政区划类别
     */
    private String regionType;

    /**
     * 行政区划全称
     */
    private String regionFullname;

    /**
     * 行政区划排序
     */
    private Integer sortid;

    /**
     * 行政区划备注
     */
    private String remark;

    /**
     * 区域Id
     */
    private String areaId;

    /**
     * 预留字段 1
     */
    private String reservedField1;

    /**
     * 预留字段 2
     */
    private String reservedField2;

    /**
     * 行政区划状态
     */
    private String status;
}
