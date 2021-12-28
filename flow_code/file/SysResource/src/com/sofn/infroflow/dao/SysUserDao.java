package com.sofn.infroflow.dao;

import com.sofn.infroflow.model.SysUser;
import com.sofn.infroflow.util.BeanHandler;
import com.sofn.infroflow.util.IdUtil;
import com.sofn.infroflow.util.JdbcTemplate;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 查询用户
 *
 * @author heyongjie
 * @date 2020/4/28 14:05
 */
public class SysUserDao {

    /**
     * 用户统一返回字段
     */
    private static final String USER_RESULT = "select su.id,su.username,su.nickname,su.email,su.mobile as mobilephone,su.remark as description ";

    /**
     * 用户基本条件
     */
    private static final String USER_SQL = USER_RESULT + " from sys_user su where su.del_flag != 'y' and su.status = '1'  ";


    /**
     * 获取所有用户
     *
     * @return List<SysUser>
     */
    public List<SysUser> getAllUser() {
        String sql = USER_SQL;
        List<SysUser> list = JdbcTemplate.query(sql, new BeanHandler<>(SysUser.class));
        return list;
    }

    /**
     * 根据ID获取用户
     *
     * @param id 用户ID
     * @return List<SysUser>
     */
    public SysUser getUserById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        String sql = USER_SQL + "and su.username = ?";
        List<SysUser> list = JdbcTemplate.query(sql, new BeanHandler<>(SysUser.class), id);
        
