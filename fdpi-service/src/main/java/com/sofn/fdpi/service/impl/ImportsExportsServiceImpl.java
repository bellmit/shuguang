package com.sofn.fdpi.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.mapper.ImportsExportsMapper;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.model.ImportsExportsSpecies;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.service.ImportsExportsService;
import com.sofn.fdpi.service.ImportsExportsSpeService;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.util.ImportsListener;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2019-12-30 16:11
 */
@Slf4j
@Service("ImportsExportsService")
@Transactional
public class ImportsExportsServiceImpl extends BaseService<ImportsExportsMapper, ImportsExports> implements ImportsExportsService {
    @Resource
    private ImportsExportsMapper importsexportsmapper;
    @Resource
    private ImportsExportsSpeService imService;
    @Resource
    private SpeService speService;
    @Resource
    private RedisHelper redisHelper;
    @Resource
    private TbDepartmentService tbDepartmentService;

    /**
     * 插入进出口记录
     *
     * @param importsExports
     * @return
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertImportsExports(ImportsExports importsExports) {
        RedisUserUtil.validReSubmit("fdpi_imports_save");
        int i = 0;
        String id = IdUtil.getUUId();
        String impAuform = importsExports.getImpAuform().trim();
        ImportsExports importsExportsByImpAuform = importsexportsmapper.getImportsExportsByImpAuform(impAuform);
        if (importsExportsByImpAuform == null) {
            String impComp = importsExports.getImpComp();
            String exComp = importsExports.getExComp();
            Date validityTime = importsExports.getValidityTime();
            String visaAuth = importsExports.getVisaAuth();
            Date issueDate = importsExports.getIssueDate();
            Map map = new HashMap(11);
            map.put("id", id);
            map.put("impAuform", impAuform);
            map.put("impComp", impComp);
            map.put("exComp", exComp);
            map.put("validityTime", validityTime);
            map.put("visaAuth", visaAuth);
            map.put("issueDate", issueDate);
            map.put("createUserId", UserUtil.getLoginUserId());
            map.put("createTime", new Date());
            i = importsexportsmapper.addImoretExport(map);
            List<ImportsExportsSpecies> ies = importsExports.getIes();
            for (ImportsExportsSpecies imp :
                    ies) {
                imp.setId(IdUtil.getUUId());
                imp.setExportsId(id);
                Spe speciesByName = speService.getSpeciesByName(imp.getSpeName());
                imp.setSpeName(speciesByName.getId());
            }
            imService.addImportsExportsSpe(ies);
        }
//        redisHelper.del(Constants.REDIS_KEY_ALL_IES);
//      this.test();
        return i;
    }

    /**
     * 通过id获取出入口信息
     *
     * @param id
     * @return
     */
    @Override
    public ImportsExports getImportsExportsById(String id) {
        ImportsExports importsExports = importsexportsmapper.selectById(id);
        List<ImportsExportsSpecies> importsExportsSpeciesList = imService.selectBySourceId(id);
        importsExports.setIes(importsExportsSpeciesList);
        return importsExports;
    }

