package com.sofn.agpjyz.mapper;

import com.sofn.agpjyz.model.KnowledgeBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:31
 */
public interface KnowledgeBaseMapper {
    int save(Map map);
    KnowledgeBase getNameAndType(Map map);
    List<KnowledgeBase> list(Map map);
    KnowledgeBase getOne(@Param("id") String id);
    int  del(@Param("id") String id);
    int update(Map map);
}
