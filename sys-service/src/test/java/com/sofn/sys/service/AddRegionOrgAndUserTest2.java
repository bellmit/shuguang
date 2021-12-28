package com.sofn.sys.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.sofn.SysServiceApplicationTests;
import com.sofn.common.excel.test.bigdatatest.model.SysRegion;
import com.sofn.common.excel.test.bigdatatest.model.SysUserModel;
import com.sofn.common.excel.test.regionexport.dto.SysRegionExportDto;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysRoleResourceMapper;
import com.sofn.sys.mapper.SysSystemOrgMapper;
import com.sofn.sys.model.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2020/7/13 14:06
 */
public class AddRegionOrgAndUserTest2 extends SysServiceApplicationTests {

    private static final String SUFFIX = "-ducss1";

    /**
     * 所有的机构名称
     */
    private static List<String> allOrgName = Lists.newArrayList();

    /**
     * 所有的机构信息
     */
    private static   List<SysOrg> allOrgInfo = Lists.newArrayList();


    /**
     * 所有的用户信息
     */
    private static List<SysUser> allSysUser = Lists.newArrayList();

    /**
     * 所有的角色信息
     */
    private static List<SysRole> sysRoles = Lists.newArrayList();


    private static List<SysRoleResource> sysRoleResources = Lists.newArrayList();


    private static List<SysUserRole> sysUserRoles = Lists.newArrayList();

    /**
     * 所有的机构关联关系
     */
    private static List<SysSystemOrg> allSysSystemOrg = Lists.newArrayList();

    /**
     * 所有的轻清数脚本
     */
    private static List<String> allRemoveSql = Lists.newArrayList();

    /**
     * 删除Sql语句
     */
    private static String deleteSqlTemplate = "delete from %s where id ='%s';";


    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SysSystemOrgMapper sysSystemOrgMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Autowired
    private SysUserService sysUserService;


