package com.sofn.fdzem.content;

import com.sofn.fdzem.mapper.MonitorStationMapper;
import com.sofn.fdzem.mapper.MonitoringStationTaskMapper;
import com.sofn.fdzem.vo.MonitroIdVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UpdateIndexAspect {
    @Autowired
    MonitoringStationTaskMapper monitoringStationTaskMapper;
    @Autowired
    MonitorStationMapper monitorStationMapper;

    @Pointcut("execution(* com.sofn.fdzem.web.DistributeController.updateIndex(..))&& args(monitroIdVo)")
    public void updateIndex(MonitroIdVo monitroIdVo) {

    }

    /**
     * 监测中心分配时，更新数据
     *
     * @param point
     * @param monitroIdVo
     * @return
     * @throws Throwable
     */
    @Around("updateIndex(monitroIdVo)")
    public Object updateIndex(ProceedingJoinPoint point, MonitroIdVo monitroIdVo) throws Throwable {
        //long time = System.currentTimeMillis();
        try {
            Object returns = point.proceed();
            //如果没值，就将监测站点全清空
            if (!monitroIdVo.getMonitroIds().isEmpty()) {
                //根据id查出监测站名字
                List<String> names = monitorStationMapper.selectNameByIds(monitroIdVo.getMonitroIds());
                //根据名字集合批量跟新
                monitoringStationTaskMapper.updateBynames(names, monitroIdVo.getId());
            } else {
                monitoringStationTaskMapper.updateMonitroIdById(monitroIdVo.getId());
            }
            return returns;
        } catch (Throwable e) {
            throw e;
        }
    }
}
