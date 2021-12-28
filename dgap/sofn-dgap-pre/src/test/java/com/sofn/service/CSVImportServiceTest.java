package com.sofn.service;

import com.sofn.model.dgap.RowData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by helong on 17-7-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-testconfig.xml")
public class CSVImportServiceTest {

    @Autowired
    private CSVImportService csvImportService;

    @Test
    public void readCsvFile() throws Exception {
        URL url = getClass().getClassLoader().getResource("dgap_resource.csv");
        //System.out.println("url:"+url.getFile());
        List<RowData> rowDatas = csvImportService.readCsvFile(url.getFile());
        assertEquals(41,rowDatas.size());
    }


    @Test
    public void noRecCopyFilesTo(){
        List<CSVImportService.FileOpStatus> results = CSVImportService.noRecCopyFilesTo("aaaaaa","/home/helong/he/codes/github/py/pysqlcli",
                "/tmp/pysqlcli",false);
        for(CSVImportService.FileOpStatus result: results){
            System.out.println(result);
        }
    }

    @Test
    public void noRecCopySingleFilesTo(){
        List<CSVImportService.FileOpStatus> results = CSVImportService.noRecCopyFilesTo("bbbbbb","/home/helong/he/lky/down/linux/CentOS-6.4-x86_64-LiveDVD.iso",
                "/tmp",false);
        for(CSVImportService.FileOpStatus result: results){
            System.out.println(result);
        }
    }

}