package com.sofn.agpjyz.mapper;

import com.sofn.agpjyz.model.KnowledgeBaseAttachment;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:32
 */
public interface KnowledgeBaseAttachmentMapper {
    int save(Map map);
    int del(String id);
    int update(Map map);
    KnowledgeBaseAttachment getOne(String id);
    KnowledgeBaseAttachment getOneBySouceId(String id);
}
