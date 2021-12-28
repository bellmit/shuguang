package com.sofn.fdpi.service.impl;


import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.mapper.CaptureMapper;
import com.sofn.fdpi.model.Capture;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.service.CaptureService;
import com.sofn.fdpi.service.RegionService;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.util.ImportCapListenner;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
 * @author: xiaobo
 * @Date: 2019-12-30 10:07
 */
@Service("CaptureService")
public class CaptureServiceImpl extends BaseService<CaptureMapper, Capture> implements CaptureService {
    @Resource
    private CaptureMapper capturemapper;
    @Resource
    private SysRegionApi sysRegionApi;
    @Resource
    private SpeService speService;
    @Resource
    private RegionService regionService;
    @Resource
    private RedisHelper redisHelper;
    @Resource
    private TbDepartmentService tbDepartmentService;

    /**
     * 插入特许猎捕证证书
     *
     * @param capture
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCapture(Capture capture) {
//        有效日期开始
        RedisUserUtil.validReSubmit("fdpi_cap_save");
        Date dataStart = capture.getDataStart();
//        有效日期结束
        Date dataClos = capture.getDataClos();
//        发证日期
        Date issueDate = capture.getIssueDate();
        int res = dataStart.compareTo(issueDate);
        int rs = dataClos.compareTo(issueDate);
        if (res != 1) {
            throw new SofnException("发证日期必须小于有效日期开始");
        }
        if (rs != 1) {
            throw new SofnException("发证日期必须小于有效日期结束");
        }
        int i = 0;
        Capture captureByPapersNum = capturemapper.getCaptureBypapersNumber(capture.getPapersNumber().trim());
        if (captureByPapersNum == null) {
            Spe species = speService.getSpeciesByName(capture.getSpeName());
            if (species == null) {
                throw new SofnException(capture.getSpeName() + "不存在");
            }
            capture.setSpeName(species.getId());
            capture.setProLevel(species.getProLevel());
            capture.setId(IdUtil.getUUId());
            capture.setPapersType("特许猎捕证");
            capture.setPapersNumber(capture.getPapersNumber().trim());
            capture.setDelFlag("N");
            capture.setCreateUserId(UserUtil.getLoginUserId());
            capture.setCreateTime(new Date());
            i = capturemapper.addCapture(capture);
        }
        return i;
    }

    /**
     * 通过id查看特许猎捕证书详细信息
     *
     * @param id
     * @return
     */
    @Override
    public Capture selectCaptureInfoByPapersNumber(String id) {
        Capture cap = capturemapper.getCaptureBypapersNumber(id);
        Spe spe = speService.getSpeBySpeId(cap.getSpeName());
        if (spe != null) {
            cap.setSpeName(spe.getSpeName());
            cap.setProLevel(spe.getProLevel());
        }
        return cap;
    }

