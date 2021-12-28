package com.sofn.dgap.scheduler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inf.cxf.service.IReaderService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapSyncDate;
import com.sofn.provider.dgap.TDgapSyncDateProvider;
import com.sofn.ws.DataImport;
import com.sofn.wsClient.DataImportClient;
import com.sofn.wsClient.ex.DataxImportClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.validation.constraints.NotNull;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SpybDataSyncScheduler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @DubboReference
    @NotNull
    private TDgapSyncDateProvider tDgapSyncDateProvider;

    @Value("${dgap.spyb.datafetch.youji.time}")
    String youjitime;

    @Value("${dgap.spyb.datafetch.lvse.time}")
    String lvsetime;

    @Value("${dgap.spyb.datafetch.youji.enable}")
    String youjiisAuto;

    @Value("${dgap.spyb.datafetch.lvse.enable}")
    String lvseisAuto;

    @Value("${dgap.spyb.datafetch.youji.url}")
    String youjiUrl = "";
    @Value("${dgap.spyb.datafetch.resource.xinyouji.token}")
    String xinyoujiToken = "";

    @Value("${dgap.spyb.datafetch.lvse.url}")
    String lvseUrl = "";
    @Value("${dgap.spyb.datafetch.resource.xinlvse.token}")
    String lvseToken = "";

    @Value("${dgap.spyb.datafetch.resource.dataimport.url}")
    String importUrl = "";


    @Scheduled(cron = "0 0,30 * * * ?")
    public void syncLvseData() throws Exception {

        String rT = "";
        String startdate = "";
        //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
        if (StringUtils.isNotBlank(tDgapSyncDateProvider.getLastSyncDate("lvse"))) {
            //读取数据库得到起始日期
            startdate = tDgapSyncDateProvider.getLastSyncDate("lvse");
        } else {
            //读取配置文件得到起始日期
            startdate = lvsetime;
        }
        //设置enddate
        String enddate = "";
        Date date = new Date();
        Calendar nowcalendar = Calendar.getInstance();
        nowcalendar.setTime(date);
        nowcalendar.add(Calendar.DAY_OF_MONTH, -1);//当前时间

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(startdate));
        calendar.add(Calendar.DAY_OF_MONTH, 14);//起始日期后的14天
        if (calendar.getTime().after(nowcalendar.getTime())) {
            enddate = df.format(nowcalendar.getTime());
        } else {
            enddate = df.format(calendar.getTime());
        }

        boolean isGo = false;
        if (RedisUtil.get("synclvsetime") == null || RedisUtil.get("synclvsetime").equals("") && !startdate.equalsIgnoreCase(enddate)) {

            RedisUtil.set("synclvsetime", enddate, 60);
            isGo = true;
        } else if (!RedisUtil.get("synclvsetime").equals(enddate) && !startdate.equalsIgnoreCase(enddate)) {
            isGo = true;
            RedisUtil.set("synclvsetime", enddate, 60);
        }

        boolean enabled = lvseisAuto.equalsIgnoreCase("true");
        log_info("lvse, isGo:" + isGo + ",enabled:" + enabled);
        if (isGo && enabled) {

            rT = runLvse("", startdate, enddate);
        } else {
            rT = enddate + "任务未执行";
        }
        log_info(enddate + "绿色数据:" + rT);
    }


    //秒（0~59） 分（0~59） 时（0~23） 天（月0~31） 月（0~11） 天（星期1~7）
    @Scheduled(cron = "0 0,30 * * * ?")
    public void syncYoujiData() throws Exception {

        String rT = "";
        String startdate = "";
        //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
        if (StringUtils.isNotBlank(tDgapSyncDateProvider.getLastSyncDate("youji"))) {
            //读取数据库得到起始日期
            startdate = tDgapSyncDateProvider.getLastSyncDate("youji");
        } else {
            //读取配置文件得到起始日期
            startdate = youjitime;
        }
        //设置enddate
        String enddate = "";
        Date date = new Date();
        Calendar nowcalendar = Calendar.getInstance();
        nowcalendar.setTime(date);
        nowcalendar.add(Calendar.DAY_OF_MONTH, -1);//当前时间

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(startdate));
        calendar.add(Calendar.DAY_OF_MONTH, 14);//起始日期后的14天
        if (calendar.getTime().after(nowcalendar.getTime())) {
            enddate = df.format(nowcalendar.getTime());
        } else {
            enddate = df.format(calendar.getTime());
        }

        boolean isGo = false;
        if (RedisUtil.get("syncyoujitime") == null || RedisUtil.get("syncyoujitime").equals("") && !startdate.equalsIgnoreCase(enddate)) {

            RedisUtil.set("syncyoujitime", enddate, 60);
            isGo = true;
        } else if (!RedisUtil.get("syncyoujitime").equals(enddate) && !startdate.equalsIgnoreCase(enddate)) {
            isGo = true;
            RedisUtil.set("syncyoujitime", enddate, 60);
        }

        boolean enabled = youjiisAuto.equalsIgnoreCase("true");
        log_info("youji, isGo:" + isGo + ",enabled:" + enabled);
        if (enabled && isGo) {
            rT = runYouji("", startdate, enddate);
        } else {
            rT = enddate + "任务未执行";
        }
        log_info(enddate + "有机数据:" + rT);
    }


    //秒（0~59） 分（0~59） 时（0~23） 天（月0~31） 月（0~11） 天（星期1~7）
    public String synctestYoujiData(String time, String n) throws Exception {

        String rT = "";
        String startdate = "";
        //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
        if (StringUtils.isNotBlank(tDgapSyncDateProvider.getLastSyncDate("youji"))) {
            //读取数据库得到起始日期
            startdate = tDgapSyncDateProvider.getLastSyncDate("youji");
        } else {
            //读取配置文件得到起始日期
            startdate = lvsetime;
        }
        //设置enddate
        String enddate = "";
        Date date = new Date();
        Calendar nowcalendar = Calendar.getInstance();
        nowcalendar.setTime(date);
        nowcalendar.add(Calendar.DAY_OF_MONTH, -1);//当前时间

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(startdate));
        calendar.add(Calendar.DAY_OF_MONTH, 14);//起始日期后的14天
        if (calendar.getTime().after(nowcalendar.getTime())) {
            enddate = df.format(nowcalendar.getTime());
        } else {
            enddate = df.format(calendar.getTime());
        }

        RedisUtil.set("syncyoujitime", n, 60);

        boolean isGo = false;
        if (RedisUtil.get("syncyoujitime") == null || RedisUtil.get("syncyoujitime").equals("") && !startdate.equalsIgnoreCase(enddate)) {

            RedisUtil.set("syncyoujitime", enddate, 60);
            isGo = true;
        } else if (!RedisUtil.get("syncyoujitime").equals(enddate)) {
            isGo = true;
            RedisUtil.set("syncyoujitime", enddate, 60);
        }

        if (isGo) {

            rT = runYouji(time, startdate, enddate);

        } else {
            rT = "参数time和n取值错误";
        }
        return rT;
    }


    //秒（0~59） 分（0~59） 时（0~23） 天（月0~31） 月（0~11） 天（星期1~7）
    public String syncTestLvseData(String time, String n) throws Exception {
        String rT = "";
        String startdate = "";
        //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
        if (StringUtils.isNotBlank(tDgapSyncDateProvider.getLastSyncDate("lvse"))) {
            //读取数据库得到起始日期
            startdate = tDgapSyncDateProvider.getLastSyncDate("lvse");
        } else {
            //读取配置文件得到起始日期
            startdate = lvsetime;
        }
        //设置enddate
        String enddate = "";
        Date date = new Date();
        Calendar nowcalendar = Calendar.getInstance();
        nowcalendar.setTime(date);
        nowcalendar.add(Calendar.DAY_OF_MONTH, -1);//当前时间

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(startdate));
        calendar.add(Calendar.DAY_OF_MONTH, 14);//起始日期后的14天
        if (calendar.getTime().after(nowcalendar.getTime())) {
            enddate = df.format(nowcalendar.getTime());
        } else {
            enddate = df.format(calendar.getTime());
        }

        RedisUtil.set("synclvsetime", n, 60);

        boolean isGo = false;
        if (RedisUtil.get("synclvsetime") == null || RedisUtil.get("synclvsetime").equals("")) {

            RedisUtil.set("synclvsetime", enddate, 60);
            isGo = true;
        } else if (!RedisUtil.get("synclvsetime").equals(enddate)) {
            isGo = true;
            RedisUtil.set("synclvsetime", enddate, 60);
        }

        if (isGo) {

            rT = runLvse(time, startdate, enddate);
        } else {
            rT = "参数time和n取值错误";
        }
        return rT;
    }

    //绿色运行任务
    private String runLvse(String time, String startdate, String enddate) throws Exception {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient(lvseUrl);
        client.getOutInterceptors().add(new AddHeaderInterceptor("greenfood", "3449A67B4F3247D65AB42D4130E19490"));


        if (StringUtils.isNotBlank(time)) {
            startdate = time;
        }


        Object[] objects;
        //获得绿色食品产品申请信息
//            String sqstr = service.getReportData(startdate, enddate);
//            //解密
//            sqstr = getFromBase64(sqstr);
        //获得绿色食品产品获证信息getCertData
        objects = client.invoke("getCertData", startdate, enddate);
        String hzstr = objects[0].toString();
        hzstr = getFromBase64(hzstr);
        //获得绿色食品证书变更信息getCertChange
        objects = client.invoke("getCertChange", startdate, enddate);
        String bgstr = objects[0].toString();
        bgstr = getFromBase64(bgstr);
        //获得绿色食品证书状态信息getCertStatus
        objects = client.invoke("getCertStatus", startdate, enddate);
        String ztstr = objects[0].toString();
        ztstr = getFromBase64(ztstr);


        //获取证书图片
//            String tpstr = service.getCertPic(startdate, enddate);
//            tpstr = getFromBase64(tpstr);

        Map<String, String> allMap = new HashMap<>();
//            allMap.put("sq", sqstr);
        allMap.put("getCertData", hzstr);
        allMap.put("getCertChange", bgstr);
        allMap.put("getCertStatus", ztstr);
//            allMap.put("tp", tpstr);

        String lis = "null";//定义这个lis属性是为了判断所有的接口是不是都没有传过来值，如果有值的话要将lis的值进行修改

        //分别解析getCertData、getCertChange、getCertStatus
        //在解析对面发过来的json数据的时候，因为大小写以及有-的问题，和实体类的属性不一致
        //所以统一在几个地方用了多个map转换了一下key值
        //后期维护可以在这个地方进行修改，将遍历从新储存map 的操作去掉
        for (String s : allMap.keySet()) {
            if (s.equalsIgnoreCase("getCertData")) {
                String data = allMap.get(s);
                Map<String, Object> map = JSON.parseObject(data, Map.class);
                if (map.get("LIST") != null) {
                    lis = "yes";
                    List<Map<String, Object>> li = (List<Map<String, Object>>) map.get("LIST");
                    for (int i = 0; i < li.size(); i++) {
                        Map<String, Object> map_tmp = li.get(i);
                        Map<String, Object> map_new = new HashMap<>();
                        //将一些空值过滤掉
                        for (String mapKey : map_tmp.keySet()) {
                            String newKey = mapKey.replace(" ", "");
                            //去掉下划线
                            newKey = newKey.toUpperCase();
                            map_new.put(newKey, map_tmp.get(mapKey));
                        }

                        Xinlvse xinlvse = (Xinlvse) convertMap(Xinlvse.class, map_new);
                        String id = map_new.get("BUSIID").toString();
                        xinlvse.setId(id);

                        DataxImportClient client1 = new DataxImportClient(importUrl);

                        DataImportResult result = client1.addLogData(lvseToken, xinlvse);

                        log_info("lvse——getCertData--addLogData: " + xinlvse + " result: " + result);

                        if (result.isSuccess()) {
                            savelvse(enddate);
                        } else {
                            log_error("error when addLogData, lvseToken:" + lvseToken + ",data:" + data + ",result:" + result);
                        }
                    }
                }
            } else if (s.equalsIgnoreCase("getCertChange")) {
                String data = allMap.get(s);
                Map<String, Object> map = JSON.parseObject(data, Map.class);
                if (map.get("LIST") != null) {
                    lis = "yes";
                    List<Map<String, Object>> li = (List<Map<String, Object>>) map.get("LIST");
                    for (int i = 0; i < li.size(); i++) {
                        Map<String, Object> map_tmp = li.get(i);
                        Map<String, Object> map_new = new HashMap<>();
                        boolean breakFlag = false;//**
                        for (String mapKey : map_tmp.keySet()) {
                            String newKey = mapKey.replace(" ", "");
                            if (mapKey.equalsIgnoreCase("changeItemType")) {
                                String value = "";

                                //这里就是把对应的changeItemType传来的数字编码找到对应的javabean属性
                                if (map_tmp.get(mapKey).toString().equals("01")) {
                                    value = "ENT_NAME_CN";
                                } else if (map_tmp.get(mapKey).toString().equals("03")) {
                                    value = "PRO_NAME_CN";
                                } else if (map_tmp.get(mapKey).toString().equals("04")) {
                                    value = "ACT_ANN_PRO_SCA";
                                } else if (map_tmp.get(mapKey).toString().equals("02")) {
//                                    value = "ACT_ANN_PRO_SCA";//产品标签

                                    // 目前数据库中，没有02代表的产品标签
                                    // 后面数据库中有了产品标签以后，注释掉后面的代码带**的代码
                                    // 并且修改lvse这个实体类

                                    breakFlag = true;//**
                                    break;//**

                                }
                                map_new.put(newKey, value);
                            }
                            if (mapKey.equalsIgnoreCase("tblGrBusiBaseId")) {
                                map_new.put("BUSI_ID", map_tmp.get(mapKey));
                            } else {
                                map_new.put(newKey, map_tmp.get(mapKey));
                            }
                        }

                        //下列代码也要注释掉
                        if (breakFlag) {//**
                            continue;//**
                        }//**

                        RowData rowData = new RowData();

                        rowData.setId(map_tmp.get("tblGrBusiBaseId").toString());

                        List<FieldData> fieldsData = new ArrayList<>();

                        FieldData fieldData = new FieldData();
                        fieldData.setColumnName(map_new.get("changeItemType").toString());
                        fieldData.setValue(map_new.get("changeAftKey").toString());

                        fieldsData.add(fieldData);

                        rowData.setFieldsData(fieldsData);

                        DataImportClient ct = new DataImportClient();
                        DataImport dI = ct.createImportClient(importUrl);

                        DataImportResult result = dI.updateDataLog(lvseToken, rowData);
                        log_info("lvse——getCertChange--updateDataLog: " + rowData + " result: " + result);

                        if (result.isSuccess()) {

                            //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
                            savelvse(enddate);
                        } else {
                            log_error("error when addLogData, lvseToken:" + lvseToken + ",data:" + data + ",result:" + result);
                        }
                    }
                }

            } else if (s.equalsIgnoreCase("getCertStatus")) {
                String data = allMap.get(s);
                Map<String, Object> map = JSON.parseObject(data, Map.class);
                if (map.get("LIST") != null) {
                    lis = "yes";
                    List<Map<String, Object>> li = (List<Map<String, Object>>) map.get("LIST");
                    for (int i = 0; i < li.size(); i++) {
                        Map<String, Object> map_tmp = li.get(i);

                        RowData rowData = new RowData();

                        rowData.setId(map_tmp.get("busiId").toString());

                        List<FieldData> fieldsData = new ArrayList<>();

                        FieldData fieldData1 = new FieldData();
                        fieldData1.setColumnName("LIC_STATUS");
                        fieldData1.setValue(map_tmp.get("licStatus").toString());

                        FieldData fieldData2 = new FieldData();
                        fieldData2.setColumnName("LIC_DEADLINE_END");
                        fieldData2.setValue(map_tmp.get("licDeadlineEnd").toString());

                        FieldData fieldData3 = new FieldData();
                        fieldData3.setColumnName("BUSI_ID");
                        fieldData3.setValue(map_tmp.get("busiId").toString());

                        fieldsData.add(fieldData1);
                        fieldsData.add(fieldData2);
                        fieldsData.add(fieldData3);

                        rowData.setFieldsData(fieldsData);

                        DataImportClient ct = new DataImportClient();
                        DataImport dI = ct.createImportClient(importUrl);

                        DataImportResult result = dI.updateDataLog(lvseToken, rowData);

                        log_info("lvse——getCertStatus--updateDataLog: " + rowData + " result: " + result);

                        if (result.isSuccess()) {

                            //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
                            savelvse(enddate);
                        } else {
                            log_error("error when addLogData, lvseToken:" + lvseToken + ",data:" + data + ",result:" + result);
                        }
                    }
                }
            }
        }

        //如果对面接口没有传来值。也要更新数据库
        if (lis.equals("null")) {
            //更新数据信息
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("data", "lvse");
            updateMap.put("lastSyncDate", enddate);
            //更新时间
            int rS = tDgapSyncDateProvider.updateByData(updateMap);
            if (rS != 1) {
                log_error("日期更新不成功！enddate:" + enddate);

                //新建数据信息
                Map<String, Object> insetMap = new HashMap<>();
                insetMap.put("data", "lvse");
                insetMap.put("lastSyncDate", enddate);
                SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                Date dates = new Date();
                insetMap.put("id", dfs.format(dates));
                insetMap.put("mark", "");
                //新建时间信息
                int rS1 = tDgapSyncDateProvider.insertByData(insetMap);
                if (rS1 != 1) {
                    log_error("日期新建不成功！enddate:" + enddate);
                } else {
                    logger.debug("日期新建成功！");
                }


            } else {
                logger.debug("日期更新成功！");
            }
        }

        String fi = JSON.toJSONString(allMap);

        return fi;
    }


    private void savelvse(String enddate) {
        //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
        if (StringUtils.isNotBlank(tDgapSyncDateProvider.getLastSyncDate("lvse"))) {
            //更新数据信息
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("data", "lvse");
            updateMap.put("lastSyncDate", enddate);
            //更新时间
            int rS = tDgapSyncDateProvider.updateByData(updateMap);
            if (rS != 1) {
                log_error("日期更新不成功！enddate:" + enddate);
            } else {
                logger.debug("日期更新成功！");
            }

        } else {
            //新建数据信息
            Map<String, Object> insetMap = new HashMap<>();
            insetMap.put("data", "lvse");
            insetMap.put("lastSyncDate", enddate);
            SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            Date dates = new Date();
            insetMap.put("id", dfs.format(dates));
            insetMap.put("mark", "");
            //新建时间信息
            int rS = tDgapSyncDateProvider.insertByData(insetMap);
            if (rS != 1) {
                log_error("日期新建不成功！enddate:" + enddate);
            } else {
                logger.debug("日期新建成功！");
            }

        }
    }


    private String runYouji(String time, String startdate, String enddate) throws Exception {
        String rT = "";

        IReaderService service = get(youjiUrl);

        if (StringUtils.isNotBlank(time)) {
            startdate = time;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(df.parse(startdate));
            calendar2.add(Calendar.DAY_OF_MONTH, 14);//起始日期后的14天
            enddate = df.format(calendar2.getTime());
        }

        //得到获证信息
        String hzStr = service.GetTraceProduct(startdate, enddate);
        //解密
        hzStr = getFromBase64(hzStr);

        //得到暂停信息
        String ztStr = service.GetPauseProduct(startdate, enddate);
        //解密
        ztStr = getFromBase64(ztStr);

        //得到注销信息
        String zxStr = service.GetCancelProduct(startdate, enddate);
        //解密
        zxStr = getFromBase64(zxStr);

        //得到撤销信息
        String cxStr = service.GetRevokeProduct(startdate, enddate);
        //解密
        cxStr = getFromBase64(cxStr);

        Map<String, String> allMap = new HashMap<>();
        allMap.put("hz", hzStr);
        allMap.put("zt", ztStr);
        allMap.put("zx", zxStr);
        allMap.put("cx", cxStr);

        String lis = "null";//定义这个lis属性是为了判断所有的接口是不是都没有传过来值，如果有值的话要将lis的值进行修改

        for (String s : allMap.keySet()) {
            String data = allMap.get(s);
            Map<String, Object> map = JSON.parseObject(data, Map.class);
            if (map.get("LIST") != null) {
                lis = "yes";
                List<Map<String, Object>> li = (List<Map<String, Object>>) map.get("LIST");
                for (int i = 0; i < li.size(); i++) {
                    Map<String, Object> map_tmp = li.get(i);
                    Map<String, Object> map_new = new HashMap<>();
                    for (String mapKey : map_tmp.keySet()) {
                        String newKey = mapKey.replace(" ", "");
                        //去掉下划线
                        newKey = newKey.replace("_", "");
                        map_new.put(newKey, map_tmp.get(mapKey));
                    }
                    //设置企业信息
                    String qyStr = service.GetBasePermitEnt(startdate, enddate);
                    //解密
                    qyStr = getFromBase64(qyStr);

                    Map<String, Object> qyMap = JSON.parseObject(qyStr, Map.class);
                    if (qyMap.get("LIST") != null) {
                        List<Map<String, Object>> liqy = (List<Map<String, Object>>) qyMap.get("LIST");
                        for (int j = 0; j < liqy.size(); j++) {
                            Map<String, Object> qyMap_tmp = liqy.get(j);
                            if (qyMap_tmp.get("ENT_NAME").equals(map_new.get("ENTNAME"))) {
                                for (String mapKey : qyMap_tmp.keySet()) {
                                    String newKey = mapKey.replace(" ", "");
                                    //去掉下划线
                                    newKey = newKey.replace("_", "");
                                    map_new.put(newKey, qyMap_tmp.get(mapKey));
                                }
                                //设置证书状态
                                setZS(s, map_new, enddate);
                                break;

                            } else {
                                //继续循环
                                if (j == liqy.size() - 1) {
                                    //没有企业信息
                                    //设置证书状态
                                    setZS(s, map_new, enddate);
                                }
                            }
                        }

                    } else {
                        //没有企业信息则直接存数据库
                        //设置证书状态
                        setZS(s, map_new, enddate);
                    }

                }
            }
        }

        //如果对面接口没有传来值。也要更新数据库
        if (lis.equals("null")) {
            //更新数据信息
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("data", "youji");
            updateMap.put("lastSyncDate", enddate);
            //更新时间
            int rS = tDgapSyncDateProvider.updateByData(updateMap);
            if (rS != 1) {
                log_error("日期更新不成功！enddate:" + enddate);

                //新建数据信息
                Map<String, Object> insetMap = new HashMap<>();
                insetMap.put("data", "youji");
                insetMap.put("lastSyncDate", enddate);
                SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                Date dates = new Date();
                insetMap.put("id", dfs.format(dates));
                insetMap.put("mark", "");
                //新建时间信息
                int rS1 = tDgapSyncDateProvider.insertByData(insetMap);
                if (rS1 != 1) {
                    log_error("日期新建不成功！enddate:" + enddate);
                } else {
                    logger.debug("日期新建成功！");
                }

            } else {
                logger.debug("日期更新成功！");
            }
        }
        rT = JSON.toJSONString(allMap);
        return rT;
    }

    /*
    //设置证书状态
     */
    private void setZS(String s, Map map_new, String enddate) throws Exception {
        //设置证书状态
        map_new.put("LICSTATUS", s);
        Xinyouji xinyouji = (Xinyouji) convertMap(Xinyouji.class, map_new);
        String prodName = StringUtils.isNotBlank(map_new.get("PRODNAME").toString()) ? map_new.get("PRODNAME").toString() : "";
        String prodDesc = StringUtils.isNotBlank(map_new.get("PRODDESC").toString()) ? map_new.get("PRODDESC").toString() : "";
        String certNo = StringUtils.isNotBlank(map_new.get("CERTNO").toString()) ? map_new.get("CERTNO").toString() : "";
        String id = signByMD5(prodName + prodDesc + certNo);
        xinyouji.setId(id);
        saveyoujiDatabase(importUrl, xinyoujiToken, xinyouji, tDgapSyncDateProvider, enddate, logger, "youji");
    }

    private void saveyoujiDatabase(String importUrl
            , String settoken
            , Xinyouji xin
            , TDgapSyncDateProvider tDgapSyncDateProvider
            , String lastSyncDate, Logger log, String d) {
        DataxImportClient client = new DataxImportClient(importUrl);

        DataImportResult result = client.addLogData(settoken, xin);

        log_info("youji——addLogData: " + xin + " result: " + result);
        if (result.isSuccess()) {

            //判断数据库日期是否为空，如果为空则采取读取配置文件得到起始日期
            if (StringUtils.isNotBlank(tDgapSyncDateProvider.getLastSyncDate(d))) {
                //更新数据信息
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("data", d);
                updateMap.put("lastSyncDate", lastSyncDate);
                //更新时间
                int rS = tDgapSyncDateProvider.updateByData(updateMap);
                if (rS != 1) {
                    log_error("日期更新不成功！enddate:" + lastSyncDate);
                } else {
                    log.info("日期更新成功！");
                }

            } else {
                //新建数据信息
                Map<String, Object> insetMap = new HashMap<>();
                insetMap.put("data", d);
                insetMap.put("lastSyncDate", lastSyncDate);
                SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                Date dates = new Date();
                insetMap.put("id", dfs.format(dates));
                insetMap.put("mark", "");
                //新建时间信息
                int rS = tDgapSyncDateProvider.insertByData(insetMap);
                if (rS != 1) {
                    log_error("日期新建不成功！enddate:" + lastSyncDate);
                } else {
                    log.info("日期新建成功！");
                }

            }
        }
    }


    private void log_error(String message) {
        logger.error(Constants.Features.Spybdj_e2g.logTag() + ":" + message);
    }

    private void log_info(String message) {
        logger.info(Constants.Features.Spybdj_e2g.logTag() + ":" + message);
    }


    public String getData() throws Exception {
        String data = "";
        List<TDgapSyncDate> list = tDgapSyncDateProvider.getData();
        JSONObject top = new JSONObject();
        JSONArray sync_dates = new JSONArray();
        for (TDgapSyncDate pLog : list) {
            JSONObject jo = new JSONObject();
            jo.put("id", pLog.getId());
            jo.put("data", pLog.getData());
            jo.put("lastSyncDate", pLog.getLastSyncDate());
            jo.put("mark", pLog.getMark());
            sync_dates.add(jo);
        }

        JSONObject spybScheduler = new JSONObject();
        spybScheduler.put("youjitime",youjitime);
        spybScheduler.put("lvsetime",lvsetime);
        spybScheduler.put("youjiisAuto",youjiisAuto);
        spybScheduler.put("lvseisAuto",lvseisAuto);

        top.put("spyb_scheduler",spybScheduler);
        top.put("spyb_sync_date",sync_dates);

        data = top.toJSONString();
        return data;
    }


    // 解密
    private String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    //创建webService，这个主要是创建youji接口的webservice
    private static IReaderService get(String url) {
        // 普通方式创建webService
        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION,
                WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, "admin");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientPasswordCallback.class.getName());
        ArrayList list = new ArrayList();
        // 创建安全验证
        list.add(new SAAJOutInterceptor());
        list.add(new WSS4JOutInterceptor(outProps));

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(IReaderService.class);
        factory.getOutInterceptors().addAll(list);
        factory.setAddress(url);
        return (IReaderService) factory.create();
    }

    public static String signByMD5(String sourceData)
            throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        String data = sourceData;

        byte[] sign = md5.digest(data.getBytes("GBK"));

        StringBuffer ret = new StringBuffer(sign.length);
        String hex = "";
        for (int i = 0; i < sign.length; i++) {
            hex = Integer.toHexString(sign[i] & 0xFF);

            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString().toUpperCase();
    }


    @SuppressWarnings("rawtypes")
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException, ParseException {

        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        //下面代码是判断map里面的key是否都有对应的javaBean属性
        //修改lvse对应的实体类之后，放开下面的注释代码，就能保证所有的key都有对应的javaBean属性
        for (Object KEY : map.keySet()) {
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (KEY.toString().equalsIgnoreCase(propertyName)) {
                    break;
                } else if (i == propertyDescriptors.length - 1) {

                    //修改javabean之后，放开下面代码

//                    throw new RuntimeException("三品一标数据项与数据库字段不符" + KEY.toString());
                }
            }
        }

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            for (Object mapKey : map.keySet()) {
                if (mapKey.toString().equalsIgnoreCase(propertyName)) {

                    Object value = map.get(propertyName.toUpperCase());

                    //因为对面传来的时间格式不同，需要把时间单独提出来处理掉，后期维护要增加判断语句
                    if (propertyName.equalsIgnoreCase("licDeadlineBeg")
                            || propertyName.equalsIgnoreCase("licDeadlineEnd")
                            || propertyName.equalsIgnoreCase("issueDate")
                            || propertyName.equalsIgnoreCase("licInvalidDate")
                            || propertyName.equalsIgnoreCase("recordTime")
                            ) {
                        if (map.get(propertyName.toUpperCase()) != null) {
                            //转换时间格式
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date utilDate = sdf.parse(value.toString());
                            value = new java.sql.Date(utilDate.getTime());
                        } else {
                            continue;
                        }

                        //处理小数格式数据
                    } else if (propertyName.equalsIgnoreCase("actAnnProSca")
                            || propertyName.equalsIgnoreCase("regCap")
                            || propertyName.equalsIgnoreCase("staffNum")
                            || propertyName.equalsIgnoreCase("technicistNum")
                            || propertyName.equalsIgnoreCase("sales")
                            || propertyName.equalsIgnoreCase("exportAm")
                            ) {
                        if (!value.equals("")) {
                            //转换小数格式
                            value = new BigDecimal(value.toString());
                        } else {
                            continue;
                        }
                    }

                    Object[] args = new Object[1];
                    args[0] = value;

                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }


        }
        return obj;
    }
}


