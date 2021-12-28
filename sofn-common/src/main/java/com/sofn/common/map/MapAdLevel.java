package com.sofn.common.map;

import lombok.Getter;

/**
 * @Description: 地图行政级别
 * @Author: quzhijie
 * @Date: 2020/7/24 16:40
 **/
@Getter
public enum MapAdLevel {

  /**
   * 国家级
   */
  AD_COUNTRY("ad_country","国家级"),

  /**
   * 省级
   */
  AD_PROVINCE("ad_province","省级"),

  /**
   * 市级
   */
  AD_CITY("ad_city","市级");

  private String code;
  private String desc;

  MapAdLevel(String code, String desc){
    this.code = code;
    this.desc = desc;
  }

}
