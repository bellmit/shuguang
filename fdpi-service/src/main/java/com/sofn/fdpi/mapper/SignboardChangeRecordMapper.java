package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SignboardChangeRecord;

import java.util.List;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/2/6 10:30
 **/
public interface SignboardChangeRecordMapper extends BaseMapper<SignboardChangeRecord> {

    List<SignboardChangeRecord> listByParams(Map<String, Object> params);
}
