package com.sofn.ducss.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.excel.properties.ExcelPropertiesUtils;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.fileutil.FileDownloadUtils;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.model.SysUser;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysDictService;
import com.sofn.ducss.service.SysDictTypeService;
import com.sofn.ducss.service.SysUserService;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.SysDictAllVo;
import com.sofn.ducss.vo.SysDictTypeVo;
import com.sofn.ducss.vo.SysDictVo;
import com.sofn.ducss.vo.SysUserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * zhouqingchun
 * 2019/5/29
 */
@RestController
@Api(tags = "字典管理接口")
@RequestMapping("/dict")
//@RequestMapping("/sys/dict")
public class DictController extends BaseController {

    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "查询字典列表接口", notes = "权限码(sys:dict:index)")
    @RequestMapping(value = "/getDictInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<PageUtils<HashMap<String, Object>>> getDictInfo(@RequestParam(name = "typekeyname", required = false) @ApiParam(value = "字典类型名称或者字典名称关键字") String typekeyname,
                                                                  @RequestParam(name = "pageNo", required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                                                  @RequestParam(name = "pageSize", required = false) @ApiParam(value = "每页显示条数") Integer pageSize) {

        PageUtils<HashMap<String, Object>> res = sysDictService.getDictInfo(typekeyname, pageNo, pageSize);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据字典名称查询字典数据接口", notes = "权限码(sys:dict:getDictByName)")
//    @RequiresPermissions("sys:dict:getDictByName")
    @ResponseBody
    @RequestMapping(value = "/getDictByName", method = RequestMethod.GET)
    public Result<List<SysDict>> getDictByName(@ApiParam(required = true, value = "字典名称") @RequestParam(value = "dictname") String dictname) {
        List<SysDict> res = sysDictService.getDictByName(dictname);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据字典值和类型查询字典数据接口", notes = "权限码(sys:dict:getDictByValueAndType)")
//    @RequiresPermissions("sys:dict:getDictByValueAndType")
    @RequestMapping(value = "/getDictByValueAndType", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SysDict>> getDictByValueAndType(@ApiParam(required = true, value = "字典类型ID") @RequestParam(value = "dicttypeid") String dicttypeid,
                                                       @ApiParam(value = "字典名称") @RequestParam(value = "dictname", required = false) String dictname,
                                                       @ApiParam(value = "字典值") @RequestParam(value = "dictcode", required = false) String dictcode) {
        List<SysDict> res = sysDictService.getDictByValueAndType(dicttypeid, dictname, dictcode);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据字典值和类型查询字典名称数据接口", notes = "权限码(sys:dict:getDictNameByValueAndType)")
//    @RequiresPermissions("sys:dict:getDictNameByValueAndType")
    @RequestMapping(value = "/getDictNameByValueAndType", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SysDict>> getDictNameByValueAndType(@ApiParam(required = true, value = "字典类型名称") @RequestParam(value = "typename") String typename,
                                                           @ApiParam(value = "字典值") @RequestParam(value = "dictcode", required = false) String dictcode) {
        List<SysDict> res = sysDictService.getDictNameByValueAndType(typename, dictcode);
        return Result.ok(res);
    }

    @ApiOperation(value = "增加字典数据接口", notes = "权限码(sys:dict:create)")
//    @RequiresPermissions("sys:dict:saveDictInfo")
    @RequestMapping(value = "/saveDictInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveDictInfo(@ApiParam(value = "字典ID") @RequestParam(value = "id", required = false) String id,
                                       @ApiParam(required = true, value = "字典名字") @RequestParam(value = "dictname") String dictname,
                                       @ApiParam(required = true, value = "启用禁用") @RequestParam(value = "enable") String enable,
                                       @ApiParam(value = "备注") @RequestParam(value = "remark", required = false) String remark) {

        String dicttypeid = "100000";
        SysDictVo sysDictVo = new SysDictVo();
        SysDictTypeVo sysDictTypeVo = new SysDictTypeVo();
        sysDictTypeVo.setId(dicttypeid);
        sysDictVo.setId(id);
        sysDictVo.setSysDictTypeVo(sysDictTypeVo);
        sysDictVo.setDictname(dictname);
        sysDictVo.setEnable(enable == null ? "Y" : enable);
        sysDictVo.setRemark(remark);

        SysDict dictDb = sysDictService.getAllByName(dicttypeid, dictname);
        String msg = sysDictService.saveOrUpdateDict(sysDictVo, dictDb);
        return Result.ok(msg);
    }

    @ApiOperation(value = "修改字典数据接口", notes = "权限码(sys:dict:update)")
    @ResponseBody
//    @RequiresPermissions("sys:dict:updateDictInfo")
    @RequestMapping(value = "/updateDictInfo", method = RequestMethod.PUT)
    public Result<String> updateDictInfo(@ApiParam(required = true, value = "字典ID") @RequestParam(value = "id") String id,
                                         @ApiParam(required = true, value = "字典状态") @RequestParam(value = "enable") String enable) {
        sysDictService.updateDictInfo(id, enable);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "刪除字典数据接口", notes = "权限码(sys:dict:delete)")
//    @RequiresPermissions("sys:dict:deleteDictInfo")
    @RequestMapping(value = "/deleteDictInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<String> deleteDictInfo(@ApiParam(required = true, value = "字典ID") @RequestParam(value = "ids") String ids) {
        sysDictService.deleteDictInfo(ids);
        return Result.ok("删除成功");
    }

/*    @ApiOperation(value = "查询当前类型下的所有字典", notes = "权限码(sys:dict:getDictListByType)")
    //    @RequiresPermissions("sys:dict:getDictListByType")
    @ResponseBody
    @GetMapping(value = "/getDictListByType")
    public Result<List<SysDict>> getDictListByTypeV1(@ApiParam(required = true, value = "字典类型值") @RequestParam(value = "typevalue") String typevalue) {
        List<SysDict> sysDictList = sysDictService.getDictListByType(typevalue);
        return Result.ok(sysDictList);
    }*/

    @ApiOperation(value = "查询当前类型下的所有字典", notes = "权限码(sys:dict:getDictListByType)")
    @ResponseBody
    @GetMapping(value = "/getDictListByType")
    public Result<PageUtils<SysDict>> getDictListByTypeV2(@RequestParam(name = "keyword", required = false) @ApiParam(value = "作物种类") String keyword,
                                                          @RequestParam(name = "pageNo", required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                                          @RequestParam(name = "pageSize", required = false) @ApiParam(value = "每页显示条数") Integer pageSize,
                                                          @RequestParam(name = "typevalue", required = false) @ApiParam(value = "typevalue") String typevalue) {
        if (pageNo != null && pageSize != null) {
            PageHelper.offsetPage(pageNo, pageSize);
        }
        List<SysDict> sysDictList = sysDictService.getDictListByTypeAndName(typevalue, keyword);
        if (CollectionUtils.isNotEmpty(sysDictList)) {
            List<String> userIds = sysDictList.stream().map(SysDict::getUpdateUserId).collect(Collectors.toList());
            Map<String, Object> queryMap = Maps.newHashMap();
            queryMap.put("ids", userIds);
            List<SysUser> allUserList = sysUserService.findByUserIds(queryMap);
            if (CollectionUtils.isNotEmpty(allUserList)) {
                Map<String, String> userMap = allUserList.stream().collect(Collectors.toMap(SysUser::getId, SysUser::getUsername));
                for (SysDict dict : sysDictList) {
                    dict.setCreateName(userMap.get(dict.getUpdateUserId()));
                }
            }
        }
        PageInfo<SysDict> pageInfo = new PageInfo<>(sysDictList);
        return Result.ok(PageUtils.getPageUtils(pageInfo));
    }

    @ApiOperation(value = "批量导出接口", notes = "权限码(sys:dict:export)")
//    @RequiresPermissions("sys:dict:exportDict")
    @GetMapping("/exportDict")
    public void exportDict(HttpServletResponse response) {

//        String filePath =  "C:\\Users\\zhouqingchun\\Desktop\\export.xlsx";
        String fileName = "dict.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + File.separator + fileName;
        try {
            sysDictService.exportDict(filePath);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
    }

    @ApiOperation(value = "查询所有全部作物字段", httpMethod = "GET")
    @ResponseBody
    @GetMapping(value = "/getSysDictAllList")
    public Result getSysDictAllList() {
        List<SysDictAllVo> list = sysDictService.getSysDictAllList();
        return Result.ok(list);
    }
}
