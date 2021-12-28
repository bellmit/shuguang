package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.SignboardApplyProcessEnum;
import com.sofn.fdpi.mapper.SturgeonProcessMapper;
import com.sofn.fdpi.model.SturgeonProcess;
import com.sofn.fdpi.model.SturgeonSub;
import com.sofn.fdpi.service.SturgeonPaperService;
import com.sofn.fdpi.service.SturgeonProcessService;
import com.sofn.fdpi.service.SturgeonReprintService;
import com.sofn.fdpi.service.SturgeonService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.vo.SturgeonProcessVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 鲟鱼子酱标识流程服务类
 **/
@Service(value = "sturgeonProcessService")
@Slf4j
public class SturgeonProcessServiceImpl implements SturgeonProcessService {

    @Resource
    private SturgeonProcessMapper spMapper;

    @Resource
    private SysRegionApi sysRegionApi;

    @Resource
    @Lazy
    private SturgeonService sturgeonService;

    @Resource
    @Lazy
    private SturgeonReprintService sturgeonReprintService;

    @Resource
    @Lazy
    private SturgeonPaperService sturgeonPaperService;

    @Override
    public void insertSturgeonProcess(SturgeonProcess sturgeonProcess) {
        sturgeonProcess.setId(IdUtil.getUUId());
        sturgeonProcess.setConTime(new Date());
        sturgeonProcess.setPerson(this.getCurrentPerson(sturgeonProcess.getStatus()));
        spMapper.insert(sturgeonProcess);
    }

    @Override
    public List<SturgeonProcessVo> listSturgeonProcess(String applyId, String type) {
        QueryWrapper<SturgeonProcess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("APPLY_ID", applyId).orderByDesc("CON_TIME");
        List<SturgeonProcess> sturgeonProcesses = spMapper.selectList(queryWrapper);
        String applyType = "";
        if ("1".equals(type)) {
            applyType = sturgeonService.getApplyType(applyId);
        } else if ("2".equals(type)) {
            applyType = sturgeonReprintService.getApplyType(applyId);
        } else if ("3".equals(type)) {
            applyType = sturgeonPaperService.getApplyType(applyId);
        }
        if (!CollectionUtils.isEmpty(sturgeonProcesses)) {
            List<SturgeonProcessVo> result = Lists.newArrayListWithCapacity(sturgeonProcesses.size());
            for (SturgeonProcess sp : sturgeonProcesses) {
                result.add(SturgeonProcessVo.entity2Vo(sp, applyType, type));
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public int delByApplyIds(List<String> applyIds) {
        if (!CollectionUtils.isEmpty(applyIds)) {
            QueryWrapper<SturgeonProcess> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("apply_id", applyIds);
            return spMapper.delete(queryWrapper);
        }
        return 0;
    }

    /**
     * 获取当前用户/单位名称
     */
    private String getCurrentPerson(String processStatus) {
        String person = "";
        if (SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
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
}
