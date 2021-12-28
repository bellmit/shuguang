package com.sofn.provider.dgap;


//import org.csource.common.MyException;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface TDgapCheckResourceRuleProvider {

    /**
     *
     * @param resource
     * @param authCode
     * @param userId
     */
    @Deprecated
    public void checkResouce(String resource,String authCode,String userId) throws Exception;

    public Map<String,Object> getTDgapResourceApplication(String authCode);

}