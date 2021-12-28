package com.sofn.service;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.provider.dgap.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiq on 2016/12/7.
 */
@Service
public class DataImportService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${dataImport.applydataRealtime.enable}")
    String applydataRealtime = "false";
    @Value("${dgap.notifier.lp.gjzspt.zhuisu.url}")
    String noticeCountryUrl = "http://218.89.222.118:8981/local_connect/tts/addNotice.do";

    @Value("${dgap.notifier.lp.localplatform.sichuan.url}")
    String noticeLocalUrl = "http://218.89.222.118:8981/local_connect/tts/addNotice.do";

    @DubboReference(timeout = 100*5*1000)
    private DataImportProvider dataImportProvider;

    @DubboReference(timeout = 100*5*1000)
    private DataImportApplyProvider dataImportApplyProvider;

    @DubboReference(timeout = 100*5*1000)
    private TDgapResourceProvider resourceProvider;
    @DubboReference(timeout = 100*5*1000)
    private TDgapResourceApplicationProvider tDgapResourceApplicationProvider;
    @DubboReference(timeout = 100*5*1000)
    private TDgapLocalPlatFormAppliProvider tDgapLocalPlatFormAppliProvider;



    String paraName="notice";

    /**
     *  通知国家或地方平台
     * @param resourceId
     * @param mark
     */
    public void dataNotifier(String token,String resourceId,String mark) {
        logger.info("token:"+token+", resourceId:"+ resourceId + ",mark:"+mark);
        TDgapResource dgapResource = resourceProvider.getDgapResource(resourceId);
        LocalPlatformNotifier lpn= new LocalPlatformNotifier();
        if(Constants.DataNotifier.数据采集通知.code().equals(dgapResource.getDataNotifier())){
            try {
                TDgapResourceApplication appli= tDgapResourceApplicationProvider.getAppliByToken(resourceId,token);
                logger.info("getLocalPlatformAppliId():"+appli.getLocalPlatformAppliId());
                TDgapLocalPlatFormAppli formAppli=tDgapLocalPlatFormAppliProvider.queryById(appli.getLocalPlatformAppliId());
                if(formAppli == null || formAppli.getDataNotifierUrl()==null||formAppli.getDataNotifierUrl().equals("")){
                    logger.info("notify url:"+noticeLocalUrl);
                    lpn.notify(noticeLocalUrl,paraName,mark,"UTF-8",true);
                }else{
                    logger.info("notify url:"+formAppli.getDataNotifierUrl());
                    lpn.notify(formAppli.getDataNotifierUrl(),paraName,mark,"UTF-8",true);
                }
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }else if (Constants.DataNotifier.数据下发通知.code().equals(dgapResource.getDataNotifier())){
            try {
                TDgapResourceApplication appli= tDgapResourceApplicationProvider.getAppliByToken(resourceId,token);
                logger.info("getLocalPlatformAppliId():"+appli.getLocalPlatformAppliId());
                TDgapLocalPlatFormAppli formAppli=tDgapLocalPlatFormAppliProvider.queryById(appli.getLocalPlatformAppliId());
                if(formAppli == null || formAppli.getDataNotifierUrl()==null||formAppli.getDataNotifierUrl().equals("")){
                    logger.info("notify url:"+noticeCountryUrl);
                    lpn.notify(noticeCountryUrl,paraName,mark,"UTF-8",true);
                }else{
                    logger.info("notify url:"+formAppli.getDataNotifierUrl());
                    lpn.notify(formAppli.getDataNotifierUrl(),paraName,mark,"UTF-8",true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            logger.info("notification not needed!");
/*
            try {
                TDgapResourceApplication appli= tDgapResourceApplicationProvider.getAppliByToken(resourceId,token);
                logger.info("getLocalPlatformAppliId():"+appli.getLocalPlatformAppliId());
                TDgapLocalPlatFormAppli formAppli=tDgapLocalPlatFormAppliProvider.queryById(appli.getLocalPlatformAppliId());
                if(formAppli == null || formAppli.getDataNotifierUrl()==null||formAppli.getDataNotifierUrl().equals("")){
                    lpn.notify(noticeLocalUrl,paraName,mark,"UTF-8",true);
                }else{
                    lpn.notify(formAppli.getDataNotifierUrl(),paraName,mark,"UTF-8",true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
        }
    }

    /**
     *  获取导入表的 最大mark值
     * @param resourceId
     * @return
     */
    private String getMaxMark(String resourceId) {
        int mark =0;
        if(dataImportProvider.getMark(resourceId)==null||dataImportProvider.getMark(resourceId)==0){
            mark=1;
        }else {
            mark=dataImportProvider.getMark(resourceId)+1;
        }
        return String.valueOf(mark);

    }

    public DataImportResult insertData(String token,String resourceId, RowData rowData){
        String mark=getMaxMark(resourceId);
        DataImportResult addResult = dataImportProvider.addData(token, resourceId, rowData);
        if("true".equals(applydataRealtime)){
            try {
                dataImportApplyProvider.applyDataUpsert(token,resourceId, rowData);
            } catch (Exception dataImportApplyExeption) {
                dataImportApplyExeption.printStackTrace();
            }
        }
        dataNotifier(token,resourceId,mark);
        return addResult;
    }

    public DataImportResult insertLogData(String token,String resourceId, RowData rowData){
        String mark=getMaxMark(resourceId);
        DataImportResult addResult = dataImportProvider.addDataLog(token, resourceId, rowData);
        dataNotifier(token,resourceId,mark);
        return addResult;
    }


    public DataImportResult updateData(String token,String resourceId,RowData rowData){
        String mark=getMaxMark(resourceId);
        DataImportResult updateResult = dataImportProvider.updateData(token,resourceId, rowData);
        if("true".equals(applydataRealtime)){
            try {
                dataImportApplyProvider.applyDataUpsert(token,resourceId, rowData);
//            } catch (DataImportApplyExeption dataImportApplyExeption) {
//                dataImportApplyExeption.printStackTrace();
//            }
            } catch (Exception dataImportApplyExeption) {
                logger.error("dataImportApplyExeption" +dataImportApplyExeption);
                dataImportApplyExeption.printStackTrace();
            }
        }
        dataNotifier(token,resourceId,mark);
        return updateResult;
    }

    public DataImportResult updateDataLog(String token,String resourceId,RowData rowData){
        String mark=getMaxMark(resourceId);
        DataImportResult updateResult = dataImportProvider.updateData(token,resourceId, rowData);
        dataNotifier(token,resourceId,mark);
        return updateResult;
    }

    public DataImportResult deleteData(String token,String resourceId,RowData rowData){
        String mark=getMaxMark(resourceId);
        DataImportResult delResult = dataImportProvider.deleteData(token,resourceId, rowData);
        if("true".equals(applydataRealtime)){
            try {
                dataImportApplyProvider.applyDataDelete(token,resourceId, rowData);
            } catch (Exception dataImportApplyExeption) {
                dataImportApplyExeption.printStackTrace();
            }
        }
        dataNotifier(token,resourceId,mark);
        return delResult;

    }

    public DataImportResult insertBatch(String token,String resourceId, List<RowData> data){
        String mark=getMaxMark(resourceId);
        DataImportResult addResult = dataImportProvider.addBatch(token,resourceId, data);
        if("true".equals(applydataRealtime)){
            try {
                addResult = dataImportApplyProvider.applyDataBatchAdd(token,resourceId, data);
            } catch (Exception dataImportApplyExeption) {
                dataImportApplyExeption.printStackTrace();
                //addResult.setSuccess(false);
            }

        }
        dataNotifier(token,resourceId,mark);
        return addResult;
    }
    public List<RowData> sampleData(String token, int size){
        return dataImportProvider.sampleData(token,size);
    }
}
