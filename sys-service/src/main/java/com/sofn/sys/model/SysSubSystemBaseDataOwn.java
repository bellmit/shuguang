package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *@Description 基础数据分配给子系统的实体类
 *@Author quzhijie
 *@Date 2019/12/5 14:10
 *@Version 1.0
 **/
@Data
@TableName("SYS_SUBSYSTEM_BASEDATA_OWN")
public class SysSubSystemBaseDataOwn  {

    private String id;
    /**
     * 子系统appid
     */
    private String appId;
    /**
    * 基础数据类型id
    */
    private String baseDataTypeId;
    /**
     * 基础数据id
     */
    private String baseDataId;

    public SysSubSystemBaseDataOwn(String appId, String baseDataTypeId, String baseDataId) {
        this.appId = appId;
        this.baseDataTypeId = baseDataTypeId;
        this.baseDataId = baseDataId;
    }

    public SysSubSystemBaseDataOwn(){}
}
