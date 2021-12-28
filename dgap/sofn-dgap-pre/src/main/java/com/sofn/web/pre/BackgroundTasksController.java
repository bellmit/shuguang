package com.sofn.web.pre;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseController;
import com.sofn.dgap.scheduler.FilesSyncInSheduler;
import com.sofn.dgap.scheduler.SpybDataSyncScheduler;
import org.apache.cxf.common.i18n.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/tasks")
public class BackgroundTasksController extends BaseController {

    @Autowired
    private FilesSyncInSheduler filesSyncInSheduler;
    @Autowired
    private SpybDataSyncScheduler spybDataSyncScheduler;


    @GetMapping(value = "/filesImport")
    public void importCsv() throws Exception {
        filesSyncInSheduler.timerHandle();
    }

    @RequestMapping(value = "/t", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String simportCsv(@RequestParam(value = "time") String time, @RequestParam(value = "n") String n) throws java.lang.Exception {
        return spybDataSyncScheduler.synctestYoujiData(time, n);
    }

    @RequestMapping(value = "/l", produces = "application/json;charset=utf-8")
    public String simportCsv1(@RequestParam(value = "time") String time, @RequestParam(value = "n") String n) throws java.lang.Exception {
        return spybDataSyncScheduler.syncTestLvseData(time, n);
    }


    @RequestMapping(value = "/spybdjstatus", produces = "application/json;charset=utf-8")
    public String getData() throws java.lang.Exception {
        return spybDataSyncScheduler.getData();
    }

}
