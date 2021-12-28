package com.sofn.ws.client;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;
import com.sofn.provider.dgap.DataImportApplyProvider;
import com.sofn.provider.dgap.DataImportProvider;
import com.sofn.wsclient.mdata.MdataGetter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Created by helong on 16-12-27. TODO WEIQIANG */
@Component
public class RowDataProcessorImpl implements MdataGetter.RowDataProcessor {

  @Value("${dataImport.applydataRealtime.enable}")
  String applydataRealtime = "false";

  @DubboReference(timeout = 100*5*1000)
  private DataImportApplyProvider dataImportApplyProvider;

  @DubboReference(timeout = 100*5*1000)
  private DataImportProvider dataImportProvider;

  private static final String TOKEN="mdata";

  @Override
  public boolean logData(String resourceId, Op op, RowData data) {
    Boolean b = false;
    if (op == null) {
      throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
    } else {
      if (op.equals(Op.添加)) {
        DataImportResult dataImportResult = dataImportProvider.addData(TOKEN,resourceId, data);
        if("true".equals(applydataRealtime)){
          try {
            dataImportApplyProvider.applyDataAdd(TOKEN,resourceId, data);
          } catch (DataImportApplyExeption dataImportApplyExeption) {
            dataImportApplyExeption.printStackTrace();
          }
        }
        if (dataImportResult != null) {
          b = true;
        }
      }
      if (op.equals(Op.修改)) {
        DataImportResult dataImportResult = dataImportProvider.updateData(TOKEN,resourceId, data);
        if("true".equals(applydataRealtime)){
          try {
            dataImportApplyProvider.applyDataUpdate(TOKEN,resourceId, data);
          } catch (DataImportApplyExeption dataImportApplyExeption) {
            dataImportApplyExeption.printStackTrace();
          }
        }
        if (dataImportResult != null) {
          b = true;
        }
      }
      if (op.equals(Op.删除)) {
        DataImportResult dataImportResult = dataImportProvider.deleteData(TOKEN,resourceId, data);
        if("true".equals(applydataRealtime)){
          try {
            dataImportApplyProvider.applyDataDelete(TOKEN, resourceId, data);
          } catch (DataImportApplyExeption dataImportApplyExeption) {
            dataImportApplyExeption.printStackTrace();
          }
        }
        if (dataImportResult != null) {
          b = true;
        }
      }
    }
    return b;
  }

  /*@Override
  public boolean applyData(String resourceId, Op op, RowData data) {
      return false;
  }*/
}
