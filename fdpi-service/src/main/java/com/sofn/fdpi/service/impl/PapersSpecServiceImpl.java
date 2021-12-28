package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.service.BaseService;
import com.sofn.fdpi.mapper.PapersSpecMapper;
import com.sofn.fdpi.model.PapersSpec;
import com.sofn.fdpi.model.SignboardApplyList;
import com.sofn.fdpi.service.PapersSpecService;
import com.sofn.fdpi.vo.PapersSpecForm;
import com.sofn.fdpi.vo.PapersSpecVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-23 10:23
 */
@Service("papersSpecService")
public class PapersSpecServiceImpl extends BaseService<PapersSpecMapper, PapersSpec> implements PapersSpecService {
    @Autowired
    private PapersSpecMapper papersSpecMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<PapersSpecForm> papersSpecList, String papersId) {
        if (!CollectionUtils.isEmpty(papersSpecList)) {
            for (PapersSpecForm p :
                    papersSpecList) {
                PapersSpec papersSpec = new PapersSpec();
                BeanUtils.copyProperties(p, papersSpec);
                papersSpec.preInsert();
                papersSpec.setPapersId(papersId);
                papersSpecMapper.insert(papersSpec);
            }
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSpec(PapersSpecForm papersSpecForm) {
        QueryWrapper<PapersSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PAPERS_ID", papersSpecForm.getPapersId())
                .eq("SPEC_ID", papersSpecForm.getSpecId())
                .eq("DEL_FLAG", "N");
        PapersSpec papersSpec1 = papersSpecMapper.selectOne(queryWrapper);
        if (papersSpec1 != null) {
            papersSpec1.setAmount(papersSpecForm.getAmount());
            papersSpec1.setSource(papersSpecForm.getSource());
            papersSpec1.preUpdate();
            papersSpecMapper.updateById(papersSpec1);
        } else {
            PapersSpec papersSpec = new PapersSpec();
            BeanUtils.copyProperties(papersSpecForm, papersSpec);
            papersSpec.preInsert();
            papersSpecMapper.insert(papersSpec);
        }

    }

    /**
     * 根据证书id 逻辑删除证书物种信息
     *
     * @param papersId 证书id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(String papersId) {
        papersSpecMapper.delByPapersId(papersId);
    }

    /**
     * 通过证书id 获取证书物种信息
     *
     * @param papersId 证书id
     * @return
     */
    @Override
    public List<PapersSpec> getBypapersId(String papersId) {
        QueryWrapper<PapersSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PAPERS_ID", papersId).eq("DEL_FLAG", "N");
        List<PapersSpec> papersSpecs = papersSpecMapper.selectList(queryWrapper);
        return papersSpecs;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(List<PapersSpecForm> papersSpecList, String papersId) {
        del(papersId);
        save(papersSpecList, papersId);
    }

    /**
     * 通过证书id，获取物种列表
     * wuXY
     * 2020-6-24 16:05:08
     *
     * @param papersId 证书编号
     * @return List<PapersSpecVo>
     */
    @Override
    public List<PapersSpecVo> listForCondition(String papersId) {
        return papersSpecMapper.listForCondition(papersId);
    }

    @Override
    public List<PapersSpecVo> listByPapersIds(List<String> papersIdList) {
        return papersSpecMapper.listByPapersIds(papersIdList);
    }

    @Override
    public PapersSpec checkPapersSpe(String papersId, String speId) {
        return papersSpecMapper.checkPapersSpe(papersId, speId);
    }

    @Override
    public int delByPaperIds(List<String> papersIds) {
        if (!CollectionUtils.isEmpty(papersIds)) {
            QueryWrapper<PapersSpec> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("papers_id", papersIds);
            return papersSpecMapper.delete(queryWrapper);
        }
        return 0;
    }
}
