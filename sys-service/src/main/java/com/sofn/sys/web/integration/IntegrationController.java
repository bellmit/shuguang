package com.sofn.sys.web.integration;

import com.alibaba.fastjson.JSON;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysSplitUserOrgService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.vo.SysOrgForm;
import com.sofn.sys.vo.SysUserForm;
import com.sofn.sys.web.integration.IntegrationService.IntegrationService;
import com.sofn.sys.web.integration.unti.AttributeEntity;
import com.sofn.sys.web.integration.unti.BamboocloudUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.*;

import com.banboocloud.Codec.BamboocloudFacade;

/**
 * @author
 * @date
 */

@RestController
@RequestMapping("/IntegrationUser")
@Api(tags = "整合 三合一")
public class IntegrationController extends BaseController {

    @Value("${bam.type}")
    private String type;
    @Value("${bam.key}")
    private String key;

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysSplitUserOrgService sysSplitUserOrgService;
    @Autowired
    SysOrgService sysOrgService;
    @Autowired
    IntegrationService integrationService;


    @PostMapping("/SchemaService")
    public String SchemaService(HttpServletRequest req) {
        Map<String, Object> reqmap = this.bodyParse(req);
        Map<String, Object> schema = new LinkedHashMap<>();
//            账户信息
        List<AttributeEntity> accountAttrList = new ArrayList<>();
        AttributeEntity attr1 = new AttributeEntity();
        attr1.setType("String");
        attr1.setName("username");
        attr1.setRequired(true);
        attr1.setMultivalued(false);
        accountAttrList.add(attr1);
        AttributeEntity attr2 = new AttributeEntity();
        attr2.setType("String");
        attr2.setName("nickname");
        attr2.setRequired(true);
        attr2.setMultivalued(false);
        accountAttrList.add(attr2);
        AttributeEntity attr3 = new AttributeEntity();
        attr3.setType("String");
        attr3.setName("email");
        attr3.setRequired(true);
        attr3.setMultivalued(false);
        accountAttrList.add(attr3);
        AttributeEntity attr4 = new AttributeEntity();
        attr4.setType("String");
        attr4.setName("mobile");
        attr4.setRequired(true);
        attr4.setMultivalued(false);
        accountAttrList.add(attr4);
        AttributeEntity attr5 = new AttributeEntity();
        attr5.setType("String");
        attr5.setName("sex");
        attr5.setRequired(true);
        attr5.setMultivalued(false);
        accountAttrList.add(attr5);
        AttributeEntity attr6 = new AttributeEntity();
        attr6.setType("String");
        attr6.setName("status");
        attr6.setRequired(true);
        attr6.setMultivalued(false);
        accountAttrList.add(attr6);
        AttributeEntity attr7 = new AttributeEntity();
        attr7.setType("String");
        attr7.setName("organizationId");
        attr7.setRequired(true);
        attr7.setMultivalued(false);
        accountAttrList.add(attr7);
        AttributeEntity attr8 = new AttributeEntity();
        attr8.setType("String");
        attr8.setName("roleIds");
        attr8.setRequired(true);
        attr8.setMultivalued(false);
        accountAttrList.add(attr8);

        //组织机构
        List<AttributeEntity> orgattrlist = new ArrayList<>();
        AttributeEntity org1 = new AttributeEntity();
        org1.setType("String");
        org1.setName("address");
        org1.setRequired(true);
        org1.setMultivalued(true);
        orgattrlist.add(org1);
        AttributeEntity org2 = new AttributeEntity();
        org2.setType("String");
        org2.setName("addressLastCode");
        org2.setRequired(true);
        org2.setMultivalued(false);
        orgattrlist.add(org2);
        AttributeEntity org3 = new AttributeEntity();
        org3.setType("String");
        org3.setName("appIds");
        org3.setRequired(true);
        org3.setMultivalued(true);
        orgattrlist.add(org3);
        AttributeEntity org4 = new AttributeEntity();
        org4.setType("String");
        org4.setName("organizationLevel");
        org4.setRequired(true);
        org4.setMultivalued(false);
        orgattrlist.add(org4);
        AttributeEntity org5 = new AttributeEntity();
        org5.setType("String");
        org5.setName("organizationName");
        org5.setRequired(true);
        org5.setMultivalued(false);
        orgattrlist.add(org5);
        AttributeEntity org6 = new AttributeEntity();
        org6.setType("String");
        org6.setName("parentId");
        org6.setRequired(true);
        org6.setMultivalued(false);
        orgattrlist.add(org6);
        AttributeEntity org7 = new AttributeEntity();
        org7.setType("String");
        org7.setName("phone");
        org7.setRequired(true);
        org7.setMultivalued(false);
        orgattrlist.add(org7);
        AttributeEntity org8 = new AttributeEntity();
        org8.setType("String");
        org8.setName("principal");
        org8.setRequired(true);
        org8.setMultivalued(false);
        orgattrlist.add(org8);

        AttributeEntity org9 = new AttributeEntity();
        org9.setType("String");
        org9.setName("regionLastCode");
        org9.setRequired(true);
        org9.setMultivalued(false);
        orgattrlist.add(org9);

        AttributeEntity org10 = new AttributeEntity();
        org10.setType("String");
        org10.setName("regioncode");
        org10.setRequired(true);
        org10.setMultivalued(true);
        orgattrlist.add(org10);

        AttributeEntity org11 = new AttributeEntity();
        org11.setType("String");
        org11.setName("thirdOrg");
        org11.setRequired(true);
        org11.setMultivalued(false);
        orgattrlist.add(org11);
        AttributeEntity org12 = new AttributeEntity();
        org12.setType("String");
        org12.setName("year");
        org12.setRequired(true);
        org12.setMultivalued(false);
        orgattrlist.add(org12);

        schema.put("bimRequestId", reqmap.get("bimRequestId"));
        schema.put("account", accountAttrList);
        schema.put("organization", orgattrlist);

        String mapJson = this.bodyEncrypt(schema);
        return mapJson;
    }


