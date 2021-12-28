package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapAlertLog;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface TDgapAlertConfigLogProvider extends BaseProvider<TDgapAlertLog> {
    PageInfo<Map<String, Object>> list(Map<String, Object> params);

    /**
     * 检查该资源是否有未解决的告警信息
     * @param resourceId
     * @return
     */
    public boolean checkResourceAlert(String resourceId);

}
