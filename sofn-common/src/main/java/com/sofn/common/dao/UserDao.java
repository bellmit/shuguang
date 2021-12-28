package com.sofn.common.dao;

import com.sofn.common.db.BeanHandler;
import com.sofn.common.db.JdbcTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户Dao
 * @author heyongjie
 * @date 2019/11/18 14:52
 */
@Slf4j
public class UserDao {

    /**
     * 根据Id获取用户名称
     * @param id  id
     * @return   用户名称
     */
    public String getUsernameById(String id){

        String sql = "select nickname from SYS_USER where id = ? and del_flag = 'N'";
        List<String> list = JdbcTemplate.query(sql, new BeanHandler<>(String.class), id);
        if(CollectionUtils.isEmpty(list)){
            log.warn("用户不存在。。。id={}",id);
            return "";
        }else{
            return list.get(0);
        }
    }

}
