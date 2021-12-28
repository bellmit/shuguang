package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.service.EnterpriseService;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.vo.CompInRegisterVo;
import com.sofn.fdpi.vo.EnterpriseForm;
import com.sofn.fdpi.vo.SelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enterprise")
@Api(value = "企业注册相关接口", tags = "企业注册相关接口")
public class EnterpriseController extends BaseController {
    @Autowired
    private EnterpriseService enterpriseService;


    @ApiOperation(value = "v3.0企业注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody @ApiParam(name = "enterpriseForm", value = "企业注册表单json对象", required = true) EnterpriseForm enterpriseForm
            , BindingResult result) {
        if (result.hasErrors()) {
            Result.error(getErrMsg(result));
        }
        String saveResult = enterpriseService.registerCompany(enterpriseForm);
        if (!"1".equals(saveResult)) {
            return Result.error(saveResult);
        }
        return Result.ok("注册成功！");
    }

    @ApiOperation(value = "v3.0注册->通过企业名称获取证书中的企业的信息（通讯地址、法人代表信息）")
    @GetMapping("/getCompByCompName")
    public Result<CompInRegisterVo> getCompByCompName(@RequestParam(value ="compName")@ApiParam(name="compName",value = "企业名称",required = true) String compName){
        if(StringUtils.isEmpty(compName)){
            return Result.error("请上传企业名称！");
        }
        return  Result.ok(enterpriseService.getCompByCompName(compName));
    }


    @ApiOperation(value = "获取企业证书编号下拉列表(注册、证书绑定使用)")
    @GetMapping("/listPapersForSelect")
    public Result<List<SelectVo>> listPapersForSelect(@RequestParam(value = "compName") @ApiParam(name = "compName", value = "企业名称", required = true) String compName
            , @RequestParam(value = "papersType") @ApiParam(name = "papersType", value = "证书类型：'1'：人工繁育；'2'：驯养繁殖；'3'：经营利用", required = true) String papersType
            , @RequestParam(value = "provinceCode",required = false) @ApiParam(name = "provinceCode", value = "企业区域的省级编码") String provinceCode
            , @RequestParam(value = "papersId", required = false) @ApiParam(name = "papersId", value = "证书id：如需要获取包含编辑数据中的证书编号（如证书绑定编辑中），则需要传参，其它则不需要（如注册）") String papersId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("compName", compName);
        map.put("papersType", papersType);
        map.put("papersId", papersId);
        map.put("provinceCode", provinceCode);

        return Result.ok(enterpriseService.listPapersForSelect(map));
    }

    @ApiOperation(value = "根据证书编号获取证书信息(注册、证书绑定使用)")
    @GetMapping("/getPapersById")
    public Result<Papers> getPapersById(@RequestParam(value = "papersId") @ApiParam(name = "papersId", value = "证书表中id，证书下拉列表中的key值", required = true) String papersId) {
        if (StringUtils.isEmpty(papersId)) {
            return Result.error("请选择证书编号列表！");
        }
        return Result.ok(enterpriseService.getPapersById(papersId));
    }
}
