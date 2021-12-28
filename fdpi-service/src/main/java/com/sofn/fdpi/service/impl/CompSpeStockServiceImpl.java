package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.mapper.CompSpeStockMapper;
import com.sofn.fdpi.model.CompSpeStock;
import com.sofn.fdpi.service.CompSpeStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企业物种库存服务类
 */
@Service(value = "compSpeStockService")
@Slf4j
public class CompSpeStockServiceImpl implements CompSpeStockService {

    @Autowired
    private CompSpeStockMapper compSpeStockMapper;

    /**
     * 根据物种ID和企业ID物种库存
     */
    @Override
    public CompSpeStock getBySpeIdAndCompId(String speId, String compId) {
        if (StringUtils.isBlank(compId)) {
            compId = UserUtil.getLoginUserOrganizationId();
        }
        QueryWrapper<CompSpeStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("SPECIES_ID", speId)
                .eq("COMP_ID", compId);
        return compSpeStockMapper.selectOne(queryWrapper);
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<CompSpeStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return compSpeStockMapper.delete(queryWrapper);
    }
}
