package com.sofn.flow.util;

import com.sofn.flow.exception.SofnException;
import com.sofn.flow.vo.SysParamVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动获取参数工具类
 * 在流程定义的时候，我们内置一些值，当参数有这些值的时候就直接初始化进去
 *
 * @author heyongjie
 * @date 2020/5/28 17:01
 */
@Component
public class AutoParamUtil {

    /**
     * 系统内置参数信息
     */
    private final static List<SysParamVo> SYS_PARAM_VOS = new ArrayList<>();

    /**
     * 用户角色参数
     */
    public final static String USER_ROLE_PARAM = "applicantRole";

    /**
     * 是否通过审批
     */
    public final static String IS_APPROVED = "isapproved";

    /**
     * 报表参数
     */
    public final static String REPORT_VALUE ="selectReport";

    @Autowired
    private ApplicationContext applicationContext;

    static {
        // 系统内置可动态获取用户角色
        SYS_PARAM_VOS.add(
                new SysParamVo().buildParamName(USER_ROLE_PARAM)
                        .buildIsShow(false)
                        .buildParamDescribe("用户角色信息，后台自动获取值，如果一个节点之后有二个角色所对应不同分支，用户又有这二个角色，那么随机取出一个作为该流程的角色提起流程！")
                        .buildParamGetValueFunction("com.sofn.flow.service.impl.AutoGetUserParamValue.getValueByParam")
        );
        // 系统内置审批信息，用于界面下拉框选择
        SYS_PARAM_VOS.add(
                new SysParamVo().buildParamName(IS_APPROVED)
                        .buildIsShow(true)
                        .buildParamDescribe("是否通过审批，由用户提交流程时选择，是：表示通过审批，否：表示未通过审批")
                        .buildValus("是","否")
        );

        // 报表
        SYS_PARAM_VOS.add(
                new SysParamVo().buildParamName(REPORT_VALUE)
                        .buildIsShow(true)
                        .buildParamDescribe("选择报表，如果启动流程是报表，流程启动的时候会让选择报表，流程过程中会出现报表预览按钮")
        );
    }

    /**
     * 通过参数直接调用方法然后获取值
     *
     * @param params        参数
     * @param functionParam 参数值
     * @return 参数对应的值
     */
    public Object getValueByParam(String params, Object... functionParam) {
        if (!contains(params)) {
            return null;
        }
        SysParamVo sysParamVo = getSysParamVo(params);
        if(sysParamVo == null){
            return null;
        }
        String methodPath = sysParamVo.getParamGetValueFunction();
        if (StringUtils.isBlank(methodPath)) {
            throw new SofnException(String.format("参数%s无法获取值", params));
        }
        try {
            String classPath = methodPath.substring(0, methodPath.lastIndexOf("."));
            String method = methodPath.substring(methodPath.lastIndexOf(".") + 1);
            Class<?> aClass = Class.forName(classPath);
            Object obj = applicationContext.getBean(aClass);
            Method method1 = null;
            Method[] methods = aClass.getMethods();
            for (Method method2 : methods) {
                if (method2.getName().equals(method)) {
                    method1 = method2;
                    break;
                }
            }
            if (method1 == null) {
                throw new SofnException(String.format("在类【%s】中没有找到方法【%s】", classPath, method));
            }

            Object resultValue;
            if (functionParam.length > 0) {
                // 这里注意将参数转为Object，不然会报错，因为参数类型和实际的接口上的参数有可能不匹配
                resultValue = method1.invoke(obj, (Object) functionParam);
            } else {
                resultValue = method1.invoke(obj);
            }
            if (resultValue != null) {
                return resultValue;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(String.format("参数%s自动获取值失败!", params));
        }
    }

    /**
     * 根据参数名称获取参数信息
     * @param param  参数名称
     * @return  参数配置信息
     */
    public static SysParamVo getSysParamVo(String param){
        for (SysParamVo sysParamVo : SYS_PARAM_VOS) {
            if(sysParamVo.getParamName().equals(param)){
                return sysParamVo;
            }
        }
        return null;
    }

    /**
     * 判断是否含有参数
     *
     * @param param 参数
     * @return true 有 false没有
     */
    public static boolean contains(String param) {
        for (SysParamVo sysParamVo : SYS_PARAM_VOS) {
            if(sysParamVo.getParamName().equals(param)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取系统配置的参数
     * @return     List<SysParamVo>
     */
    public static List<SysParamVo> getSysParamVos(){
        return SYS_PARAM_VOS;
    }


    public static void main(String[] args) {

        TokenUtil.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzb2ZuIiwiZXhwIjoxNTkwODA1OTQyLCJ0eXBlIjoiIiwiaWF0IjoxNTkwNzE5NTQyLCJ1c2VybmFtZSI6ImZsb3dfdGVzdF91c2VyMSJ9.7EhAH9QibCE9vH45KM_ZOymGUVpPBKNdrqBY_chsGkM");

//        Object flow_test_act4 = getValueByParam(USER_ROLE_PARAM, "flow_test:flow_test", "flow_test_Act4");
//
//        AutoGetUserParamValue autoGetParamValueService = new AutoGetUserParamValue();
//        String flow_test_act4 = autoGetParamValueService.getValueByParam("flow_test:flow_test", "flow_test_Act4");
//        System.out.println(flow_test_act4.toString());

        TokenUtil.remove();

    }
}
