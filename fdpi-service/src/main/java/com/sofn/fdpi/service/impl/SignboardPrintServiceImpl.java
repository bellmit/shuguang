package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.enums.PrintStatusEnum;
import com.sofn.fdpi.enums.SturgeonStatusDomesticEnum;
import com.sofn.fdpi.mapper.SignboardPrintMapper;
import com.sofn.fdpi.model.SignboardPrint;
import com.sofn.fdpi.model.SturgeonProcess;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.vo.SignboardPrintForm;
import com.sofn.fdpi.vo.SignboardPrintVo;
import com.sofn.fdpi.vo.SturgeonSubVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 标识打印服务类
 *
 * @Author yumao
 * @Date 2020/1/6 16:26
 **/
@Service(value = "signboardPrintService")
@Slf4j
public class SignboardPrintServiceImpl extends BaseService<SignboardPrintMapper, SignboardPrint> implements SignboardPrintService {

    @Resource
    private SignboardPrintMapper signboardPrintMapper;

    @Resource
    @Lazy
    private SturgeonService sturgeonService;

    @Resource
    private SturgeonSignboardDomesticService ssdService;

    @Resource
    private SturgeonSubService sturgeonSubService;

    @Resource
    private SturgeonProcessService sturgeonProcessService;

    /**
     * 新增标识打印
     */
    @Override
    @Transactional
    public SignboardPrint insertSignboardPrint(SignboardPrintForm signboardPrintForm, String createUserId) {
        SignboardPrint entity = new SignboardPrint();
        BeanUtils.copyProperties(signboardPrintForm, entity);
        entity.setUpdateUserId(createUserId);
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setDelFlag(BoolUtils.N);
        entity.setEntrustDate(entity.getCreateTime());
        if (StringUtils.isEmpty(entity.getApplyType())) {
            entity.setApplyType("1");
        }
        entity.setStatus(PrintStatusEnum.NOT_PRINTED.getKey());
        signboardPrintMapper.insert(entity);
        return entity;
    }

    /**
     * 分页查询标识打印
     */
    @Override
    public PageUtils<SignboardPrintVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        if (Objects.isNull(params.get("applyType"))) {
            params.put("applyType", "1");
        }
        List<SignboardPrint> list = signboardPrintMapper.listByParams(params);
        PageInfo<SignboardPrint> spPageInfo = new PageInfo<>(list);
        if (!CollectionUtils.isEmpty(list)) {
            List<SignboardPrintVo> listVo = new ArrayList<>(list.size());
            for (SignboardPrint entity : list) {
                listVo.add(SignboardPrintVo.entity2Vo(entity));
            }
            PageInfo<SignboardPrintVo> spvPageInfo = new PageInfo<>(listVo);
            spvPageInfo.setTotal(spPageInfo.getTotal());
            spvPageInfo.setPageSize(pageSize);
            spvPageInfo.setPageNum(spPageInfo.getPageNum());
            return PageUtils.getPageUtils(spvPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    /**
     * 根据打印ID查看企业名称
     */
    @Override
    public String getCompNameByPrintId(String id) {
        return signboardPrintMapper.getCompNameByPrintId(id);
    }

    /**
     * 更新打印状态为已打印
     */
    @Override
    public SignboardPrint updateStatusByPrintId(String id) {
        SignboardPrint entity = signboardPrintMapper.selectById(id);
        entity.setStatus(PrintStatusEnum.ALREADY_PRINTED.getKey());
        entity.preUpdate();
        signboardPrintMapper.updateById(entity);
        return entity;
    }

    @Override
    public String getYearMaxSequenceNum(String contractNum) {
        return signboardPrintMapper.getYearMaxSequenceNum(contractNum);
    }

    @Override
    public void updateSignboardPrint(String id, String contractNum) {
        SignboardPrint sp = new SignboardPrint();
        sp.setId(id);
        sp.setContractNum(contractNum);
        sp.setMakeTime(new Date());
        signboardPrintMapper.updateById(sp);
    }

    @Override
    public List<SignboardPrintVo> listByApplyId(String applyId) {
        QueryWrapper<SignboardPrint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply_id", applyId);
        List<SignboardPrint> sps = signboardPrintMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(sps)) {
            List<SignboardPrintVo> spvs = Lists.newArrayListWithCapacity(sps.size());
            for (SignboardPrint sp : sps) {
                spvs.add(SignboardPrintVo.entity2Vo(sp));
            }
            return spvs;
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void print(String printId) {
        SignboardPrint sp = signboardPrintMapper.selectById(printId);
        String applyId = sp.getApplyId();
        List<SturgeonSubVo> sturgeonSubVos = sturgeonSubService.listBySturgeonId(applyId);
        if (!CollectionUtils.isEmpty(sturgeonSubVos)) {
            for (SturgeonSubVo ssv : sturgeonSubVos) {
                ssdService.updatePrintStatusBySturgeonSubId(ssv.getId());
            }
        }
        sp.preUpdate();
        sp.setStatus("1");
        signboardPrintMapper.updateById(sp);
        String sturgeonId = sp.getApplyId();
        //改变流程状态
        sturgeonService.updateProcessStatus(sturgeonId, SturgeonStatusDomesticEnum.PRINT.getKey());

        //增加流程纪录
        SturgeonProcess sturgeonProcess = new SturgeonProcess();
        sturgeonProcess.setApplyId(sturgeonId);
        sturgeonProcess.setStatus(SturgeonStatusDomesticEnum.PRINT.getKey());
        sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<SignboardPrint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return signboardPrintMapper.delete(queryWrapper);
    }


}
