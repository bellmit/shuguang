package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.OmExportModel;
import com.sofn.fdpi.vo.OmHistogram;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/21 17:24
 **/
@Mapper
public interface OmExportMapper extends BaseMapper<OmExportModel> {
    //得到再出口的柱状图数据
    List<OmHistogram> getOmHistogram(@Param("pr") String procName, @Param("cr") String credential, @Param("st") Date startDate, @Param("en") Date endDate);
}
