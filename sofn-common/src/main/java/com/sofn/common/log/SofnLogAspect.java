package com.sofn.common.log;

import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.utils.WebUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 日志切面
 */
@Aspect
@Component
public class SofnLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogName.OPERATE.getLogName());

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Pointcut("@annotation(sofnLog)")
    public void systemLogPointcut(SofnLog sofnLog) {
    }

    @Around("systemLogPointcut(sofnLog)")
    public Object aroundMethod(ProceedingJoinPoint point, SofnLog sofnLog) throws Throwable {
        long time = System.currentTimeMillis();
        try {
            Object returns = point.proceed();
            save(point, returns, sofnLog, System.currentTimeMillis() - time);
            return returns;
        } catch (Throwable e) {
            save(point, e, sofnLog, System.currentTimeMillis() - time);
            throw e;
        }
    }


    private void save(ProceedingJoinPoint point, Object returns, SofnLog sofnLog, Long time) {
        String sign = point.getSignature().toString();

        // 获取相关参数
        WebUtil wu = WebUtil.getInstance();
        // 请求对象
        HttpServletRequest req = wu.getRequest();
        // 当前用户
        String userName = "";
        if (StringUtils.isNotBlank(UserUtil.getLoginUserName())){
          userName = UserUtil.getLoginUserName();
        }
        // 查询参数
        String qs = filterArgs(point.getArgs());
        // url
        String url = req.getRequestURL().append("?" + qs).toString();
        // IP地址
        String ip = wu.getIpAddress();
        // 方法
        String method = req.getMethod();
        // 协议
        String protocol = req.getProtocol();
        int code = 0;
        String msg = null;
        String desc = !StringUtils.isEmpty(sofnLog.value()) ? sofnLog.value() : "";
        String type = !StringUtils.isEmpty(sofnLog.type()) ? sofnLog.type() : "";
        String tag = !StringUtils.isEmpty(sofnLog.tag()) ? sofnLog.tag() : "";

        String text = "";
        if (sofnLog.returnData()){
          if (returns instanceof Result) {
            Result result = (Result) returns;
            code = result.getCode();
            msg = result.getMsg();
          }

          if (returns != null) {
            Object tmp;
            if (returns instanceof CharSequence) {
              tmp = returns.toString();
            } else if (returns instanceof Throwable) {
              String m = ((Throwable) returns).getMessage();
              String n = returns.getClass().getSimpleName();
              tmp = m == null ? n : n + ": " + m;
            } else {
              tmp = returns;
            }

            text = JsonUtils.obj2json(tmp);;
          }
        }

        // 构造入库参数
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        // 用户信息
        log.setUsername(userName);
        // 请求信息
        log.setIp(ip);
        log.setReqMethod(method + " " + protocol);
        log.setReqUri(url);
        // 请求参数
        log.setArgs(qs);
        // 执行信息
        log.setExecMethod(sign);
        log.setExecTime(time);

        log.setStatus(String.valueOf(code));
        log.setMsg(msg);
        log.setExecDesc(desc);
//        log.setLogValue(desc);
        log.setType(type);
//        log.setLogTag(tag);
        Date date = new Date();
        log.setCreateTime(date);
        // 响应信息
        log.setReturnVal(text);
        try {
            // 打印日志
            logger.info(JsonUtils.obj2json(log));
            // 入库
            applicationEventPublisher.publishEvent(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤掉会导致json转换失败的参数
     */
    private String filterArgs(Object[] args){
      Stream<Object> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Stream.of(args);
      List<Object> argsList = stream
        .filter(arg -> (!(arg instanceof HttpServletRequest)
          && !(arg instanceof HttpServletResponse)
          && !(arg instanceof BindingResult)
          && !(arg instanceof MultipartFile)
        ))
        .collect(Collectors.toList());
      //过滤后序列化无异常
      try{
        return JsonUtils.obj2json(argsList);
      }catch (Exception e){
        return "";
      }
    }
}
