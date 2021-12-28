package com.sofn.agpjyz.service;


import com.sofn.agpjyz.model.Halophytes;
import com.sofn.agpjyz.model.KnowledgeBase;
import com.sofn.agpjyz.vo.KnowledgeVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:34
 */
public interface KnowledgeBaseService {
    int save(KnowledgeVo k);
    PageUtils<KnowledgeBase> list(Map map, int pageNo, int pageSize);
    KnowledgeBase getOne(String id);
    int del(String id);
    void update(KnowledgeVo know);

}
