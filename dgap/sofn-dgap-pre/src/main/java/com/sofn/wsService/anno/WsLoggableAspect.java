package com.sofn.wsService.anno;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.ErrorSupport;
//import com.sofn.model.generator.SysUser;
import com.sofn.model.generator.TDgapWsErrorLog;
import com.sofn.model.generator.TDgapWsLog;
import com.sofn.provider.dgap.TDgapWsDailyStatProvider;
//import com.sofn.provider.sys.SysUserProvider;
import com.sofn.service.TDgapCheckResourceApplicationService;
import com.sofn.service.TDgapWsLogService;
import com.sofn.ws.anno.AccessToken;
import com.sofn.ws.anno.WsLoggable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

@Component
@Aspect
public class WsLoggableAspect {

    private static final Logger logger = LoggerFactory.getLogger(WsLoggableAspect.class.getName());

//    private SysUserProvider sysUserProvider;
    private TDgapWsDailyStatProvider tDgapWsDailyStatProvider;

    @DubboReference
    public void setWsDailyStatProvider(TDgapWsDailyStatProvider tDgapWsDailyStatProvider) {
        this.tDgapWsDailyStatProvider = tDgapWsDailyStatProvider;
    }

    @Autowired
    private TDgapWsLogService tDgapWsLogService;

    @Autowired
    private TDgapCheckResourceApplicationService tDgapCheckResourceApplicationService;