    @Override
    public PageUtils<ImportsExports> getImportsExportsListPage(Map<String, Object> map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<ImportsExports> importsExportsList = importsexportsmapper.getImportsExportsList(map);
        Boolean canPrint = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_PRINT.getKey());
        Boolean canHandle = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_RECORD.getKey());
        for (ImportsExports ie : importsExportsList) {
            ie.setCanPrint(canPrint);
            ie.setCanHandle(canHandle);
        }
        PageInfo<ImportsExports> pageInfo = new PageInfo<>(importsExportsList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateImportsExports(ImportsExportsForm importsExports) {
        RedisUserUtil.validReSubmit("fdpi_imports_update");
        Map map = new HashMap(9);
        map.put("id", importsExports.getId());
        map.put("impAuform", importsExports.getImpAuform().trim());
        map.put("impComp", importsExports.getImpComp());
        map.put("exComp", importsExports.getExComp());
        map.put("validityTime", importsExports.getValidityTime());
        map.put("visaAuth", importsExports.getVisaAuth());
        map.put("issueDate", importsExports.getIssueDate());
        map.put("updateUserId", UserUtil.getLoginUserId());
        map.put("updateTime", new Date());
        QueryWrapper<ImportsExports> qr = new QueryWrapper();
        qr.eq("IMP_AUFORM", importsExports.getImpAuform().trim()).eq("DEL_FLAG", "N").ne("ID", importsExports.getId());
        ImportsExports importsExports1 = importsexportsmapper.selectOne(qr);
        if (importsExports1 != null) {
            throw new SofnException("审批编号已存在");
        }
        importsexportsmapper.updateImportsExports(map);
        imService.del(importsExports.getId());
        List<ImportsExportsSpeciesForm> ies = importsExports.getIes();
        for (ImportsExportsSpeciesForm ie :
                ies) {
            ImportsExportsSpecies im = new ImportsExportsSpecies();
            BeanUtils.copyProperties(ie, im);
            im.setExportsId(importsExports.getId());
            im.setId(IdUtil.getUUId());
            im.setSpeName(speService.getSpeciesByName(im.getSpeName()).getId());
            imService.insert(im);
        }
//        redisHelper.del(Constants.REDIS_KEY_ALL_IES);
//        this.test();

    }

    @Override
    public int delImportsExports(String id) {
        int i = importsexportsmapper.delImportsExports(id);
        imService.del(id);
//        redisHelper.del(Constants.REDIS_KEY_ALL_IES);
//        this.test();
        return i;
    }

    @Override
    public String add(List<ImportExcel> importList) {
        if (importList.size() >= 3001) {
            throw new SofnException("一次导入的数据不超过3000");
        }
        for (int i = 0; i < importList.size(); i++) {
            ImportExcel importExcel = importList.get(i);
//            转换保护等级为code 一级：1，二级：2
            if ("一级".equals(importExcel.getProLevel())) {
                importExcel.setProLevel("1");
            } else if ("二级".equals(importExcel.getProLevel())) {
                importExcel.setProLevel("2");
            } else {
                throw new SofnException("进出口审核编号" + importExcel.getImpAuform() + "保护等级输入格式错误[一级，二级]");
            }
//            查看当前物种和保护等级是否匹配
            Map map = new HashMap();
            map.put("proLevel", importExcel.getProLevel());
            map.put("speName", importExcel.getSpeName().trim());
            Spe spe = speService.getSpe(map);
            if (spe == null) {
                throw new SofnException("进出口审核编号" + importExcel.getImpAuform() + "物种和保护级别不匹配");
            }
            //            判断进出口审核编号是否存在
            String impAuform = importExcel.getImpAuform().trim();
            ImportsExports importsExportsByImpAuform = importsexportsmapper.getImportsExportsByImpAuform(impAuform);
            ImportsExports importsExports = new ImportsExports();
            BeanUtils.copyProperties(importExcel, importsExports);
            importsExports.setImpAuform(importsExports.getImpAuform().trim());
            if (importsExportsByImpAuform == null) {
                //                插入进出口信息
                importsExports.preInsert();
                if (importExcel.getExComp() != null || importExcel.getImpComp() != null) {
                    importsExports.setId(IdUtil.getUUId());
                    importsexportsmapper.insert(importsExports);
                    //                插入进出口物种信息
                    ImportsExportsSpecies ime = new ImportsExportsSpecies();
                    BeanUtils.copyProperties(importExcel, ime);
                    ime.setExportsId(importsExports.getId());
                    ime.setId(IdUtil.getUUId());
                    ime.setSpeName(speService.getSpeciesByName(ime.getSpeName()).getId());
                    imService.insert(ime);
                } else {
                    throw new SofnException("进出口审核编号" + importExcel.getImpAuform() + "进口单位或出口单位必须存在一个");
                }

            }
            if (importsExportsByImpAuform != null) {
                importsExports.setId(importsExportsByImpAuform.getId());
//                importsExports.setExComp("N");
                importsExports.preUpdate();
                importsexportsmapper.updateById(importsExports);
                ImportsExportsSpecies ime = new ImportsExportsSpecies();
                BeanUtils.copyProperties(importExcel, ime);
                String speName = ime.getSpeName();
                ime.setSpeName(speService.getSpeciesByName(ime.getSpeName()).getId());
                ime.setExportsId(importsExports.getId());
                ImportExportCheck check = imService.check(importsExportsByImpAuform.getImpAuform(), ime.getSpeName());
                if (check != null) {
                    throw new SofnException("审批编号" + importsExportsByImpAuform.getImpAuform() + "已存在此物种" + speName);
                }
                imService.save1(ime);
            }

        }
//        redisHelper.del(Constants.REDIS_KEY_ALL_IES);
//        this.test();
        return null;
    }

    @Override
    public ImportsExports print(String id) {
        importsexportsmapper.printImports(id);
        ImportsExports importsExportsById = getImportsExportsById(id);
        return importsExportsById;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String importData(MultipartFile file, ImportsExportsService importsexportsservice) {
        log.info("开始结束");
        RedisUserUtil.validReSubmit("fdpi_imports_import", 15L);
        //        把数据库中的所有 审批编号 和主键id 放在redis中 map3
        //        将物种名字和保护等级放在redis中 名字作为键  保护等级作为值 map2
        //        map1 将物种名字和 id 放在 redis中 名字作为键 id作为值 ，再将查出来的放在一个map1里
        Map<String, String> map1 = Maps.newHashMap();
        Map<String, String> map2 = Maps.newHashMap();
        Map<String, String> map3 = Maps.newHashMap();
        this.saveCache();
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        Object redisObj = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES);
        Object redisObjforAud = redisHelper.get(Constants.REDIS_KEY_ALL_IES);
        if (redisObj != null) {
            List<SpeNameLevelVo> dropDownVos = JsonUtils.json2List(redisObj.toString(), SpeNameLevelVo.class);
            dropDownVos.forEach(o -> {
                map1.put(o.getSpeName(), o.getId());
                map2.put(o.getSpeName(), o.getProLevel());
            });
        }
        List<ImExportCacheVo> dropDownVos1 = new ArrayList<>();
        if (redisObjforAud != null) {
            dropDownVos1 = JsonUtils.json2List(redisObjforAud.toString(), ImExportCacheVo.class);
            dropDownVos1.forEach(o -> {
                map3.put(o.getImpAuform(), o.getId());
            });
        }


        try {
            InputStream is = null;
            try {
                is = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EasyExcel.read(is, ImportExcel.class, new ImportsListener(importsexportsservice, imService, map1, map2, map3, dropDownVos1)).sheet().headRowNumber(2).doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        System.out.println(new Date());
        return "";
    }

    @Override
//    @Async("asyncServiceExecutor")
    public void importsExportsWirte(List<ImportsExports> list) {
        this.saveBatch(list, 3000);
    }

    @Override
    public void saveCache() {
        redisHelper.del(Constants.REDIS_KEY_ALL_IES);
        List<ImExportCacheVo> dropDownVos = importsexportsmapper.saveRedis();
        if (!CollectionUtils.isEmpty(dropDownVos)) {
            redisHelper.set(Constants.REDIS_KEY_ALL_IES, JsonUtils.obj2json(dropDownVos));
        }
    }

    @Override
    public void downDataCountTemplate(HttpServletResponse response) {
        {
            String fileName = "进出口导入模板.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("进出口导入模板");
            HSSFRow row0 = sheet.createRow(0);
            row0.setHeight((short) (10 * 200));
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("备注（导入限制）若导入失败检查导入序号为N的数据是否满足以下条件\n" +
                    "1.除（进口单位，出口、在出口单位外）其余字段不能为空，并且进口单位，出口、在出口单位不能同时存在，也不能同时为空\n" +
                    "2.有效期大于签证日期\n" +
                    "3.物种学名来自于本系统，并且级别与物种的中国保护级别一致，并且只可导入一级和二级物种\n" +
                    "4.同一进出口审批表导入多物种信息，新建一列数据进出口信息与之前的一致，物种信息不同即可（物种信息包括后6列从物种学名），参考导入示例");
            //合并需要的单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
            HSSFCellStyle cellStyle0 = workbook.createCellStyle();
            //启动单元格内换行
            cellStyle0.setWrapText(true);
            Font font0 = workbook.createFont();
            font0.setColor(Font.COLOR_RED);
            cellStyle0.setFont(font0);
            //设置此单元格换行
            cell0.setCellStyle(cellStyle0);


            HSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("序号");
            row1.createCell(1).setCellValue("审批表编号");
            row1.createCell(2).setCellValue("进口单位");
            row1.createCell(3).setCellValue("出口、再出口单位");
            row1.createCell(4).setCellValue("有效期至");
            row1.createCell(5).setCellValue("签证机关");
            row1.createCell(6).setCellValue("签证日期");
            row1.createCell(7).setCellValue("物种学名");
            row1.createCell(8).setCellValue("保护级别");
            row1.createCell(9).setCellValue("数量或重量");
            row1.createCell(10).setCellValue("来源地或目的地");
            row1.createCell(11).setCellValue("进出口岸");

            HSSFCellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setAlignment(HorizontalAlignment.CENTER);
            cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font1 = workbook.createFont();
            font1.setColor((short) 9);
            cellStyle1.setFont(font1);
            for (int i = 0; i < 12; i++) {
                row1.getCell(i).setCellStyle(cellStyle1);
            }

            for (int i = 2; i <= 3; i++) {
                HSSFRow rowI = sheet.createRow(i);
                rowI.createCell(0).setCellValue(i - 1);
                rowI.createCell(1).setCellValue("农渔野审【2020】第110002号 ");
                rowI.createCell(2).setCellValue("新五丰");
                rowI.createCell(3).setCellValue("");
                rowI.createCell(4).setCellValue("1970/01/01");
                rowI.createCell(5).setCellValue("贵阳渔业");
                rowI.createCell(6).setCellValue("1970/01/01");
                rowI.createCell(7).setCellValue(i == 2 ? "海龟" : "虎鲸");
                rowI.createCell(8).setCellValue(i == 2 ? "一级" : "二级");
                rowI.createCell(9).setCellValue(i == 2 ? 50 : 100);
                rowI.createCell(10).setCellValue("贵阳");
                rowI.createCell(11).setCellValue("贵阳海关");
            }

            DataValidationHelper helper1 = sheet.getDataValidationHelper();
            String[] paperType = new String[]{"一级", "二级"};
            DataValidationConstraint constraint1 = helper1.createExplicitListConstraint(paperType);
            CellRangeAddressList addressList1 = null;
            DataValidation dataValidation1 = null;
            for (int i = 2; i < 3000; i++) {
                addressList1 = new CellRangeAddressList(i, i, 8, 8);
                dataValidation1 = helper1.createValidation(constraint1, addressList1);
                sheet.addValidationData(dataValidation1);
            }

            for (int i = 0; i < 15; i++) {
                // 调整每一列宽度
                sheet.autoSizeColumn(i);
                // 解决自动设置列宽中文失效的问题
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
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

}