    @Test
    @Transactional(rollbackFor = Exception.class)
    public void testSave(){
        // 添加数据
        try {
            getInfo();
            sysOrgService.saveBatch(allOrgInfo,1000);
            sysSystemOrgMapper.saveBatch(allSysSystemOrg);
            sysUserRoleService.saveBatch(sysUserRoles,1000);
//            sysRoleService.saveBatch(sysRoles,1000);
            sysUserService.saveBatch(allSysUser,1000);


//            executorService.execute(()->{
//                sysRoleResourceMapper.batchSaveSysRoleResource(sysRoleResources);
//                countDownLatch.countDown();
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        writeFile();
    }

    private static SysOrg getSysOrgByMap(Map<String, String> maps){
        SysOrg sysOrg = new SysOrg();
        if(maps != null || StringUtils.isNotBlank(maps.get("id"))){

            sysOrg.setId(maps.get("id"));
            sysOrg.setParentIds(maps.get("parentids"));
//            sysOrg.setAddress();
            return sysOrg;
        }
        return sysOrg;
    }


    public static Integer num = 1;

    public static String getOrgName(String orgName){
        if(orgName.equals("城区-ducss10")){
            System.out.println(123);
        }

        Integer orgNameNum = SysRegionExportDto.getOrgNameNum(orgName);

        if(allOrgName.contains(orgName) || orgNameNum > 0){
            // 如果存在
            orgName = orgName + num;
            num ++;
        }

        return orgName;

    }

    /**
     * 获取所有数据
     * @throws Exception   异常
     */
    public static void getInfo() throws Exception{
        // 省是否存在
        boolean flag1 ;

        // 市是否存在
        boolean flag2 ;


        List<SysRegion> shengList =  SysRegionExportDto.getData("100000");
        List<SysUserModel>  users = SysRegionExportDto.getUser(SUFFIX);
        List<String> names = users.stream().map(SysUserModel::getUsername).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(shengList)){
            for (SysRegion sheng : shengList) {
                // 查询这个机构是否存在
                String orgName = sheng.getRegionName() + SUFFIX;
                Map<String, String> orgInfo = SysRegionExportDto.getOrgInfo(sheng.getRegionCode(), SUFFIX);
                SysOrg shengOrgInfo = null;
                if(orgInfo == null || StringUtils.isBlank(orgInfo.get("id"))){
                    flag1 = false;
                    shengOrgInfo = getOrgInfo();

                    // 获取省机构信息
                    shengOrgInfo.setOrganizationName(getOrgName(sheng.getRegionName() + SUFFIX));
                    shengOrgInfo.setParentId(SysManageEnum.ROOT_ORG.getCode());
                    shengOrgInfo.setParentIds("/sysorgroot");
                    shengOrgInfo.setRegionLastCode(sheng.getRegionCode());
                    List<String> regionCode = Lists.newArrayList();
                    shengOrgInfo.setRegioncode(regionCode.toString());
                    shengOrgInfo.setOrganizationLevel("province");
                    // 用户是否存在， 不存                regionCode.add(sheng.getId());在加这段代码
                    String userId = getSysUserInfo(sheng.getRegionCode() + SUFFIX,sheng.getRegionName() + SUFFIX,shengOrgInfo.getId());
                    getUserRoleInfo(userId,"5e81e01b33d748cf9ee2b4994f212c55");
                }else {
                    flag1 = true;
                    shengOrgInfo = getSysOrgByMap(orgInfo);
                }
                // 2. 查询市的数据
                List<SysRegion> shiList = SysRegionExportDto.getData(sheng.getRegionCode());
                for (SysRegion shi : shiList) {
                    orgName = shi.getRegionName() + SUFFIX;
                    orgInfo = SysRegionExportDto.getOrgInfo(shi.getRegionCode(), SUFFIX);
                    SysOrg shiOrgInfo;
                    if(orgInfo == null || StringUtils.isBlank(orgInfo.get("id"))){
                        flag2 = false;
                        //  获取市机构信息
                        shiOrgInfo = getOrgInfo();
                        if(shi.getRegionName().equals("市辖区") ||shi.getRegionName().equals("县") || allOrgName.contains(shiOrgInfo.getOrganizationName()+ SUFFIX ) ){
                            shiOrgInfo.setOrganizationName(sheng.getRegionName() + shi.getRegionName() + SUFFIX);
                        }else{
                            shiOrgInfo.setOrganizationName(getOrgName(shi.getRegionName() + SUFFIX));
                        }
                        String shiUserId = getSysUserInfo(shi.getRegionCode() + SUFFIX,shi.getRegionName() + SUFFIX,shiOrgInfo.getId());
                        getUserRoleInfo(shiUserId,"39579bbc2321436e8602fb4d2a881219");

                        shiOrgInfo.setParentId(shengOrgInfo.getId() );
                        shiOrgInfo.setParentIds(shengOrgInfo.getParentIds() + "/" + shengOrgInfo.getId() );
                        shiOrgInfo.setRegionLastCode(shi.getRegionCode());
                        List<String> shiRegionCode = Lists.newArrayList();
                        shiRegionCode.add(sheng.getRegionCode());
                        shiRegionCode.add(shi.getRegionCode());
                        shiOrgInfo.setRegioncode(shiRegionCode.toString());
                        shiOrgInfo.setOrganizationLevel("city");
                    }else {
                        flag2 = true;
                        shiOrgInfo = getSysOrgByMap(orgInfo);
                    }

                    // 3. 查询县的数据
                    List<SysRegion> xianList = SysRegionExportDto.getData(shi.getRegionCode());
                    for (SysRegion xian : xianList) {
                        String orgName2 = xian.getRegionName() + SUFFIX;
                        Map<String,String> orgInfo2 = SysRegionExportDto.getOrgInfo(xian.getRegionCode(), SUFFIX);
                        if(orgInfo2 == null || StringUtils.isBlank(orgInfo2.get("id"))){
                            // 设置县级机构信息
                            SysOrg xianOrgInfo = getOrgInfo();
                            String xianUserId = getSysUserInfo(xian.getRegionCode() + SUFFIX,xian.getRegionName() + SUFFIX,xianOrgInfo.getId());
                            getUserRoleInfo(xianUserId,"d4f014ce307b41c8bb5bea278be43c62");

                            if(allOrgName.contains(xian.getRegionName() + SUFFIX)){
                                xianOrgInfo.setOrganizationName(getOrgName(sheng.getRegionName() + shi.getRegionName() + xian.getRegionName() + SUFFIX));

                            }else{
                                xianOrgInfo.setOrganizationName(getOrgName(xian.getRegionName() + SUFFIX));
                            }

                            xianOrgInfo.setParentId(shiOrgInfo.getId() );
                            xianOrgInfo.setParentIds(shiOrgInfo.getParentIds() + "/" + shiOrgInfo.getId() );


                            xianOrgInfo.setRegionLastCode(xian.getRegionCode());
                            List<String> xianRegionCode = Lists.newArrayList();
                            xianRegionCode.add(sheng.getRegionCode());
                            xianRegionCode.add(shi.getRegionCode());
                            xianRegionCode.add(xian.getRegionCode());
                            xianOrgInfo.setRegioncode(xianRegionCode.toString());
                            xianOrgInfo.setOrganizationLevel("county");

                            // 设置地址信息  关联关系
                            xianOrgInfo.setAddress(xianRegionCode.toString());
                            xianOrgInfo.setAddressLastCode(xian.getRegionCode());

                            // 如果市级不存在才需要设置地址
                            if(!flag2){
                                shiOrgInfo.setAddress(xianRegionCode.toString());
                                shiOrgInfo.setAddressLastCode(xian.getRegionCode());
                            }
                            // 如果省级不存在才需要设置地址
                            if(!flag1){
                                shengOrgInfo.setAddress(xianRegionCode.toString());
                                shengOrgInfo.setAddressLastCode(xian.getRegionCode());
                            }

                            getSysSystemOrg(xianOrgInfo.getId());

                            allOrgName.add(xianOrgInfo.getOrganizationName());
                            allOrgInfo.add(xianOrgInfo);

                            if(!flag2){
                                if(!allOrgName.contains(shiOrgInfo.getOrganizationName())){
                                    allOrgInfo.add(shiOrgInfo);
                                    getSysSystemOrg(shiOrgInfo.getId());
                                    allOrgName.add(shiOrgInfo.getOrganizationName());
                                }
                            }

                            if(!flag1) {
                                if (!allOrgName.contains(shengOrgInfo.getOrganizationName())) {
                                    allOrgInfo.add(shengOrgInfo);
                                    getSysSystemOrg(shengOrgInfo.getId());
                                    allOrgName.add(shengOrgInfo.getOrganizationName());
                                }
                            }
                        }

                    };
                }
            }
        }



    }


