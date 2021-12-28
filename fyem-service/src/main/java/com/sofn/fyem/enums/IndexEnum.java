package com.sofn.fyem.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 指标枚举
 * @Author: DJH
 * @Date: 2020/8/3 11:42
 */
@Getter
public enum IndexEnum {

    RELEASES_POINTS_DTB("release_points_distribute", "放流点位分布"),
    RELEASES_COUNT("release_count", "放流数量（万尾）"),
    INVEST_FUNDS("invest_funds", "投入资金（万元）"),
    RELEASES_AC_COUNT("release_activities_count", "放流活动次数");

    private String index;
    private String desc;

    IndexEnum(String index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public static List<String> indexList(){
        List<String> indexs = new ArrayList<>();
        for (IndexEnum indexEnum: IndexEnum.values()){
            indexs.add(indexEnum.getIndex());
        }
        return indexs;
    }

    public static Map<String, String> toMap(){
        Map<String, String> map = new HashMap<>();
        for (IndexEnum indexEnum: IndexEnum.values()){
            map.put(indexEnum.getIndex(), indexEnum.getDesc());
        }
        return map;
    }

    public static IndexEnum getEnum(String index){
        for (IndexEnum indexEnum : IndexEnum.values()){
            if (indexEnum.getIndex().equals(index)){
                return indexEnum;
            }
        }
        return null;
    }
}
