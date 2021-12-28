package com.sofn.fdzem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdzem.model.Evaluate;
import com.sofn.fdzem.vo.EvaluateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluateMapper extends BaseMapper<Evaluate> {
    @Select("select score_record from tb_evaluate where monitor_id=#{id} and submit_year=#{date}")
    String selectScore(String id, String date);
}
