package com.sofn.sys.web;

import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.service.SysDictService;
import com.sofn.sys.service.SysDictTypeService;
import com.sofn.sys.vo.SysDictTypeVo;
import com.sofn.sys.vo.SysDictVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

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

    @ApiOperation(value = "查询字典列表接口",notes="权限码(sys:dict:index)")
    @RequestMapping(value = "/getDictInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<PageUtils<HashMap<String,Object>>> getDictInfo(@RequestParam(name="typekeyname",required = false) @ApiParam(value = "字典类型名称或者字典名称关键字") String typekeyname,
                              @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                              @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize){

        PageUtils<HashMap<String,Object>> res = sysDictService.getDictInfo(typekeyname, pageNo, pageSize);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据字典名称查询字典数据接口",notes="权限码(sys:dict:getDictByName)")
//    @RequiresPermissions("sys:dict:getDictByName")
    @ResponseBody
    @RequestMapping(value = "/getDictByName",method = RequestMethod.GET)
    public Result<List<SysDict>> getDictByName(@ApiParam(required = true, value = "字典名称") @RequestParam(value = "dictname") String dictname){
        List<SysDict> res = sysDictService.getDictByName(dictname);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据字典值和类型查询字典数据接口",notes="权限码(sys:dict:getDictByValueAndType)")
//    @RequiresPermissions("sys:dict:getDictByValueAndType")
    @RequestMapping(value = "/getDictByValueAndType",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SysDict>> getDictByValueAndType(@ApiParam(required = true, value = "字典类型ID") @RequestParam(value = "dicttypeid") String dicttypeid,
                                        @ApiParam(value = "字典名称") @RequestParam(value = "dictname",required = false) String dictname,
                                        @ApiParam(value = "字典值") @RequestParam(value = "dictcode",required = false) String dictcode){
        List<SysDict> res = sysDictService.getDictByValueAndType(dicttypeid,dictname,dictcode);
        return Result.ok(res);
    }

    @ApiOperation(value = "根据字典值和类型查询字典名称数据接口",notes="权限码(sys:dict:getDictNameByValueAndType)")
//    @RequiresPermissions("sys:dict:getDictNameByValueAndType")
    @RequestMapping(value = "/getDictNameByValueAndType",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SysDict>> getDictNameByValueAndType(@ApiParam(required = true, value = "字典类型名称") @RequestParam(value = "typename") String typename,
                                        @ApiParam(value = "字典值") @RequestParam(value = "dictcode",required = false) String dictcode){
        List<SysDict> res = sysDictService.getDictNameByValueAndType(typename,dictcode);
        return Result.ok(res);
    }

    @ApiOperation(value = "增加字典数据接口",notes = "权限码(sys:dict:create)")
//    @RequiresPermissions("sys:dict:saveDictInfo")
    @RequestMapping(value = "/saveDictInfo",method = RequestMethod.POST)
    @ResponseBody
    @SofnLog(value = "增加字典数据接口", type = "新增")
    public Result<String> saveDictInfo(@ApiParam(value = "字典ID") @RequestParam(value = "id",required = false) String id,
                                @ApiParam(required = true, value = "字典类型") @RequestParam(value = "dicttypeid") String dicttypeid,
                               @ApiParam(required = true, value = "字典名字") @RequestParam(value = "dictname") String dictname,
                               @ApiParam(required = true, value = "字典代码") @RequestParam(value = "dictcode") String dictcode,
                               @ApiParam(value = "字典状态") @RequestParam(value = "enable",required = false) String enable,
                               @ApiParam(value = "备注") @RequestParam(value = "remark",required = false) String remark){

        SysDictVo sysDictVo = new SysDictVo();
        SysDictTypeVo sysDictTypeVo = new SysDictTypeVo();
        sysDictTypeVo.setId(dicttypeid);
        sysDictVo.setId(id);
        sysDictVo.setSysDictTypeVo(sysDictTypeVo);
        sysDictVo.setDictname(dictname);
        sysDictVo.setDictcode(dictcode);
        sysDictVo.setEnable(enable);
        sysDictVo.setRemark(remark);

        String msg;
        if (StringUtils.isBlank(id)){
            List<SysDict> checkRes = sysDictService.getDictByValueAndType(dicttypeid, dictname,null);
            if (checkRes != null && checkRes.size()>0) {
                throw new SofnException("该字典名称已存在");
            }

            List<SysDict> res = sysDictService.getDictByValueAndType(dicttypeid,dictname,dictcode);
            if(res.size() >= 1) {
                msg = "该字典已保存";
                return Result.error(msg);
            }else {
                sysDictService.saveDictInfo(sysDictVo);
                msg = "保存成功";
            }
        }else {
            sysDictService.updateById(SysDictVo.getSysDict(sysDictVo));
            msg = "修改成功";
        }

        return Result.ok(msg);
    }

    @ApiOperation(value = "修改字典数据接口",notes = "权限码(sys:dict:update)")
    @ResponseBody
//    @RequiresPermissions("sys:dict:updateDictInfo")
    @SofnLog(value = "修改字典数据接口", type = "修改")
    @RequestMapping(value = "/updateDictInfo",method = RequestMethod.PUT)
    public Result<String> updateDictInfo(@ApiParam(required = true, value = "字典ID") @RequestParam(value = "id") String id,
                                 @ApiParam(required = true, value = "字典状态") @RequestParam(value = "enable") String enable){
        sysDictService.updateDictInfo(id,enable);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "刪除字典数据接口",notes = "权限码(sys:dict:delete)")
//    @RequiresPermissions("sys:dict:deleteDictInfo")
    @RequestMapping(value = "/deleteDictInfo",method = RequestMethod.DELETE)
    @ResponseBody
    @SofnLog(value = "刪除字典数据接口", type = "刪除")
    public Result<String> deleteDictInfo(@ApiParam(required = true, value = "字典ID") @RequestParam(value = "ids") String ids){
        sysDictService.deleteDictInfo(ids);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "查询当前类型下的所有字典",notes = "权限码(sys:dict:getDictListByType)")
    //    @RequiresPermissions("sys:dict:getDictListByType")
    @ResponseBody
    @GetMapping(value = "/getDictListByType")
    public Result<List<SysDict>> getDictListByType(@ApiParam(required = true, value = "字典类型值") @RequestParam(value = "typevalue") String typevalue){
        List<SysDict> sysDictList = sysDictService.getDictListByType(typevalue);
        return Result.ok(sysDictList);
    }

    @ApiOperation(value = "批量导出接口",notes = "权限码(sys:dict:export)")
//    @RequiresPermissions("sys:dict:exportDict")
    @GetMapping( "/exportDict")
    public void exportDict(HttpServletResponse response){

//        String filePath =  "C:\\Users\\zhouqingchun\\Desktop\\export.xlsx";
        String fileName = "dict.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + File.separator + fileName;
        try{
            sysDictService.exportDict(filePath);
            FileDownloadUtils.downloadAndDeleteFile(filePath,response);
        }catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
    }
}
