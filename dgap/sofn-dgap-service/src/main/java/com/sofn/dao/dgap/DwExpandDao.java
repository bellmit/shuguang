package com.sofn.dao.dgap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author tianjie
 *
 * @date 2016/11/22
 */
@Repository
public class DwExpandDao {

  @Resource(name = "dwJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  /**
   * 查询仓库中有的表
   *
   * @param pattern
   * @return
   */
  public List<String> listDwTables(String pattern) {
    String sql = "select table_name from user_tables where table_name like '%" + pattern + "%'";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    List<String> tableList = new ArrayList<String>();
    for (Map<String, Object> map : list) {
      tableList.add(String.valueOf(map.get("table_name")));
    }
    return tableList;
  }

  public List<String> listDwTableFields(String tableName) {
    String sql = "select column_name from user_tab_columns where table_name = '" + tableName + "'";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    List<String> fieldList = new ArrayList<String>();
    for (Map<String, Object> map : list) {
      fieldList.add(String.valueOf(map.get("column_name")));
    }
    return fieldList;
  }

  public List<Map<String, Object>> getDatas(
      String tableName, Integer start, Integer end, List<String> fieldList, String condition) {
    StringBuilder sb = new StringBuilder();
    StringBuilder fieldsSb = new StringBuilder();
    for (String field : fieldList) {
      fieldsSb.append(field).append(",");
    }
    fieldsSb.setLength(fieldsSb.length() - 1);

    if(start==0 && end==0){
      sb.append("select ")
              .append(fieldsSb)
              .append(" from (")
              .append("select t.*,rownum rn from ")
              .append(tableName)
              .append(" t");

      if (condition != null) {
        sb.append(" where ");
        sb.append(condition);
      }
      sb.append(")");
    }else {
      sb.append("select ")
              .append(fieldsSb)
              .append(" from (")
              .append("select t.*,rownum rn from ")
              .append(tableName)
              .append(" t where rownum<=" + end);

      if (condition != null) {
        sb.append(" and ");
        sb.append(condition);
      }

      sb.append(") t1 where t1.rn>=" + start);
    }
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
    return list;
  }

  public Long getdataNumber(String tableName, String condition) {
    StringBuilder sb = new StringBuilder();
      sb.append("select ")
              .append("count(*)")
              .append(" from ")
              .append(tableName);
      if (condition != null) {
        sb.append(" where ");
        sb.append(condition);
      }
    long dataNumber= jdbcTemplate.queryForObject(sb.toString(),Long.class);
    return dataNumber;
  }

  public boolean isNullByDataId(String id,String tableName) {
    String sql = "select * from "+tableName+" where ID = '" + id + "'";
    List<Map<String, Object>> list=jdbcTemplate.queryForList(sql.toString());
    if(list.size()==0){
      return true;
    }else{
      return false;
    }
  }

  /**获得dw中已经被应用过的所有数据*/
  public Map<String,Map<String,Object>> getExistedData(String tableName) {
    String sql = "select * from "+tableName;
    List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
    Map<String,Map<String,Object>> id2Object = new HashMap<>();
    for(Map<String,Object> map : list){
      String id = (String) map.get("ID");
      id2Object.put(id,map);
    }
    return id2Object;
  }
}
