package com.sofn.agpjpm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjpm.constants.Constants;
import com.sofn.agpjpm.mapper.SurveyMapper;
import com.sofn.agpjpm.model.*;
import com.sofn.agpjpm.service.*;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.sysapi.SysFileApi;
import com.sofn.agpjpm.util.ApiUtil;
import com.sofn.agpjpm.util.AreaUtil;
import com.sofn.agpjpm.util.ExportUtil;
import com.sofn.agpjpm.util.RedisUserUtil;
import com.sofn.agpjpm.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
 * @Date: 2020-06-10 9:51
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyMapper surveyMapper;
    @Autowired
    private SoilTypeService  soilService;
    @Autowired
    private LandformTypeService lService;
    @Autowired
    private HabitatTypeService hService;
    @Autowired
    private ClimaticTypeService climaticService;
    @Autowired
    private TargetSpecService targetSpecService;
    @Autowired
    private JzbApi jzbApi;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SurveyForm sur) {
        Survey survey=new Survey();
        BeanUtils.copyProperties(sur,survey);
        survey.setCreateUserId(UserUtil.getLoginUserId());
         String   surveyId = IdUtil.getUUId();
         survey.setId(surveyId);
//         添加验证
//         AreaUtil.checkArea(survey.getProvince(), survey.getCity(), survey.getCounty());
        String[] regionNames = RedisUserUtil.getRegionNames(survey.getProvince(), survey.getCity(), survey.getCounty());
        survey.setCountyName(regionNames[2]);
        survey.setCityName(regionNames[1]);
        survey.setProvinceName(regionNames[0]);
        //        插入基础数据
        int insert = surveyMapper.insert(survey);
        //        插入土壤类型
        List<SoilType> soil = sur.getSoil();
        if (!CollectionUtils.isEmpty(soil)) {
            if(soil.size()>1){
                throw new SofnException("土壤类型为单选,只能选择一项");
            }
            for (SoilType s :
                    soil) {
                soilService.save(s, surveyId);
            }
        }
        //        插入生境类型
        List<HabitatType> hab = sur.getHab();
        if (!CollectionUtils.isEmpty(hab)) {
            if(hab.size()>1){
                throw new SofnException("生态类型为单选,只能选择一项");
            }
            for (HabitatType s :
                    hab) {
                hService.save(s, surveyId);
            }
        }
        //        插入气候类型
        List<ClimaticType> climaticType = sur.getCli();
        if (!CollectionUtils.isEmpty(climaticType)) {
            if(climaticType.size()>1){
                throw new SofnException("气候类型为单选,只能选择一项");
            }
            for (ClimaticType s :
                    climaticType) {
                climaticService.save(s, surveyId);
            }
        }
        //        插入地形类型
        List<LandformType> landformType = sur.getLand();
        if (!CollectionUtils.isEmpty(landformType)) {
            if(landformType.size()>1){
                throw new SofnException("地形为单选,只能选择一项");
            }
            for (LandformType s :
                    landformType) {
                lService.save(s, surveyId);
            }
        }
        //       插入物种信息
        List<SpeciesSurveyForm> speciesForms = sur.getSpeciesForms();
        if (!CollectionUtils.isEmpty(speciesForms)) {
            for (SpeciesSurveyForm s :
                    speciesForms) {
                targetSpecService.save(s, null, surveyId);
            }
        }


    }

    @Override
    public SurveyVo get(String id) {
        SurveyVo surveyVo=new SurveyVo();
        Survey survey = surveyMapper.selectById(id);
        BeanUtils.copyProperties(survey,surveyVo);
        if (survey.getAltitude()!=null){
            surveyVo.setAltitude(survey.getAltitude().toString());
        }
//        获取土壤类型
        List<SoilType> SoilTypeList = soilService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(SoilTypeList)){
            //         回显土壤类型的中文名，并用，隔开
            StringBuilder soiNames=new StringBuilder();
            Boolean a=false;
            for (SoilType ha:
                    SoilTypeList) {
                if (!ha.getSoilId().isEmpty()){
                    String st1 = ApiUtil.getResultStrMap(jzbApi.listForSoilType()).get(ha.getSoilId());
                    soiNames.append(","+st1);
                    a=true;
                }
            }
            if (a){
                surveyVo.setSoilName(soiNames.toString().substring(1));
                List<String> habitatTypeIds = SoilTypeList.stream().map(SoilType::getSoilId).collect(Collectors.toList());
                surveyVo.setSoil(habitatTypeIds);
            }else {
                surveyVo.setSoilName("");
                surveyVo.setSoil(null);
            }



        }



