package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapSyncDate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author hyl
 * @date 2018/8/31 10:32
 */
public interface TDgapSyncDateProvider extends BaseProvider<TDgapResource> {
    String getLastSyncDate(String params);
    int updateByData(Map<String, Object> params);
    int insertByData(Map<String, Object> params);
    List<TDgapSyncDate> getData();
    //根据数据类型获取自增序列mark
    String getMark(@Param(value = "dataSourceType") String dataSourceType);
    //根据数据类型更新自增序列mark
    void updateMark(@Param(value = "dataSourceType") String dataSourceType,@Param(value = "updateMark") String updateMark);
}
