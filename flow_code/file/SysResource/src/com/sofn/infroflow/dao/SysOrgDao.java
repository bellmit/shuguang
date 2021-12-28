package com.sofn.infroflow.dao;

import com.sofn.infroflow.model.SysOrg;
import com.sofn.infroflow.util.BeanHandler;
import com.sofn.infroflow.util.IdUtil;
import com.sofn.infroflow.util.JdbcTemplate;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author heyongjie
 * @date 2020/4/28 15:53
 */
public class SysOrgDao {

    /**
     * 获取系统所有机构
     */
    public List<SysOrg> getAllOrg() {
        String sql = "select so.id,so.organization_name as name,\r\n" +
                "so.parent_id as parentid,\r\n" +
                "'机构所属系统标识：'|| string_agg(sso.app_id, ',') as description\r\n" +
                "from sys_org so \r\n" +
                "left join sys_system_org sso on sso.org_id = so.id\r\n" +
                "where so.del_flag != 'y' group by so.id,so.organization_name,so.parent_id";
        System.out.println(sql);
        List<SysOrg> list = JdbcTemplate.query(sql, new BeanHandler<>(SysOrg.class));
        return list;
    }

    /**
     * 获取机构列表
     *
     * @param id 父ID
     * @return List<SysOrg>
     */
    public SysOrg getParentIdByParentId(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        String sql = "select so.id ,\n" +
                "so.organization_name as name,\n" +
                "so.parent_id as parentid,\n" +
                "'机构所属系统标识：'||string_agg(sso.app_id, ',') as description\n" +
                "from sys_org so \n" +
                "left join sys_system_org sso on sso.org_id = so.id\n" +
                "where so.del_flag != 'y' and so.id = ? " +
                " group by so.id,so.organization_name,so.parent_id";
        System.out.println(sql);
        List<SysOrg> list = JdbcTemplate.query(sql, new BeanHandler<>(SysOrg.class), id);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    public List<SysOrg> getOrgInfoByIds(Set<String> ids) {

        if (ids == null && ids.size() == 0) {
            return new ArrayList<>();
        }
        List<String> collect = IdUtil.getId(ids);
        String join = IdUtil.getStrId(collect);
        String sql = "select so.id,\n" +
                "so.organization_name as name,\n" +
                "so.parent_id as parentid,\n" +
                "'机构所属系统标识：'|| string_agg(sso.app_id, ',') as description\n" +
                "from sys_org so \n" +
                "left join sys_system_org sso on sso.org_id = so.id\n" +
                "where so.del_flag != 'y'  and so.id in (" + join + ") group by so.id,so.organization_name,so.parent_id";

        System.out.println(sql);
        List<SysOrg> list = JdbcTemplate.query(sql, new BeanHandler<>(SysOrg.class));
        return list;
    }


    public static void main(String[] args) {

        SysOrgDao sysOrgDao = new SysOrgDao();
        SysOrg org = sysOrgDao.getParentIdByParentId("123");
        Set<String> set = new HashSet<>();
        set.add("123");
        sysOrgDao.getOrgInfoByIds(set);
        System.out.println(123);


    }

}
