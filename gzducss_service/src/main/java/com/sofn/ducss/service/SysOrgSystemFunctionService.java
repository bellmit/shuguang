package com.sofn.ducss.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.SysSystemFunction;

import java.util.List;

public interface SysOrgSystemFunctionService extends IService<SysSystemFunction> {
  void saveFunction(String orgId, List<SysSystemFunction> list);
  List<SysSystemFunction> getByOrgId(String orgId);
}
