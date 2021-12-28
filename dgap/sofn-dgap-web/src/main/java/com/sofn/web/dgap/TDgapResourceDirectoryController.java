package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.dgap.Tree;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceDirectory;
import com.sofn.service.dgap.TDgapResourceDirectoryService;
import com.sofn.service.dgap.TDgapResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/** Created by Administrator on 2016/10/20. */
@RestController
@Api(value = "资源目录", description = "资源目录")
@RequestMapping(value = "/resourceDirectory")
public class TDgapResourceDirectoryController extends BaseController {
  @Autowired private TDgapResourceDirectoryService dgapResourceDirectoryService;
  @Autowired private TDgapResourceService tDgapResourceService;
  /**
   * 资源目录分页列表
   *
   * @param name
   * @param start
   * @param length
   * @return
   */
  @ApiOperation(value = "资源目录")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)分页显示资源目录", operationType = "分页")
  @Authorization
  public Object list(String name, int start, int length) {
    if(name.length()>30){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    PageInfo<Map<String, Object>> list =
        dgapResourceDirectoryService.list(name.trim(), ((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "资源目录列表")
  @RequestMapping(value = "/all")
  @SystemControllerLog(description = "(共享系统)查询资源目录列表", operationType = "查询")
  public Object resDirectoryList(HttpServletRequest request) {
    List<Map<String, Object>> list = dgapResourceDirectoryService.list();
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "资源目录修改数据回显根据id查询")
  @RequestMapping(value = "/updateList")
  @SystemControllerLog(description = "(共享系统)根据id查询资源目录", operationType = "查询")
  public Object updateList(@RequestBody TDgapResourceDirectory dir) {
    TDgapResourceDirectory tDgapResource = dgapResourceDirectoryService.queryById(dir.getId());
    return setSuccessModelMap(new ModelMap(), tDgapResource);
  }

  @ApiOperation(value = "资源目录新增")
  @RequestMapping(value = "/add")
  @SystemControllerLog(description = "(共享系统)资源目录新增", operationType = "新增")
  public Object addResourceDirectory(@RequestBody TDgapResourceDirectory resourceDir) {
    if(resourceDir.getName().length()>30){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resourceDir.getDescription().length()>200){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if (dgapResourceDirectoryService.getByDirName(resourceDir.getName())) {
      return setSuccessModelMap(new ModelMap(), "该资源目录已存在");
    }
    if (dgapResourceDirectoryService.getByDirNameY(resourceDir.getName()).size() > 0) {
      // 需要查询出原有数据,再更新最新数据
      TDgapResourceDirectory oldResource =
          dgapResourceDirectoryService.getByDirNameY(resourceDir.getName()).get(0);
      if (oldResource != null) {
        oldResource.setDelFlag("N");
        oldResource.setUpdateTime(new Date());
        oldResource.setCreateTime(new Date());
        oldResource.setName(resourceDir.getName());
        oldResource.setDescription(resourceDir.getDescription());
        dgapResourceDirectoryService.update(oldResource);
        return setSuccessModelMap(new ModelMap(), HttpCode.OK);
      }
    }
    resourceDir.setDelFlag("N");
    TDgapResourceDirectory add = dgapResourceDirectoryService.add(resourceDir);
    if (add == null) {
      return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    } else {
      return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
    }
  }
  /**
   * 修改资源
   *
   * @param resourceDir
   * @return
   */
  @ApiOperation(value = "修改资源目录")
  @RequestMapping(value = "/update")
  @SystemControllerLog(description = "(共享系统)修改资源目录", operationType = "修改")
  public Object updateResourceDir(@RequestBody TDgapResourceDirectory resourceDir) {
    if(resourceDir.getName().length()>30){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(resourceDir.getDescription().length()>200){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if (StringUtils.isNotBlank(resourceDir.getId())) {
      // 需要查询出原有数据,再更新最新数据
      TDgapResourceDirectory oldResource =
          dgapResourceDirectoryService.queryById(resourceDir.getId());
      StringBuilder sb = new StringBuilder();
      if (oldResource.getName().equals(resourceDir.getName())) {
        if (oldResource.getDescription().equals(resourceDir.getDescription())) {
          return setSuccessModelMap(new ModelMap(), "");
        } else {
          oldResource.setDescription(resourceDir.getDescription());
          dgapResourceDirectoryService.update(oldResource);
          return setSuccessModelMap(new ModelMap(), HttpCode.OK);
        }
      }
      if (dgapResourceDirectoryService.getByDirName(resourceDir.getName())) {
        return setSuccessModelMap(new ModelMap(), "该资源目录已存在");
      }

      while (dgapResourceDirectoryService.getByDirNameY(resourceDir.getName()).size() > 0) {
        TDgapResourceDirectory tDgapResourceDirectory =
            dgapResourceDirectoryService.getByDirNameY(resourceDir.getName()).get(0);
        sb.append(tDgapResourceDirectory.getName());

        sb.append("1");
        while (dgapResourceDirectoryService.getByDirNameY(sb.toString()).size() > 0
            || dgapResourceDirectoryService.getByDirName(sb.toString())) {
          sb.append("1");
        }
        if (dgapResourceDirectoryService.getByDirNameY(sb.toString()).size() <= 0
            && !dgapResourceDirectoryService.getByDirName(sb.toString())) {
          tDgapResourceDirectory.setName(sb.toString());
          dgapResourceDirectoryService.update(tDgapResourceDirectory);
        }
      }
      if (oldResource != null) {
        oldResource.setName(resourceDir.getName());
        oldResource.setDescription(resourceDir.getDescription());
        dgapResourceDirectoryService.update(oldResource);
      } else {
        return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
      }
    }
    return setSuccessModelMap(new ModelMap(), HttpCode.OK);
  }
  /**
   * 删除资源
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "删除资源目录")
  @RequestMapping(value = "/delete")
  @SystemControllerLog(description = "(共享系统)删除资源目录", operationType = "删除")
  public Object deleteResourceDir(String id) {
    List<TDgapResource> resourceByDirId = tDgapResourceService.getResourceByDirId(id);
    TDgapResourceDirectory tDgapResource = dgapResourceDirectoryService.queryById(id);
    if (tDgapResource != null && resourceByDirId.size() == 0) {
      tDgapResource.setDelFlag("Y");
      dgapResourceDirectoryService.update(tDgapResource);
      return setModelMap(new ModelMap(), HttpCode.OK);
    } else {
      return setModelMap(new ModelMap(), HttpCode.FORBIDDEN, "该目录不能删除");
    }
  }
  /**
   * 删除资源
   *
   * @return
   */
  @ApiOperation(value = "批量删除资源目录")
  @RequestMapping(value = "/deletes")
  @SystemControllerLog(description = "(共享系统)批量删除资源目录", operationType = "删除")
  public Object deleteResourceDirs(String dataInfo) {
    ModelMap modelMap = new ModelMap();
    String[] jsonStr = dataInfo.split("\\|");
    int number = jsonStr.length;
    for (int i = 0; i < jsonStr.length; i++) {
      logger.debug(jsonStr[i] + "  ");
      String oStr = jsonStr[i].toString();
      List<TDgapResource> resourceByDirId = tDgapResourceService.getResourceByDirId(oStr);
      if (oStr == null || oStr.equals("") || resourceByDirId.size() != 0) {
        number--;
        continue;
      }
      TDgapResourceDirectory tDgapResourceDirectory = dgapResourceDirectoryService.queryById(oStr);
      tDgapResourceDirectory.setDelFlag("Y");
      dgapResourceDirectoryService.update(tDgapResourceDirectory);
    }
    return setSuccessModelMap(modelMap, number);
  }

  @ApiOperation("资源目录树结构")
  @RequestMapping("/tree")
  @ResponseBody
  @SystemControllerLog(description = "(共享系统)查询资源目录树", operationType = "查询")
  public Tree getTree() {
    List<TDgapResourceDirectory> list = this.dgapResourceDirectoryService.getAlldirs();
    return buildDgapDirectoryTree(list);
  }

  private Tree buildDgapDirectoryTree(List<TDgapResourceDirectory> list) {
    Tree tree = new Tree("1", "资源目录");
    for (TDgapResourceDirectory directory : list) {
      Tree tree1 = new Tree(directory.getId(), directory.getName());
      tree.addChild(tree1);
    }
    return tree;
  }
}
