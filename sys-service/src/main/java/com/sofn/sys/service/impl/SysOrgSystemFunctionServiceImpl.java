package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.utils.IdUtil;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.mapper.SysSystemFunctionMapper;
import com.sofn.sys.model.SysSystemFunction;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysOrgSystemFunctionService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
@SuppressWarnings("ALL")
public class SysOrgSystemFunctionServiceImpl extends ServiceImpl<SysSystemFunctionMapper, SysSystemFunction>
        implements SysOrgSystemFunctionService {

    @Autowired
    private SysSystemFunctionMapper sysSystemFunctionMapper;

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    @Autowired
    private SysOrgService sysOrgService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveFunction(String orgId, List<SysSystemFunction> list) {
        List<String> appIds = permissionSubSystemService.getUserAppIds();
        sysSystemFunctionMapper.delete(new UpdateWrapper<SysSystemFunction>()
                .eq("ORG_ID", orgId)
                .in("APP_ID", appIds));

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(new Consumer<SysSystemFunction>() {
                @Override
                public void accept(SysSystemFunction sysSystemFunction) {
                    sysSystemFunction.setId(IdUtil.getUUId());
                    sysSystemFunctionMapper.insert(sysSystemFunction);
                }
            });
        }
    }

    @Override
    public List<SysSystemFunction> getByOrgId(String orgId) {
        if(StringUtils.isBlank(orgId)){
            return null;
        }

        List<String> appIds = permissionSubSystemService.getUserAppIds();
        if (CollectionUtils.isEmpty(appIds)) {
            return new ArrayList<>();
        }
        return sysSystemFunctionMapper.selectList(new QueryWrapper<SysSystemFunction>()
                .eq("ORG_ID", orgId)
                .in("APP_ID", appIds));
    }
}