//        获取地形类型
        List<LandformType> LandformTypeList = lService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(LandformTypeList)) {
            //         回显地形类型的中文名，并用，隔开
            StringBuilder landNames=new StringBuilder();
            Boolean a=false;
            for (LandformType ha:
                    LandformTypeList) {
                if (!ha.getLandformId().isEmpty()) {
                    String st1 = ApiUtil.getResultStrMap(jzbApi.listForTopography()).get(ha.getLandformId());
                    landNames.append("," + st1);
                    a=true;
                }
            }
            if (a){
                surveyVo.setLandName(landNames.toString().substring(1));
                List<String> LandformTypeIds = LandformTypeList.stream().map(LandformType::getLandformId).collect(Collectors.toList());
                surveyVo.setLand(LandformTypeIds);
            }else {
                surveyVo.setLandName("");
                surveyVo.setLand(null);
            }



        }
//        获取生境类型
        List<HabitatType> habitatTypeList = hService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(habitatTypeList)) {
            //       回显生境类型的中文名，并用，隔开
            StringBuilder habNames=new StringBuilder();
            Boolean a=false;
            for (HabitatType ha:
                    habitatTypeList) {
                if (!ha.getHabitatId().isEmpty()){
                    String st1 = ApiUtil.getResultStrMap(jzbApi.listForHabitatType()).get(ha.getHabitatId());
                    habNames.append(","+st1);
                    a=true;
                }

            }
            if (a){
                surveyVo.setHabName(habNames.toString().substring(1));
                List<String> habitatTypeListIds = habitatTypeList.stream().map(HabitatType::getHabitatId).collect(Collectors.toList());
                surveyVo.setHab(habitatTypeListIds);
            }else {
                surveyVo.setHabName("");
                surveyVo.setHab(null);
            }

        }

////        获取气候类型
        List<ClimaticType> climaticList = climaticService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(climaticList)) {
            //         回显气候类型的中文名，并用，隔开
            StringBuilder cliNames=new StringBuilder();
            Boolean a=false;
            for (ClimaticType ha:
                    climaticList) {
                if (!ha.getClimaticId().isEmpty()){
                    String st1 = ApiUtil.getResultStrMap(jzbApi.listForClimateType()).get(ha.getClimaticId());
                    cliNames.append(","+st1);
                    a=true;
                }

            }
            if (a){
                surveyVo.setCliName(cliNames.toString().substring(1));
                List<String> climaticListIds = climaticList.stream().map(ClimaticType::getClimaticId).collect(Collectors.toList());
                surveyVo.setCli(climaticListIds);
            }else {
                surveyVo.setCliName("");
                surveyVo.setCli(null);
            }

        }


