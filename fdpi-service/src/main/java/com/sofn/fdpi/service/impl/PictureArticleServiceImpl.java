package com.sofn.fdpi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.mapper.PictureArticleMapper;
import com.sofn.fdpi.model.PictureArticle;
import com.sofn.fdpi.model.TbArticles;
import com.sofn.fdpi.service.PictureArticleService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.FileUtil;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.vo.PictureArticleVo;
import com.sofn.fdpi.vo.SysFileVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-28 11:37
 */
@Transactional
@Service("pictureArticleService")
public class PictureArticleServiceImpl extends BaseService<PictureArticleMapper, PictureArticle> implements PictureArticleService {
    @Autowired
    private PictureArticleMapper pictureArticleMapper;

    @Autowired
    private SysRegionApi sysRegionApi;
    @Resource
    private FileUtil fileUtil;

    @Override
    public PageUtils<PictureArticle> getArticlesList(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<PictureArticle> articlesList = pictureArticleMapper.getArticlesList(map);
        PageInfo<PictureArticle> pageInfo = new PageInfo<>(articlesList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveArticles(PictureArticleVo tbArticlesVo) {
        RedisUserUtil.validReSubmit("fdpi_pnews_save");
//        ???????????????????????????
        PictureArticle pictureArticles = pictureArticleMapper.getOne1((String) tbArticlesVo.getEssaytitle());
        if (pictureArticles != null) {
            throw new SofnException("???????????????");
        }
//        ????????????????????????????????????????????????
        List<PictureArticle> pic = pictureArticleMapper.getSize();
        String essaystatus = tbArticlesVo.getEssaystatus();
//        ???????????????????????????
        if ("1".equals(essaystatus)) {
            if (pic.size() > 5 || pic.size() == 5) {
                throw new SofnException("????????????????????????????????????????????????????????????5");
            }
        }
//        ????????????
        fileUtil.activationFile(tbArticlesVo.getFileId());
        PictureArticle pa = new PictureArticle();
        BeanUtils.copyProperties(tbArticlesVo, pa);
        pa.setCreateTime(new Date());
        pa.setCreateUserId(UserUtil.getLoginUserId());
        pa.setDelFlag("N");
        pa.setEssaycount(0);
        int insert = pictureArticleMapper.insert(pa);
        return "????????????";

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteArticles(String id) {
        PictureArticle pictureArticle = pictureArticleMapper.getOne(id);
        pictureArticleMapper.deleteArticles(id);
        sysRegionApi.delFile(pictureArticle.getFileId());
        return "????????????";
    }

    @Override
    public PictureArticle getArticles(String id) {
        PictureArticle pic = pictureArticleMapper.getOne(id);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        map.put("count", pic.getEssaycount() + 1);
        pictureArticleMapper.updatecount(map);
        return pictureArticleMapper.getOne(id);
    }

    @Override
    public String updateArticles(PictureArticle tbArticlesVo) {
        RedisUserUtil.validReSubmit("fdpi_pnews_update");
//        ??????????????????
        PictureArticle pictureArticle = pictureArticleMapper.getOne(tbArticlesVo.getId());
        if (tbArticlesVo.getFileId() != pictureArticle.getFileId()) {
            sysRegionApi.delFile(pictureArticle.getFileId());
            SysFileManageForm sfmf = new SysFileManageForm();
            sfmf.setIds(tbArticlesVo.getFileId());
            sfmf.setInterfaceNum("hidden");
            sfmf.setSystemId(Constants.SYSTEM_ID);
            try {
                Result<List<SysFileVo>> result = sysRegionApi.activationFile(sfmf);
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("??????????????????!");
                }
            } catch (Exception e) {
                throw new SofnException("??????????????????!");
            }
        }
        pictureArticleMapper.updateById(tbArticlesVo);

        return "????????????";
    }

    @Override
    public String updateStatus(String id, String status) {
//        ?????????????????????=5?????????????????????1,
//        status???1 ?????? 2?????????  3.?????????
        List<PictureArticle> pic = pictureArticleMapper.getSize();
        int size = pic.size();

        if ((size > 5 || size == 5) && status.equals("1")) {
            throw  new SofnException("??????5?????????????????????????????????");
        } else {

            Map map = Maps.newHashMap();
            map.put("id", id);
            map.put("status", status);
            int i = pictureArticleMapper.updateStatus(map);
            if (i == 1) {
                return "????????????";
            } else {
             throw  new SofnException("????????????");
            }
        }


    }

    @Override
    public String updateCount(String id) {
        PictureArticle pictureArticle = pictureArticleMapper.selectById(id);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        map.put("count", pictureArticle.getEssaycount() + 1);
        int c = pictureArticleMapper.updatecount(map);
        if (c == 1) {
            return "1";
        }
        throw new SofnException("????????????????????????");
    }

    @Override
    public List<PictureArticle> getRotationSeeding() {
        return pictureArticleMapper.getLubBo();
    }

}
