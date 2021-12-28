package com.sofn.agzirz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.model.Xgxxsj;
import com.sofn.agzirz.vo.FkyjzhVo;
import com.sofn.agzirz.vo.XgxxsjVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface XgxxsjService extends IService<Xgxxsj> {

    PageUtils<XgxxsjVo> getXgxxsjByPage(Map<String, Object> params, int pageNo, int pageSize);

    XgxxsjVo getXgxxsjById(String id);

    void addXgxxsj(Xgxxsj xgxxsj);

    void updateXgxxsj(Xgxxsj xgxxsj,String userId);

    void removeXgxxsj(String id,String userId);

    List<Xgxxsj> getXgxxsjList(Map<String, Object> params);

}
