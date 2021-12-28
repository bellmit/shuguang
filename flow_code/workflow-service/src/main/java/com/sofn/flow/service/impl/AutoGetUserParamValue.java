package com.sofn.flow.service.impl;

import com.sofn.flow.exception.SofnException;
import com.sofn.flow.service.AutoGetParamValueService;
import com.sofn.flow.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 自动获取用户的值
 *
 * @author heyongjie
 * @date 2020/5/29 9:57
 */
@Service
public class AutoGetUserParamValue implements AutoGetParamValueService {

    @Resource
    RedisUtil redisUtil;

    @Override
    public String getValueByParam(Object... params) {
        // 参数校验
        checkParam(params);
        String loginUserName = redisUtil.getLoginUserName();
        if (StringUtils.isBlank(loginUserName)) {
            throw new SofnException("用户未登录！");
        }
        // 流程定义ID
        String processDefId = params[0].toString();

        // 流程中的实例ID
        String activityId = params[1].toString();
        // 获取当前节点往后面执行的所有条件，就是直接连接在当前节点上的线条的条件
        List<String> condition = XmlUtil.getCondition(processDefId, activityId);
        if (CollectionUtils.isEmpty(condition)) {
            return null;
        }
        // 获取流程期望的角色值
        Set<String> expectValue = XmlUtil.getCondition(condition, AutoParamUtil.USER_ROLE_PARAM);
        if (CollectionUtils.isEmpty(expectValue)) {
            return null;
        }
        // 根据用户将用户的角色取出来
        List<String> userRoles = UserUtil.getUserRoles(loginUserName);
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new SofnException("当前用户没有任何角色");
        }

        // 如果用户在该流程中有多个角色，那么就只取出一个就好了！
        String userRole = "";
        for (String expectRole : expectValue) {
            if (userRoles.contains(expectRole)) {
                userRole = expectRole;
                break;
            }
        }
        if (StringUtils.isBlank(userRole)) {
            throw new SofnException("该用户没有权限启动流程！");
        }
        return userRole;
    }

    @Override
    public void checkParam(Object... params) throws SofnException {
        if (params == null || params.length < 2) {
            throw new SofnException("流程定义ID和节点ID必传");
        }
        if (params[0] == null || StringUtils.isBlank(params[0].toString())) {
            throw new SofnException("流程定义ID必传");
        }

        if (params[1] == null || StringUtils.isBlank(params[1].toString())) {
            throw new SofnException("activityID必传");
        }
    }


}
