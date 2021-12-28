package com.sofn.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;

/**
 * Created by: dong4j.
 * Date: 2016-09-29.
 * Time: 17:54.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-dao.xml")
public class DBUtilTest {

    @Test
    public void testGetInstance() throws Exception {

    }

    @Test
    public void testGetConnection() throws Exception {
        Connection connection =  DBUtil.getConnection();
        System.out.println(connection.getCatalog());
    }
}