package com.sofn.common.dao;

import com.sofn.common.db.BeanHandler;
import com.sofn.common.db.JdbcTemplate;
import com.sofn.common.model.BaseData;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 基础数据查询Dao
 */
@Component
public class BaseDataDao {
    /**
     * 根据基础数据类型和基础数据key获取基础数据
     * @param type 基础数据类型
     * @param key 基础数据key
     * @return 返回BaseData简单基础数据对象
     */
    public BaseData getByTypeAndKey(String type, String key) {
        String sql = "SELECT ID, BASEDATATYPEID, BASEDATANAME, BASEDATACODE FROM SYS_BASEDATA WHERE BASEDATATYPE = ? AND BASEDATACODE = ?";

        List<BaseData> list = JdbcTemplate.query(sql, new BeanHandler<>(BaseData.class), type, key);
        if (list != null && list.size()>0){
            return list.get(0);
        }

        return null;
    }

    /**
     * 根据基础数据类型获取基础数据
     * @param type 基础数据类型
     * @return 返回BaseData简单基础数据列表
     */
    public List<BaseData> getByType(String appId,String type) {
        String sql = "\n" +
                "select\n" +
                "\tsbt. id,\n" +
                "\tsbt.basedatatypeid,\n" +
                "\tsbt.basedataname,\n" +
                "\tsbt.basedatacode\n" +
                "from\n" +
                "\tsys_basedata sbt\n" +
                "where\n" +
                "\tsbt. id in (\n" +
                "\t\tselect\n" +
                "\t\t\ta .base_data_id\n" +
                "\t\tfrom\n" +
                "\t\t\tsys_subsystem_basedata_own a\n" +
                "\t\tleft join sys_basedata_type sbte on a .base_data_type_id = sbte.\"id\"\n" +
                "\t\twhere\n" +
                "\t\t\ta .app_id = ?\n" +
                "\t\tand (\n" +
                "\t\t\ta .base_data_type_id = ? \n" +
                "\t\t\tor sbte.typevalue =?\n" +
                "\t\t)\n" +
                "\t)\n" +
                "and sbt.\"enable\" = 'Y'";
        return JdbcTemplate.query(sql, new BeanHandler<>(BaseData.class), appId,type, type);
    }

    /**
     * 根据基础数据类型获取未分配的基础数据
     * @param type 基础数据类型
     * @return 返回BaseData简单基础数据列表
     */
    public List<BaseData> getNotInDataByType(String appId,String type) {
      String sql = "SELECT sbt. ID, sbt.BASEDATATYPEID, sbt.BASEDATANAME, sbt.BASEDATACODE FROM SYS_BASEDATA sbt " +
        "left join SYS_BASEDATA_TYPE bt on bt.id=sbt.BASEDATATYPEID WHERE sbt. ID " +
        "not IN (" +
        "SELECT A.base_data_id FROM SYS_SUBSYSTEM_BASEDATA_OWN A " +
        "LEFT JOIN SYS_BASEDATA_TYPE sbte ON A .BASE_DATA_TYPE_ID = SBTE.ID WHERE A .APP_ID =? AND (A .BASE_DATA_TYPE_ID =? OR SBTE.TYPEVALUE =?)) AND sbt.ENABLE = 'Y' and bt.TYPEVALUE =?";
      return JdbcTemplate.query(sql, new BeanHandler<>(BaseData.class), appId,type, type, type);
    }

    /**
     * 根据基础数据类型获取全部的基础数据
     * @param type 基础数据类型
     * @return 返回BaseData简单基础数据列表
     */
    public List<BaseData> getDataByType(String type) {
      String sql = "SELECT sbt. ID, sbt.BASEDATATYPEID, sbt.BASEDATANAME, sbt.BASEDATACODE FROM SYS_BASEDATA sbt " +
        "left join SYS_BASEDATA_TYPE bt on bt.id=sbt.BASEDATATYPEID WHERE sbt.ENABLE = 'Y' and bt.TYPEVALUE =?";
      return JdbcTemplate.query(sql, new BeanHandler<>(BaseData.class), type);
    }

}