//        获取物种信息
        List<SpeciesSurveyVo> speciesVos = targetSpecService.getBySourceId(id, Constants.SPEC_SOURCE_SURVEY,SpeciesSurveyVo.class);
        if (!CollectionUtils.isEmpty(speciesVos)) {
            surveyVo.setSpeciesVosList(speciesVos);
        }
        return surveyVo;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SurveyForm sur) {
        Survey survey1 = surveyMapper.selectById(sur.getId());
        if (survey1==null){
            throw new SofnException("待修改的数据不存在");
        }
        String surveyId = sur.getId();
        Survey survey=new Survey();
        BeanUtils.copyProperties(sur,survey);
        //         添加验证
//        AreaUtil.checkArea(survey.getProvince(), survey.getCity(), survey.getCounty());
        String[] regionNames = RedisUserUtil.getRegionNames(survey.getProvince(), survey.getCity(), survey.getCounty());
        survey.setCountyName(regionNames[2]);
        survey.setCityName(regionNames[1]);
        survey.setProvinceName(regionNames[0]);
//        修改基础信息
        surveyMapper.updateById(survey);
        //修改物种信息
        targetSpecService.updateBySourceId(sur.getSpeciesForms(),surveyId);
        List<SoilType> soil = sur.getSoil();
//        修改土壤类型
//        先删除之前所有的
        soilService.delete(surveyId);
//        在循环插入有的
        if(!CollectionUtils.isEmpty(soil)){
            for (SoilType s:
                    soil) {
                soilService.save(s,surveyId);
            }
        }
//          修改天气
        List<ClimaticType> cli = sur.getCli();

        climaticService.delete(surveyId);
        //        在循环插入有的
        if(!CollectionUtils.isEmpty(cli)){
            for (ClimaticType s:
                    cli) {
                climaticService.save(s,surveyId);
            }
        }
//        修改生境类型
        //        先删除之前所有的
        hService.delete(surveyId);
        List<HabitatType> hab = sur.getHab();
        if(!CollectionUtils.isEmpty(hab)){
            for (HabitatType s:
                    hab) {
                hService.save(s,surveyId);
            }
        }
//          修改地形
        //        先删除之前所有的
        lService.delete(surveyId);
        List<LandformType> land = sur.getLand();
        //        在循环插入有的
        if(!CollectionUtils.isEmpty(land)){
            for (LandformType s:
                    land) {
                lService.save(s,surveyId);
            }
        }
     }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(String id) {
//        删除基础信息
        Survey survey = surveyMapper.selectById(id);
        if (survey!=null){
            surveyMapper.deleteById(id);
        }
//        删除土壤信息
        List<SoilType> SoilTypeList = soilService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(SoilTypeList)) {
            soilService.delete(id);
        }
//        删除地形信息
        List<LandformType> LandformTypeList = lService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(LandformTypeList)) {
            lService.delete(id);
        }
//        删除生境信息
        List<HabitatType> habitatTypeList = hService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(habitatTypeList)) {
            hService.delete(id);
        }
