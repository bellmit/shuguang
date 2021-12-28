package com.sofn.fdpi.service;

import com.sofn.fdpi.model.PeritoneumSpec;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-24 14:38
 */
public interface PeritoneumSpecService {
    void save(List<PeritoneumSpec> ps,String peritoneumId);
    void del(String peritoneumId);
    void update(List<PeritoneumSpec> ps,String peritoneumId);
    List<PeritoneumSpec>  get(String peritoneumId);
}
