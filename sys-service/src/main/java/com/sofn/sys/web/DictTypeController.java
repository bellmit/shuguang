package com.sofn.sys.web;

import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysDictType;
import com.sofn.sys.service.SysDictTypeService;
import com.sofn.sys.vo.SysDictTypeAndValueVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * zhouqingchun
 * 2019/06/12
 */
@RestController
@Api(tags = "字典类型管理接口")
@RequestMapping("/DictType")
public class DictTypeController extends BaseController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @ApiOperation(value = "查询字典类型数据接口",notes = "权限点(sys:dict:getDictTypeByName)")
    @ResponseBody
    @RequestMapping(value = "/getDictTypeByName",method = RequestMethod.GET)
    public Result<List<SysDictType>> getDictTypeByName(@ApiParam(value = "字典类型名称") @RequestParam(value = "typename",required = false) String typename,
                                    @ApiParam(value = "字典类型值") @RequestParam(value = "typevalue",required = false) String typevalue){
        List<SysDictType> res = sysDictTypeService.getDictTypeByName(typename,typevalue);
        return Result.ok(res);
    }

    @GetMapping("/getDictTypeByPage")
    @ApiOperation(value = "分页查询字典类型接口",notes = "权限点(sys:dict:getDictTypeByPage)")
    public Result<PageUtils<SysDictTypeAndValueVo>> getDictTypeByPage(@ApiParam(value = "字典类型名称或者代码，模糊匹配") @RequestParam(required = false) String keyword,
                                                                      @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                                                      @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize){
        Map<String,Object> params = Maps.newHashMap();
        if(!StringUtils.isBlank(keyword)){
            params.put("keyword",keyword);
        }
        PageUtils<SysDictTypeAndValueVo> dictTypeAndValueByPage = sysDictTypeService.getDictTypeAndValueByPage(params, pageNo, pageSize);
        return Result.ok(dictTypeAndValueByPage);

    }



    @ApiOperation(value = "保存字典类型数据接口",notes = "权限点(sys:dict:dictType:create)")
    @ResponseBody
    @RequestMapping(value = "/saveDictType",method = RequestMethod.POST)
    @SofnLog(value = "保存字典类型数据接口", type = "新增")
    public Result<String> saveDictType(@ApiParam(required = true, value = "字典类型名称") @RequestParam(value = "typename") String typename,
                               @ApiParam(required = true, value = "字典类型值") @RequestParam(value = "typevalue") String typevalue){
        SysDictType sysDictType = new SysDictType();
        sysDictType.setTypename(typename);
        sysDictType.setTypevalue(typevalue);

        String msg;

        List<SysDictType> resName = sysDictTypeService.getDictTypeByName(typename,"");
        List<SysDictType> resValue = sysDictTypeService.getDictTypeByName("",typevalue);
        if(resName.size()>=1){
            msg = "该字典类型名称已存在";
            return Result.error(msg);
        }else if(resValue.size()>=1){
            msg = "该字典类型值已存在";
            return Result.error(msg);
        }else{
            sysDictTypeService.saveDictType(sysDictType);
            msg = "保存成功";
        }

        return Result.ok(msg);
    }

    @ApiOperation(value = "删除字典类型数据接口",notes = "权限点(sys:dict:dictType:delete)")
    @ResponseBody
    @RequestMapping(value = "/delDictType",method = RequestMethod.DELETE)
    @SofnLog(value = "删除字典类型数据接口", type = "删除")
    public Result<String> delDictType(@ApiParam(required = true, value = "字典ID") @RequestParam(value = "id") String id){
        sysDictTypeService.delDictType(id);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "修改字典类型数据接口",notes = "权限点(sys:dict:dictType:update)")
    @ResponseBody
    @RequestMapping(value = "/updateDictType",method = RequestMethod.PUT)
    @SofnLog(value = "修改字典类型数据接口", type = "修改")
    public Result<String> updateDictType(@ApiParam(required = true, value = "字典ID") @RequestParam(value = "id") String id,
                                 @ApiParam(value = "字典类型名称") @RequestParam(value = "typename",required = false) String typename,
                                 @ApiParam(value = "字典类型值") @RequestParam(value = "typevalue",required = false) String typevalue){

        String msg;

        List<SysDictType> resName = sysDictTypeService.getDictTypeByName1(typename,"");
        List<SysDictType> resValue = sysDictTypeService.getDictTypeByName1("",typevalue);

        if(resName.size()>=1){
            List<SysDictType> sysDictTypes = new ArrayList<>();
            for(SysDictType sysDictType : resName){
                String resNmId = sysDictType.getId();
                if(!id.equals(resNmId)){
                    sysDictTypes.add(sysDictType);
                }
            }

            if(sysDictTypes.size()>0){
                msg = "该字典类型名称已存在";
                return Result.error(msg);
            }
        }

        if(resValue.size()>=1){
            List<SysDictType> sysDictTypes = new ArrayList<>();
            for(SysDictType sysDictType : resValue){
                String resVaId = sysDictType.getId();
                if(!id.equals(resVaId)){
                    sysDictTypes.add(sysDictType);
                }
            }
            if(sysDictTypes.size()>0){
                msg = "该字典类型值已存在";
                return Result.error(msg);
            }
        }

        sysDictTypeService.updateDictType(id,typename,typevalue);
        msg = "修改成功";
        return Result.ok(msg);
    }

    @Deprecated
    @ApiOperation(value = "保存给子系统分配字典接口",notes = "权限点(sys:dict:saveSubSystemDict)")
    @ResponseBody
    @PostMapping(value = "/saveSubSystemDict")
    @SofnLog(value = "保存给子系统分配字典接口", type = "修改")
    public Result<String> saveSubSystemDict(@ApiParam(required = true, value = "子系统ID") @RequestParam(value = "subsystemid") String subsystemid,
                                    @ApiParam(required = true, value = "字典IDs") @RequestParam(value = "dictids") String dictids){
        sysDictTypeService.saveSubSystemDict(subsystemid,dictids);
        return Result.ok("保存成功");
    }

    @ApiOperation(value = "根据子系统查询字典接口",notes = "权限点(sys:dict:getDictBySubSystem)")
    @ResponseBody
    @GetMapping(value = "/getDictBySubSystem")
    public Result<List<SysDictType>> getDictBySubSystem(@ApiParam(required = true, value = "子系统ID") @RequestParam(value = "subsystemid") String subsystemid){
        List<SysDictType> res = sysDictTypeService.getDictBySubSystem(subsystemid);
        return Result.ok(res);
    }

}
