package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.dgap.TDgapDataImportFieldDto;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapDataImportFieldProvider extends BaseProvider<TDgapDataImportField> {

    public List<TDgapDataImportField> getByFieldNames(String[] fieldNames);

    public List<TDgapDataImportField> getByTableId(String tableId);

    /**
     * 查询对应表的所有字段
     *
     * @param id
     * @return
     */
    public List<TDgapDataImportFieldDto> selectAllField(String id);


    public  List<TDgapDataImportField> getAllField(String id);

    public List<String> getAllEnglishName(String id);
}
