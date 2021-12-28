package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.TbArticles;
import com.sofn.fdpi.service.TbArticlesService;
import com.sofn.fdpi.vo.ArticleAllVo;
import com.sofn.fdpi.vo.ArticlesVo;
import com.sofn.fdpi.vo.TbArticlesListVo;
import com.sofn.fdpi.vo.TbArticlesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
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
 * @Auther: Bay
 * @Date: 2020/1/8 09:21
 */
@RestController
@Api(value = "一般文章",tags = "一般文章管理接口")
@Slf4j
@RequestMapping("TbArticles")
public class TbArticlesController extends BaseController {
    @Autowired
    TbArticlesService asService;
//    @RequiresPermissions("fdpi:news:query")
    @ApiOperation("获取文章信息(分页)")
    @GetMapping("/list")
    @SofnLog("获取文章信息(分页)")
     public Result<List<TbArticles>> getArticlesList(@ApiParam(name = "essaystatus",value = "文章状态 1：发布2：隐藏3：未发布")@RequestParam(value = "essaystatus",required = false)String essaystatus,
                                                           @ApiParam(name = "essaytitle",value = "标题")@RequestParam(value = "essaytitle",required = false)String essaytitle,
                                                           @ApiParam(name = "cName",value = "栏目名")@RequestParam(value = "cName",required = false)String cName,
                                                           @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
                                                           @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
                                                           @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                           @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){
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
        PageUtils<TbArticles> pageUtils = asService.getArticlesList(map,pageNo ,pageSize );
        return Result.ok(pageUtils);

    }
    @RequiresPermissions("fdpi:news:create")
    @ApiOperation("新建文章")
    @SofnLog("新建文章")
    @PostMapping("/save")
    public Result saveArticles(@Validated @RequestBody @ApiParam(name = "一般文章信息对象", value = "传入json格式", required = true)
                                       TbArticlesVo tbArticlesVo, BindingResult result){
        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String s = asService.saveArticles(tbArticlesVo);
        if ("1".equals(s)){
            return Result.ok("新增文章成功");
        }
        return Result.error(s);
    }
    @RequiresPermissions("fdpi:news:delete")
    @ApiOperation("删除文章")
    @SofnLog("删除文章")
    @DeleteMapping("/delete")
    public Result deleteArticles(@RequestParam(value = "id") @ApiParam(name = "id", value = "罚没id",required = true)String id){
        int i = asService.deleteArticles(id);
        if (i==1) {
            return Result.ok("删除文章成功");
        }
        return Result.error("不能删除公布文章");
    }
    @RequiresPermissions("fdpi:news:update")
    @ApiOperation("修改文章信息")
    @SofnLog("修改文章信息")
    @PostMapping("/update")
    public Result updateArticles(@Validated @RequestBody @ApiParam(name = "文章对象",
            value = "传入json格式", required = true) TbArticlesVo tbArticlesVo, BindingResult result){
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String s = asService.updateArticles(tbArticlesVo);
        if ("1".equals(s)){
            return Result.ok("修改文章信息成功");
        }
        return Result.error(s);
    }

//    @RequiresPermissions("fdpi:news:view")
    @ApiOperation(value = " 根据id查询文章详细信息")
    @GetMapping("/get")
    @SofnLog("根据id查询文章详细信息")
    public Result<TbArticles> getArticles(@RequestParam(value = "id")String id){
        TbArticles articles = asService.getArticles(id);
        return Result.ok(articles);
    }

    @RequiresPermissions(value = {"fdpi:news:publish","fdpi:news:hidden"},logical = Logical.OR)
    @ApiOperation("修改文章公开状态")
    @PutMapping("status")
    @SofnLog("修改文章公开状态")
    public Result updatestatus(@ApiParam(name = "id",value = "主键id")@RequestParam(value = "id")String id,
                               @ApiParam(name = "status",value = "1：发布2：隐藏3：未发布")@RequestParam(value = "status")String status){
        String s = asService.updateStatus(id, status);
        if ("1".equals(s)) {
            return Result.ok("修改公开状态成功");
        }
        return Result.error(s);
    }
    @ApiOperation("更新文章浏览次数")
    @PutMapping("/count")
    @SofnLog("更新文章浏览次数")
    public Result updateCount(@RequestParam(value = "id")String id){
        String s = asService.updateCount(id);
        if ("1".equals(s)){
            return Result.ok("更新文章浏览次数");
        }
        return Result.error("更新浏览次数失败");
    }


    @ApiOperation("最新消息")
    @GetMapping("/listNews")
    @SofnLog("获取文章最新消息(分页)")
    public Result<List<TbArticles>> getArticlesListNews(
                                                    @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                    @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){

        PageUtils<TbArticles> pageUtils = asService.getArticlesListNews(pageNo ,pageSize );
        return Result.ok(pageUtils);

    }



    @ApiOperation("获取全局文章信息(分页)（单开的接口）")
    @GetMapping("/getAllList")
    @SofnLog("获取文章信息(分页)")
    public Result<PageUtils<ArticleAllVo>> List(@ApiParam(name = "essaycontent",value = "文章内容")@RequestParam(value = "essaycontent",required = false)String essaycontent,
                                           @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                           @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize){
        HashMap<String, Object> map = Maps.newHashMap();

        map.put("essaycontent",essaycontent);
        PageUtils<ArticleAllVo> pageUtils = asService.getList(map,pageNo ,pageSize );
        return Result.ok(pageUtils);

    }

}
