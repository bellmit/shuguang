package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.mapper.CollectFlowMapper;
import com.sofn.ducss.model.StandardSet;
import com.sofn.ducss.model.StandardValue;
import com.sofn.ducss.service.CollectFlowService;
import com.sofn.ducss.service.StandardSetService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysRegionForm;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.vo.StandarSetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.StringList;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author Zhang Yi
 * @Date 2020/10/27 17:30
 * @Version 1.0
 * 部级标准值设定
 */
@RequestMapping("/StandardSet")
@Api(tags = "部级标准草谷比，可收集系数设定接口", value = "部级标准设定")
@RestController
public class StandardSetController extends BaseController {

    @Autowired
    private StandardSetService standardSetService;

    @Autowired
    private CollectFlowService collectFlowService;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private CollectFlowMapper collectFlowMapper;


    //新增 (1.不要重复增加) 传3个字段
    @SofnLog("年度标准新增")
    @ApiOperation(value = "添加年度标准")
    @PostMapping("/addYearStandarSet")
    public Result<Object> addYearStandarSet(@Validated @RequestBody StandarSetVo standarSetVo, BindingResult result) {

        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        return standardSetService.add(standarSetVo);

    }

    //编辑
    @SofnLog("年度标准编辑")
    @ApiOperation(value = "编辑年度标准")
    @PostMapping("/updateYearStandarSet")
    public Result<Object> updateYearStandarSet(@RequestBody List<StandardValue> standardValues) {
        //1*14
//	if (standardValues.size() != 14) {
//		throw new SofnException("作物类型缺省");
//	}
        StandardValue standardValue = standardValues.get(0);
        String areaId = standardValue.getAreaId();
        String year = standardValue.getYear();
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("year", year);
        prams.put("area_id", areaId);
        List<StandardValue> standardValuesCountry = standardSetService.showStandardValues(prams);


        if (areaId.equals("100000")) {
            //说明全国有标准
            if (standardValuesCountry != null && standardValuesCountry.size() > 0) {
                HashMap<String, Object> parms = getMapByYearArea(year, null);

                Integer count = collectFlowService.selectPass(parms);
                if (count > 0) {
                    //任何一个县下有无 上报 ，已读，通过的数据
                    return new Result(320, "全国指标已被县级使用且上报，不能编辑");
                } else {
                    //表示可以编辑全国标准
                    standardSetService.updateStandSetValue(standardValues);
                    LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_EDIT.getCode(), "编辑-" + year + "年参数");
                }
            } else {
                //说明全国没有标准
                //查询省份自制的指标，
                List<String> provinceids = standardSetService.selectAreaIdsByYear(year);
                if (provinceids == null || provinceids.size() == 0) {
                    //说明省份也没有自制标准
                    HashMap<String, Object> parms = getMapByYearArea(year, null);
                    Integer count = collectFlowService.selectPass(parms);
                    if (count > 0) {
                        //任何一个县下有无 上报 ，已读，通过的数据
                        return new Result(320, "全国指标已被县级使用且上报，不能编辑");
                    } else {
                        //表示可以编辑全国标准
                        standardSetService.updateStandSetValue(standardValues);
                        LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_EDIT.getCode(), "编辑-" + year + "年参数");
                    }
                } else {
                    //查询areaIds下所有县  是否有上报数，  与全国的上报数。相等说明 其余县没用全国标准，全国标准则可以编辑
                    ArrayList<String> areaIds = new ArrayList<>();
                    for (String provinceid : provinceids) {
                        //查询该省下所有县级数据
                        Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
                        if (treeVoResult != null && treeVoResult.getData() != null) {
                            SysRegionTreeVo Root = treeVoResult.getData();
                            for (SysRegionTreeVo provinceTree : Root.getChildren()) {
                                if (provinceTree.getRegionCode().equals(provinceid)) {
                                    for (SysRegionTreeVo cityTree : provinceTree.getChildren()) {
                                        for (SysRegionTreeVo countyTree : cityTree.getChildren()) {
                                            String countyId = countyTree.getRegionCode();
                                            areaIds.add(countyId);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    HashMap<String, Object> mapByYearArea = getMapByYearArea(year, areaIds);

                    //省自制标准上报数。
                    Integer integer = collectFlowMapper.countReportAndAudit(mapByYearArea);
                    //全国上报数
                    Integer integerC = collectFlowService.selectPass(getMapByYearArea(year, null));
                    if (integer.equals(integerC)) {
                        //表示可以编辑全国标准
                        standardSetService.updateStandSetValue(standardValues);
                        LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_EDIT.getCode(), "编辑-" + year + "年参数");

                        return Result.ok("编辑成功");
                    }
                }
            }
        } else {
            //不是编辑全国数据
            //1.查看当年年度省份制定标准没有，2.查当年全国制定标准没有，有标准且前省下有任何县上报，则不能编辑。
            if (standardValuesCountry != null && standardValuesCountry.size() > 0) {
                ArrayList<String> areaIds = new ArrayList<>();
                Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
                if (treeVoResult != null && treeVoResult.getData() != null) {
                    SysRegionTreeVo Root = treeVoResult.getData();
                    for (SysRegionTreeVo provinceTree : Root.getChildren()) {
                        if (provinceTree.getRegionCode().equals(areaId)) {
                            for (SysRegionTreeVo cityTree : provinceTree.getChildren()) {
                                for (SysRegionTreeVo countyTree : cityTree.getChildren()) {
                                    String countyId = countyTree.getRegionCode();
                                    areaIds.add(countyId);
                                }
                            }
                        }
                    }
                }
                HashMap<String, Object> mapByYearArea = getMapByYearArea(year, areaIds);
                //省自制标准上报数。
                Integer integer = collectFlowMapper.countReportAndAudit(mapByYearArea);
                if (integer > 0) {
                    return new Result(320, "该区域标准已被该县使用，不能编辑");
                }
                //查全国
                prams.put("area_id", "100000");
                List<StandardValue> nationwide = standardSetService.showStandardValues(prams);
                if (nationwide != null && nationwide.size() > 0) {
                    //全国上报数
                    HashMap<String, Object> mapByYearArea1 = getMapByYearArea(year, null);
                    Integer integerC = collectFlowService.selectPass(mapByYearArea1);

                    if (integerC > 0) {
                        return new Result(320, "该区域标准已使用全国标准，且已有县级上报不能编辑");
                    }
                }
                standardSetService.updateStandSetValue(standardValues);
                LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_EDIT.getCode(), "编辑-" + year + "年参数");

                return Result.ok("编辑成功");

            } else {
                standardSetService.updateStandSetValue(standardValues);
                LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_EDIT.getCode(), "编辑-" + year + "年参数");

            }

        }
        return Result.ok("编辑成功");


    }


    //年度标准分页查询
    @SofnLog("年度标准分页查询")
    @ApiOperation(value = "分页查询年度标准")
    @GetMapping("/StandardSetPage")
    public Result<PageUtils<StandardSet>> selectPage(@RequestParam(value = "pageNo") @ApiParam(value = "起始行数", required = true) Integer pageNo
            , @RequestParam(value = "pageSize") @ApiParam(value = "每页显示数", required = true) Integer pageSize
            , @RequestParam(value = "year", required = false) @ApiParam(value = "年度") String year
    ) {

        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(year)) {
            years = Arrays.asList(year.split(","));
        }
        return standardSetService.selectPage(pageNo, pageSize, years);

    }


