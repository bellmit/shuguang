package com.sofn.ws.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/** Created by helong on 16-12-27. TODO WANGTAO */
public class ReceiveWebServiceWrapper {
  public static Client CLIENT = null;

  public ReceiveWebServiceWrapper() {}

  public ReceiveWebServiceWrapper(String wsdl) {
    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
    Client client = dcf.createClient(wsdl);
    CLIENT = client;
  }
  /*public static String getValue(String subscriptionId,String startDate,String endDate){
      ReceiveWebServiceImplService service = new ReceiveWebServiceImplService();
      ReceiveWebService port = service.getReceiveWebServiceImplPort();
      String valueBody = port.getValue(subscriptionId,startDate,endDate);
      return valueBody;
  };*/

  public static String getValue(String subscriptionId, String startDate, String endDate) {
    String method = "getNameAndAge";
    Object[] res = null;
    try {
      res = CLIENT.invoke(method, subscriptionId, startDate, endDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res[0].toString();
  }
}
