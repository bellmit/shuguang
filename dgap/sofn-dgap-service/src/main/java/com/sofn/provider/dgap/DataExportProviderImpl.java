package com.sofn.provider.dgap;

/**
 * @description:
 * @author: dongql
 * @date: 2017/8/31 15:36
 */


import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.UUIDUtil;

import com.sofn.dao.dgap.DataImportExpandMapper;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.model.dgap.*;
import com.sofn.model.generator.TDgapTbResourceConfig;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;


@DubboService(interfaceClass = DataExportProvider.class)
public class DataExportProviderImpl implements DataExportProvider {
    @Autowired
    private TDgapResourceApplicationExpandMapper tDgapResourceApplicationExpandMapper;
    @Autowired
    private TDgapTbResourceConfigProviderImp provider;
    @Autowired
    private TDgapDwProviderImpl tDgapDwProvider;
    @DubboReference
    private TDgapPreProvider tDgapPreProvider;

    @DubboReference
    private TDgapTbResourceConfigProvider tDgapTbResourceConfigProvider;

    @Autowired
    private DataImportApplyProvider dataImportApplyProvider;
    @Autowired
    private DataImportExpandMapper dataImportExpandMapper;

    Base64 bs = new Base64(-1);

    @Override
    public DataExportResult getData(
            String accessToken, int start, int finish, List<String> fields, String condition) {
        DataExportResult ret = new DataExportResult();
        Map<String, Object> map =
                tDgapResourceApplicationExpandMapper.getTdgapResourceApplication(accessToken);
        String resourceId = map.get("WS_ID").toString();
        List<TDgapTbResourceConfig> tbResource = provider.getByResourceId(resourceId);
        String tableName = "";
        for (TDgapTbResourceConfig config : tbResource) {
            tableName = config.getTableName();
        }
        try {
            List<Map<String, Object>> resultList =
                    tDgapDwProvider.getDatas(tableName, start, finish, fields, condition);
            ret.setSuccess(true);
            List<RowData> rows = new ArrayList<RowData>();
            List<FieldData> fieldDatas = new ArrayList<FieldData>();
            RowData rowData = new RowData();
            for (Map<String, Object> record : resultList) {
                for (String field : fields) {
                    FieldData fd = new FieldData();
                    fd.setColumnName(field);
                    if (record.get(field) == null) {
                        fd.setValue("");
                    } else {
                        fd.setValue(record.get(field).toString());
                    }
                    fieldDatas.add(fd);
                }
                rowData.setFieldsData(fieldDatas);
                rowData.setId(UUIDUtil.getUUID());
            }
            rows.add(rowData);
            ret.setData(rows);
        } catch (Exception e) {
            ret.setSuccess(false);
            ret.setErrorCode(Constants.WSErrorType.DATA_ERROR.getErrorCode());
            ret.setErrorDesc(Constants.WSErrorType.DATA_ERROR.getErrorOutDesc());
        }
        return ret;
    }

    @Override
    public String fixAppliedData(String dwResourceId) {
        List<TDgapTbResourceConfig> byResourceId = tDgapTbResourceConfigProvider.getByResourceId(dwResourceId);
        String tableName = byResourceId.get(0).getTableName();
        String preResourceId = dataImportExpandMapper.getPreResourceId(tableName).getResourceId();
        List<Map<String, Object>> allDatas = tDgapPreProvider.getAllDatas(tableName); //已按导入时间降序
        List<OpretaRowData> rows = new ArrayList<OpretaRowData>();
        Set<String> dataIds = new HashSet<>();
        Map<String,Map<String,Object>> existedData = tDgapDwProvider.getExistedData(tableName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String, Object> record : allDatas) {
            OpretaRowData rowData = new OpretaRowData();

            String id = (String) record.get("DATAID");
            if (id.equals("") || id.equalsIgnoreCase("NULL") || id == null) {
                return "该资源为文件导入，无需数据应用";
            }
            if(dataIds.contains(id)){  // 相同的dataId不再导入
                continue;
            }

            //该dataId已经应用过，比较应用数据与当前记录的操作时间，如果当前记录时间晚于已应用数据，说明有效
            if(existedData.containsKey(id)){
                Map currMap = existedData.get(id);
                if(!judgeCurrRec(currMap,record,sdf)){
                    continue;
                }
            }

            dataIds.add(id);
            List<FieldData> fieldDatas = new ArrayList<FieldData>();
            for (Map.Entry<String, Object> entry : record.entrySet()) {
                String key = entry.getKey();
                Object fv = entry.getValue();

                FieldData fd = new FieldData();

                fd.setColumnName(key);
                if (fv != null) {
                    String value = fv instanceof byte[] ? bs.encodeBase64String((byte[]) fv) : fv.toString();
                    fd.setValue(value);
                }
                fieldDatas.add(fd);
            }
            rowData.setFieldsData(fieldDatas);
            rowData.setOperata((String)record.get("OPREATE"));

            rows.add(rowData);
        }
        String token = "fix";
        if (rows != null && rows.size()>0) {
            try {
                for (OpretaRowData data : rows) {
                    String opreta = data.getOperata();
                    if (opreta.equals("addData") || opreta.equals("addBatch") || opreta.equals("addDataLog")) {
                        //去除id,token等pre数据库字段
                        removeSystemFields(data);
                        dataImportApplyProvider.applyDataAdd(token, preResourceId, data);
                    } else if (opreta.equals("updateData")) {
                        removeSystemFields(data);
                        dataImportApplyProvider.applyDataUpsert(token, preResourceId, data);
                    } else {
                        removeSystemFields(data);
                        dataImportApplyProvider.applyDataDelete(token, preResourceId, data);
                    }
                }
                return "数据应用导入仓库库成功";
            } catch (Exception e) {
                e.printStackTrace();
                return "数据应用导入仓库库失败";
            }
        } else {
            return "数据一致，无需数据应用导入";
        }
    }

    private boolean judgeCurrRec(Map<String,Object> existedData,Map<String, Object> currRec, SimpleDateFormat sdf){
        boolean result = true; //默认有效
        try {
            String existed_updateTime = existedData.get("UPDATE_TIME").toString();
            Date existed = sdf.parse(existed_updateTime);
            String this_updateTime = currRec.get("UPDATE_TIME").toString();
            Date curr = sdf.parse(this_updateTime);
            result =  curr.after(existed);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 去除id,token等pre数据库字段
     */
    private void removeSystemFields(OpretaRowData data) {
        List<FieldData> fieldDatas = data.getFieldsData();
        Iterator<FieldData> it = fieldDatas.iterator();
        while (it.hasNext()) {
            FieldData field = it.next();
            String key = field.getColumnName();
            if (key.equalsIgnoreCase("ID") || key.equalsIgnoreCase("STATUS")
                    || key.equalsIgnoreCase("OPREATE")
                    || key.equalsIgnoreCase("DATA_IMPORT_TIME")
                    || key.equalsIgnoreCase("TOKEN") || key.equalsIgnoreCase("MARK")) {
                it.remove();
            } else if (key.equalsIgnoreCase("DATAID")) {
                Object fv = field.getValue();
                data.setId(fv.toString());
                it.remove();
            }
        }
    }
}
