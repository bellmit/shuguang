package com.sofn.dhhrp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.model.User;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.dhhrp.Api.AhhdpApi;
import com.sofn.dhhrp.constants.Constants;
import com.sofn.dhhrp.enums.ProcessEnum;
import com.sofn.dhhrp.mapper.BaseinfoMapper;
import com.sofn.dhhrp.model.AuditProcess;
import com.sofn.dhhrp.model.Baseinfo;
import com.sofn.dhhrp.service.AuditProcessService;
import com.sofn.dhhrp.service.BaseinfoService;
import com.sofn.dhhrp.util.ApiUtil;
import com.sofn.dhhrp.util.RedisUserUtil;
import com.sofn.dhhrp.vo.BaseinfoForm;
import com.sofn.dhhrp.vo.BaseinfoLastVo;
import com.sofn.dhhrp.vo.BaseinfoVo;
import com.sofn.dhhrp.vo.DropDownVo;
import com.sofn.dhhrp.vo.exportTemplate.ExportBaseinfoBean;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 基础信息模块服务类
 **/
@Service(value = "baseinfoService")
public class BaseinfoServiceImpl extends BaseService<BaseinfoMapper, Baseinfo> implements BaseinfoService {


    @Resource
    private BaseinfoMapper baseinfoMapper;

    @Resource
    private AuditProcessService auditProcessService;

    @Resource
    private AhhdpApi ahhdpApi;


    @Override
    public BaseinfoVo save(BaseinfoForm form) {
        RedisUserUtil.validReSubmit("dhhrp_save");
        String status = form.getStatus();
        if (!ProcessEnum.KEEP.getKey().equals(status) && !ProcessEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("新增状态只能为0保存2上报,请检查");
        }
        Baseinfo entity = new Baseinfo();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        entity.setProportion(this.simplifyProportion(entity.getProportion()));
        entity.setMonitoringTime(entity.getCreateTime());
        List<String> codeList = UserUtil.getLoginUserRoleCodeList();
        if (!codeList.contains("dev")) {
            //检查是否县级用户填报
            String organizationLevel = RedisUserUtil.getOrganizationLevel();
            if (StringUtils.hasText(organizationLevel)
                    && !Constants.REGION_TYPE_COUNTY.equals(organizationLevel)) {
                throw new SofnException("只有县级用户才有填报权限!");
            }
        }
        //验证用户机构配置是否满足要求
        String province = entity.getProvince();
        String city = entity.getCity();
        String county = entity.getCounty();
        if (!StringUtils.hasText(county)) {
            throw new SofnException("所在地区必须选择到县级");
        }
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(province, city, county);
        entity.setCountyName(regionNames[2]);
        entity.setCityName(regionNames[1]);
        entity.setProvinceName(regionNames[0]);

        entity.setMonitor("监测人");
        User user = UserUtil.getLoginUser();
        if (Objects.nonNull(user)) {
            entity.setMonitor(user.getNickname());
        }
        baseinfoMapper.insert(entity);
        return BaseinfoVo.entity2Vo(entity);
    }

