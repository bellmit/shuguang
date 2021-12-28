package com.sofn.fdpi.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisStopException;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.model.Capture;
import com.sofn.fdpi.service.CaptureService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.vo.CaptureExcel;
import com.sofn.fdpi.vo.SysRegionForm;
import com.sofn.fdpi.vo.SysRegionInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;


import java.util.*;


/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-04 14:44
 */
@Slf4j
public class ImportCapListenner  extends AnalysisEventListener<CaptureExcel> {
        List<CaptureExcel> listExcel=new ArrayList<>();
        List<Capture> list=new ArrayList<>();
        private SysRegionApi sysRegionApi;
        private CaptureService captureService;
        private  String  sysOrgLevelId;
        private Map<String, String> map;
        private  Map<String, String> map1;
        private  Map<String, String> map2;
        private  Map<String, String> map3;
        public  ImportCapListenner(CaptureService captureService,SysRegionApi sysRegionApi, String sysOrgLevelId,  Map<String, String> map,Map<String, String> map1, Map<String, String> map2, Map<String, String> map3){
            this.captureService=captureService;
            this.sysRegionApi=sysRegionApi;
            this.sysOrgLevelId=sysOrgLevelId;
            this.map=map;
            this.map1=map1;
            this.map2=map2;
            this.map3=map3;
        }
    @Override
    public void invoke(CaptureExcel captureExcel, AnalysisContext analysisContext) {
        listExcel.add(captureExcel);
        if (listExcel.size()>3000){
            throw  new SofnException("一次导入不超过3000");
        }
        Capture capture = new Capture();
        BeanUtils.copyProperties(captureExcel, capture);
        checkData(captureExcel,capture);
        list.add(capture);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        captureService.saveBatch(list,3000);
    }
    private void checkData(CaptureExcel captureExcel,Capture capture){
        String index=captureExcel.getId();
//        校验数据格式问题
        checkDataFormat(captureExcel,index);
        if (map1.containsKey(captureExcel.getSpeName().trim())){
//            设置为物种名 设置为物种id
            capture.setSpeName(map1.get(captureExcel.getSpeName().trim()));
        }else if (map.containsKey(captureExcel.getSpeName().trim())){
//            throw new SofnException("当前用户不能导入此级别的物种"+captureExcel.getSpeName());
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }else {
//            throw new SofnException("当前保护级别的物种名不存在"+captureExcel.getSpeName());
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
//        将保护级别 有数字转为 状态
            capture.setProLevel(map2.get(capture.getSpeName().trim()));
        if (map3.containsKey(captureExcel.getPapersNumber().trim())){
//            throw  new SofnException("当前证书编号已存在"+ captureExcel.getPapersNumber().trim());
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(captureExcel.getProvince().trim());
        if ("".equals(sysRegionByName.getData().getRegionCode())) {
//            throw new SofnException("请检查导入猎捕地点（省），省份名错误");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        capture.setProvince(sysRegionByName.getData().getRegionCode());

        Result<SysRegionForm> sysRegionByName1 = sysRegionApi.getSysRegionByName(captureExcel.getCity().trim());
        if ("".equals(sysRegionByName1.getData().getRegionCode())) {
//            throw new SofnException("请检查导入猎捕地点（市），市名错误");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        capture.setCity(sysRegionByName1.getData().getRegionCode());
        Result<SysRegionForm> sysRegionByName2 = sysRegionApi.getSysRegionByName(captureExcel.getArea().trim());
        if ("".equals(sysRegionByName2.getData().getRegionCode())) {
//            throw new SofnException("请检查导入猎捕地点（区），区名错误");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        capture.setArea(sysRegionByName2.getData().getRegionCode());
//        此处检验导入的区县是否符合逻辑（即满足该区县再此省市下）
        if (!sysRegionByName2.getData().getParentId().equals(capture.getCity())){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (!sysRegionByName1.getData().getParentId().equals(capture.getProvince())){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
//        如果是省级 检验数据 的导入区域是否为本省
        if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
            if (capture.getProvince() != null) {
                Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
                String sysOrgProvince = sysRegionInfoByOrgId.getData().getProvince();
                if (!capture.getProvince().equals(sysOrgProvince)) {
//                    throw new SofnException("录入的捕获地点必须为本省");
                    throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
                }
            }
        }

        capture.preInsert();
        capture.setPapersType("特许猎捕证");
        Date dateS ;
        Date dateC ;
        try {
            String term = captureExcel.getTerm().trim();
            String dataS = term.substring(0, 10);
            String dataC = term.substring(11);
            String pattern = "yyyy/MM/dd";
            dateS = DateUtils.stringToDate(dataS, pattern);
            dateC = DateUtils.stringToDate(dataC, pattern);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new SofnException("有效期格式错误，请检查有效期格式");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
//                加入时间判断
        Date issueDate = capture.getIssueDate();
        int res = dateS.compareTo(issueDate);
        int rs = dateC.compareTo(issueDate);
        int rs2 = dateC.compareTo(dateS);
        if (rs2 != 1) {
//            throw new SofnException("有效日期结束要大于有效日期开始");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (res != 1) {
//            throw new SofnException("有效日期开始时间要大于发证日期");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (rs != 1) {
//            throw new SofnException("有效日期结束时间要大于发证日期");
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        capture.setDataClos(dateC);
        capture.setDataStart(dateS);
        capture.setPapersNumber(capture.getPapersNumber().trim());
        capture.setId(IdUtil.getUUId());
        map3.put(capture.getPapersNumber().trim(),capture.getId());
    }
//    校验数据格式
    private void checkDataFormat(CaptureExcel captureExcel, String index){
        if (captureExcel.getId()==null){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (captureExcel.getPapersNumber()==null|| captureExcel.getPapersNumber().length()>30){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
            }
        if (captureExcel.getCapUnit()==null|| captureExcel.getCapUnit().length()>50){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (captureExcel.getAppNum()==null|| captureExcel.getAppNum().length()>50){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (captureExcel.getCause()==null|| captureExcel.getCause().length()>100){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (captureExcel.getProLevel()==null){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (captureExcel.getSpeName()==null|| captureExcel.getSpeName().length()>30){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (captureExcel.getCapNum()==null|| captureExcel.getCapNum()>100000||captureExcel.getCapNum()<0){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getTerm()==null||captureExcel.getTerm().length()>25){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getProvince()==null||captureExcel.getProvince().length()>10){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getCity()==null||captureExcel.getCity().length()>10){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getArea()==null||captureExcel.getArea().length()>10){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getCapLocal()==null||captureExcel.getCapLocal().length()>50){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getCapWay()==null||captureExcel.getCapWay().length()>50){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getIssueUnit()==null||captureExcel.getIssueUnit().length()>50){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if(captureExcel.getIssueDate()==null){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
    }

}
