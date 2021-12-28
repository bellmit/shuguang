package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysSystemFunction;

import java.util.List;

public interface SysOrgSystemFunctionService extends IService<SysSystemFunction> {
  void saveFunction(String orgId, List<SysSystemFunction> list);
  List<SysSystemFunction> getByOrgId(String orgId);
}
