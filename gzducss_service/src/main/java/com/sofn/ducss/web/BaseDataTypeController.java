package com.sofn.ducss.web;

import com.google.common.collect.Maps;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.SysBaseDataType;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysBaseDataTypeService;
import com.sofn.ducss.service.SysBaseTypeAndValueService;
import com.sofn.ducss.service.SysSubBaseDataOwnService;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.BaseDataOwnVo;
import com.sofn.ducss.vo.SaveSubSystemAndBaseDataRelationshipVo;
import com.sofn.ducss.vo.SysBaseTypeAndDataVo;
import com.sofn.ducss.vo.SysBaseTypeAndValueVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "基础数据类型接口")
@RequestMapping("/BaseDataType")
public class BaseDataTypeController extends BaseController {
    @Autowired
    private SysBaseDataTypeService sysBaseDataTypeService;
    @Autowired
    private SysSubBaseDataOwnService sysSubBaseDataOwnService;

    @Autowired
    private SysBaseTypeAndValueService sysBaseTypeAndValueService;

    @ApiOperation(value = "查询基础数据类型数据接口",notes = "权限点(sys:basedata:getBaseDataTypeByName)")
//    @RequiresPermissions("sys:basedata:getBaseDataTypeByName")
    @ResponseBody
    @RequestMapping(value = "/getBaseDataTypeByName",method = RequestMethod.GET)
    public Result<List<SysBaseDataType>> getBaseDataTypeByName(@ApiParam(value = "基础数据类型名称") @RequestParam(value = "typename",required = false) String typename,
                                                               @ApiParam(value = "基础数据类型值") @RequestParam(value = "typevalue",required = false) String typevalue){
        List<SysBaseDataType> res = sysBaseDataTypeService.getBaseDataTypeByName(typename,typevalue);
        return Result.ok(res);
    }

    @ApiOperation(value = "保存基础数据类型数据接口",notes = "权限点(sys:basedata:BaseDataType:create)")
//    @RequiresPermissions("sys:basedata:BaseDataType:create")
    @ResponseBody
    @RequestMapping(value = "/saveBaseDataType",method = RequestMethod.POST)
    @SofnLog(value = "保存基础数据类型数据接口", type = "新增")
    public Result<String> saveBaseDataType(@ApiParam(required = true, value = "基础数据类型名称") @RequestParam(value = "typename") String typename,
                               @ApiParam(required = true, value = "基础数据类型值") @RequestParam(value = "typevalue") String typevalue){
        SysBaseDataType sysBaseDataType = new SysBaseDataType();
        sysBaseDataType.setTypename(typename);
        sysBaseDataType.setTypevalue(typevalue);

        String msg;

        List<SysBaseDataType> resName = sysBaseDataTypeService.getBaseDataTypeByName(typename,"");
        List<SysBaseDataType> resValue = sysBaseDataTypeService.getBaseDataTypeByName("",typevalue);
        if(resName.size()>=1){
            msg = "该基础数据类型名称已存在";
            return Result.error(msg);
        }else if(resValue.size()>=1){
            msg = "该基础数据类型值已存在";
            return Result.error(msg);
        }else{
            sysBaseDataTypeService.saveBaseDataType(sysBaseDataType);
            msg = "保存成功";
        }

        return Result.ok(msg);
    }

