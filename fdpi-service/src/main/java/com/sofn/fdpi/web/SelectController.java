package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.model.ChangeType;
import com.sofn.fdpi.service.PapersService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.TbCompVo;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "下拉选择管理接口类", tags = "下拉选择管理接口类")
@RequestMapping("/select")
@RestController
public class SelectController {
    @Resource
    private SysRegionApi sysRegionApi;
    @Resource
    private TbCompService tbCompService;
    @Resource
    private PapersService papersService;

    @ApiOperation(value = "标识申请类型下拉列表")
    @GetMapping("/getSignboardApplyType")
    public Result<List<SelectVo>> getSignboardApplyType() {
        return Result.ok(SignboardApplyTypeEnum.getSelect());
    }

    @ApiOperation(value = "标识配发/补发/换发申请状态下拉列表")
    @GetMapping("/getSignboardApplyStatus")
    public Result<List<SelectVo>> getSignboardApplyStatus() {
        return Result.ok(SignboardApplyProcessEnum.getSelect());
    }

    @ApiOperation(value = "标识状态下拉列表")
    @GetMapping("/getSignboardStatus")
    public Result<List<SelectVo>> getSignboardStatus() {
        return Result.ok(SignboardStatusEnum.getSelect());
    }

    @ApiOperation(value = "物种来源下拉列表")
    @GetMapping("/getSpeciesSource")
    public Result<List<SelectVo>> getSpeciesSource() {
        return Result.ok(SpeciesSourceEnum.getSelect());
    }

    @ApiOperation(value = "物种利用类型下拉列表")
    @GetMapping("/getSpeciesUtilizeType")
    public Result<List<SelectVo>> getSpeciesUtilizeType() {
        return Result.ok(SpeciesUtilizeTypeEnum.getSelect());
    }

    @ApiOperation(value = "性别下拉列表")
    @GetMapping("/getSexType")
    public Result<List<SelectVo>> getSexType() {
        return Result.ok(SexTypeEnum.getSelect());
    }

    @ApiOperation(value = "是否下拉列表")
    @GetMapping("/getIs")
    public Result<List<SelectVo>> getIs() {
        return Result.ok(IsEnum.getSelect());
    }

    @ApiOperation(value = "有无下拉列表")
    @GetMapping("/getHas")
    public Result<List<SelectVo>> getHas() {
        return Result.ok(HasEnum.getSelect());
    }

    @ApiOperation(value = "标识变更记录中的状态下拉列表")
    @GetMapping("/getSignboardChangeStatus")
    public Result<List<SelectVo>> getSignboardChangeStatus() {
        return Result.ok(SignboardChangeStatusEnum.getSelect());
    }

    @ApiOperation(value = "证书类型下拉列表,人工/驯养/经营")
    @GetMapping("/getPapersTypeRegister")
    public Result<List<SelectVo>> getPapersTypeRegister() {
        return Result.ok(PapersType2Enum.getSelect());
    }

    @ApiOperation(value = "证书类型下拉列表,人工/驯养")
    @GetMapping("/getPapersType")
    public Result<List<SelectVo>> getPapersType() {
        return Result.ok(PapersType2Enum.getSelect());
    }

    @ApiOperation(value = "企业用户账号状态，1启用0停用")
    @GetMapping("/getCompanyStatus")
    public Result<List<SelectVo>> getCompanyStatus() {
        return Result.ok(CompanyStatusEnum.getSelect());
    }


    @ApiOperation(value = "获取变更原因下拉列表")
    @GetMapping("/getChangeType")
    public Result<List<ChangeType>> getChangeType() {
        List<ChangeType> list = new ArrayList<>();
        List<SysDict> fdpiChangeType = sysRegionApi.getDictListByType("fdpi_changeType").getData();
        if (!CollectionUtil.isEmpty(fdpiChangeType)) {
            fdpiChangeType.forEach(o -> {
                ChangeType ct = new ChangeType();
                ct.setId(o.getDictcode());
                ct.setType(o.getDictname());
                list.add(ct);
            });
        }
        return Result.ok(list);
    }

