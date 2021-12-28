package com.sofn.common.map;

import lombok.Getter;

/**
 * @Description: 地图视图类型
 * @Author: quzhijie
 * @Date: 2020/7/24 16:40
 **/
@Getter
public enum MapViewType {

  /**
   * 在地图上根据经纬度打点
   */
  POINT("point","描点"),

  /**
   * 在地图上行政区域高亮
   */
  AREA("area","区域"),

  PANCAKE("pancake","饼状图"),

  COLUMNAR("columnar","柱状图"),
  /**
   * 以上2中都存在
   */
  ALL("all","全部");

  private String code;
  private String desc;

  MapViewType(String code, String desc){
    this.code = code;
    this.desc = desc;
  }

}
