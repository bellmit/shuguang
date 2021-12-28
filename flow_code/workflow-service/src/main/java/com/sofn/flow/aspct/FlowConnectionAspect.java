package com.sofn.flow.aspct;

import com.alibaba.druid.pool.DruidDataSource;
import com.cvicse.workflow.api.WfClient;
import com.cvicse.workflow.api.WfManagerClient;
import com.sofn.flow.annotation.GetFlowConnection;
import com.sofn.flow.util.DbUtil;
import com.sofn.flow.util.GetWfClientUtil;
import com.sofn.flow.util.RedisUtil;
import com.sofn.flow.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 获取工作流连接对象切面
 * <p>
 * 提供一个环绕通知，在方法开始前获取连接，方法结束后关闭连接，针对有GetFlowConnection注解的方法
 * 提供isTransation 用于控制事务
 *
 * @author heyongjie
 * @date 2020/5/9 13:53
 */
@Component
@Aspect
public class FlowConnectionAspect {

    @Resource
    RedisUtil redisUtil;

    @Resource
    DruidDataSource dataSource;

    @Pointcut("@annotation(com.sofn.flow.annotation.GetFlowConnection)")
    public void includeService() {
    }

    @Around("includeService()")
    public Object getConnect(ProceedingJoinPoint proceedingJoinPoint) {
        // 程序启动的时候第一个参数
        Object[] args = proceedingJoinPoint.getArgs();
        if (args == null || args.length == 0) {
            return Result.error("该方法没有参数，必须含有参数并且第一个参数为WfClient");
        }
        MethodSignature sign = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = sign.getMethod();
        boolean isTransactional = true;
        try {
            Method realMethod = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(sign.getName(),
                    method.getParameterTypes());
            //获取方法上的注解
            GetFlowConnection getFlowConnection = realMethod.getAnnotation(GetFlowConnection.class);
            if (getFlowConnection != null) {
                isTransactional = getFlowConnection.isTransactional();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取数据库连接
        Connection dbConnection = null;
        try {
            dbConnection = dataSource.getConnection();
        }catch (Exception e) {
            return new Result<>(500, "数据库连接获取异常");
        }

        if (isTransactional) {
            try {
                dbConnection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                return new Result<>(500, "数据库无法设置手动提交");
            }
        }

        if (args[0] == null) {
            // 执行方法
            return getResult(proceedingJoinPoint, args, isTransactional, dbConnection);
        } else if (!(args[0] instanceof WfClient)) {
            return Result.error("使用了GetFlowConnection注解的方法的第一个参数必须是WfClient，请查看方法：" + method.getName());
        } else {
            // 如果是传入的连接，不能自动关闭，只有在方法执行异常的时候关闭连接
            // 传入连接不会提交事务
            try {
                return proceedingJoinPoint.proceed(args);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                // 执行失败关闭连接
                WfClient wfClient = (WfClient) args[0];
                if (isTransactional) {
                    DbUtil.roleback(dbConnection);
                }
                DbUtil.closeConnection(dbConnection);
                return Result.error("方法执行失败，" + throwable.getMessage());
            }
        }
    }

    /**
     * 当没有传入连接的时候，执行方法，获取返回值
     *
     * @param proceedingJoinPoint   切面
     * @param args   参数
     * @param isTransactional   是否开启事务
     * @param dbConnection   数据库连接
     * @return   方法返回值
     */
    private Object getResult(ProceedingJoinPoint proceedingJoinPoint, Object[] args, boolean isTransactional, Connection dbConnection) {
        String username = redisUtil.getLoginUserName();
        if (StringUtils.isBlank(username)) {
            return new Result<>(401, "token过期");
        }

        MethodSignature sign = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = sign.getMethod();
        Class<?>[] paramsTypes = method.getParameterTypes();

        WfClient wfClient = null;
        WfManagerClient wfManagerClient = null;
        if (paramsTypes != null && paramsTypes.length>0) {
            if (paramsTypes[0] == WfClient.class) {
                wfClient = GetWfClientUtil.getWfClientConnection(username, dbConnection);
                if (isTransactional) {
                    wfClient.beginTransaction();
                }
                args[0] = wfClient;
            }else if (paramsTypes[0] == WfManagerClient.class) {
                wfManagerClient = GetWfClientUtil.getWfManageClientConnection(username, dbConnection);
                if (isTransactional) {
                    wfManagerClient.beginTransaction();
                }
                args[0] = wfManagerClient;
            }
        }

        try {
            Object proceed = proceedingJoinPoint.proceed(args);
            if (proceed instanceof Result) {
                Result result = (Result) proceed;
                // 判断状态码是否是正确的，如果是错误的就直接回滚
                if (isTransactional) {
                    if (result.getCode().equals(Result.CODE)) {
                        dbConnection.commit();
                    } else {
                        DbUtil.roleback(dbConnection);
                    }
                }
                return proceed;
            } else {
                if (isTransactional) {
                    dbConnection.commit();
                }
                return proceed;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (isTransactional) {
                DbUtil.roleback(dbConnection);
            }
            return Result.error("方法执行失败，" + throwable.getMessage());
        } finally {
            // 必须关闭连接
            if (wfClient != null) {
                GetWfClientUtil.closeConnection(wfClient);
            }

            if (wfManagerClient != null) {
                GetWfClientUtil.closeConnection(wfManagerClient);
            }

            DbUtil.closeConnection(dbConnection);
        }
    }

}