    @ApiOperation(value = "证书类型下拉列表,人工/经营许可证下拉 type:1 获取人工繁育证下拉 ，type:2获取经营许可证下拉， type：3 获取全部类型 默认不传为获取全部")
    @GetMapping("/papersType")
    public Result<List<SelectVo>> PapersType(@RequestParam(value = "type", required = false) String type) {
        List<SelectVo> list = new ArrayList<>();
        List<SysDict> fdpiChangeType = sysRegionApi.getDictListByType("fdpi_PaperType").getData();
        if (!CollectionUtil.isEmpty(fdpiChangeType)) {
//            获取证书全部类型
            if ("3".equals(type) || StringUtils.isEmpty(type)) {
                fdpiChangeType.forEach(o -> {
                    SelectVo ct = new SelectVo();
                    ct.setKey(o.getDictcode());
                    ct.setVal(o.getDictname());
                    list.add(ct);
                });
            } else if ("2".equals(type)) {
                fdpiChangeType.forEach(o -> {
                    if ("3".equals(o.getDictcode())) {
                        SelectVo ct = new SelectVo();
                        ct.setKey(o.getDictcode());
                        ct.setVal(o.getDictname());
                        list.add(ct);
                    }
                });
            } else if ("1".equals(type)) {
                fdpiChangeType.forEach(o -> {
                    if ("1".equals(o.getDictcode())) {
                        SelectVo ct = new SelectVo();
                        ct.setKey(o.getDictcode());
                        ct.setVal(o.getDictname());
                        list.add(ct);
                    }
                });
            } else {

            }
        }
        return Result.ok(list);
    }

    @ApiOperation(value = "获取物种转移的状态值")
    @GetMapping("/getTranferStatus")
    public Result<List<SelectVo>> getTranferStatus() {
        return Result.ok(TranferStatusEnum.getSelect());
    }

    @ApiOperation(value = "获取物种类型下拉")
    @GetMapping("/getFdpiSpeType")
    public Result<List<SelectVo>> getFdpiSpeType() {
        List<SelectVo> list = new ArrayList<>();
        List<SysDict> sysDicts = sysRegionApi.getDictListByType("fdpi_spe_type").getData();
        if (!CollectionUtil.isEmpty(sysDicts)) {
            for (SysDict sd : sysDicts) {
                list.add(new SelectVo(sd.getDictcode(), sd.getDictname()));
            }
            list.stream().sorted(Comparator.comparing(SelectVo::getKey)).collect(Collectors.toList());
        }
        TbCompVo tcv = tbCompService.getCombById(UserUtil.getLoginUserOrganizationId());
        if (Objects.nonNull(tcv)) {
            String compType = tcv.getCompType();
            for (SelectVo selectVo : list) {
                if ("1".equals(compType) && "01".equals(selectVo.getKey())) {
                    list.remove(selectVo);
                    break;
                } else if ("2".equals(compType) && "02".equals(selectVo.getKey())) {
                    list.remove(selectVo);
                    break;
                }
            }
        }
        return Result.ok(list);
    }


    @ApiOperation(value = "国内鱼子酱申请流程")
    @GetMapping("/getCitesDomesticProcess")
    public Result<List<SelectVo>> getCitesDomesticProcess() {
//        List<SysDict> sysDicts = sysRegionApi.getDictListByType("fdpi_cites_domestic_process").getData();
//        List<SelectVo> list = new ArrayList<>();
//        if (!CollectionUtil.isEmpty(sysDicts)) {
//            for (SysDict sd : sysDicts) {
//                list.add(new SelectVo(sd.getDictcode(), sd.getDictname()));
//            }
//            list.stream().sorted(Comparator.comparing(SelectVo::getKey)).collect(Collectors.toList());
//        }
//        return Result.ok(list);
        return Result.ok(SturgeonStatusDomesticEnum.getSelect());
    }

    @ApiOperation(value = "国内鱼子酱补打申请流程")
    @GetMapping("/getCitesReprintProcess")
    public Result<List<SelectVo>> getCitesReprintProcess() {
        return Result.ok(SturgeonPaperEnum.getSelect());
    }

    @ApiOperation(value = "国内鱼子酱标签纸申请流程")
    @GetMapping("/getCitesPaperProcess")
    public Result<List<SelectVo>> getCitesPaperProcess() {
        return Result.ok(SturgeonPaperEnum.getSelect());
    }

    @ApiOperation(value = "物种种类")
    @GetMapping("/getSpeCategory")
    public Result<List<SelectVo>> getSpeCategory() {
        return Result.ok(SpeCategoryEnum.getSelect());
    }

    @ApiOperation(value = "根据物种类别获取单位")
    @GetMapping(value = "/getUnitsByType")
    public Result<List<SelectVo>> getUnitsByType(@ApiParam(value = "物种类别", required = true) @RequestParam("speType") String speType) {
        return Result.ok(papersService.getUnitsByType(speType));
    }
}
