package com.sofn.ducss.util;

import com.sofn.ducss.model.basemodel.BaseData;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/7/31 18:20
 *@Version 1.0
 **/
public class RegionUtils {

  public final static String dict_value="virtual_region";

  public static List<BaseData> getBaseDataList(String appId){
    List<BaseData> baseDataList = null;
    if (StringUtils.isNotBlank(appId)){
      baseDataList = BaseDataUtils.getNotInDataByType(appId, dict_value);
    }else {
      baseDataList = BaseDataUtils.getAllByType(dict_value);
    }

    return baseDataList;
  }

  public static boolean isContainRegion(String regionCode, List<BaseData> baseDataList) {
    if (!CollectionUtils.isEmpty(baseDataList)){
      for (BaseData baseData : baseDataList) {
        if (StringUtils.equals(regionCode, baseData.getBaseDataCode())){
          return true;
        }
      }
    }

    return false;
  }
}
