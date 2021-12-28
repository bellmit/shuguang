package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.mapper.PapersYearInspectMapper;
import com.sofn.fdpi.mapper.PapersYearInspectProcessMapper;
import com.sofn.fdpi.model.PapersYearInspect;
import com.sofn.fdpi.model.PapersYearInspectProcess;
import com.sofn.fdpi.model.SignboardProcess;
import com.sofn.fdpi.service.PapersYearInspectProcessService;
import com.sofn.fdpi.sysapi.bean.ActContextVo;
import com.sofn.fdpi.sysapi.bean.ActivityDataParamsVo;
import com.sofn.fdpi.sysapi.bean.ActivityDataVo;
import com.sofn.fdpi.util.WorkUtil;
import com.sofn.fdpi.vo.AuditProcessVo;
import com.sofn.fdpi.vo.PapersProcessVo;
import com.sofn.fdpi.vo.PapersYearInspectViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service("papersYearInspectProcessService")
public class PapersYearInspectProcessServiceImpl extends BaseService<PapersYearInspectProcessMapper, PapersYearInspectProcess> implements PapersYearInspectProcessService {
    @Autowired
    private PapersYearInspectProcessMapper papersYearInspectProcessMapper;

    @Autowired
    private PapersYearInspectMapper papersYearInspectMapper;

    //流程id
    private final static String defId = "papers_year_inspect:papers_year_inspect";

    //业务数据名称
    private final static String idAttrName = "dataId";

    /**
     * 获取证书年审记录列表（分页）
     * wuXY
     * 2020-1-7 16:17:31
     *
     * @param map 查询参数
     * @return PageUtils<PapersYearInspectProcess>
     */
    @Override
    public PageUtils<PapersYearInspectProcess> listByPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {

        PageHelper.offsetPage(pageNo, pageSize);
        List<PapersYearInspectProcess> processList = papersYearInspectProcessMapper.listByCondition(map);
        PageInfo<PapersYearInspectProcess> pageInfo = new PageInfo<>(processList);

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public PageUtils<PapersYearInspectProcess> listByPageInfo(Map<String, Object> paramMap, Integer pageNo, Integer pageSize) {
        List<PapersYearInspectProcess> processList = papersYearInspectProcessMapper.listByConditionByInfo(paramMap);
        Map<String, PapersYearInspectProcess> map = processList.stream().collect(Collectors.toMap(PapersYearInspectProcess::getId, a -> a, (k1, k2) -> k1));
        //业务数据Id列表
        List<String> dataIds = processList.stream().map(PapersYearInspectProcess::getId).collect(Collectors.toList());

        //封闭查询参数
        ActivityDataParamsVo activityDataParamsVo = ActivityDataParamsVo.getInstance(defId, idAttrName,
                dataIds, paramMap, pageNo, pageSize);
        //流程组件返回结果
        PageUtils<ActivityDataVo> activityDataVoPageUtils = WorkUtil.getPageUtilsByParams(activityDataParamsVo);
        List<ActivityDataVo> activityDataVos = activityDataVoPageUtils.getList();
        //解析成需要的格式
        List<PapersYearInspectProcess> papersProcessVos1 = this.activityDataVoByPapersYearInspectProcess(activityDataVos, map);
        PageInfo<PapersYearInspectProcess> papersProcessVoPageInfo = new PageInfo<>(papersProcessVos1);
        papersProcessVoPageInfo.setTotal(activityDataVoPageUtils.getTotalCount());
        papersProcessVoPageInfo.setPageSize(activityDataVoPageUtils.getPageSize());
        papersProcessVoPageInfo.setPageNum(activityDataVoPageUtils.getCurrPage());
        return PageUtils.getPageUtils(papersProcessVoPageInfo);
    }

    private List<PapersYearInspectProcess> activityDataVoByPapersYearInspectProcess(List<ActivityDataVo> activityDataVos, Map<String, PapersYearInspectProcess> map) {
        List<PapersYearInspectProcess> papersYearInspectProcessList = Collections.EMPTY_LIST;
        if (!CollectionUtils.isEmpty(activityDataVos)) {
            papersYearInspectProcessList = Lists.newArrayListWithCapacity(activityDataVos.size());
            for (ActivityDataVo activityDataVo : activityDataVos) {
                PapersYearInspectProcess papersYearInspectProcess = new PapersYearInspectProcess();
                PapersYearInspectProcess process = map.get(activityDataVo.getUnitValue());
                papersYearInspectProcess.setYear(process.getYear());
                papersYearInspectProcess.setId(IdUtil.getUUId());
                papersYearInspectProcess.setApplyNum(process.getApplyNum());
                List<ActContextVo> actContextVos = activityDataVo.getActContextVos();
                for (ActContextVo actContextVo : actContextVos) {
                    String dataFieldId = actContextVo.getDataFieldId();
                    String value = actContextVo.getValue();
                    if (StringUtils.hasText(value)) {
                        if ("status".equals(dataFieldId)) {
                            papersYearInspectProcess.setStatus(value);
                            papersYearInspectProcess.setStatusName(DefaultAdviceEnum.getDefaultAdviceEnumByCode(value).getMsg());
                        } else if ("advice".equals(dataFieldId)) {
                            papersYearInspectProcess.setAdvice(value);
                        } else if ("personName".equals(dataFieldId)) {
                            papersYearInspectProcess.setPersonName(value);
                        }
                    }
                }
                papersYearInspectProcess.setConTime(DateUtils.stringToDate(activityDataVo.getActivityCompleteTime(), DateUtils.DATE_TIME_PATTERN));
                papersYearInspectProcessList.add(papersYearInspectProcess);
            }
        }

        return papersYearInspectProcessList;
    }

    /**
     * 获取证书年审的流程记录列表
     *
     * @param inspectId 证书年审id
     * @return 记录列表
     */
    @Override
    public List<AuditProcessVo> listForAuditProcessByInspectId(String inspectId) {
        return papersYearInspectProcessMapper.listForAuditProcessByInspectId(inspectId);
    }

    @Override
    public List<AuditProcessVo> listForAuditProcessByInspectIdY(String inspectId) {
        //判断是否是新增 还未上报
        QueryWrapper<PapersYearInspect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", inspectId);
        queryWrapper.eq("DEL_FLAG", "N");
        PapersYearInspect papersYearInspect = papersYearInspectMapper.selectOne(queryWrapper);
        if (papersYearInspect.getStatus().equals(DefaultAdviceEnum.BINDING_NOT_REPORTED.getCode())) {
            return null;
        }

        ArrayList<AuditProcessVo> auditProcessVos = Lists.newArrayList();
        List<Map<String, Object>> changeRecordId = WorkUtil.getProcesslist(defId, idAttrName, inspectId);
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
    public int delByYearInspectIds(List<String> papersYearInspectIds) {
        if (!CollectionUtils.isEmpty(papersYearInspectIds)) {
            QueryWrapper<PapersYearInspectProcess> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("papers_year_inspect_id", papersYearInspectIds);
            return papersYearInspectProcessMapper.delete(queryWrapper);
        }
        return 0;
    }
}
