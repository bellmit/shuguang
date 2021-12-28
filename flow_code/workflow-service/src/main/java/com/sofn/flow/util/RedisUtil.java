package com.sofn.flow.util;

import com.sofn.flow.vo.ProcessDefinitionAttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 缓存工具类
 *
 * @author heyongjie
 * @date 2020/5/8 18:08
 */
@Component
public class RedisUtil {

    /**
     * 用户ID
     */
    private static final String USER_ID = "userId";

    /**
     * 用户名称
     */
    private static final String USER_NAME = "userName";

    /**
     * 缓存流程定义属性
     */
    private static final String DEF_ATTR_KEY = "DEF_ATTR_DEF";

    @Resource
    private RedisHelper redisHelper;

    /**
     * 根据TOKEN获取用户信息
     *
     * @param token token信息
     * @return 当前登录用户ID
     */
    public String getLoginUserId(String token) {
        if (!StringUtils.isBlank(token)) {
            Object userId = redisHelper.hget(token, USER_ID);
            return userId==null?"":String.valueOf(userId);
        }
        return "";
    }

    /**
     * 获取当前登录用户name
     *
     * @return String
     */
    public String getLoginUserName() {
        String token = TokenUtil.getToken();
        if (!StringUtils.isBlank(token)) {
            Object obj = redisHelper.hget(token, USER_NAME);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
        return "";
    }

    /**
     * 将工作流的数据缓存起来，避免每次都解析XML
     * @param defId   流程定义ID
     * @param processDefinitionAttrVos   流程定义的属性
     */
    public void cacheProcessDefAttr(String defId, List<ProcessDefinitionAttrVo> processDefinitionAttrVos){
        if(CollectionUtils.isEmpty(processDefinitionAttrVos) || StringUtils.isBlank(defId)){
            return ;
        }

        redisHelper.hset(DEF_ATTR_KEY,defId, JsonUtils.obj2json(processDefinitionAttrVos));
    }

    /**
     * 获取流程定义的属性
     * @param proDefId  流程定义ID
     * @return    List<ProcessDefinitionAttrVo>
     */
    public List<ProcessDefinitionAttrVo> getProcessDefAttr(String proDefId){
        Object json = redisHelper.hget(DEF_ATTR_KEY, proDefId);
        List<ProcessDefinitionAttrVo> resultInfo = null;
        if (json != null) {
            resultInfo = JsonUtils.json2List(String.valueOf(json),ProcessDefinitionAttrVo.class);
        }

        return resultInfo;
    }


    /**
     * 移除缓存
     * @param proDefId  流程定义ID
     */
    public void removeProcessDefAttr(String proDefId){
        if(StringUtils.isBlank(proDefId)){
            return;
        }

        redisHelper.hdel(DEF_ATTR_KEY, proDefId);
    }


    public static void main(String[] args) {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzb2ZuIiwiZXhwIjoxNTg5MDc2MjIzLCJ0eXBlIjoiIiwiaWF0IjoxNTg4OTg5ODIzLCJ1c2VybmFtZSI6Imh5aiJ9.YNyvx78uyn3ozNUL05fRKIvibVZ40Y7MOe4xuZ7lLxU";

//        getLoginUserId(token);
//        List<ProcessDefinitionAttrVo> processDefinitionAttrVos = new ArrayList<ProcessDefinitionAttrVo>();
//        ProcessDefinitionAttrVo processDefinitionAttrVo = new ProcessDefinitionAttrVo();
//        processDefinitionAttrVo.setAttrId("123");
//        processDefinitionAttrVos.add(processDefinitionAttrVo);
//
//        cacheProcessDefAttr("100",processDefinitionAttrVos);
//        cacheProcessDefAttr("101",processDefinitionAttrVos);
//
//        List<ProcessDefinitionAttrVo> processDefAttr = getProcessDefAttr("100");
//        System.out.println(processDefAttr);
//
//        removeProcessDefAttr("100");
//        List<ProcessDefinitionAttrVo> processDefAttr2 = getProcessDefAttr("100");
//        System.out.println(processDefAttr2);
    }
}
