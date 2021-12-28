package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.model.ImportsExportsSpecies;
import com.sofn.fdpi.vo.ImportExportCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 16:47
 */
public interface ImportsExportsSpeciesMapper  extends BaseMapper<ImportsExportsSpecies> {
    /**
     *
     * @param
     * @return
     */
    int  addList(@Param("list") List<ImportsExportsSpecies> list);
    int updateImportsExportsSpecies(Map map);
    int insert1(ImportsExportsSpecies ies);
    int  del(String id);
    void insetBatch(List<ImportsExportsSpecies> list);

    ImportExportCheck check(@Param("impAuform")String impAuform,@Param("speName")String speName);
}
