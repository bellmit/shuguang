//package com.sofn;
//
///**
// * Created by Administrator on 2018-8-13.
// */
//
//import com.sofn.model.dgap.DataChangeLogResult;
//import com.sofn.ws.DataExport;
//import com.sofn.wsClient.DataExportClient;
//import org.junit.Test;
//
///**
// * @author lijiang
// * @create 2018-08-13 18:16
// * @desc webservice测试
// **/
//public class WsClientTest {
//    @Test
//    public void WsTest() throws Exception {
//        String url = "http://10.0.50.167:17080/sofn-dgap-pre/ws/dataExport?wsdl";
//        String token = "6YvpNH0413";
//        String mark = "";
//
//        DataExportClient client = new DataExportClient();
//        DataExport dataExport = client.createExportClent(url);
//        DataChangeLogResult result = dataExport.getDataChangeLog(token, mark);
//        System.out.println(result);
//    }
//}
