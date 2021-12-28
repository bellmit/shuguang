package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.mapper.TbArticlesMapper;
import com.sofn.fdpi.model.Essaycolumn;
import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.model.TbArticles;
import com.sofn.fdpi.service.EssaycolumnService;
import com.sofn.fdpi.service.FileManageService;
import com.sofn.fdpi.service.TbArticlesService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/8 09:20
 */
@Slf4j
@Service
public class TbArticlesServiceImpl extends BaseService<TbArticlesMapper, TbArticles> implements TbArticlesService {
    @Autowired
    TbArticlesMapper articlesMapper;
    @Autowired
    EssaycolumnService eService;

    /**
     * 获取文章信息(分页)
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<TbArticles> getArticlesList(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<TbArticles> articlesList = articlesMapper.getArticlesList(map);
        PageInfo<TbArticles> pageInfo = new PageInfo<>(articlesList);
        return PageUtils.getPageUtils(pageInfo);
    }

    /**
     * 增加文章
     *
     * @param tbArticlesVo
     * @return
     */
    @Override
    public String saveArticles(TbArticlesVo tbArticlesVo) {
        RedisUserUtil.validReSubmit("fdpi_news_save");
        TbArticles tbArticles = articlesMapper.selectinfoByC(tbArticlesVo.getEssaytitle());
        if (tbArticles == null) {
            TbArticles articles = tbArticlesVo.convertToModel(TbArticles.class);
            articles.preInsert();
            tbArticlesVo.setId(articles.getId());
            int insert = articlesMapper.insert(articles);
            if (insert == 1) {
                return "1";
            }
        } else {
            throw new SofnException("该文章标题已存在");
        }
        throw new SofnException("新增文章失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteArticles(String id) {
        TbArticles articles = this.getArticles(id);
        int i = 0;
        if (!"1".equals(articles.getEssaystatus())) {
            HashMap<String, Object> map = Maps.newHashMap();
            map.put("id", id);
            map.put("updateTime", new Date());
            map.put("updateUserId", UserUtil.getLoginUserId());
            i = articlesMapper.deleteArticlesInfo(map);
        }
        return i;
    }

    @Override
    public TbArticles getArticles(String id) {
        TbArticles a = articlesMapper.selectById(id);
        Map map = new HashMap();
        map.put("id", id);
        map.put("count", a.getEssaycount() + 1);
        int c = articlesMapper.updatecount(map);
        TbArticles articles = articlesMapper.selectById(id);
        return articles;
    }

    @Override
    public String updateArticles(TbArticlesVo tbArticlesVo) {
        RedisUserUtil.validReSubmit("fdpi_news_update");
        TbArticles articles = this.getArticles(tbArticlesVo.getId());
        if (articles == null) {
            return "该文章不存在";
        }
        QueryWrapper<TbArticles> qr = new QueryWrapper<>();
        qr.eq("ESSAYTITLE", tbArticlesVo.getEssaytitle()).eq("DEL_FLAG", "N").ne("ID", tbArticlesVo.getId());
        TbArticles tbArticles1 = articlesMapper.selectOne(qr);
        if (tbArticles1 != null) {
            throw new SofnException("文章标题已存在");
        }
        TbArticles tbArticles = tbArticlesVo.convertToModel(TbArticles.class);
        tbArticles.preUpdate();
        boolean isSuccess = this.updateById(tbArticles);
        if (isSuccess) {
            return "1";
        }
        throw new SofnException("修改文章信息失败");
    }

    @Override
    public String updateStatus(String id, String status) {
        TbArticles articles = articlesMapper.selectById(id);
        articles.preUpdate();
        articles.setEssaystatus(status);
        boolean b = this.updateById(articles);
        if (b) {
            return "1";
        }
        throw new SofnException("修改隐藏失败");
    }

    @Override
    public String updateCount(String id) {
        TbArticles articles = this.getArticles(id);
        if (articles == null) {
            return "该文章不存在";
        }
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        map.put("count", articles.getEssaycount() + 1);
        int c = articlesMapper.updatecount(map);
        if (c == 1) {
            return "1";
        }
        throw new SofnException("更新浏览次数失败");
    }

    @Override
    public PageUtils<TbArticles> getArticlesListNews(int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<TbArticles> articlesList = articlesMapper.getArticlesListNews();
        PageInfo<TbArticles> pageInfo = new PageInfo<>(articlesList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public PageUtils<ArticleAllVo> getList(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<ArticleAllVo> allList = articlesMapper.getAllList(map);
        PageInfo<ArticleAllVo> pageInfo = new PageInfo<>(allList);
        return PageUtils.getPageUtils(pageInfo);
    }

}
