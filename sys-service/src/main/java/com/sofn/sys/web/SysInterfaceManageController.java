package com.sofn.sys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysInterfaceManageService;
import com.sofn.sys.vo.SysInterAllParVo;
import com.sofn.sys.vo.SysInterfaceManageForm;
import com.sofn.sys.vo.SysInterfaceManageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用接口管理接口
 *
 */
@RestController
@RequestMapping("/sysInterface")
@Api(tags = "应用接口管理")
public class SysInterfaceManageController extends BaseController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SysInterfaceManageService sysInterfaceManageService;

    @ApiOperation(value = "按条件分页获取接口参数信息/不传递分页参数则查询全部", notes = "权限点(sys:inter:getSysInterfaceByPage)")
    @GetMapping("/pageList")
    public Result<PageUtils<SysInterfaceManageVo>> getSysInterfaceByPage(@RequestParam(required = false) @ApiParam("关键字(接口名称/接口地址)") String keyword,
                                                                         @RequestParam(required = false) @ApiParam("请求方式") String type,
                                                                         @RequestParam(required = false) @ApiParam("所属系统") String subSystemId,
                                                                         @RequestParam(value = "pageNo", required = false) @ApiParam(name = "pageNo", value = "分页序号") Integer pageNo,
                                                                         @RequestParam(value = "pageSize", required = false) @ApiParam(name = "pageSize", value = "每页数量") Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode()); //未删除
        params.put("keyword", keyword);
        params.put("type", type);
        params.put("subSystemId", subSystemId);

        //就不用分页
        if (pageSize == null || pageNo == null) {
            List<SysInterfaceManageVo> sysInterfaceManageVoList = sysInterfaceManageService.getSysIntefaceContion(params);
            return Result.ok(sysInterfaceManageVoList);
        } else {
            PageUtils<SysInterfaceManageVo> sysInterfaceManagePageUtils = sysInterfaceManageService.getSysIntefaceContion(params, pageNo, pageSize);
            return Result.ok(sysInterfaceManagePageUtils);
        }


    }


    @ApiOperation(value = "新增接口参数管理", notes = "权限点(sys:inter:create)")
    @PostMapping("/createPar")
    @SofnLog("新增接口参数管理")
    public Result<String> create(@Validated @RequestBody @ApiParam(name = "应用接口参数管理对象", value = "传入json格式")
                                         SysInterfaceManageForm sysInterfaceManageForm, BindingResult result) {
        String errMsg = this.getErrMsg(result);
        if (!StringUtils.isEmpty(errMsg)) {
            return Result.error(errMsg);
        }
        //将form转为Model
        SysInterfaceManage sysInterfaceManage = SysInterfaceManageForm.getSysInterfaceManage(sysInterfaceManageForm);
        sysInterfaceManageService.saveSysInterfacePar(sysInterfaceManage);
        return Result.ok();
    }

    @ApiOperation(value = "修改接口参数管理信息", notes = "权限点(sys:inter:updateInterfacePar)")
    @PostMapping("/updateInterfacePar")
    @SofnLog("修改接口参数管理信息")
    public Result<String> update(@Validated @RequestBody @ApiParam(name = "应用接口参数管理对象", value = "传入json格式")
                                         SysInterfaceManageForm sysInterfaceManageForm, BindingResult result) {
        String errMsg = this.getErrMsg(result);
        if (!StringUtils.isEmpty(errMsg)) {
            return Result.error(errMsg);
        }
        //将form转为Model
        SysInterfaceManage sysInterfaceManage = SysInterfaceManageForm.getSysInterfaceManage(sysInterfaceManageForm);
        sysInterfaceManageService.update(sysInterfaceManage);
        return Result.ok();
    }

    //回显
    @ApiOperation(value = "根据ID查询接口管理参数", notes = "权限点(sys:inter:getOne)")
    @PostMapping("/getOne")
    @SofnLog("根据ID查询接口管理参数")
    public Result<SysInterfaceManageVo> getOne(@ApiParam(name = "id", value = "接口管理参数ID", required = true) @RequestParam("id") String id) {
        SysInterfaceManageVo sysInterfaceManageVo = sysInterfaceManageService.getOne(id);
        return Result.ok(sysInterfaceManageVo);
    }

    @ApiOperation(value = "根据ID删除接口管理参数", notes = "权限点(sys:inter:deleteById)")
    @DeleteMapping("/delete")
    @SofnLog("根据ID删除接口管理参数")
    public Result<String> deleteById(@ApiParam(name = "id", value = "接口管理参数ID", required = true) @RequestParam("id") String id) {
        sysInterfaceManageService.deleteById(id);
        return Result.ok();

    }

    //批量删除
    @ApiOperation(value = "根据IDS批量删除", notes = "权限码(sys:inter:batchDelete)")
    @PostMapping("/batchDeleteInter/{ids}")
    public Result<String> batchDeleteInter(@ApiParam(value = "待删除IDS，ID用英文,号分隔") @PathVariable(value = "ids") String ids) {
        if (StringUtils.isBlank(ids)) {
            return Result.error("请选择删除选项");
        }
        List<String> idList = IdUtil.getIdsByStr(ids);
        sysInterfaceManageService.batchDeleteInter(idList);
        return Result.ok("批量删除接口成功");
    }

