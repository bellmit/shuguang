package com.sofn.sys.event.bean;

import com.sofn.sys.model.SysBaseData;
import lombok.Data;

import java.util.List;

/**
 *@Description 基础数据分配广播对象
 *@Author quzhijie
 *@Date 2020/8/3 17:04
 *@Version 1.0
 **/
@Data
public class BaseDataAllotEvent {

  private String appId;

  private List<SysBaseData> regionCodes;

}
