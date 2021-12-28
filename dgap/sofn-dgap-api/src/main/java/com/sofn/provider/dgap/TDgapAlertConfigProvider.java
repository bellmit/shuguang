package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapAlertConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface TDgapAlertConfigProvider extends BaseProvider<TDgapAlertConfig> {
    PageInfo<Map<String, Object>> list(Map<String, Object> params);


    List<TDgapAlertConfig> queryAllAlertConfig();
    List<Map> getAlertById(String id);
    public void batchDelete(String[] ids);

    int countByResourceId(String id);

    int countByResources(String[] ids);
}
