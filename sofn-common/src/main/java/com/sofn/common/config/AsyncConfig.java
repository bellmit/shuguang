package com.sofn.common.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 异步执行配置
 * 只需要在要异步执行的方法上添加@Async注解即可异步执行
 * @Author: quzhijie
 * @Date: 2020/5/29 13:58
 **/
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    return new ScheduledThreadPoolExecutor(2,
      new BasicThreadFactory.Builder()
        .namingPattern("sofn-schedule-pool-%d")
        .daemon(true)
        .build()
    );
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    // 执行过程中的异常捕获处理
    return new AsyncUncaughtExceptionHandler() {
      @Override
      public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.error("async execute error, method:{}, param:{}", method.getName(), JSON.toJSONString(objects), throwable);
      }
    };
  }
}
