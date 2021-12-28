package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.SignboardApplyProcessEnum;
import com.sofn.fdpi.enums.SignboardApplyTypeEnum;
import com.sofn.fdpi.mapper.SignboardProcessMapper;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.model.SignboardProcess;
import com.sofn.fdpi.service.SignboardApplyService;
import com.sofn.fdpi.service.SignboardProcessService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.ActContextVo;
import com.sofn.fdpi.sysapi.bean.ActivityDataParamsVo;
import com.sofn.fdpi.sysapi.bean.ActivityDataVo;
import com.sofn.fdpi.util.WorkUtil;
import com.sofn.fdpi.vo.SignboardProcessForm;
import com.sofn.fdpi.vo.SignboardProcessVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 标识流程服务类
 *
 * @Author yumao
 * @Date 2019/12/31 9:16
 **/
@Service(value = "signboardProcessService")
@Slf4j
public class SignboardProcessServiceImpl extends BaseService<SignboardProcessMapper, SignboardProcess> implements SignboardProcessService {


    private final String DEF_ID = "fdpi:signboard";

    private final String ID_ATTR_NAME = "dataId";

    @Resource
    private SignboardProcessMapper spMapper;

    @Resource
    private SysRegionApi sysRegionApi;

    @Resource
    @Lazy
    private SignboardApplyService signboardApplyService;

    /**
     * 新增流程
     */
    @Override
    public SignboardProcess insertSignboardProcess(SignboardProcessForm form) {
//        //验证是否重复操作终审通过
//        if (SignboardApplyProcessEnum.FINAL_AUDIT.getKey().equals(form.getStatus())) {
//            QueryWrapper<SignboardProcess> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("STATUS", SignboardApplyProcessEnum.FINAL_AUDIT.getKey())
//                    .eq("APPLY_ID", form.getApplyId());
//            if (!CollectionUtils.isEmpty(spMapper.selectList(queryWrapper))) {
//                throw new SofnException("已经终审或者最在终审生成标识，不可重复操作！");
//            }
//        }
        SignboardProcess entity = new SignboardProcess();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        entity.setPerson(this.getCurrentPerson(form.getStatus()));
        entity.setConTime(entity.getCreateTime());
        spMapper.insert(entity);
        return entity;
    }

