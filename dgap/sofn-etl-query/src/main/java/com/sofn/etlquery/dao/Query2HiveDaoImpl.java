package com.sofn.etlquery.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sofn.etlquery.utils.HiveUtils;
import com.sofn.etlquery.utils.ReplaceCnUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("query2HiveDao")
public class Query2HiveDaoImpl implements Query2HiveDao {


    @Resource(name = "etlDwDataSource")
    private DruidDataSource dataSource;


    /**
     * 获取所有的表
     *
     * @return
     * @throws Exception
     */
    @Override
    public List getAllTables() {
        List res = new ArrayList();
//        Connection conn = dataSource.getConnection();
//        String sql = "select t.table_name from user_tables t";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();

        String tableName[] = {"SYS_ARGI_PRODUCT", "TTS_SCLTXXCJ_COMPLAINT_V2", "SYS_TEST_STANDARD", "SYS_ORGANIZATION"};

        for (String name : tableName) {
            Map mid = new HashMap();
            mid.put("name", ReplaceCnUtils.replaceTableName(name.trim()));

            mid.put("value", name);

            res.add(mid);
        }
//        while (rs.next()) {
//            Map mid = new HashMap();
//            mid.put("name", ReplaceCnUtils.replaceTableName(rs.getString(1)));
//
//            mid.put("value", rs.getString(1));
//
//            res.add(mid);
//        }
//        HiveUtils.close(conn, ps, rs);
        return res;
    }

    /**
     * 组合查询去获取统计值
     *
     * @param key
     * @param tableName
     * @param sort
     * @return
     * @throws Exception
     */
    @Override
    public List getCountData(String key, String tableName, String sort) {
        List result = null;

        String sql = "select " + key + ", count(*) count from " + tableName + " GROUP BY " + key + " order by count " + sort;
        //被投诉企业最多的前20个

        if (tableName.toLowerCase().trim().equals("tts_scltxxcj_complaint_v2") && key.toLowerCase().trim().equals("complaint_ent_name")) {
            sql = "select a.complaint_ent_name,a.count,rownum from (select complaint_ent_name, count(*) count from "
                    + tableName + " group by " + key.trim() + " order by count " + sort + ")a where rownum<=20";
        }

        System.out.println(sql);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            result = new ArrayList();
            conn = dataSource.getConnection();
            System.out.println(sql);

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map res = new HashMap();
                if (rs.getString(1) == null || "".equals(rs.getString(1))) {
                    res.put("name", "其他");
                    res.put("value", Integer.parseInt(rs.getString(2)));
                } else {
                    res.put("name", ReplaceCnUtils.replaceTableField(rs.getString(1)));
                    res.put("value", Integer.parseInt(rs.getString(2)));
                }
                result.add(res);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HiveUtils.close(conn, ps, rs);

        }

        return result;
    }


    /**
     * 用表格的方式显示详细的数据
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    @Override
    public List getDetailData(String tableName, int start, int length) throws Exception {
        Connection conn = null;
        String sql = "select b.* from (select  a.* ,rownum rn from " + tableName + " a where rownum <= " + (start + length) + ") b where b.rn > " + start;
        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println("detailSql:" + sql);
        //获取表对应的列
        List<String> fields = null;
        JSONArray arr = new JSONArray();
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("detailSql:" + sql);
            //获取表对应的列
            fields = getField(tableName.toUpperCase());

            while (rs.next()) {
                JSONObject obj = new JSONObject(true);
                for (String field : fields) {
                    //todo 没有对应中文字段的直接替换掉
                    if (field.contains("ID") || field.contains("rn"))
                        continue;
                    String key = ReplaceCnUtils.tableFieldNameCn.get(field);
                    System.out.println(field + "key" + key);
                    obj.put(key, rs.getString(field));
                }
                arr.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HiveUtils.close(conn, ps, rs);
        }
        return arr;
    }

    @Override
    public Long getCountSize(String tableName) {

        Connection conn = null;
        String sql = "select count(*) from " + tableName;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long size = 0l;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next())
                size = rs.getLong(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HiveUtils.close(conn, ps, rs);
        }
        return size;
    }


    /**
     * 获取所有列名
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<String> getField(String tableName) {
        String sql = " select column_name from user_tab_columns where Table_Name='" + tableName + "'";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> fields = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            fields = new ArrayList<String>();
            while (resultSet.next()) {
                fields.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HiveUtils.close(conn, preparedStatement, resultSet);
        }

        return fields;
    }

}
