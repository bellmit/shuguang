package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjpm.constants.Constants;
import com.sofn.agpjpm.mapper.MonitorMapper;
import com.sofn.agpjpm.model.Monitor;
import com.sofn.agpjpm.service.MonitorService;
import com.sofn.agpjpm.service.PictureAttService;
import com.sofn.agpjpm.service.SpeciesMonitorService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.util.*;
import com.sofn.agpjpm.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 监测表服务类
 **/
@Service(value = "monitorService")
public class MonitorServiceImpl extends BaseService<MonitorMapper, Monitor> implements MonitorService {

    @Resource
    private MonitorMapper monitorMapper;

    @Resource
    private PictureAttService pictureAttService;

    @Resource
    private SpeciesMonitorService speciesMonitorService;

    @Resource
    private JzbApi jzbApi;

    @Resource
    private FileUtil fileUtil;

    String[] fileUses = {"protectPic", "fencePic", "nursePic", "warningPic", "patrolPic", "towerPic", "beforePic",
            "otherFacilitiesPic", "superCommunityPic", "otherPic", "plantPic", "invadeCommunityPic", "specPic", "afterPic"};

    @Override
    @Transactional
    public MonitorVo save(MonitorForm form) {
        RedisUserUtil.validReSubmit("agpjpm_save");
        Monitor entity = new Monitor();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(entity.getProvince(), entity.getCity(), entity.getCounty());
        entity.setCountyName(regionNames[2]);
        entity.setCityName(regionNames[1]);
        entity.setProvinceName(regionNames[0]);
        monitorMapper.insert(entity);
        MonitorVo vo = MonitorVo.entity2Vo(entity);
        List<SpeciesMonitorForm> speciesMonitorForms = form.getSpeciesMonitorForms();
        if (!CollectionUtils.isEmpty(speciesMonitorForms)) {
            for (int i = 0; i < speciesMonitorForms.size(); i++) {
                speciesMonitorService.save(speciesMonitorForms.get(i), entity.getId(), i + 1);
            }
        }
        //处理文件信息
        StringBuilder ids = new StringBuilder();
        for (String fileUse : fileUses) {
            try {
                Method method = MonitorForm.class.getMethod(
                        new StringBuilder("get").append(StrUtil.captureName(fileUse)).toString());
                String subIds = this.handleFileInfo((List<PictureAttForm>) method.invoke(form), vo, fileUse);
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
        return vo;
    }

    @Override
    @Transactional
    public void delete(String id) {
        RedisUserUtil.validReSubmit("agpjpm_del");
        QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        Monitor entity = monitorMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待删除的数据不存在");
        }
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        monitorMapper.updateById(entity);
        speciesMonitorService.deleteBySourceId(id);
        pictureAttService.deleteBySourceId(id);
    }

    @Override
    @Transactional
    public void update(MonitorForm form) {
        RedisUserUtil.validReSubmit("agpjpm_update");
        String id = form.getId();
        QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        Monitor entity = monitorMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待修改数据不存在!");
        }
        BeanUtils.copyProperties(form, entity);
        entity.preUpdate();
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(entity.getProvince(), entity.getCity(), entity.getCounty());
        entity.setCountyName(regionNames[2]);
        entity.setCityName(regionNames[1]);
        entity.setProvinceName(regionNames[0]);
        monitorMapper.updateById(entity);
        //更新文件信息
        for (String fileUse : fileUses) {
            try {
                Method method = MonitorForm.class.getMethod(
                        new StringBuilder("get").append(StrUtil.captureName(fileUse)).toString());
                pictureAttService.updateSourceId(id, (List<PictureAttForm>) method.invoke(form), Constants.FILE_SOURCE_MONITOR, fileUse);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        //更新目标物种
        speciesMonitorService.update(form.getSpeciesMonitorForms(), id);
    }

    @Override
    public MonitorVo get(String id) {
        QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        MonitorVo monitorVo = MonitorVo.entity2Vo(monitorMapper.selectOne(queryWrapper));
        //处理agpjzb保护点被更新，原id找不到的情况
        if (!ApiUtil.existIdFromAgpjzb(jzbApi.listForProtectPoints(null), monitorVo.getProtectId())) {
            monitorVo.setProtectId("");
            monitorVo.setProtectName("");
        }
        if (Objects.nonNull(monitorVo)) {
            Map<String, String> protectMap = ApiUtil.getResultStrMap(jzbApi.listForProtectPoints(null));
            monitorVo.setProtectName(protectMap.get(monitorVo.getProtectId()));
            List<PictureAttVo> pictureAttVos = pictureAttService.listBySourceId(id);
            if (!CollectionUtils.isEmpty(pictureAttVos)) {
                for (String fileUse : fileUses) {
                    try {
                        Method method = MonitorVo.class.getMethod(
                                new StringBuilder("set").append(StrUtil.captureName(fileUse)).toString(), List.class);
                        method.invoke(monitorVo, pictureAttVos.stream().
                                filter(vo -> fileUse.equals(vo.getFileUse())).collect(Collectors.toList()));
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            monitorVo.setSpeciesMonitorVos(speciesMonitorService.listWithPicBySourceId(id));
        }
        return monitorVo;
    }

    @Override
    public PageUtils<MonitorVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //完善查询参数
        RedisUserUtil.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Monitor> monitors = monitorMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(monitors)) {
            Map<String, String> protectMap = ApiUtil.getResultStrMap(jzbApi.listForProtectPoints(null));
            PageInfo<Monitor> monitorPageInfo = new PageInfo<>(monitors);
            List<MonitorVo> monitorVos = new ArrayList<>(monitors.size());
            for (Monitor monitor : monitors) {
                MonitorVo vo = MonitorVo.entity2Vo(monitor);
                vo.setProtectName(protectMap.get(vo.getProtectId()));
                monitorVos.add(vo);
            }
            PageInfo<MonitorVo> monitorVoPageInfo = new PageInfo<>(monitorVos);
            monitorVoPageInfo.setPageSize(pageSize);
            monitorVoPageInfo.setTotal(monitorPageInfo.getTotal());
            monitorVoPageInfo.setPageNum(monitorPageInfo.getPageNum());
            return PageUtils.getPageUtils(monitorVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(monitors));
    }


    @Override
    public void exportByTemplate(Map<String, Object> params, HttpServletResponse response) {
        params.put("type", "export");
        RedisUserUtil.perfectParams(params);
        List<Monitor> monitors = monitorMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(monitors)) {
            String fileName = "农业野生植物原生境保护区(点)监测表.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();

            Integer serialNumber = 0;
            for (Monitor monitor : monitors) {
                Map<String, String> protectMap = ApiUtil.getResultStrMap(jzbApi.listForProtectPoints(null));
                String protectName = protectMap.get(monitor.getProtectId());
                Date surveyDate = monitor.getSurveyDate();
                String surveyDateStr = Objects.isNull(surveyDate) ? "" : DateUtils.format(surveyDate, "yyyyMMdd");
                String serialStr = ++serialNumber + "-";
                String sheetName = serialStr + protectName + (StringUtils.hasText(surveyDateStr) ? surveyDateStr : "");
                List<SpeciesMonitorVo> list = speciesMonitorService.listBySourceId(monitor.getId());
                XSSFSheet sheet = this.getSheetTemplate(workbook, sheetName, list.size());
                /********************保护区（点）信息*******************/
                XSSFRow row1 = sheet.getRow(1);
                row1.getCell(1).setCellValue(protectName);
                row1.getCell(3).setCellValue(monitor.getSurveyDept());
                row1.getCell(5).setCellValue(monitor.getSurveyor());
                row1.getCell(7).setCellValue(DateUtils.format(monitor.getSurveyDate()));

                XSSFRow row2 = sheet.getRow(2);
                row2.getCell(1).setCellValue(monitor.getTel());
                row2.getCell(3).setCellValue(monitor.getProvinceName() +
                        monitor.getCityName() + monitor.getCountyName() + monitor.getAddr());
                /********************目标物种和基础设施保护情况*******************/
                XSSFRow row4 = sheet.getRow(4);
                String targetSpec = monitor.getTargetSpec();
                if (targetSpec.contains("其他")) {
                    targetSpec += "(" + monitor.getOther() + ")";
                }
                row4.getCell(1).setCellValue(targetSpec);
                Double damage = monitor.getDamage();
                if (Objects.nonNull(damage)) {
                    row4.getCell(3).setCellValue(damage);
                }
                row4.getCell(5).setCellValue(monitor.getFence());
                row4.getCell(7).setCellValue(monitor.getNurse());

                XSSFRow row5 = sheet.getRow(5);
                row5.getCell(1).setCellValue(monitor.getWarning());
                row5.getCell(3).setCellValue(monitor.getPatrol());
                row5.getCell(5).setCellValue(monitor.getTower());
                row5.getCell(7).setCellValue(monitor.getOtherFacilities());
                /********************生境指标*******************/
                XSSFRow row7 = sheet.getRow(7);
                Double avgTemp = monitor.getAvgTemp();
                if (Objects.nonNull(avgTemp)) {
                    row7.getCell(1).setCellValue(avgTemp);
                }
                Double avgRainfall = monitor.getAvgRainfall();
                if (Objects.nonNull(avgRainfall)) {
                    row7.getCell(3).setCellValue(avgRainfall);
                }
                /********************目标物种*******************/
                Integer currentRow = 9;
                Map<String, DropDownWithLatinVo> specMap = ApiUtil.getResultObjMap(jzbApi.listForSpecies());
                for (int i = 0; i < list.size(); i++) {
                    SpeciesMonitorVo vo = list.get(i);
                    currentRow++;
                    XSSFRow row41 = sheet.getRow(currentRow++);
                    DropDownWithLatinVo dropDownWithLatinVo = specMap.get(vo.getSpecId());
                    if (Objects.nonNull(dropDownWithLatinVo)) {
                        row41.getCell(1).setCellValue(dropDownWithLatinVo.getFamilyName());
                        row41.getCell(3).setCellValue(dropDownWithLatinVo.getAttrName());
                        row41.getCell(5).setCellValue(dropDownWithLatinVo.getName());
                        row41.getCell(7).setCellValue(dropDownWithLatinVo.getLatinName());
                    }
                    XSSFRow row42 = sheet.getRow(currentRow++);
                    row42.getCell(1).setCellValue(vo.getGrowth());
                    row42.getCell(3).setCellValue(vo.getDensity());
                    row42.getCell(5).setCellValue(vo.getRichness());
                    String area = vo.getArea();
                    if (StringUtils.hasText(area)) {
                        row42.getCell(7).setCellValue(area);
                    }
                }
                /********************优势伴生物种*******************/
                currentRow++;
                XSSFRow rowSuper1 = sheet.getRow(currentRow++);
                Integer superFamily = monitor.getSuperFamily();
                if (Objects.nonNull(superFamily)) {
                    rowSuper1.getCell(1).setCellValue(superFamily);
                }
                Integer superGenera = monitor.getSuperGenera();
                if (Objects.nonNull(superGenera)) {
                    rowSuper1.getCell(3).setCellValue(superGenera);
                }
                Integer superSpecies = monitor.getSuperSpecies();
                if (Objects.nonNull(superSpecies)) {
                    rowSuper1.getCell(5).setCellValue(superSpecies);
                }
                Integer superSeedling = monitor.getSuperSeedling();
                if (Objects.nonNull(superSeedling)) {
                    rowSuper1.getCell(7).setCellValue(superSeedling);
                }
                XSSFRow rowSuper2 = sheet.getRow(currentRow++);
                rowSuper2.getCell(1).setCellValue(monitor.getSuperGrowth());
                Double superCover = monitor.getSuperCover();
                if (Objects.nonNull(superCover)) {
                    rowSuper2.getCell(3).setCellValue(superCover);
                }
                Double superArea = monitor.getSuperArea();
                if (Objects.nonNull(superArea)) {
                    rowSuper2.getCell(5).setCellValue(superArea);
                }
                XSSFRow rowSuper3 = sheet.getRow(currentRow++);
                Date trunGreenStart = monitor.getTrunGreenStart();
                Date trunGreenEnd = monitor.getTrunGreenEnd();
                rowSuper3.getCell(1).setCellValue(
                        (Objects.isNull(trunGreenStart) ? "" : DateUtils.format(trunGreenStart, "MM-dd")) + "至"
                                + (Objects.isNull(trunGreenEnd) ? "" : DateUtils.format(trunGreenEnd, "MM-dd")));
                Date witherStart = monitor.getWitherStart();
                Date witherEnd = monitor.getWitherEnd();
                rowSuper3.getCell(5).setCellValue(
                        (Objects.isNull(witherStart) ? "" : DateUtils.format(witherStart, "MM-dd")) + "至"
                                + (Objects.isNull(witherEnd) ? "" : DateUtils.format(witherEnd, "MM-dd")));
                /********************入侵物种*******************/
                currentRow++;
                XSSFRow rowInvade1 = sheet.getRow(currentRow++);
                Integer invadeSpecies = monitor.getInvadeSpecies();
                if (invadeSpecies != null) {
                    rowInvade1.getCell(1).setCellValue(invadeSpecies);
                }
                rowInvade1.getCell(3).setCellValue(monitor.getInvadeSpecName());
                Double invadeArea = monitor.getInvadeArea();
                if (Objects.nonNull(invadeArea)) {
                    rowInvade1.getCell(7).setCellValue(invadeArea);
                }
                XSSFRow rowInvade2 = sheet.getRow(currentRow++);
                rowInvade2.getCell(1).setCellValue(monitor.getInvadeHazard());
                XSSFRow rowInvade3 = sheet.getRow(currentRow++);
                rowInvade3.getCell(1).setCellValue(monitor.getInvadeEradicate());

                for (int i = 0; i < 8; i++) {
                    // 调整每一列宽度
                    sheet.autoSizeColumn((short) i);
                    // 解决自动设置列宽中文失效的问题
                    sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
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
        row0.createCell(0).setCellValue("1-保护区（点）信息");

        XSSFRow row1 = sheet.createRow(currentRow++);
        row1.createCell(0).setCellValue("保护点名称");
        row1.createCell(2).setCellValue("调查单位");
        row1.createCell(4).setCellValue("调查人");
        row1.createCell(6).setCellValue("调查时间");

        XSSFRow row2 = sheet.createRow(currentRow++);
        row2.createCell(0).setCellValue("联系人电话");
        row2.createCell(2).setCellValue("所在地点");

        XSSFRow row3 = sheet.createRow(currentRow++);
        row3.createCell(0).setCellValue("2-目标物种和基础设施保护情况");

        XSSFRow row4 = sheet.createRow(currentRow++);
        row4.createCell(0).setCellValue("目标物种");
        row4.createCell(2).setCellValue("受损面积(亩)");
        row4.createCell(4).setCellValue("围栏设施");
        row4.createCell(6).setCellValue("看护房");

        XSSFRow row5 = sheet.createRow(currentRow++);
        row5.createCell(0).setCellValue("警示牌");
        row5.createCell(2).setCellValue("巡护路");
        row5.createCell(4).setCellValue("瞭望塔");
        row5.createCell(6).setCellValue("其他设施");

        XSSFRow row6 = sheet.createRow(currentRow++);
        row6.createCell(0).setCellValue("3-生境指标");

        XSSFRow row7 = sheet.createRow(currentRow++);
        row7.createCell(0).setCellValue("年均温");
        row7.createCell(2).setCellValue("年均降雨量");

        XSSFRow row8 = sheet.createRow(currentRow++);
        row8.createCell(0).setCellValue("4-目标物种");

        for (int i = 1; i <= targetSpecNum; i++) {
            XSSFRow row40 = sheet.createRow(currentRow++);
            row40.createCell(0).setCellValue("目标物种" + i);
            XSSFRow row41 = sheet.createRow(currentRow++);
            row41.createCell(0).setCellValue("科名");
            row41.createCell(2).setCellValue("属名");
            row41.createCell(4).setCellValue("种名");
            row41.createCell(6).setCellValue("拉丁文种名");
            XSSFRow row42 = sheet.createRow(currentRow++);
            row42.createCell(0).setCellValue("生长状况");
            row42.createCell(2).setCellValue("分布密度");
            row42.createCell(4).setCellValue("丰富度");
            row42.createCell(6).setCellValue("分布面积（亩）");
        }

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 7));
        XSSFRow rowSuper0 = sheet.createRow(currentRow++);
        rowSuper0.createCell(0).setCellValue("5-优势伴生物种");

        XSSFRow rowSuper1 = sheet.createRow(currentRow++);
        rowSuper1.createCell(0).setCellValue("科数");
        rowSuper1.createCell(2).setCellValue("属数");
        rowSuper1.createCell(4).setCellValue("种数");
        rowSuper1.createCell(6).setCellValue("总株、苗数");

        XSSFRow rowSuper2 = sheet.createRow(currentRow++);
        rowSuper2.createCell(0).setCellValue("生长状况");
        rowSuper2.createCell(2).setCellValue("群落覆盖度(%)");
        rowSuper2.createCell(4).setCellValue("分布面积（亩）");

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 5, 7));
        XSSFRow rowSuper3 = sheet.createRow(currentRow++);
        rowSuper3.createCell(0).setCellValue("返青期");
        rowSuper3.createCell(4).setCellValue("枯萎/落叶期");

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 7));
        XSSFRow rowInvade0 = sheet.createRow(currentRow++);
        rowInvade0.createCell(0).setCellValue("6-入侵物种");

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 3, 5));
        XSSFRow rowInvade1 = sheet.createRow(currentRow++);
        rowInvade1.createCell(0).setCellValue("种类数");
        rowInvade1.createCell(2).setCellValue("主要物种名称");
        rowInvade1.createCell(6).setCellValue("分布面积（亩）");

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, 7));
        XSSFRow rowInvade2 = sheet.createRow(currentRow++);
        rowInvade2.createCell(0).setCellValue("危害程度");

        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 1, 7));
        XSSFRow rowInvade3 = sheet.createRow(currentRow++);
        rowInvade3.createCell(0).setCellValue("铲除情况");

        ExportUtil.setCellStyle(workbook, sheet, currentRow, 8);

        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 7));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 7));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 7));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 7));

        return sheet;
    }

    /**
     * 处理文件信息
     */
    private String handleFileInfo(List<PictureAttForm> pictureAttForms, MonitorVo monitorVo, String fileUse) {
        if (!CollectionUtils.isEmpty(pictureAttForms)) {
            List<PictureAttVo> pictureAttVos = new ArrayList<>(pictureAttForms.size());
            StringBuilder ids = new StringBuilder();
            for (PictureAttForm paForm : pictureAttForms) {
                paForm.setSourceId(monitorVo.getId());
                paForm.setFileUse(fileUse);
                ids.append("," + paForm.getFileId());
                pictureAttVos.add(pictureAttService.save(paForm, Constants.FILE_SOURCE_MONITOR));
            }
            try {
                Method method = MonitorVo.class.getMethod(
                        new StringBuilder("set").append(StrUtil.captureName(fileUse)).toString(), List.class);
                method.invoke(monitorVo, pictureAttVos);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return ids.toString().substring(1);
        }
        return "";
    }
}
