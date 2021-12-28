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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2020/7/13 14:06
 */
public class AddRegionOrgAndUserTest extends SysServiceApplicationTests {

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
     * 行政区划名字集合 如果出现重复名字就加一
     */
    private static List<String> regionName = Lists.newArrayList();

    private static Integer num = 0;


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

  OkHttpClient client = new OkHttpClient();

  @Test
  public void testUpdateLondLatd() {
    List<com.sofn.sys.model.SysRegion> list = sysRegionService.list(new QueryWrapper<com.sofn.sys.model.SysRegion>()
      .eq("LATITUDE","").or().isNull("LATITUDE"));
    list.forEach(new Consumer<com.sofn.sys.model.SysRegion>() {
      @Override
      public void accept(com.sofn.sys.model.SysRegion sysRegion) {
        if (StringUtils.isBlank(sysRegion.getLongitude())){
          Request request = new Request.Builder()
//            .url(buildUrl(sysRegion.getRegionCode(),sysRegion.getRegionName()))
            .url(buildUrl(null,sysRegion.getParentNames()))
            .build();

          try (Response response = client.newCall(request).execute()) {
            String jsonStr = response.body().string();
            if (StringUtils.isNotBlank(jsonStr)){
              System.out.println(jsonStr);
              PoiResult poiResult = JSON.parseObject(jsonStr, PoiResult.class);
              if (poiResult != null) {
                String location = poiResult.getPois().get("location");
                if (StringUtils.isNotBlank(location)){
                  String[] ll = location.split(",");
                  updateLongLant(sysRegion, ll[0], ll[1]);
                }
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  private void updateLongLant(com.sofn.sys.model.SysRegion sysRegion, String longd, String lantd){
    sysRegion.setLongitude(longd);
    sysRegion.setLatitude(lantd);
    sysRegionService.updateById(sysRegion);
  }

  private String buildUrl(String adCode, String keyword){
    StringBuilder sb = new StringBuilder();
    sb.append("https://restapi.amap.com/v3/place/text?");
//    sb.append("key=7ec4ae9fe58ae3de3f9689b90520e74f");
    sb.append("key=b072f3b8bdd60d72f294a9c0354cee38");
    sb.append("&");
    sb.append("keywords="+keyword);
    sb.append("&");
//    sb.append("city="+adCode);
//    sb.append("&");
    sb.append("offset=1");
    sb.append("&");
    sb.append("page=1");
    sb.append("&");
    sb.append("children=1");
    sb.append("&");
    sb.append("extensions=base");

    return sb.toString();
  }
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Autowired
    private SysUserService sysUserService;


    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void testSave(){
        // 添加数据
        try {
            getInfo();
            // 添加总站信息   总站用户属于根节点
            String userId = getSysUserInfo("100000" + SUFFIX,"总站用户"+ SUFFIX,"sysorgroot");
            getUserRoleInfo(userId,"84de7c4b27b34dd89bc549adad65668e");
            sysOrgService.saveBatch(allOrgInfo,1000);
            sysSystemOrgMapper.saveBatch(allSysSystemOrg);

            sysUserRoleService.saveBatch(sysUserRoles,1000);

//            sysRoleService.saveBatch(sysRoles,1000);

            sysUserService.saveBatch(allSysUser,1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        writeFile();
    }


    /**
     * 获取所有数据
     * @throws Exception   异常
     */
    public static void getInfo() throws Exception{
        List<SysRegion> shengList =  SysRegionExportDto.getData("100000");
        if(!CollectionUtils.isEmpty(shengList)){
            for (SysRegion sheng : shengList) {

                String shengRegionName = sheng.getRegionName();
                String realShengRegionName = "";
                if(regionName.contains(shengRegionName)){
                    num ++;
                    realShengRegionName = shengRegionName + num;
                }else{
                    realShengRegionName = shengRegionName;
                }
                regionName.add(shengRegionName);

                // 获取省机构信息
                SysOrg shengOrgInfo = getOrgInfo();
                shengOrgInfo.setOrganizationName(realShengRegionName + SUFFIX);
                shengOrgInfo.setParentId(SysManageEnum.ROOT_ORG.getCode());
                shengOrgInfo.setParentIds("/sysorgroot");
                shengOrgInfo.setRegionLastCode(sheng.getRegionCode());
                List<String> regionCode = Lists.newArrayList();
                regionCode.add(sheng.getRegionCode());
                shengOrgInfo.setRegioncode(regionCode.toString());
                shengOrgInfo.setOrganizationLevel("province");

//                getUserAndRoleInfo(sheng,shengOrgInfo.getId());   自动获取用户和机构信息
                String userId = getSysUserInfo(sheng.getRegionCode() + SUFFIX,realShengRegionName + SUFFIX,shengOrgInfo.getId());
                getUserRoleInfo(userId,"5e81e01b33d748cf9ee2b4994f212c55");
                // 2. 查询市的数据
                List<SysRegion> shiList = SysRegionExportDto.getData(sheng.getRegionCode());
                for (SysRegion shi : shiList) {

                    String shiRegionName = shi.getRegionName();
                    String realShiRegionName;

                    if(regionName.contains(shiRegionName)){
                        num ++;
                        realShiRegionName = shiRegionName + num;
                    }else{
                        realShiRegionName = shiRegionName;
                    }


                    regionName.add(shiRegionName);

                    //  获取市机构信息
                    SysOrg shiOrgInfo = getOrgInfo();
                    shiOrgInfo.setOrganizationName(realShiRegionName + SUFFIX);

                    String shiUserId = getSysUserInfo(shi.getRegionCode() + SUFFIX,realShiRegionName + SUFFIX,shiOrgInfo.getId());
                    getUserRoleInfo(shiUserId,"39579bbc2321436e8602fb4d2a881219");
                    shiOrgInfo.setParentId(shengOrgInfo.getId() );
                    shiOrgInfo.setParentIds(shengOrgInfo.getParentIds() + "/" + shengOrgInfo.getId() );
                    shiOrgInfo.setRegionLastCode(shi.getRegionCode());
                    List<String> shiRegionCode = Lists.newArrayList();
                    shiRegionCode.add(sheng.getRegionCode());
                    shiRegionCode.add(shi.getRegionCode());
                    shiOrgInfo.setRegioncode(shiRegionCode.toString());
                    shiOrgInfo.setOrganizationLevel("city");
                    // 3. 查询县的数据
                    List<SysRegion> xianList = SysRegionExportDto.getData(shi.getRegionCode());
                    xianList.forEach((xian) -> {

                        String xianRegionName = xian.getRegionName();
                        String realXianRegionName ="";
                        if(regionName.contains(xianRegionName)){
                            num ++;
                            realXianRegionName = xianRegionName + num;
                        }else{
                            realXianRegionName = xianRegionName;
                        }




                        regionName.add(xianRegionName);

                        // 设置县级机构信息
                        SysOrg xianOrgInfo = getOrgInfo();

                        String xianUserId = getSysUserInfo(xian.getRegionCode() + SUFFIX,realXianRegionName + SUFFIX,xianOrgInfo.getId());
                        getUserRoleInfo(xianUserId,"d4f014ce307b41c8bb5bea278be43c62");

                        xianOrgInfo.setOrganizationName(sheng.getRegionName() + shi.getRegionName() + realXianRegionName + SUFFIX);

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

                        shiOrgInfo.setAddress(xianRegionCode.toString());
                        shiOrgInfo.setAddressLastCode(xian.getRegionCode());

                        shengOrgInfo.setAddress(xianRegionCode.toString());
                        shengOrgInfo.setAddressLastCode(xian.getRegionCode());


                        getSysSystemOrg(xianOrgInfo.getId());
                        allOrgName.add(xianOrgInfo.getOrganizationName());
                        allOrgInfo.add(xianOrgInfo);
                        if(!allOrgName.contains(shiOrgInfo.getOrganizationName())){
                            allOrgInfo.add(shiOrgInfo);
                            getSysSystemOrg(shiOrgInfo.getId());
                            allOrgName.add(shiOrgInfo.getOrganizationName());
                        }

                        if(!allOrgName.contains(shengOrgInfo.getOrganizationName())) {
                            allOrgInfo.add(shengOrgInfo);
                            getSysSystemOrg(shengOrgInfo.getId());
                            allOrgName.add(shengOrgInfo.getOrganizationName());
                        }
                    });
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
    private static String getSysUserInfo(String username,String nickName,String orgId){
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
        sysUser.setRemark("CLF系统根据行政区划批量添加！");
        sysUser.setStatus(SysManageEnum.STATUS_1.getCode());
        sysUser.setPassword("721222cf58800881f98a33953cd06bc19e2ce03351f227e68cc2c240b39d4a5a");
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
        File file = new File("C:\\Users\\heyongjie\\Desktop\\deleteRegionOrgAndUser.sql");
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
