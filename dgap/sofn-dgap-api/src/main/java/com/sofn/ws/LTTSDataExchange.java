package com.sofn.ws;

import com.sofn.ws.beans.TTSCustomer;

/**
 * Created by helong on 18-1-16.
 */
public interface LTTSDataExchange {

    boolean addCustomer(String token, String id, TTSCustomer subject);

    boolean deleteCustomer(String token, String id);

    boolean updateCustomer(String token, String id, TTSCustomer subject);

}
