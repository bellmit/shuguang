package com.sofn.ducss.service;

import com.sofn.ducss.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/10/9  16:47
 * @description ES聚合条件查询
 **/
public interface SearchService {
    /**
     * 全文检索
     * @param paramMap  查询参数
     * @return
     */
  /*  public List search(Map<String, String> paramMap) throws Exception;*/
    PageUtils search2(Map<String, String> paramMap) throws Exception;
}
