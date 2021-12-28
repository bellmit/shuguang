package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.KnowledgeBaseAttachment;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 11:16
 */
@Repository
public interface KnowledgeBaseAttachmentMapper extends BaseMapper<KnowledgeBaseAttachment> {

    int del(String id);
    int updateKnow(Map map);
    KnowledgeBaseAttachment getOne(String id);
    KnowledgeBaseAttachment getOneBySouceId1(String id);
}
