package com.sofn.ahhdp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.mapper.DirectoriesRecordMapper;

import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.ahhdp.service.DirectoriesRecordService;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.ahhdp.vo.excelTemplate.ExcelDirectoriesNameBean;
import com.sofn.ahhdp.vo.excelTemplate.ExcelDirectoriesRangeBean;
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
 * @Date: 2020-04-21 11:38
 */
@Service("directoriesRecordService")
public class DirectoriesRecordServiceImpl implements DirectoriesRecordService {

    @Autowired
    private DirectoriesRecordMapper directoriesRecordMapper;

    @Override
    public void save(DirectoriesRecord entity) {
        entity.setId(IdUtil.getUUId());
        directoriesRecordMapper.insert(entity);
    }

    @Override
    public DirectoriesRecord getLastChangeByCode(String code) {
        QueryWrapper<DirectoriesRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CODE", code).orderByDesc("CHANGE_TIME");
        List<DirectoriesRecord> list = directoriesRecordMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public PageUtils<DirectoriesRecord> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<DirectoriesRecord> directories = directoriesRecordMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(directories));
    }

    @Override
    public PageUtils<DirectoriesRecord> listByParamsForPublish(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<DirectoriesRecord> directories = directoriesRecordMapper.listByParamsForPublish(params);
        return PageUtils.getPageUtils(new PageInfo(directories));
    }

    @Override
    public List<DropDownVo> getYears() {
        List<String> list = directoriesRecordMapper.getYears();
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
        ExcelExportUtil.createTemplate(filePath, ExcelDirectoriesNameBean.class);
        List<DirectoriesRecord> Directoriess = directoriesRecordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(Directoriess)) {
            List<ExcelDirectoriesNameBean> exportDirectoriesNameBeans = new ArrayList<>(Directoriess.size());
            for (DirectoriesRecord directoriesRecord : Directoriess) {
                ExcelDirectoriesNameBean eznb = new ExcelDirectoriesNameBean();
                eznb.setCode(directoriesRecord.getCode());
                eznb.setNewName(directoriesRecord.getNewName());
                eznb.setOldRegion(directoriesRecord.getNewRegion());
                eznb.setOldName(directoriesRecord.getOldName());
                exportDirectoriesNameBeans.add(eznb);
            }
            ExcelExportUtil.createExcel(response, ExcelDirectoriesNameBean.class, exportDirectoriesNameBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public void exportForCompany(Map<String, Object> params, HttpServletResponse response) {
        String fileName = "建设变更名单.xlsx";
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/" + fileName;
        ExcelExportUtil.createTemplate(filePath, ExcelDirectoriesRangeBean.class);
        List<DirectoriesRecord> Directoriess = directoriesRecordMapper.listByParamsForPublish(params);
        if (!CollectionUtils.isEmpty(Directoriess)) {
            List<ExcelDirectoriesRangeBean> exportDirectoriesCompanyBeans = new ArrayList<>(Directoriess.size());
            for (DirectoriesRecord DirectoriesRecord : Directoriess) {
                ExcelDirectoriesRangeBean ezcb = new ExcelDirectoriesRangeBean();
                ezcb.setCode(DirectoriesRecord.getCode());
                ezcb.setOldName(DirectoriesRecord.getNewName());
                ezcb.setOldRegion(DirectoriesRecord.getOldRegion());
                ezcb.setNewRegion(DirectoriesRecord.getNewRegion());
                exportDirectoriesCompanyBeans.add(ezcb);
            }
            ExcelExportUtil.createExcel(response, ExcelDirectoriesRangeBean.class, exportDirectoriesCompanyBeans, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }
}
