package com.sofn.fdpi.service.impl;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.mapper.ForfeiProcessMapper;
import com.sofn.fdpi.model.ForfeiProcess;
import com.sofn.fdpi.service.ForfeiProcessService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.vo.ForfeiProcessFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 10:57
 */
@Service
@Slf4j
public class ForfeiProcessServiceImpl extends BaseService<ForfeiProcessMapper, ForfeiProcess> implements ForfeiProcessService {
    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired
    ForfeiProcessMapper fProcessMapper;
    /**
     * 新增流程
     */
    @Override
    public ForfeiProcess insertForfeiProcess(ForfeiProcessFrom from) {
        ForfeiProcess forfeiProcess = from.convertToModel(ForfeiProcess.class);
        forfeiProcess.preInsert();
        forfeiProcess.setPerson(this.getCurrentPerson(from.getStatus()));
        forfeiProcess.setConTime(forfeiProcess.getCreateTime());
        fProcessMapper.insert(forfeiProcess);
        return forfeiProcess;
    }
    //获取当前用户/单位
    private String getCurrentPerson(int processStatus){
        String person = "";
        if (DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(processStatus)){
            person = UserUtil.getLoginUserName();
        }else {
            String organizationId = UserUtil.getLoginUserOrganizationId();
            Result<List<Map<String, String>>> infoByCondition = sysRegionApi.getInfoByCondition(organizationId, null, null, null);
            if (!Result.CODE.equals(infoByCondition.getCode())){
                throw new SofnException("获取系统当前用户组织机构信息失败 ");
            }
            List<Map<String, String>> infoByConditionData = infoByCondition.getData();
            if (!CollectionUtils.isEmpty(infoByConditionData)){
               person = infoByConditionData.get(0).get("ORGNAME");
            }
        }
        return person;
    }

}
