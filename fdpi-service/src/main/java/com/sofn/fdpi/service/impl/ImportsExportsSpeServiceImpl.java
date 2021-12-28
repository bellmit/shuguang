package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.sofn.common.service.BaseService;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdpi.mapper.ImportsExportsSpeciesMapper;
import com.sofn.fdpi.model.ImportsExportsSpecies;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.service.ImportsExportsSpeService;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.vo.ImportExportCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-03 16:00
 */
@Slf4j
@Service("ImportsExportsSpeService")
public class ImportsExportsSpeServiceImpl extends BaseService<ImportsExportsSpeciesMapper,ImportsExportsSpecies> implements ImportsExportsSpeService {
    @Autowired
    private ImportsExportsSpeciesMapper iMapper;
    @Autowired
    private SpeService speService;

    @Override
    public int addImportsExportsSpe(List<ImportsExportsSpecies> list) {
        return iMapper.addList(list);
    }

    @Override
    public int updateImportsExportsSpecies(Map map) {
        return iMapper.updateImportsExportsSpecies(map);
    }

    @Override
    public int insert(ImportsExportsSpecies im) {
        return iMapper.insert1(im);
    }

    @Override
    public int del(String id) {
        return iMapper.del(id);
    }

    @Override
    public void save1(ImportsExportsSpecies im) {
        QueryWrapper<ImportsExportsSpecies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EXPORTS_ID", im.getExportsId())
                .eq("SPE_NAME", im.getSpeName())
                .eq("PRO_LEVEL", im.getProLevel());
        ImportsExportsSpecies papersSpec1 = iMapper.selectOne(queryWrapper);
        if (papersSpec1 != null) {
            papersSpec1.setAmount(im.getAmount());
            papersSpec1.setPort(im.getPort());
            papersSpec1.setSource(im.getSource());
            iMapper.updateById(papersSpec1);
        } else {
            ImportsExportsSpecies bs = new ImportsExportsSpecies();
            BeanUtils.copyProperties(im, bs);
            bs.setId(IdUtil.getUUId());
            iMapper.insert(bs);
        }

    }

    @Override
    public List<ImportsExportsSpecies> selectBySourceId(String id) {
        QueryWrapper<ImportsExportsSpecies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EXPORTS_ID", id);
        List<ImportsExportsSpecies> importsExportsSpeciesList = iMapper.selectList(queryWrapper);
        for (ImportsExportsSpecies im :
                importsExportsSpeciesList) {
            Spe speBySpeId = speService.getSpeBySpeId(im.getSpeName());
            if (speBySpeId != null) {
                im.setSpeName(speBySpeId.getSpeName());
            }

        }
        return importsExportsSpeciesList;
    }

    @Override
//    @Async("asyncServiceExecutor")
    public void importsExportsSpeWirte(List<ImportsExportsSpecies> list) {
            this.saveBatch(list,3000);
    }

    @Override
    public ImportExportCheck check(String impAuform, String speName) {
        return iMapper.check(impAuform,speName);
    }

}
