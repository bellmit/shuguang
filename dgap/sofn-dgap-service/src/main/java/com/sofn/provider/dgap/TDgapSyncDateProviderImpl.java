package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapSyncDateExpandMapper;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapSyncDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author hyl
 * @date 2018/8/31 11:47
 */

@DubboService(interfaceClass = TDgapSyncDateProvider.class)
public class TDgapSyncDateProviderImpl extends BaseProviderImpl<TDgapResource>
        implements TDgapSyncDateProvider {
    @Autowired
    private TDgapSyncDateExpandMapper tDgapSyncDateExpandMapper;
    @Override
    public String getLastSyncDate(String params) {
        String result = tDgapSyncDateExpandMapper.getLastSyncDate(params);
        if(StringUtils.isNotBlank(result)){
            return result;
        } else {
            return null;
        }
    }

    @Override
    public int updateByData(Map<String, Object> params) {
        int result = tDgapSyncDateExpandMapper.updateByData(params);
        return result;
    }

    @Override
    public int insertByData(Map<String, Object> params) {
        int result = tDgapSyncDateExpandMapper.insertByData(params);
        return result;
    }

    @Override
    public List<TDgapSyncDate> getData() {
        List<TDgapSyncDate> list = tDgapSyncDateExpandMapper.getData();
        return list;
    }

    @Override
    public String getMark(String dataSourceType) {
        String mark=tDgapSyncDateExpandMapper.getMark(dataSourceType);
        return mark;
    }

    @Override
    public void updateMark(String dataSourceType,String updateMark) {
        tDgapSyncDateExpandMapper.updateMark(dataSourceType,updateMark);
    }
}
