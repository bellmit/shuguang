package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.OmBreed;
import com.sofn.fdpi.model.SourceInfoModel;
import com.sofn.fdpi.vo.OmBreedReVo;
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
 * @Date 2021/5/15 19:28
 **/
@Mapper
public interface OmBreedMapper extends BaseMapper<OmBreed> {
    //根据进口企业和允许进出口证明书号得到溯源信息
    List<SourceInfoModel> getSourceInfo(@Param("ce") String cellComp, @Param("cr") String credential);

    //双表关联根据进口企业名和允许进出口说明书拿到详情
    OmBreedReVo getOneInfo(@Param("ce") String cellComp, @Param("cr") String credential);

    //得到养殖企业的柱状图数据
    List<OmHistogram> getOmHistogram(@Param("im") String importMan, @Param("cr") String credential, @Param("st") Date startDate, @Param("en") Date endDate);

    //得到剩余欧鳗数量不为零的养殖企业
    List<SelectVo> getBreedList();

    List<QuotaListVo> getImportQuotaListByMap(Map params);
}
