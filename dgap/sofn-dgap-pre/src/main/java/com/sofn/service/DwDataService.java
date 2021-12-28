package com.sofn.service;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.*;
import com.sofn.model.generator.TDgapTbResourceConfig;
import com.sofn.provider.dgap.TDgapDwProvider;
import com.sofn.provider.dgap.TDgapPreProvider;
import com.sofn.provider.dgap.TDgapTbResourceConfigProvider;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author tianjie
 *
 * @date 2016/12/1
 */
@Service
public class DwDataService {

    private static final Logger logger = LoggerFactory.getLogger(DwDataService.class.getName());
    @DubboReference
    private TDgapDwProvider tDgapDwProvider;

    Base64 bs = new Base64(-1);

    @DubboReference
    private TDgapTbResourceConfigProvider tDgapTbResourceConfigProvider;
    @DubboReference
    private TDgapPreProvider tDgapPreProvider;

    int markNumber = 1000;//每次传输的条数

    public DataExportResult getData(String resourceId, int start, int end, String[] fields, String condition) {
        DataExportResult ret = new DataExportResult();

        if (!(start == 0 && end == 0) && (start < 1 || end < 1 || start > end)) {
            ret.setSuccess(false);
            ret.setErrorCode(Constants.WSErrorType.DATA_ERROR.getErrorCode());
            ret.setErrorDesc(Constants.WSErrorType.DATA_ERROR.getErrorOutDesc());
            return ret;
        }

        List<TDgapTbResourceConfig> byResourceId = tDgapTbResourceConfigProvider.getByResourceId(resourceId);
        String tableName = null;

        List<String> fList = new ArrayList<>();
        if (fields != null) {
            fList = Arrays.asList(fields);
        }
        List<String> fieldsList = new ArrayList<>();
        List<String> allFieldsList = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        List<String> allColumnList = new ArrayList<>();

        for (TDgapTbResourceConfig config : byResourceId) {
            tableName = config.getTableName();

            String columnName;
            String type = config.getType().toUpperCase();
            if ("DATE".equals(type) || type.startsWith("TIMESTAMP")) {
                columnName = "to_char(" + config.getFieldName() + ",'yyyy-mm-dd hh24:mi:ss') as " + config.getFieldName();
            } else {
                columnName = config.getFieldName();
            }

            if (fields != null && fields.length > 0 && fList.contains(config.getFieldName())) {
                fieldsList.add(config.getFieldName());
                columnList.add(columnName);
            }
            allFieldsList.add(config.getFieldName());
            allColumnList.add(columnName);
        }

        if (fields == null || fields.length == 0) {
            fieldsList = allFieldsList;
            columnList = allColumnList;
        }

        try {
            List<Map<String, Object>> resultList = tDgapDwProvider.getDatas(tableName, start, end, columnList, condition);
            ret.setSuccess(true);
            List<RowData> rows = new ArrayList<RowData>();
            List<FieldData> fieldDatas = new ArrayList<FieldData>();
            RowData rowData = new RowData();
            for (Map<String, Object> record : resultList) {
                fieldDatas = new ArrayList<FieldData>();
                rowData = new RowData();
                for (String field : fieldsList) {
                    FieldData fd = new FieldData();
                    fd.setColumnName(field);
                    Object fv = record.get(field);
                    if (fv != null) {
                        //System.out.println("field value's type:"+fv.getClass().getSimpleName());
                        if (fv instanceof byte[]) {
                            String value = bs.encodeBase64String((byte[]) fv);
                            fd.setValue(value);
                            //System.out.println("value:"+value);
                        } else {
                            fd.setValue(fv.toString());
                        }
                    } else {
                        fd.setValue(null);
                    }
                    fieldDatas.add(fd);
                }
                rowData.setFieldsData(fieldDatas);
                rows.add(rowData);
            }
            ret.setData(rows);
        } catch (Exception e) {
            logger.error("", e);
            ret.setSuccess(false);
            ret.setErrorCode(Constants.WSErrorType.DATA_ERROR.getErrorCode());
            ret.setErrorDesc(Constants.WSErrorType.DATA_ERROR.getErrorOutDesc());
        }
        return ret;
    }

