package com.sofn.etlquery.service;


import com.sofn.etlquery.dao.Query2HiveDao;
import com.sofn.etlquery.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("queryService")
public class QueryService {

    @Autowired
    private Query2HiveDao query2HiveDao;

    /**
     * 获取所有的表名
     *
     * @return
     * @throws Exception
     */
    public Result getAllTables() throws Exception {
        //result用于储存结果
        Result result = new Result();

        List res = query2HiveDao.getAllTables();
        System.out.println(res.toString());
        if (res == null || res.size() < 1) {
            result.setError(result, "未找到查询结果！", res.size(), res);
        } else {
            result.setSuccessec(result, "成功查询到结果!", res.size(), res);
        }
        return result;
    }


    /**
     * 根据表名去获取不同的Count值
     *
     * @param key
     * @param sort
     * @param tableName
     * @return
     */
    public Result getCountData(String key, String tableName, String sort) throws Exception {

        //result用于储存结果
        Result result = new Result();

        List res = new ArrayList();
        res = query2HiveDao.getCountData(key, tableName, sort);
        if (res == null || res.size() < 1) {
            result.setError(result, "未找到查询结果！", res.size(), res);
        } else {
            result.setSuccessec(result, "成功查询到结果!", res.size(), res);
        }
        return result;

    }

    /**
     * 获取详细数据
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public Result getDetailData(String tableName, int start, int length) throws Exception {

        Result result = new Result();

        List res = query2HiveDao.getDetailData(tableName, start, length);

        Long size=query2HiveDao.getCountSize(tableName);

        if (res == null || res.size() < 1) {
            result.setError(result, "未找到查询结果！", size.intValue(), res);
        } else {
            result.setSuccessec(result, "成功查询到结果!", size.intValue(), res);
        }
        return result;
    }


}
