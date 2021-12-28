package com.sofn.ws.client;

import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.wsclient.mdata.MdataGetter;
import com.sofn.wsclient.mdata.MdataWsClientConfig;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/** Created by helong on 16-12-21. TODO weiqiang */
@Component
public class MdataGetterImpl implements MdataGetter {
  @Override
  public void getMdataCodeSets(
      MdataWsClientConfig config, Date startDate, Date endDate, RowDataProcessor processor) {
    getData(
        config.getWsdlUrl(),
        config.getCodeSetsSubId(),
        config.getCodeSetResourceId(),
        startDate,
        endDate,
        processor);
  }

  @Override
  public void getMdataCodes(
      MdataWsClientConfig config, Date startDate, Date endDate, RowDataProcessor processor) {
    getData(
        config.getWsdlUrl(),
        config.getCodesSubId(),
        config.getCodesResourceId(),
        startDate,
        endDate,
        processor);
  }

  /**
   * @param wsdl
   * @param subscriptionId
   * @param resourceId
   * @param startDate
   * @param endDate
   * @param processor
   */
  void getData(
      String wsdl,
      String subscriptionId,
      String resourceId,
      Date startDate,
      Date endDate,
      RowDataProcessor processor) {
    // TODO new ReceiveWebServiceWrapper use @para wsdl
    ReceiveWebServiceWrapper clientWrapper = new ReceiveWebServiceWrapper(wsdl);

    // TODO convert Data to String
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String start = sdf.format(startDate);
    String end = sdf.format(endDate);
    String jsonStr = clientWrapper.getValue(subscriptionId, start, end);
    // TODO weiqiang parse jsonStr to List<data>
    RowData rowData = new RowData();
    List<FieldData> fieldsData = new ArrayList<>();
    RowDataProcessor.Op op = null;
    RowDataProcessor.Op tempOp;
    JSONObject jsonObject;
    // 解析json数组
    JSONArray jsonArray = new JSONArray(jsonStr);
    // 遍历数组
    if (jsonArray.length() > 0) {
      for (int i = 0; i < jsonArray.length() - 1; i++) {
        jsonObject = jsonArray.getJSONObject(i);
        Iterator iterator = jsonObject.keys();
        // 遍历key
        while (iterator.hasNext()) {
          // 获取每一个key
          String key = iterator.next().toString();
          tempOp = lookupOp(jsonObject, key, wsDataStausFieldName);
          if (tempOp != null) op = tempOp;
          // 提取数据封装对象
          FieldData fieldData = new FieldData();
          fieldData.setColumnName(key);
          fieldData.setValue(jsonObject.getString(key));
          fieldsData.add(fieldData);
        }
      }
      rowData.setFieldsData(fieldsData);
      rowData.setId(UUID.randomUUID().toString());
    }
    processor.logData(resourceId, op, rowData);
  }

  private RowDataProcessor.Op lookupOp(JSONObject object, String key, String keyName) {
    if (key.equals(keyName)) {
      try {
        String opStaus = object.getString(key);
        for (RowDataProcessor.Op op : RowDataProcessor.Op.values()) {
          if (opStaus.equals(op.getwsStr())) {
            return op;
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
