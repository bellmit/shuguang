package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.model.sys.OperateLogQueryParam;
import com.sofn.model.sys.OperateLogStats;
import com.sofn.provider.sys.SysOperateLogProvider;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/16.
 */
@Service
public class SysOperateLogService extends BaseService<SysOperateLogProvider,SysOperateLog> {

    @DubboReference
    public void setSysOperateLogProvider(SysOperateLogProvider provider){this.provider = provider ;}

    /**
     * 分页查询数据
     * @param map
     * @return
     */
    public List<SysOperateLog> getPageInfo(Map<String,Object> map){
        return provider.getPageInfo(map);
    }

    /**
     * 分页查询数据
     * @param map
     * @return
     */
    public List<SysOperateLog> getDataPageInfo(Map<String,Object> map){
        return provider.getDataPageInfo(map);
    }

    public long getCount(Map<String,Object> map){
        return provider.getCount(map);
    }

    public long getDataCount(Map<String,Object> map){
        return provider.getDataCount(map);
    }

    /**
     * 添加数据到数据库
     * @param sysOperateLog
     * @return
     */
    public int addSysOperatelog(SysOperateLog sysOperateLog){
        return provider.addSysOperateLog(sysOperateLog);
    }

    /**
     * 根据IP查询
     * @param operateIp
     * @return
     */
    public List<SysOperateLog> findByIp(String operateIp){
        return provider.findByIp(operateIp);
    }

    /**
     * 获取记录中的最小日期
     * @param format 日期格式字符串
     * @return 日期字符串
     */
    public String getMinDate(String format) {
        return DateUtil.format(provider.getMinDate(), format);
    }

    /**
     * 获取系统操作日志统计数据
     * @param startDate 查询起始日期
     * @param endDate 查询截至日期
     * @return 系统操作日志统计数据
     */
    public List<OperateLogStats> getOperateLogStats(String startDate, String endDate) {
        return provider.getOperateLogStats(startDate, endDate);
    }

    public void disposeExcelTempFile(SXSSFWorkbook workbook) {
        if (workbook != null) {
            workbook.dispose();
            System.out.println("清理临时文件");
        }
    }

    public PageInfo<SysOperateLog> getOperateLogs(OperateLogQueryParam param) {
        return provider.getOperateLogs(param);
    }

    public List<String> getOperateTypes() {
        return provider.getOperateTypes();
    }
}
