package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.PictureArticle;
import com.sofn.fdpi.model.TbArticles;
import com.sofn.fdpi.vo.PictureArticleVo;
import com.sofn.fdpi.vo.TbArticlesVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-28 11:36
 */
public interface PictureArticleService extends IService<PictureArticle> {
//    条件分页
    PageUtils<PictureArticle> getArticlesList(Map<String, Object> map, int pageNo, int pageSize);
//    新增
    String saveArticles(PictureArticleVo tbArticlesVo);
//    删除文章，修改文章状态，删除支撑平台文件，
    String deleteArticles(String id);

    //通过id获取文章详情
    PictureArticle getArticles(String id);
//    修改文章内容和图片信息，并替换支撑平台文件信息
    String updateArticles(PictureArticle tbArticlesVo);

// 修改文章的状态
    String updateStatus(String id, String status);
// 更新浏览次数
    String updateCount(String id);
//    获取轮播页图片信息
    List<PictureArticle> getRotationSeeding();
}
