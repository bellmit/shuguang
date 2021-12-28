package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SignboardPrint;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SignboardPrintMapper extends BaseMapper<SignboardPrint> {

    List<SignboardPrint> listByParams(Map<String, Object> params);

    String getCompNameByPrintId(@Param("printId") String printId);

    String getYearMaxSequenceNum(String contractNum);
}
