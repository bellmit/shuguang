package com.sofn;

import com.sofn.etlquery.dao.Query2HiveDao;
import com.sofn.etlquery.dao.Query2HiveDaoImpl;
import org.junit.Test;

import java.util.List;


/**
 * @author: Perkinl
 * @Date: Create in 2018/3/20
 * @Version: 1.0
 */
public class HiveTest extends BaseTest{


    private OracleUtils oracleUtils=new OracleUtils();

    private Query2HiveDao query2HiveDao=new Query2HiveDaoImpl();



    @Test
    public void testJdbc(){
        oracleUtils.getConnection();
    }


    @Test
    public void getAlltables() throws Exception {
        List allTables = query2HiveDao.getAllTables();
    }
}
