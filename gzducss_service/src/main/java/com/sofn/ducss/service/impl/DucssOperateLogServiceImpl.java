package com.sofn.ducss.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.DucssOperateLogMapper;
import com.sofn.ducss.model.DucssOperateLog;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.service.DucssOperateLogService;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DucssOperateLogServiceImpl implements DucssOperateLogService {

    @Autowired
    private DucssOperateLogMapper ducssOperateLogMapper;

    @Override
    public void save(String operateType, String operateDetail) {
        DucssOperateLog ducssOperateLog = new DucssOperateLog();
        ducssOperateLog.setId(IdUtil.getUUId());
        ducssOperateLog.setOperateDetail(operateDetail);
        ducssOperateLog.setOperateType(operateType);
        ducssOperateLog.setOperateTime(new Date());
        ducssOperateLog.setOperateUserId(UserUtil.getLoginUserId());
        ducssOperateLog.setOperateUserName(UserUtil.getLoginUserName());

//        ducssOperateLog.setOperateUserId("123");
//        ducssOperateLog.setOperateUserName("2345");
        //获取当前登录用户等级
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        if (StringUtils.isBlank(organizationInfo)) {
            throw new SofnException("当前登录用户异常，未获取到机构信息");
        }
        try {
            SysOrganization sysOrganization = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
            String regionLastCode = sysOrganization.getRegionLastCode();
            ducssOperateLog.setLevel(sysOrganization.getOrganizationLevel());
            ducssOperateLog.setAreaId(regionLastCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ducssOperateLogMapper.insert(ducssOperateLog);
    }

    @Override
    public PageUtils<DucssOperateLog> getLogList(String startDate, String endDate, String operateType, String operateDetail, String areaId, Integer pageNo, Integer pageSize) {
        if (StringUtils.isBlank(areaId)) {
            throw new SofnException("区域ID不能为空，区域ID为当前登录用户的所属区划");
        }
        if (pageSize == null || pageSize <= 0) {
            throw new SofnException("请传入分页信息");
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<DucssOperateLog> logList = ducssOperateLogMapper.getLogList(startDate, endDate, operateType, operateDetail, areaId);
        PageInfo<DucssOperateLog> ducssOperateLogPageInfo = new PageInfo<>(logList);
        List<DucssOperateLog> list = ducssOperateLogPageInfo.getList();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                String tempOperateType = item.getOperateType();
                String typeName = LogEnum.getEnumDes("LOG_TYPE", tempOperateType);
                item.setOperateTypeName(typeName);
            });
            ducssOperateLogPageInfo.setList(list);
        }
        return PageUtils.getPageUtils(ducssOperateLogPageInfo);
    }


}
