package com.sofn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.wsClient.ex.DataxExportClient;
import com.sofn.wsClient.ex.DataxImportClient;
import com.sofn.wsClient.samplebeans.GreenCertification;
import org.apache.commons.io.FileUtils;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.File;
import java.util.List;

/**
 * Created by helong on 18-5-8.
 */
public class GreenCertSyncClient {

    public static void main(String[] args) throws Exception {

        GreenCertSyncClient cli = new GreenCertSyncClient();

        DataxImportClient client = new DataxImportClient("http://1.1.1.10:17080/sofn-dgap-pre/ws/dataImport?wsdl");
        String token = "6h1caw98zv";
        GreenCertification data = new GreenCertification();

        data.setId("adsafdafdsaf");
        data.setProductName("绿色青椒");
        //设置其他属性

        //增加
        DataImportResult result  = client.addData(token, data);

        //变更
        //result = client.updateData(token, data);
        //删除
        //result = client.deleteData(token, data);

        if (!result.isSuccess()) {
            System.out.println(result.getErrorCode());
        }else{
            System.out.println("success!");
        }
    }

 }