    private static void getUserAndRoleInfo(SysRegion sysRegion,String orgId){
        if(sysRegion == null){
            throw new SofnException("区划信息为空！");
        }

        // 添加用户
        String userId = getSysUserInfo(sysRegion.getId() + SUFFIX,sysRegion.getRegionName() + SUFFIX,orgId);
        // 添加角色
        String roleId = getRoleInfo(sysRegion.getRegionName() + SUFFIX);
        // 添加用户角色关联关系
        getUserRoleInfo(userId,roleId);
    }


    /**
     * 获取角色信息
     */
    private static String getRoleInfo(String roleName){
        SysRole sysRole = new SysRole();
        String id = IdUtil.getUUId();
        sysRole.setId(id);
        sysRole.setRoleName(roleName + SUFFIX);
        sysRole.setRoleCode(id);
        sysRole.setStatus("1");
        sysRole.setSubsystemId("1cc23447-777-b599f9f8864b");
        sysRole.setDescribe("批量添加");
        sysRole.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        String removeSql =  String.format(deleteSqlTemplate,"SYS_ROLE",id);
        getRoleAndMenuInfo(id);
        allRemoveSql.add(removeSql);
        sysRoles.add(sysRole);
        return id;
    }

    /**
     * 获取所有的角色和资源的信息
     * @param roleId  角色ID
     */
    private static void  getRoleAndMenuInfo(String roleId){
        SysRoleResource sysRoleResource = new SysRoleResource();
        String id = IdUtil.getUUId();
        sysRoleResource.setId(id);
        // 菜单为：test111111
        sysRoleResource.setResourceId("70938112cab14235ac2a3c3f608bb63f");
        sysRoleResource.setRoleId(roleId);
        String removeSql =  String.format(deleteSqlTemplate,"SYS_ROLE_RESOURCE",id);
        allRemoveSql.add(removeSql);
        sysRoleResources.add(sysRoleResource);

    }

