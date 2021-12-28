package com.sofn.agsjdm.service;

import com.sofn.agsjdm.model.KnowledgeBaseAttachment;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:35
 */
public interface KnowledgeBaseAttachmentService {
    int save(KnowledgeBaseAttachment map);
    int del(String id);
    void update(Map map);
    KnowledgeBaseAttachment getSource(String id);
}
