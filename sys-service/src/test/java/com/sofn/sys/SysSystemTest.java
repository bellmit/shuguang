package com.sofn.sys;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sofn.SysServiceApplicationTests;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.utils.DateUtils;
import com.sofn.sys.bean.ExcelSystemInfo;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.model.SysSubsystem;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class SysSystemTest extends SysServiceApplicationTests {

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;

    private static final String excelSetFilePath = "I:/绿康源/生态系统/开发计划及配置/生态项目子系统访问地址列表.xlsx";

    @Test
    public void updateSystemSubSystemNameByAppId(){
        List<ExcelSystemInfo> list = ExcelImportUtil.getDataByExcel(excelSetFilePath, 1, ExcelSystemInfo.class);

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(new Consumer<ExcelSystemInfo>() {
                @Override
                public void accept(ExcelSystemInfo info) {
                    if (StringUtils.isNotBlank(info.getAppName())
                            && StringUtils.equals(info.getIsMenHu(),"N")){

                        SysSubsystem sysSubsystem = new SysSubsystem();
                        sysSubsystem.setViewUrl(info.getViewUrl());

                        sysSubsystemMapper.update(sysSubsystem, new UpdateWrapper<SysSubsystem>()
                                .eq("SUBSYSTEM_NAME",info.getAppName()));
                    }
                }
            });
        }
    }

    @Test
    public void updateSystemViewUrlByAppId(){
        List<ExcelSystemInfo> list = ExcelImportUtil.getDataByExcel(excelSetFilePath, 1, ExcelSystemInfo.class);

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(new Consumer<ExcelSystemInfo>() {
                @Override
                public void accept(ExcelSystemInfo info) {
                    if (StringUtils.isNotBlank(info.getAppName()) && StringUtils.equals(info.getIsMenHu(),"N")){

                        SysSubsystem sysSubsystem = new SysSubsystem();
                        sysSubsystem.setViewUrl(info.getInViewUrl());

                        sysSubsystemMapper.update(sysSubsystem, new UpdateWrapper<SysSubsystem>()
                                .eq("SUBSYSTEM_NAME",info.getAppName()));
                    }
                }
            });
        }
    }

}