    @ApiOperation(value = "删除基础数据类型数据接口",notes = "权限点(sys:basedata:BaseDataType:delete)")
//    @RequiresPermissions("sys:basedata:BaseDataType:delete")
    @ResponseBody
    @RequestMapping(value = "/delBaseDataType",method = RequestMethod.DELETE)
    @SofnLog(value = "删除基础数据类型数据接口", type = "删除")
    public Result<String> delBaseDataType(@ApiParam(required = true, value = "基础数据ID") @RequestParam(value = "id") String id){
        sysBaseDataTypeService.delBaseDataType(id);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "修改基础数据类型数据接口",notes = "权限点(sys:basedata:BaseDataType:update)")
//    @RequiresPermissions("sys:basedata:BaseDataType:update")
    @ResponseBody
    @RequestMapping(value = "/updateBaseDataType",method = RequestMethod.PUT)
    @SofnLog(value = "修改基础数据类型数据接口", type = "修改")
    public Result<String> updateBaseDataType(@ApiParam(required = true, value = "基础数据ID") @RequestParam(value = "id") String id,
                                 @ApiParam(value = "基础数据类型名称") @RequestParam(value = "typename",required = false) String typename,
                                 @ApiParam(value = "基础数据类型值") @RequestParam(value = "typevalue",required = false) String typevalue){

        String msg;

        List<SysBaseDataType> resName = sysBaseDataTypeService.getBaseDataTypeByName1(typename,"");
        List<SysBaseDataType> resValue = sysBaseDataTypeService.getBaseDataTypeByName1("",typevalue);

        if(resName.size()>=1){
            List<SysBaseDataType> sysBaseDataTypes = new ArrayList<>();
            for(SysBaseDataType sysBaseDataType : resName){
                String resNmId = sysBaseDataType.getId();
                if(!id.equals(resNmId)){
                    sysBaseDataTypes.add(sysBaseDataType);
                }
            }

            if(sysBaseDataTypes.size()>0){
                msg = "该基础数据类型名称已存在";
                return Result.error(msg);
            }
        }

        if(resValue.size()>=1){
            List<SysBaseDataType> sysBaseDataTypes = new ArrayList<>();
            for(SysBaseDataType sysBaseDataType : resValue){
                String resVaId = sysBaseDataType.getId();
                if(!id.equals(resVaId)){
                    sysBaseDataTypes.add(sysBaseDataType);
                }
            }
            if(sysBaseDataTypes.size()>0){
                msg = "该基础数据类型值已存在";
                return Result.error(msg);
            }
        }

        sysBaseDataTypeService.updateBaseDataType(id,typename,typevalue);
        msg = "修改成功";
        return Result.ok(msg);
    }

    @ApiOperation(value = "保存给子系统分配基础数据接口",notes = "权限点(sys:basedata:saveSubSystemBaseData)")
    @ResponseBody
    //    @RequiresPermissions("sys:basedata:saveSubSystemBaseData")
    @PostMapping(value = "/saveSubSystemBaseData")
    @SofnLog(value = "保存给子系统分配基础数据接口", type = "修改")
    public Result<String> saveSubSystemBaseData(@ApiParam(required = true, value = "子系统ID") @RequestParam(value = "subsystemid") String subsystemid,
                                    @ApiParam(required = true, value = "基础数据IDs") @RequestParam(value = "basedataids") String basedataids){
        sysBaseDataTypeService.saveSubSystemBaseData(subsystemid,basedataids);
        return Result.ok("保存成功");
    }

    @ApiOperation(value = "根据子系统查询基础数据接口",notes = "权限点(sys:basedata:getBaseDataBySubSystem)")
    @ResponseBody
    //    @RequiresPermissions("sys:basedata:getBaseDataBySubSystem")
    @GetMapping(value = "/getBaseDataBySubSystem")
    public Result<List<SysBaseDataType>> getBaseDataBySubSystem(@ApiParam(required = true, value = "子系统ID") @RequestParam(value = "subsystemid") String subsystemid){
        if (StringUtils.isBlank(subsystemid)){
            return Result.ok(new ArrayList<>());
        }

        List<SysBaseDataType> res = sysBaseDataTypeService.getBaseDataBySubSystem(subsystemid);
        return Result.ok(res);
    }

    @ApiOperation(value = "选择分配给子系统的基础数据接口",notes = "权限点(sys:basedata:selectBaseDataForSubSystem)")
    @ResponseBody
    @PostMapping(value = "/selectBaseDataForSubSystem")
    public Result<String> selectBaseDataForSubSystem(@Validated @RequestBody @ApiParam(name = "登录信息对象", value = "传入json格式", required = true) BaseDataOwnVo vo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(getErrMsg(bindingResult));
        }

