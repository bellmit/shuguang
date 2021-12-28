package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapRoleResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface TDgapRoleResourceProvider extends BaseProvider<TDgapRoleResource> {
    PageInfo<Map<String,Object>> list(Map<String,Object> parames);
    List<Map<String,Object>> listAll();
    List<TDgapRoleResource> checkRepetition(String roleId);
}
