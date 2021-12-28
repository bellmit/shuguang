package com.sofn.fdpi.service.impl;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.mapper.ResProcessMapper;
import com.sofn.fdpi.model.ResProcess;
import com.sofn.fdpi.service.ResProcessService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.vo.ResProcessFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:12
 */
@Slf4j
@Service
public class ResProcessServiceimpl extends BaseService<ResProcessMapper, ResProcess>implements ResProcessService {
    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired
    ResProcessMapper resProcessMapper;

    /**
     * 流程新增
     * @param from
     * @return
     */
    @Override
    public ResProcess insertResProcess(ResProcessFrom from){
        ResProcess resProcess = from.convertToModel(ResProcess.class);
        resProcess.preInsert();
        resProcess.setPerson(this.getCurrentPerson(from.getStatus()));
        resProcess.setConTime(resProcess.getCreateTime());
        resProcessMapper.insert(resProcess);
        return resProcess;
    }
    //获取当前用户/单位
    private String getCurrentPerson(int processStatus){
        String person="";
        if (DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(processStatus))) {
            person = UserUtil.getLoginUserName();
        }else {
            String organizationId = UserUtil.getLoginUserOrganizationId();
            Result<List<Map<String, String>>> infoByCondition = sysRegionApi.getInfoByCondition(organizationId, null, null, null);
            if (!Result.CODE.equals(infoByCondition.getCode())){
                throw new SofnException("获取当前用户组织机构信息失败");
            }
            List<Map<String, String>> data = infoByCondition.getData();
            if (!CollectionUtils.isEmpty(data)){
                person = data.get(0).get("ORGNAME");
            }
        }
        return person;
    }
}
