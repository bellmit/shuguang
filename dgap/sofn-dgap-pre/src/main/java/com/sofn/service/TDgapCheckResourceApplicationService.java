package com.sofn.service;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.provider.dgap.TDgapCheckResourceRuleProvider;
import com.sofn.provider.dgap.TDgapResourceApplicationProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * author tianjie
 *
 * @date 2016/10/17
 */
@Service
public class TDgapCheckResourceApplicationService {

    private static String PRE_USER_CHAR="user_cache_";
    private static int TIME_OUT = 60*60;

    @DubboReference
    TDgapCheckResourceRuleProvider tDgapCheckResourceRuleProvider;

    @DubboReference
    TDgapResourceApplicationProvider resourceApplicationProvider;


    @Deprecated
    public void checkResourceApplication(String resource,String userId,String authCode) throws Exception {
        String redisKey = PRE_USER_CHAR+userId+authCode;
        if(RedisUtil.get(redisKey)==null){
           this.tDgapCheckResourceRuleProvider.checkResouce(resource,
                   authCode,userId);
            RedisUtil.expire(redisKey,TIME_OUT);
            RedisUtil.set(redisKey,1);
        }

    }

    public Map<String,Object> getTdgapResourceApplication(String authCode){
        return this.tDgapCheckResourceRuleProvider.getTDgapResourceApplication(authCode);
    }


    public List<TDgapResourceApplication> queryResourceExportApplicationList() {
        return resourceApplicationProvider.getByResourceType(Constants.ResourceType.仓库资源.getCode());
    }
}
