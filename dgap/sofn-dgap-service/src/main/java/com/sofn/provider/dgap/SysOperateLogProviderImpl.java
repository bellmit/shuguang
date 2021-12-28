package com.sofn.provider.dgap;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.SysOperateLogExpandMapper;
import com.sofn.dao.generator.SysOperateLogMapper;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.model.sys.OperateLogQueryParam;
import com.sofn.model.sys.OperateLogStats;
import com.sofn.provider.sys.SysOperateLogProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/16.
 */
@DubboService(interfaceClass = SysOperateLogProvider.class)
public class SysOperateLogProviderImpl extends BaseProviderImpl<SysOperateLog> implements SysOperateLogProvider {

    @Autowired
    private SysOperateLogMapper sysOperateLogMapper;

    @Autowired
    private SysOperateLogExpandMapper sysOperateLogExpandMapper;

    @Override
    public List<SysOperateLog> getPageInfo(Map<String, Object> map) {
        return sysOperateLogExpandMapper.getPageInfo(map);
    }

    @Override
    public List<SysOperateLog> getDataPageInfo(Map<String, Object> map) {
        return sysOperateLogExpandMapper.getDataPageInfo(map);
    }

    @Override
    public long getCount(Map<String, Object> map) {
        return sysOperateLogExpandMapper.getCount(map);
    }

    @Override
    public long getDataCount(Map<String, Object> map) {
        return sysOperateLogExpandMapper.getDataCount(map);
    }

    @Override
    public int addSysOperateLog(SysOperateLog sysOperateLog) {
        return sysOperateLogMapper.insert(sysOperateLog);
    }

    @Override
    public List<SysOperateLog> findByIp(String operateIp) {
        return sysOperateLogExpandMapper.findByIp(operateIp);
    }

    @Override
    public Date getMinDate() {
        return sysOperateLogExpandMapper.getMinDate();
    }

    @Override
    public List<OperateLogStats> getOperateLogStats(String startDate, String endDate) {
        return sysOperateLogExpandMapper.getOperateLogStats(startDate, endDate);
    }

    @Override
    public PageInfo<SysOperateLog> getOperateLogs(OperateLogQueryParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<SysOperateLog> list = sysOperateLogExpandMapper.queryOperateLog(param);
        PageInfo<SysOperateLog> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<String> getOperateTypes() {
        return sysOperateLogExpandMapper.queryOperateTypes();
    }

}
