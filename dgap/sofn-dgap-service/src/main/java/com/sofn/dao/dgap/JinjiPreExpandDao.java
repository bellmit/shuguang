package com.sofn.dao.dgap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * author tianjie
 *
 * @date 2016/11/22
 */
@Repository
public class JinjiPreExpandDao {

  @Resource(name = "preJdbcTemplate")
  private JdbcTemplate jdbcTemplate;


  public List<Map<String, Object>> getDatas(
      String tableName) {
    StringBuilder sb = new StringBuilder();

    sb.append("select * from "+tableName + " where ROWNUM <= 2000");

    List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
    return list;
  }
}
