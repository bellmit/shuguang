package com.sofn.common.clearlogicaldeletedata;

import com.sofn.common.clearlogicaldeletedata.dao.ClearLogicalDeleteDataDao;
import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 清除逻辑删除数据，这里只考虑oracle数据库
 * @author heyongjie
 * @date 2019/11/26 15:11
 */
@Slf4j
public class ClearLogicalDeleteData {

    /**
     * 当前应用的数据源连接
     */
    private DataSource dataSource;

    /**
     * 数据源连接URL
     */
    private String dataSourceUrl;

    /**
     * 清除数据具体实现
     */
    private ClearLogicalDeleteDataDao clearLogicalDeleteDataDao;

    private  final static String NOT_SUPPORT_LOG = "清理逻辑删除数据工具目前只支持ORACLE数据库，请配置sofn.cleardata.enable=false关闭该工具";

    /**
     * 配置清理逻辑删除数据工具
     * @param dataSource  数据源
     * @param dataSourceUrl   数据源连接串
     */
    public ClearLogicalDeleteData(DataSource dataSource,String dataSourceUrl,Integer dayAgo){
        this.dataSource = dataSource;
        this.dataSourceUrl = dataSourceUrl;
        isSupport();
        clearLogicalDeleteDataDao = new ClearLogicalDeleteDataDao(dataSource,dayAgo);
        log.info("清理数据工具启动成功");
    }

    /**
     * 检查该数据库连接是否被支持
     */
    private void isSupport(){
        if(StringUtils.isBlank(dataSourceUrl)){
            throw new SofnException(NOT_SUPPORT_LOG);
        }
        boolean flag = !dataSourceUrl.toUpperCase().contains("ORACLE");
        if(flag){
            throw new SofnException(NOT_SUPPORT_LOG);
        }
    }

    /**
     * 清除数据
     */
    public void clearData(){
        try {
            log.info("清理数据程序启动");
            clearLogicalDeleteDataDao.clearData();
            log.info("清理数据程序结束");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SofnException("数据清除失败");
        }
    }
}
