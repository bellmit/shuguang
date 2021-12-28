package com.sofn.agzirz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.vo.FkyjzhVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface FkyjzhService extends IService<FKYJZH> {

    PageUtils<FkyjzhVo> getFkyjzhByPage(Map<String, Object> params, int pageNo, int pageSize);

    FkyjzhVo getFkyjzhById(String id);

    void addFkyjzh(FKYJZH fkyjzh);

    void updateFkyjzh(FKYJZH fkyjzh,String userId);

    void removeFkyjzh(String id,String userId);

    List<FKYJZH> getFkyjzhList(Map<String, Object> params);

}
