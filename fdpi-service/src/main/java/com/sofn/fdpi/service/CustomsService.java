package com.sofn.fdpi.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Customs;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 15:07
 */
public interface CustomsService {
    /**
     * c插入
     * @param customs 海关对象
     */
    void save(Customs customs);

    /**
     * x修改
     * @param customs 海关对象
     */
    void update(Customs customs);

    /**
     *
     * @param id 主键id
     * @return 濒管办信息
     */
    Customs get(String id);

    /**
     *
     * @param id 主键id
     */
    void del(String id);

    /**
     *
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<Customs> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
}
