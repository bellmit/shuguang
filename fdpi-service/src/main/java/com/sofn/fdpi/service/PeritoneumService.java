package com.sofn.fdpi.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Peritoneum;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-24 14:28
 */
public interface PeritoneumService {
    /**
     * c插入
     * @param peritoneum 濒管办对象
     */
    void save(Peritoneum peritoneum);

    /**
     * x修改
     * @param peritoneum 濒管办对象
     */
    void update(Peritoneum peritoneum);

    /**
     *
     * @param id 主键id
     * @return 濒管办信息
     */
    Peritoneum get(String id);

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
    PageUtils<Peritoneum> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
}
