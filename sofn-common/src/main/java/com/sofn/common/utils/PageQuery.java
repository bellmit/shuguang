package com.sofn.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询
 */
@Data
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码，从1开始
     */
    private int pageNum;

    /**
     * 页面大小
     */
    private int pageSize;

}
