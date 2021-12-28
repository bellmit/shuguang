package com.sofn.common.excel.test.bigdatatest.dto;

import com.google.common.collect.Lists;
import com.sofn.common.db.DruidUtil;
import com.sofn.common.excel.test.bigdatatest.model.SysRegion;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by heyongjie on 2019/6/19 13:41
 */
@Slf4j
public class SysRegionDto {


    /**
     * 获取数量
     *
     * @param params 参数
     * @return
     */
    public int getSysRegionNum(Map<String, Object> params) throws Exception {
        Connection connection = DruidUtil.getConnection();
        String sql = "select count(1) totalCount from SYS_REGION";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal data = (BigDecimal) rs.getObject(1);
            DruidUtil.releaseSqlConnection(rs, ps, connection);
            return data.intValue();
        }
        return 0;

    }


    public List<SysRegion> getData() throws Exception {

        String sql = "SELECT  " +
                "  ID id,  " +
                "  PARENT_ID parentId,  " +
                "  REGION_NAME regionName,  " +
                "  REGION_CODE regionCode,  " +
                "  SORTID sortid,  " +
                "  REMARK remark,  " +
                "  CREATE_USER_ID createUserId,  " +
                "  CREATE_TIME createTime   " +
                "FROM  " +
                "  sys_region_temp";

        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<SysRegion> sysRegions = Lists.newArrayList();
        while (rs.next()) {
            SysRegion sysRegion = new SysRegion();
            sysRegion.setId(rs.getObject("id") == null ? "" : rs.getObject("id").toString());
            sysRegion.setParentId(rs.getObject("parentId") == null ? "" : rs.getObject("parentId").toString());
            sysRegion.setRegionName(rs.getObject("regionName") == null ? "" : rs.getObject("regionName").toString());
            sysRegion.setRegionCode(rs.getObject("regionCode") == null ? "" : rs.getObject("regionCode").toString());
            sysRegion.setSortid(rs.getObject("sortid") == null ? 0 : Integer.parseInt(rs.getObject("sortid").toString()));
            sysRegion.setRemark(rs.getObject("remark") == null ? "" : rs.getObject("remark").toString());
            sysRegion.setCreateUserId(rs.getObject("createUserId") == null ? "" : rs.getObject("createUserId").toString());
            sysRegions.add(sysRegion);
        }
        DruidUtil.releaseSqlConnection(rs, ps, connection);
        // 总页数
        return sysRegions;
    }


    public static void main(String[] args) {
        SysRegionDto sysRegionDto = new SysRegionDto();
        try {
            List<SysRegion> sysRegions = sysRegionDto.getData();
            System.out.println(sysRegions);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
