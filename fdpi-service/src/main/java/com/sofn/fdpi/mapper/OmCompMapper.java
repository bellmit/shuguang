package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.OmComp;
import com.sofn.fdpi.vo.OmBreedProcTableVo;
import com.sofn.fdpi.vo.QuotaListVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface OmCompMapper extends BaseMapper<OmComp> {

    //省级部级用户的交易比例折算和数据补录、汇总分析接口
    List<OmBreedProcTableVo> getConvertList(Map<String, Object> params);
}