    //  @ApiOperation(value = "账号创建" , notes = "权限:(sys:user:create)")
    @PostMapping("/UserCreateService")
    @SofnLog(value = "账号创建", type = "新增")
    public String UserCreateService(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqmap = this.bodyParse(request);
        SysUser sysUser = this.getsysUserFormAndSysUser(reqmap);
        Map<String, Object> schema = new HashMap<String, Object>();
        String userId = null;
        try {
            userId = integrationService.saveSysUserNew(sysUser);
            schema.put("resultCode", "0");
            schema.put("message", "success");
        } catch (Exception e) {
            schema.put("resultCode", "500");
            schema.put("message", "账号创建失败");
        }
        schema.put("bimRequestId", reqmap.get("bimRequestId"));
        schema.put("uid", userId);
//                加密
        String mapJson = this.bodyEncrypt(schema);
        //未加密用于调试。
        //String mapJson = JSON.toJSONString(schema);
        return mapJson;
    }


    //    @RequiresPermissions("sys:user:update")
    // @ApiOperation(value = "修改用户基本信息(不含密码)", notes = "权限:(sys:user:update)")
    @PostMapping("/UserUpdateService")
    @SofnLog(value = "修改用户", type = "修改")
    public String UserUpdateService(HttpServletRequest request, HttpServletResponse response) {
//        解密
        Map<String, Object> map = this.bodyParse(request);

        StringBuilder sbmsg = new StringBuilder();
        int resultCode = 0;

        SysUser storeUser = userService.getById(map.get("bimUid").toString());

        if (storeUser == null) {
            sbmsg.append("验证接口用户信息失败;");
            resultCode = 500;
        } else {
            SysUser sysUser = this.getsysUserFormAndSysUser(map);
            if (map.get("__ENABLE__") != null && map.get("__ENABLE__") !=""){
                if (map.get("__ENABLE__").toString()=="true"){
                    sysUser.setStatus("1");
                }
                if (map.get("__ENABLE__").toString()=="false"){
                    sysUser.setStatus("2");
                }
            }else {
                sysUser.setStatus("1");
            }
            sysUser.setId(map.get("bimUid").toString());
            try {
               // userService.updateSysUser(sysUser);
                integrationService.updateSysUser(sysUser);
                resultCode = 0;
                sbmsg.append("success");
            } catch (Exception e) {
                resultCode = 500;
                sbmsg.append("修改用户信息失败");
            }
        }
        Map<String, Object> schema = new HashMap<String, Object>();
        schema.put("bimRequestId", map.get("bimRequestId"));
        //schema.put("bimUid", map.get("bimUid").toString());
        schema.put("resultCode", resultCode);
        schema.put("message", sbmsg.toString());
        //                加密
        String mapJson = this.bodyEncrypt(schema);

        return mapJson;
    }