    /**
     * 获取当前用户/单位名称
     */
    private String getCurrentPerson(String processStatus) {
        String person = "";
        if (SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus) ||
                SignboardApplyProcessEnum.CANCEL.getKey().equals(processStatus)) {
            person = UserUtil.getLoginUser().getNickname();
        } else {
            String organizationId = UserUtil.getLoginUserOrganizationId();
            Result<List<Map<String, String>>> result = sysRegionApi.getInfoByCondition(organizationId, null, null, null);
            if (!Result.CODE.equals(result.getCode())) {
                throw new SofnException("系统当前用户组织机构失败!");
            }
            List<Map<String, String>> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                person = list.get(0).get("orgname");
            }
        }
        return person;
    }

    /**
     * 分页查询流程
     */
    @Override
    public PageUtils<SignboardProcessVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        return Constants.WORKFLOW.equals(BoolUtils.N) ? this.listPageByLocal(params, pageNo, pageSize) :
                this.listPageByInfor(params, pageNo, pageSize);
    }

    /**
     * 分页查询流程（数据来源本系统）
     */
    public PageUtils<SignboardProcessVo> listPageByLocal(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        if (Objects.isNull(params.get("compId"))) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        List<SignboardProcess> list = spMapper.listByParams(params);
        PageInfo<SignboardProcess> spPageInfo = new PageInfo<>(list);
        List<SignboardProcessVo> listVo = this.listSignboardProcessVo(list);
        if (!CollectionUtils.isEmpty(listVo)) {
            PageInfo<SignboardProcessVo> spvPageInfo = new PageInfo<>(listVo);
            spvPageInfo.setTotal(spPageInfo.getTotal());
            spvPageInfo.setPageSize(pageSize);
            spvPageInfo.setPageNum(spPageInfo.getPageNum());
            return PageUtils.getPageUtils(spvPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    /**
     * 分页查询流程（数据来源流程组件）
     */
    public PageUtils<SignboardProcessVo> listPageByInfor(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        List<SignboardApply> list = signboardApplyService.listByParams(params);
        Map<String, SignboardApply> map = list.stream().collect(Collectors.toMap(SignboardApply::getId, a -> a, (k1, k2) -> k1));
        //业务数据Id列表
        List<String> dataIds = list.stream().map(SignboardApply::getId).collect(Collectors.toList());
        //封闭查询参数
        ActivityDataParamsVo activityDataParamsVo = ActivityDataParamsVo.getInstance(DEF_ID, ID_ATTR_NAME,
                dataIds, params, pageNo, pageSize);
        //流程组件返回结果

        PageUtils<ActivityDataVo> activityDataVoPageUtils = WorkUtil.getPageUtilsByParams(activityDataParamsVo);
        List<ActivityDataVo> activityDataVos = activityDataVoPageUtils.getList();

        //解析成需要的格式
        List<SignboardProcessVo> signboardProcessVos = this.activityDataVos2SignboardProcessVo(activityDataVos, map);
        PageInfo<SignboardProcessVo> signboardProcessVoPageInfo = new PageInfo<>(signboardProcessVos);
        signboardProcessVoPageInfo.setTotal(activityDataVoPageUtils.getTotalCount());
        signboardProcessVoPageInfo.setPageSize(activityDataVoPageUtils.getPageSize());
        signboardProcessVoPageInfo.setPageNum(activityDataVoPageUtils.getCurrPage());
        return PageUtils.getPageUtils(signboardProcessVoPageInfo);
    }

    /**
     * 转换成自己封装的流程类
     */
    private List<SignboardProcessVo> activityDataVos2SignboardProcessVo(
            List<ActivityDataVo> activityDataVos, Map<String, SignboardApply> map) {
        List<SignboardProcessVo> signboardProcessVos = Collections.EMPTY_LIST;
        if (!CollectionUtils.isEmpty(activityDataVos)) {
            signboardProcessVos = Lists.newArrayListWithCapacity(activityDataVos.size());
            for (ActivityDataVo activityDataVo : activityDataVos) {
                SignboardProcessVo signboardProcessVo = new SignboardProcessVo();
                SignboardApply signboardApply = map.get(activityDataVo.getUnitValue());
                signboardProcessVo.setSpeName(signboardApply.getSpeName());
                signboardProcessVo.setApplyNum(signboardApply.getApplyNum());
                signboardProcessVo.setApplyTypeName(SignboardApplyTypeEnum.getVal(signboardApply.getApplyType()));
                signboardProcessVo.setApplyTime(signboardApply.getApplyTime());
                signboardProcessVo.setApplyCode(signboardApply.getApplyCode());
                List<ActContextVo> actContextVos = activityDataVo.getActContextVos();
                for (ActContextVo actContextVo : actContextVos) {
                    String dataFieldId = actContextVo.getDataFieldId();
                    String value = actContextVo.getValue();
                    if (StringUtils.hasText(value)) {
                        if ("status".equals(dataFieldId)) {
                            signboardProcessVo.setStatusName(SignboardApplyProcessEnum.getVal(value));
                        } else if ("person".equals(dataFieldId)) {
                            signboardProcessVo.setPerson(value);
                        } else if ("opinion".equals(dataFieldId)) {
                            signboardProcessVo.setAdvice(value);
                        }
                    }
                }
                signboardProcessVo.setConTime(
                        DateUtils.stringToDate(activityDataVo.getActivityCompleteTime(), DateUtils.DATE_TIME_PATTERN));
                signboardProcessVos.add(signboardProcessVo);

            }
        }
        return signboardProcessVos;
    }

    /**
     * 列表查询流程
     */
    @Override
    public List<SignboardProcessVo> listSignboardProcess(Map<String, Object> params) {
        if (Objects.isNull(params.get("compId"))) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        return this.listSignboardProcessVo(spMapper.listByParams(params));
    }

    @Override
    public List<SignboardProcessVo> listSignboardProcess(String applyId) {
        QueryWrapper<SignboardProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("APPLY_ID", applyId).eq("DEL_FLAG", BoolUtils.N).orderByDesc("CREATE_TIME");
        List<SignboardProcess> signboardProcesses = spMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(signboardProcesses)) {
            List<SignboardProcessVo> result = Lists.newArrayListWithCapacity(signboardProcesses.size());
            for (SignboardProcess sp : signboardProcesses) {
                result.add(SignboardProcessVo.SignboardProcessVo2Vo(sp));
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void delByApplyIdAndStatus(String applyId, String status) {
        QueryWrapper<SignboardProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("apply_id", applyId).eq("status", status);
        spMapper.delete(wrapper);
    }

    @Override
    public int delByApplyIds(List<String> applyIds) {
        if (!CollectionUtils.isEmpty(applyIds)) {
            QueryWrapper<SignboardProcess> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("apply_id", applyIds);
            return spMapper.delete(queryWrapper);
        }
        return 0;
    }

    private List<SignboardProcessVo> listSignboardProcessVo(List<SignboardProcess> list) {
        if (!CollectionUtils.isEmpty(list)) {
            List<SignboardProcessVo> listVo = new ArrayList<>(list.size());
            for (SignboardProcess entity : list) {
                listVo.add(SignboardProcessVo.SignboardProcessVo2Vo(entity));
            }
            return listVo;
        }
        return null;
    }

}
