package com.sofn.ahhdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.mapper.FarmRecordMapper;

import com.sofn.ahhdp.model.FarmRecord;
import com.sofn.ahhdp.service.FarmRecordService;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.ahhdp.vo.excelTemplate.ExportFarmCompanyBean;
import com.sofn.ahhdp.vo.excelTemplate.ExportFarmNameBean;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:47
 */
@Service(value = "farmRecordService")
public class FarmRecordServiceImpl implements FarmRecordService {
    @Autowired
    private FarmRecordMapper farmRecordMapper;

    @Override
    public void save(FarmRecord entity) {
        entity.setId(IdUtil.getUUId());
        farmRecordMapper.insert(entity);
    }

    @Override
    public FarmRecord getLastChangeByCode(String code) {
        QueryWrapper<FarmRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CODE", code).orderByDesc("CHANGE_TIME");
        List<FarmRecord> list = farmRecordMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public PageUtils<FarmRecord> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<FarmRecord> Farms = farmRecordMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(Farms));
    }

    @Override
    public PageUtils<FarmRecord> listByParamsForPublish(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<FarmRecord> Farms = farmRecordMapper.listByParamsForPublish(params);
        return PageUtils.getPageUtils(new PageInfo(Farms));
    }

    @Override
    public List<DropDownVo> getYears() {
        List<String> list = farmRecordMapper.getYears();
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
    public void exportForName(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "保护区名称变更名单.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExportFarmNameBean.class);
        List<FarmRecord> Farms = farmRecordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(Farms)) {
            List<ExportFarmNameBean> exportFarmNameBeans = new ArrayList<>(Farms.size());
            for (FarmRecord FarmRecord : Farms) {
                ExportFarmNameBean eznb = new ExportFarmNameBean();
                eznb.setCode(FarmRecord.getCode());
                eznb.setNewName(FarmRecord.getNewName());
                eznb.setNewCompany(FarmRecord.getNewCompany());
                eznb.setOldName(FarmRecord.getOldName());
                exportFarmNameBeans.add(eznb);
            }
            ExcelExportUtil.createExcel(response, ExportFarmNameBean.class, exportFarmNameBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public void exportForCompany(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "建设变更名单.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExportFarmCompanyBean.class);
        List<FarmRecord> Farms = farmRecordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(Farms)) {
            List<ExportFarmCompanyBean> exportFarmCompanyBeans = new ArrayList<>(Farms.size());
            for (FarmRecord FarmRecord : Farms) {
                ExportFarmCompanyBean ezcb = new ExportFarmCompanyBean();
                ezcb.setCode(FarmRecord.getCode());
                ezcb.setNewName(FarmRecord.getNewName());
                ezcb.setNewCompany(FarmRecord.getNewCompany());
                ezcb.setOldCompany(FarmRecord.getOldCompany());
                exportFarmCompanyBeans.add(ezcb);
            }
            ExcelExportUtil.createExcel(response, ExportFarmCompanyBean.class, exportFarmCompanyBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }


}
