package com.sofn.sys.service;

import lombok.Data;

import java.util.Map;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/7/29 16:41
 *@Version 1.0
 **/
@Data
public class PoiResult {
  private String status;
  private String info;
  private String count;
  private Map<String,String> pois;
}
