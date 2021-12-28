package com.sofn.ducss.util.timing;

import com.sofn.ducss.mapper.StoredProcedureMapper;
import com.sofn.ducss.model.StoredProcedure;
import com.sofn.ducss.util.MysqlIpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/12/7 15:04
 * @description mysql定时调用执行过程类（创建一个新表，填报时修改状态码，定时扫描状态码并调用执行过程）
 */

@Component
@Configuration
@EnableAsync
@EnableScheduling
public class TriggerClass {

    @Autowired
    private StoredProcedureMapper storedProcedureMapper;

    /**
     * 定时扫描
     * initialDelay=5000 初始化5s执行方法
     * fixedDelay=60000 该方法的执行周期为一分钟
     */
    //@Scheduled(initialDelay = 5000, fixedDelay = 2000)
    public void doSomething() {
        //查询stored_procedure表进行执行函数的传参，并删除数据
        MysqlIpUtil mysqlIpUtil = new MysqlIpUtil();
        StoredProcedure storedProcedure = storedProcedureMapper.queryList();
        System.out.println(storedProcedure);
        if (!StringUtils.isEmpty(storedProcedure)) {
            System.out.println(mysqlIpUtil.transfer(storedProcedure.getStrawTypeData(), storedProcedure.getYearData(), storedProcedure.getAreaIdData()));
            //删除表数据
            storedProcedureMapper.deleteById(storedProcedure.getId());
        }
        else{
            System.out.println("没有数据");
        }
    }
}
