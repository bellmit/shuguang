package com.sofn.ducss.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.ducss.exception.ExceptionType;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.SysBaseDataTypeMapper;
import com.sofn.ducss.mapper.SysSubSystemBaseDataOwnMapper;
import com.sofn.ducss.model.SysBaseDataType;
import com.sofn.ducss.model.SysSubSystemBaseDataOwn;
import com.sofn.ducss.service.SysSubBaseDataOwnService;
import com.sofn.ducss.util.BaseDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author qzj
 */
@SuppressWarnings("ALL")
@Service(value = "sysSubSystemBaseDataOwnService")
@Slf4j
public class SysSubSystemBaseDataOwnServiceImpl extends ServiceImpl<SysSubSystemBaseDataOwnMapper, SysSubSystemBaseDataOwn> implements SysSubBaseDataOwnService {
    @Autowired
    private SysSubSystemBaseDataOwnMapper sysSubSystemBaseDataOwnMapper;
    @Autowired
    private SysBaseDataTypeMapper sysBaseDataTypeMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void selectBaseDataForSubSystem(String appId, String baseDataTypeId, List<String> baseDataIds) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(baseDataTypeId)) {
            throw new SofnException(ExceptionType.PARAMS_ERROR);
        }

        sysSubSystemBaseDataOwnMapper.delete(new UpdateWrapper<SysSubSystemBaseDataOwn>()
                .eq("APP_ID",appId)
                .eq("BASE_DATA_TYPE_ID",baseDataTypeId));

        // 集合不为空插入新数据
        if (!CollectionUtils.isEmpty(baseDataIds)){
            baseDataIds.forEach(item -> {
                if (StringUtils.isNotBlank(item)){
                    sysSubSystemBaseDataOwnMapper.insert(new SysSubSystemBaseDataOwn(appId,baseDataTypeId,item));
                }
            });
        }

        // 删除对应缓存
        SysBaseDataType sysBaseDataType = sysBaseDataTypeMapper.selectById(baseDataTypeId);
        if (sysBaseDataType != null) {
            BaseDataUtils.deleteCacheByTypeAndAppId(appId,sysBaseDataType.getTypevalue());
        }
    }
}
