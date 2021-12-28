package com.sofn.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysReportMapper;
import com.sofn.sys.model.SysReport;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysReportService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.util.FineReportUtil;
import com.sofn.sys.vo.SysReportVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/26 17:16
 */
@Service
public class SysReportServiceImpl implements SysReportService {

    @Autowired
    private SysReportMapper sysReportMapper;

    @Autowired
    private SysSubsystemService subsystemService;

    @Override
    public PageUtils<SysReportVo> getSysReportByCondition(Map<String, String> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<SysReportVo> sysReportByCondition = sysReportMapper.getSysReportByCondition(params);
        PageInfo<SysReportVo> sysReportVoPageInfo = new PageInfo<>(sysReportByCondition);
        List<SysReportVo> list = sysReportVoPageInfo.getList();
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(item-> item.setUrl(FineReportUtil.getReportUrl(item.getReportName(),item.getReportType())));
        }
        return PageUtils.getPageUtils(sysReportVoPageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysReport(SysReportVo sysReportVo) {
        if(sysReportVo == null){
            throw new SofnException("没有需要保存的信息");
        }
        SysReport sysReport = check(sysReportVo);
        sysReport.setReportName(sysReportVo.getReportName());
        sysReport.setRemark(sysReportVo.getRemark());
        sysReport.setReportType(sysReportVo.getReportType());
        sysReport.setAppId(sysReportVo.getAppId());
        sysReport.preUpdate();
        sysReportMapper.updateById(sysReport);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSysReport(SysReportVo sysReportVo) {
        if(sysReportVo == null){
            throw new SofnException("没有需要保存的信息");
        }
        check(sysReportVo);
        SysReport sysReport = SysReportVo.getSysReport(sysReportVo);
        sysReport.preInsert();
        sysReportMapper.insert(sysReport);
    }

    @Override
    public void delSysReport(String id) {
        SysReport sysReport = sysReportMapper.selectById(id);
        if(sysReport == null){
            throw new SofnException("报表不存在！");
        }
        if(SysManageEnum.DEL_FLAG_Y.getCode().equals(sysReport.getDelFlag())){
            throw new SofnException("报表已删除！");
        }
        sysReportMapper.deleteSysReportById(id, UserUtil.getLoginUserId(),new Date());
    }

    @Override
    public String getUrl(String reportName,String type) {
        return FineReportUtil.getReportUrl(reportName,type);
    }

    @Override
    public SysReportVo getOne(String id) {
        SysReport sysReport = sysReportMapper.selectById(id);
        if(sysReport == null){
            throw new SofnException("ID对应的报表不存在！");
        }
        if(SysManageEnum.DEL_FLAG_Y.getCode().equals(sysReport.getDelFlag())){
            throw new SofnException("报表已经删除！");
        }

        return SysReportVo.getSysReportVo(sysReport);
    }

    /**
     * 检查数据
     * @param sysReportVo  待检查的数据
     */
    private SysReport check(SysReportVo sysReportVo){
        SysReport sysReport = null;
        if(!StringUtils.isBlank(sysReportVo.getId())){
            sysReport = sysReportMapper.selectById(sysReportVo.getId());
            if(sysReport == null){
                throw new SofnException("报表不存在！");
            }
            if(SysManageEnum.DEL_FLAG_Y.getCode().equals(sysReport.getDelFlag())){
                throw new SofnException("报表已删除！");
            }
        }

        //检查报表是否能够正确访问
        FineReportUtil.checkReportIsExists(sysReportVo.getReportName(),sysReportVo.getReportType());

        // 检查子系统是否存在
        int reportCountByNameAndAppId = sysReportMapper.getReportCountByNameAndAppId(sysReportVo.getReportName(), sysReportVo.getAppId(), sysReportVo.getId());
        if(reportCountByNameAndAppId > 0){
            throw new SofnException("一个子系统下报表名称不能重复！");
        }
        SysSubsystem sysSubsystemAppId = subsystemService.getSysSubsystemAppId(sysReportVo.getAppId());
        if(sysSubsystemAppId == null){
            throw new SofnException("子系统不存在！");
        }
        return sysReport;
    }



}
