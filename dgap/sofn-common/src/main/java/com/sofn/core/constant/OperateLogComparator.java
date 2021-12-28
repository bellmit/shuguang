package com.sofn.core.constant;

import java.util.Comparator;

/**
 * Created by Liw on 2017/1/16.
 * 对Redis中存放的OperateLog，按照操作日期进行降序排序的Comparator
 */
public class OperateLogComparator implements Comparator<OperateLog> {
    public int compare(OperateLog o1, OperateLog o2) {
        return (int) (o2.getOperateTime().getTime() - o1.getOperateTime().getTime());
    }
}
