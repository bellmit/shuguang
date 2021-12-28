package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.mapper.SignboardChangeRecordMapper;
import com.sofn.fdpi.model.SignboardChangeRecord;
import com.sofn.fdpi.service.SignboardChangeRecordService;
import com.sofn.fdpi.vo.SignboardChangeRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Deacription 标识变更服务类
 * @Author yumao
 * @Date 2020/1/6 11:48
 **/
@Service(value = "signboardChangeRecordService")
@Slf4j
public class SignboardChangeRecordServiceImpl implements SignboardChangeRecordService {

    @Autowired
    private SignboardChangeRecordMapper scrMapper;

    @Override
    public SignboardChangeRecord insertSignboardChangeRecord(SignboardChangeRecordVo vo) {
        SignboardChangeRecord entity = new SignboardChangeRecord();
        BeanUtils.copyProperties(vo, entity);
        entity.setId(IdUtil.getUUId());
        entity.setChangeTime(new Date());
        scrMapper.insert(entity);
        return entity;
    }

    @Override
    public PageUtils<SignboardChangeRecordVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {

        PageHelper.offsetPage(pageNo, pageSize);
        List<SignboardChangeRecord> list = scrMapper.listByParams(params);
        PageInfo<SignboardChangeRecord> scrPageInfo = new PageInfo<>(list);
        if (!CollectionUtils.isEmpty(list)) {
            List<SignboardChangeRecordVo> listVo = new ArrayList<>(list.size());
            for (SignboardChangeRecord entity : list) {
                listVo.add(SignboardChangeRecordVo.entity2Vo(entity));
            }
            PageInfo<SignboardChangeRecordVo> scrvPageInfo = new PageInfo<>(listVo);
            scrvPageInfo.setTotal(scrPageInfo.getTotal());
            scrvPageInfo.setPageSize(pageSize);
            scrvPageInfo.setPageNum(scrPageInfo.getPageNum());
            return PageUtils.getPageUtils(scrvPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<SignboardChangeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return scrMapper.delete(queryWrapper);
    }
}
