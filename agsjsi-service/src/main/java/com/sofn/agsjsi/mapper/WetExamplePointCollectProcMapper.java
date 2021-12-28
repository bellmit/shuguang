package com.sofn.agsjsi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjsi.model.WetExamplePointCollectProc;
import com.sofn.agsjsi.vo.ProcessVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WetExamplePointCollectProcMapper extends BaseMapper<WetExamplePointCollectProc> {
    List<ProcessVo> listByForeignId(@Param("foreignId") String foreignId);
}