    /**
     * 获取捕获证书列表（分页）
     *
     * @return
     */
    @Override
    public PageUtils<Capture> getCaptureList(Map<String, Object> map, int pageNo, int pageSize) {
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = null;
        try {
            sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取当前用户的级别失败");
        }
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();
        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
            String provinceName = sysRegionInfoByOrgId.getData().getProvince();
            map.put("province", provinceName);
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<Capture> capture = capturemapper.getCapture(map);
        Boolean canPrint = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_PRINT.getKey());
        Boolean canHandle = tbDepartmentService.isAuth(DepartmentTypeEnum.PAPER_RECORD.getKey());
        for (Capture cap : capture) {
            if (cap.getProvince() != null) {
                cap.setProvince(sysRegionApi.getSysRegionName(cap.getProvince()).getData());
            }
            if (cap.getCity() != null) {
                cap.setCity(sysRegionApi.getSysRegionName(cap.getCity()).getData());
            }
            if (cap.getArea() != null) {
                cap.setArea(sysRegionApi.getSysRegionName(cap.getArea()).getData());
            }
            cap.setCanPrint(canPrint);
            cap.setCanHandle(canHandle);
        }
        PageInfo<Capture> pageInfo = new PageInfo<>(capture);
        return PageUtils.getPageUtils(pageInfo);
    }

    /**
     * 删除特许猎捕证
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delCapture(String id, String proLevel) {
        Capture cap = capturemapper.selectById(id);
        if (cap == null) {
            throw new SofnException("待删除ID不存在");
        }
        Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery;
        try {
            sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取当前用户的级别失败");
        }
//        1:代表国家保护级别 1级 2：代表国家保护级别 2级
        String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();
        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            if ("2".equals(cap.getProLevel())) {
                return capturemapper.removeCapture(id);
            } else {
                throw new SofnException("省级用户不能删除国家1级证书");
            }
        }
        if (OrganizationLevelEnum.DIRECT_AND_MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            if ("1".equals(cap.getProLevel())) {
                return capturemapper.removeCapture(id);
            } else {
                throw new SofnException("部级用户不能删除国家2级证书");
            }
        }
        return 0;
    }

    /**
     * 修改特许猎捕证信息
     *
     * @param capture
     * @return
     */
    @Override
    public int updateCapture(Capture capture) {
        RedisUserUtil.validReSubmit("fdpi_cap_update");
        //        有效日期开始
        Date dataStart = capture.getDataStart();
//        有效日期结束
        Date dataClos = capture.getDataClos();
//        发证日期
        Date issueDate = capture.getIssueDate();
        int res = dataStart.compareTo(issueDate);
        int rs = dataClos.compareTo(issueDate);
        if (res != 1) {
            throw new SofnException("发证日期必须小于有效日期开始");
        }
        if (rs != 1) {
            throw new SofnException("发证日期必须小于有效日期结束");
        }
        Map map = Maps.newHashMap();
        map.put("id", capture.getId());
        map.put("papersNumber", capture.getPapersNumber().trim());
        Capture oneByNumber = capturemapper.getOneByNumber(map);
        Capture capture1 = capturemapper.selectById(capture.getId());
        if (oneByNumber == null) {
            Spe species = speService.getSpeciesByName(capture.getSpeName());
            if (species == null) {
                throw new SofnException(capture.getSpeName() + "不存在");
            }
            capture.setIsPrint(capture1.getIsPrint());
            capture.setSpeName(species.getId());
            capture.setProLevel(species.getProLevel());
            capture.setPapersNumber(capture.getPapersNumber().trim());
            return capturemapper.updateCapture(capture);
        }
        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(List<CaptureExcel> importList) {
        String sysOrgLevelId = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery().getData().getSysOrgLevelId();
        String resultMsg = "";
        for (int i = 0; i < importList.size(); i++) {
            Capture capture = new Capture();
            CaptureExcel CaptureExcel = importList.get(i);
            BeanUtils.copyProperties(CaptureExcel, capture);
            String proLevel = CaptureExcel.getProLevel();


//            根据录入的省市县名来找到对应的code
            Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(CaptureExcel.getProvince().trim());
            if ("".equals(sysRegionByName.getData().getRegionCode())) {
                throw new SofnException("请检查导入猎捕地点（省），省份名错误");
            }
            capture.setProvince(sysRegionByName.getData().getRegionCode());
            Result<SysRegionForm> sysRegionByName1 = sysRegionApi.getSysRegionByName(CaptureExcel.getCity().trim());
            if ("".equals(sysRegionByName1.getData().getRegionCode())) {
                throw new SofnException("请检查导入猎捕地点（市），市名错误");
            }
            capture.setCity(sysRegionByName1.getData().getRegionCode());
            Result<SysRegionForm> sysRegionByName2 = sysRegionApi.getSysRegionByName(CaptureExcel.getArea().trim());
            if ("".equals(sysRegionByName2.getData().getRegionCode())) {
                throw new SofnException("请检查导入猎捕地点（区），区名错误");
            }
            capture.setArea(sysRegionByName2.getData().getRegionCode());
//             导入数据用户为省级  则导入地点必须是登录用户所在的省份
            if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
                //            获取当前登录用户的所在省份
                if (capture.getProvince() != null) {
                    Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
                    String sysOrgProvince = sysRegionInfoByOrgId.getData().getProvince();
                    if (!capture.getProvince().equals(sysOrgProvince)) {
                        throw new SofnException("录入的捕获地点必须为本省");
                    }
//                    部级用户录入1级物种 省级录入2级物种
                    if ("1".equals(proLevel)) {
                        throw new SofnException("录入的物种必须是二级物种");
                    }
                }

            }
//            导入数据用户为部级  导入地点不做限制，但是导入物种保护级别为1级
            if (OrganizationLevelEnum.MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
                if ("2".equals(proLevel)) {
                    throw new SofnException("部级用户录入的物种必须是一级物种");
                }
            }
            Map<String, Object> map = new HashMap(2);
            map.put("proLevel", capture.getProLevel());
            map.put("speName", capture.getSpeName().trim());
//            根据保护级别和物种名查询导入数据物种是否已录入且保护级别于部级录入的级别一致
            Spe spe = speService.getSpe(map);
//           查询 证书编号 是否已存在
            if (spe == null) {
                throw new SofnException(CaptureExcel.getSpeName() + "与保护级别不匹配");
            }
            Capture captureByPapersNum = capturemapper.getCaptureBypapersNumber(capture.getPapersNumber().trim());
            if (captureByPapersNum != null) {
                throw new SofnException(CaptureExcel.getPapersNumber() + "该证书编号已存在");
            }
            if (spe != null && captureByPapersNum == null) {
                capture.preInsert();
                capture.setPapersType("特许猎捕证");
                Date dateS = null;
                Date dateC = null;
                try {
                    String term = CaptureExcel.getTerm().trim();
                    String dataS = term.substring(0, 10);
                    String dataC = term.substring(11);
                    String pattern = "yyyy/MM/dd";
                    dateS = DateUtils.stringToDate(dataS, pattern);
                    dateC = DateUtils.stringToDate(dataC, pattern);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SofnException("有效期格式错误，请检查有效期格式");
                }
//                加入时间判断
                Date issueDate = capture.getIssueDate();
                int res = dateS.compareTo(issueDate);
                int rs = dateC.compareTo(issueDate);
                int rs2 = dateC.compareTo(dateS);
                if (rs2 != 1) {
                    throw new SofnException("有效日期结束要大于有效日期开始");
                }
                if (res != 1) {
                    throw new SofnException("有效日期开始时间要大于发证日期");
                }
                if (rs != 1) {
                    throw new SofnException("有效日期结束时间要大于发证日期");
                }
                capture.setDataClos(dateS);
                capture.setDataStart(dateC);
                Spe species = speService.getSpeciesByName(capture.getSpeName());
                if (species == null) {
                    throw new SofnException(capture.getSpeName() + "不存在");
                }
                capture.setPapersNumber(capture.getPapersNumber().trim());
                capture.setSpeName(species.getId());
                capture.setProLevel(species.getProLevel());
                capture.setId(IdUtil.getUUId());
                int add = capturemapper.insert(capture);
            }

        }
        return resultMsg;
    }

    @Override
    public Capture print(String id) {
        capturemapper.printCap(id);
        Capture capture = capturemapper.selectById(id);
        Spe spe = speService.getSpeBySpeId(capture.getSpeName());
        if (spe != null) {
            capture.setSpeName(spe.getSpeName());
            capture.setProLevel(spe.getProLevel());
        }
        return capture;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String importData(MultipartFile file, CaptureService captureService) {
        RedisUserUtil.validReSubmit("fdpi_cap_import", 15L);
        String sysOrgLevelId = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery().getData().getSysOrgLevelId();
        Map<String, String> map = Maps.newHashMap();
        Map<String, String> map1 = Maps.newHashMap();
        Map<String, String> map2 = Maps.newHashMap();
        Map<String, String> map3 = Maps.newHashMap();
//        重设缓存
        this.setCache();
        Object redisforSpe = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES);
        Object redisforCap = redisHelper.get(Constants.REDIS_KEY_ALL_CAP);
        List<SpeNameLevelVo> dropDownVos = JsonUtils.json2List(redisforSpe.toString(), SpeNameLevelVo.class);
        List<CapCacheVo> capCacheVo = new ArrayList<>();
        if (redisforCap != null) {
            capCacheVo = JsonUtils.json2List(redisforCap.toString(), CapCacheVo.class);
        }

//        如果是省级 把二级物种放在 map1中
        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            dropDownVos.forEach(o -> {
                map.put(o.getSpeName(), o.getId());
                map2.put(o.getSpeName(), o.getProLevel());
                if ("2".equals(o.getProLevel())) {
                    map1.put(o.getSpeName(), o.getId());
                }
            });
        } else {
//            如果是部级 把一级物种放在map中
            dropDownVos.forEach(o -> {
                map2.put(o.getSpeName(), o.getProLevel());
                if ("1".equals(o.getProLevel())) {
                    map1.put(o.getSpeName(), o.getId());
                }
            });
        }
