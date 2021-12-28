package com.sofn.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Administrator on 2016/9/27.
 */
@WebService(name = "dataOutport")
public interface SoapService extends HeartService {
    @WebMethod
    public String getMessage(String xml);

}
