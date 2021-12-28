package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SignboardProcess;

import java.util.List;
import java.util.Map;

/**
 * @Deacription TODO
 * @Author yumao
 * @Date 2019/12/31 9:14
 **/
public interface SignboardProcessMapper extends BaseMapper<SignboardProcess> {

    List<SignboardProcess> listByParams(Map<String, Object> params);
}
