package com.sofn.ws;

import javax.jws.WebMethod;

/**
 * author tianjie
 *
 * @date 2016/10/21
 */
public interface HeartService {

    @WebMethod
    String heartbeatMessage();
}
