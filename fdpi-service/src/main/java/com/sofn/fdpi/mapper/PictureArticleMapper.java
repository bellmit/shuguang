package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PictureArticle;
import com.sofn.fdpi.model.TbArticles;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-28 11:33
 */
public interface PictureArticleMapper extends BaseMapper<PictureArticle> {
//    分页
    List<PictureArticle> getArticlesList(Map<String, Object> map);
//    删除
    int deleteArticles(String id);
//    获取当前公开文章的数目
    List<PictureArticle> getSize();
//    修改当前文章的状态
    int updateStatus(Map map);
//    修改浏览次数
    int updatecount(Map<String, Object> map);

    List<PictureArticle> getLubBo();
    PictureArticle getOne(String id);
    PictureArticle getOne1(String essaytitle);

}
