package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;

import java.util.Map;

/**
 *
 */
public interface TDgapLocalAuditProvider extends BaseProvider<TDgapLocalPlatFormAppli> {
    PageInfo<Map<String,Object>> list(Map<String, Object> params);

    /**
     * 添加
     * @param localPlatFormAppli
     * @return
     */
    public int addLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli);

    /**
     * 修改
     * @param localPlatFormAppli
     * @return
     */
    public int updateLocalPlatFormAppli(TDgapLocalPlatFormAppli localPlatFormAppli);
}
