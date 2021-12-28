package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapWsLog;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface TDgapWsLogProvider extends BaseProvider<TDgapWsLog> {

    public int insertInvokeLog(TDgapWsLog tDgapWsLog);
}
