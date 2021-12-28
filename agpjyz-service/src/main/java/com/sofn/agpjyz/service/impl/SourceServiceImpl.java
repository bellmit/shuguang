package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.mapper.SourceMapper;
import com.sofn.agpjyz.model.AuditProcess;
import com.sofn.agpjyz.model.Source;
import com.sofn.agpjyz.service.AuditProcessService;
import com.sofn.agpjyz.service.HabitatTypeService;
import com.sofn.agpjyz.service.PictureAccessoriesService;
import com.sofn.agpjyz.service.SourceService;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.util.FileUtil;
import com.sofn.agpjyz.util.RedisUserUtil;
import com.sofn.agpjyz.util.StrUtil;
import com.sofn.agpjyz.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 资源调查模块服务类
 **/
@Service(value = "sourceService")
public class SourceServiceImpl extends BaseService<SourceMapper, Source> implements SourceService {

    @Resource
    private SourceMapper sourceMapper;
    @Resource
    private HabitatTypeService habitatTypeService;
    @Resource
    private PictureAccessoriesService paService;
    @Resource
    private AuditProcessService apService;
    @Resource
    private SqlSessionFactory sessionFactory;
    @Resource
    private FileUtil fileUtil;

    Integer charLength = 1000;

    String[] fileUses = {"root", "stem", "leaf", "flower", "fruit", "seed", "other"};

