package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdpi.mapper.PeritoneumSpecMapper;
import com.sofn.fdpi.model.Peritoneum;
import com.sofn.fdpi.model.PeritoneumSpec;
import com.sofn.fdpi.service.PeritoneumSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-24 14:39
 */
@Service("peritoneumSpecService")
public class PeritoneumSpecServiceImpl implements PeritoneumSpecService {
    @Autowired
    private PeritoneumSpecMapper peritoneumSpecMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<PeritoneumSpec> ps,String peritoneumId) {
        if (!CollectionUtils.isEmpty(ps)){
            for (PeritoneumSpec p:
                 ps) {
                p.setId(IdUtil.getUUId());
                p.setPeritoneumId(peritoneumId);
                peritoneumSpecMapper.insert(p);
            }
        }
    }

    @Override
    public void del(String peritoneumId) {
        peritoneumSpecMapper.delByperitoneumId(peritoneumId);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(List<PeritoneumSpec> ps, String peritoneumId) {
//        先删除所有物种信息
        del(peritoneumId);
//        再将现在拥有的物种信息保存
        save(ps,peritoneumId);
    }

    @Override
    public List<PeritoneumSpec> get(String peritoneumId) {
        QueryWrapper<PeritoneumSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PERITONEUM_ID", peritoneumId);
        List<PeritoneumSpec> ps = peritoneumSpecMapper.selectList(queryWrapper);
        return ps;
    }
}