    /**
     * 获取机构信息
     * @return  机构信息
     */
    private static SysOrg getOrgInfo(){
        String id = IdUtil.getUUId();

        SysOrg sysOrg = new SysOrg();
        sysOrg.setId(id);
        sysOrg.setCreateTime(new Date());
        sysOrg.setDelFlag("N");
        sysOrg.setCreateUserId("05");
        sysOrg.setThirdOrg("Y");
        String removeSql = String.format(deleteSqlTemplate,"SYS_ORG",id);
        allRemoveSql.add(removeSql);
        return sysOrg;
    }


    /**
     * 获取用户信息
     * @param username   用户名称-行政区划代码
     * @param nickName   用户昵称-行政区划名称
     * @param orgId   所属机构
     */
    private static String getSysUserInfo(String username,String nickName,String orgId ){
        String id = IdUtil.getUUId();
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setCreateUserId("05");
        sysUser.setCreateTime(new Date());
        sysUser.setUsername(username);
        sysUser.setNickname(nickName);
        sysUser.setMobile("11111111111");
        sysUser.setEmail("111@qq.com");
        sysUser.setInitPassword("123456");
        sysUser.setOrganizationId(orgId);
        sysUser.setPassword("721222cf58800881f98a33953cd06bc19e2ce03351f227e68cc2c240b39d4a5a");
        sysUser.setRemark("CLF系统根据行政区划批量添加！");
        sysUser.setStatus(SysManageEnum.STATUS_1.getCode());
        sysUser.setSex(Integer.parseInt(SysManageEnum.SEX_MAN.getCode()));
        allSysUser.add(sysUser);

        String removeSql = String.format(deleteSqlTemplate,"SYS_USER",id);
        allRemoveSql.add(removeSql);
        return id;
    }


    private static String getUserRoleInfo(String userId,String roleId){
        SysUserRole sysUserRole = new SysUserRole();
        String id = IdUtil.getUUId();
        sysUserRole.setId(id);
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRoles.add(sysUserRole);
        String removeSql = String.format(deleteSqlTemplate,"SYS_USER_ROLE",id);
        allRemoveSql.add(removeSql);
        return id;
    }


    /**
     * 获取机构关联信息
     * @param orgId  机构ID
     */
    private static void getSysSystemOrg(String orgId){
        String id = IdUtil.getUUId();
        String id2 = IdUtil.getUUId();
        SysSystemOrg sysSystemOrg = new SysSystemOrg(id,"sys",orgId);
        SysSystemOrg sysSystemOrg2 = new SysSystemOrg(id2,"ducss",orgId);
        allSysSystemOrg.add(sysSystemOrg);
        allSysSystemOrg.add(sysSystemOrg2);
        String removeSql = String.format(deleteSqlTemplate,"SYS_SYSTEM_ORG",id);
        allRemoveSql.add(removeSql);
        String removeSql2 = String.format(deleteSqlTemplate,"SYS_SYSTEM_ORG",id2);
        allRemoveSql.add(removeSql2);
    }





    /**
     * 写清数脚本
     */
    private static void writeFile(){
        File file = new File("C:\\Users\\heyongjie\\Desktop\\deleteRegionOrgAndUser_20210204.sql");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file) );
            allRemoveSql.forEach(item->{
                try {
                    bufferedWriter.write(item + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedWriter.flush();
            bufferedWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
