package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.model.ImportsExportsSpecies;
import com.sofn.fdpi.vo.ImportExportCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-03 16:00
 */
public interface ImportsExportsSpeService extends IService<ImportsExportsSpecies> {

    int addImportsExportsSpe(List<ImportsExportsSpecies> list);
    int  updateImportsExportsSpecies(Map map);

    int insert(ImportsExportsSpecies im);
    int  del(String id);
    void save1(ImportsExportsSpecies im);
  List<ImportsExportsSpecies>   selectBySourceId(String id);

    void importsExportsSpeWirte(List<ImportsExportsSpecies> list);

    /**
     *
     *
     */
    ImportExportCheck check(String impAuform, String speName);
}