//----

    //根据一个接口id，展示该接口的详细参数
    @ApiOperation(value = "根据一个接口id，展示该接口的详细参数", notes = "权限点(sys:inter:showReqParams)")
    @GetMapping("/showReqParams")
    @SofnLog("根据接口id查询详细接口请求参数")
    public Result<SysInterAllParVo> showReqParams(@ApiParam(value = "接口ID", required = true) @RequestParam("interId") String interId) {
        try {
            SysInterAllParVo sysInterAllParVo = sysInterfaceManageService.showReqParams(interId);
            if (sysInterAllParVo==null){
                return Result.error("接口已被删除或不存在");
            }

            return Result.ok(sysInterAllParVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("查询参数失败");
        }
    }


    @ApiOperation(value = "根据参数id删除参数字段", notes = "权限点(sys:inter:deleteParamsById)")
    @DeleteMapping("/deleteParamsById")
    @SofnLog("根据参数id删除参数字段")
    public Result<String> deleteParamsById(@ApiParam(name = "id", value = "接口管理参数ID", required = true) @RequestParam("id") String id) {
        sysInterfaceManageService.deleteParamsById(id);
        return Result.ok("删除成功");

    }


    //修改接口参数字段
    @ApiOperation(value = "修改参数字段", notes = "权限点(sys:inter:updateParFields)")
    @PostMapping("/updateParFields")
    @SofnLog("修改参数字段")
    public Result<String> updateParFields(@ApiParam("修改参数字段") @RequestBody SysInterAllParVo sysInterParamsVo

    ) {
        try {
            if (sysInterParamsVo != null) {
                sysInterfaceManageService.updateParFields(sysInterParamsVo);
                return Result.ok("修改参数字段成功");
            }
            return Result.error("参数不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改参数字段失败失败");
        }
    }



    /**
     * test
     * @param sysInterAllParVo
     * @return
     */
    @ApiOperation(value = "新增接口请求参数详情", notes = "权限点(sys:inter:createParDetails)")
    @PostMapping("/createParDetails")
    @SofnLog("新增接口请求参数详情")
    public Result<String> createParDetails(@ApiParam("新增接口请求参数详情") @RequestBody SysInterAllParVo sysInterAllParVo) {

        try {
            sysInterfaceManageService.batchInsertParams(sysInterAllParVo);
            return Result.ok("新增参数成功");

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("新增参数错误");
        }


    }

    //新增接口请求参数详情测试
    @ApiOperation(value = "在线测试", notes = "权限点(sys:inter:onlineTest)")
    @PostMapping("/onlineTest")
    @SofnLog("在线测试")
    public Result<Object> onLineTest(@Validated @ApiParam("在线测试接受参数对象") @RequestBody SysInterOlinePar sysInterOlinePar, BindingResult result) {

        if (result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        try {
            String url = sysInterOlinePar.getUrl();//获取链接
            Map<String, String> params = sysInterOlinePar.getParams();//获取参数
            Map<String, String> header = sysInterOlinePar.getHeader();//获取请求头
            String jsonData = sysInterOlinePar.getJsonData();//获取请求体

            String loginUserId = UserUtil.getLoginUserId();
            if (StringUtils.isEmpty(loginUserId)){
                return Result.error("请登录在进行在线测试");
            }

            header.put("Authorization",UserUtil.getLoginToken()); //设置请求头
            if (StringUtils.isNotBlank(header.get("authorization_outtoken"))){
                header.put("Authorization",header.get("authorization_outtoken"));
            }

            params.keySet().removeIf(key->key.equals("interface_urlId"));
            HttpHeaders headers = new HttpHeaders();
            headers.setAll(header);

            HttpEntity<String> HttpEntity = new HttpEntity<>(headers);//构建请求头
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);//动态拼接url


            if (!params.isEmpty()) {  //gets
                for (Map.Entry<String, String> kv : params.entrySet()) {
                    //拼接好参数后的URltest.com/url?param1={param1}&param2={param2}
                    builder.queryParam(kv.getKey(), kv.getValue());
                }
                url = builder.build().toString();//获得最终拼接的url
            }
            if ("GET".equalsIgnoreCase(sysInterOlinePar.getMethodType())){
                //尝试get请求
                ResponseEntity<String> exchange = null;
                try {
                    exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity, String.class);
                } catch (RestClientException e) {
                    e.printStackTrace();
                    String localizedMessage = e.getLocalizedMessage();
                    if (localizedMessage.contains("500")){
                        return Result.ok("请检查测试接口客户端是否运行或接口设置错误--"+e.getMessage(),null);
                    }
                    if (localizedMessage.contains("401")){
                        return Result.ok("请求的接口权限不足或接口设置错误--"+e.getMessage(),null);
                    }
                    return Result.ok("接口设置错误--"+e.getMessage(),null);
                }
               // 支撑平台不过滤
                String body = exchange.getBody();
                JSONObject jsonObject = JSON.parseObject(body);
                return Result.ok(jsonObject,null);

            }

            if ("POST".equalsIgnoreCase(sysInterOlinePar.getMethodType())){  //POST请求需要拼接参数吗？
                HttpEntity<String> entity = new HttpEntity<String>(jsonData,headers);
                ResponseEntity<String> exchange = null;
                try {
                    exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                } catch (RestClientException e) {
                    e.printStackTrace();
                    String localizedMessage = e.getLocalizedMessage();
                    if (localizedMessage.contains("500")){
                        return Result.ok("请检查测试接口客户端是否运行或接口设置错误--"+e.getMessage(),null);
                    }
                    if (localizedMessage.contains("401")){
                        return Result.ok("请求的接口权限不足或接口设置错误--"+e.getMessage(),null);
                    }
                    return Result.ok("接口设置错误--"+e.getMessage(),null);
                }
                String body = exchange.getBody();
                JSONObject jsonObject = JSON.parseObject(body);
                return Result.ok(jsonObject,null);
            }


            return Result.error("在线测试失败");
        } catch (RestClientException e) {
            e.printStackTrace();
            return Result.error("在线测试失败");
        }


    }




}









