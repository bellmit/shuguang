package com.sofn.fdpi.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.enums.PrintStatusEnum;
import com.sofn.fdpi.mapper.SignboardPrintListMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysSubsystemForm;
import com.sofn.fdpi.util.ImageUtil;
import com.sofn.fdpi.util.ZipUtil;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SignboardApplyVo;
import com.sofn.fdpi.vo.SignboardPrintListForm;
import com.sofn.fdpi.vo.SignboardVo;
import com.sofn.fdpi.vo.importBean.UploadCodeExcelBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

/**
 * 标识打印列表服务类
 *
 * @Author yumao
 * @Date 2020/1/6 16:26
 **/
@Service(value = "signboardPrintListService")
@Slf4j
public class SignboardPrintListServiceImpl implements SignboardPrintListService {

    @Resource
    private SignboardPrintListMapper signboardPrintListMapper;

    @Resource
    private SignboardApplyService signboardApplyService;

    @Resource
    @Lazy
    private SignboardPrintService signboardPrintService;

    @Resource
    private SpeService speService;

    @Resource
    private SignboardService signboardService;

    @Resource
    private SturgeonSubService sturgeonSubService;

    @Resource
    private SignboardApplyListService signboardApplyListService;

    @Resource
    private SturgeonReprintService sturgeonReprintService;

    @Resource
    @Lazy
    private TbCompService tbCompService;

    @Resource
    private SysRegionApi sysRegionApi;


    @Override
    public SignboardPrintList insertSignboardPrintList(SignboardPrintListForm form, String printId) {
        SignboardPrintList entity = new SignboardPrintList();
        String signboardId = form.getSignboardId();
        Signboard signboard = signboardService.getById(signboardId);
        if (validaPrintStatus(signboard)) {
            throw new SofnException("标识编码(" + signboard.getCode() + ")已经申请打印, 不可重复申请");
        }
        BeanUtils.copyProperties(form, entity);
        entity.setId(IdUtil.getUUId());
        entity.setPrintId(printId);
        signboardPrintListMapper.insert(entity);
        return entity;
    }

    /**
     * 验证标识打印状态
     */
    private boolean validaPrintStatus(Signboard signboard) {
        return PrintStatusEnum.ALREADY_PRINTED.getKey().equals(signboard.getPrintStatus());
    }