    public DataExportCountResult getdataNumber(String resourceId,String condition) {
        DataExportCountResult dataExportCountResult = new DataExportCountResult();
        try{
            log_info("resourceId:"+resourceId);
            List<TDgapTbResourceConfig> byResourceId = tDgapTbResourceConfigProvider.getByResourceId(resourceId);
            log_info("byResourceId:"+byResourceId);
            if(byResourceId==null|| byResourceId.size() <= 0){
                dataExportCountResult.setTotalCount(0);

            }else{
                String tableName =byResourceId.get(0).getTableName();
                log_info("tableName:"+tableName);
                Long dataNumber = tDgapDwProvider.getdataNumber(tableName, condition);
                log_info("dataNumber:"+dataNumber);
                dataExportCountResult.setTotalCount(dataNumber);
            }
            dataExportCountResult.setSuccess(true);
        }catch (Exception e){
            dataExportCountResult.setErrorCode(Constants.WSErrorType.DATA_ERROR.getErrorCode());
            dataExportCountResult.setErrorDesc(Constants.WSErrorType.DATA_ERROR.getErrorOutDesc());
        }
        return  dataExportCountResult;
    }

    public DataChangeLogResult getDataChangeLog(String resourceId, String mark) {
        DataChangeLogResult dr = new DataChangeLogResult();
        log_info("resourceId:"+resourceId);
        List<TDgapTbResourceConfig> byResourceId = tDgapTbResourceConfigProvider.getByResourceId(resourceId);
        log_info("byResourceId:"+byResourceId);
        if (byResourceId == null || byResourceId.size() <= 0) { //表名不为空
            dr.setSuccess(false);
            dr.setErrorCode(Constants.WSErrorType.DATA_ERROR.getErrorCode());
            dr.setErrorDesc(Constants.WSErrorType.DATA_ERROR.getErrorOutDesc());
            return dr;
        }
        String tableName = byResourceId.get(0).getTableName();
        log_info("tableName:"+tableName);
        List<String> allTableName = tDgapPreProvider.getAllTableNames(tableName);
        log_info("allTableName:"+allTableName);
        if (allTableName.size() == 0 || allTableName == null) {  //前置数据库没有该表
            dr.setSuccess(false);
            dr.setErrorCode(Constants.WSErrorType.DATA_ERROR.getErrorCode());
            dr.setErrorDesc(Constants.WSErrorType.DATA_ERROR.getErrorOutDesc());
            return dr;
        }
        log_info("tableName:"+tableName+",mark:"+mark);
        List<Map<String, Object>> resultList = tDgapPreProvider.getDatas(tableName, mark);//获取前置数据库该表大于mark的1000条数据
        log_info("resultList:"+resultList);
        for (Map<String, Object> temMap : resultList) {
            for (String temMapKey : temMap.keySet()) {
                if (temMapKey.equalsIgnoreCase(Constants.PresetField.访问令牌.code())) {
                    temMap.put(temMapKey, "");
                }
            }
        }
        if (resultList == null||resultList.size()==0) {//返回数据为空
            List<RowData> rows = new ArrayList<RowData>();
            dr.setSuccess(true);
            dr.setData(rows);
            return dr;
        }
        List<RowData> rows = new ArrayList<RowData>();
        List<FieldData> fieldDatas = new ArrayList<FieldData>();
        RowData rowData = new RowData();
        for (Map<String, Object> record : resultList) {
            fieldDatas = new ArrayList<FieldData>();
            rowData = new RowData();
            for (String key : record.keySet()) {

                FieldData fd = new FieldData();
                    fd.setColumnName(key);
                    Object fv = record.get(key);
                    if (fv != null) {
                        if (fv instanceof byte[]) {
                            String value = bs.encodeBase64String((byte[]) fv);//blob字段的处理
                            fd.setValue(value);
                        } else {
                            fd.setValue(fv.toString());
                        }
                    } else {
                        fd.setValue(null);
                    }
                    fieldDatas.add(fd);

            }
            rowData.setFieldsData(fieldDatas);

            rows.add(rowData);
        }
        dr.setData(rows);
        dr.setSuccess(true);
        int end = 0;
        if (resultList.size() < markNumber) { //最后一次前置数据库中剩余数据已不足1000条
            if (mark == null || mark.equals("") || mark.equals("null")) {
                end =  resultList.size()+1;
            } else {
                end = Integer.parseInt(mark) + resultList.size() + 1;
            }
            mark = String.valueOf(end);
        } else {
            if (mark == null || mark.equals("") || mark.equals("null")) {
                end = markNumber+1;
            } else {
                end = Integer.parseInt(mark) + markNumber;
            }
            mark = String.valueOf(end);
        }
        dr.setMark(mark);
        log_info("dr:"+dr);
        return dr;
    }

    private void log_info(String message) {
        logger.info(Constants.Features.Dgappre.logTag() + ":" + message);
    }

}
