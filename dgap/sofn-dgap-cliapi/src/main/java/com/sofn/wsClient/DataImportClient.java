package com.sofn.wsClient;

import com.sofn.ws.DataImport;

/** Created by wangt on 2017/3/30. */
public class DataImportClient {
  public DataImport createImportClient(String wsdl) {
    return WsClient.createClent(wsdl, DataImport.class);
  }
}
