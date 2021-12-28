package com.sofn.common.dao;

import com.sofn.common.db.BeanHandler;
import com.sofn.common.db.JdbcTemplate;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Dict;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典查询Dao
 */
@Component
public class DictDao {

    /**
     * 根据字典类型和字典key获取字典
     * @param type 字典类型
     * @param key 字典key
     * @return 返回Dict简单字典对象
     */
    public Dict getByTypeAndKey(String type, String key) {
        String sql = "SELECT ID, DICTTYPEID, DICTNAME, DICTCODE FROM SYS_DICT WHERE DICTTYPE = ? AND DICTCODE = ?";

        List<Dict> list = JdbcTemplate.query(sql, new BeanHandler<>(Dict.class), type, key);
        if (list != null && list.size()>0){
            return list.get(0);
        }

        return null;
    }

    /**
     * 根据字典类型获取字典
     * @param type 字典类型
     * @return 返回Dict简单字典列表
     */
    public List<Dict> getByType(String type) {
        String sql = "SELECT SD.ID, SD.DICTTYPEID, SD.DICTNAME, SD.DICTCODE FROM SYS_DICT_TYPE SDT LEFT JOIN SYS_DICT SD " +
                "ON SDT.ID=SD.DICTTYPEID WHERE SD.ENABLE='Y' AND SDT.TYPEVALUE=?";
        return JdbcTemplate.query(sql, new BeanHandler<>(Dict.class), type);
    }

    /**
     * 根据字典类型和字典key获取字典
     * @param type 字典类型
     * @param value 字典key
     * @return 返回Dict简单字典对象
     */
    public Dict getByTypeAndValue(String type, String value) {
        String sql = "SELECT SD.ID, SD.DICTTYPEID, SD.DICTNAME, SD.DICTCODE FROM SYS_DICT_TYPE SDT LEFT JOIN SYS_DICT SD " +
                "ON SDT.ID=SD.DICTTYPEID WHERE SD.ENABLE='Y' AND SDT.TYPEVALUE=? AND SD.DICTCODE=?";
        List<Dict> list = JdbcTemplate.query(sql, new BeanHandler<>(Dict.class), type, value);
        if (list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 自定义Sql查询字典，Sql只能有一个返回值
     * @param sql  Sql
     * @param params  参数
     * @return     查询值
     */
    public List<String> getDictBySql(String sql,String[] params){
        if(!StringUtils.isBlank(sql)){
            // 校验 ? 和参数数量是否匹配
            char[] chars = sql.toCharArray();
            int num = 0;
            for (char aChar : chars) {
                if ('?' == aChar) {
                    num++;
                }
            }
            if(num != 0){
                if(params == null){
                    throw new SofnException("自定义Sql问号没有参数");
                }else if(num != params.length){
                    throw new SofnException("自定义Sql问号和参数个数不匹配");
                }
                return  JdbcTemplate.query(sql, new BeanHandler<>(String.class), params);
            }else{
                return  JdbcTemplate.query(sql, new BeanHandler<>(String.class));
            }

        }
        return null;
    }
}
