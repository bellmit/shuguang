package com.sofn.dhhrp.service;


import com.sofn.common.utils.PageUtils;
import com.sofn.dhhrp.model.IndexPar;
import com.sofn.dhhrp.vo.DropDownVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-28 14:07
 */
public interface IndexParService {
    void save(IndexPar indexpar);
    IndexPar get(String id);
    void update(IndexPar indexpar);
    void delete(String id);
    PageUtils<IndexPar> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
    List<DropDownVo> listName();
}