    //@ApiOperation(value = "删除用户", notes = "权限:(sys:user:batchDelete)")
    @PostMapping("/UserDeleteService")
    @SofnLog(value = "删除用户", type = "删除")
    public String UserDeleteService(HttpServletRequest request, HttpServletResponse response) {
//        解密
        Map<String, Object> map = this.bodyParse(request);

        StringBuilder sbmsg = new StringBuilder();
        int resultCode = 0;
        Map<String, Object> schema = new HashMap<String, Object>();

        if (StringUtils.isEmpty(map.get("bimUid").toString())) {
            sbmsg.append("当前用户不存在");
            resultCode = 500;
        } else {
            try {
               // userService.deleteSysUser(map.get("bimUid").toString());
                integrationService.deleteSysUser(map.get("bimUid").toString());
                sbmsg.append("删除成功");
                resultCode = 0;
            } catch (Exception e) {
                sbmsg.append("当前用户不存在");
                resultCode = 500;
            }
        }
        schema.put("bimRequestId", map.get("bimRequestId"));
        schema.put("resultCode", resultCode);
        schema.put("message", sbmsg.toString());
        //                加密
        String mapJson = this.bodyEncrypt(schema);
        return mapJson;
    }


    //  @ApiOperation(value = "组织机构创建",notes = "权限点:(sys:org:save)")
    @PostMapping("/OrgCreateService")
    public Result<String> OrgCreateService(@Validated @RequestBody SysOrgForm sysOrgForm, BindingResult br, String bimRequestId) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        SysOrg sysOrg = new SysOrg();
        BeanUtils.copyProperties(sysOrgForm, sysOrg);
        String id = sysOrgService.saveOrg(sysOrg, sysOrgForm.getAppIds());
        return Result.ok(id, bimRequestId);
    }


    //  @ApiOperation(value = "组织机构创建",notes = "权限点:(sys:org:save)")
    @PostMapping("/OrgUpdateService")
    public Result<String> OrgUpdateService(@Validated @RequestBody SysOrgForm sysOrgForm, BindingResult br, String bimRequestId) {
        if (br.hasErrors()) {
            return Result.error(getErrMsg(br));
        }

        SysOrg sysOrg = new SysOrg();
        BeanUtils.copyProperties(sysOrgForm, sysOrg);
        sysOrgService.saveOrg(sysOrg, sysOrgForm.getAppIds());
        return Result.ok(bimRequestId);
    }


    @PostMapping("/OrgDeleteService")
    @SofnLog(value = "删除机构", type = "删除")
    public Result<String> OrgDeleteService(@NotBlank @RequestParam("id") String id, String bimRequestId) {
        if (!StringUtils.isEmpty(id)) {
            // return Result.error(id);
            throw new SofnException("用户不存在");
        }
        sysSplitUserOrgService.deleteOrg(id);
        return Result.ok(bimRequestId);
    }


    //解密
    public Map<String, Object> bodyParse(HttpServletRequest req) {
        String bodyparam = BamboocloudUtils.getRequestBody(req);
        bodyparam = BamboocloudUtils.getPlaintext(bodyparam, "123456", "AES");
        Map<String, Object> reqmap = (Map<String, Object>) JSON.parse(bodyparam);
        return reqmap;
    }

    //加密
    public String bodyEncrypt(Map<String, Object> schema) {
        String mapJson = JSON.toJSON(schema).toString();
        mapJson = BamboocloudFacade.encrypt(mapJson, key, type);
        return mapJson;
    }

    //    封装对象 并转换为 vo对象
    public SysUser getsysUserFormAndSysUser(Map<String, Object> reqmap) {
        SysUserForm sysUserForm = new SysUserForm();
        if (reqmap.get("username") != null){
            sysUserForm.setUsername(reqmap.get("username").toString());
        }
        if (reqmap.get("nickname") != null){
            sysUserForm.setNickname(reqmap.get("nickname").toString());
        }
        if (reqmap.get("email") != null){
            sysUserForm.setEmail(reqmap.get("email").toString());
        }
        if (reqmap.get("mobile") != null){
            sysUserForm.setMobile(reqmap.get("mobile").toString());
        }
        //sysUserForm.setSex(Integer.parseInt(reqmap.get("sex").toString()));
        sysUserForm.setSex(3);
        //sysUserForm.setOrganizationId(reqmap.get("organizationId").toString());
        sysUserForm.setOrganizationId("sysorgroot");
        sysUserForm.setRoleIds("developer-role-has-all-perms");
        sysUserForm.setGroupIds("");
        SysUser sysUser = SysUserForm.getSysUser(sysUserForm);
        return sysUser;
    }

}

