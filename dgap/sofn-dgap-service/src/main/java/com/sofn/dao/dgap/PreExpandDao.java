package com.sofn.dao.dgap;

import com.sofn.core.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.sofn.model.dgap.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** Created by Administrator on 2016/12/7. */
@Repository
public class PreExpandDao {

  private Logger logger = LoggerFactory.getLogger(PreExpandDao.class);

  @Resource(name = "preJdbcTemplate")
  private JdbcTemplate jdbcTemplate;
  //@Value("${dataChangeLogResult.mark}")
  int markNumber=1000;//每次传输的条数

  /**
   * 分页查询得到数据
   *
   * @param start
   * @param end
   * @param status
   * @param tableName
   * @return
   */
  public List<Map<String, Object>> dataList(
      Integer start,
      Integer end,
      String status,
      String tableName,
      List<String> leftKey,
      List<String> rightValue) {
    StringBuilder sb = new StringBuilder();
    sb.append("select * ")
        .append(" from (")
        .append("select tt.*, ROWNUM AS rowno from (  select * from ")
        .append(tableName + " t");
    // 如果status不为空
    if (!StringUtils.isNullBlank(status)) {
      sb.append(" where t.status ='" + status + "'");
    }
    // 如果有其他条件，遍历条件加上去
    if (leftKey.size() != 0) {
      for (int i = 0; i < leftKey.size(); i++) {
        // 如过条件key对应的的值为""就不拼接到sql中
        if (!rightValue.get(i).equals("")) {
          sb.append(" and ")
              .append(leftKey.get(i))
              .append(" like ")
              .append(" '%")
              .append(rightValue.get(i))
              .append("%' ");
        } else sb.append("");
      }
    }
    sb.append(" order by t.data_import_time asc ) tt");
    sb.append(")table_alias where table_alias.rowno BETWEEN " + start + " and " + end);
    logger.info("------pre sql ------->" + sb);
    List<Map<String, Object>> list = null;
    try {
      list = jdbcTemplate.queryForList(sb.toString());
    } catch (Exception e) {
      /* e.printStackTrace();*/
      logger.error("", e);
    }

    return list;
  }

  /**
   * 分页查询得到数据的总个数
   *
   * @param status
   * @param tableName
   * @return
   */
  public int dataCount(
      String status, String tableName, List<String> leftKey, List<String> rightValue) {
    StringBuilder sb = new StringBuilder();
    sb.append("select count(*) ").append(" from ").append(tableName);
    if (!StringUtils.isNullBlank(status) && leftKey.size() != 0) {
      sb.append(" where status ='" + status + "'");
      for (int i = 0; i < leftKey.size(); i++) {
        if (!rightValue.get(i).equals("")) {
          sb.append(" and ");
          sb.append(leftKey.get(i))
              .append(" like ")
              .append(" '%")
              .append(rightValue.get(i))
              .append("%' ");
        } else sb.append("");
      }

    } else if (StringUtils.isNullBlank(status) && leftKey.size() != 0) {
      // 如只有一个条件，且条件的key对应的值为""则不用拼接where及后面的
      if (rightValue.size() == 1 && rightValue.get((rightValue.size() - 1)).equals("")) {
        sb.append("");
      } else {
        sb.append(" where ");
      }
      for (int i = 0; i < leftKey.size(); i++) {
        if (i != 0) {
          sb.append(" and ");
        }
        if (!rightValue.get(i).equals("")) {
          sb.append(leftKey.get(i))
              .append(" like  ")
              .append(" '%")
              .append(rightValue.get(i))
              .append("%' ");
        } else sb.append("");
      }
    } else if (StringUtils.isNullBlank(status) && leftKey.size() == 0) {
      sb.append("");
    } else if (!StringUtils.isNullBlank(status) && rightValue.size() == 0) {
      sb.append(" where status ='" + status + "'");
    }
    logger.info("------pre sql ------->" + sb);
    Integer count = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
    return count;
  }

  public List<String> getAllTableNames(String pattern) {
    String sql = "select table_name from user_tables where table_name like '%" + pattern + "%'";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    List<String> tableList = new ArrayList<String>();
    for (Map<String, Object> map : list) {
      tableList.add(String.valueOf(map.get("table_name")));
    }
    return tableList;
  }

  public List<Map<String,Object>> getDatas(String tableName, String mark) {
    StringBuilder sb = new StringBuilder();
    StringBuilder markSb = new StringBuilder();
    List<Map<String, Object>> list;
    if(mark==null||mark.equals("")||mark.equals("null")){
      markSb.append("SELECT min(Mark) FROM ") .append(tableName);
      mark=jdbcTemplate.queryForObject(markSb.toString(),java.lang.String.class);
      if(mark==null){
        mark="1";
      }
    }
    int end=Integer.parseInt(mark)+markNumber;//每次查询1000条
    sb.append("select *")
            .append(" from (")
            .append("select t.* from ")
            .append(tableName)
            .append(" t where t.MARK < " + String.valueOf(end));
    sb.append(") t1 where t1.MARK>=" + mark+"  order by t1.MARK");
    logger.info(Constants.Features.Dgappre.logTag()+":queryForList+sb.toString()"+sb.toString());
    list = jdbcTemplate.queryForList(sb.toString());
    logger.info(Constants.Features.Dgappre.logTag()+":queryForList+list"+list);
    return list;
  }

  public Map<String,Object> getDataById(String tableName, String id) {
    StringBuilder sb = new StringBuilder();
    sb.append("select *")
            .append(" from ").append(tableName)
            .append("  t where t.id ='" + id + "'");
    Map<String,Object> data = jdbcTemplate.queryForMap(sb.toString());
    return  data;
  }

  public List<Map<String, Object>> getAllDatas(String tableName) {
    StringBuilder sb = new StringBuilder();
    StringBuilder markSb = new StringBuilder();
    List<Map<String, Object>> list;
    sb.append("select *")
            .append(" from ")
            .append(tableName)
            .append(" t order by t.DATA_IMPORT_TIME desc");
    logger.info(Constants.Features.Dgappre.logTag()+":queryForList+sb.toString()"+sb.toString());
    list = jdbcTemplate.queryForList(sb.toString());
    logger.info(Constants.Features.Dgappre.logTag()+":queryForList+list"+list);
    return list;
  }
}
