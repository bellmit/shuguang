package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.mapper.SpecimenMapper;
import com.sofn.agpjyz.model.AuditProcess;

import com.sofn.agpjyz.model.Source;
import com.sofn.agpjyz.model.Specimen;
import com.sofn.agpjyz.service.AuditProcessService;
import com.sofn.agpjyz.service.CharacterService;
import com.sofn.agpjyz.service.PictureAccessoriesService;
import com.sofn.agpjyz.service.SpecimenService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.sysapi.SysFileApi;
import com.sofn.agpjyz.util.ApiUtil;
import com.sofn.agpjyz.util.AreaUtil;
import com.sofn.agpjyz.util.FileUtil;
import com.sofn.agpjyz.util.RedisUserUtil;
import com.sofn.agpjyz.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 14:42
 */
@Service("specimenService")
public class SpecimenServiceImpl extends BaseService<SpecimenMapper, Specimen> implements SpecimenService {
    @Autowired
    private SpecimenMapper specimenMapper;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private SqlSessionFactory sessionFactory;
    @Autowired
    private PictureAccessoriesService paService;
    @Autowired
    private AuditProcessService apService;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private SysFileApi sysFileApi;
    @Autowired
    private JzbApi jzbApi;

    private String handleFileInfo(List<PictureAccessoriesForm> pictureAccessoriesForms, SpecimenVo specimenVo, String fileUse) {
        if (!CollectionUtils.isEmpty(pictureAccessoriesForms)) {
            List<PictureAccessoriesVo> pictureAccessoriesVos = new ArrayList<>(pictureAccessoriesForms.size());
            StringBuilder ids = new StringBuilder();
            for (PictureAccessoriesForm paForm : pictureAccessoriesForms) {
                paForm.setSourceId(specimenVo.getId());
                paForm.setFileUse(fileUse);
                PictureAccessoriesVo pictureAccessoriesVo = paService.save(paForm, Constants.FILE_SOURCE_SPECIMEN);
                ids.append("," + pictureAccessoriesVo.getFileId());
                pictureAccessoriesVos.add(pictureAccessoriesVo);
            }
            if ("root".equals(fileUse)) {
                specimenVo.setRoot(pictureAccessoriesVos);
            } else if ("stem".equals(fileUse)) {
                specimenVo.setStem(pictureAccessoriesVos);
            } else if ("leaf".equals(fileUse)) {
                specimenVo.setLeaf(pictureAccessoriesVos);
            } else if ("flower".equals(fileUse)) {
                specimenVo.setFlower(pictureAccessoriesVos);
            } else if ("fruit".equals(fileUse)) {
                specimenVo.setFruit(pictureAccessoriesVos);
            } else if ("seed".equals(fileUse)) {
                specimenVo.setSeed(pictureAccessoriesVos);
            }
            return ids.toString().substring(1);
        }
        return "";
    }

    @Override
    public SpecimenVo save(SpecimenForm specimenForm) {
        RedisUserUtil.validReSubmit("agpjyz_save");
//        ????????????
        String status = specimenForm.getStatus();
        if (!ProcessEnum.KEEP.getKey().equals(status) && !ProcessEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("????????????????????????????????????,?????????");
        }
//        ?????? ??????????????????
        Specimen entity = new Specimen();
        BeanUtils.copyProperties(specimenForm, entity);
        //         ????????????
//        AreaUtil.checkArea(entity.getProvince(), entity.getCity(), entity.getCounty());
        String[] regionNames = RedisUserUtil.getRegionNames(entity.getProvince(), entity.getCity(), entity.getCounty());
        entity.setCountyName(regionNames[2]);
        entity.setCityName(regionNames[1]);
        entity.setProvinceName(regionNames[0]);
        entity.preInsert();
        entity.setAuditFlag(BoolUtils.N);
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        //??????????????????????????????
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)) {
            entity.setExpertReport(BoolUtils.Y);
        } else {
            entity.setExpertReport(BoolUtils.N);
        }
        specimenMapper.insert(entity);
//        ??????????????????
        SpecimenVo specimenVo = SpecimenVo.entity2Vo(entity);
        String id = entity.getId();
        List<CharacterForm> characterForm = specimenForm.getTreeValue();
        if (!CollectionUtils.isEmpty(characterForm)) {
            List<CharacterVo> characterVo = new ArrayList<>(characterForm.size());
            for (CharacterForm chForm : characterForm) {
                chForm.setSpecimenId(id);
                characterVo.add(characterService.save(chForm));
            }
            specimenVo.setTreeValue(characterVo);
        }
