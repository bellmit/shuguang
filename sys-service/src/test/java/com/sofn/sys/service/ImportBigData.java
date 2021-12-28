//package com.sofn.sys.service;
//
//import com.sofn.common.utils.IdUtil;
//import com.sofn.sys.enums.SysManageEnum;
//import com.sofn.sys.model.SysOrg;
//import com.sofn.sys.model.SysSystemOrg;
//import com.sofn.sys.model.SysUser;
//import org.junit.Test;
//
//import java.util.Date;
//
///**
// *
// *
// * 测试导入大数据
// * @author heyongjie
// * @date 2020/7/28 14:13
// */
//public class ImportBigData {
//
//    /**
//     * 测试导入的数据量
//     */
//    public static final Integer DATA_NUM =  10000;
//
//
//    /**
//     * 删除Sql语句
//     */
//    private static String deleteSqlTemplate = "delete from %s where id ='%s';";
//
//
//    @Test
//    public void testAddOrg(){
//
//
//
//        for (int i = 0; i < DATA_NUM; i++) {
//
//        }
//
//
//
//
//    }
//
//
//    @Test
//    public void testAddRole(){
//
//
//    }
//
//
//    @Test
//    public void testAddUser(){}
//
//
//
//    /**
//     * 获取机构信息
//     * @return  机构信息
//     */
//    private static SysOrg getOrgInfo(String orgName,){
//        String id = IdUtil.getUUId();
//        SysOrg sysOrg = new SysOrg();
//        sysOrg.setId(id);
//        sysOrg.setCreateTime(new Date());
//        sysOrg.setDelFlag("N");
//        sysOrg.setCreateUserId("05");
//        sysOrg.setThirdOrg("Y");
//        sysOrg.setOrganizationName(orgName);
//        sysOrg.setParentId(SysManageEnum.);
//        String removeSql = String.format(deleteSqlTemplate,"SYS_ORG",id);
//        return sysOrg;
//    }
//
//    /**
//     * 获取机构关联信息
//     * @param orgId  机构ID
//     */
//    private static void getSysSystemOrg(String orgId){
//        String id = IdUtil.getUUId();
//        SysSystemOrg sysSystemOrg = new SysSystemOrg(id,"sys",orgId);
//        String removeSql = String.format(deleteSqlTemplate,"SYS_SYSTEM_ORG",id);
//    }
//
//
//
//    /**
//     * 获取用户信息
//     * @param username   用户名称-行政区划代码
//     * @param nickName   用户昵称-行政区划名称
//     * @param orgId   所属机构
//     */
//    private static void getSysUserInfo(String username,String nickName,String orgId){
//        String id = IdUtil.getUUId();
//        SysUser sysUser = new SysUser();
//        sysUser.setId(id);
//        sysUser.setCreateUserId("05");
//        sysUser.setCreateTime(new Date());
//        sysUser.setUsername(username);
//        sysUser.setNickname(nickName);
//        sysUser.setMobile("11111111111");
//        sysUser.setEmail("111@qq.com");
//        sysUser.setInitPassword("123456");
//        sysUser.setOrganizationId(orgId);
//        sysUser.setRemark("CLF系统根据行政区划批量添加！");
//
//        String removeSql = String.format(deleteSqlTemplate,"SYS_USER",id);
//
//    }
//
//
//
//
//
//}
