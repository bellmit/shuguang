package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.mapper.PlantUtilizationMapper;
import com.sofn.agpjyz.model.PlantUtilization;
import com.sofn.agpjyz.service.PictureAccessoriesService;
import com.sofn.agpjyz.service.PlantUtilizationService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.util.*;
import com.sofn.agpjyz.vo.*;
import com.sofn.agpjyz.vo.exportBean.ExportPuBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 植物利用模块服务类
 **/
@Service(value = "plantUtilizationService")
public class PlantUtilizationServiceImpl extends
        BaseService<PlantUtilizationMapper, PlantUtilization> implements PlantUtilizationService {

    @Resource
    private PlantUtilizationMapper puMapper;
    @Resource
    private PictureAccessoriesService paService;
    @Resource
    private FileUtil fileUtil;
    @Resource
    private JzbApi jzbApi;

    String[] fileUses = {"picOther"};

    @Override
    @Transactional
    public PlantUtilizationVo save(PlantUtilizationForm form) {
        RedisUserUtil.validReSubmit("agpjyz_save");
        PlantUtilization entity = new PlantUtilization();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        User user = UserUtil.getLoginUser();
        if (!Objects.isNull(user)) {
            entity.setReportPerson(user.getNickname());
        }
        entity.setReportTime(entity.getCreateTime());

        //验证用户机构配置是否满足要求
        String province = entity.getProvince();
        String city = entity.getCity();
        String county = entity.getCounty();
        RedisUserUtil.validLoginUser(BoolUtils.N, province, city, county);
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(province, city, county);
        entity.setCountyName(regionNames[2]);
        entity.setCityName(regionNames[1]);
        entity.setProvinceName(regionNames[0]);

        Integer charLength = 1000;
        String other = entity.getOther();
        boolean flag = StringUtils.hasText(other) && other.length() > charLength;
        if (flag) {
            entity.setOther(other.substring(0, charLength));
        }
        puMapper.insert(entity);
        if (flag) {
            puMapper.updateOther(entity.getId(), other.substring(charLength));
        }
        PlantUtilizationVo plantUtilizationVo = PlantUtilizationVo.entity2Vo(entity);
        //处理文件信息
        StringBuilder ids = new StringBuilder();
        for (String fileUse : fileUses) {
            try {
                Method method = PlantUtilizationForm.class.getMethod(
                        new StringBuilder("get").append(StrUtil.captureName(fileUse)).toString());
                String subIds = this.handleFileInfo((List<PictureAccessoriesForm>) method.invoke(form), plantUtilizationVo, fileUse);
                if (StringUtils.hasText(subIds)) {
                    ids.append(",").append(subIds);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        //激活系统文件
        if (StringUtils.hasText(ids.toString())) {
            fileUtil.activationFile(ids.toString().substring(1));
        }
        return plantUtilizationVo;
    }

    /**
     * 处理文件信息
     */
    private String handleFileInfo(List<PictureAccessoriesForm> pictureAccessoriesForms, PlantUtilizationVo plantUtilizationVo, String fileUse) {
        if (!CollectionUtils.isEmpty(pictureAccessoriesForms)) {
            List<PictureAccessoriesVo> pictureAccessoriesVos = new ArrayList<>(pictureAccessoriesForms.size());
            StringBuilder ids = new StringBuilder();
            for (PictureAccessoriesForm paForm : pictureAccessoriesForms) {
                paForm.setSourceId(plantUtilizationVo.getId());
                paForm.setFileUse(fileUse);
                PictureAccessoriesVo pictureAccessoriesVo = paService.save(paForm, Constants.FILE_SOURCE_PLANT_UTILIZATION);
                ids.append("," + pictureAccessoriesVo.getFileId());
                pictureAccessoriesVos.add(pictureAccessoriesVo);
            }
            try {
                Method method = SourceVo.class.getMethod(
                        new StringBuilder("set").append(StrUtil.captureName(fileUse)).toString(), List.class);
                method.invoke(plantUtilizationVo, pictureAccessoriesVos);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return ids.toString().substring(1);
        }
        return "";
    }

    @Override
    public void delete(String id) {
        QueryWrapper<PlantUtilization> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        PlantUtilization entity = puMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待删除的数据不存在");
        }
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        puMapper.updateById(entity);
    }

    @Override
    public void update(PlantUtilizationForm form) {
        String id = form.getId();
        QueryWrapper<PlantUtilization> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        PlantUtilization pu = puMapper.selectOne(queryWrapper);
        if (Objects.isNull(pu)) {
            throw new SofnException("待修改数据不存在!");
        }
        BeanUtils.copyProperties(form, pu);
        pu.preUpdate();
        //保存区域中文名称
        String[] regionNames = RedisUserUtil.getRegionNames(pu.getProvince(), pu.getCity(), pu.getCounty());
        pu.setCountyName(regionNames[2]);
        pu.setCityName(regionNames[1]);
        pu.setProvinceName(regionNames[0]);


        Integer charLength = 1000;
        String other = pu.getOther();
        boolean flag = StringUtils.hasText(other) && other.length() > charLength;
        if (flag) {
            pu.setOther(other.substring(0, charLength));
        }
        puMapper.updateById(pu);
        if (flag) {
            puMapper.updateOther(pu.getId(), other.substring(charLength));
        }
        //更新文件信息
        for (String fileUse : fileUses) {
            try {
                Method method = PlantUtilizationForm.class.getMethod(
                        new StringBuilder("get").append(StrUtil.captureName(fileUse)).toString());
                paService.updateSourceId(id, (List<PictureAccessoriesForm>) method.invoke(form), Constants.FILE_SOURCE_PLANT_UTILIZATION, fileUse);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PlantUtilizationVo get(String id) {
        QueryWrapper<PlantUtilization> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        PlantUtilizationVo plantUtilizationVo = PlantUtilizationVo.entity2Vo(puMapper.selectOne(queryWrapper));
        //产业利用ID
        String industrialIds = plantUtilizationVo.getIndustrialId();
        if (StringUtils.hasText(industrialIds)) {
            Map<String, String> industriaMap = ApiUtil.getResultMap(jzbApi.listForIndustrial());
            StringBuilder industrialValues = new StringBuilder();
            for (String industrialId : industrialIds.split(",")) {
                String industrialStr = industriaMap.get(industrialId);
                industrialValues.append("," + (StringUtils.hasText(industrialStr) ? industrialStr : ""));
            }
            if (StringUtils.hasText(industrialValues) && industrialValues.length() > 1) {
                plantUtilizationVo.setIndustrialValue(industrialValues.substring(1).toString());
            }
        }
        //用途ID
        String purposes = plantUtilizationVo.getPurpose();
        if (StringUtils.hasText(purposes)) {
            Map<String, String> purposeMap = ApiUtil.getResultMap(jzbApi.listForPurpose(null));
            StringBuilder purposeValues = new StringBuilder();
            for (String purpose : purposes.split(",")) {
                String purposeStr = purposeMap.get(purpose);
                purposeValues.append("," + (StringUtils.hasText(purposeStr) ? purposeStr : ""));
            }
            if (StringUtils.hasText(purposeValues) && purposeValues.length() > 1) {
                plantUtilizationVo.setPurposeName(purposeValues.substring(1).toString());
            }
        }
        //效益描述
        plantUtilizationVo.setWorthName(plantUtilizationVo.getWorth());
        List<PictureAccessoriesVo> pictureAccessoriesVos = paService.listBySourceId(id);
        if (!CollectionUtils.isEmpty(pictureAccessoriesVos)) {
            for (String fileUse : fileUses) {
                try {
                    Method method = PlantUtilizationVo.class.getMethod(
                            new StringBuilder("set").append(StrUtil.captureName(fileUse)).toString(), List.class);
                    method.invoke(plantUtilizationVo, pictureAccessoriesVos.stream().
                            filter(vo -> fileUse.equals(vo.getFileUse())).collect(Collectors.toList()));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return plantUtilizationVo;
    }

    @Override
    public PageUtils<PlantUtilizationVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        //完善查询参数
        RedisUserUtil.perfectParams2(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<PlantUtilization> plantUtilizations = puMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(plantUtilizations)) {
            PageInfo<PlantUtilization> plantUtilizationPageInfo = new PageInfo<>(plantUtilizations);
            List<PlantUtilizationVo> plantUtilizationVos = new ArrayList<>(plantUtilizations.size());
            Map<String, String> industriaMap = ApiUtil.getResultMap(jzbApi.listForIndustrial());
            Map<String, String> purposeMap = ApiUtil.getResultMap(jzbApi.listForPurpose(null));
            for (PlantUtilization plantUtilization : plantUtilizations) {
                PlantUtilizationVo vo = PlantUtilizationVo.entity2Vo(plantUtilization);
                String industrialIds = vo.getIndustrialId();
                if (StringUtils.hasText(industrialIds)) {
                    StringBuilder industrialValues = new StringBuilder();
                    for (String industrialId : industrialIds.split(",")) {
                        String industrialStr = industriaMap.get(industrialId);
                        industrialValues.append("," + (StringUtils.hasText(industrialStr) ? industrialStr : ""));
                    }
                    if (StringUtils.hasText(industrialValues) && industrialValues.length() > 1) {
                        vo.setIndustrialValue(industrialValues.substring(1).toString());
                    }
                }
                String purposes = vo.getPurpose();
                if (StringUtils.hasText(purposes)) {
                    StringBuilder purposeValues = new StringBuilder();
                    for (String purpose : purposes.split(",")) {
                        String purposeStr = purposeMap.get(purpose);
                        purposeValues.append("," + (StringUtils.hasText(purposeStr) ? purposeStr : ""));
                    }
                    if (StringUtils.hasText(purposeValues) && purposeValues.length() > 1) {
                        vo.setPurposeName(purposeValues.substring(1).toString());
                    }
                }
                plantUtilizationVos.add(vo);
            }
            PageInfo<PlantUtilizationVo> plantUtilizationVoPageInfo = new PageInfo<>(plantUtilizationVos);
            plantUtilizationVoPageInfo.setPageSize(pageSize);
            plantUtilizationVoPageInfo.setTotal(plantUtilizationPageInfo.getTotal());
            plantUtilizationVoPageInfo.setPageNum(plantUtilizationPageInfo.getPageNum());
            return PageUtils.getPageUtils(plantUtilizationVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo<>(plantUtilizations));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        //完善查询参数
        RedisUserUtil.perfectParams2(params, "export");
        List<PlantUtilization> tsList = puMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<ExportPuBean> exportList = new ArrayList<>(tsList.size());
            Map<String, String> industriaMap = ApiUtil.getResultMap(jzbApi.listForIndustrial());
            Map<String, String> purposeMap = ApiUtil.getResultMap(jzbApi.listForPurpose(null));
            for (PlantUtilization pu : tsList) {
                ExportPuBean epb = ExportPuBean.entity2Bean(pu);
                String industrialIds = pu.getIndustrialId();
                if (StringUtils.hasText(industrialIds)) {
                    StringBuilder industrialValues = new StringBuilder();
                    for (String industrialId : industrialIds.split(",")) {
                        String industrialStr = industriaMap.get(industrialId);
                        industrialValues.append("," + (StringUtils.hasText(industrialStr) ? industrialStr : ""));
                    }
                    if (StringUtils.hasText(industrialValues) && industrialValues.length() > 1) {
                        epb.setIndustrialValue(industrialValues.substring(1).toString());
                    }
                }
                String purposes = pu.getPurpose();
                if (StringUtils.hasText(purposes)) {
                    StringBuilder purposeValues = new StringBuilder();
                    for (String purpose : purposes.split(",")) {
                        String purposeStr = purposeMap.get(purpose);
                        purposeValues.append("," + (StringUtils.hasText(purposeStr) ? purposeStr : ""));
                    }
                    if (StringUtils.hasText(purposeValues) && purposeValues.length() > 1) {
                        epb.setPurposeName(purposeValues.substring(1).toString());
                    }
                }
                exportList.add(epb);
            }
            try {
                ExportUtil.createExcel(ExportPuBean.class, exportList, response, "农业野生植物利用.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
