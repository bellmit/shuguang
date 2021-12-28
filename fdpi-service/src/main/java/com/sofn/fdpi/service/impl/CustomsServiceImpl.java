package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.mapper.CustomsMapper;
import com.sofn.fdpi.model.Customs;
import com.sofn.fdpi.model.CustomsSpec;
import com.sofn.fdpi.service.CustomsService;
import com.sofn.fdpi.service.CustomsSpecService;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.util.RedisUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 15:09
 */
@Service("customsService")
public class CustomsServiceImpl implements CustomsService {
    @Resource
    private CustomsMapper customsMapper;
    @Resource
    private CustomsSpecService customsSpecService;
    @Resource
    private TbDepartmentService tbDepartmentService;

    /**
     * c插入
     *
     * @param customs 海关对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Customs customs) {
        RedisUserUtil.validReSubmit("fdpi_customs_save");
        QueryWrapper<Customs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CUSTOMS_NUMBER", customs.getCustomsNumber());
        List<Customs> peritoneums = customsMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(peritoneums)) {
            throw new SofnException("报告单编号已存在");
        }
//        设置主键
        customs.setId(IdUtil.getUUId());
        customs.setCreateTime(new Date());
//        插入基础信息
        customsMapper.insert(customs);
        List<CustomsSpec> ps = customs.getPs();
//        插入物种信息
        customsSpecService.save(ps, customs.getId());
    }

    /**
     * x修改
     *
     * @param customs 海关对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Customs customs) {
        RedisUserUtil.validReSubmit("fdpi_customs_update");
        Customs customs1 = customsMapper.selectById(customs.getId());
        if (customs1 == null) {
            throw new SofnException("该报告单不存在");
        }
        QueryWrapper<Customs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CUSTOMS_NUMBER", customs.getCustomsNumber()).ne("ID", customs.getId());
        Customs customsRepeat = customsMapper.selectOne(queryWrapper);
        if (customsRepeat != null) {
            throw new SofnException("该报告单已存在");
        }
//        修改基础信息
        customs.setCreateTime(customs1.getCreateTime());
        customsMapper.updateById(customs);
        List<CustomsSpec> ps = customs.getPs();
        customsSpecService.update(ps, customs.getId());
    }

    /**
     * @param id 主键id
     * @return 濒管办信息
     */
    @Override
    public Customs get(String id) {
        Customs customs = customsMapper.selectById(id);
        List<CustomsSpec> customsSpecs = customsSpecService.get(id);
        customs.setPs(customsSpecs);
        return customs;
    }

    /**
     * @param id 主键id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(String id) {
        customsMapper.deleteById(id);
        customsSpecService.del(id);
    }

    /**
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Customs> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Customs> customsList = customsMapper.listParam(params);
        if (!CollectionUtils.isEmpty(customsList)) {
            Boolean canHandle = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_RECORD.getKey());
            for (Customs c : customsList) {
                c.setCanHandle(canHandle);
            }
        }
        PageInfo<Customs> pageInfo = new PageInfo<>(customsList);
        return PageUtils.getPageUtils(pageInfo);
    }
}