//        删除气候信息
        List<ClimaticType> climaticList = climaticService.getBySurveyId(id);
        if (!CollectionUtils.isEmpty(climaticList)) {
            climaticService.delete(id);
        }
        List<SpeciesSurveyVo> speciesVos = targetSpecService.getBySourceId(id, Constants.SPEC_SOURCE_SURVEY,SpeciesSurveyVo.class);
        if (!CollectionUtils.isEmpty(speciesVos)) {
            targetSpecService.deleteBySurveyId(id);
        }
    }

    /**
     * 分页查询
     *
     * @param params
     * @param pageNo
     * @param pageSize
     */
    @Override
    public PageUtils<ServeyListVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        RedisUserUtil.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<ServeyListVo> serveyListVos = surveyMapper.listByParams(params);
        if(!CollectionUtils.isEmpty(serveyListVos)){
            serveyListVos.forEach(o->{
                o.setCliName(ApiUtil.getResultStrMap(jzbApi.listForClimateType()).get(o.getClimaticId()));
                o.setHabName(ApiUtil.getResultStrMap(jzbApi.listForHabitatType()).get(o.getHabitatId()));
                o.setSoilName(ApiUtil.getResultStrMap(jzbApi.listForSoilType()).get(o.getSoilId()));
                o.setLandName(ApiUtil.getResultStrMap(jzbApi.listForTopography()).get(o.getLandformId()));
            });
        }
        return  PageUtils.getPageUtils(new PageInfo(serveyListVos));
    }

    /**
     * 根据模板导出
     *
     * @param params
     * @param response
     */
    @Override
    public void exportByTemplate(Map<String, Object> params, HttpServletResponse response) {
        params.put("type", "export");
        RedisUserUtil.perfectParams(params);
     List<ServeyListVo> serveyListVos = surveyMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(serveyListVos)) {
            String fileName = "农业野生植物调查表.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            Integer serialNumber = 0;
            for (ServeyListVo servey: serveyListVos) {
                String surveyNum = servey.getSurveyNum();
//                String surveyDateStr = Objects.isNull(surveyDate) ? "" : DateUtils.format(surveyDate, "yyyyMMdd");
                String serialStr = ++serialNumber + "-";
//                String sheetName = serialStr + (StringUtils.hasText(surveyDateStr) ? surveyDateStr : "");
                String sheetName = serialStr +surveyNum;
                SurveyVo surveyVo = get(servey.getId());
                List<SpeciesSurveyVo> list = surveyVo.getSpeciesVosList();
                XSSFSheet sheet = this.getSheetTemplate(workbook, sheetName, list.size());
//                农业野生植物调查表
                XSSFRow row1 = sheet.getRow(1);
                row1.getCell(1).setCellValue(surveyVo.getSurveyNum());
                row1.getCell(3).setCellValue(surveyVo.getSurveyor());
                row1.getCell(5).setCellValue(surveyVo.getTel());
                row1.getCell(7).setCellValue(surveyVo.getQq());
                XSSFRow row2 = sheet.getRow(2);
                if (surveyVo.getAltitude()!=null){
                    row2.getCell(1).setCellValue(surveyVo.getAltitude());
                }
                row2.getCell(3).setCellValue(DateUtils.format(surveyVo.getSurveyDate()));
                row2.getCell(5).setCellValue(surveyVo.getProvinceName()+"/"+surveyVo.getCityName()+"/"+surveyVo.getCountyName());
                XSSFRow row3 = sheet.getRow(3);
                row3.getCell(1).setCellValue(surveyVo.getHabName());
                row3.getCell(3).setCellValue(surveyVo.getLandName());
                row3.getCell(5).setCellValue(surveyVo.getCliName());
                row3.getCell(7).setCellValue(surveyVo.getSoilName());
//                      目标物种
                Integer currentRow = 4;
                for (int i = 0; i < list.size(); i++) {
                    SpeciesSurveyVo speciesSurveyVo = list.get(i);
                    currentRow++;
                    XSSFRow row41 = sheet.getRow(currentRow++);
                    row41.getCell(1).setCellValue(speciesSurveyVo.getFamilyName());
                    row41.getCell(3).setCellValue(speciesSurveyVo.getAttrName());
                    row41.getCell(5).setCellValue(speciesSurveyVo.getSpecName());
                    row41.getCell(7).setCellValue(speciesSurveyVo.getLatinName());
                    XSSFRow row42 = sheet.getRow(currentRow++);
                    if (speciesSurveyVo.getGreenS()!=null&&speciesSurveyVo.getGreenE()!=null){
                        row42.getCell(1).setCellValue(DateUtils.format(speciesSurveyVo.getGreenS(), "MM-dd") + "至" + DateUtils.format(speciesSurveyVo.getGreenE(), "MM-dd"));
                    }
                   if (speciesSurveyVo.getBreedS()!=null&&speciesSurveyVo.getBreedE()!=null){
                       row42.getCell(3).setCellValue(DateUtils.format(speciesSurveyVo.getBreedS(), "MM-dd") + "至" + DateUtils.format(speciesSurveyVo.getBreedE(), "MM-dd"));
                   }
                    if (speciesSurveyVo.getWitheredS()!=null&&speciesSurveyVo.getWitheredE()!=null){
                        row42.getCell(5).setCellValue(DateUtils.format(speciesSurveyVo.getWitheredS(), "MM-dd") + "至" + DateUtils.format(speciesSurveyVo.getWitheredE(), "MM-dd"));
                    }
                    XSSFRow row43 = sheet.getRow(currentRow++);
                    if (speciesSurveyVo.getArea()!=null){
                        row43.getCell(1).setCellValue(speciesSurveyVo.getArea());
                    }
                    if (speciesSurveyVo.getAmount()!=null){
                    row43.getCell(3).setCellValue(speciesSurveyVo.getAmount());
                    }
                    if (speciesSurveyVo.getDiscovery()!=null){
                        if ("1".equals(speciesSurveyVo.getDiscovery())){
                            row43.getCell(5).setCellValue("是");
                        }else{
                            row43.getCell(5).setCellValue("否");
                        }
                    }
                    if (speciesSurveyVo.getNewSpecies()!=null){
                        if ("1".equals(speciesSurveyVo.getNewSpecies())){
                            row43.getCell(7).setCellValue("是");
                        }else{
                            row43.getCell(7).setCellValue("否");
                        }
                    }

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




    /**
     * 获取模板
     */
    private XSSFSheet getSheetTemplate(XSSFWorkbook workbook, String sheetName, Integer targetSpecNum) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        Integer currentRow = 0;
        XSSFRow row0 = sheet.createRow(currentRow++);
        row0.createCell(0).setCellValue("农业野生植物调查表");

        XSSFRow row1 = sheet.createRow(currentRow++);
        row1.createCell(0).setCellValue("调查点编号：");
        row1.createCell(2).setCellValue("调查人姓名：");
        row1.createCell(4).setCellValue("联系电话：");
        row1.createCell(6).setCellValue("QQ号：");

        XSSFRow row2 = sheet.createRow(currentRow++);
        row2.createCell(0).setCellValue("海拔（米）：");
        row2.createCell(2).setCellValue("调查日期：");
        row2.createCell(4).setCellValue("调查地点：");

        XSSFRow row3 = sheet.createRow(currentRow++);
        row3.createCell(0).setCellValue("生境类型：");
        row3.createCell(2).setCellValue("地形：");
        row3.createCell(4).setCellValue("气候类型：");
        row3.createCell(6).setCellValue("土壤类型：");


        for (int i = 1; i <= targetSpecNum; i++) {
            XSSFRow row40 = sheet.createRow(currentRow++);
            row40.createCell(0).setCellValue("目标物种" + i);
            sheet.addMergedRegion(new CellRangeAddress(4*i, 4*i, 0, 7));
            XSSFRow row41 = sheet.createRow(currentRow++);
            row41.createCell(0).setCellValue("科名");
            row41.createCell(2).setCellValue("属名");
            row41.createCell(4).setCellValue("种名");
            row41.createCell(6).setCellValue("拉丁文种名");
            XSSFRow row42 = sheet.createRow(currentRow++);
            row42.createCell(0).setCellValue("返青期：");
            row42.createCell(2).setCellValue("繁殖期：");
            row42.createCell(4).setCellValue("枯萎期：");
            XSSFRow row43 = sheet.createRow(currentRow++);
            row43.createCell(0).setCellValue("分布面积（亩）：");
            row43.createCell(2).setCellValue("分布数量（丛、片等）：");
            row43.createCell(4).setCellValue("省内新发现：");
            row43.createCell(6).setCellValue("是否为国家新发物种：");
        }


        ExportUtil.setCellStyle(workbook, sheet, currentRow, 8);
//        CellRangeAddress（int， int， int， int）
//
//        参数：起始行号，终止行号， 起始列号，终止列号
        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        for (int i = 0; i < 8; i++) {
            // 调整每一列宽度
            sheet.autoSizeColumn((short) i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        return sheet;
    }

}
