package com.sofn.fdpi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.Query;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.enums.SignboardApplyProcessEnum;
import com.sofn.fdpi.enums.SignboardApplyTypeEnum;
import com.sofn.fdpi.mapper.AuditProcessMapper;
import com.sofn.fdpi.mapper.PapersMapper;
import com.sofn.fdpi.model.AuditProcess;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.service.AuditProcessService;
import com.sofn.fdpi.sysapi.bean.ActContextVo;
import com.sofn.fdpi.sysapi.bean.ActivityDataParamsVo;
import com.sofn.fdpi.sysapi.bean.ActivityDataVo;
import com.sofn.fdpi.util.WorkUtil;
import com.sofn.fdpi.vo.AuditProcessVo;
import com.sofn.fdpi.vo.PapersProcessVo;
import com.sofn.fdpi.vo.SignboardProcessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service("auditProcessService")
public class AuditProcessServiceImpl extends ServiceImpl<AuditProcessMapper, AuditProcess> implements AuditProcessService {
    @Autowired
    private AuditProcessMapper auditProcessMapper;

    @Autowired
    private PapersMapper papersMapper;

    //流程id
    private final static String defId = "fdpi_paper_binding:fdpi_paper_binding";

    //业务数据名称
    private final static String idAttrName = "dataId";

