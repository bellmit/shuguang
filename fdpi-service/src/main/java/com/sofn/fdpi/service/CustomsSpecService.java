package com.sofn.fdpi.service;

import com.sofn.fdpi.model.CustomsSpec;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 15:11
 */
public interface CustomsSpecService {
    void save(List<CustomsSpec> ps, String customsId);
    void del(String customsId);
    void update(List<CustomsSpec> ps,String customsId);
    List<CustomsSpec>  get(String customsId);
}
