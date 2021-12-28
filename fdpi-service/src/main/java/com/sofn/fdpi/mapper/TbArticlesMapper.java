package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.TbArticles;
import com.sofn.fdpi.vo.ArticleAllVo;
import com.sofn.fdpi.vo.TbArticlesListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/8 09:16
 */
public interface TbArticlesMapper extends BaseMapper<TbArticles> {
    List<TbArticles> getArticlesList(Map<String, Object> map);
    int deleteArticlesInfo(Map<String, Object> map);
    int updatecount(Map<String, Object> map);
    //根据栏目名称获取文章信息

    TbArticles selectinfoByid(@Param("id")String id);

    List<TbArticles> getArticlesListNews();
    TbArticles selectinfoByC(String essaytitle);
    TbArticles getOne(String id);

    List<ArticleAllVo>  getAllList(Map map);

}