        sysSubBaseDataOwnService.selectBaseDataForSubSystem(vo.getAppId(),vo.getDataBaseTypeId(),vo.getDataBaseIds());
        return Result.ok();
    }

    /**
     * 获取所有的字典类型和字典值
     * @return  List<getAllBaseTypeAndValue>
     */
    @GetMapping("/getAllBaseTypeAndValue")
    @ApiOperation(value = "获取所有的字典类型和字典值，最终组成一棵树，第一层为字典类型，第二层为字典值",notes = "权限点(sys:basedata:getAllBaseTypeAndValue)")
    public Result<List<SysBaseTypeAndValueVo>> getAllBaseTypeAndValue(){
        List<SysBaseTypeAndValueVo> allBaseTypeAndValue = sysBaseTypeAndValueService.getAllBaseTypeAndValue();
        return Result.ok(allBaseTypeAndValue);
    }

    /**
     * 获取子系统有的基础数据字典类型和值的ID
     * @param appId   子系统
     * @return   List<String>
     */
    @ApiOperation(value = "获取子系统有的基础数据字典类型和值的ID",notes = "权限点(sys:basedata:getSubSystemHaveBaseTypeAndValueId)")
    @GetMapping("/getSubSystemHaveBaseTypeAndValueId")
    public Result<List<String>> getSubSystemHaveBaseTypeAndValueId(@ApiParam(required = true, value = "子系统APPID") @RequestParam(value = "appId")String appId){
        List<String> subSystemHaveBaseTypeAndValueId = sysBaseTypeAndValueService.getSubSystemHaveBaseTypeAndValueId(appId);
        return Result.ok(subSystemHaveBaseTypeAndValueId);

    }

    @ApiOperation(value = "保存子系统和基础数据字典的关系",notes = "权限点(sys:basedata:saveSubSystemAndBaseDataRelationship)")
    @PostMapping("/saveSubSystemAndBaseDataRelationship")
    @SofnLog(value = "保存子系统和基础数据字典的关系", type = "修改")
    public Result<String> saveSubSystemAndBaseDataRelationship(@Validated @RequestBody SaveSubSystemAndBaseDataRelationshipVo saveSubSystemAndBaseDataRelationshipVo, BindingResult result){
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sysBaseTypeAndValueService.saveSubSystemAndBaseDataRelationship(IdUtil.getIdsByStr(saveSubSystemAndBaseDataRelationshipVo.getIds()),saveSubSystemAndBaseDataRelationshipVo.getAppId());
        return Result.ok((Object) "保存成功！");
    }

    @GetMapping("/getBaseTypeAndDataByPage")
    @ApiOperation(value = "分页获取字典类型和字典类型对应的值",notes = "权限点(sys:basedata:getBaseTypeAndDataByPage)")
    public Result<PageUtils<List<SysBaseTypeAndDataVo>>> getBaseTypeAndDataByPage(@ApiParam(value = "字典类型名称或者代码，模糊匹配") @RequestParam(required = false) String keyword,
                                                                                  @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                                                                  @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize ){
        Map<String,Object> params = Maps.newHashMap();
        if(!StringUtils.isBlank(keyword)){
            params.put("keyword",keyword);
        }

        PageUtils<List<SysBaseTypeAndDataVo>> sysBaseDataByPage = sysBaseDataTypeService.getSysBaseDataByPage(params, pageNo, pageSize);
        return Result.ok(sysBaseDataByPage);
    }


    @GetMapping("/getBaseTypeByPage")
    @ApiOperation(value = "分页获取字典类型，不包含字典值",notes = "权限点(sys:basedata:getBaseTypeByPage)")
    public Result<PageUtils<List<SysBaseDataType>>> getBaseTypeByPage(@ApiParam(value = "字典类型名称或者代码，模糊匹配") @RequestParam(required = false) String keyword,
                                                                      @RequestParam(name="pageNo",required = false) @ApiParam(value = "当前页数") Integer pageNo,
                                                                      @RequestParam(name="pageSize",required = false) @ApiParam(value = "每页显示条数") Integer pageSize ){

        return Result.ok(sysBaseDataTypeService.getBaseTypeByPage(keyword, pageNo, pageSize));
    }


}
