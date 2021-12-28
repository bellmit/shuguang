package com.sofn.provider.dgap;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.UUIDUtil;
import com.sofn.dao.dgap.TDgapResourceExpandMapper;
import com.sofn.dao.dgap.TDgapWsDailyStatExpandMapper;
import com.sofn.model.generator.TDgapWsDailyStat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/10/13.
 */
@DubboService(interfaceClass = TDgapWsDailyStatProvider.class)
public class TDgapWsDailyStatProviderImpl extends BaseProviderImpl<TDgapWsDailyStat>
        implements TDgapWsDailyStatProvider {
    @Autowired
    private TDgapWsDailyStatExpandMapper wsDailyStatExpandMapper;
    @Autowired
    private TDgapResourceExpandMapper tDgapResourceExpandMapper;

    @Override
    public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
        List<Map<String, Object>> list = wsDailyStatExpandMapper.getList(params);
        long count = wsDailyStatExpandMapper.getCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public int stateDailyLog(Date time) {
        List<TDgapWsDailyStat> stats = wsDailyStatExpandMapper.getDailyLogs(time);
        for (TDgapWsDailyStat stat : stats) {
            logger.debug("stat:" + stat);
            String uid = UUIDUtil.getUUID();
            System.out.println(uid);
            Map<String, Object> map = new HashedMap();
            map.put("webserviceId", stat.getWebServiceId());
            map.put("bussnessName", stat.getBussnessName());
            map.put("callerUser", stat.getCallerUser());
            map.put("time", time);
            stat.setSuccessTimes((BigDecimal) wsDailyStatExpandMapper.calcSuccessTimes(map));
            logger.debug("stat.getSuccessTimes():" + stat.getSuccessTimes());
            stat.setFailTimes((BigDecimal) wsDailyStatExpandMapper.calcFailTimes(map));
            logger.debug("stat.getFailTimes():" + stat.getFailTimes());
            stat.setAvgTimecost((BigDecimal) wsDailyStatExpandMapper.calcAvgTimeCost(map));
            logger.debug("stat.getAvgTimecost():" + stat.getAvgTimecost());
            stat.setId(uid == null ? "" : uid);
            if (stat.getSuccessTimes() != null
                    && stat.getFailTimes() != null
                    && stat.getAvgTimecost() != null) {
                logger.debug("++++++++++++++++++++++++++++++++++++++++");
                if (wsDailyStatExpandMapper.getBy(stat) == null) {
                    wsDailyStatExpandMapper.insert(stat);
                } else {
                    wsDailyStatExpandMapper.update(stat);
                }
            } /*else{
            logger.debug("skipped");
        }*/
        }

        int num = stats == null ? 0 : stats.size();
        logger.info("generate total " + num + " stats for day:" + time);
        return num;
    }

    /**
     * 分页示例
     */
    public void testPageHelper() {
        PageHelper.startPage(1, 10, true);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = {Exception.class})
    public void setWebServiceLog(TDgapWsDailyStat stat) {
        if (stat.getSuccessTimes() != null
                && stat.getFailTimes() != null
                && stat.getAvgTimecost() != null) {
            logger.debug("++++++++++++++++++++++++++++++++++++++++");
            try {
                if (wsDailyStatExpandMapper.newGetBy(stat).size() == 0) {
                    wsDailyStatExpandMapper.insert(stat);
                } else {
                    wsDailyStatExpandMapper.update(stat);
                }
            } catch (Exception e) {
                throw e;
            }

        }
    }
}
