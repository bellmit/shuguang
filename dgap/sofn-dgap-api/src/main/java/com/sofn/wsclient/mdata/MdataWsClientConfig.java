package com.sofn.wsclient.mdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * Created by helong on 16-12-21.
 */
@Component
public class MdataWsClientConfig {

    @Value("${mdata.ws.client.wsdlurl}")
    private String wsdlUrl;

    @Value("${mdata.ws.client.codesets.subid}")
    private String codeSetsSubId;

    @Value("${mdata.ws.client.codes.subid}")
    private String codesSubId;

    @Value("${mdata.ws.client.codesets.resid}")
    private String codeSetResourceId;

    @Value("${mdata.ws.client.codes.resid}")
    private String codesResourceId;

    /**
     * ws服务wsdl的url地址
     */
    public String getWsdlUrl() {
        return wsdlUrl;
    }

    public void setWsdlUrl(String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    /**
     * 代码集数据的订阅id
     */
    public String getCodeSetsSubId() {
        return codeSetsSubId;
    }

    public void setCodeSetsSubId(String codeSetsSubId) {
        this.codeSetsSubId = codeSetsSubId;
    }

    /**
     * 代码数据的订阅id
     */
    public String getCodesSubId() {
        return codesSubId;
    }

    public void setCodesSubId(String codesSubId) {
        this.codesSubId = codesSubId;
    }

    /**
     *
     */
    public String getCodeSetResourceId() {
        return codeSetResourceId;
    }

    public void setCodeSetResourceId(String codeSetResourceId) {
        this.codeSetResourceId = codeSetResourceId;
    }

    /**
     *
     */
    public String getCodesResourceId() {
        return codesResourceId;
    }

    public void setCodesResourceId(String codesResourceId) {
        this.codesResourceId = codesResourceId;
    }

}

