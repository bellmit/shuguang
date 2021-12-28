package com.sofn.ahhdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.mapper.ZoneRedordMapper;
import com.sofn.ahhdp.model.ZoneRecord;
import com.sofn.ahhdp.service.ZoneRecordService;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.ahhdp.vo.excelTemplate.ExportZoneCompanyBean;
import com.sofn.ahhdp.vo.excelTemplate.ExportZoneNameBean;
import com.sofn.ahhdp.vo.excelTemplate.ExportZoneRangeBean;
import com.sofn.ahhdp.vo.excelTemplate.ExportZoneRecordBean;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service(value = "zoneRecordService")
public class ZoneRecordServiceImpl implements ZoneRecordService {

    @Autowired
    private ZoneRedordMapper zoneRedordMapper;

    @Override
    public void save(ZoneRecord entity) {
        entity.setId(IdUtil.getUUId());
        zoneRedordMapper.insert(entity);
    }

    @Override
    public ZoneRecord getLastChangeByCode(String code) {
        QueryWrapper<ZoneRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CODE", code).orderByDesc("CHANGE_TIME");
        List<ZoneRecord> list = zoneRedordMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public PageUtils<ZoneRecord> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<ZoneRecord> zones = zoneRedordMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(zones));
    }

    @Override
    public PageUtils<ZoneRecord> listByParamsForPublish(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<ZoneRecord> zones = zoneRedordMapper.listByParamsForPublish(params);
        return PageUtils.getPageUtils(new PageInfo(zones));
    }

    @Override
    public List<DropDownVo> getYears() {
        List<String> list = zoneRedordMapper.getYears();
        List<DropDownVo> res = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(str -> {
                DropDownVo ddv = new DropDownVo();
                ddv.setName(str);
                ddv.setId(str);
                res.add(ddv);
            });
        }
        return res;
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "国家畜禽遗传资源保护区变更记录.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExportZoneNameBean.class);
        List<ZoneRecord> zones = zoneRedordMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(zones)) {
            List<ExportZoneRecordBean> exportZoneRecordBeans = new ArrayList<>(zones.size());
            for (ZoneRecord zoneRecord : zones) {
                ExportZoneRecordBean ezrb = new ExportZoneRecordBean();
                BeanUtils.copyProperties(zoneRecord, ezrb);
                exportZoneRecordBeans.add(ezrb);
            }
            ExcelExportUtil.createExcel(response, ExportZoneRecordBean.class, exportZoneRecordBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public void exportForName(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "保护区名称变更名单.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExportZoneNameBean.class);
        List<ZoneRecord> zones = zoneRedordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(zones)) {
            List<ExportZoneNameBean> exportZoneNameBeans = new ArrayList<>(zones.size());
            for (ZoneRecord zoneRecord : zones) {
                ExportZoneNameBean eznb = new ExportZoneNameBean();
                eznb.setCode(zoneRecord.getCode());
                eznb.setNewName(zoneRecord.getNewName());
                eznb.setNewRange(zoneRecord.getNewRange());
                eznb.setNewCompany(zoneRecord.getNewCompany());
                eznb.setAreaName(zoneRecord.getAreaName());
                exportZoneNameBeans.add(eznb);
            }
            ExcelExportUtil.createExcel(response, ExportZoneNameBean.class, exportZoneNameBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public void exportForCompany(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "建设变更名单.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExportZoneCompanyBean.class);
        List<ZoneRecord> zones = zoneRedordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(zones)) {
            List<ExportZoneCompanyBean> exportZoneCompanyBeans = new ArrayList<>(zones.size());
            for (ZoneRecord zoneRecord : zones) {
                ExportZoneCompanyBean ezcb = new ExportZoneCompanyBean();
                ezcb.setCode(zoneRecord.getCode());
                ezcb.setNewName(zoneRecord.getNewName());
                ezcb.setNewRange(zoneRecord.getNewRange());
                ezcb.setNewCompany(zoneRecord.getNewCompany());
                ezcb.setCompany(zoneRecord.getCompany());
                exportZoneCompanyBeans.add(ezcb);
            }
            ExcelExportUtil.createExcel(response, ExportZoneCompanyBean.class, exportZoneCompanyBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public void exportForRange(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "保护区名称变更名单.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExportZoneNameBean.class);
        List<ZoneRecord> zones = zoneRedordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(zones)) {
            List<ExportZoneRangeBean> exportZoneRangeBeans = new ArrayList<>(zones.size());
            for (ZoneRecord zoneRecord : zones) {
                ExportZoneRangeBean ezrb = new ExportZoneRangeBean();
                ezrb.setCode(zoneRecord.getCode());
                ezrb.setNewName(zoneRecord.getNewName());
                ezrb.setNewRange(zoneRecord.getNewRange());
                ezrb.setNewCompany(zoneRecord.getNewCompany());
                ezrb.setAreaRange(zoneRecord.getAreaRange());
                exportZoneRangeBeans.add(ezrb);
            }
            ExcelExportUtil.createExcel(response, ExportZoneRangeBean.class, exportZoneRangeBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

}
