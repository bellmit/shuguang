package com.sofn.fdzem.content;

import com.sofn.common.log.Log;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.utils.WebUtil;
import com.sofn.fdzem.mapper.MonitorStationMapper;
import com.sofn.fdzem.mapper.MonitoringStationTaskMapper;
import com.sofn.fdzem.model.MonitorStation;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.MonitoringStationTaskService;
import com.sofn.fdzem.vo.MonitroIdVo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Aspect
@Component
public class CoorDinateAspect {

    /*  @Autowired
      private ApplicationEventPublisher applicationEventPublisher;*/
    @Autowired
    MonitoringStationTaskMapper monitoringStationTaskMapper;
    @Autowired
    MonitorStationMapper monitorStationMapper;


    @Pointcut("execution(* com.sofn.fdzem.web.MonitorStationController.saveOrUpdate(..))&& args(monitorStation)")
    public void saveOrUpdate(MonitorStation monitorStation) {

    }



    /**
     * 监测站维护发生改变时，更新数据
     *
     * @param point
     * @param monitorStation
     * @return
     * @throws Throwable
     */
    @Around("saveOrUpdate(monitorStation)")
    public Object aroundMethod(ProceedingJoinPoint point, MonitorStation monitorStation) throws Throwable {
        //long time = System.currentTimeMillis();
        try {
            Object returns = point.proceed();
            //根据名字批量更新
            monitoringStationTaskMapper.updateByMonitorStation(monitorStation);
            return returns;
        } catch (Throwable e) {
            throw e;
        }
    }

}
