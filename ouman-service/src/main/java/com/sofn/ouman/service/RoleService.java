package com.sofn.ouman.service;


import com.sofn.ouman.vo.SelectVo;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 获取企业类型list
     */
    List<SelectVo> listCompType();

    /**
     * 获取企业类型Map key-类型id val-类型名称
     */
    Map<String, String> mapComeType();

}
