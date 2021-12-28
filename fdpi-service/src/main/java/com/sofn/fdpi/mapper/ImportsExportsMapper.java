package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.vo.ImExportCacheVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 16:08
 */
public interface ImportsExportsMapper extends BaseMapper<ImportsExports> {
    int  addImoretExport(Map map);
    ImportsExports getImportsExportsById(@Param("id") String id);
    List<ImportsExports> getImportsExportsList(Map map);
    void updateImportsExports(Map map);
    int delImportsExports(@Param("id") String id);
    ImportsExports getImportsExportsByImpAuform(@Param("impAuform") String impAuform);
    void printImports(String id);



    List<ImExportCacheVo> saveRedis();

}
