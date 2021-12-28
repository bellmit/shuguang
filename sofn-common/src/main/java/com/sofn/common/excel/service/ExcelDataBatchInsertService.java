package com.sofn.common.excel.service;

import java.util.List;

/**
 * 批量添加接口
 *
 * @author heyongjie
 * @date 2020/6/11 9:33
 */
public interface ExcelDataBatchInsertService<T> {

    /**
     * 批量添加接口
     * @param values   需要插入的值
     */
    void batchInsert(List<T> values);

}
