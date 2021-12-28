package com.sofn.etlquery.controller;

import com.sofn.etlquery.pojo.Result;
import com.sofn.etlquery.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/etlQuery")
public class QueryController {


    @Autowired
    private QueryService queryService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 获取所有表名
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getTables")
    public Result getTables() throws Exception {
        Result result = queryService.getAllTables();
        return result;
    }

    /**
     * 获取页面的统计值
     *
     * @param key
     * @param value
     * @param sort
     * @param tableName
     * @return
     */
    @ResponseBody
    @RequestMapping("getCount")
    public Result getCountData(@RequestParam String key,
                               @RequestParam String value,
                               @RequestParam String sort,
                               @RequestParam String tableName) throws Exception {
        Result data = queryService.getCountData(key, tableName, sort);

        return data;
    }

    /**
     * 获取用于表格显示的详细数据
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getDetail")
    public Result getDeatil(@RequestParam String tableName,
                            @RequestParam int start, //从start开始
                            @RequestParam int length) throws Exception {

        Result data = queryService.getDetailData(tableName,start,length);
        return data;
    }

}
