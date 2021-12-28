package com.sofn.fdpi.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisStopException;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.model.ImportsExports;
import com.sofn.fdpi.model.ImportsExportsSpecies;
import com.sofn.fdpi.service.ImportsExportsService;
import com.sofn.fdpi.service.ImportsExportsSpeService;
import com.sofn.fdpi.vo.ImExportCacheVo;
import com.sofn.fdpi.vo.ImportExcel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.*;


/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-03 9:50
 */
@Slf4j
public class ImportsListener extends AnalysisEventListener<ImportExcel> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
//    /**
//     *  这个集合用于接收 读取Excel文件得到的数据
//     */
    List<ImportExcel> listExcel = new ArrayList<>();
//    /**
//     * 不能直接使用自动装备，必须以参数的形式传入
//     */
    List<ImportsExports> ie = new ArrayList<>();
    List<ImportsExportsSpecies> ies = new ArrayList<>();
    private ImportsExportsService importsexportsservice;

    private ImportsExportsSpeService imService;
    private List<ImExportCacheVo> dropDownVos1;
    private  Map<String, String> map1;
    private  Map<String, String> map2;
    private  Map<String, String> map3;
    public ImportsListener() {

    }

    public ImportsListener(ImportsExportsService importsexportsservice, ImportsExportsSpeService imService, Map<String, String> map1, Map<String, String> map2,Map<String, String> map3,List<ImExportCacheVo> dropDownVos1) {
        this.importsexportsservice = importsexportsservice;
        this.imService = imService;
        this.map1=map1;
        this.map2=map2;
        this.map3=map3;
        this.dropDownVos1=dropDownVos1;
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ImportExcel importExcel, AnalysisContext analysisContext) {
//        关键字段去掉空格
        String index=importExcel.getId();
        checkDataFormat(importExcel,index);
        importExcel.setImpAuform(importExcel.getImpAuform().trim());
//        String speName=importExcel.getSpeName().trim();
        if (map2.containsKey(importExcel.getSpeName())){
            importExcel.setProLevel(map2.get(importExcel.getSpeName()));
        }else {
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (map1.containsKey(importExcel.getSpeName())){
            importExcel.setSpeName(map1.get(importExcel.getSpeName()));
        }else {
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        ImportsExports importsExports = new ImportsExports();
        BeanUtils.copyProperties(importExcel,importsExports);
        ImportsExportsSpecies importsExpoSpecies=new ImportsExportsSpecies();
        BeanUtils.copyProperties(importExcel,importsExpoSpecies);
        if (map3.containsKey(importExcel.getImpAuform())){
            importsExpoSpecies.setExportsId(map3.get(importExcel.getImpAuform()));
            importsExpoSpecies.setId(IdUtil.getUUId());
            dropDownVos1.forEach(o->{
                if (o.getImpAuform().equals(importExcel.getImpAuform().trim())&&o.getSpeName().equals(importExcel.getSpeName())){
//                        throw new SofnException("审批编号为"+importExcel.getImpAuform().trim()+"已存在物种"+speName);
                    throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
                }
            });
            ImExportCacheVo  ime=new ImExportCacheVo();
            ime.setId(importsExpoSpecies.getExportsId());
            ime.setImpAuform(importExcel.getImpAuform());
            ime.setSpeName(importExcel.getSpeName());
            dropDownVos1.add(ime);
            ies.add(importsExpoSpecies);
        }else {
            importsExports.setId(IdUtil.getUUId());
            importsExpoSpecies.setId(IdUtil.getUUId());
            importsExpoSpecies.setExportsId(importsExports.getId());
            importsExports.preInsert();
            map3.put(importExcel.getImpAuform(),importExcel.getId());
            ImExportCacheVo  ime=new ImExportCacheVo();
            ime.setId(importsExports.getId());
            ime.setImpAuform(importsExports.getImpAuform().trim());
            ime.setSpeName(importsExpoSpecies.getSpeName());
            dropDownVos1.add(ime);
            map3.put(ime.getImpAuform(),ime.getId());
            ie.add(importsExports);
            ies.add(importsExpoSpecies);
        }

    }
    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData(ie,ies);
    }

    private void saveData( List<ImportsExports> ie, List<ImportsExportsSpecies> ies) {
                importsexportsservice.importsExportsWirte(ie);
                imService.importsExportsSpeWirte(ies);
                RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
                redisHelper.del(Constants.REDIS_KEY_ALL_IES);
                importsexportsservice.saveCache();
                System.out.println(new Date());

    }

    private void checkDataFormat(ImportExcel importExcel,String index){
        if (importExcel.getId()==null){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getImpAuform()==null||importExcel.getImpAuform().length()>30){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getImpComp()!=null&&importExcel.getImpComp().length()>20){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getExComp()!=null&&importExcel.getExComp().length()>20){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getValidityTime()==null){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getVisaAuth()==null||importExcel.getVisaAuth().length()>20){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getIssueDate()==null){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getSpeName()==null||importExcel.getSpeName().length()>30){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getAmount()==null||importExcel.getAmount().length()>10){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getSource()==null||importExcel.getSource().length()>20){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (importExcel.getPort()==null||importExcel.getPort().length()>20){
            throw new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     * @param exception
     * @param context
     * @throws ExcelAnalysisStopException
     */
//    @Override
//    public void onException(Exception exception, AnalysisContext context) {
//        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
//        // 如果是某一个单元格的转换异常 能获取到具体行号
//        // 如果要获取头的信息 配合invokeHeadMap使用
//        int col = 0, row = 0;
//        ExcelDataConvertException excelDataConvertException = null;
//        if (exception instanceof ExcelDataConvertException) {
//            excelDataConvertException = (ExcelDataConvertException) exception.getCause();
//            col = excelDataConvertException.getColumnIndex();
//            row = excelDataConvertException.getRowIndex();
//        }
//        throw new ExcelAnalysisStopException("第{+" + row + "}行，第{+" + col + "}列解析异常，数据为:{+" + excelDataConvertException.getCellData() + "}");
//    }
}
