package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.KnowledgeBase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 11:17
 */
@Repository
public interface KnowledgeBaseMapper extends BaseMapper<KnowledgeBase> {

    KnowledgeBase getNameAndType(Map map);
    List<KnowledgeBase> list(Map map);


}
