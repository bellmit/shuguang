package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行政区划持久对象
 * Created by heyongjie on 2019/5/29 11:04
 */
@Data
@TableName("SYS_REGION")
public class SysRegion   extends BaseModel<SysRegion> {
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
     * 行政区划状态
     */
    private String status;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 版本年份
     */
    private Integer versionYear;

    /**
     * 版本代码
     */
    private Long versionCode;
}
