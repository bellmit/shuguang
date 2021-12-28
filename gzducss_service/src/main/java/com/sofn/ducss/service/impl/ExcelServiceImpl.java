package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.excel.ExcelImportUtil;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExportExcel;
import com.sofn.ducss.model.excelmodel.StrawUtilizeExcel;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExcel;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExportExcel;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.*;
import com.sofn.ducss.vo.excelVo.DataAnalysisExcelVo;
import com.sofn.ducss.vo.excelVo.DisperseUtilizeExcelVo;
import com.sofn.ducss.vo.excelVo.StrawUtilizeExcelVo;
import com.sofn.ducss.vo.excelVo.YieldAndReturnExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName ExcelServiceImpl
 * @Description Excel导入导出相关
 * @Author Administrator
 * @Date 2020/7/1 10:15
 * Version1.0
 **/
@Service
@Slf4j
public class ExcelServiceImpl extends ServiceImpl<ProStillDetailMapper, ProStillDetail> implements ExcelService {
    @Autowired
    private CountryTaskMapper countryTaskMapper;
    @Autowired
    private DisperseUtilizeMapper disperseUtilizeMapper;

    @Autowired
    DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    StoredProcedureMapper storedProcedureMapper;

    @Autowired
    private ProStillDetailService proStillDetailService;
    @Autowired
    private ProStillService proStillService;
    @Autowired
    private DisperseUtilizeDetailService disperseUtilizeDetailService;
    @Autowired
    private StrawUtilizeDetailService strawUtilizeDetailService;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private AsyncService asyncService;

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        String fileName = "产生量与直接还田量导入模板.xlsx";
        List<YieldAndReturnExcel> exportList = new ArrayList<>();
        //生成导入示例数据
        YieldAndReturnExcel basicInformationExcel = new YieldAndReturnExcel();
        basicInformationExcel.setCollectionRatio(new BigDecimal(0.00));
        basicInformationExcel.setExportYield(new BigDecimal(0.00));
        basicInformationExcel.setGrainYield(new BigDecimal(0.00));
        basicInformationExcel.setGrassValleyRatio(new BigDecimal(0.00));
        basicInformationExcel.setReturnArea(new BigDecimal(0.00));
        basicInformationExcel.setSeedArea(new BigDecimal(0.00));
        basicInformationExcel.setStrawType("早稻");
        exportList.add(basicInformationExcel);
        response.setCharacterEncoding("utf-8");
        ExportUtil.createExcel(YieldAndReturnExcel.class, exportList, response, fileName);
    }

    //todo 在导入时把系数回填设置
    @Override
    public Result importExcel(MultipartFile file, String year) {
        //查询条件组装
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("请检查当年任务是否生成!");
        }
        //获取excel表格数据,判断数据是否符合要求
        //对不符合要求数据给出提示
        List<YieldAndReturnExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, YieldAndReturnExcelVo.class, null);
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前用户所在机构对象信息
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        for (YieldAndReturnExcelVo excel : dataList) {
            if (null == excel.getSeedArea()) {
                excel.setSeedArea(new BigDecimal(0));
            }
            if (null == excel.getReturnArea()) {
                excel.setReturnArea(new BigDecimal(0));
            }
            if (null == excel.getExportYield()) {
                excel.setExportYield(new BigDecimal(0));
            }

            if (excel.getSeedArea().compareTo(excel.getReturnArea()) < 0) {
                return Result.error(excel.getStrawType() + "-行中【播种面积】不能小于【还田面积】");
            }
            if (excel.getExportYield().compareTo(
                    excel.getGrainYield()
                            .multiply(excel.getGrassValleyRatio())
                            .multiply(excel.getCollectionRatio())) > 0) {
                return Result.error(excel.getStrawType() + "-行中【秸秆调出量】不能大于【可收集资源量】");
            }

            resultMap = CropsEnum.checkValue(resultMap, excel.getGrainYield(), excel.getGrassValleyRatio(), excel.getStrawType(), excel.getStrawType());
            String message = (String) resultMap.get("strawStr") + resultMap.get("threshold");
            if (!resultMap.isEmpty())
                return Result.error(message);
        }
        //数据正常，则将数据录入到数据库中
        ProStillVo proStillVo = new ProStillVo();
        proStillVo.setYear((String) params.get("year"));
        proStillVo.setDepartment(organization.getOrganizationName());
        proStillVo.setProStillId("");
        proStillVo.setAddTime(new Date());
        List<ProStillDetail> proStillDetails = new ArrayList<>(dataList.size());
        proStillDetails = copyExcelValueToDetail(dataList, proStillDetails);
        proStillVo.setProStillDetails(proStillDetails);
        //调用统一方法添加数据
        String result = proStillDetailService.addOrUpdateProStill(proStillVo, UserUtil.getLoginUserId());
        return Result.ok(result);
    }

    @Override
    public void downloadDisperseUtilizeTemplate(HttpServletResponse response) throws IOException {
        //String fileName = "2020年农户分散利用量导入模板.xlsx";
        /*
        List<DisperseUtilizeExcel> excelList = new ArrayList<>();
        //生成导入示例数据
        DisperseUtilizeExcel basicInformationExcel = new DisperseUtilizeExcel();
        basicInformationExcel.setAddress("xx镇xx村");
        basicInformationExcel.setBase(new BigDecimal(0.00));
        basicInformationExcel.setFarmerNo("00001");
        basicInformationExcel.setFarmerName("张三");
        basicInformationExcel.setFarmerPhone("13800000000");
        basicInformationExcel.setFertilising(new BigDecimal(0.00));
        basicInformationExcel.setForage(new BigDecimal(0.00));
        basicInformationExcel.setFuel(new BigDecimal(0.00));
        basicInformationExcel.setMaterial(new BigDecimal(0.00));
        basicInformationExcel.setReuse(new BigDecimal(0.00));
        basicInformationExcel.setSownArea(new BigDecimal(0.00));
        basicInformationExcel.setStrawName("早稻");
        basicInformationExcel.setYieldPerMu(new BigDecimal(0.00));

        excelList.add(basicInformationExcel);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
        response.setHeader("Content-Disposition", "attachment;filename=农户分散利用量导入模板.xlsx");
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        ExcelExportUtil.createExcel(response, DisperseUtilizeExcel.class, excelList, "农户分散利用量导入模板.xlsx");
        */
        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/农户分散利用量导入模板.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, 1);
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("农户分散利用量导入模板.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    @Override
    public Result importDisperseUtilize(MultipartFile file, String year) {
        //查询条件组装
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("请检查当年任务是否创建!");
        }
        //判断产生量与直接还田量是否完成
        ProStill proStill = proStillService.getProStill((String) params.get("year"), (String) params.get("areaId"));
        if (null == proStill) {
            throw new SofnException("请检查产生量与直接还田量的填写是否完成!");
        }
        //获取当前用户所在机构对象信息
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        //获取excel表格数据,判断数据是否符合要求
        //对不符合要求数据给出提示
        List<DisperseUtilizeExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, DisperseUtilizeExcelVo.class, 0);
        checkDate2(dataList);
        //以农户分组
        Map<String, List<DisperseUtilizeExcelVo>> voGroupMap = dataList.stream().collect(Collectors.groupingBy(
                DisperseUtilizeExcelVo::getFarmerNo));
        String result = "";
        //循环分组，对数据进行分组处理
        for (Map.Entry<String, List<DisperseUtilizeExcelVo>> entry : voGroupMap.entrySet()) {
            List<DisperseUtilizeExcelVo> voList = entry.getValue();
            if (null == voList || voList.isEmpty()) continue;

            //数据正常，则将数据录入到数据库中
            DisperseUtilizeVo disperseUtilizeVo = new DisperseUtilizeVo();
            disperseUtilizeVo.setYear((String) params.get("year"));
            disperseUtilizeVo.setDepartment(organization.getOrganizationName());
            disperseUtilizeVo.setDisperseUtilizeId("");
            disperseUtilizeVo.setAddTime(new Date());
            disperseUtilizeVo.setAddress(voList.get(0).getAddress());
            disperseUtilizeVo.setFarmerName(voList.get(0).getFarmerName());
            disperseUtilizeVo.setFarmerNo(voList.get(0).getFarmerNo());
            disperseUtilizeVo.setFarmerPhone(voList.get(0).getFarmerPhone());
            List<DisperseUtilizeDetail> details = new ArrayList<>(voList.size());
            details = copyExcelValToDetail(voList, details);
            disperseUtilizeVo.setDisperseUtilizeDetailList(details);
            //调用统一方法添加数据
            result = disperseUtilizeDetailService.addOrUpdateDisperseUtilizeDetail(disperseUtilizeVo, UserUtil.getLoginUserId());

        }

