package com.sofn.ducss.service;

import com.sofn.ducss.mapper.DisperseUtilizeDetailMapper;
import com.sofn.ducss.mapper.DisperseUtilizeMapper;
import com.sofn.ducss.mapper.StoredProcedureMapper;
import com.sofn.ducss.model.DisperseUtilize;
import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.StoredProcedure;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/***
 * @desc 异步任务
 * @author xl
 * @date 2021/04/15 10:17
 */
public interface AsyncService {


    /***
     *  异步任务 农户分散利用量批量导入
     * @param list
     * @param disperseUtilizeDetailMapper
     * @param countDownLatch
     */
    @Async("asyncServiceExecutor")
    void executeAsync(List<DisperseUtilizeDetail> list,  DisperseUtilizeDetailMapper disperseUtilizeDetailMapper,
                      CountDownLatch countDownLatch);

    /***
     * 存储过程 异步新增
     * @param list
     * @param storedProcedureMapper
     * @param countDownLatch
     */
    @Async("asyncServiceExecutor")
    void excuteAsyncByStored(  List<StoredProcedure> list, StoredProcedureMapper storedProcedureMapper, CountDownLatch countDownLatch);


    void excuteAsncUpadate(List<DisperseUtilize>  list, DisperseUtilizeMapper disperseUtilizeDetailMapper, CountDownLatch countDownLatch);
}
