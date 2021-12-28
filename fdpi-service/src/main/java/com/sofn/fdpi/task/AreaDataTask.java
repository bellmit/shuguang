package com.sofn.fdpi.task;

import com.sofn.common.utils.RedisUtils;
import com.sofn.fdpi.service.RegionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class AreaDataTask {

    @Resource
    RedisUtils redisUtils;

    @Resource
    RegionService regionService;

    private final String FDPI_AREA_DATA = "FDPI_AREA_DATA";
    //redis锁定时间
    private Long lockTime = 60L;

    //每天零晨1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void handleAreaDataTask() {
        if (Objects.isNull(redisUtils.get("FDPI_AREA_DATA"))) {
            redisUtils.set(FDPI_AREA_DATA, true, lockTime);
            //同步支撑平台行政区域数据
            regionService.handleAreaData();
        }
    }
}
