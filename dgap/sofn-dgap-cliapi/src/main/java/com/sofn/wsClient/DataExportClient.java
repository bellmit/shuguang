package com.sofn.wsClient;

import com.sofn.ws.DataExport;

/** Created by wangt on 2017/4/1. */
public class DataExportClient {
  public DataExport createExportClent(String wsdl) {
    return WsClient.createClent(wsdl, DataExport.class);
  }
}
