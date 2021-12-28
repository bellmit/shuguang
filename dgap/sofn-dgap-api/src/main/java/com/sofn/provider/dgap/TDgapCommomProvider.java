package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.model.generator.TDgapCommon;

import java.util.List;

/**
 * Created by weiqiang on 2016/11/3.
 */
public interface TDgapCommomProvider extends BaseProvider<TDgapAlertConfig>{
    TDgapCommon add(String json,String token);
    TDgapCommon update(String json,String token);
    List<TDgapCommon> checkRepeat(String targetId,String id);

    List checkUser(String alertId);
}