//        将证书编号和id放入map中
        if (capCacheVo != null) {
            capCacheVo.forEach(o -> {
                map3.put(o.getPapersNumber(), o.getId());
            });
        }

        try {
            InputStream is = null;
            try {
                is = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EasyExcel.read(is, CaptureExcel.class, new ImportCapListenner(captureService, sysRegionApi, sysOrgLevelId, map, map1, map2, map3)).sheet().headRowNumber(2).doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
        return null;
    }

    @Override
    public void setCache() {
        redisHelper.del(Constants.REDIS_KEY_ALL_CAP);
        List<CapCacheVo> capCache = capturemapper.getCapCache();
        if (!CollectionUtils.isEmpty(capCache)) {
            redisHelper.set(Constants.REDIS_KEY_ALL_CAP, JsonUtils.obj2json(capCache));
        }
    }

    @Override
    public void downDataCountTemplate(HttpServletResponse response) {
        {
            String fileName = "特许猎捕证模板.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("特许猎捕证模板");
            XSSFRow row0 = sheet.createRow(0);
            row0.setHeight((short) (12 * 200));
            Cell cell0 = row0.createCell(0);

            cell0.setCellValue("备注（导入限制）若导入失败检查导入序号为N的数据是否满足以下条件：\n" +
                    "1.所有字段均不能为空\n" +
                    "2.导入的证书编号唯一\n" +
                    "3.物种学名要在本系统中存在\n" +
                    "4.保护级别与物种的中国保护级别一致\n" +
                    "5.发证日期<有效日期开始<有效日期结束\n" +
                    "6.省级用户只能录入捕获地点为本省下的省市区\n" +
                    "7.捕获地点的省市区要与系统中的省市区名称对应\n" +
                    "8.省级用户只可以导入保护级别为二级的物种、部级用户只可以导入保护级别为一级的物种\n" +
                    "9.导入数据时，红字行不可删除");
            //合并需要的单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
            XSSFCellStyle cellStyle0 = workbook.createCellStyle();
            //启动单元格内换行
            cellStyle0.setWrapText(true);
            Font font0 = workbook.createFont();
            font0.setColor(Font.COLOR_RED);
            cellStyle0.setFont(font0);
            //设置此单元格换行
            cell0.setCellStyle(cellStyle0);


            XSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("序号");
            row1.createCell(1).setCellValue("证书编号");
            row1.createCell(2).setCellValue("猎捕持证单位（人）");
            row1.createCell(3).setCellValue("批准文号");
            row1.createCell(4).setCellValue("猎捕事由");
            row1.createCell(5).setCellValue("保护级别");
            row1.createCell(6).setCellValue("物种学名（物种名要在本系统中存在）");
            row1.createCell(7).setCellValue("数量或重量");
            row1.createCell(8).setCellValue("有效期");
            row1.createCell(9).setCellValue("猎捕地点（省/直辖市）");
            row1.createCell(10).setCellValue("猎捕地点（市）");
            row1.createCell(11).setCellValue("猎捕地点（区）");
            row1.createCell(12).setCellValue("猎捕地点（具体地点）");
            row1.createCell(13).setCellValue("猎捕方式");
            row1.createCell(14).setCellValue("发证机关");
            row1.createCell(15).setCellValue("发证日期");

            XSSFCellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setAlignment(HorizontalAlignment.CENTER);
            cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font1 = workbook.createFont();
            font1.setColor((short) 9);
            cellStyle1.setFont(font1);
            for (int i = 0; i < 16; i++) {
                row1.getCell(i).setCellStyle(cellStyle1);
            }

            for (int i = 2; i <= 2; i++) {
                XSSFRow rowI = sheet.createRow(i);
                rowI.createCell(0).setCellValue(i - 1);
                rowI.createCell(1).setCellValue("JGDSHHS");
                rowI.createCell(2).setCellValue("腾讯");
                rowI.createCell(3).setCellValue("（国渔）水野驯繁字（2018）111024号");
                rowI.createCell(4).setCellValue("抓获");
                rowI.createCell(5).setCellValue("二级");
                rowI.createCell(6).setCellValue("白花鱼");
                rowI.createCell(7).setCellValue(50);
                rowI.createCell(8).setCellValue("2021/01/02-2022/02/02");
                rowI.createCell(9).setCellValue("四川省");
                rowI.createCell(10).setCellValue("成都市");
                rowI.createCell(11).setCellValue("武侯区");
                rowI.createCell(12).setCellValue("天益街38号1栋附8号");
                rowI.createCell(13).setCellValue("抓捕");
                rowI.createCell(14).setCellValue("省渔业渔政主管部门");
                rowI.createCell(15).setCellValue("2020-12-18");
            }

            DataValidationHelper helper1 = sheet.getDataValidationHelper();
            String[] paperType = new String[]{"国家一级", "国家二级"};
            DataValidationConstraint constraint1 = helper1.createExplicitListConstraint(paperType);
            CellRangeAddressList addressList1 = null;
            DataValidation dataValidation1 = null;
            for (int i = 2; i < 3000; i++) {
                addressList1 = new CellRangeAddressList(i, i, 5, 5);
                dataValidation1 = helper1.createValidation(constraint1, addressList1);
                sheet.addValidationData(dataValidation1);
            }

            for (int i = 0; i < 14; i++) {
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

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "特许猎捕证信息.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("特许猎捕证信息");
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("序号");
        row0.createCell(1).setCellValue("证书编号");
        row0.createCell(2).setCellValue("猎捕持证单位（人）");
        row0.createCell(3).setCellValue("批文编号");
        row0.createCell(4).setCellValue("猎捕事由");
        row0.createCell(5).setCellValue("保护级别");
        row0.createCell(6).setCellValue("物种学名（物种名要在本系统中存在）");
        row0.createCell(7).setCellValue("数量或重量");
        row0.createCell(8).setCellValue("有效期");
        row0.createCell(9).setCellValue("猎捕地点（省/直辖市）");
        row0.createCell(10).setCellValue("猎捕地点（市）");
        row0.createCell(11).setCellValue("猎捕地点（区）");
        row0.createCell(12).setCellValue("猎捕地点（具体地点）");
        row0.createCell(13).setCellValue("猎捕方式");
        row0.createCell(14).setCellValue("发证机关");
        row0.createCell(15).setCellValue("发证日期");

        XSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font1 = workbook.createFont();
        font1.setColor((short) 9);
        cellStyle1.setFont(font1);
        for (int i = 0; i < 16; i++) {
            row0.getCell(i).setCellStyle(cellStyle1);
        }
        Map<String, String> areaMap = regionService.getAreaDataMap();
        List<Capture> capture = capturemapper.list(params);
        int captureSize = capture.size();
        for (int i = 1; i <= captureSize; i++) {
            Capture c = capture.get(i - 1);
            XSSFRow rowI = sheet.createRow(i);
            rowI.createCell(0).setCellValue(i);
            rowI.createCell(1).setCellValue(c.getPapersNumber());
            rowI.createCell(2).setCellValue(c.getCapUnit());
            rowI.createCell(3).setCellValue(c.getAppNum());
            rowI.createCell(4).setCellValue(c.getCause());
            rowI.createCell(5).setCellValue("1".equals(c.getProLevel()) ? "国家一级" : "2".equals(c.getProLevel()) ? "国家二级" : "");
            rowI.createCell(6).setCellValue(c.getSpeName());
            rowI.createCell(7).setCellValue(c.getCapNum());
            rowI.createCell(8).setCellValue(DateUtils.format(
                    c.getDataStart(), "yyyy/MM/dd") + "-" + DateUtils.format(c.getDataClos(), "yyyy/MM/dd"));
            rowI.createCell(9).setCellValue(areaMap.get(c.getProvince()));
            rowI.createCell(10).setCellValue(areaMap.get(c.getCity()));
            rowI.createCell(11).setCellValue(areaMap.get(c.getArea()));
            rowI.createCell(12).setCellValue(c.getCapLocal());
            rowI.createCell(13).setCellValue(c.getCapWay());
            rowI.createCell(14).setCellValue(c.getIssueUnit());
            rowI.createCell(15).setCellValue(DateUtils.format(c.getIssueDate()));
        }
        for (int i = 0; i < 14; i++) {
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

