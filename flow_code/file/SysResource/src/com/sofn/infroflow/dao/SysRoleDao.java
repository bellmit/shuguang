package com.sofn.infroflow.dao;

import com.sofn.infroflow.model.SysRole;
import com.sofn.infroflow.util.BeanHandler;
import com.sofn.infroflow.util.JdbcTemplate;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色Dao
 * @author heyongjie
 * @date 2020/4/28 15:42
 */
public class SysRoleDao {


    /**
     * 获取所有用户
     * @return  List<SysUser>
     */
    public List<SysRole> getAllRole(){
        String sql = "select sr.id ,\n" +
                "sr.role_name as name,\n" +
                "sr.describe as description,\n" +
                "ss.app_id as appid,\n" +
                "sr.role_code as rolecode \n" +
                "from sys_role sr\n" +
                "inner join sys_subsystem ss on sr.subsystem_id = ss.id\n" +
                " where sr.del_flag != 'y' and sr.status = '1'";
        List<SysRole> list = JdbcTemplate.query(sql, new BeanHandler<>(SysRole.class));
        return list;
    }

    /**
     * 获取用户有的角色信息
     * @param userId  用户ID
     * @return    List<SysRole>
     */
    public List<SysRole> getRoleByUserId(String userId){
        if(StringUtils.isBlank(userId)){
            return new ArrayList<>();
        }
        String sql = "select sr.id ,sr.role_name as name,sr.describe as description,ss.app_id as appid,sr.role_code as rolecode from sys_role sr \n" +
                "                 left join sys_user_role sur on sur.role_id =  sr.id\n" +
                "\t\t\t\t\t\t\t\t left join sys_user su on su.id = sur.user_id\n" +
                "\t\t\t\t\t\t\t\t left join sys_subsystem ss on sr.subsystem_id = ss.id\n" +
                "                 where sr.del_flag != 'y' and sr.status = '1' and su.username = ?\n" +
                "union\n" +
                "select sr.id ,sr.role_name as name,sr.describe as description,ss.app_id as appid,sr.role_code as rolecode from sys_role sr \n" +
                "left join sys_role_group srg on sr.id = srg.role_id \n" +
                "left join sys_user_group sug on sug.group_id = srg.group_id\n" +
                "left join sys_user su on su.id = sug.user_id \n" +
                "left join sys_subsystem ss on sr.subsystem_id = ss.id\n" +
                "where sr.del_flag != 'y' and sr.status = '1' and su.username = ?";

        List<SysRole> list = JdbcTemplate.query(sql, new BeanHandler<>(SysRole.class),userId,userId);
        return list;
    }

    public static void main(String[] args) {

        SysRoleDao sysOrgDao = new SysRoleDao();
//        sysOrgDao.getRoleByUserId("05");
        List<SysRole> allRole = sysOrgDao.getAllRole();
        for(SysRole sysRole: allRole){
            if(sysRole.getName().contains("流程测试")){
                System.out.println("123");
            }

        }
        List<SysRole> hyj = sysOrgDao.getRoleByUserId("heyongjie-main");


        System.out.println(allRole);
    }



}
