package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.mapper.PeritoneumMapper;

import com.sofn.fdpi.model.Peritoneum;
import com.sofn.fdpi.model.PeritoneumSpec;
import com.sofn.fdpi.service.PeritoneumService;
import com.sofn.fdpi.service.PeritoneumSpecService;

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
 * @Date: 2020-06-24 14:28
 */
@Service("peritoneumService")
public class PeritoneumServiceImpl implements PeritoneumService {
    @Resource
    private PeritoneumMapper peritoneumMapper;
    @Resource
    private PeritoneumSpecService peritoneumSpecService;
    @Resource
    private TbDepartmentService tbDepartmentService;

    /**
     * c插入
     *
     * @param peritoneum
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Peritoneum peritoneum) {
        RedisUserUtil.validReSubmit("fdpi_peritoneum_save");
        QueryWrapper<Peritoneum> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CERTIFICATE_NO", peritoneum.getCertificateNo());
        List<Peritoneum> peritoneums = peritoneumMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(peritoneums)) {
            throw new SofnException("证号已存在");
        }
//        设置主键
        peritoneum.setId(IdUtil.getUUId());
        peritoneum.setCreateTime(new Date());
//        插入基础信息
        peritoneumMapper.insert(peritoneum);
        List<PeritoneumSpec> ps = peritoneum.getPs();
//        插入物种信息
        peritoneumSpecService.save(ps, peritoneum.getId());
    }

    /**
     * x修改
     *
     * @param peritoneum 濒管办对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Peritoneum peritoneum) {
        RedisUserUtil.validReSubmit("fdpi_peritoneum_update");
        Peritoneum peritoneumOld = peritoneumMapper.selectById(peritoneum.getId());
        if (peritoneumOld == null) {
            throw new SofnException("该证书不存在");
        }
        QueryWrapper<Peritoneum> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CERTIFICATE_NO", peritoneum.getCertificateNo()).ne("ID", peritoneum.getId());
        Peritoneum peritoneumRepeat = peritoneumMapper.selectOne(queryWrapper);
        if (peritoneumRepeat != null) {
            throw new SofnException("该证号已存在");
        }
//        修改基础信息
        List<PeritoneumSpec> newps = peritoneum.getPs();
        peritoneum.setCreateTime(peritoneumOld.getCreateTime());
        peritoneumMapper.updateById(peritoneum);
//        修改物种信息
        peritoneumSpecService.update(newps, peritoneum.getId());
    }

    /**
     * @param id 主键id
     * @return 濒管办信息
     */
    @Override
    public Peritoneum get(String id) {
        Peritoneum peritoneum = peritoneumMapper.selectById(id);
        List<PeritoneumSpec> peritoneumSpecs = peritoneumSpecService.get(id);
        peritoneum.setPs(peritoneumSpecs);
        return peritoneum;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(String id) {
//        删除基础信息
        peritoneumMapper.deleteById(id);
//        删除物种信息
        peritoneumSpecService.del(id);
    }

    /**
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Peritoneum> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Peritoneum> peritoneums = peritoneumMapper.listParam(params);
        if (CollectionUtils.isEmpty(peritoneums)) {
            Boolean canHandle = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_RECORD.getKey());
            for (Peritoneum p : peritoneums) {
                p.setCanHandle(canHandle);
            }
        }
        PageInfo<Peritoneum> pageInfo = new PageInfo<>(peritoneums);
        return PageUtils.getPageUtils(pageInfo);
    }
}