    @Override
    @Transactional
    public SourceVo save(SourceForm form) {
        RedisUserUtil.validReSubmit("agpjyz_save");
        String status = form.getStatus();
        if (!ProcessEnum.KEEP.getKey().equals(status) && !ProcessEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("新增状态只能为0保存2上报,请检查");
        }
        Source entity = new Source();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        //初始是否专家填报字段
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            if (loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)) {
                entity.setExpertReport(BoolUtils.Y);
            } else if (loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
                entity.setExpertReport(BoolUtils.N);
            } else {
                throw new SofnException("只有专家和县级用户才有填报权限");
            }
        }
        //验证用户机构配置是否满足要求
        String province = entity.getProvince();
        String city = entity.getCity();
        String county = entity.getCounty();
        if (!StringUtils.hasText(county)) {
            throw new SofnException("所在地区必须选择到县级");
        }
        RedisUserUtil.validLoginUser(entity.getExpertReport(), province, city, county);
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(province, city, county);
        entity.setCountyName(regionNames[2]);
        entity.setCityName(regionNames[1]);
        entity.setProvinceName(regionNames[0]);

        String features = entity.getFeatures();
        boolean featuresFlag = StringUtils.hasText(features) && features.length() > charLength;
        if (featuresFlag) {
            entity.setFeatures(features.substring(0, charLength));
        }
        String characteristic = entity.getCharacteristic();
        boolean characteristicFlag = StringUtils.hasText(characteristic) && characteristic.length() > charLength;
        if (characteristicFlag) {
            entity.setCharacteristic(characteristic.substring(0, charLength));
        }
        String threaten = entity.getThreaten();
        boolean threatenFlag = StringUtils.hasText(threaten) && threaten.length() > charLength;
        if (threatenFlag) {
            entity.setThreaten(threaten.substring(0, charLength));
        }
        String protectionUtilization = entity.getProtectionUtilization();
        boolean protectionUtilizationFlag = StringUtils.hasText(protectionUtilization) && protectionUtilization.length() > charLength;
        if (protectionUtilizationFlag) {
            entity.setProtectionUtilization(protectionUtilization.substring(0, charLength));
        }
        String suggest = entity.getSuggest();
        boolean suggestFlag = StringUtils.hasText(suggest) && suggest.length() > charLength;
        if (suggestFlag) {
            entity.setSuggest(suggest.substring(0, charLength));
        }
        sourceMapper.insert(entity);

        if (featuresFlag) {
            sourceMapper.updateFeatures(entity.getId(), features.substring(charLength));
        }
        if (characteristicFlag) {
            sourceMapper.updateCharacteristic(entity.getId(), characteristic.substring(charLength));
        }
        if (threatenFlag) {
            sourceMapper.updateThreaten(entity.getId(), threaten.substring(charLength));
        }
        if (protectionUtilizationFlag) {
            sourceMapper.updateProtectionUtilization(entity.getId(), protectionUtilization.substring(charLength));
        }
        if (suggestFlag) {
            sourceMapper.updateSuggest(entity.getId(), suggest.substring(charLength));
        }


        SourceVo sourceVo = SourceVo.entity2Vo(entity);
        String id = entity.getId();
        List<HabitatTypeForm> habitatTypeForms = form.getHabitatTypeForms();
        if (!CollectionUtils.isEmpty(habitatTypeForms)) {
            List<HabitatTypeVo> habitatTypeVos = new ArrayList<>(habitatTypeForms.size());
            for (HabitatTypeForm htForm : habitatTypeForms) {
                htForm.setSourceId(id);
                habitatTypeVos.add(habitatTypeService.save(htForm));
            }
            sourceVo.setHabitatTypeVos(habitatTypeVos);
        }
        //处理文件信息
        StringBuilder ids = new StringBuilder();
        for (String fileUse : fileUses) {
            try {
                Method method = SourceForm.class.getMethod(
                        new StringBuilder("get").append(StrUtil.captureName(fileUse)).toString());
                String subIds = this.handleFileInfo((List<PictureAccessoriesForm>) method.invoke(form), sourceVo, fileUse);
                if (StringUtils.hasText(subIds)) {
                    ids.append(",").append(subIds);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        //激活系统文件
        if (StringUtils.hasText(ids.toString())) {
            fileUtil.activationFile(ids.toString().substring(1));
        }
        return sourceVo;
    }

    /**
     * 处理文件信息
     */
    private String handleFileInfo(List<PictureAccessoriesForm> pictureAccessoriesForms, SourceVo sourceVo, String fileUse) {
        if (!CollectionUtils.isEmpty(pictureAccessoriesForms)) {
            List<PictureAccessoriesVo> pictureAccessoriesVos = new ArrayList<>(pictureAccessoriesForms.size());
            StringBuilder ids = new StringBuilder();
            for (PictureAccessoriesForm paForm : pictureAccessoriesForms) {
                paForm.setSourceId(sourceVo.getId());
                paForm.setFileUse(fileUse);
                PictureAccessoriesVo pictureAccessoriesVo = paService.save(paForm, Constants.FILE_SOURCE_SOURCE);
                ids.append("," + pictureAccessoriesVo.getFileId());
                pictureAccessoriesVos.add(pictureAccessoriesVo);
            }
            try {
                Method method = SourceVo.class.getMethod(
                        new StringBuilder("set").append(StrUtil.captureName(fileUse)).toString(), List.class);
                method.invoke(sourceVo, pictureAccessoriesVos);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return ids.toString().substring(1);
        }
        return "";
    }

    @Override
    @Transactional
    public void delete(String id) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Source entity = sourceMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待删除的数据不存在");
        }
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        sourceMapper.updateById(entity);
        paService.deleteBySourceId(id);
    }

    @Override
    @Transactional
    public void update(SourceForm form) {
        String status = form.getStatus();
        if (!ProcessEnum.KEEP.getKey().equals(status) && !ProcessEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("修改状态只能为0保存2上报,请检查");
        }
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && !loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)
                && !loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
            throw new SofnException("只有专家和县级用户才有修改填报权限!");
        }
        String id = form.getId();
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Source source = sourceMapper.selectOne(queryWrapper);
        if (Objects.isNull(source)) {
            throw new SofnException("待修改数据不存在!");
        }
        SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);

        this.handleNullNumber(form);
        BeanUtils.copyProperties(form, source);
        source.preUpdate();
        String county = source.getCounty();
        String city = source.getCity();
        String province = source.getProvince();
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(province, city, county);
        source.setCountyName(regionNames[2]);
        source.setCityName(regionNames[1]);
        source.setProvinceName(regionNames[0]);
        if (StringUtils.isEmpty(county)) {
            source.setCounty("");
            source.setCountyName("");
        }

        String features = source.getFeatures();
        boolean featuresFlag = StringUtils.hasText(features) && features.length() > charLength;
        if (featuresFlag) {
            source.setFeatures(features.substring(0, charLength));
        }
        String characteristic = source.getCharacteristic();
        boolean characteristicFlag = StringUtils.hasText(characteristic) && characteristic.length() > charLength;
        if (characteristicFlag) {
            source.setCharacteristic(characteristic.substring(0, charLength));
        }
        String threaten = source.getThreaten();
        boolean threatenFlag = StringUtils.hasText(threaten) && threaten.length() > charLength;
        if (threatenFlag) {
            source.setThreaten(threaten.substring(0, charLength));
        }
        String protectionUtilization = source.getProtectionUtilization();
        boolean protectionUtilizationFlag = StringUtils.hasText(protectionUtilization) && protectionUtilization.length() > charLength;
        if (protectionUtilizationFlag) {
            source.setProtectionUtilization(protectionUtilization.substring(0, charLength));
        }
        String suggest = source.getSuggest();
        boolean suggestFlag = StringUtils.hasText(suggest) && suggest.length() > charLength;
        if (suggestFlag) {
            source.setSuggest(suggest.substring(0, charLength));
        }
        sourceMapper.updateById(source);

        if (featuresFlag) {
            sourceMapper.updateFeatures(source.getId(), features.substring(charLength));
        }
        if (characteristicFlag) {
            sourceMapper.updateCharacteristic(source.getId(), characteristic.substring(charLength));
        }
        if (threatenFlag) {
            sourceMapper.updateThreaten(source.getId(), threaten.substring(charLength));
        }
        if (protectionUtilizationFlag) {
            sourceMapper.updateProtectionUtilization(source.getId(), protectionUtilization.substring(charLength));
        }
        if (suggestFlag) {
            sourceMapper.updateSuggest(source.getId(), suggest.substring(charLength));
        }
        //先根据资源id删除生境类型
        habitatTypeService.deleteBySourceId(id);
        List<HabitatTypeForm> habitatTypeForms = form.getHabitatTypeForms();
        if (!CollectionUtils.isEmpty(habitatTypeForms)) {
            for (HabitatTypeForm htForm : habitatTypeForms) {
                htForm.setSourceId(id);
                habitatTypeService.save(htForm);
            }
        }
        //更新文件信息
        for (String fileUse : fileUses) {
            try {
                Method method = SourceForm.class.getMethod(
                        new StringBuilder("get").append(StrUtil.captureName(fileUse)).toString());
                paService.updateSourceId(id, (List<PictureAccessoriesForm>) method.invoke(form), Constants.FILE_SOURCE_SOURCE, fileUse);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        session.commit();
    }

    /**
     * 更新数据处理空数字
     *
     * @param source
     */
    private void handleNullNumber(SourceForm source) {
        if (StringUtils.isEmpty(source.getAltitude())) {
            source.setAltitude("");
        }
        if (StringUtils.isEmpty(source.getDistribution())) {
            source.setDistribution("");
        }
        if (StringUtils.isEmpty(source.getAmount())) {
            source.setAmount("");
        }
        if (StringUtils.isEmpty(source.getTemperature())) {
            source.setTemperature("");
        }
        if (StringUtils.isEmpty(source.getGreater())) {
            source.setGreater("");
        }
        if (StringUtils.isEmpty(source.getPrecipitation())) {
            source.setPrecipitation("");
        }
        if (StringUtils.isEmpty(source.getSunshine())) {
            source.setSunshine("");
        }
        if (StringUtils.isEmpty(source.getEvaporation())) {
            source.setEvaporation("");
        }
        if (StringUtils.isEmpty(source.getVegetationCoverage())) {
            source.setVegetationCoverage("");
        }
    }

    @Override
    public SourceVo get(String id) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SourceVo sourceVo = SourceVo.entity2Vo(sourceMapper.selectOne(queryWrapper));
        if (!Objects.isNull(sourceVo)) {
            List<HabitatTypeVo> habitatTypeVos = habitatTypeService.listBySourceId(id);
            if (!CollectionUtils.isEmpty(habitatTypeVos)) {
                sourceVo.setHabitatTypeVos(habitatTypeVos);
                List<String> habitatTypeIds = habitatTypeVos.stream().map(HabitatTypeVo::getHabitatId).collect(Collectors.toList());
                sourceVo.setHabitatTypeIds(habitatTypeIds);
            }
            List<PictureAccessoriesVo> pictureAccessoriesVos = paService.listBySourceId(id);
            if (!CollectionUtils.isEmpty(pictureAccessoriesVos)) {
                for (String fileUse : fileUses) {
                    try {
                        Method method = SourceVo.class.getMethod(
                                new StringBuilder("set").append(StrUtil.captureName(fileUse)).toString(), List.class);
                        method.invoke(sourceVo, pictureAccessoriesVos.stream().
                                filter(vo -> fileUse.equals(vo.getFileUse())).collect(Collectors.toList()));
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            sourceVo.setAuditProcessVos(apService.listBySourceId(id));
        }
        return sourceVo;
    }

    @Override
    public SourceLastVo getLastCommit() {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CREATE_USER_ID", UserUtil.getLoginUserId())
                .eq("DEL_FLAG", BoolUtils.N)
                .orderByDesc("CREATE_TIME");
        List<Source> list = sourceMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            SourceLastVo vo = SourceLastVo.entity2Vo(list.get(0));
            if (Objects.nonNull(vo)) {
                List<HabitatTypeVo> habitatTypeVos = habitatTypeService.listBySourceId(vo.getId());
                vo.setHabitatTypeVos(habitatTypeVos);
                if (!CollectionUtils.isEmpty(habitatTypeVos)) {
                    List<String> habitatTypeIds = habitatTypeVos.stream().map(HabitatTypeVo::getHabitatId).collect(Collectors.toList());
                    vo.setHabitatTypeIds(habitatTypeIds);
                }
                vo.setId("");
            }
            return vo;
        }
        return null;
    }

    @Override
    public PageUtils<SourceVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //完善查询参数
        RedisUserUtil.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Source> sources = sourceMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sources)) {
            PageInfo<Source> sourcePageInfo = new PageInfo<>(sources);
            List<SourceVo> sourceVos = new ArrayList<>(sources.size());
            for (Source source : sources) {
                SourceVo vo = SourceVo.entity2Vo(source);
                List<HabitatTypeVo> habitatTypeVos = habitatTypeService.listBySourceId(source.getId());
                StringBuilder habitatTypeNames = new StringBuilder();
                if (!CollectionUtils.isEmpty(habitatTypeVos)) {
                    for (HabitatTypeVo habitatTypeVo : habitatTypeVos) {
                        String habitatValue = habitatTypeVo.getHabitatValue();
                        habitatTypeNames.append("," + (StringUtils.hasText(habitatValue) ? habitatValue : ""));
                    }
                }
                if (habitatTypeNames != null && habitatTypeNames.length() > 1) {
                    vo.setHabitatTypeName(habitatTypeNames.toString().substring(1));
                }
                Boolean[] handlePower = RedisUserUtil.getHandlePower(source.getStatus(), source.getExpertReport());
                vo.setCanAudit(handlePower[0]);
                vo.setCanCancel(handlePower[1]);
                vo.setCanEdit(handlePower[2]);
                sourceVos.add(vo);
            }
            PageInfo<SourceVo> sourceVoPageInfo = new PageInfo<>(sourceVos);
            sourceVoPageInfo.setPageSize(pageSize);
            sourceVoPageInfo.setTotal(sourcePageInfo.getTotal());
            sourceVoPageInfo.setPageNum(sourcePageInfo.getPageNum());
            return PageUtils.getPageUtils(sourceVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(sources));
    }

    @Override
    @Transactional
    public void cancel(String id) {
        QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && !loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)
                && !loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
            throw new SofnException("只有专家和县级用户才有撤回填报权限!");
        }
        Source source = sourceMapper.selectOne(queryWrapper);
        if (Objects.isNull(source)) {
            throw new SofnException("待撤回数据不存在");
        }
        if (!ProcessEnum.REPORT.getKey().equals(source.getStatus())) {
            throw new SofnException("只有已上报状态才可以撤回!");
        }
        source.preUpdate();
        source.setStatus(ProcessEnum.WITHDRAW.getKey());
        sourceMapper.updateById(source);
    }

    /**
     * 审核
     */
    private void audit(String ids, String status, String auditOpinion) {
        RedisUserUtil.validReSubmit("agpjyz_audit");
        String[] auditIds = ids.split(",");
        for (String id : auditIds) {
            QueryWrapper<Source> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
            Source source = sourceMapper.selectOne(queryWrapper);
            if (Objects.isNull(source)) {
                throw new SofnException("待审核的数据不存在");
            }
            source.preUpdate();
            source.setStatus(status);
            sourceMapper.updateById(source);
            //增加流程记录
            AuditProcess auditProcess = new AuditProcess();
            auditProcess.setStatus(status);
            auditProcess.setAuditor(source.getCreateUserId());
            User user = UserUtil.getLoginUser();
            if (!Objects.isNull(user)) {
                auditProcess.setAuditorName(user.getNickname());
            }
            auditProcess.setAuditTime(source.getUpdateTime());
            auditProcess.setAuditSource(Constants.FILE_SOURCE_SOURCE);
            auditProcess.setSourceId(source.getId());
            auditProcess.setAuditOpinion(auditOpinion);
            apService.save(auditProcess);
        }
    }

    @Override
    @Transactional
    public void auditPass(String ids, String auditOpinion) {
        String level = this.getOrganizationLevel();
        String status = Constants.REGION_TYPE_CITY.equals(level) ? ProcessEnum.CITY_AUDIT.getKey() :
                (Constants.REGION_TYPE_PROVINCE.equals(level) ? ProcessEnum.PROVINCE_AUDIT.getKey() :
                        ProcessEnum.FINAL_AUDIT.getKey());
        this.audit(ids, status, auditOpinion);
    }

    /**
     * 获取当前用户机构级别并判断是否拥有审核权限
     */
    private String getOrganizationLevel() {
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            if (loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE) ||
                    loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
                throw new SofnException("只有市级/省级/总站用户才有审核权限!");
            }
        }
        return RedisUserUtil.getOrgInfo().getOrganizationLevel();
    }

    @Override
    @Transactional
    public void auditReturn(String ids, String auditOpinion) {
        String level = this.getOrganizationLevel();
        String status = Constants.REGION_TYPE_CITY.equals(level) ? ProcessEnum.CITY_AUDIT_RETURN.getKey() :
                (Constants.REGION_TYPE_PROVINCE.equals(level) ? ProcessEnum.PROVINCE_AUDIT_RETURN.getKey() :
                        ProcessEnum.FINAL_AUDIT_RETURN.getKey());
        this.audit(ids, status, auditOpinion);
    }

    @Override
    public void exportByTemplate(Map<String, Object> params, HttpServletResponse response) {
        //完善查询参数
        RedisUserUtil.perfectParams(params, "export");
        List<Source> sources = sourceMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sources)) {
            String fileName = "农业野生植物资源调查.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            Integer serialNumber = 0;
            for (Source source : sources) {
                SourceVo sourceVo = SourceVo.entity2Vo(source);
                String specValue = sourceVo.getSpecValue();
                Date survey = source.getSurvey();
                String surveyStr = Objects.isNull(survey) ? "" : DateUtils.format(survey, "yyyyMMdd");
                String serialStr = String.valueOf(++serialNumber) + "-";
                String sheetName = serialStr + specValue + (StringUtils.hasText(surveyStr) ? surveyStr : "");
                try {
                    XSSFSheet sheet = getSheetTemplate(workbook, sheetName);
                    //第一行
                    XSSFRow row0 = sheet.getRow(0);
                    XSSFCell cell01 = row0.getCell(1);
                    cell01.setCellValue(surveyStr);
                    row0.getCell(3).setCellValue(sourceVo.getInvestigator());
                    row0.getCell(5).setCellValue(sourceVo.getPhone());
                    //第二行
                    XSSFRow row1 = sheet.getRow(1);
                    row1.getCell(1).setCellValue(sourceVo.getSpecValue());
                    row1.getCell(3).setCellValue(sourceVo.getLatin());
                    row1.getCell(5).setCellValue(sourceVo.getCommonName());
                    //第四行
                    XSSFRow row3 = sheet.getRow(3);
                    row3.getCell(1).setCellValue(sourceVo.getProvinceName());
                    row3.getCell(2).setCellValue(sourceVo.getCityName());
                    row3.getCell(3).setCellValue(sourceVo.getCountyName());
                    row3.getCell(4).setCellValue(sourceVo.getAltitude());
                    //第五行
                    XSSFRow row4 = sheet.getRow(4);
                    row4.getCell(1).setCellValue(sourceVo.getDistribution());
                    row4.getCell(4).setCellValue(sourceVo.getAmount());
                    //第六行
                    sheet.getRow(5).getCell(1).setCellValue(sourceVo.getFeatures());
                    //第七行
                    sheet.getRow(6).getCell(1).setCellValue(sourceVo.getCharacteristic());
                    //第八行
                    sheet.getRow(7).getCell(1).setCellValue(sourceVo.getEndangeredValue());
                    //第九行
                    sheet.getRow(8).getCell(1).setCellValue(sourceVo.getThreaten());
                    //第十行
                    List<HabitatTypeVo> habitatTypeVos = habitatTypeService.listBySourceId(sourceVo.getId());
                    if (!CollectionUtils.isEmpty(habitatTypeVos)) {
                        XSSFRow row9 = sheet.getRow(9);
                        List<String> nameList = habitatTypeVos.stream().map(HabitatTypeVo::getHabitatValue).collect(Collectors.toList());
                        row9.getCell(1).setCellValue(String.join(",", nameList));
                    }
                    //第十二行
                    XSSFRow row11 = sheet.getRow(11);
                    row11.getCell(1).setCellValue(sourceVo.getTemperature());
                    row11.getCell(2).setCellValue(sourceVo.getGreater());
                    row11.getCell(3).setCellValue(sourceVo.getPrecipitation());
                    row11.getCell(4).setCellValue(sourceVo.getSunshine());
                    row11.getCell(5).setCellValue(sourceVo.getEvaporation());
                    //第十三行
                    XSSFRow row12 = sheet.getRow(12);
                    row12.getCell(1).setCellValue(sourceVo.getVegetationValue());
                    row12.getCell(4).setCellValue(sourceVo.getVegetationCoverage());
                    //第十四行
                    XSSFRow row13 = sheet.getRow(13);
                    row13.getCell(1).setCellValue(sourceVo.getSoilValue());
//                    row13.getCell(4).setCellValue(sourceVo.getSoilFertility());
                    //第十五行
                    sheet.getRow(14).getCell(1).setCellValue(sourceVo.getProtectionUtilization());
                    //第十六行
                    sheet.getRow(15).getCell(1).setCellValue(sourceVo.getSuggest());
                } catch (IllegalArgumentException e) {
                    throw new SofnException("Sheet名不能重复,已存在" + sheetName);
                }
            }

            try {
                response.reset();
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                        + URLEncoder.encode(fileName, "utf-8"));
                ServletOutputStream outputStream = response.getOutputStream();
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void report(String id) {
        UpdateWrapper<Source> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", ProcessEnum.REPORT.getKey()).set("update_time", new Date()).
                set("update_user_id", UserUtil.getLoginUserId()).eq("id", id);
        sourceMapper.update(null, updateWrapper);
    }

    /**
     * 获取模板
     */
    private XSSFSheet getSheetTemplate(XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("调查日期");
        row0.createCell(2).setCellValue("调查人");
        row0.createCell(4).setCellValue("联系电话");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("中文名称(种名)");
        row1.createCell(2).setCellValue("拉丁学名");
        row1.createCell(4).setCellValue("俗名");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("所在地点");
        row2.createCell(1).setCellValue("省(自治区)");
        row2.createCell(2).setCellValue("市");
        row2.createCell(3).setCellValue("县");
        row2.createCell(4).setCellValue("海拔(米)");

        XSSFRow row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("分布面积(亩)");
        row4.createCell(3).setCellValue("种群数量");

        XSSFRow row5 = sheet.createRow(5);
        row5.createCell(0).setCellValue("形态特征");
        XSSFRow row6 = sheet.createRow(6);
        row6.createCell(0).setCellValue("生物学特性");
        XSSFRow row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue("濒危状况");
        XSSFRow row8 = sheet.createRow(8);
        row8.createCell(0).setCellValue("威胁因素");
        XSSFRow row9 = sheet.createRow(9);
        row9.createCell(0).setCellValue("生境类型");
        XSSFRow row10 = sheet.createRow(10);
        row10.createCell(0).setCellValue("气候环境");
        row10.createCell(1).setCellValue("年平均气温(°C)");
        row10.createCell(2).setCellValue("≥10°C年积温(°C)");
        row10.createCell(3).setCellValue("年平均降水量(mm)");
        row10.createCell(4).setCellValue("年平均日照时数");
        row10.createCell(5).setCellValue("年蒸发量(mm)");

        XSSFRow row12 = sheet.createRow(12);
        row12.createCell(0).setCellValue("植被类型");
        row12.createCell(3).setCellValue("植被覆盖率");

        XSSFRow row13 = sheet.createRow(13);
        row13.createCell(0).setCellValue("土壤类型");
//        row13.createCell(3).setCellValue("土壤肥力");

        XSSFRow row14 = sheet.createRow(14);
        row14.createCell(0).setCellValue("保护与利用状况");
        XSSFRow row15 = sheet.createRow(15);
        row15.createCell(0).setCellValue("评价和建议");

        ExportUtil.setCellStyle(workbook, sheet, 16, 6);

        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(10, 11, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(13, 13, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(13, 13, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 5));

        for (int i = 0; i < 6; i++) {
            // 调整每一列宽度
            sheet.autoSizeColumn(i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        return sheet;
    }


}
