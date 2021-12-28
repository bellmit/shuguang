package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysSubSystemBaseDataOwn;

import java.util.List;

public interface SysSubBaseDataOwnService extends IService<SysSubSystemBaseDataOwn> {

    /**
     * 选择子系统需要的基础数据
     * @param appId 子系统appID
     * @param baseDataTypeId 基础数据类型id
     * @param baseDataIds  基础数据ID集合
     */
    void selectBaseDataForSubSystem(String appId, String baseDataTypeId, List<String> baseDataIds);

}
