package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapTbResourceConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapTbResourceConfigProvider extends BaseProvider<TDgapTbResourceConfig>{
    PageInfo<Map<String,Object>> list(Map<String,Object> parames);

    public List<TDgapTbResourceConfig> getByResourceId(String resourceId);
}
