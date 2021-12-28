package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapWsErrorLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface TDgapWsErrorLogProvider extends BaseProvider<TDgapWsErrorLog> {

    PageInfo<List<Map<String, Object>>> list(Map<String, Object> params);

    public int insertWsErrorLog(TDgapWsErrorLog tDgapWsErrorLog);

}
