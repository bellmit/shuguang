/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-03-13 11:25
 */
package com.sofn.common.service;

import com.github.pagehelper.PageInfo;
import com.sofn.common.model.PagingParam;

/**
 * 分页查询服务
 *
 * @author lijiang
 * @version 1.0
 **/
public interface PagingQueryService<T, P extends PagingParam> {
    /**
     * 分页查询
     *
     * @param pagingParam 分页查询参数
     * @return 查询结果
     */
    PageInfo<T> pagingQueryList(P pagingParam);
}
