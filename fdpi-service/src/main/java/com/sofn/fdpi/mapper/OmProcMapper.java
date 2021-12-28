package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.OmProc;
import com.sofn.fdpi.vo.OmHistogram;
import com.sofn.fdpi.vo.QuotaListVo;
import com.sofn.fdpi.vo.SelectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/20 10:05
 **/
@Mapper
public interface OmProcMapper extends BaseMapper<OmProc> {
    //得到加工企业的交易数量和交易频次的柱状图数据
    List<OmHistogram> getOmHistogram(@Param("im") String omProcComp, @Param("cr") String credential, @Param("st") Date startDate, @Param("en") Date endDate);

    //加工企业再出口时得到有效的养殖企业下拉vo
    List<SelectVo> getBreedSelectVoList(@Param("cid") String compId);

    List<QuotaListVo> getImportQuotaListByMap(Map<String, Object> params);
}