//        List<CharacterForm> characterForm1 = specimenForm.getYiLi();
//        if (!CollectionUtils.isEmpty(characterForm1)) {
//            List<CharacterVo>  characterVo1 = new ArrayList<>(characterForm1.size());
//            for (CharacterForm chForm: characterForm1) {
//                chForm.setSpecimenId(id);
//                characterVo1.add(characterService.save(chForm));
//            }
//            specimenVo.setYiLi(characterVo1);
//        }
//        ??????????????????
        //??????????????????
        StringBuilder ids = new StringBuilder();
        String idsRoot = this.handleFileInfo(specimenForm.getRoot(), specimenVo, "root");
        if (StringUtils.hasText(idsRoot)) {
            ids.append("," + idsRoot);
        }
        String idsStem = this.handleFileInfo(specimenForm.getStem(), specimenVo, "stem");
        if (StringUtils.hasText(idsStem)) {
            ids.append("," + idsStem);
        }
        String idsLeaf = this.handleFileInfo(specimenForm.getLeaf(), specimenVo, "leaf");
        if (StringUtils.hasText(idsLeaf)) {
            ids.append("," + idsLeaf);
        }
        String idsFlower = this.handleFileInfo(specimenForm.getFlower(), specimenVo, "flower");
        if (StringUtils.hasText(idsFlower)) {
            ids.append("," + idsFlower);
        }
        String idsFruit = this.handleFileInfo(specimenForm.getFruit(), specimenVo, "fruit");
        if (StringUtils.hasText(idsFruit)) {
            ids.append("," + idsFruit);
        }
        String idsSeed = this.handleFileInfo(specimenForm.getSeed(), specimenVo, "seed");
        if (StringUtils.hasText(idsSeed)) {
            ids.append("," + idsSeed);
        }
        //??????????????????
        if (StringUtils.hasText(ids.toString())) {
            fileUtil.activationFile(ids.toString().substring(1));
        }
        return specimenVo;
    }

    @Override
    public int del(String id) {
        int i = specimenMapper.deleteById(id);
        int del = characterService.del(id);
        paService.deleteBySourceId(id);
        return 0;
    }

    @Override
    public SpecimenVo getOne(String id) {
        QueryWrapper<Specimen> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SpecimenVo specimenVo = SpecimenVo.entity2Vo(specimenMapper.selectOne(queryWrapper));
        specimenVo.setCollectionValue(ApiUtil.getResultMap2(jzbApi.listForCollectDept()).get(specimenVo.getCollectionId()));
        specimenVo.setCollectioner(ApiUtil.getResultMap3(jzbApi.listForCollectDept()).get(specimenVo.getCollectionId()));
        List<CharacterVo> one = characterService.getOne(id);
        if (!Objects.isNull(one)) {
//            specimenVo.setYiLi(one.stream().
//                    filter(vo -> "2".equals(vo.getType())).collect(Collectors.toList()));
            specimenVo.setTreeValue(one.stream().
                    filter(vo -> "1".equals(vo.getType())).collect(Collectors.toList()));
            List<String> list1 = new ArrayList<String>();
            for (CharacterVo y :
                    specimenVo.getTreeValue()) {
                list1.add(y.getCharacterValue());
            }
            specimenVo.setTv(list1);
        }
//        List<String> list=new ArrayList<String>();

//        for (CharacterVo  y:
//                specimenVo.getYiLi()) {
//            list.add(y.getCharacterValue());
//        }
//        specimenVo.setYl(list);

        if (!Objects.isNull(specimenVo)) {

            List<PictureAccessoriesVo> pictureAccessoriesVos = paService.listBySourceId(id);
            if (!CollectionUtils.isEmpty(pictureAccessoriesVos)) {
                specimenVo.setRoot(pictureAccessoriesVos.stream().
                        filter(vo -> "root".equals(vo.getFileUse())).collect(Collectors.toList()));
                specimenVo.setStem(pictureAccessoriesVos.stream().
                        filter(vo -> "stem".equals(vo.getFileUse())).collect(Collectors.toList()));
                specimenVo.setLeaf(pictureAccessoriesVos.stream().
                        filter(vo -> "leaf".equals(vo.getFileUse())).collect(Collectors.toList()));
                specimenVo.setFlower(pictureAccessoriesVos.stream().
                        filter(vo -> "flower".equals(vo.getFileUse())).collect(Collectors.toList()));
                specimenVo.setFruit(pictureAccessoriesVos.stream().
                        filter(vo -> "fruit".equals(vo.getFileUse())).collect(Collectors.toList()));
                specimenVo.setSeed(pictureAccessoriesVos.stream().
                        filter(vo -> "seed".equals(vo.getFileUse())).collect(Collectors.toList()));
            }
            specimenVo.setAuditProcessVos(apService.listBySourceId(id));
        }
        return specimenVo;
    }

    @Override
    public int update(SpecimenForm specimenForm) {
//        ????????????
//        AreaUtil.checkArea(specimenForm.getProvince(), specimenForm.getCity(), specimenForm.getCounty());
        String status = specimenForm.getStatus();
        if (!ProcessEnum.KEEP.getKey().equals(status) && !ProcessEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("?????????????????????0??????2??????,?????????");
        }
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && !loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)
                && !loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
            throw new SofnException("???????????????????????????????????????????????????!");
        }
        String id = specimenForm.getId();
        QueryWrapper<Specimen> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Specimen specimen = specimenMapper.selectOne(queryWrapper);
        if (Objects.isNull(specimen)) {
            throw new SofnException("????????????????????????!");
        }
        SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
        BeanUtils.copyProperties(specimenForm, specimen);
        String[] regionNames = RedisUserUtil.getRegionNames(specimen.getProvince(), specimen.getCity(), specimen.getCounty());
        specimen.setCountyName(regionNames[2]);
        specimen.setCityName(regionNames[1]);
        specimen.setProvinceName(regionNames[0]);
        specimen.preUpdate();
        if (specimen.getAltitude() == null) {
            specimen.setAltitude("");
        }
        if (specimen.getPlantHeight() == null) {
            specimen.setPlantHeight("");
        }
        if (specimen.getDiameter() == null) {
            specimen.setDiameter("");
        }
        specimenMapper.updateById(specimen);
        int del = characterService.del(id);
        List<CharacterForm> characterForm = specimenForm.getTreeValue();
        if (!CollectionUtils.isEmpty(characterForm)) {
            for (CharacterForm chForm : characterForm) {
                chForm.setSpecimenId(id);
                characterService.save(chForm);
            }

        }
