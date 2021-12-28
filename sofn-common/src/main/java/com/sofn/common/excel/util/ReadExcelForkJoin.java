package com.sofn.common.excel.util;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.core.ExcelImport;
import com.sofn.common.excel.service.ExcelDataBatchInsertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 使用ForkJoin框架读取Excel
 *
 * @author heyongjie
 * @date 2020/6/11 9:58
 */
@Slf4j
public class ReadExcelForkJoin extends RecursiveTask<Integer> {

    /**
     * 阀值，每个线程最多读取多少条数据
     */
    private static final int THRESHOLD = 1000;


    /**
     * 从哪行开始
     */
    private int start;

    /**
     * 从哪行结束
     */
    private int end;

    private  ExcelImport excelImport;

    private  Class<T> cls;

    private ExcelDataBatchInsertService excelDataBatchInsertService;

    public ReadExcelForkJoin(int start, int end, ExcelImport excelImport,Class<T> cls,ExcelDataBatchInsertService excelDataBatchInsertService) {
        this.start = start;
        this.end = end;
        this.excelImport = excelImport;
        this.cls = cls;
        this.excelDataBatchInsertService = excelDataBatchInsertService;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            List<T> dataList = excelImport.getDataList(cls, ExcelField.Type.ALL, start, end);
            count += dataList.size();
            if(!CollectionUtils.isEmpty(dataList) && excelDataBatchInsertService != null){
                excelDataBatchInsertService.batchInsert(dataList);
            }
            dataList.clear();
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ReadExcelForkJoin leftTask = new ReadExcelForkJoin(start, middle,this.excelImport,this.cls,excelDataBatchInsertService);
            ReadExcelForkJoin rightTask = new ReadExcelForkJoin(middle + 1, end,this.excelImport,this.cls,excelDataBatchInsertService);
            leftTask.fork();
            rightTask.fork();
            int leftCount = leftTask.join();
            int rightCount = rightTask.join();
            count = leftCount + rightCount;
        }
        return count;
    }
}
