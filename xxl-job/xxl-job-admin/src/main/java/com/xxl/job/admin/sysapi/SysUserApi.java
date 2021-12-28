package com.xxl.job.admin.sysapi;

import com.xxl.job.admin.core.util.JacksonUtil;
import com.xxl.job.admin.sysapi.bean.Result;
import com.xxl.job.admin.sysapi.bean.SysUserForm;
import com.xxl.job.admin.sysapi.core.SendRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 发送Get请求
 *
 * @author heyongjie
 * @date 2019/12/11 17:31
 */
@Component
public class SysUserApi {

    @Autowired
    private SendRequestUtil sendRequestUtil;

    private final static String URL_INFO_URL = "/user/getUserInfoByIds";

    /**
     * 根据TOKEN 和用户ID 获取当前登录用户信息
     *
     * @param token  登录TOKEN
     * @param userId 用户信息
     * @return Result<SysUserForm>  用户信息
     */
    public SysUserForm getUserInfo(String token, String userId) {
        if (token != null && !"".equals(token) && userId != null && !"".equals(userId)) {
            String msg = sendRequestUtil.sendGet(URL_INFO_URL, "ids=" + userId, token);
            if(msg == null || "".equals(msg)){
                return null;
            }
            Result<List<SysUserForm>> result = JacksonUtil.readValue(msg,Result.class);
            if(result != null){
                List list =  result.getData();
                if(!CollectionUtils.isEmpty(list)){
                    LinkedHashMap<String,Object> userInfo = (LinkedHashMap<String, Object>) list.get(0);
                    if(!CollectionUtils.isEmpty(userInfo)){
                        SysUserForm sysUserForm = new SysUserForm();
                        sysUserForm.setId((String) userInfo.get("id"));
                        sysUserForm.setNickname((String) userInfo.get("nickname"));
                        sysUserForm.setUsername((String) userInfo.get("username"));
                        sysUserForm.setRoleCodes(new HashSet( (List) userInfo.get("roleCodes")));
                        //resourceList
                        sysUserForm.setResourceList(new HashSet( (List) userInfo.get("resourceList")));
                        sysUserForm.setSex((Integer) userInfo.get("sex"));
                        return sysUserForm;
                    }
                    return null;
                }
            }
        }
        return null;
    }


}