//        List<CharacterForm> characterForm1 = specimenForm.getYiLi();
//        if (!CollectionUtils.isEmpty(characterForm1)) {
//            for (CharacterForm chForm: characterForm1) {
//                chForm.setSpecimenId(id);
//               characterService.save(chForm);
//            }
//        }
        paService.updateSourceId(id, specimenForm.getRoot(), Constants.FILE_SOURCE_SPECIMEN, "root");
        paService.updateSourceId(id, specimenForm.getStem(), Constants.FILE_SOURCE_SPECIMEN, "stem");
        paService.updateSourceId(id, specimenForm.getLeaf(), Constants.FILE_SOURCE_SPECIMEN, "leaf");
        paService.updateSourceId(id, specimenForm.getFlower(), Constants.FILE_SOURCE_SPECIMEN, "flower");
        paService.updateSourceId(id, specimenForm.getFruit(), Constants.FILE_SOURCE_SPECIMEN, "fruit");
        paService.updateSourceId(id, specimenForm.getSeed(), Constants.FILE_SOURCE_SPECIMEN, "seed");
        session.commit();
        return 0;
    }

    @Override
    public void exportByTemplate(Map<String, Object> params, HttpServletResponse response) {
        RedisUserUtil.perfectParams(params, "export");
        List<Specimen> sources = specimenMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sources)) {
            String fileName = "??????????????????????????????.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            Integer serialNumber = 0;
            for (Specimen source : sources) {
                SpecimenVo specimenVo = SpecimenVo.entity2Vo(source);
                String chineseValue = specimenVo.getChineseValue();
                Date collectionDate = specimenVo.getCollectionDate();
                String surveyStr = Objects.isNull(collectionDate) ? "" : DateUtils.format(collectionDate);
                String serialStr = String.valueOf(++serialNumber) + "-";
                String sheetName = serialStr + chineseValue + (StringUtils.hasText(surveyStr) ? "(" + surveyStr + ")" : "");
                List<CharacterVo> one = characterService.getOne(source.getId());
                if (specimenVo.getProvince() != null) {
                    Result<String> sysRegionName = sysFileApi.getSysRegionName(specimenVo.getProvince());
                    specimenVo.setProvinceName(sysRegionName.getData());
                }
                if (specimenVo.getCity() != null) {
                    Result<String> sysRegionName1 = sysFileApi.getSysRegionName(specimenVo.getCity());
                    specimenVo.setCityName(sysRegionName1.getData());
                }
                if (specimenVo.getCounty() != null) {
                    Result<String> sysRegionName2 = sysFileApi.getSysRegionName(specimenVo.getCounty());
                    specimenVo.setCountyName(sysRegionName2.getData());
                }
                if (!Objects.isNull(one)) {
//                    specimenVo.setYiLi(one.stream().
//                            filter(vo -> "2".equals(vo.getType())).collect(Collectors.toList()));
                    specimenVo.setTreeValue(one.stream().
                            filter(vo -> "1".equals(vo.getType())).collect(Collectors.toList()));
                }
                specimenVo.setCollectionValue(ApiUtil.getResultMap2(jzbApi.listForCollectDept()).get(specimenVo.getCollectionId()));
                specimenVo.setCollectioner(ApiUtil.getResultMap3(jzbApi.listForCollectDept()).get(specimenVo.getCollectionId()));
                try {
                    XSSFSheet sheet = getSheetTemplate(workbook, sheetName);
                    //?????????
                    XSSFRow row0 = sheet.getRow(0);
                    XSSFCell cell01 = row0.getCell(1);
                    cell01.setCellValue(specimenVo.getCollectionNumber());
                    Date survey = specimenVo.getCollectionDate();
                    if (!Objects.isNull(survey)) {
                        row0.getCell(3).setCellValue(DateUtils.format(survey));
                    }
                    //?????????
                    XSSFRow row1 = sheet.getRow(1);
                    row1.getCell(1).setCellValue(specimenVo.getCollectionValue());
                    row1.getCell(3).setCellValue(specimenVo.getCollectioner());
//                //?????????
                    XSSFRow row2 = sheet.getRow(2);
                    row2.getCell(1).setCellValue(specimenVo.getChineseValue());
                    row2.getCell(3).setCellValue(specimenVo.getLatinName());
                    row2.getCell(5).setCellValue(specimenVo.getScientificName());
                    XSSFRow row3 = sheet.getRow(3);
                    row3.getCell(1).setCellValue(specimenVo.getGenericName());
                    row3.getCell(5).setCellValue(specimenVo.getCommonName());
                    XSSFRow row4 = sheet.getRow(4);
                    row4.getCell(1).setCellValue(specimenVo.getProvinceName());
                    row4.getCell(2).setCellValue(specimenVo.getCityName());
                    row4.getCell(3).setCellValue(specimenVo.getCountyName());
                    row4.getCell(5).setCellValue(specimenVo.getAltitude());
                    XSSFRow row5 = sheet.getRow(5);
                    StringBuilder st = new StringBuilder();
//                    StringBuilder st1=new StringBuilder();
                    List<CharacterVo> treeValue = specimenVo.getTreeValue();
                    if (!CollectionUtils.isEmpty(treeValue)) {
                        for (CharacterVo t :
                                treeValue) {
                            st.append(t.getCharacterValue() + ",");
                        }
                        row5.getCell(1).setCellValue(st.toString().substring(0, st.length() - 1));
                    }
//                    List<CharacterVo> yiLi = specimenVo.getYiLi();
//                 if (!CollectionUtils.isEmpty(yiLi)){
//                     for (CharacterVo t:
//                             yiLi) {
//                         st1.append(t.getCharacterValue()+",");
//                     }
//                 }


                    XSSFRow row7 = sheet.getRow(7);
                    row7.getCell(1).setCellValue(specimenVo.getPlantHeight());
                    row7.getCell(3).setCellValue(specimenVo.getDiameter());
                } catch (Exception e) {
                    throw new SofnException("Sheet???????????????,?????????" + sheetName);
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
    public PageUtils<SpecimenVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        RedisUserUtil.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Specimen> specimens = specimenMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(specimens)) {
            PageInfo<Specimen> sourcePageInfo = new PageInfo<>(specimens);
            List<SpecimenVo> specimenVos = new ArrayList<>(specimens.size());
            for (Specimen sp :
                    specimens) {


                SpecimenVo vo = SpecimenVo.entity2Vo(sp);
                Boolean[] handlePower = RedisUserUtil.getHandlePower(sp.getStatus(), sp.getExpertReport());
                vo.setCanAudit(handlePower[0]);
                vo.setCanCancel(handlePower[1]);
                vo.setCanEdit(handlePower[2]);
                vo.setCollectionValue(ApiUtil.getResultMap2(jzbApi.listForCollectDept()).get(sp.getCollectionId()));
                vo.setCollectioner(ApiUtil.getResultMap3(jzbApi.listForCollectDept()).get(sp.getCollectionId()));
                specimenVos.add(vo);

            }
            PageInfo<SpecimenVo> specimenVoPageInfo = new PageInfo<>(specimenVos);
            specimenVoPageInfo.setPageSize(pageSize);
            specimenVoPageInfo.setPageNum(sourcePageInfo.getPageNum());
            specimenVoPageInfo.setTotal(sourcePageInfo.getTotal());
            return PageUtils.getPageUtils(specimenVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(specimens));
    }

    /**
     * ??????
     *
     * @param ids
     * @param status
     * @param auditOpinion
     */
    @Override
    public void audit(String ids, String status, String auditOpinion) {
        RedisUserUtil.validReSubmit("agpjyz_audit");
        String[] auditIds = ids.split(",");
        for (String id : auditIds) {
            QueryWrapper<Specimen> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
            Specimen specimen = specimenMapper.selectOne(queryWrapper);
            if (Objects.isNull(specimen)) {
                throw new SofnException("???????????????????????????");
            }
            //?????????????????????
            specimen.preUpdate();
            specimen.setStatus(status);
            specimenMapper.updateById(specimen);
            //??????????????????
            AuditProcess auditProcess = new AuditProcess();
            auditProcess.setStatus(status);
            auditProcess.setAuditor(specimen.getCreateUserId());
            User user = UserUtil.getLoginUser();
            if (!Objects.isNull(user)) {
                auditProcess.setAuditorName(user.getNickname());
            }
            auditProcess.setAuditTime(specimen.getUpdateTime());
            auditProcess.setAuditSource(Constants.FILE_SOURCE_SPECIMEN);
            auditProcess.setSourceId(specimen.getId());
            auditProcess.setAuditOpinion(auditOpinion);
            apService.save(auditProcess);
        }
    }

    /**
     * ??????
     *
     * @param id
     */
    @Override
    public void cancel(String id) {
        QueryWrapper<Specimen> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)
                && !loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE)
                && !loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
            throw new SofnException("???????????????????????????????????????????????????!");
        }
        Specimen specimen = specimenMapper.selectOne(queryWrapper);
        if (Objects.isNull(specimen)) {
            throw new SofnException("????????????????????????");
        }
        if (!ProcessEnum.REPORT.getKey().equals(specimen.getStatus())) {
            throw new SofnException("????????????????????????????????????!");
        }
        specimen.preUpdate();
        specimen.setStatus(ProcessEnum.WITHDRAW.getKey());
        specimenMapper.updateById(specimen);
    }

    /**
     * ????????????
     *
     * @param ids
     * @param auditOpinion
     */
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
     * ???????????????????????????????????????????????????????????????
     */
    private String getOrganizationLevel() {
        List<String> loginUserRoleCodeList = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(loginUserRoleCodeList)) {
            if (loginUserRoleCodeList.contains(Constants.EXPERT_ROLE_CODE) ||
                    loginUserRoleCodeList.contains(Constants.COUNTY_ROLE_CODE)) {
                throw new SofnException("????????????/??????/??????????????????????????????!");
            }
        }
        return RedisUserUtil.getOrgInfo().getOrganizationLevel();
    }

    /**
     * ????????????
     *
     * @param ids
     * @param auditOpinion
     */
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
    public SpecimenLastVo getLast() {
        QueryWrapper<Specimen> sp=new QueryWrapper<>();
        sp.eq("CREATE_USER_ID", UserUtil.getLoginUserId())
                .eq("DEL_FLAG", BoolUtils.N)
                .orderByDesc("CREATE_TIME");
        List<Specimen> list = specimenMapper.selectList(sp);

        if (!CollectionUtils.isEmpty(list)) {
            SpecimenLastVo specimenLastVo = SpecimenLastVo.entity2Vo(list.get(0));
            specimenLastVo.setCollectionValue(ApiUtil.getResultMap2(jzbApi.listForCollectDept()).get(specimenLastVo.getCollectionId()));
            specimenLastVo.setCollectioner(ApiUtil.getResultMap3(jzbApi.listForCollectDept()).get(specimenLastVo.getCollectionId()));
            List<CharacterVo> one = characterService.getOne(list.get(0).getId());
            if (!Objects.isNull(one)) {
//            specimenVo.setYiLi(one.stream().
//                    filter(vo -> "2".equals(vo.getType())).collect(Collectors.toList()));
                specimenLastVo.setTreeValue(one.stream().
                        filter(vo -> "1".equals(vo.getType())).collect(Collectors.toList()));
                List<String> list1 = new ArrayList<String>();
                for (CharacterVo y :
                        specimenLastVo.getTreeValue()) {
                    list1.add(y.getCharacterValue());
                }
                specimenLastVo.setTv(list1);
            }
            specimenLastVo.setId("");
             String number=specimenLastVo.getCollectionNumber().substring(0,specimenLastVo.getCollectionNumber().length()-4)+IdUtil.getNumCode(4);
            specimenLastVo.setCollectionNumber(number);
            return  specimenLastVo;
        }
        return null;
    }

    @Override
    public void report(String id) {
        UpdateWrapper<Specimen> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", ProcessEnum.REPORT.getKey()).set("update_time", new Date()).
                set("update_user_id", UserUtil.getLoginUserId()).eq("id", id);
        specimenMapper.update(null, updateWrapper);
    }

    private void checkProcess(Specimen specimen, String status) {
        String sourceStatus = specimen.getStatus();
        //??????????????????
        if (BoolUtils.Y.equals(specimen.getExpertReport())) {
            if (ProcessEnum.REPORT.getKey().equals(sourceStatus)) {
                if (!ProcessEnum.FINAL_AUDIT_RETURN.getKey().equals(status)
                        && !ProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
                    throw new SofnException("???????????????????????????????????????????????????");
                }
            }
        }
        //?????????????????????
        else {
            if (ProcessEnum.REPORT.getKey().equals(sourceStatus)) {
                if (!ProcessEnum.CITY_AUDIT_RETURN.getKey().equals(status)
                        && !ProcessEnum.CITY_AUDIT.getKey().equals(status)) {
                    throw new SofnException("???????????????????????????????????????????????????");
                }
            } else if (ProcessEnum.CITY_AUDIT.getKey().equals(sourceStatus)) {
                if (!ProcessEnum.PROVINCE_AUDIT_RETURN.getKey().equals(status)
                        && !ProcessEnum.PROVINCE_AUDIT.getKey().equals(status)) {
                    throw new SofnException("???????????????????????????????????????????????????");
                }
            } else if (ProcessEnum.PROVINCE_AUDIT.getKey().equals(sourceStatus)) {
                if (!ProcessEnum.FINAL_AUDIT_RETURN.getKey().equals(status)
                        && !ProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
                    throw new SofnException("???????????????????????????????????????????????????");
                }
            }
        }
    }

    /**
     * ????????????
     */
    private XSSFSheet getSheetTemplate(XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("?????????");
        row0.createCell(2).setCellValue("????????????");
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("????????????");
        row1.createCell(2).setCellValue("?????????");
        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("?????????");
        row2.createCell(2).setCellValue("????????????");
        row2.createCell(4).setCellValue("??????");
        XSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("??????");
        row3.createCell(4).setCellValue("??????");
        XSSFRow row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("????????????");
        row4.createCell(4).setCellValue("???????????????");
        XSSFRow row5 = sheet.createRow(5);
        row5.createCell(0).setCellValue("??????");
        XSSFRow row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue("???????????????m???");
        row7.createCell(2).setCellValue("??????(m)");

        this.setCellStyle(workbook, sheet);
        //????????????????????????
        //                                          ????????????   ????????????
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(5, 6, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(5, 6, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 3, 5));


        for (int i = 0; i < 6; i++) {
            // ?????????????????????
            sheet.autoSizeColumn(i);
            // ?????????????????????????????????????????????
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        return sheet;
    }

    /**
     * ??????excel??????
     */
    private void setCellStyle(XSSFWorkbook workbook, XSSFSheet sheet) {
        int totalRow = 8;
        int totalColumn = 6;
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        for (int i = 0; i < totalRow; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = 0; j < totalColumn; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellStyle(style);
            }
        }
    }

}
