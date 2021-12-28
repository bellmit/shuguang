package com.sofn.ahhrm.service;

import com.sofn.ahhrm.model.IndexPar;
import com.sofn.ahhrm.model.Threshold;
import com.sofn.ahhrm.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-28 14:07
 */
public interface IndexParService {
    void save(IndexPar indexpar);
    IndexPar get(String  id);
    void update(IndexPar indexpar);
    void delete(String id);
    PageUtils<IndexPar> listPage(Map<String, Object> params,Integer pageNo, Integer pageSize);
    List<DropDownVo> listName();
}
