package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SignboardPrintList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SignboardPrintListMapper extends BaseMapper<SignboardPrintList> {

    List<SignboardPrintList> listByParams(Map<String, Object> params);
}
