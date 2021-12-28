package com.sofn.agpjpm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjpm.model.SoilType;
import com.sofn.agpjpm.model.TargetSpec;
import com.sofn.agpjpm.vo.DropDownWithLatinVo;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 15:47
 */
public interface TargetSpecMapper extends BaseMapper<TargetSpec> {

    List<String>  getList();
    DropDownWithLatinVo getBySpecId(String specId);


    void updateForDelete(String id);
}
