package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;


@Data
@TableName("sys_region")
public class Region extends BaseModel<Region> {

    /**
     * 上级行政区ID
     */
    private String parentId;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 行政区划编码
     */
    private String regionCode;
    /**
     * 行政区划类型
     */
    private String regionType;
    /**
     * 排序
     */
    private Integer sortid;
    /**
     * 父节点ID路由
     */
    private String parentIds;
    /**
     * 父节点名称路由
     */
    private String parentNames;


}
