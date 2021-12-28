package com.sofn.wsService.impl;

import com.sofn.ws.LTTSDataExchange;
import com.sofn.ws.anno.AccessToken;
import com.sofn.ws.anno.WsLoggable;
import com.sofn.ws.beans.TTSCustomer;
import com.sofn.wsService.BaseWsService;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by helong on 18-1-16.
 */
@WebService(name = "LTTSDataExchange"/*,wsdlLocation = "http://10.0.50.167:17080/sofn-dgap-pre/ws/lttsApi?wsdl"*/)
@Component
@WSDLDocumentation("地方追溯平台对接接口")
public class LTTSDataExchangeImpl extends BaseWsService implements LTTSDataExchange {

    @Override
    @WebMethod
    @WsLoggable
    @WSDLDocumentation("新增追溯主体")
    public boolean addCustomer(@AccessToken @WebParam String token, @WebParam String id, @WebParam TTSCustomer subject) {
        return true;
    }

    @Override
    @WebMethod
    @WsLoggable
    @WSDLDocumentation("删除追溯主体")
    public boolean deleteCustomer(@AccessToken @WebParam String token, @WebParam String id) {
        return false;
    }

    @Override
    @WebMethod
    @WsLoggable
    @WSDLDocumentation("变更追溯主体")
    public boolean updateCustomer(@AccessToken @WebParam String token, @WebParam String id, @WebParam TTSCustomer subject) {
        return false;
    }

}
