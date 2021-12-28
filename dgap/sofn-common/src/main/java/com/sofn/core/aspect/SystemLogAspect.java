package com.sofn.core.aspect;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.constant.OperateLog;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by: dong4j.
 * Date: 2016-10-27.
 * Time: 12:45.
 * Description: 日志切入点
 */

@Aspect
@Component
public class SystemLogAspect {
    private final        ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
    //注入Service用于把日志保存数据库
    //@Autowired
    //private OperateLogService1 operateLogService;
    //本地操作日志记录对象
    private static final Logger            logger               = LoggerFactory.getLogger(SystemLogAspect.class);

    //Controller层切点
    @Pointcut("@annotation(com.sofn.core.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    //@Before("controllerAspect()")
    //public void doBefore(JoinPoint joinPoint) {
    //
    //}

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) {
        String   targetName  = joinPoint.getTarget().getClass().getName();
        String   methodName  = joinPoint.getSignature().getName();
        Object[] arguments   = joinPoint.getArgs();
        Class    targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods     = targetClass.getMethods();
        String   description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodOperation(JoinPoint joinPoint) {
        String   targetName  = joinPoint.getTarget().getClass().getName();
        String   methodName  = joinPoint.getSignature().getName();
        Object[] arguments   = joinPoint.getArgs();
        Class    targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods     = targetClass.getMethods();
        String   operationType = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    operationType = method.getAnnotation(SystemControllerLog.class).operationType();
                    break;
                }
            }
        }
        return operationType;
    }


    /////////////////////////////////////////////////////////////////////

    /**
     * 在接口的每一个实现类的每一个方法开始之前执行一段代码
     */
    //@Before("execution(public int com.yl.spring.aop.ArithmeticCalculator.*(..))")
    //public void beforeMethod(JoinPoint joinPoint) {
    //    String methodName = joinPoint.getSignature().getName();
    //    Object[] args = joinPoint.getArgs();
    //    System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
    //}

    /**
     * 在接口的每一个实现类的每一个方法执行之后执行一段代码
     * 无论该方法是否出现异常
     */
    //@After("execution(public int com.yl.spring.aop.ArithmeticCalculator.*(..))")
    //public void afterMethod(JoinPoint joinPoint) {
    //    String methodName = joinPoint.getSignature().getName();
    //    Object[] args = joinPoint.getArgs();
    //    System.out.println("The method " + methodName + " ends with " + Arrays.asList(args));
    //}

    /**
     * 方法正常结束后执行的代码
     * 返回通知是可以访问到方法的返回值的
     */
    //@AfterReturning(value="execution(public int com.yl.spring.aop.ArithmeticCalculator.*(..))", returning="result")
    //public void afterReturning(JoinPoint joinPoint, Object result) {
    //    String methodName = joinPoint.getSignature().getName();
    //    System.out.println("The method " + methodName + " return with " + result);
    //}

    /**
     * 在方法出现异常时会执行的代码
     * 可以访问到异常对象，可以指定在出现特定异常时在执行通知代码
     */
    //@AfterThrowing(value="execution(public int com.yl.spring.aop.ArithmeticCalculator.*(..))", throwing="ex")
    //public void afterThrowing(JoinPoint joinPoint, Exception ex) {
    //    String methodName = joinPoint.getSignature().getName();
    //    System.out.println("The method " + methodName + " occurs exception: " + ex);
    //}

    /**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
     * 而且环绕通知必须有返回值，返回值即为目标方法的返回值
     */
    @Around("controllerAspect()")
    public Object aroundMethod(ProceedingJoinPoint pjd){
        startTimeThreadLocal.set(System.currentTimeMillis());
        Object result     = null;
        String methodName = pjd.getSignature().getName();
        logger.debug("前置通知");
        //执行目标方法
        //try {
        //    //前置通知
        //    System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
        //    result = pjd.proceed();
        //    //返回通知
        //    System.out.println("The method " + methodName + " ends with " + Arrays.asList(pjd.getArgs()));
        //} catch (Throwable e) {
        //    //异常通知
        //    System.out.println("The method " + methodName + " occurs expection : " + e);
        //    throw new RuntimeException(e);
        //}

        logger.debug("日志记录");
        HttpServletRequest  request  = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpSession         session  = request.getSession();
        //读取session中的用户
        //User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        //请求的IP
        String ip = getIp(request);
        // 请求头 token
        String uniqueToken = request.getHeader("token");
        if (org.springframework.util.StringUtils.isEmpty(uniqueToken)) {
            uniqueToken = request.getParameter("token");
        }
        logger.debug("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
        //*========控制台输出=========*//
        logger.debug("=====前置通知开始=====");
        logger.debug("请求方法:" + (pjd.getTarget().getClass().getName() + "." + pjd.getSignature().getName() + "()"));
        //logger.debug("请求人:" + user.getName());
        logger.debug("请求IP:" + ip);
        //*========数据库日志=========*//
        OperateLog operateLog = new OperateLog();
        // 操作说明
        logger.debug("方法描述:" + getControllerMethodDescription(pjd));
        operateLog.setOperateDescribe(getControllerMethodDescription(pjd));
        //operateLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
        operateLog.setId(StringUtils.getUUIDString());
        operateLog.setOperateUserid(WebUtil.getCurrentUserId());
        operateLog.setOperateUsername(WebUtil.getCurrentUserName(uniqueToken));
        operateLog.setCreateBy(WebUtil.getCurrentUserId());
        operateLog.setCreateTime(new Date());
        operateLog.setUpdateBy(WebUtil.getCurrentUserId());
        operateLog.setUpdateTime(new Date());
        operateLog.setOperateIp(ip);
        operateLog.setUserToken(uniqueToken);
        operateLog.setVisitUrl(request.getRequestURI());
        operateLog.setOperateTime(new Date());
        operateLog.setVisitParam(Arrays.asList(pjd.getArgs()).toString());
        operateLog.setDelFlag("N");
        //  todo 操作类型 正则匹配
        operateLog.setOperateType(getControllerMethodOperation(pjd));
        String status   = "Y";
        String failInfo = "";
        try {
            result = pjd.proceed();
            operateLog.setStatus(status);
            operateLog.setFailInfo(failInfo);
            logger.debug("{}",operateLog);
            // 添加日志
            saveOperateLog(operateLog, System.currentTimeMillis());
        } catch (Throwable throwable) {
            System.out.println("The method " + methodName + " occurs expection : " + throwable);
            logger.error("异常信息:{}", throwable.getMessage());
            operateLog.setStatus("N");
            operateLog.setFailInfo(throwable.getMessage());
            // 添加日志
            saveOperateLog(operateLog, System.currentTimeMillis());
            throw new RuntimeException(throwable);
        }
        return result;
    }

    // 存入数据库
    private void saveOperateLog(OperateLog operateLog, Long startTime) {
        RedisUtil.set(WebUtil.generateLogKey(operateLog.getId()), operateLog, 864000);
        //operateLogService.saveOperateLog(operateLog, startTime);
        if (operateLog != null && null != operateLog.getOperateDescribe() && !"".equals(operateLog.getOperateDescribe())) {
            if (("(共享系统)分页显示资源目录").equals(operateLog.getOperateDescribe())
                    || ("(共享系统)资源目录新增").equals(operateLog.getOperateDescribe())
                    || ("(共享系统)修改资源目录").equals(operateLog.getOperateDescribe())
                    || ("(共享系统)删除资源目录").equals(operateLog.getOperateDescribe())
                    || ("(共享系统)批量删除资源目录").equals(operateLog.getOperateDescribe())) {
                RedisUtil.set("operateDataLog&" + operateLog.getId(), operateLog, 386400000);
            }
        }
    }

    /**
     * 获取真是IP
     * @param request
     * @return
     */
    public  String getIpAddr(HttpServletRequest request)  {
        String ip  =  request.getHeader( "x-forwarded-for" );
        if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
            ip  =  request.getHeader( "Proxy-Client-IP" );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
            ip  =  request.getHeader( "WL-Proxy-Client-IP" );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
            ip  =  request.getRemoteAddr();
        }
        //对多级代理的情况进行处理
        if(ip.length() > 15){
            String[] ipA = ip.split(",");
            for(String i : ipA){
                if(!"unknown".equals(i)){
                    ip = i ;
                    break ;
                }
            }
        }
        return  ip;
    }

    /**
     * 获取真实IP(对应多次反向代理)
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