    @Override
    public PageUtils<SignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        SignboardPrint sp = signboardPrintService.getById(params.get("printId").toString());
        PageUtils<SignboardVo> pageUtils = signboardService.listPage(params, pageNo, pageSize);
        return pageUtils;
    }

    @Override
    @Transactional
    public void export(Map<String, Object> params, HttpServletResponse response) {
        String applyType = Objects.isNull(params.get("applyType")) ? "1" : params.get("applyType").toString();
        String printId = params.get("printId").toString();
        SignboardPrint sp = signboardPrintService.getById(printId);
        List<String> signboards;
        List<SelectVo> selectVos = null;
        Boolean isDomestic = "2".equals(applyType);
        if (isDomestic) {
            selectVos = sturgeonSubService.listSignboardCode(sp.getApplyId());
            signboards = selectVos.stream().map(SelectVo::getKey).collect(Collectors.toList());
        } else {
            signboards = signboardService.listSignboardCode(printId);
        }
        String speName = "";
        if (isDomestic) {
            speName = "鱼子酱";
        } else {
            SignboardApplyVo sav = signboardApplyService.getSignboardApply(sp.getApplyId());
            speName = Objects.isNull(sav) ? "" : sav.getSpeName();
        }

        TbComp tc = tbCompService.getCombByCode(sp.getCompCode());
        //合同号
        String contractNum = sp.getContractNum();
        Date makeTime = sp.getMakeTime();
        //当前日期字符串
        Date now = new Date();
        //标识类型
        String type = sp.getType();
        String typeCode = "0000".equals(type) ? "Zp" : type.startsWith("00") ? "Ht" : "Zp";
        String typeStr = "0000".equals(type) ? "鱼子酱" : type.startsWith("00") ? "活体类" : "制品类";
        if (Objects.isNull(makeTime)) {
            contractNum = "Ht-" + DateUtils.format(now, DateUtils.DATE_PATTERN).substring(2, 4) + "-";
            String sequenceNum = this.getSequenceNum(contractNum);
            String sequenceNum2 = sturgeonReprintService.getContractSequenceNum(contractNum);
            sequenceNum = Integer.parseInt(sequenceNum2) > Integer.parseInt(sequenceNum) ? sequenceNum2 : sequenceNum;
            contractNum += sequenceNum;
            makeTime = now;
            signboardPrintService.updateSignboardPrint(printId, contractNum);
        }
        //信息单号
        String[] contractNumArr = contractNum.split("-");
        String infoNum = "20" + contractNumArr[1] + "-" + typeCode + "-" + contractNumArr[2];
        String zipName = DateUtils.format(now, DateUtils.DATE_PATTERN) + "(" + infoNum + ")";
        String fileName = zipName + ".zip";
        OutputStream os = null;
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            os = response.getOutputStream();
            ZipOutputStream zos = new ZipOutputStream(os);

            if (isDomestic) {
                List<String> a = Lists.newArrayList();
                List<String> b = Lists.newArrayList();
                List<String> c = Lists.newArrayList();
                for (SelectVo s : selectVos) {
                    String key = s.getKey();
                    String val = s.getVal();
                    if ("A".equals(val)) {
                        a.add(key);
                    } else if ("B".equals(val)) {
                        b.add(key);
                    } else if ("S".equals(val)) {
                        c.add(key);
                    }
                }
                if (!CollectionUtils.isEmpty(a)) {
                    ZipUtil.createZip(zos, "code-a.txt", getTxtCodes(a));
                }
                if (!CollectionUtils.isEmpty(b)) {
                    ZipUtil.createZip(zos, "code-b.txt", getTxtCodes(b));
                }
                if (!CollectionUtils.isEmpty(c)) {
                    ZipUtil.createZip(zos, "code-c.txt", getTxtCodes(c));
                }
            } else {
                ZipUtil.createZip(zos, "code-1.txt", getTxtCodes(signboards));
                ZipUtil.createZip(zos, "url-1.txt", getTxtUrls(signboards));
            }
//            ZipUtil.createZip(zos, "系统字体.txt", getSysFonts());

            String[] speCodesAndNames = null;
            if (isDomestic) {
                speCodesAndNames = SturgeonReprintServiceImpl.getSpeCodesAndNames(signboards);
            }
            String codeStart = sp.getCodeStart();
            String[][] tableData = new String[][]{{"供货方", "", "信息单号：" + infoNum},
                    {"信息核验", "中国野生动物保护协会水生野生动物保护分会"},
                    {"制单日期", DateUtils.format(makeTime, DateUtils.DATE_PATTERN), "打印内容:"},
                    {"物种代码", isDomestic ? speCodesAndNames[0] : sp.getSpeCode(), "原料名称：" + (isDomestic ? speCodesAndNames[1] : speName)},
                    {"省份代码", sp.getProvinceCode(), "制品名称：" + (isDomestic ? speCodesAndNames[2] : "Zp".equals(typeCode) ? speName + "制品" : "")},
                    {"企业代码", sp.getCompCode(), "标识代码：" + (isDomestic ? SturgeonReprintServiceImpl.getCodes(signboards) : "")},
                    {"企业名称", tc.getCompName()},
                    {"制作数量", sp.getNum() + "枚", isDomestic ? "" : (StringUtils.hasText(codeStart) ? codeStart : "") + "********"}
            };
            String[][] bottomData = new String[][]{{"制单人： 王振伟  ", "核验人： 李晓菲",
                    "下单日期： " + DateUtils.format(makeTime, "yyyy年MM月dd日")}};
            String[] titel = {"专用标识制作信息单（" + typeStr + "）", "合同编号：" + contractNum};
//            ZipUtil.createZip(zos, zipName + ".png",
//                    imageToBytes(ImageUtil.creatImageBuffer(titel, tableData, bottomData), "png"));
            ZipUtil.createZip(zos, zipName + ".xlsx", getExcel(titel, tableData, bottomData));
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private byte[] getExcel(String titel[], String data[][], String bottom[][]) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(1).setCellValue(titel[0]);
        row0.createCell(2).setCellValue(titel[1]);
        row0.setHeight((short) 480);

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("供货方");
        row1.createCell(1).setCellValue("北京翰龙翔天防伪科技有限公司");
        row1.createCell(2).setCellValue(data[0][2]);

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("信息核验");
        row2.createCell(1).setCellValue("中国野生动物保护协会水生野生动物保护分会");

        XSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("制单日期");
        row3.createCell(1).setCellValue(data[2][1]);
        row3.createCell(2).setCellValue("打印内容:");

        XSSFRow row4 = sheet.createRow(4);
        row4.createCell(0).setCellValue("物种代码");
        row4.createCell(1).setCellValue(data[3][1]);
        row4.createCell(2).setCellValue(data[3][2]);

        XSSFRow row5 = sheet.createRow(5);
        row5.createCell(0).setCellValue("省份代码");
        row5.createCell(1).setCellValue(data[4][1]);
        row5.createCell(2).setCellValue(data[4][2]);

        XSSFRow row6 = sheet.createRow(6);
        row6.createCell(0).setCellValue(data[5][0]);
        row6.createCell(1).setCellValue(data[5][1]);
        row6.createCell(2).setCellValue(data[5][2]);

        XSSFRow row7 = sheet.createRow(7);
        row7.createCell(0).setCellValue(data[6][0]);
        row7.createCell(1).setCellValue(data[6][1]);
        row7.createCell(2).setCellValue(data[7][2]);

        XSSFRow row8 = sheet.createRow(8);
        row8.createCell(0).setCellValue(data[7][0]);
        row8.createCell(1).setCellValue(data[7][1]);


        XSSFRow row9 = sheet.createRow(9);
        row9.createCell(0).setCellValue(bottom[0][0]);
        row9.createCell(1).setCellValue(bottom[0][1]);
        row9.createCell(2).setCellValue(bottom[0][2]);

        //合并需要的单元格
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 2));


        sheet.setColumnWidth(0, 5500);
        sheet.setColumnWidth(1, 8500);
        sheet.setColumnWidth(2, 8000);


        XSSFCellStyle titleStyle1 = workbook.createCellStyle();
        titleStyle1.setAlignment(HorizontalAlignment.CENTER);
        titleStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        sheet.getRow(0).getCell(1).setCellStyle(titleStyle1);
        XSSFCellStyle titleStyle2 = workbook.createCellStyle();
        titleStyle2.setAlignment(HorizontalAlignment.RIGHT);
        titleStyle2.setVerticalAlignment(VerticalAlignment.BOTTOM);
        sheet.getRow(0).getCell(2).setCellStyle(titleStyle2);

        XSSFCellStyle dataStyle1 = workbook.createCellStyle();
        dataStyle1.setBorderTop(BorderStyle.THIN);
        dataStyle1.setBorderBottom(BorderStyle.THIN);
        dataStyle1.setBorderLeft(BorderStyle.THIN);
        dataStyle1.setBorderRight(BorderStyle.THIN);
        dataStyle1.setWrapText(true);
        for (int i = 1; i < 9; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = 0; j < 3; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellStyle(dataStyle1);
            }
        }

        XSSFCellStyle bottomStyle1 = workbook.createCellStyle();
        bottomStyle1.setAlignment(HorizontalAlignment.CENTER);
        sheet.getRow(9).getCell(1).setCellStyle(bottomStyle1);
        XSSFCellStyle bottomStyle2 = workbook.createCellStyle();
        bottomStyle2.setAlignment(HorizontalAlignment.RIGHT);
        sheet.getRow(9).getCell(2).setCellStyle(bottomStyle2);


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }

    @Override
    @Transactional
    public void export2(Map<String, Object> params, HttpServletResponse response) {


        String fileName = "test.zip";
        OutputStream os = null;
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            os = response.getOutputStream();
            ZipOutputStream zos = new ZipOutputStream(os);


            ZipUtil.createZip(zos, "a.png",
                    imageToBytes(ImageUtil.getImage(), "png"));
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void upload(MultipartFile multipartFile, String id) {

        SignboardPrint sp = signboardPrintService.getById(id);
        sp.setStatus(PrintStatusEnum.ALREADY_PRINTED.getKey());
        signboardPrintService.updateById(sp);
        List<String> printList = this.getPrintList(multipartFile, id);
        List<String> signboards =
                signboardService.listByPrintId(id).stream().map(Signboard::getCode).collect(Collectors.toList());
        //检验上传文件编码正确性
        this.validaPrintList(signboards, printList);
        //移除打印的标识，剩下为待删除标识
        signboards.removeAll(printList);
        //物理删除标识表多余标识
        List<String> ids = signboardService.delByCodes(signboards);
        //物理删除标识申请表多余标识
        signboardApplyListService.deleteBatchIds(ids);
//        //更改标识表已打印标识的状态为已打印
        signboardService.updatePrintStatusByPringId(id, PrintStatusEnum.ALREADY_PRINTED.getKey());
    }

    private void validaPrintList(List<String> signboards, List<String> printList) {
        for (String code : printList) {
            if (!signboards.contains(code)) {
                throw new SofnException("标识编码" + code + "有误，不在原标识编码范围内，请检查");
            }
        }
    }

    private List<String> getPrintList(MultipartFile multipartFile, String id) {
        String filename = multipartFile.getResource().getFilename();
        List<String> codes;
        if (filename.endsWith("xlsx") || filename.endsWith("xls")) {
            codes = this.getPrintListWithExcel(multipartFile, id);
        } else {
            codes = this.getPrintListWithExcelTxt(multipartFile, id);
        }
        Set<String> printSet = Sets.newHashSet();
        for (String code : codes) {
            code = code.trim();
            if (StringUtils.hasText(code))
                printSet.add(code);
        }
        Integer printSize = printSet.size();
        SignboardPrint sp = signboardPrintService.getById(id);
        if (!printSize.equals(sp.getNum())) {
            throw new SofnException("打印数量(" + sp.getNum() + ")和上传数量(" + printSize + ")不对，请检查！");
        }
        return new ArrayList<>(printSet);
    }

    private List<String> getPrintListWithExcel(MultipartFile multipartFile, String id) {
        List<UploadCodeExcelBean> excelBeans = ExcelImportUtil.getDataByExcel(multipartFile, 0, UploadCodeExcelBean.class, null);
        return excelBeans.stream().map(UploadCodeExcelBean::getCode).collect(Collectors.toList());
    }

    private List<String> getPrintListWithExcelTxt(MultipartFile multipartFile, String id) {
        List<String> codes = Lists.newArrayList();
        // 获取txt 编码列表
        try {
            byte[] byteArr = multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            byte[] getData = this.readInputStream(inputStream);
            String str = new String(getData).trim();
            if (str.contains("/r/n"))
                str = str.replaceAll("/r/n", "#");
            if (str.contains("\r\n"))
                str = str.replaceAll("\r\n", "#");
            if (str.contains("\t\n"))
                str = str.replaceAll("\t\n", "#");
            if (str.contains("\t"))
                str = str.replaceAll("\t", "#");
            if (str.contains("\n"))
                str = str.replaceAll("\n", "#");
            codes = Arrays.asList(str.split("#"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codes;
    }

    public byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    @Override
    public String getSequenceNum(String contractNum) {
        String maxSequenceNum = signboardPrintService.getYearMaxSequenceNum(contractNum);
        return StringUtils.hasText(maxSequenceNum) ?
                String.format("%06d", (Integer.valueOf(maxSequenceNum.substring(6)) + 1)) : "000001";
    }

    public byte[] getTxtCodes(List<String> codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            sb.append(codes.get(i)).append("\t\n");
        }
        return sb.toString().getBytes();
    }

    public byte[] getTxtUrls(List<String> codes) {
        Result<SysSubsystemForm> result = sysRegionApi.getSysSubsystemOneByAppId("fdpi");
        SysSubsystemForm sysSubsystemForm = result.getData();
        String url = sysSubsystemForm.getViewUrl();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.size(); i++) {
            sb.append(url).append("/signdetail/").append(codes.get(i)).append("\t\n");
        }
        return sb.toString().getBytes();
    }

    public static byte[] imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    private byte[] getSysFonts() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontList = ge.getAvailableFontFamilyNames();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fontList.length; i++) {
            sb.append(fontList[i]).append("\t\n");
        }
        return sb.toString().getBytes();
    }
}
