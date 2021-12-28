package com.sofn.fyem.enums;

import com.sofn.fyem.vo.ReleaseLevelDropDownVo;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 放流活动级别枚举
 * @Author: DJH
 * @Date: 2020/4/27 16:39
 */
@Getter
public enum ReleaseLevelEnum {
    OTHER_LEVEL("0","其他"),
    CITY_COUNTY_LEVEL("1","市县级"),
    PROV_LEVEL("2","省级"),
    COUNTRY_LEVEL("3","国家级");

    private String status;
    private String describe;

    ReleaseLevelEnum(String status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    /**
     * 获取放流活动级别列表
     * @return
     */
    public static List<ReleaseLevelDropDownVo> listForLevels(){
        List levelList = new ArrayList();
        ReleaseLevelEnum[] values = ReleaseLevelEnum.values();
        for (ReleaseLevelEnum level : values){
            levelList.add(new ReleaseLevelDropDownVo(level.getStatus(), level.getDescribe()));
        }
        return levelList;
    }

    public static ReleaseLevelEnum getEnum(String status){
        if (StringUtils.isBlank(status)){
            return null;
        }
        for (ReleaseLevelEnum releaseLevelEnum : ReleaseLevelEnum.values()){
            if (releaseLevelEnum.getStatus().equals(status)){
                return releaseLevelEnum;
            }
        }
        return null;
    }

    // 根据放流级别描述转换放流级别
    public static String getStatus(String describe){
        for (ReleaseLevelEnum releaseLevelEnum : ReleaseLevelEnum.values()){
            if (releaseLevelEnum.getDescribe().equals(describe)){
                return releaseLevelEnum.getStatus();
            }
        }
        return ReleaseLevelEnum.OTHER_LEVEL.getStatus();
    }
}
