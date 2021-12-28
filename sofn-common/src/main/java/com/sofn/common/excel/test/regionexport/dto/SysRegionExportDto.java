package com.sofn.common.excel.test.regionexport.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.db.DruidUtil;
import com.sofn.common.excel.test.bigdatatest.model.SysRegion;
import com.sofn.common.excel.test.bigdatatest.model.SysUserModel;
import com.sofn.common.excel.test.regionexport.SysRegionExportBean;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 导出行政区划程序---罗春斌需求
 * 导出格式为：
 *       省     市     县
 * 如  四川省  宜宾市   高县
 *     四川省  宜宾市  长宁县
 *     四川省  成都市  青羊区 。。。
 * @author heyongjie
 * @date 2019/10/17 15:39
 */
public class SysRegionExportDto {

    /**
     * 查询所有的省
     */
    private static final String GET_LIST_SQL = "SELECT\n" +
            "\tID,\n" +
            "\tREGION_NAME regionName,\n" +
            "\tregion_code AS regioncode\n" +
            "FROM\n" +
            "\tsys_region A\n" +
            "WHERE\n" +
            "\tA .PARENT_ID = ?\n" +
            "AND A .DEL_FLAG = 'N'\n" +
            " AND VERSION_CODE=(\n" +
            "      SELECT MAX(VERSION_CODE) FROM SYS_REGION RI\n" +
            "      WHERE RI.REGION_CODE=A.REGION_CODE\n" +
            "      AND RI.VERSION_CODE <= '1613977662344'\n" +
            "      )";


    /**
     * 查询用户
     */
    private static final String GET_USER_LIST_SQL = "select id,username from sys_user a where username like ? and DEL_FLAG = 'N'";

    /**
     * 根据机构名字查询机构
     */
    private static final String GET_ORG_INFO_BY_ORGNAME = "select id, organization_name  as orgname, parent_ids as parentids  from sys_org a where a.region_last_code = ? and a.organization_name like '%' || ?";


    private static final String GET_ORG = "select count(1) as num from sys_org a where a.organization_name = ?";


    /**
     * 根据名字查询机构信息
     * @param orgCode  机构名字
     * @param suffix   后缀
     * @return  机构信息
     */
    public static Integer getOrgNameNum(String orgName) {
        Map<String,String> maps  = Maps.newHashMap();
        try{
            Connection connection = DruidUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ORG);
            ps.setString(1, orgName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getObject("num") == null ? 0 : rs.getInt("num");

            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 根据名字查询机构信息
     * @param orgCode  机构名字
     * @param suffix   后缀
     * @return  机构信息
     */
    public static Map<String,String> getOrgInfo(String orgCode, String suffix) {
        Map<String,String> maps  = Maps.newHashMap();

        try{
            Connection connection = DruidUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ORG_INFO_BY_ORGNAME);
            ps.setString(1, orgCode);
            ps.setString(2, suffix);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maps.put("id", rs.getObject("id") == null ? "" : rs.getObject("id").toString());
                maps.put("orgname", rs.getObject("orgname") == null ? "" : rs.getObject("orgname").toString());
                maps.put("parentids", rs.getObject("parentids") == null ? "" : rs.getObject("parentids").toString());
                return maps;
            }
//            DruidUtil.releaseSqlConnection(rs, ps, connection);
            return maps;
        }catch (Exception e){
            e.printStackTrace();

        }

        return null;

    }

    /**
     * 获取省市县数据
     * @return    List<SysRegionExportBean>
     * @throws Exception   异常
     */
    public static List<SysRegionExportBean> getRegionExport() throws Exception {
        List<SysRegionExportBean> sysRegionExportBeans = Lists.newArrayList();
        // 1. 查询出所有的省
        List<SysRegion> shengList =  getData("100000");
        if(!CollectionUtils.isEmpty(shengList)){
            for (SysRegion sheng : shengList) {
                // 2. 查询市的数据
                List<SysRegion> shiList = getData(sheng.getId());
                for (SysRegion shi : shiList) {
                    // 3. 查询县的数据
                    List<SysRegion> xianList = getData(shi.getId());
                    xianList.forEach((xian) -> {
                        SysRegionExportBean exportData = new SysRegionExportBean();
                        exportData.setSheng(sheng.getRegionName());
                        exportData.setShi(shi.getRegionName());
                        exportData.setXian(xian.getRegionName());
                        sysRegionExportBeans.add(exportData);

                    });
                }
            }
        }
        return sysRegionExportBeans;
    }

    /**
     * 获取相应级别数据
     *
     * @return List<SysRegionExportBean>
     * @throws Exception   异常
     */
    public static List<SysRegion> getData(String parentId) throws Exception {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(GET_LIST_SQL);
        ps.setString(1, parentId);
//        ps.setString(2, "2021");
        ResultSet rs = ps.executeQuery();
        List<SysRegion> sysRegions = Lists.newArrayList();
        while (rs.next()) {
            SysRegion sysRegion = new SysRegion();
            sysRegion.setId(rs.getObject("id") == null ? "" : rs.getObject("id").toString());
            sysRegion.setRegionName(rs.getObject("regionName") == null ? "" : rs.getObject("regionName").toString());
            sysRegion.setRegionCode(rs.getObject("regioncode") == null ? "" : rs.getObject("regioncode").toString());
            sysRegions.add(sysRegion);
        }
//        DruidUtil.releaseSqlConnection(rs, ps, connection);
        return sysRegions;
    }

    /**
     * 获取相应用户
     *
     * @return  List<SysUser>
     * @throws Exception   异常
     */
    public static List<SysUserModel> getUser(String username) throws Exception {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(GET_USER_LIST_SQL);
        String param = "%"+username;
        ps.setString(1, param);
        ResultSet rs = ps.executeQuery();
        List<SysUserModel> sysUsers = Lists.newArrayList();
        while (rs.next()) {
            SysUserModel sysUser = new SysUserModel();
            sysUser.setId(rs.getObject("id") == null ? "" : rs.getObject("id").toString());
            sysUser.setUsername(rs.getObject("username") == null ? "" : rs.getObject("username").toString());
            sysUsers.add(sysUser);
        }
//        DruidUtil.releaseSqlConnection(rs, ps, connection);
        return sysUsers;
    }

}
