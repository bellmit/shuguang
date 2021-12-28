package com.sofn.common.clearlogicaldeletedata.dao;

import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * 定期清除数据持久层
 *
 * @author heyongjie
 * @date 2019/11/26 15:52
 */
@Slf4j
public class ClearLogicalDeleteDataDao {

    /**
     * 当前应用的数据源连接  30天之前
     */
    private DataSource dataSource;

    /**
     * 多少天之前的数据
     */
    private Integer dayAgo;

    /**
     * 获取更改日期和现在日期之前的差距的Sql
     * 因为有可能各个数据库存储的日期格式不一致，所以需要转一下
     */
    private final static String GET_DISPARITY_SQL = "abs(round(TO_NUMBER (SYSDATE - TO_DATE (TO_CHAR (A.UPDATE_TIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss'))))";


    /**
     * 构造数据源
     *
     * @param dataSource dataSource
     */
    public ClearLogicalDeleteDataDao(DataSource dataSource, Integer dayAgo) {
        this.dataSource = dataSource;
        if (dataSource == null) {
            throw new SofnException("ClearLogicalDeleteDataDao数据源为空");
        }
        this.dayAgo = dayAgo;
    }

    /**
     * 获取有DEL_FLAG 、UPDATE_TIME 、CREATE_TIME三个字段的表
     *
     * @return Set<String>
     */
    private Set<String> getUserHavaTable() throws SQLException {
        Set<String> userHaveTable = Sets.newHashSet();
        String sql = "select TABLE_NAME\n" +
                "from user_tab_columns c \n" +
                "where c.COLUMN_NAME IN ('DEL_FLAG','UPDATE_TIME','CREATE_TIME')\n" +
                "HAVING COUNT(TABLE_NAME) = 3\n" +
                "GROUP BY TABLE_NAME ";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            userHaveTable.add(resultSet.getString(1));
        }
        connection.close();
        return userHaveTable;
    }

    /**
     * 清除数据
     */
    public void clearData() throws SQLException {
        Set<String> userHaveTable = getUserHavaTable();
        if (!CollectionUtils.isEmpty(userHaveTable)) {
            Connection connection = dataSource.getConnection();
            for (String tableName : userHaveTable) {
                boolean delete = isDelete(tableName, connection);
                if (delete) {
                    StringBuilder sub = new StringBuilder("delete from ");
                    sub.append(tableName);
                    sub.append("  a where a.DEL_FLAG = 'Y' and   ");
                    sub.append(GET_DISPARITY_SQL);
                    sub.append(" >= ");
                    sub.append(dayAgo);
                    log.info("执行的Sql={}", sub.toString());
                    PreparedStatement preparedStatement = connection.prepareStatement(sub.toString());
                    int updateNum = preparedStatement.executeUpdate();
                    log.info("执行成功，共删除了{}条数据", updateNum);
                }
            }
            connection.close();
        }
    }

    /**
     * 判断是否需要删除
     *
     * @param tableName  表名
     * @param connection 数据库连接
     * @return true 可以删除   false 不需要删除
     */
    private boolean isDelete(String tableName, Connection connection) {
        StringBuilder sub = new StringBuilder("select COUNT(1) from ");
        sub.append(tableName);
        sub.append(" a where a.DEL_FLAG = 'Y' and  ");
        sub.append(GET_DISPARITY_SQL);
        sub.append(" >= ");
        sub.append(dayAgo);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sub.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long aLong = resultSet.getLong(1);
            if (aLong > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Sql【{}】执行失败", sub.toString());
            return false;
        }
        return false;

    }
}
