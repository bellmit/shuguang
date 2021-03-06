package com.sofn.ahhdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.mapper.ZoneMapper;
import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneApply;
import com.sofn.ahhdp.service.ZoneApplyService;
import com.sofn.ahhdp.service.ZoneService;
import com.sofn.ahhdp.util.ValidatorUtil;
import com.sofn.ahhdp.vo.excelTemplate.ZoneExcel;
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


@Service(value = "zoneService")
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneMapper zoneMapper;
    @Autowired
    private ZoneApplyService zoneApplyService;
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Override
    public Zone save(Zone entity, String operator, Date now) {
        entity.setId(entity.getCode());
        entity.setImportTime(now);
        entity.setOperator(operator);
        zoneMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        zoneMapper.deleteById(id);
    }

    @Override
    public void update(Zone entity) {
        zoneMapper.updateById(entity);
    }

    @Override
    public Zone get(String id) {
        return zoneMapper.selectById(id);
    }

    @Override
    public void downTemplate(HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/???????????????????????????????????????.xlsx";
        ExcelExportUtil.createTemplate(filePath, ZoneExcel.class);
        ZoneExcel ze = new ZoneExcel();
        ze.setCode("???????????????????????????");
        ze.setAreaName("??????????????????");
        ze.setCompany("????????????????????????");
        ze.setAreaRange("??????xxx,??????xxx. ????????????????????????????????????(?????????????????????????????????)");
        String fileName = "???????????????????????????????????????.xlsx";
        ExcelExportUtil.createExcel(response, ZoneExcel.class, Arrays.asList(ze), ExcelField.Type.IMPORT, fileName);
        FileDownloadUtils.downloadAndDeleteFile(filePath, response);
    }

    @Override
    @Transactional
    public void importZone(MultipartFile multipartFile) {
        List<ZoneExcel> importList = ExcelImportUtil.getDataByExcel(multipartFile, 2, ZoneExcel.class, null);
        if (!CollectionUtils.isEmpty(importList)) {
            Date now = new Date();
            User user = UserUtil.getLoginUser();
            String operator = "?????????";
            if (Objects.nonNull(user)) {
                operator = user.getNickname();
            }
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (ZoneExcel zx : importList) {
                String code = zx.getCode();
                ValidatorUtil.validStrLength(zx.getCode(), "??????", 20);
                ValidatorUtil.validStrLength(zx.getAreaName(), "???????????????", 32);
                ValidatorUtil.validStrLength(zx.getAreaRange(), "???????????????", 100);
                ValidatorUtil.validStrLength(zx.getCompany(), "????????????", 32);
                Zone zone = zoneMapper.selectById(code);
                if (Objects.nonNull(zone)) {
                    this.delete(zone.getId());
                }
                zone = new Zone();
                BeanUtils.copyProperties(zx, zone);
                this.save(zone, operator, now);

                ZoneApply za = zoneApplyService.get(code);
                if (Objects.isNull(za)) {
                    ZoneApply zoneApply = new ZoneApply();
                    BeanUtils.copyProperties(zx, zoneApply);
                    zoneApplyService.save(zoneApply);
                }
            }
            session.commit();
        }
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/?????????????????????????????????.xlsx";
        ExcelExportUtil.createTemplate(filePath, ZoneExcel.class);
        List<Zone> zones = zoneMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(zones)) {
            List<ZoneExcel> zoneExcels = new ArrayList<>(zones.size());
            for (Zone zone : zones) {
                ZoneExcel ze = new ZoneExcel();
                BeanUtils.copyProperties(zone, ze);
                zoneExcels.add(ze);
            }
            String fileName = "?????????????????????????????????.xlsx";
            ExcelExportUtil.createExcel(response, ZoneExcel.class, zoneExcels, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public PageUtils<Zone> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Zone> zones = zoneMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(zones));
    }


}