    /**
     * 证书绑定进度查询
     * wuXY
     * 2020-1-2 19:01:30
     */
    @Override
    public PageUtils<PapersProcessVo> listForBindingSpeed(Map<String, Object> map) {
        PageHelper.offsetPage(Integer.parseInt(map.get("pageNo").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<PapersProcessVo> papersProcessVoList = auditProcessMapper.listForCondition(map);
        PageInfo<PapersProcessVo> pageInfo = new PageInfo<>(papersProcessVoList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public PageUtils<PapersProcessVo> listForBindingSpeedByInfo(Map<String, Object> paramMap) {
        List<PapersProcessVo> papersProcessVos = auditProcessMapper.listForConditionByInfo(paramMap);
        Map<String, PapersProcessVo> map = papersProcessVos.stream().collect(Collectors.toMap(PapersProcessVo::getPapersId, a -> a, (k1, k2) -> k1));
        //业务数据Id列表
        List<String> dataIds = papersProcessVos.stream().map(PapersProcessVo::getPapersId).collect(Collectors.toList());
        //封闭查询参数
        ActivityDataParamsVo activityDataParamsVo = ActivityDataParamsVo.getInstance(defId, idAttrName,
                dataIds, paramMap, Integer.parseInt(paramMap.get("pageNo").toString()), Integer.parseInt(paramMap.get("pageSize").toString()));
        //流程组件返回结果
        PageUtils<ActivityDataVo> activityDataVoPageUtils = WorkUtil.getPageUtilsByParams(activityDataParamsVo);
        List<ActivityDataVo> activityDataVos = activityDataVoPageUtils.getList();
        //解析成需要的格式
        List<PapersProcessVo> papersProcessVos1 = this.activityDataVoByPapersProcessVo(activityDataVos, map);
        PageInfo<PapersProcessVo> papersProcessVoPageInfo = new PageInfo<>(papersProcessVos1);
        papersProcessVoPageInfo.setTotal(activityDataVoPageUtils.getTotalCount());
        papersProcessVoPageInfo.setPageSize(activityDataVoPageUtils.getPageSize());
        papersProcessVoPageInfo.setPageNum(activityDataVoPageUtils.getCurrPage());

        return PageUtils.getPageUtils(papersProcessVoPageInfo);
    }

    private List<PapersProcessVo> activityDataVoByPapersProcessVo(List<ActivityDataVo> activityDataVos, Map<String, PapersProcessVo> map) {
        List<PapersProcessVo> papersProcessVos = Collections.EMPTY_LIST;
        if (!CollectionUtils.isEmpty(activityDataVos)) {
            papersProcessVos = Lists.newArrayListWithCapacity(activityDataVos.size());
            for (ActivityDataVo activityDataVo : activityDataVos) {
                PapersProcessVo papersProcesVo = new PapersProcessVo();
                PapersProcessVo procesVo = map.get(activityDataVo.getUnitValue());
                papersProcesVo.setPapersNumber(procesVo.getPapersNumber());
                papersProcesVo.setAdvice(procesVo.getPapersNumber());
                papersProcesVo.setIssueSpe(procesVo.getIssueSpe());
                papersProcesVo.setApplyTime(procesVo.getApplyTime());
                papersProcesVo.setApplyNum(procesVo.getApplyNum());
                papersProcesVo.setId(IdUtil.getUUId());
                papersProcesVo.setPapersId(procesVo.getPapersId());
                List<ActContextVo> actContextVos = activityDataVo.getActContextVos();
                for (ActContextVo actContextVo : actContextVos) {
                    String dataFieldId = actContextVo.getDataFieldId();
                    String value = actContextVo.getValue();
                    if (StringUtils.hasText(value)) {
                        if ("status".equals(dataFieldId)) {
                            papersProcesVo.setParStatusName(DefaultAdviceEnum.getDefaultAdviceEnumByCode(value).getMsg());
                            papersProcesVo.setAdvice(DefaultAdviceEnum.getDefaultAdviceEnumByCode(value).getMsg());
                        } else if ("advice".equals(dataFieldId)) {
                            papersProcesVo.setAdvice(value);
                        } else if ("personName".equals(dataFieldId)) {
                            papersProcesVo.setPersonName(value);
                        }
                    }
                }
                papersProcesVo.setOperationTime(DateUtils.stringToDate(activityDataVo.getActivityCompleteTime(), DateUtils.DATE_TIME_PATTERN));
                papersProcessVos.add(papersProcesVo);
            }
        }
        return papersProcessVos;
    }

    /**
     * 获取证书的审核流程记录列表
     * wuXY
     * 2020-11-5 15:10:51
     *
     * @param papersId 证书id
     * @return
     */
    @Override
    public List<AuditProcessVo> listForAuditProcessByPapersId(String papersId) {
        if (papersId.contains(",")) {
            String[] split = papersId.split(",");
            papersId = split[0];
        }
        return auditProcessMapper.listForAuditProcessByPapersId(papersId);
    }

    @Override
    public List<AuditProcessVo> listForAuditProcessByPapersIdY(String papersId) {
        if (papersId.contains(",")) {
            String[] split = papersId.split(",");
            papersId = split[0];
        }
        //判断是否是绑定未上报
        QueryWrapper<Papers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", papersId);
        queryWrapper.eq("DEL_FLAG", "N");
        Papers papers = papersMapper.selectOne(queryWrapper);
        if (papers.getParStatus().equals(DefaultAdviceEnum.BINDING_NOT_REPORTED.getCode())) {
            return null;
        }

        ArrayList<AuditProcessVo> auditProcessVos = Lists.newArrayList();
        List<Map<String, Object>> changeRecordId = WorkUtil.getProcesslist(defId, idAttrName, papersId);
        changeRecordId.forEach(o -> {
            AuditProcessVo auditProcessVo = new AuditProcessVo();
            auditProcessVo.setAdvice(String.valueOf(o.get("advice")));
            auditProcessVo.setOperationTime((Date) o.get("createTime"));
            String status = String.valueOf(o.get("status"));
            auditProcessVo.setParStatus(status);
            auditProcessVo.setParStatusName(DefaultAdviceEnum.getDefaultAdviceEnumByCode(status).getMsg());
            auditProcessVo.setPersonName(String.valueOf(o.get("personName")));
            auditProcessVos.add(auditProcessVo);
        });
        return auditProcessVos;
    }

    @Override
    public void delByPapersId(String papersId) {
        QueryWrapper<AuditProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("papers_id", papersId);
        auditProcessMapper.delete(queryWrapper);
    }
}
