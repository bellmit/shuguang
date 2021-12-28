package com.sofn.dgap.scheduler;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapResource;
import com.sofn.provider.dgap.TDgapResourceProvider;
import com.sofn.service.CSVImportService;
import org.apache.cxf.common.i18n.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author helong
 *
 * @date 2016/10/24
 */
@Component
public class FilesSyncInSheduler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${scheduler.datafile.import.enable}")
    String enabled = "false";

/*
    @Value("${datafile.source.basedir}")
    String basedir = "/tmp/apusic";
*/

    @DubboReference
    @NotNull
    private TDgapResourceProvider resourceProvider;

    @Autowired
    @NotNull
    CSVImportService importService;

    //秒（0~59） 分（0~59） 时（0~23） 天（月0~31） 月（0~11） 天（星期1~7）
    @Scheduled(cron = "0 0 * * * ?")
    public void timerHandle() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");//设置日期格式
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String currentHour = df.format(date);

        boolean isGo = false;
        if (RedisUtil.get("dgap_file_import") == null || RedisUtil.get("dgap_file_import").equals("")) {

            RedisUtil.set("dgap_file_import", currentHour);
            isGo = true;
        } else if (!RedisUtil.get("dgap_file_import").equals(currentHour)) {
            isGo = true;
            RedisUtil.set("dgap_file_import", currentHour);
        }

        if (isGo) {
            if (enabled != null && enabled.equalsIgnoreCase("true")) {
                logger.info(Constants.Features.Dgappre_csv.logTag()+":Scheduler for datafile.import started!");
                List<TDgapResource> resources = resourceProvider.getResourceByType(Constants.ResourceType.数据导入.code());
                for (TDgapResource resource : resources) {
                    String filePath = resource.getFilePath();
                    if (filePath != null) {
                        importService.newFilesArrived(filePath);
                    }
                }
                logger.info(Constants.Features.Dgappre_csv.logTag()+":Scheduler for datafile.import finished!");
            } else {
                logger.info(Constants.Features.Dgappre_csv.logTag()+":Scheduler for datafile.import disabled!");
            }
        }
    }

}