    //删除
    @SofnLog("删除年度标准")
    @ApiOperation(value = "删除年度标准")
    @GetMapping("/deleteStandardSet")
    public Result<String> deleteStandardSet(
            @RequestParam(value = "year") @ApiParam(value = "年度") String year
    ) {

        HashMap<String, Object> parms = getMapByYearArea(year, null);

        int count = collectFlowService.selectPass(parms);
        //只要当前年度  数据 已上报 或 已读 或 已通过 则不能删除标准
        if (count > 0) {
            return Result.ok("当年标准已有县级上报使用，请退回县级上报数据后，再进行删除");
        } else {

            //根据表中年度删除 所有 标准值(删除不管有无上报)
            standardSetService.deleteStandardByYear(year);
            standardSetService.deleteByYear(year);
            LogUtil.addLog(LogEnum.LOG_TYPE_PARAM_DELETE.getCode(), "删除-" + year + "年参数");

            return Result.ok("删除成功");
        }

    }

//单独区划详情展示

    @SofnLog("单独区划详情标准值展示")
    @ApiOperation(value = "单独区划详情标准值展示")
    @GetMapping("/showStandardValues")
    public Result<List<StandardValue>> showStandardValues(@ApiParam("年份") @RequestParam(value = "year") String year,
                                                          @ApiParam("省以上区划代码") @RequestParam(value = "areaId") String areaId
    ) {


        return standardSetService.getGcByProvince(year, areaId, false);


//	 //查询全国区域值直接返回
//	 if (areaId.equals("100000")) {
//		 HashMap<String, Object> map = new HashMap<>();
//		 map.put("year",year);
//		 map.put("area_id",areaId);
//		 //当前区域值
//		 List<StandardValue>  standardValues=   standardSetService.showStandardValues(map) ;
//		 return Result.ok(standardValues);
//	 }else {
//	 	  return     getStandardValues(year,areaId);
//	 }
//	 }else {
//		 //查询全国区域值
//		 map.put("year",year);
//		 map.put("area_id","100000");
//		 List<StandardValue>  standardValuesCountry=   standardSetService.showStandardValues(map) ;
//		 //如果 标准为空直接返回null
//		 if (standardValuesCountry.size()==0||standardValues.size()==0){
//			 return 	Result.ok(standardValuesCountry);
//		 }
//
//		 for (int i = 0; i < standardValuesCountry.size(); i++) {
//			 for (int i1 = 0; i1 < standardValues.size(); i1++) {
//				 StandardValue standardCountry = standardValuesCountry.get(i);
//				 StandardValue standardArea = standardValues.get(i1);
//				 if (standardCountry.getStrawType().equals(standardArea.getStrawType())){
//					 if (standardArea.getCollectCoefficient()==null||standardArea.getCollectCoefficient().compareTo(new BigDecimal(0))==0){
//						 //用全国标准值
//						 standardArea.setCollectCoefficient(standardCountry.getCollectCoefficient());
//					 }
//					 if (standardArea.getGrassValley()==null||standardArea.getGrassValley().compareTo(new BigDecimal(0))==0){
//						 //用全国标准值
//						 standardArea.setGrassValley(standardCountry.getGrassValley());
//					 }
//				 }
//				 break;
//
//			 }
//		 }
//	 }
//
//
//
//
//	return Result.ok(standardValues);

    }


