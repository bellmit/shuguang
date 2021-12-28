package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.HasSignboardEnum;
import com.sofn.fdpi.service.ChangeRecordService;
import com.sofn.fdpi.service.CompanySpeciesService;
import com.sofn.fdpi.vo.CompanySpeciesInfoVO;
import com.sofn.fdpi.vo.CompanySpeciesVO;
import com.sofn.fdpi.vo.SpeciesSelect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:52
 */
@Slf4j
@Api(value = "APP_企业信息物种信息",tags = "APP_企业信息物种信息相关接口")
@RestController
@RequestMapping("/app/companySpecies")
public class CompanySpeciesAppController {

    @Autowired
    private CompanySpeciesService companySpeciesService;

    @Autowired
    private ChangeRecordService changeRecordService;

    @ApiOperation("获取企业物种库存信息")
    @PostMapping("listCompanySpeciesVO")
    //@RequiresPermissions("companySpecies:saveChangeRec")
    public Result<List<CompanySpeciesVO>> listCompanySpeciesVO(@RequestParam(required = false) @ApiParam("物种ID")String speciesId,
                                                               @RequestParam(value = "pageNo")Integer pageNo,
                                                               @RequestParam(value = "pageSize")Integer pageSize){
        Map map=new HashMap();
        map.put("speciesId",speciesId);
        map.put("companyId", UserUtil.getLoginUserOrganizationId());
        PageUtils<CompanySpeciesVO> list= companySpeciesService.listCompanySpeciesVO(map,pageNo,pageSize);
        return Result.ok(list);
    }

    @ApiOperation("获取当前用户所在企业的物种下拉列表")
    @PostMapping("listSpeciesSelect")
    //@RequiresPermissions("companySpecies:saveChangeRec")
    public Result<List<SpeciesSelect>> listSpeciesSelect(){
        Map map=new HashMap<>();
        map.put("companyId", UserUtil.getLoginUserOrganizationId());
        map.put("identify", HasSignboardEnum.OMNIDISTANCE.getKey());
        List<SpeciesSelect> list=changeRecordService.listSpeciesSelect(map);
        return Result.ok(list);
    }

    @ApiOperation("获取企业某物种具体库存信息以及流水列表")
    @PostMapping("getCompanySpeciesInfo")
    //@RequiresPermissions("companySpecies:saveChangeRec")
    public Result<CompanySpeciesInfoVO> getCompanySpeciesInfo(@RequestParam(required = true) @ApiParam(value="物种ID",required = true) String speciesId,
                                                                    @RequestParam(value = "pageNo")Integer pageNo,
                                                                    @RequestParam(value = "pageSize")Integer pageSize){
        Map map=new HashMap<>();
        map.put("companyId", UserUtil.getLoginUserOrganizationId());
        map.put("speciesId",speciesId);
        CompanySpeciesInfoVO companySpeciesInfoVO=companySpeciesService.getCompanySpeciesInfo(map,pageNo,pageSize);
        return Result.ok(companySpeciesInfoVO);
    }


}