    @Override
    public void delete(String id) {
        QueryWrapper<Baseinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        Baseinfo entity = baseinfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待删除的数据不存在");
        }
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        baseinfoMapper.updateById(entity);
    }

    @Override
    public void update(BaseinfoForm form) {
        String status = form.getStatus();
        if (!ProcessEnum.KEEP.getKey().equals(status) && !ProcessEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("修改状态只能为0保存2上报,请检查");
        }
        List<String> codeList = UserUtil.getLoginUserRoleCodeList();
        if (!codeList.contains("dev")) {
            String organizationLevel = RedisUserUtil.getOrganizationLevel();
            if (StringUtils.hasText(organizationLevel)
                    && !Constants.REGION_TYPE_COUNTY.equals(organizationLevel)) {
                throw new SofnException("只有县级用户才有修改填报权限!");
            }
        }
        String id = form.getId();
        QueryWrapper<Baseinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        Baseinfo baseinfo = baseinfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(baseinfo)) {
            throw new SofnException("待修改数据不存在!");
        }

        BeanUtils.copyProperties(form, baseinfo);
        baseinfo.preUpdate();
        baseinfo.setProportion(this.simplifyProportion(baseinfo.getProportion()));
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(baseinfo.getProvince(), baseinfo.getCity(), baseinfo.getCounty());
        baseinfo.setCountyName(regionNames[2]);
        baseinfo.setCityName(regionNames[1]);
        baseinfo.setProvinceName(regionNames[0]);
        baseinfo.setMonitoringTime(baseinfo.getUpdateTime());
        baseinfoMapper.updateById(baseinfo);
    }

    @Override
    public BaseinfoVo get(String id) {
        QueryWrapper<Baseinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        BaseinfoVo baseinfoVo = BaseinfoVo.entity2Vo(this.handleDouble(baseinfoMapper.selectOne(queryWrapper)));
        Map<String, String> varietyNameMap = ApiUtil.getResultMap(ahhdpApi.listForName(RedisUserUtil.getProvinceCode()));
        baseinfoVo.setVarietyName(varietyNameMap.get(baseinfoVo.getVariety()));
        if (Objects.nonNull(baseinfoVo)) {
            baseinfoVo.setAuditProcessVos(auditProcessService.listByBaseId(id));
        }
        return baseinfoVo;
    }

    @Override
    public BaseinfoLastVo getLastCommit() {
        QueryWrapper<Baseinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CREATE_USER_ID", UserUtil.getLoginUserId())
                .eq("DEL_FLAG", BoolUtils.N)
                .orderByDesc("CREATE_TIME");
        List<Baseinfo> list = baseinfoMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            return BaseinfoLastVo.entity2Vo(list.get(0));
        }
        return null;
    }

    private Baseinfo handleDouble(Baseinfo baseinfo) {
        if (null != baseinfo.getTemperature())
            baseinfo.setTemperature(Double.valueOf(new DecimalFormat("#.00").format(baseinfo.getTemperature())));
        if (null != baseinfo.getHumidity())
            baseinfo.setHumidity(Double.valueOf(new DecimalFormat("#.00").format(baseinfo.getHumidity())));
        if (null != baseinfo.getIllumination())
            baseinfo.setIllumination(Double.valueOf(new DecimalFormat("#.00").format(baseinfo.getIllumination())));
        if (null != baseinfo.getRainfall())
            baseinfo.setRainfall(Double.valueOf(new DecimalFormat("#.00").format(baseinfo.getRainfall())));
        if (null != baseinfo.getPlant())
            baseinfo.setPlant(Double.valueOf(new DecimalFormat("#.00").format(baseinfo.getPlant())));
        if (null != baseinfo.getYield())
            baseinfo.setYield(Double.valueOf(new DecimalFormat("#.00").format(baseinfo.getYield())));
        return baseinfo;
    }


    @Override
    public PageUtils<BaseinfoVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        RedisUserUtil.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Baseinfo> baseinfos = baseinfoMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(baseinfos)) {
            PageInfo<Baseinfo> baseinfoPageInfo = new PageInfo<>(baseinfos);
            List<BaseinfoVo> baseinfoVos = new ArrayList<>(baseinfos.size());
            Map<String, String> varietyNameMap =
                    ApiUtil.getResultMap(ahhdpApi.listForName(RedisUserUtil.getProvinceCode()));
            for (Baseinfo baseinfo : baseinfos) {
                BaseinfoVo vo = BaseinfoVo.entity2Vo(this.handleDouble(baseinfo));
                Boolean[] cans = RedisUserUtil.getHandlePower(baseinfo.getStatus());
                vo.setCanAudit(cans[0]);
                vo.setCanCancel(cans[1]);
                vo.setCanEdit(cans[2]);
                vo.setVarietyName(varietyNameMap.get(vo.getVariety()));
                baseinfoVos.add(vo);
            }
            PageInfo<BaseinfoVo> sourceVoPageInfo = new PageInfo<>(baseinfoVos);
            sourceVoPageInfo.setPageSize(pageSize);
            sourceVoPageInfo.setTotal(baseinfoPageInfo.getTotal());
            sourceVoPageInfo.setPageNum(baseinfoPageInfo.getPageNum());
            return PageUtils.getPageUtils(sourceVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(baseinfos));
    }

    @Override
    public void cancel(String id) {
        QueryWrapper<Baseinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        List<String> codeList = UserUtil.getLoginUserRoleCodeList();
        if (!codeList.contains("dev")) {
            String organizationLevel = RedisUserUtil.getOrganizationLevel();
            if (StringUtils.hasText(organizationLevel)
                    && !Constants.REGION_TYPE_COUNTY.equals(organizationLevel)) {
                throw new SofnException("只有县级用户才有撤回填报权限!");
            }
        }
        Baseinfo baseinfo = baseinfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(baseinfo)) {
            throw new SofnException("待撤回数据不存在");
        }
        if (!ProcessEnum.REPORT.getKey().equals(baseinfo.getStatus())) {
            throw new SofnException("只有已上报状态才可以撤回!");
        }
        baseinfo.preUpdate();
        baseinfo.setStatus(ProcessEnum.WITHDRAW.getKey());
        baseinfoMapper.updateById(baseinfo);
    }

    @Override
    @Transactional
    public void auditPass(String id, String auditOpinion) {
        String status = this.get(id).getStatus();
        status = ProcessEnum.REPORT.getKey().equals(status) ? ProcessEnum.CITY_AUDIT.getKey() :
                (ProcessEnum.CITY_AUDIT.getKey().equals(status) ? ProcessEnum.PROVINCE_AUDIT.getKey() :
                        ProcessEnum.FINAL_AUDIT.getKey());
        this.audit(id, status, auditOpinion);
    }

    @Override
    @Transactional
    public void auditReturn(String id, String auditOpinion) {
        String status = this.get(id).getStatus();
        status = ProcessEnum.REPORT.getKey().equals(status) ? ProcessEnum.CITY_AUDIT_RETURN.getKey() :
                (ProcessEnum.CITY_AUDIT.getKey().equals(status) ? ProcessEnum.PROVINCE_AUDIT_RETURN.getKey() :
                        ProcessEnum.FINAL_AUDIT_RETURN.getKey());
        this.audit(id, status, auditOpinion);
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        RedisUserUtil.perfectParams(params);
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/地方畜禽遗传资源原生境基础信息.xlsx";
        ExcelExportUtil.createTemplate(filePath, ExportBaseinfoBean.class);
        List<Baseinfo> baseinfos = baseinfoMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(baseinfos)) {
            List<ExportBaseinfoBean> baseinfoBeans = new ArrayList<>(baseinfos.size());
            Map<String, String> varietyNameMap =
                    ApiUtil.getResultMap(ahhdpApi.listForName(RedisUserUtil.getProvinceCode()));
            for (Baseinfo baseinfo : baseinfos) {
                ExportBaseinfoBean baseinfoBean = ExportBaseinfoBean.entity2Export(this.handleDouble(baseinfo));
                baseinfoBean.setVarietyName(varietyNameMap.get(baseinfo.getVariety()));
                baseinfoBeans.add(baseinfoBean);
            }
            String fileName = "地方畜禽遗传资源原生境基础信息.xlsx";
            ExcelExportUtil.createExcel(response, ExportBaseinfoBean.class, baseinfoBeans, ExcelField.Type.EXPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public List<DropDownVo> getYears() {
        Map<String, Object> params = new HashMap<>(1);
        String orgLevel = RedisUserUtil.getOrganizationLevel();
        if (Constants.REGION_TYPE_COUNTY.equals(orgLevel)) {
            params.put("createUserId", UserUtil.getLoginUserId());
        } else if (Constants.REGION_TYPE_CITY.equals(orgLevel)) {
            params.put("city", RedisUserUtil.getCityCode());
        } else if (Constants.REGION_TYPE_PROVINCE.equals(orgLevel)) {
            params.put("province", RedisUserUtil.getProvinceCode());
        }
        List<String> years = baseinfoMapper.getYears(params);
        if (CollectionUtils.isEmpty(years)) {
            return DropDownVo.string2DropDownVo(Collections.EMPTY_LIST);
        }
        return DropDownVo.string2DropDownVo(years);
    }

    @Override
    public List<DropDownVo> getPointNames() {
        Map<String, Object> params = new HashMap<>(1);
        String orgLevel = RedisUserUtil.getOrganizationLevel();
        if (Constants.REGION_TYPE_COUNTY.equals(orgLevel)) {
            params.put("createUserId", UserUtil.getLoginUserId());
        } else if (Constants.REGION_TYPE_CITY.equals(orgLevel)) {
            params.put("city", RedisUserUtil.getCityCode());
        } else if (Constants.REGION_TYPE_PROVINCE.equals(orgLevel)) {
            params.put("province", RedisUserUtil.getProvinceCode());
        }
        List<String> pointNames = baseinfoMapper.getPointNames(params);
        if (CollectionUtils.isEmpty(pointNames)) {
            return DropDownVo.string2DropDownVo(Collections.EMPTY_LIST);
        }
        return DropDownVo.string2DropDownVo(pointNames);
    }

    /**
     * 审核
     */
    private void audit(String id, String status, String auditOpinion) {
        RedisUserUtil.validReSubmit("dhhrp_audit", id);
        QueryWrapper<Baseinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        Baseinfo baseinfo = baseinfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(baseinfo)) {
            throw new SofnException("待审核的数据不存在");
        }
        baseinfo.preUpdate();
        baseinfo.setStatus(status);
        baseinfoMapper.updateById(baseinfo);

        //增加流程记录
        AuditProcess auditProcess = new AuditProcess();
        auditProcess.setStatus(status);
        auditProcess.setAuditor(baseinfo.getCreateUserId());
        User user = UserUtil.getLoginUser();
        if (!Objects.isNull(user)) {
            auditProcess.setAuditorName(user.getNickname());
        }
        auditProcess.setAuditTime(baseinfo.getUpdateTime());
        auditProcess.setBaseId(baseinfo.getId());
        auditProcess.setAuditOpinion(auditOpinion);
        auditProcessService.save(auditProcess);
    }


    private String simplifyProportion(String proportion) {
        String arr[] = new String[2];
        if (proportion.contains(":")) {
            arr = proportion.split(":");
        } else {
            arr = proportion.split("：");
        }
        Integer i1 = Integer.parseInt(arr[0]);
        Integer i2 = Integer.parseInt(arr[1]);
        int gra = this.getgra(i1, i2);
        return String.valueOf(i1 / gra) + ":" + String.valueOf(i2 / gra);
    }

    //获取公约数
    private int getgra(int m, int n) {
        for (int x = m; x >= 1; x--) {
            if (m % x == 0 && n % x == 0) {
                return x;
            }
        }
        return 1;
    }
}
