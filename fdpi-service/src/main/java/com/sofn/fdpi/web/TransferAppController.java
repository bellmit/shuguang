package com.sofn.fdpi.web;

import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.model.Transfer;
import com.sofn.fdpi.service.ChangeRecordService;
import com.sofn.fdpi.service.TransferService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:52
 */
@Slf4j
@Api(value = "APP_物种转移相关接口",tags = "APP_物种转移相关接口")
@RestController
@RequestMapping("/app/transfer")
public class TransferAppController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private ChangeRecordService changeRecordService;

    @ApiOperation("获取根据企业id获取企业信息")
    @PostMapping("getCompany")
    //@RequiresPermissions("transfer:getCompany")
    public Result<ChangeRecordCompanyVO> getCompany(@RequestParam(required = true) @ApiParam(value="传入模糊查询的企业名称",required = true)  String companyId){
        Map map=new HashMap();
        map.put("companyId", companyId);
        ChangeRecordCompanyVO changeRecordCompanyVO=transferService.getCompanyByIdOrName(map);
        return Result.ok(changeRecordCompanyVO);
    }



    @ApiOperation("模糊搜索企业")
    @PostMapping("listCompany")
    //@RequiresPermissions("transfer:listCompany")
    public Result<List<ChangeRecordCompanyVO>> listCompany(@RequestParam(required = true) @ApiParam(value="传入模糊查询的企业名称",required = true)  String companyName){
        Map map=new HashMap();
        map.put("companyName", "%"+companyName+"%");
        List<ChangeRecordCompanyVO> list=transferService.listCompanyByIdOrName(map);
        return Result.ok(list);
    }


    @ApiOperation("获取某个企业的物种下拉列表")
    @PostMapping("listSpeciesSelect")
    public Result<List<SpeciesSelect>> listSpeciesSelect(@RequestParam(required = true) @ApiParam(value="企业ID",required = true)  String companyId){
        Map map=new HashMap<>();
        if (companyId==null||companyId.trim().equals("")){
            map.put("companyId", UserUtil.getLoginUserOrganizationId());
        }else{
            map.put("companyId",companyId);
        }
        map.put("type","2");
        List<SpeciesSelect> list=changeRecordService.listSpeciesSelect(map);
        return Result.ok(list);
    }


    @ApiOperation("获取某企业的标识下拉列表")
    @PostMapping("listSign")
    //@RequiresPermissions("transfer:saveChangeRec")
    public Result<List<CompanySignVO>> listSign(@RequestParam(required = true) @ApiParam(value="企业ID",required = true)  String companyId){
        if (companyId==null||companyId.trim().equals("")){
            return Result.error("企业ID必须传！");
        }
        List<CompanySignVO> list=transferService.listSignByCompanyId(companyId);
        return Result.ok(list);
    }


    @ApiOperation("保存物种转移,第一次保存调用，返回数据ID")
    @PostMapping("saveTransfer")
    @RequiresPermissions(value = {"fdpi:speciesAddApply:create","fdpi:speciesAddApplyWithNoPaper:create"},logical =Logical.OR)
    public Result<TransferVO> saveTransfer(@ApiParam(value="物种转移对象",required = true) @RequestBody TransferVO transferVO){
        if (transferVO.getIsHavePaper()==null||transferVO.getIsHavePaper().equals("")){
            return Result.error("有无证书标识参数未传递!");
        }
        String id=IdUtil.getUUId();
        transferVO.setId(id);
        transferVO.setCreateTime(new Date());
        transferVO.setCreateUserId(UserUtil.getLoginUserId());
        transferVO.setDelFlag("N");
        transferVO.setOpnion("");
        transferVO.setTransferStatus("0");

        ChangeRecordProcess changeRecordProcess=new ChangeRecordProcess();
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setStatus("0");
        changeRecordProcess.setChangeRecordId(id);
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setContent("保存");
        changeRecordProcess.setAdvice("");
        changeRecordProcess.setConTime(new Date());
        transferService.saveTransfer(transferVO, changeRecordProcess);
        return Result.ok(id);
    }


    @ApiOperation("删除物种转移")
    @PostMapping("deleteTransfer")
    @RequiresPermissions(value = {"fdpi:speciesAddApply:delete","fdpi:speciesAddApplyWithNoPaper:delete"},logical = Logical.OR)
    public Result<Transfer> deleteTransfer(@RequestParam(required = true) @ApiParam(value="删除数据的ID",required = true)  String transferId){
        if (transferId==null||transferId.equals("")){
            return Result.error("物种转移记录ID为必传参数!");
        }
        TransferVO transferVO=new TransferVO();
        transferVO.setId(transferId);
        transferVO.setDelFlag("Y");

        ChangeRecordProcess changeRecordProcess=new ChangeRecordProcess();
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setStatus("12");
        changeRecordProcess.setChangeRecordId(transferId);
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setContent("删除");
        changeRecordProcess.setAdvice("");
        changeRecordProcess.setConTime(new Date());

        transferService.deleteTransfer(transferVO, changeRecordProcess);
        return Result.ok("删除成功!");
    }

    @ApiOperation("查询物种转移列表")
    @PostMapping("listTransfer")
    @RequiresPermissions(value = {"fdpi:speciesAddApply:query","fdpi:speciesAddApplyWithNoPaper:query","fdpi:speciesTransferApply:index","fdpi:speciesReducceApply:query","fdpi:speciesAddApprove:query"},logical = Logical.OR)
    public Result<List<TransferVO>> listTransfer(
            @RequestParam(required = true) @ApiParam(value ="“1”：物种增加申请列表；“2”：物种减少确认列表；“3”：审核列表",required = true) String queryType,
            @RequestParam(required = false) @ApiParam("企业名称")String companyName,
            @RequestParam(required = false) @ApiParam("物种ID")String speciesId,
            @RequestParam(required = false) @ApiParam("审核状态，审核状态，0：保存；1：上报；2：增加企业直属退回；3：增加企业直属通过；4：减少企业退回；5：减少企业通过；6：减少企业直属退回；7：减少企业直属通过；8：结束")String status,
            @RequestParam(required = false) @ApiParam("申请日期起")String createTimeStart,
            @RequestParam(required = false) @ApiParam("申请日期止")String createTimeEnd,
            @RequestParam(required = false) @ApiParam("是否无证书")String isHavePaper,
            @RequestParam(value = "pageNo")Integer pageNo,
            @RequestParam(value = "pageSize")Integer pageSize){

        String[] keys = {"createTimeStart", "createTimeEnd", "queryType", "companyName", "speciesId", "isHavePaper"};
        String[] vals = {createTimeStart, createTimeEnd, queryType, companyName, speciesId, isHavePaper};
        if (StringUtils.hasText(createTimeStart))
            vals[0] = createTimeStart + " 00:00:00";
        if (StringUtils.hasText(createTimeEnd))
            vals[1] = createTimeEnd + " 23:59:59";
        PageUtils<TransferVO> list=transferService.listTransferVO2(MapUtil.getParams(keys,vals),pageNo,pageSize);
        return Result.ok(list);
    }


    @ApiOperation("状态修改接口,适用于再次保存或者未保存直接上报，编辑，上报，审核，退回等.根据场景，传入对应的值，比如保存，除了ID外其他内容都要传，比如上报，除了ID外，只需要传入其中的transferStatus为1。审核状态，0：保存；1：上报；2：增加企业直属退回；3：增加企业直属通过；4：减少企业退回；5：减少企业通过；6：减少企业直属退回；7：减少企业直属通过；8：结束")
    @PostMapping("updateTransfer")
    @RequiresPermissions(value={"fdpi:speciesAddApply:update","fdpi:speciesAddApplyWithNoPaper:update","fdpi:speciesAddApply:report","fdpi:speciesAddApplyWithNoPaper:report","fdpi:speciesReducceApply:update","fdpi:speciesAddApprove:approve","fdpi:speciesAddApprove:back"},logical = Logical.OR)
    public Result<Transfer> updateTransfer(@ApiParam(value="物种转移对象",required = true) @RequestBody TransferVO transferVO){
        transferVO.setUpdateTime(new Date());
        transferVO.setUpdateUserId(UserUtil.getLoginUserId());
        ChangeRecordProcess changeRecordProcess=new ChangeRecordProcess();
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setConTime(new Date());
//        if (transferVO.getTransferStatus().equals("5")){
//            Map map=new HashMap();
//            map.put("transferId",transferVO.getId());
//            TransferVO transferVO_temp=transferService.getTransferVO(map);
//            if (transferVO_temp.getReduceCompanyVO().getDireclyId().equals(UserUtil.getLoginUserOrganizationId())&&transferVO_temp.getReduceCompanyVO().getDirectOrgLevel().equals(Constants.REGION_TYPE_PROVINCE)){
//                transferVO.setTransferStatus("7");
//            }
//        }
//        if (transferVO.getTransferStatus().equals("9")){
//            Map map=new HashMap();
//            map.put("transferId",transferVO.getId());
//            TransferVO transferVO_temp=transferService.getTransferVO(map);
//            if (transferVO_temp.getAddCompanyVO().getDireclyId().equals(UserUtil.getLoginUserOrganizationId())&&transferVO_temp.getAddCompanyVO().getDirectOrgLevel().equals(Constants.REGION_TYPE_PROVINCE)){
//                transferVO.setTransferStatus("11");
//            }
//        }


        transferService.updateTransfer(transferVO, changeRecordProcess);

        return Result.ok("修改成功!");
    }

    @ApiOperation("查询单条物种转移数据")
    @PostMapping("getTransfer")
    @RequiresPermissions(value={"fdpi:speciesAddApply:view","fdpi:speciesAddApplyWithNoPaper:view","fdpi:speciesReducceApply:view","fdpi:speciesAddApprove:view"},logical = Logical.OR)
    public Result<TransferVO> getTransfer(@RequestParam(required = true) @ApiParam(value="物种转移数据ID",required = true)String transferId){

        Map map=new HashMap();
        map.put("transferId",transferId);
        TransferVO transferVO=transferService.getTransferVO(map);
        return Result.ok(transferVO);
    }


    @ApiOperation("查询物种转移进度")
    @PostMapping("listTransferProcessVO")
    @RequiresPermissions("fdpi:speciesTransferSpeed:query")
    public Result<PageUtils<TransferProcessVO>> listTransferProcessVO(@RequestParam(required = false) @ApiParam("0：保存；1：上报；2：增加企业直属退回；3：增加企业直属通过；4：减少企业退回；5：减少企业通过；6：减少企业直属退回；7：减少企业直属通过；8：结束")String status,
                                                                      @RequestParam(required = false) @ApiParam("申请日期起")String createTimeStart,
                                                                      @RequestParam(required = false) @ApiParam("申请日期止")String createTimeEnd,
                                                                      @RequestParam(value = "pageNo")Integer pageNo,
                                                                      @RequestParam(value = "pageSize")Integer pageSize){

        Boolean a=Constants.WORKFLOW.equals(BoolUtils.N);
        Map map=new HashMap();
        if (a){
            map.put("companyId",UserUtil.getLoginUserOrganizationId());
            if (createTimeStart!=null&&!createTimeStart.trim().equals("")){
                map.put("createTimeStart",createTimeStart);
            }
            if (createTimeEnd!=null&&!createTimeEnd.trim().equals("")){
                map.put("createTimeEnd",createTimeEnd);
            }
            if (status!=null&&!status.trim().equals("")){
                map.put("transferStatus",status);
            }
        }else {
            map.put("companyId",UserUtil.getLoginUserOrganizationId());
            if (createTimeStart!=null&&!createTimeStart.trim().equals("")){
                map.put("createTimeStart",createTimeStart);
            }
            if (createTimeEnd!=null&&!createTimeEnd.trim().equals("")){
                map.put("createTimeEnd",createTimeEnd);
            }
            if (status!=null&&!status.trim().equals("")){
                map.put("status",status);
            }
        }

        PageUtils<TransferProcessVO> list=Constants.WORKFLOW.equals(BoolUtils.N) ?
                transferService.listTransferProcessVO(map,pageNo,pageSize):transferService.listTransferProcessVOByassembly(map,pageNo,pageSize);

        return Result.ok(list);
    }


    @ApiOperation("查询物种转移流水")
    @GetMapping("getTransferProcessVO")
    public Result<PageUtils<TransferProcessVO>> getTransferProcessVO(@RequestParam(required = true) @ApiParam("物种转移ID")String transferId){

        Map map=new HashMap();
        map.put("transferId",transferId);
        List<TransferProcessVO> list=Constants.WORKFLOW.equals(BoolUtils.N) ?transferService.getTransferProcessVO(map)
                :transferService. getTransferProcessVOByassembly(map);
        return Result.ok(list);
    }



    @ApiOperation("查询标识列表信息")
    @PostMapping("listSignboardApplyListVo")
    public Result<List<Signboard>> listSignboardApplyListVo(@RequestParam(required = true) @ApiParam("传递多个标识编码，逗号隔开") String signboardCodes
    ){

        if (signboardCodes==null||signboardCodes.trim().equals("")){
            return Result.error("标识编码是必传参数！");
        }
        Map map=new HashMap();
        String[] signboardCode=signboardCodes.split(",");
        String signboardCodesString="";
        for (String a:
                signboardCode) {
            signboardCodesString+="'"+a+"',";
        }
        signboardCodesString=signboardCodesString.substring(0,signboardCodesString.length()-1);
        map.put("signboardCodesString",signboardCodesString);

        List<Signboard> list=transferService.listSignboardApplyListVo(map);
        return Result.ok(list);
    }


    @ApiOperation("查询统计按照区域")
    @PostMapping("listSpeciesChange")
    @RequiresPermissions(value={"fdpi:regionStatistic:index","fdpi:regionStatistic:query"},logical = Logical.OR)
    public Result<PageUtils<List<SpeciesCountVO>>> listSpeciesChange(@RequestParam(required = true) @ApiParam("时间起") String startTime,
                                                                     @RequestParam(required = true) @ApiParam("时间止") String endTime,
                                                                     @RequestParam(required = true) @ApiParam("物种ID") String speciesId,
                                                                     @RequestParam(required = true) @ApiParam("查询类型，1，全部省，2全部市，3，全部县，4，自定义") String queryType,
                                                                     @RequestParam(required = false) @ApiParam("区域代码，逗号隔开") String regiones,
                                                                     @RequestParam(required = false) @ApiParam("排序类型，1：增加量，2：减少量，3：现有量") String order,
                                                                     @RequestParam(required = false) @ApiParam("排序标志，'ASC':升序,'DESC':降序") String orderSign,
                                                                     @RequestParam(value = "pageNo")Integer pageNo,
                                                                     @RequestParam(value = "pageSize")Integer pageSize

    ){


        if (startTime==null||startTime.trim().equals("")){
            return Result.error("时间起必传！");
        }
        if (endTime==null||endTime.trim().equals("")){
            return Result.error("时间止必传！");
        }
        if (speciesId==null||speciesId.trim().equals("")){
            return Result.error("物种ID必传！");
        }
        if (queryType==null||queryType.trim().equals("")){
            return Result.error("查询类型必传！");
        }
        if (queryType.equals("4")){
            if (regiones==null||regiones.trim().equals("")){
                return Result.error("自定义必传区域代码数据！");
            }
        }

        Map map=new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("speciesId",speciesId);
        PageUtils<List<SpeciesCountVO>> pageUtils=null;
        if (order==null||order.trim().equals("")){
            map.put("order","A.ID");
        }else if (order.equals("1")){
            map.put("order","ADDNUMBER");
        }else if (order.equals("2")){
            map.put("order","REDUCENUMBER");
        }else if (order.equals("3")){
            map.put("order","CURNUMBER");
        }
        if (orderSign!=null&&!orderSign.trim().equals("")){
            map.put("orderSign",orderSign);
        }else{
            map.put("orderSign","ASC");
        }


        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
        OrganizationInfo orgInfo = JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);

        AreaVO areaVO=new AreaVO();
        if (queryType.equals("1")){
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode());
            }
            areaVO=transferService.listSpeciesCountVOProvince(map,pageNo,pageSize);
        }
        if (queryType.equals("2")){
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode());
            }
            areaVO=transferService.listSpeciesCountVOCity(map,pageNo,pageSize);
        }
        if (queryType.equals("3")){

            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode().substring(0,2)+"%");
            }
            areaVO=transferService.listSpeciesCountVOCountry(map,pageNo,pageSize);
        }
        if (queryType.equals("4")){
            String[] regionArray=regiones.split(",");
            String regionesString="";
            for (String a:
                    regionArray) {
                regionesString+="'"+a+"',";
            }
            regionesString=regionesString.substring(0,regionesString.length()-1);
            map.put("regiones",regionesString);
            areaVO=transferService.listSpeciesCountVORegiones(map,pageNo,pageSize);
        }

        return Result.ok(areaVO);
    }



    @ApiOperation(value="查询统计按照区域导出",produces="application/octet-stream")
    @PostMapping("exportSpeciesChange")
    @RequiresPermissions("fdpi:regionStatistic:export")
    public void exportSpeciesChange(@RequestParam(required = true) @ApiParam("时间起") String startTime,
                                    @RequestParam(required = true) @ApiParam("时间止") String endTime,
                                    @RequestParam(required = true) @ApiParam("物种ID") String speciesId,
                                    @RequestParam(required = true) @ApiParam("查询类型，1，全部省，2全部市，3，全部县，4，自定义") String queryType,
                                    @RequestParam(required = false) @ApiParam("区域代码，逗号隔开") String regiones,
                                    @RequestParam(required = false) @ApiParam("排序类型，1：增加量，2：减少量，3：现有量") String order,
                                    @RequestParam(required = false) @ApiParam("排序标志，'ASC':升序,'DESC':降序") String orderSign,
                                    HttpServletResponse response

    ){
        Map map=new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("speciesId",speciesId);
        List<SpeciesCountVO> list=null;
        if (order==null||order.trim().equals("")){
            map.put("order","A.ID");
        }else if (order.equals("1")){
            map.put("order","ADDNUMBER");
        }else if (order.equals("2")){
            map.put("order","REDUCENUMBER");
        }else if (order.equals("3")){
            map.put("order","CURNUMBER");
        }
        if (orderSign!=null&&!orderSign.trim().equals("")){
            map.put("orderSign",orderSign);
        }else{
            map.put("orderSign","ASC");
        }


        String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
        OrganizationInfo orgInfo = JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
        if (queryType.equals("1")){
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode());
            }
        }
        if (queryType.equals("2")){
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode());
            }
        }
        if (queryType.equals("3")){
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode().substring(0,2)+"%");
            }
        }


        if (queryType.equals("1")){
            list=transferService.listSpeciesCountVOProvince(map);
        }
        if (queryType.equals("2")){
            list=transferService.listSpeciesCountVOCity(map);
        }
        if (queryType.equals("3")){
            list=transferService.listSpeciesCountVOCountry(map);
        }
        if (queryType.equals("4")){
            String[] regionArray=regiones.split(",");
            String regionesString="";
            for (String a:
                    regionArray) {
                regionesString+="'"+a+"',";
            }
            regionesString=regionesString.substring(0,regionesString.length()-1);
            map.put("regiones",regionesString);
            list=transferService.listSpeciesCountVORegiones(map);
        }
        ExcelExportUtil.createExcel(response,SpeciesCountVO.class,list,"按区域统计物种变换结果数据.xlsx");
    }



    @ApiOperation("查询统计按照企业")
    @PostMapping("listCompanyCount")
    @RequiresPermissions(value={"fdpi:compStatistic:index","fdpi:compStatistic:query"},logical = Logical.OR)
    public Result<CompVO> listCompanyCount(
            @RequestParam(required = true) @ApiParam("物种ID") String speciesId,
            @RequestParam(required = false) @ApiParam("省") String province,
            @RequestParam(required = false) @ApiParam("市") String city,
            @RequestParam(required = false) @ApiParam("县") String country,
            @RequestParam(required = false) @ApiParam("排序类型，1：物种数量，2：标识总数，3：激活标识总数") String order,
            @RequestParam(required = false) @ApiParam("排序标志，'ASC':升序,'DESC':降序") String orderSign,
            @RequestParam(value = "pageNo")Integer pageNo,
            @RequestParam(value = "pageSize")Integer pageSize

    ){
        if (speciesId==null||speciesId.trim().equals("")){
            return Result.error("物种ID必传！");
        }
        Map map=new HashMap();
        map.put("speciesId",speciesId);
        if (province!=null&&!province.trim().equals("")){
            map.put("province",province);
        }
        if (city!=null&&!city.trim().equals("")){
            map.put("city",city);
        }
        if (country!=null&&!country.trim().equals("")){
            map.put("country",country);
        }
        if ((province==null||province.trim().equals(""))&&(city==null||city.trim().equals(""))&&(country==null||country.trim().equals(""))){
            String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
            OrganizationInfo orgInfo = JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode());
            }
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_CITY)){
                map.put("city",orgInfo.getRegionLastCode());
            }
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_COUNTRY)){
                map.put("country",orgInfo.getRegionLastCode());
            }
        }
        if (order==null||order.trim().equals("")){
            map.put("order","REGION_NAME");
        }else if (order.equals("1")){
            map.put("order","B.SPE_NUM");
        }else if (order.equals("2")){
            map.put("order","E.ALLSIGNCOUNT");
        }else if (order.equals("3")){
            map.put("order","F.LINESIGNCOUNT");
        }
        if (orderSign!=null&&!orderSign.trim().equals("")){
            map.put("orderSign",orderSign);
        }else{
            map.put("orderSign","ASC");
        }
        CompVO compVo=transferService.listCompanyCount(map,pageNo,pageSize);
        return Result.ok(compVo);
    }


    @ApiOperation(value="查询统计按照企业导出",produces="application/octet-stream")
    @PostMapping("exportCompanyCount")
    @RequiresPermissions("fdpi:compStatistic:export")
    public void exportCompanyCount(
            @RequestParam(required = true) @ApiParam("物种ID") String speciesId,
            @RequestParam(required = false) @ApiParam("省") String province,
            @RequestParam(required = false) @ApiParam("市") String city,
            @RequestParam(required = false) @ApiParam("县") String country,
            @RequestParam(required = false) @ApiParam("排序类型，1：增加量，2：减少量，3：现有量") String order,
            @RequestParam(required = false) @ApiParam("排序标志，'ASC':升序,'DESC':降序") String orderSign,
            HttpServletResponse response

    ){
        Map map=new HashMap();
        map.put("speciesId",speciesId);
        if (province!=null&&!province.trim().equals("")){
            map.put("province",province);
        }
        if (city!=null&&!city.trim().equals("")){
            map.put("city",city);
        }
        if (country!=null&&!country.trim().equals("")){
            map.put("country",country);
        }
        if ((province==null||province.trim().equals(""))&&(city==null||city.trim().equals(""))&&(country==null||country.trim().equals(""))){
            String orgInfoJosn = UserUtil.getLoginUserOrganizationInfo();
            OrganizationInfo orgInfo = JsonUtils.json2obj(orgInfoJosn, OrganizationInfo.class);
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_PROVINCE)){
                map.put("province",orgInfo.getRegionLastCode());
            }
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_CITY)){
                map.put("city",orgInfo.getRegionLastCode());
            }
            if (orgInfo.getOrganizationLevel().equals(Constants.REGION_TYPE_COUNTRY)){
                map.put("country",orgInfo.getRegionLastCode());
            }
        }
        if (order==null||order.trim().equals("")){
            map.put("order","REGION_NAME");
        }else if (order.equals("1")){
            map.put("order","B.SPE_NUM");
        }else if (order.equals("2")){
            map.put("order","E.ALLSIGNCOUNT");
        }else if (order.equals("3")){
            map.put("order","F.LINESIGNCOUNT");
        }
        if (orderSign!=null&&!orderSign.trim().equals("")){
            map.put("orderSign",orderSign);
        }else{
            map.put("orderSign","ASC");
        }
        List<CompCountVO> list=transferService.listCompanyCount(map);
        ExcelExportUtil.createExcel(response,CompCountVO.class,list,"按企业统计物种标识数量结果数据.xlsx");
    }
}