    @Around("@annotation(com.sofn.ws.anno.WsLoggable) && @annotation(wsLoggable)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, WsLoggable wsLoggable) {
        TDgapWsErrorLog erroLog = null;
        TDgapWsLog wsLog = null;
        ErrorSupport ret = null;

        Map<String, Object> map;
        String resourceId = null;
        String resourceDirId = null;
        String userId = null;
        String userName = null;
        String methodName = null;
        String errorType = null;
        String accessToken = null;
        AccessToken accessTokenAnno = null;

        try {

            Signature signature = pjp.getSignature();
            methodName = signature.getName();

            Object[] args =  pjp.getArgs();

            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();


            for (int i = 0; i < parameterAnnotations.length; i++) {
                if (parameterAnnotations[i].length > 0){
                    accessTokenAnno = (AccessToken) parameterAnnotations[i][0];
                    accessToken = (String) args[i];
                }
            }

            logger.info("methodName:" + methodName);

            logger.info("@AccessToken accessToken:" + accessToken);
            if (accessTokenAnno != null) {
                logger.info("@AccessToken description:" + accessTokenAnno.description());
            }

            logger.info("@WsLoggable description:" + wsLoggable.description());

            map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(accessToken);
            if (map != null) {
                //授权码正确
                resourceId = (String) map.get("WS_ID");
                resourceDirId = (String) map.get("DIRECTORY_ID");
                userId = (String) map.get("USER_ID");

                userName = null==map.get("USER_NAME")?"defaultUser":(String)map.get("USER_NAME");
//                SysUser sysUser = sysUserProvider.queryById(userId);
//                logger.debug("user:" + sysUser);
//                if (sysUser != null) {
//                    userName = sysUser.getUserName();
//                }

                wsLog = createWsLog(resourceDirId, resourceId, userId, userName, methodName);
                try {
                    ret = (ErrorSupport) pjp.proceed(args);
                } catch (Throwable throwable) {
                    logger.error("", throwable);
                    erroLog = createTDgapWsErrorLog(resourceDirId, resourceId, userId, userName, methodName,
                            Constants.WSErrorType.SYSTEM_ERR.getErrorCode(),
                            Constants.WSErrorType.SYSTEM_ERR.getErrorInnerDesc());

                    errorType = setErrorType(ret, Constants.WSErrorType.SYSTEM_ERR);
                }
            } else {
                //授权码错误
                wsLog = createWsLog(resourceDirId, resourceId, userId, userName, methodName);

                erroLog = createTDgapWsErrorLog(resourceDirId, resourceId, userId, userName, methodName,
                        Constants.WSErrorType.AUTH_ERROR.getErrorCode(),
                        Constants.WSErrorType.AUTH_ERROR.getErrorInnerDesc());

                errorType = setErrorType(ret, Constants.WSErrorType.AUTH_ERROR);
            }

            if (!ret.isSuccess()) {
                System.out.println(ret.getErrorCode() + "++++++++++++++++++++++");
                if ("100000005".equals(ret.getErrorCode())) {
                    erroLog = createTDgapWsErrorLog(resourceDirId, resourceId, userId, userName, methodName,
                            Constants.WSErrorType.DATA_ERROR.getErrorCode(),
                            Constants.WSErrorType.DATA_ERROR.getErrorInnerDesc());

                    errorType = setErrorType(ret, Constants.WSErrorType.DATA_ERROR);
                }
                if ("100000006".equals(ret.getErrorCode())) {
                    erroLog = createTDgapWsErrorLog(resourceDirId, resourceId, userId, userName, methodName,
                            Constants.WSErrorType.DATALEN_ERROR.getErrorCode(),
                            Constants.WSErrorType.DATALEN_ERROR.getErrorInnerDesc());

                    errorType = setErrorType(ret, Constants.WSErrorType.DATALEN_ERROR);
                }
                if ("100000000".equals(ret.getErrorCode())) {
                    erroLog = createTDgapWsErrorLog(resourceDirId, resourceId, userId, userName, methodName,
                            Constants.WSErrorType.SYSTEM_ERR.getErrorCode(),
                            Constants.WSErrorType.SYSTEM_ERR.getErrorInnerDesc());

                    errorType = setErrorType(ret, Constants.WSErrorType.SYSTEM_ERR);
                }
            }
            return ret;
        } catch (Exception e) {
            logger.error("", e);
            wsLog = createWsLog(resourceDirId, resourceId, userId, userName, methodName);
            erroLog = createTDgapWsErrorLog(resourceDirId, resourceId, userId, userName, methodName,
                    Constants.WSErrorType.SYSTEM_ERR.getErrorCode(),
                    Constants.WSErrorType.SYSTEM_ERR.getErrorInnerDesc());
            setErrorType(ret, Constants.WSErrorType.SYSTEM_ERR);
            return ret;
        } finally {
            try {
                wsLog.setInvokeEndDate(new Date());
                wsLog.setUpdateTime(new Date());
                wsLog.setUpdateBy(wsLog.getCreateBy());
                if (null == erroLog) {
                    wsLog.setErrorType("");
                } else {
                    wsLog.setErrorType(errorType);
                }
                wsLog.setErrorType(errorType);
                tDgapWsLogService.insertWsLog(wsLog);

                if (erroLog != null) {
                    erroLog.setUpdateTime(new Date());
                    erroLog.setUpdateBy(erroLog.getCreateBy());
                    tDgapWsLogService.insertWsErrLog(erroLog);
                }

                tDgapWsDailyStatProvider.stateDailyLog(new Date());
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }


    private String setErrorType(ErrorSupport ret, Constants.WSErrorType errorType) {
        ret.setSuccess(false);
        ret.setErrorCode(errorType.getErrorCode());
        ret.setErrorDesc(errorType.getErrorInnerDesc());
        return ret.getErrorDesc();
    }

    private TDgapWsLog createWsLog(String resourceDirId, String resourceId, String userId, String userName, String methodName) {
        TDgapWsLog tDgapWsLog = new TDgapWsLog();
        tDgapWsLog.setResourceDir(resourceDirId);
        tDgapWsLog.setWebServiceId(resourceId);
        tDgapWsLog.setCallerUser(userName);
        tDgapWsLog.setMethodName(methodName);

        tDgapWsLog.setInvokeStartDate(new Date());
        tDgapWsLog.setCreateTime(new Date());
        tDgapWsLog.setCreateBy(userId);
        return tDgapWsLog;
    }

    private TDgapWsErrorLog createTDgapWsErrorLog(String resourceDirId, String resourceId, String userId, String userName, String methodName, String errorCode, String errorDescription) {

        TDgapWsErrorLog tDgapWsErrorLog = new TDgapWsErrorLog();
        tDgapWsErrorLog.setResourceDirectoryId(resourceDirId);
        tDgapWsErrorLog.setWebServiceId(resourceId);
        tDgapWsErrorLog.setMethodName(methodName);
        tDgapWsErrorLog.setCallerUser(userName);

        tDgapWsErrorLog.setErrorDate(new Date());
        tDgapWsErrorLog.setCreateTime(new Date());
        tDgapWsErrorLog.setErrorType(errorCode);
        tDgapWsErrorLog.setErrorDesc(errorDescription);

        tDgapWsErrorLog.setCreateTime(new Date());
        tDgapWsErrorLog.setCreateBy(userId);
        return tDgapWsErrorLog;
    }

}