//        //数据正常，则将数据录入到数据库中
//        DisperseUtilizeVo disperseUtilizeVo = new DisperseUtilizeVo();
//        disperseUtilizeVo.setYear((String) params.get("year"));
//        disperseUtilizeVo.setDepartment(organization.getOrganizationName());
//        disperseUtilizeVo.setDisperseUtilizeId("");
//        disperseUtilizeVo.setAddTime(new Date());
//        disperseUtilizeVo.setAddress(dataList.get(0).getAddress());
//        disperseUtilizeVo.setFarmerName(dataList.get(0).getFarmerName());
//        disperseUtilizeVo.setFarmerNo(dataList.get(0).getFarmerNo());
//        disperseUtilizeVo.setFarmerPhone(dataList.get(0).getFarmerPhone());
//        List<DisperseUtilizeDetail> details = new ArrayList<>(dataList.size());
//        details = copyExcelValToDetail(dataList, details);
//        disperseUtilizeVo.setDisperseUtilizeDetailList(details);
//        //调用统一方法添加数据
//        String result = disperseUtilizeDetailService.addOrUpdateDisperseUtilizeDetail(disperseUtilizeVo, UserUtil.getLoginUserId());
        return Result.ok(result);
    }

    /***
     * 导入农户分散利用excel xl 2021/03/29
     * @param file
     * @param year
     * @return
     */
    @Override
    public Result importDisperseUtilizeV2(MultipartFile file, String year) {
        //查询条件组装
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("请检查当年任务是否创建!");
        }
        if (tasks == null && tasks.size() <= 0) {
            throw new SofnException("请检查当年任务是否生成！");
        }
        CountryTask task = tasks.get(0);
        if (task.getStatus() == Constants.ExamineState.REPORTED
                || task.getStatus() == Constants.ExamineState.READ
                || task.getStatus() == Constants.ExamineState.PASSED) {
            throw new SofnException("当前年度填报任务已经上报不能导入！");
        }
        //判断产生量与直接还田量是否完成
        ProStill proStill = proStillService.getProStill((String) params.get("year"), (String) params.get("areaId"));
        if (null == proStill) {
            throw new SofnException("请检查产生量与直接还田量的填写是否完成!");
        }
        //获取当前用户所在机构对象信息
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        if (organization == null) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        String regioncode = organization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(organization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);
        List<DisperseUtilizeExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 1, DisperseUtilizeExcelVo.class, 0);
        if (dataList != null && dataList.size() > 0) { // 如果excel表不为空
            checkDate2(dataList);
            //以农户分组
            Map<String, List<DisperseUtilizeExcelVo>> voGroupMap = dataList.stream().collect(Collectors.groupingBy(
                    DisperseUtilizeExcelVo::getFarmerNo));
            List<DisperseUtilizeVo> disperseUtilizeVoList = new ArrayList<>();
            DisperseUtilizeVo disperseUtilizeVo = null;
            String utilizeId = null;
            List<DisperseUtilizeDetail> details = null;
            for (Map.Entry<String, List<DisperseUtilizeExcelVo>> entry : voGroupMap.entrySet()) {
                List<DisperseUtilizeExcelVo> voList = entry.getValue();
                if (voList.get(0).getFarmerName().trim() == null || "".equals(voList.get(0).getFarmerName())){
                    throw new SofnException("户主名称不能为空");
                }
                Integer flag = disperseUtilizeMapper.isDisperseExists(year, voList.get(0).getFarmerName().trim(), voList.get(0).getFarmerPhone(), countyId);
                if (flag > 0) { // 如果数据库有重复数据，该excel不能导入
                    throw new SofnException("该excel表年度农户数据已存在,不能导入！");
                }
                disperseUtilizeVo = new DisperseUtilizeVo();
                disperseUtilizeVo.setYear((String) params.get("year"));
                disperseUtilizeVo.setDepartment(organization.getOrganizationName());
                utilizeId = IdUtil.getUUId();
                disperseUtilizeVo.setDisperseUtilizeId(utilizeId);
                disperseUtilizeVo.setAddTime(new Date());
                disperseUtilizeVo.setAddress(voList.get(0).getAddress());
                disperseUtilizeVo.setFarmerName(voList.get(0).getFarmerName().trim());
                String farmerNo = disperseUtilizeDetailService.createFillNumber("FS"+ countyId);
                disperseUtilizeVo.setFarmerNo(farmerNo);
                disperseUtilizeVo.setFarmerPhone(voList.get(0).getFarmerPhone());
                details = new ArrayList<>();
                details = copyExcelValToDetail(voList, details);
                for (int i = 0; i < details.size(); i++) {
                    if ((details.get(i).getReuse().compareTo(new BigDecimal(0)) == 1) && StringUtils.isEmpty(details.get(i).getApplication())) {
                        throw new SofnException("请填写农户 " + details.get(i).getFarmerName() + " 的[" + details.get(i).getStrawName() + "]的收集利用量用途类型");
                    }
                    details.get(i).setId(IdUtil.getUUId());
                    details.get(i).setUtilizeId(utilizeId);
                }
                disperseUtilizeVo.setDisperseUtilizeDetailList(details);
                disperseUtilizeVoList.add(disperseUtilizeVo);
            }
            List<DisperseUtilize> disperseUtilizeList = null;  // 农户上报数据
            List<DisperseUtilizeDetail> disperseUtilizeDetailList = null; // 农户上报详情
            if (disperseUtilizeVoList != null && disperseUtilizeVoList.size() > 0) { // 进行数据装换
                disperseUtilizeList = new ArrayList<>();
                disperseUtilizeDetailList = new ArrayList<>();
                DisperseUtilize disperseUtilize = null;
                for (int i = 0; i < disperseUtilizeVoList.size(); i++) { // 农户上报数据装换
                    disperseUtilize = new DisperseUtilize();
                    disperseUtilize.setId(disperseUtilizeVoList.get(i).getDisperseUtilizeId());
                    disperseUtilize.setFillNo(disperseUtilizeVoList.get(i).getFarmerNo().substring(2));
                    disperseUtilize.setAreaId(countyId);
                    disperseUtilize.setCityId(cityId);
                    disperseUtilize.setProvinceId(provinceId);
                    disperseUtilize.setCreateDate(new Date());
                    disperseUtilize.setAddress(disperseUtilizeVoList.get(i).getAddress());
                    disperseUtilize.setCreateUserId(UserUtil.getLoginUserId());
                    disperseUtilize.setCreateUserName(UserUtil.fetchLoginUserNameInToken());
                    disperseUtilize.setReportArea(disperseUtilizeVoList.get(i).getDepartment());
                    disperseUtilize.setFarmerName(disperseUtilizeVoList.get(i).getFarmerName().trim());
                    disperseUtilize.setFarmerNo(disperseUtilizeVoList.get(i).getFarmerNo());
                    disperseUtilize.setFarmerPhone(disperseUtilizeVoList.get(i).getFarmerPhone());
                    disperseUtilize.setYear(disperseUtilizeVoList.get(i).getYear());
                    disperseUtilize.setCreateTime(new Date());
                    disperseUtilizeList.add(disperseUtilize);
                    disperseUtilizeDetailList.addAll(disperseUtilizeVoList.get(i).getDisperseUtilizeDetailList());
                }
                Integer count = disperseUtilizeMapper.insertBatch(disperseUtilizeList); // 批量新增
                if (count > 0) { // 修改上报统计
                    countryTaskMapper.updateDynamicNumById(task.getId(), disperseUtilizeList.size(), disperseUtilizeList.size()); // 修改填报的统计数量
                }
                List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE);
                List<DisperseUtilizeDetail> list3 = new ArrayList<>();
                for (int i = 0; i < disperseUtilizeDetailList.size(); i++) { // 农户上报详情数据装换
                    if ((disperseUtilizeDetailList.get(i).getReuse().compareTo(new BigDecimal(0)) == 1) && StringUtils.isEmpty(disperseUtilizeDetailList.get(i).getApplication())) {
                        throw new SofnException("请填写农户 " + disperseUtilizeDetailList.get(i).getFarmerName() + " 的[" + disperseUtilizeDetailList.get(i).getStrawName() + "]的收集利用量用途类型");
                    }
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getDictname().equals(disperseUtilizeDetailList.get(i).getStrawName())) {
                            list3.add(disperseUtilizeDetailList.get(i));
                        }
                    }
                }
                // 如果导入
                if (list3 != null && list3.size() > 0) {//  // 新增批量新增详情
                    List<List<DisperseUtilizeDetail>> lists = com.sofn.ducss.util.ListUtils.groupList(list3, 1000);
                    CountDownLatch countDownLatch = new CountDownLatch(lists.size());
                    for (List<DisperseUtilizeDetail> listSub : lists) {
                        asyncService.executeAsync(listSub, disperseUtilizeDetailMapper, countDownLatch);
                    }
                    StoredProcedure storedProcedure = null;
                    List<StoredProcedure> storedProcedureList = new ArrayList<>();
                    for (DisperseUtilizeDetail proStillDetail : list3) {
                        storedProcedure = new StoredProcedure();
                        storedProcedure.setId(UUID.randomUUID().toString());
                        storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                        storedProcedure.setAreaIdData(countyId);
                        storedProcedure.setYearData(year);
                        storedProcedureList.add(storedProcedure);
                    }
                    List<List<StoredProcedure>> StoredProcedureLists = com.sofn.ducss.util.ListUtils.groupList(storedProcedureList, 1000);
                    for (List<StoredProcedure> listSub : StoredProcedureLists) {
                        asyncService.excuteAsyncByStored(listSub, storedProcedureMapper, countDownLatch);
                    }
                }
            }
        }
        LogUtil.addLog(LogEnum.LOG_TYPE_ADD.getCode(), "数据导入新增-" + year + "-<分散利用量填报>");
        return Result.ok("导入成功");
    }

    private void checkDate2(List<DisperseUtilizeExcelVo> excelVos) {
        //定义一个行数记录
        int rows = 2;
        String message = "";

        for (DisperseUtilizeExcelVo excelVo : excelVos) {

            boolean b = excelVo.getFarmerPhone().matches("[0-9-()（）]{7,11}");
            if (!b) {
                message = message + "第" + rows + "行第3列电话号码错误,";
            }

            if (!"".equals(message)) {
                String s = message.substring(0, message.length() - 1);
                throw new SofnException(s);
            }
            rows++;
        }


    }

    @Override
    public void downloadStrawUtilizeTemplate(HttpServletResponse response) throws IOException {
        //int year = Calendar.getInstance().get(Calendar.YEAR);
        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/市场主体规模化秸秆利用量.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, 1);
        response.reset();
        /*
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        */
        //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
        /*
        response.setHeader("Content-Disposition", "attachment;filename=市场主体规模化秸秆利用量.xlsx;"
                + URLEncoder.encode("市场主体规模化秸秆利用量.xlsx", "utf-8"));
        */
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("市场主体规模化秸秆利用量.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    @Override
    public Result importStrawUtilizeExcel(MultipartFile file, String year) {
        //查询条件组装
        Map<String, Object> params = combinConditionMap(file, year);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.isEmpty()) {
            throw new SofnException("请检查当年任务是否创建!");
        }
        //判断产生量与直接还田量是否完成
        ProStill proStill = proStillService.getProStill((String) params.get("year"), (String) params.get("areaId"));
        if (null == proStill) {
            throw new SofnException("请检查产生量与直接还田量的填写是否完成!");
        }

        //获取excel表格数据,判断数据是否符合要求
        //对不符合要求数据给出提示
        List<StrawUtilizeExcelVo> dataList = ExcelImportUtil.getDataByExcel(file, 2, StrawUtilizeExcelVo.class, null);
        //校验数据是否满足规则
        checkDate(dataList);
        //获取当前用户所在机构对象信息
        SysOrganization organization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
        //对数据根据农户分组
        Map<String, List<StrawUtilizeExcelVo>> voGroupMap = dataList.stream().collect(Collectors.groupingBy(
                StrawUtilizeExcelVo::getMainNo));
        String result = "";
        //循环分组信息，对数据分组处理
        for (Map.Entry<String, List<StrawUtilizeExcelVo>> entry : voGroupMap.entrySet()) {
            List<StrawUtilizeExcelVo> voList = entry.getValue();
            if (null == voList || voList.isEmpty()) continue;
            //数据正常，则将数据录入到数据库中
            StrawUtilizeVo strawUtilizeVo = new StrawUtilizeVo();
            strawUtilizeVo.setYear((String) params.get("year"));
            strawUtilizeVo.setStrawUtilizeId("");
            strawUtilizeVo.setAddTime(new Date());
            strawUtilizeVo.setAddress(voList.get(0).getAddress());
            strawUtilizeVo.setCorporationName(voList.get(0).getCorporationName());
            strawUtilizeVo.setMainName(voList.get(0).getMainName());
            strawUtilizeVo.setCorporationName(voList.get(0).getCorporationName());
            strawUtilizeVo.setMobilePhone(voList.get(0).getMobilePhone());
            strawUtilizeVo.setDepartment(organization.getOrganizationName());

            List<StrawUtilizeDetail> details = new ArrayList<>(entry.getValue().size());
            details = copyStrawExcelValToDetail(voList, details);
            strawUtilizeVo.setStrawUtilizeDetailList(details);
            //调用统一方法添加数据
            result = strawUtilizeDetailService.addOrUpdateStrawUtilizeDetail(strawUtilizeVo, UserUtil.getLoginUserId());
        }

        return Result.ok(result);
    }

    private void checkDate(List<StrawUtilizeExcelVo> dataList) {
        //定义一个行数记录
        int rows = 3;
        String message = "";
        for (StrawUtilizeExcelVo excelVo : dataList) {
            // 校验必填项
            if (StringUtils.isBlank(excelVo.getMainNo())) {
                throw new SofnException("主体编号必填！");
            }
            if (StringUtils.isBlank(excelVo.getMainName())) {
                throw new SofnException("市场主体名称必填！");
            }
            if (StringUtils.isBlank(excelVo.getCorporationName())) {
                throw new SofnException("法人名称必填！");
            }
            if (StringUtils.isBlank(excelVo.getAddress())) {
                throw new SofnException("法人地址必填！");
            }
            //校验长度
            if (excelVo.getMainName() != null) {
                if (excelVo.getMainName().length() > 128) {
                    throw new SofnException("市场主体名称超长，最大128位！");
                }
            }

            if (excelVo.getCorporationName() != null) {
                if (excelVo.getCorporationName().length() > 16) {
                    throw new SofnException("法人名称超长，最大16位！");
                }
            }

            if (excelVo.getAddress() != null) {
                if (excelVo.getAddress().length() > 255) {
                    throw new SofnException("地址超长，最大255位！");
                }
            }

            //校验手机号是否符合规则
            if (excelVo.getMobilePhone() != null) {
                boolean b = excelVo.getMobilePhone().matches("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
                if (excelVo.getMobilePhone().length() > 11) {
                    b = false;
                }
                if (!b) {
                    message = message + "第" + rows + "行第4列电话号码错误,";
                }
            } else {
                message = message + "第" + rows + "行第4列电话号码为空,";
            }
            if (excelVo.getFertilising() != null) {
                boolean b = excelVo.getFertilising().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "第" + rows + "行第7列肥料化数据不正确,";
                }
            } else {
                excelVo.setFertilising(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getForage() != null) {
                boolean b = excelVo.getForage().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "第" + rows + "行第8列饲料化数据不正确,";
                }
            } else {
                excelVo.setForage(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getFuel() != null) {
                boolean b = excelVo.getFuel().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "第" + rows + "行第9列燃料化数据不正确,";
                }
            } else {
                excelVo.setFuel(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getBase() != null) {
                boolean b = excelVo.getBase().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "第" + rows + "行第10列基料化数据不正确,";
                }
            } else {
                excelVo.setBase(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getMaterial() != null) {
                boolean b = excelVo.getMaterial().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "第" + rows + "行第11列原料化数据不正确,";
                }
            } else {
                excelVo.setMaterial(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            if (excelVo.getOther() != null) {
                boolean b = excelVo.getOther().toString().matches("^[0-9]+.?[0-9]*$");
                if (!b) {
                    message = message + "第" + rows + "行第12列外县来源数据不正确,";
                }
            } else {
                excelVo.setFertilising(new BigDecimal(BigDecimal.ZERO.toString()));
            }
            //获取五料化之和为本县来源
            excelVo.setOwnCountry(excelVo.getFertilising().add(excelVo.getForage()).add(excelVo.getFuel()).add(excelVo.getBase()).add(excelVo.getMaterial()));
            if (excelVo.getOwnCountry().compareTo(excelVo.getOther()) < 0) {
                message = message + "第" + rows + "行五料化之和必须大于等于外县来源,";
            }
            if (!"".equals(message)) {
                String s = message.substring(0, message.length() - 1);
                throw new SofnException(s);
            }
            rows++;
        }
    }

    //List复制,数据封装处理
    private List<ProStillDetail> copyExcelValueToDetail(List<YieldAndReturnExcelVo> dataList, List<ProStillDetail> detailList) {
        if (dataList.isEmpty()) return new ArrayList<>();
        //若出现数据集合中元素数量超过系统设定
        if (dataList.size() > 14) {
            throw new SofnException("导入数据的作物类型数量大于系统设定14种");
        }
        Set<String> cropSet = new HashSet<>();
        for (YieldAndReturnExcelVo vo : dataList) {
            if (CropsEnum.getByChineseName(vo.getStrawType()) == null) {
                throw new SofnException(vo.getStrawType() + "作物类型不存在");
            }
            ProStillDetail detail = new ProStillDetail();
            BeanUtils.copyProperties(vo, detail);
            detail.setStrawName(vo.getStrawType());
            detail.setStrawType(CropsEnum.getByChineseName(vo.getStrawType()) == null ? ""
                    : CropsEnum.getByChineseName(vo.getStrawType()).getName());
            cropSet.add(detail.getStrawType());
            detailList.add(detail);
        }

        //若存在重复数据，则会出现数据集合中元素数量不相等
        if (cropSet.size() < dataList.size()) {
            throw new SofnException("导入数据存在作物类型重复的情况");
        }

        //补齐未在excel中列出的秸秆类型数据
        for (CropsEnum ce : CropsEnum.values()) {
            if (cropSet.contains(ce.getName()))
                continue;
            ProStillDetail detail = new ProStillDetail();
            detail.setStrawType(ce.getName());
            detail.setStrawName(ce.getChineseName());
            detailList.add(detail);
        }

        return detailList;
    }

    private List<DisperseUtilizeDetail> copyExcelValToDetail(List<DisperseUtilizeExcelVo> dataList, List<DisperseUtilizeDetail> detailList) {
        if (dataList.isEmpty()) return new ArrayList<>();
        //若出现数据集合中元素数量超过系统设定
        if (dataList.size() > 14) {
            throw new SofnException("导入数据的作物类型数量大于系统设定14种");
        }
        Set<String> cropSet = new HashSet<>();
        for (DisperseUtilizeExcelVo vo : dataList) {
            if (CropsEnum.getByChineseName(vo.getStrawName()) == null) {
                throw new SofnException(vo.getStrawName() + "作物类型不存在");
            }
            DisperseUtilizeDetail detail = new DisperseUtilizeDetail();
            BeanUtils.copyProperties(vo, detail);
            detail.setStrawType(CropsEnum.getByChineseName(vo.getStrawName()) == null ? ""
                    : CropsEnum.getByChineseName(vo.getStrawName()).getName());
            detail.setStrawName(vo.getStrawName());
            cropSet.add(detail.getStrawType());

            detailList.add(detail);
        }
        //若存在重复数据，则会出现数据集合中元素数量不相等
        if (cropSet.size() < dataList.size()) {
            throw new SofnException("导入数据存在作物类型重复的情况");
        }
        //补齐未在excel中列出的秸秆类型数据
        for (CropsEnum ce : CropsEnum.values()) {
            if (cropSet.contains(ce.getName()))
                continue;
            DisperseUtilizeDetail detail = new DisperseUtilizeDetail();
            detail.setStrawType(ce.getName());
            detail.setStrawName(ce.getChineseName());
            detailList.add(detail);
        }

        return detailList;
    }

    private List<StrawUtilizeDetail> copyStrawExcelValToDetail(List<StrawUtilizeExcelVo> dataList, List<StrawUtilizeDetail> detailList) {
        if (dataList.isEmpty()) return new ArrayList<>();
        //若出现数据集合中元素数量超过系统设定
        if (dataList.size() > 14) {
            throw new SofnException("导入数据的作物类型数量大于系统设定14种");
        }
        Set<String> cropSet = new HashSet<>();
        for (StrawUtilizeExcelVo vo : dataList) {
            if (CropsEnum.getByChineseName(vo.getStrawName()) == null) {
                throw new SofnException(vo.getStrawName() + "作物类型不存在");
            }
            StrawUtilizeDetail detail = new StrawUtilizeDetail();
            BeanUtils.copyProperties(vo, detail);
            detail.setStrawType(CropsEnum.getByChineseName(vo.getStrawName()) == null ? ""
                    : CropsEnum.getByChineseName(vo.getStrawName()).getName());
            detail.setStrawName(vo.getStrawName());
            cropSet.add(detail.getStrawType());
            detailList.add(detail);
        }
        //若存在重复数据，则会出现数据集合中元素数量不相等
        if (cropSet.size() < dataList.size()) {
            throw new SofnException("导入数据存在作物类型重复的情况");
        }
        //补齐未在excel中列出的秸秆类型数据
        for (CropsEnum ce : CropsEnum.values()) {
            if (cropSet.contains(ce.getName()))
                continue;
            StrawUtilizeDetail detail = new StrawUtilizeDetail();
            detail.setStrawType(ce.getName());
            detail.setStrawName(ce.getChineseName());
            detailList.add(detail);
        }
        return detailList;
    }

    //查询条件组装
    private Map<String, Object> combinConditionMap(MultipartFile file, String year) {

        if (StringUtils.isEmpty(year)) {
            //文件命名中规定得有年度，否则默认当前年
            year = file.getOriginalFilename().indexOf("年") > -1 ? file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("年")) :
                    String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }

        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regionCode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regionCode, String.class);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("areaId", areaList.get(2));

        return params;
    }

    @Override
    public void dataAnalysisExport(List<DataAnalysisExcelVo> list, HttpServletResponse response) {
        try {
            ClassPathResource resource = new ClassPathResource("static/数据分析.xlsx");
            InputStream inputStream = resource.getInputStream();
            ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream);
            Workbook workbook = exportDetailUtil.getWorkbook();
            workbook.setSheetName(0, "数据分析数据导出");
            exportDetailUtil.setSheet(workbook.getSheetAt(0));
            Sheet sheet = exportDetailUtil.getSheet();
            //TODO
//            exportDetailUtil.replaceCellValue(0, 4, 2019);
            Row row = sheet.getRow(0);
            String[] years = list.get(0).getGYear().split(",");
            String[] indexes = list.get(0).getIndexs().split(",");
            String[] Indicator = list.get(0).getIndicatorArrays().split(",");
            for (int i = 0; i < years.length; i++) {
                for (int j = 0; j < indexes.length; j++) {
                    Cell cell = row.createCell(2 + i * indexes.length + j);
                    if (j == 0) {
                        cell.setCellValue(years[i]);
                    }
                }
                if (indexes.length > 1) {
                    CellRangeAddress region = new CellRangeAddress(0, 0, 2 + i * indexes.length, 2 + (i + 1) * indexes.length - 1);
                    sheet.addMergedRegion(region);
                }
            }
            Row row_index = sheet.getRow(1);
            for (int i = 0; i < years.length; i++) {
                for (int j = 0; j < indexes.length; j++) {
                    Cell cell = row_index.createCell(2 + i * indexes.length + j);
                    cell.setCellValue(indexes[j]);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Row row_index_data = sheet.createRow(i + 2);
                //没有值的赋值为0
                if ("".equals(Indicator[0])) {
                    for (int j = 0; j < years.length + indexes.length + 2; j++) {
                        Cell cell = row_index_data.createCell(j);
                        if (j == 0) {
                            cell.setCellValue(list.get(i).getArea_Name());
                            continue;
                        }
                        if (j == 1) {
                            cell.setCellValue(list.get(i).getStrawName());
                            continue;
                        }
                        if (j == 2) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[0]);
                            }
                            continue;
                        }
                        if (j == 3) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[1]);
                            }
                            continue;
                        }
                        if (j == 4) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[2]);
                            }
                            continue;
                        }
                        if (j == 5) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[3]);
                            }
                            continue;
                        }
                        if (j == 6) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[4]);
                            }
                            continue;
                        }
                        if (j == 7) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[5]);
                            }
                            continue;
                        }
                        if (j == 8) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[6]);
                            }
                            continue;
                        }
                        if (j == 9) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[7]);
                            }
                            continue;
                        }
                        if (j == 10) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[8]);
                            }
                            continue;
                        }
                        if (j == 11) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[9]);
                            }
                            continue;
                        }
                        if (j == 12) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[10]);
                            }
                            continue;
                        }
                        if (j == 13) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[11]);
                            }
                            continue;
                        }
                        if (j == 14) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[12]);
                            }
                            continue;
                        }
                        if (j == 15) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[13]);
                            }
                            continue;
                        }
                        if (j == 16) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[14]);
                            }
                            continue;
                        }
                        if (j == 17) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[15]);
                            }
                            continue;
                        }
                        if (j == 18) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[16]);
                            }
                            continue;
                        }
                        if (j == 19) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[17]);
                            }
                            continue;
                        }
                        if (j == 20) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[18]);
                            }
                            continue;
                        }
                        if (j == 21) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[19]);
                            }
                            continue;
                        }
                        if (j == 22) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[20]);
                            }
                            continue;
                        }
                        if (j == 23) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[21]);
                            }
                            continue;
                        }
                        if (j == 24) {
                            cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[22]);
                            continue;
                        }
                        if (j == 25) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[23]);
                            }
                            continue;
                        }
                        if (j == 26) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[24]);
                            }
                            continue;
                        }
                    }
                } else {
                    for (int j = 0; j < Indicator.length + 2; j++) {
                        Cell cell = row_index_data.createCell(j);
                        if (j == 0) {
                            cell.setCellValue(list.get(i).getArea_Name());
                            continue;
                        }
                        if (j == 1) {
                            cell.setCellValue(list.get(i).getStrawName());
                            continue;
                        }
                        if (j == 2) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[0]);
                            }
                            continue;
                        }
                        if (j == 3) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[1]);
                            }
                            continue;
                        }
                        if (j == 4) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[2]);
                            }
                            continue;
                        }
                        if (j == 5) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[3]);
                            }
                            continue;
                        }
                        if (j == 6) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[4]);
                            }
                            continue;
                        }
                        if (j == 7) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[5]);
                            }
                            continue;
                        }
                        if (j == 8) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[6]);
                            }
                            continue;
                        }
                        if (j == 9) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[7]);
                            }
                            continue;
                        }
                        if (j == 10) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[8]);
                            }
                            continue;
                        }
                        if (j == 11) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[9]);
                            }
                            continue;
                        }
                        if (j == 12) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[10]);
                            }
                            continue;
                        }
                        if (j == 13) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[11]);
                            }
                            continue;
                        }
                        if (j == 14) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[12]);
                            }
                            continue;
                        }
                        if (j == 15) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[13]);
                            }
                            continue;
                        }
                        if (j == 16) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[14]);
                            }
                            continue;
                        }
                        if (j == 17) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[15]);
                            }
                            continue;
                        }
                        if (j == 18) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[16]);
                            }
                            continue;
                        }
                        if (j == 19) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[17]);
                            }
                            continue;
                        }
                        if (j == 20) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[18]);
                            }
                            continue;
                        }
                        if (j == 21) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[19]);
                            }
                            continue;
                        }
                        if (j == 22) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[20]);
                            }
                            continue;
                        }
                        if (j == 23) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[21]);
                            }
                            continue;
                        }
                        if (j == 24) {
                            cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[22]);
                            continue;
                        }
                        if (j == 25) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[23]);
                            }
                            continue;
                        }
                        if (j == 26) {
                            if ("".equals(list.get(i).getIndicatorArrays())) {
                                cell.setCellValue(0);
                            } else {
                                cell.setCellValue(list.get(i).getIndicatorArrays().split(",")[24]);
                            }
                            continue;
                        }
                    }
                }
            }
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + "数据分析.xlsx" + ";filename*=utf-8''"
                    + URLEncoder.encode("数据分析.xlsx", "utf-8"));
            OutputStream os = response.getOutputStream();
            inputStream.close();
            exportDetailUtil.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void zipExport(HttpServletResponse response, String year) throws IOException {
        //response 输出流
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(year+"年度任务数据.zip"));
        //压缩输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(out);
        //String countyId = "";
        List<YieldAndReturnExportExcel> excelList = proStillDetailService.getExportExcelByAredIdAndYear("f", year);
        List<YieldAndReturnExportVoExcel> exportVoExcelList = new ArrayList<>();
        YieldAndReturnExportVoExcel yieldAndReturnExportVoExcel = null;
        for (YieldAndReturnExportExcel item:excelList) {
            yieldAndReturnExportVoExcel = new YieldAndReturnExportVoExcel();
            yieldAndReturnExportVoExcel.setCollectionRatio(item.getCollectionRatio());
            yieldAndReturnExportVoExcel.setExportYield(item.getExportYield());
            yieldAndReturnExportVoExcel.setGrainYield(item.getGrainYield());
            yieldAndReturnExportVoExcel.setGrassValleyRatio(item.getGrassValleyRatio());
            yieldAndReturnExportVoExcel.setReturnArea(item.getReturnArea());
            yieldAndReturnExportVoExcel.setSeedArea(item.getSeedArea());
            yieldAndReturnExportVoExcel.setStrawName(item.getStrawName());
            exportVoExcelList.add(yieldAndReturnExportVoExcel);
        }
        List<DisperseUtilizeExportExcel> detailExlList = disperseUtilizeDetailService.getDisperseUtilizeDetailExl(year, null, "", null, null);
        // 市场主体利用率
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("areaId", "");
        List<StrawUtilizeExcel> strawUtilizeExcelList = strawUtilizeDetailService.getStrawUtilizeWorkbook(params);
        List<ZipDataVo> list = new ArrayList<>();
        ZipDataVo zipDataVo = new ZipDataVo();
        zipDataVo.setTitle("产生量与直接还田量");
        zipDataVo.setDataList(excelList);
        zipDataVo.setHeader(new String[]{"秸秆类型","粮食产量（吨）","草谷比","可收集系数","播种面积（亩)","还田面积（亩）","秸秆调出量（吨）"});
        zipDataVo.setAClass(YieldAndReturnExportVoExcel.class);
        list.add(zipDataVo);
        ZipDataVo zipDataVo1 = new ZipDataVo();
        zipDataVo1.setTitle("农户分散利用量");
        zipDataVo1.setDataList(detailExlList);
        zipDataVo1.setAClass(DisperseUtilizeExportExcel.class);
        zipDataVo1.setHeader(new String[]{"农户编码","户主姓名","户主电话","详细地址","农作物类型","播种面积","亩产","肥料化","饲料化","燃料化","基料化","原料化","利用量","用途"});
        list.add(zipDataVo1);
        ZipDataVo zipDataVo2 = new ZipDataVo();
        zipDataVo2.setTitle("市场主体规模化秸秆利用量");
        zipDataVo2.setAClass(StrawUtilizeExcel.class);
        zipDataVo2.setDataList(strawUtilizeExcelList);
        list.add(zipDataVo2);
        try {
            Class classes = null;
            for (int i = 0; i < list.size(); i++) {
                //创建工作簿
                HSSFWorkbook wb = new HSSFWorkbook();
                ExcelDataUtil.createWorkbook(wb, list.get(i).getDataList(),list.get(i).getAClass(),list.get(i).getHeader());
                //重点开始,创建压缩文件
                ZipEntry z = new ZipEntry(list.get(i).getTitle() + ".xls");
                zipOutputStream.putNextEntry(z);
                //写入一个压缩文件
                wb.write(zipOutputStream);
            }
            zipOutputStream.flush();
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //注意关闭顺序，否则可能文件错误
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}