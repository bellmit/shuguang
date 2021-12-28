package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.OmEelImportFrom;
import com.sofn.fdpi.vo.OmHistogram;
import com.sofn.fdpi.vo.OmImportFromVo;
import com.sofn.fdpi.vo.QuotaListVo;
import com.sofn.fdpi.vo.SelectVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/12 13:24
 **/
@Mapper
public interface OmEelImportMapper extends BaseMapper<OmEelImportFrom> {
    //得到进口企业柱状图数据
    List<OmHistogram> getOmHistogram(@Param("im") String importMan, @Param("cr") String credential, @Param("st") Date startDate, @Param("en") Date endDate);

    //得到有效的进口企业下拉列表名称
    List<SelectVo> getImportList();

    //通过map得到列表数据
    List<OmImportFromVo> getOmEelImportFromByMap(Map map);

    //得到进口企业的欧鳗企业配额管理
    List<QuotaListVo> getImportQuotaListByMap(Map map);
}
