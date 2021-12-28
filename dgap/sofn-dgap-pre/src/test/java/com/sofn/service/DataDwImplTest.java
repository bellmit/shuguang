package com.sofn.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by weiq on 2016/12/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-testconfig.xml")
public class DataDwImplTest {

    @Autowired
    private DwDataService dataDwService;

    @Test
    public void getData() throws Exception {

        String tableName = "";
        Integer start = 1;
        Integer end = 10;
        String[] fields = {"","",""};
        String condition = null;
        dataDwService.getData(tableName,start,end,fields,condition);
    }

}