        if(list != null && list.size() > 0) {
      	  return list.get(0);
      }
        return null;
    }

    /**
     * 根据用户名称批量查询用户
     * @param usernames   用户名称
     * @return   用户信息
     */
    public List<SysUser> getUserByUsernames(Set<String> usernames){
    	if(usernames == null && usernames.size() == 0) {
   		 return new ArrayList<>();
     }
        String sql = USER_SQL + "and su.username in (" + IdUtil.getSqlId(usernames) + ")";
        List<SysUser> list = JdbcTemplate.query(sql, new BeanHandler<>(SysUser.class));
    	if(usernames == null && usernames.size() == 0) {
    		  return new ArrayList<>();
        }
        
        return list;
    }


    /**
     * 根据角色ID或者机构ID 查询用户
     *
     * @param roleId            角色ID
     * @param orgnizationUnitId 机构ID
     * @return List<SysUser>  符合条件的用户信息
     */
    public List<SysUser> getUsers(String roleId, String orgnizationUnitId) {
//        String sql = "select ID,USERNAME,NICKNAME,EMAIL，mobilePhone,description from (\n" +
//                "select \n" +
//                "\tSU.ID,SU.USERNAME,SU.NICKNAME,SU.EMAIL,SU.MOBILE mobilePhone,SU.REMARK description ,SU.DEL_FLAG,SU.STATUS,SU.CREATE_TIME,SU.ORGANIZATION_ID\n" +
//                "from SYS_USER SU\n" +
//                "LEFT JOIN SYS_USER_ROLE SUR ON SU.ID = SUR.USER_ID\n" +
//                "LEFT JOIN SYS_ROLE SR ON SR.ID = SUR.ROLE_ID\n" +
//                "WHERE 1=1 " + (StringUtils.isBlank(roleId) ? "" : " AND SR.ROLE_CODE = ?") + "\n" +
//                "UNION \n" +
//                "SELECT \t\n" +
//                "SU.ID,SU.USERNAME,SU.NICKNAME,SU.EMAIL,SU.MOBILE mobilePhone,SU.REMARK description ,SU.DEL_FLAG,SU.STATUS ,SU.CREATE_TIME,SU.ORGANIZATION_ID\n" +
//                "FROM  SYS_USER SU\n" +
//                "LEFT JOIN SYS_USER_GROUP SUG ON SUG.USER_ID = SU.ID\n" +
//                "WHERE SUG.GROUP_ID IN (\n" +
//                "\t SELECT GROUP_ID FROM SYS_ROLE_GROUP  SRG \n" +
//                "\t LEFT JOIN SYS_ROLE SR ON SRG.ROLE_ID = SR.ID \n" +
//                "   WHERE 1=1 " + (StringUtils.isBlank(roleId) ? "" : " AND SR.ROLE_CODE = ?") + " \n" +
//                ")\n" +
//                ") WHERE DEL_FLAG != 'Y' AND STATUS = '1' " + (StringUtils.isBlank(orgnizationUnitId) ? "" : " AND ORGANIZATION_ID=? ") + "  order by CREATE_TIME desc\n";


        String sql = "select id,username,nickname,email,mobilephone,description from (\n" +
                "select \n" +
                "\tsu.id as id,su.username as username,su.nickname as nickname,su.email as email,su.mobile as mobilephone,su.remark as description ,su.del_flag as del_flag,su.status as status,su.create_time as create_time,su.organization_id as organization_id\n" +
                "from sys_user su\n" +
                "left join sys_user_role sur on su.id = sur.user_id\n" +
                "left join sys_role sr on sr.id = sur.role_id\n" +
                "where 1=1 " + (StringUtils.isBlank(roleId) ? "" : " and sr.role_code = ?") + "\n" +
                "union \n" +
                "select \t\n" +
                "su.id as id,su.username as username,su.nickname as nickname,su.email  as email,su.mobile as mobilephone,su.remark as description ,su.del_flag as del_flag,su.status as status,su.create_time as create_time,su.organization_id  as organization_id\n" +
                "from  sys_user su\n" +
                "left join sys_user_group sug on sug.user_id = su.id\n" +
                "where sug.group_id in (\n" +
                "\t select group_id from sys_role_group  srg \n" +
                "\t left join sys_role sr on srg.role_id = sr.id \n" +
                "where 1=1 " + (StringUtils.isBlank(roleId) ? "" : " and sr.role_code = ?") + "\n" +
                ")\n" +
                ") kkk where del_flag != 'y' and status = '1' "+(StringUtils.isBlank(orgnizationUnitId) ? "" : "and organization_id= ?" )+"   order by create_time desc\n";


        System.out.println(String.format("根据角色Code（%s）或者机构ID（%s）查询用户，Sql如下：", roleId, orgnizationUnitId));
        System.out.println(sql);

        List<String> params = new ArrayList<>();
        if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(orgnizationUnitId)) {
            params.add(roleId);
            params.add(roleId);
            params.add(orgnizationUnitId);
        } else if (StringUtils.isNotBlank(roleId)) {
            params.add(roleId);
            params.add(roleId);
        } else if (StringUtils.isNotBlank(orgnizationUnitId)) {
            params.add(orgnizationUnitId);
        }
        return JdbcTemplate.query(sql, new BeanHandler<>(SysUser.class), params.toArray());
    }

    /**
     * 根据角色ID或者机构ID 查询用户
     *
     * @param roleId 角色ID
     * @return List<SysUser>  符合条件的用户信息
     */
    public List<SysUser> getUsersByRoleId(String roleId) {
        if (StringUtils.isBlank(roleId)) {
            return null;
        }
        return getUsers(roleId, null);
    }


    public static void main(String[] args) {
        SysUserDao sysUserDao = new SysUserDao();

//        sysUserDao.getAllUser();
//
//        sysUserDao.getUserById("heyongjie-main");


//        Set<String> usernames = new HashSet<>();
//        usernames.add("heyongjie-main");
//        usernames.add("heyongjie");
//        List<SysUser> userByUsernames = sysUserDao.getUserByUsernames(usernames);
//
        List<SysUser> allUser = sysUserDao.getUsers("fdpi_company", null);



        List<SysUser> usersByRoleId = sysUserDao.getUsersByRoleId("dev");

        System.out.println(allUser);
    }


}
