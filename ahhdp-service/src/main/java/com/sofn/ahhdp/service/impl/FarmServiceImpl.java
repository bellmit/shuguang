package com.sofn.ahhdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.mapper.FarmMapper;
import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.FarmApply;
import com.sofn.ahhdp.service.FarmService;
import com.sofn.ahhdp.service.FarmApplyService;
import com.sofn.ahhdp.util.ValidatorUtil;
import com.sofn.ahhdp.vo.excelTemplate.FarmExcel;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.model.User;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:26
 */
@Service(value = "farmService")
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmMapper farmMapper;
    @Autowired
    private FarmApplyService farmApplyService;
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Override
    public Farm save(Farm entity, String operator, Date now) {
        entity.setId(entity.getCode());
        entity.setImportTime(now);
        entity.setOperator(operator);
        farmMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        farmMapper.deleteById(id);
    }

    @Override
    public void update(Farm entity) {
        farmMapper.updateById(entity);
    }

    @Override
    public Farm get(String id) {
        return farmMapper.selectById(id);
    }

    @Override
    public void downTemplate(HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/国家畜禽遗传资源保种场模板.xlsx";
        ExcelExportUtil.createTemplate(filePath, FarmExcel.class);
        FarmExcel ze = new FarmExcel();
        ze.setCode("以国家实际编号为准");
        ze.setOldName("实际名称填写");
        ze.setOldCompany("实际建设单位填写(此行模板数据不导入)");
        String fileName = "国家畜禽遗传资源保种场模板.xlsx";
        ExcelExportUtil.createExcel(response, FarmExcel.class, Arrays.asList(ze), ExcelField.Type.IMPORT, fileName);
        FileDownloadUtils.downloadAndDeleteFile(filePath, response);
    }

    @Override
    @Transactional
    public void importFarm(MultipartFile multipartFile) {
        List<FarmExcel> importList = ExcelImportUtil.getDataByExcel(multipartFile, 2, FarmExcel.class, null);
        if (!CollectionUtils.isEmpty(importList)) {
            Date now = new Date();
            User user = UserUtil.getLoginUser();
            String operator = "导入人";
            if (Objects.nonNull(user)) {
                operator = user.getNickname();
            }
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (FarmExcel zx : importList) {
                String code1 = zx.getCode();
                ValidatorUtil.validStrLength(code1, "编号",20);
                ValidatorUtil.validStrLength(zx.getOldName(), "保护场名称", 32);
                ValidatorUtil.validStrLength(zx.getOldCompany(), "建设单位", 32);
                String code=code1;
                if (code1.contains(".")){
                    code=code1.substring(0,code1.lastIndexOf("."));
                    zx.setCode(code);
                }
                Farm Farm = farmMapper.selectById(code);
                if (Objects.nonNull(Farm)) {
                    this.delete(Farm.getId());
                }
                Farm = new Farm();
                BeanUtils.copyProperties(zx, Farm);
                this.save(Farm, operator, now);

                FarmApply za = farmApplyService.get(code);
                if (Objects.isNull(za)) {
                    FarmApply FarmApply = new FarmApply();
                    BeanUtils.copyProperties(zx, FarmApply);
                    farmApplyService.save(FarmApply);
                }
            }
            session.commit();
        }
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/国家畜禽遗传资源保护场.xlsx";
        ExcelExportUtil.createTemplate(filePath, FarmExcel.class);
        List<Farm> farms = farmMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(farms)) {
            List<FarmExcel> FarmExcels = new ArrayList<>(farms.size());
            for (Farm farm : farms) {
                FarmExcel ze = new FarmExcel();
                BeanUtils.copyProperties(farm, ze);
                FarmExcels.add(ze);
            }
            String fileName = "国家畜禽遗传资源保护场.xlsx";
            ExcelExportUtil.createExcel(response, FarmExcel.class, FarmExcels, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public PageUtils<Farm> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Farm> farms = farmMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(farms));
    }
}
