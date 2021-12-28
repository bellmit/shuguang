package com.sofn.flow.util;

import com.cvicse.workflow.api.WfFilter;
import com.cvicse.workflow.api.query.entity.RunningEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件构造工具类
 * @author heyongjie
 * @date 2020/5/13 14:55
 */
public class WfFilterUtil {

    /**
     * 根据多条件获取AND实例
     * @param wfFilterList    WfFilter
     * @return  WfFilter
     */
    public static WfFilter getWfFilterAnd(List<WfFilter> wfFilterList){
        return getWfFilter(wfFilterList,WfFilter.JOIN_AND);
    }

    /**
     * 根据多条件获取or实例
     * @param wfFilterList    WfFilter
     * @return  WfFilter
     */
    public static WfFilter getWfFilterOr(List<WfFilter> wfFilterList){
        return getWfFilter(wfFilterList,WfFilter.JOIN_OR);
    }

    /**
     * 根据多条件获取AND实例
     * @param wfFilterList    WfFilter
     * @param type   WfFilter里面的JOIN_AND 或者其他
     * @return  WfFilter
     */
    private static WfFilter getWfFilter(List<WfFilter> wfFilterList,String type){
        if(StringUtils.isBlank(type)){
            return null;
        }
        if(!CollectionUtils.isEmpty(wfFilterList)){
            // 将前面过滤器构造为后面的
            if(wfFilterList.size() <= 1){
                return  wfFilterList.get(0);
            }
            int i = 0;
            WfFilter wfFilter = null;
            while(i<wfFilterList.size() ){
                int k = 0;
                if(wfFilter == null){
                    wfFilter = wfFilterList.get(i);
                    continue;
                }else{
                    k = ++ i;
                    wfFilter = new WfFilter(wfFilter,type,wfFilterList.get( k));
                }
                if(k >=wfFilterList.size()-1 ){
                    break;
                }
            }
            return wfFilter;
        }
        return null;
    }



    public static void main(String[] args) {
        List<WfFilter > wfFilters = new ArrayList<>();

        WfFilter wfFilter = new WfFilter(RunningEntity.ProcessId,WfFilter.EXPRESSION_EQUAL,"123");

        WfFilter wfFilter2 = new WfFilter(RunningEntity.ProcessId,WfFilter.EXPRESSION_EQUAL,"456");
        WfFilter wfFilter3 = new WfFilter(RunningEntity.ProcessId,WfFilter.EXPRESSION_EQUAL,"789");
        WfFilter wfFilter4 = new WfFilter(RunningEntity.ProcessId,WfFilter.EXPRESSION_EQUAL,"ijk");
        wfFilters.add(wfFilter);
        wfFilters.add(wfFilter2);
        wfFilters.add(wfFilter3);
        wfFilters.add(wfFilter4);
        WfFilter wfFilter1 = WfFilterUtil.getWfFilterAnd(wfFilters);
        System.out.println(wfFilter1);

    }


}