    /**
     * 封装 年份 和 状态条件  和  县区域条件
     *
     * @param year
     * @return
     */
    private HashMap<String, Object> getMapByYearArea(String year, List<String> areaId) {

        HashMap<String, Object> parms = new HashMap<>();
        ArrayList<Byte> list = new ArrayList<>();
        list.add(Constants.ExamineState.REPORTED);
        list.add(Constants.ExamineState.READ);
        list.add(Constants.ExamineState.PASSED);

        parms.put("year", year);
        parms.put("statues", list);
        if (areaId != null) {
            parms.put("areaIds", areaId);
        }
        return parms;
    }


//县级标准值回填

    //单独区划详情展示

    @SofnLog("回填区划详情标准值展示")
    @ApiOperation(value = "回填区划详情标准值展示")
    @GetMapping("/showStandardValuesByCountyid")
    public Result<List<StandardValue>> showStandardValuesByCountyid(@ApiParam(name = "year", value = "年份") @RequestParam(value = "year") String year,
                                                                    @ApiParam(name = "areaId", value = "县级区划代码") @RequestParam(value = "areaId") String areaId
    ) {

        return standardSetService.getGcByAreaId(year, areaId);


//		//根据县级id查询省id
//		Result<List<SysRegionTreeVo>> parentNodeByRegionCode = sysApi.getParentNodeByRegionCode(areaId);
//		List<SysRegionTreeVo> data = parentNodeByRegionCode.getData();
//			if (data==null||data.size()==0){
//				throw new SofnException("区域id无法查询到草谷比和可收集系数比标准值");
//			}
//		SysRegionTreeVo sysRegionTreeVo = data.get(0);
//		String provinceId = sysRegionTreeVo.getRegionCode();
//		if (StringUtils.isEmpty(provinceId)){
//			throw new SofnException("区域id无法查询到草谷比和可收集系数比标准值");
//
//		}
//		return getStandardValues(year, provinceId);

    }

    //可选年份copy数据
    @SofnLog("可选年份copy标准值数据")
    @ApiOperation(value = "可选年份copy标准值数据")
    @GetMapping("/copyYear")
    public Result<List<String>> copyYear() {
        return standardSetService.selectCopyYear();
    }


}
