package com.sofn.wsClient;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/** Created by wangt on 2017/4/1. */
public class WsClient {
  public static <X> X createClent(String wsdl, Class<X> clazz) {
    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    factory.setServiceClass(clazz);
    factory.setAddress(wsdl);
    X client = (X) factory.create();
    return client;
  }
}
