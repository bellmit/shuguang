package com.sofn.ducss.service.impl;

import com.sofn.ducss.mapper.DisperseUtilizeDetailMapper;
import com.sofn.ducss.mapper.DisperseUtilizeMapper;
import com.sofn.ducss.mapper.StoredProcedureMapper;
import com.sofn.ducss.model.DisperseUtilize;
import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.StoredProcedure;
import com.sofn.ducss.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    public void executeAsync(List<DisperseUtilizeDetail> list,DisperseUtilizeDetailMapper disperseUtilizeDetailMapper,  CountDownLatch countDownLatch) {
        try{
            log.warn("start executeAsync");
            //异步线程要做的事情
            disperseUtilizeDetailMapper.insertList(list);
            // storedProcedureMapper.insertBatch(storedProcedureList);
            log.warn("end executeAsync");
        }catch (Exception e){
           e.printStackTrace();
        } finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }

    @Override
    public void excuteAsyncByStored(List<StoredProcedure> list, StoredProcedureMapper storedProcedureMapper, CountDownLatch countDownLatch) {
        try{
            log.warn("start executeAsync");
            //异步线程要做的事情
            storedProcedureMapper.insertBatch(list);
            // storedProcedureMapper.insertBatch(storedProcedureList);
            log.warn("end executeAsync");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }

    @Override
    public void excuteAsncUpadate(List<DisperseUtilize> list, DisperseUtilizeMapper disperseUtilizeMapper, CountDownLatch countDownLatch) {
        try{
            log.warn("start executeAsync");
            //异步线程要做的事情
            disperseUtilizeMapper.updateBatch(list);
            // storedProcedureMapper.insertBatch(storedProcedureList);
            log.warn("end executeAsync");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }
}
