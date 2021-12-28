package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdpi.mapper.CustomsSpecMapper;
import com.sofn.fdpi.model.CustomsSpec;
import com.sofn.fdpi.model.PeritoneumSpec;
import com.sofn.fdpi.service.CustomsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 15:11
 */
@Service("customsSpecService")
public class CustomsSpecServiceImpl implements CustomsSpecService {
    @Autowired
    private CustomsSpecMapper customsSpecMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<CustomsSpec> ps, String customsId) {
        if (!CollectionUtils.isEmpty(ps)) {
            for (CustomsSpec p :
                    ps) {
                p.setId(IdUtil.getUUId());
                p.setCustomsId(customsId);
                customsSpecMapper.insert(p);
            }
        }
    }

    @Override
    public void del(String customsId) {
        customsSpecMapper.delBycustomsId(customsId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(List<CustomsSpec> ps, String customsId) {
//        先删除所有物种信息
        del(customsId);
//        再将现在拥有的物种信息保存
        save(ps, customsId);
    }

    @Override
    public List<CustomsSpec> get(String customsId) {
        QueryWrapper<CustomsSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CUSTOMS_ID", customsId);
        List<CustomsSpec> ps = customsSpecMapper.selectList(queryWrapper);
        return ps;
    }
}
