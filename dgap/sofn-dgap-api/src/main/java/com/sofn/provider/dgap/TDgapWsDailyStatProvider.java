package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapWsDailyStat;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface TDgapWsDailyStatProvider extends BaseProvider<TDgapWsDailyStat> {
    PageInfo<Map<String, Object>> list(Map<String, Object> params);

    public int stateDailyLog(Date time);

    public void testPageHelper();

    void setWebServiceLog(TDgapWsDailyStat tDgapWsDailyStat);

}
