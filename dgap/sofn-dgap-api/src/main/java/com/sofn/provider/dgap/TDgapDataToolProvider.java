package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapWsDailyStat;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface TDgapDataToolProvider  {

    public boolean copyTTSEntities(String sourceDataSource, String destDataSource);

}
