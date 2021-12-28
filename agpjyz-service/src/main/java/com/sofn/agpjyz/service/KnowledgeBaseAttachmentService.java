package com.sofn.agpjyz.service;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:35
 */
public interface KnowledgeBaseAttachmentService {
    int save(Map map);
    int del(String id);
    void update(Map map);
}
