package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.PictureArticle;
import com.sofn.fdpi.service.PictureArticleService;
import com.sofn.fdpi.vo.PictureArticleVo;
import com.sofn.fdpi.vo.TbArticlesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-28 11:39
 */
@Api(value = "图片文章",tags = "图片文章相关接口")
@RestController
@Slf4j
@RequestMapping(value = "PictureArticle")
public class PictureArticleController extends BaseController {
    @Autowired
    PictureArticleService pictureArticleService;
//    @RequiresPermissions("fdpi:picNews:query")
    @ApiOperation("获取文章信息(分页)")
    @GetMapping("/list")
    @SofnLog("获取文章信息(分页)")
    public Result<List<PictureArticle>> getArticlesList(@ApiParam("文章标题") @RequestParam(required = false) String essaytitle,
                                                        @ApiParam("文章状态") @RequestParam(required = false) String essaystatus,
                                                        @ApiParam("文章栏目") @RequestParam(required = false) String cName,
                                                        @ApiParam("有效日期至(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
                                                        @ApiParam("有效日期至(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
                                                        @RequestParam(value = "pageNo") Integer pageNo,
                                                        @RequestParam(value = "pageSize") Integer pageSize ){
        HashMap<String, Object> map = Maps.newHashMap();
        if (StringUtils.hasText(startTime)) {
            startTime = startTime + " 00:00:00";
        }
        if (StringUtils.hasText(endTime)) {
            endTime = endTime + " 23:59:59";
        }
        map.put("cName",cName);
        map.put("essaytitle",essaytitle);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("essaystatus",essaystatus);
        PageUtils<PictureArticle> pageUtils = pictureArticleService.getArticlesList(map, pageNo,pageSize);
        return Result.ok(pageUtils);

    }

    @ApiOperation("新建文章")
    @SofnLog("新建文章")
    @PostMapping("/save")
    @RequiresPermissions("fdpi:picNews:create")
    public Result saveArticles(@Validated @RequestBody @ApiParam(name = "文章图片信息对象", value = "传入json格式", required = true)
                                       PictureArticleVo tbArticlesVo, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = pictureArticleService.saveArticles(tbArticlesVo);
        return Result.ok();
    }
    @RequiresPermissions("fdpi:picNews:delete")
    @ApiOperation("删除文章")
    @SofnLog("删除文章")
    @PutMapping("/del")
    public Result deleteArticles(@ApiParam("文章id") @RequestParam(required = false) String id){

        String s = pictureArticleService.deleteArticles(id);
        return Result.ok(s);
    }


//    @RequiresPermissions("fdpi:picNews:view")
    @ApiOperation("通过id获取文章详情")
    @SofnLog("通过id获取文章详情")
    @GetMapping("/getInfo")
    public Result<PictureArticle> getArticles(@ApiParam("文章id") @RequestParam("id") String id){


        return Result.ok(pictureArticleService.getArticles(id));
    }

    @RequiresPermissions("fdpi:picNews:update")
    @ApiOperation(" 修改文章内容和图片信息")
    @SofnLog(" 修改文章内容和图片信息")
    @PutMapping("/update")
    public Result updateArticles(@Validated @RequestBody @ApiParam(name = "文章图片信息对象", value = "传入json格式", required = true)
                                             PictureArticle PictureArticle, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = pictureArticleService.updateArticles(PictureArticle);
        return Result.ok(s);
    }
    @RequiresPermissions(value = {"fdpi:picNews:publish","fdpi:picNews:hidden"},logical = Logical.OR)
    @ApiOperation(" 修改文章状态")
    @SofnLog(" 修改文章状态")
    @PutMapping("/updateStatus")
    public Result updateArticles(@ApiParam("文章id") @RequestParam("id") String id,
                                     @ApiParam("文章状态1：发布2：隐藏3：未发布")@RequestParam("status") String status){

    String s = pictureArticleService.updateStatus(id,status);
    return Result.ok(s);
}


    @ApiOperation(" 更新浏览次数")
    @SofnLog(" 更新浏览次数")
    @PutMapping("/updateCount")
    public Result updateCount(@ApiParam("文章id") @RequestParam("id") String id
                                ){

        String s = pictureArticleService.updateCount(id);
        return Result.ok(s);
    }
//    获取轮播页图片信息
//    List<PictureArticle> getRotationSeeding();
    @ApiOperation(" 获取轮播页图片信息")
    @SofnLog(" 获取轮播页图片信息")
    @GetMapping("/rotationSeeding")
    public Result<List<PictureArticle>> getRotationSeeding(
    ){
        return Result.ok(pictureArticleService.getRotationSeeding());
    }
}
