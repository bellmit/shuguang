package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.TbArticles;
import com.sofn.fdpi.vo.ArticleAllVo;
import com.sofn.fdpi.vo.TbArticlesListVo;
import com.sofn.fdpi.vo.TbArticlesVo;

import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/8 09:19
 */
public interface TbArticlesService extends IService<TbArticles> {
    PageUtils<TbArticles> getArticlesList(Map<String, Object> map, int pageNo, int pageSize);

    String saveArticles(TbArticlesVo tbArticlesVo);

    int deleteArticles(String id);

    TbArticles getArticles(String id);

    String updateArticles(TbArticlesVo tbArticlesVo);

    String updateStatus(String id, String status);

    String updateCount(String id);

    PageUtils<TbArticles> getArticlesListNews( int pageNo, int pageSize);

    /** XiaoBo
     * 追加需求 从图片文章和 一般文章管理 进行文章内容的模糊查询
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<ArticleAllVo> getList(Map<String, Object> map, int pageNo, int pageSize);

}
