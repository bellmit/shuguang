package com.sofn.ahhdp.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhdp.enums.TablesEnum;
import com.sofn.ahhdp.mapper.DirectoriesMapper;

import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesApply;
import com.sofn.ahhdp.service.DirectoriesApplyService;
import com.sofn.ahhdp.service.DirectoriesService;
import com.sofn.ahhdp.sysapi.SysRegionApi;
import com.sofn.ahhdp.util.ValidatorUtil;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.ahhdp.vo.SysDict;
import com.sofn.ahhdp.vo.SysRegionForm;
import com.sofn.ahhdp.vo.excelTemplate.DirectoriesExcel;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
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
import java.util.stream.Collectors;


/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-20 17:56
 */
@Service("directoriesService")
public class DirectoriesServiceImpl implements DirectoriesService {
    @Autowired
    private DirectoriesMapper directoriesMapper;
    @Autowired
    private DirectoriesApplyService directoriesApplyService;
    @Autowired
    private SqlSessionFactory sessionFactory;
    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired
    private RedisHelper redisHelper;
    private final String redisKey = TablesEnum.SPE_KEY_ALL.getCode();
    private final String redisKeyByProvinceCode = TablesEnum.SPE_KEY_BY_PROVINCE_CODE.getCode();
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Directories save(Directories entity, String operator, Date now) {
        entity.setId(entity.getCode());
        entity.setImportTime(now);
        entity.setOperator(operator);
        directoriesMapper.insert(entity);
        redisHelper.del(redisKey);
        redisHelper.del(redisKeyByProvinceCode);
        return entity;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        redisHelper.del(redisKey);
        redisHelper.del(redisKeyByProvinceCode);
        directoriesMapper.deleteById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Directories entity) {
        redisHelper.del(redisKey);
        redisHelper.del(redisKeyByProvinceCode);
        directoriesMapper.updateById(entity);
    }

    @Override
    public Directories get(String id) {
        return directoriesMapper.selectById(id);
    }

    @Override
    public void downTemplate(HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/??????????????????????????????????????????.xlsx";
        ExcelExportUtil.createTemplate(filePath, DirectoriesExcel.class);
        DirectoriesExcel ze = new DirectoriesExcel();
        //????????????: ???:1; ???:2; ???:3; ???:4; ?????????:5; ????????????:6; ???????????????????????????????????????,?????????????????????
        ze.setCode("??????????????????");
        ze.setOldName("??????????????????");
        ze.setOldRegion("?????????,??????????????????:?????????????????????????????????\",\"??????,??????????????????????????????");
        ze.setCategory("??????????????????");
        String fileName = "??????????????????????????????????????????.xlsx";
        ExcelExportUtil.createExcel(response, DirectoriesExcel.class, Arrays.asList(ze), ExcelField.Type.IMPORT, fileName);
        FileDownloadUtils.downloadAndDeleteFile(filePath, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importDirectories(MultipartFile multipartFile) {
        List<DirectoriesExcel> importList = ExcelImportUtil.getDataByExcel(multipartFile, 2, DirectoriesExcel.class, null);
        if (!CollectionUtils.isEmpty(importList)) {
            Date now = new Date();
            User user = UserUtil.getLoginUser();
            String operator = "?????????";
            if (Objects.nonNull(user)) {
                operator = user.getNickname();
            }
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (DirectoriesExcel zx : importList) {
                String code1 = zx.getCode();
                if(!ValidatorUtil.checkDigit(code1)){
                    throw new SofnException("?????????"+code1+"??? ???????????????????????????");
                }
                this.checkCodeAndCategory(code1, zx.getCategory());
                Map<String, String> varietyTypeMap = sysRegionApi.getDictListByType("variety_type").getData().
                        stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
                zx.setCategory(varietyTypeMap.get(zx.getCategory()));
                String regions = zx.getOldRegion();
                if(regions.contains("???")){
                    throw new SofnException("????????????????????????????????????");
                }
                ValidatorUtil.validStrLength(regions, "????????????",250);
                ValidatorUtil.validStrLength(zx.getOldName(), "????????????", 30);
//                ValidatorUtil.validStrLength(zx.getCategory(), "????????????", 10);
                String[] regionArr = regions.split(",");
                for(String region : regionArr) {
                    if (!ValidatorUtil.checkChinese(region)) {
                        throw new SofnException("??????????????????????????????????????????????????????");
                    }
                }

                String code=code1;
                if (code1.contains(".")){
                  code=code1.substring(0,code1.lastIndexOf("."));
                    zx.setCode(code);
                }
                Directories Directories = directoriesMapper.selectById(code);
                if (Objects.nonNull(Directories)) {
                    this.delete(Directories.getId());
                }
                Directories = new Directories();
                BeanUtils.copyProperties(zx, Directories);
                StringBuilder st=new StringBuilder();
                String region= Directories.getOldRegion();
                if (region.contains("???")){
                    throw new SofnException("????????????????????????????????????????????????");
                }
                if (region.contains(",")){
                    String[] split = region.split(",");
                    for (int i=0;i<split.length;i++){
                        Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(split[i]);
                        if (sysRegionByName.getData()==null || Objects.equals(sysRegionByName.getMsg(),"???????????????")){
                            throw new SofnException("????????????:"+split[i]);
                        }
                        String regionCode = sysRegionByName.getData().getRegionCode();
                        st.append(regionCode+",");
                        Directories.setOldRegionCode(st.substring(0,st.length()-1));
                    }
                }else {
                    Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(region);
                    if (sysRegionByName.getData()==null|| Objects.equals(sysRegionByName.getMsg(),"???????????????")){
                        throw new SofnException("????????????:"+region);
                    }
                    String regionCode = sysRegionByName.getData().getRegionCode();
                    Directories.setOldRegionCode(regionCode);
                }

                this.save(Directories, operator, now);

                DirectoriesApply za = directoriesApplyService.get(code);
                if (Objects.isNull(za)) {
                    DirectoriesApply directoriesApply = new DirectoriesApply();
                    BeanUtils.copyProperties(zx, directoriesApply);
                    StringBuilder st1=new StringBuilder();
                    String region1= directoriesApply.getOldRegion();
                    if (region.contains(",")){
                        String[] split1 = region.split(",");
                        for (int i=0;i<split1.length;i++){
                            Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(split1[i]);
                            String regionCode1 = sysRegionByName.getData().getRegionCode();
                            st1.append(regionCode1+",");
                            directoriesApply.setOldRegionCode(st1.substring(0,st1.length()-1));
                        }
                    }else {
                        Result<SysRegionForm> sysRegionByName = sysRegionApi.getSysRegionByName(region1);
                        String regionCode1 = sysRegionByName.getData().getRegionCode();
                        directoriesApply.setOldRegionCode(regionCode1);
                    }

                    directoriesApplyService.save(directoriesApply);
                }
            }
            session.commit();
        }
        redisHelper.del(redisKey);
        redisHelper.del(redisKeyByProvinceCode);
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        String filePath = ExcelPropertiesUtils.getExcelFilePath() + "/????????????????????????????????????.xlsx";
        ExcelExportUtil.createTemplate(filePath, DirectoriesExcel.class);
        List<Directories> Directoriess = directoriesMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(Directoriess)) {
            List<DirectoriesExcel> DirectoriesExcels = new ArrayList<>(Directoriess.size());
            for (Directories Directories : Directoriess) {
                DirectoriesExcel ze = new DirectoriesExcel();
                BeanUtils.copyProperties(Directories, ze);
                DirectoriesExcels.add(ze);
            }
            String fileName = "????????????????????????????????????.xlsx";
            ExcelExportUtil.createExcel(response, DirectoriesExcel.class, DirectoriesExcels, ExcelField.Type.IMPORT, fileName);
            FileDownloadUtils.downloadAndDeleteFile(filePath, response);
        }
    }

    @Override
    public PageUtils<Directories> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Directories> Directoriess = directoriesMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(Directoriess));
    }

    @Override
    public List<DropDownVo> getRusult(String provinceCode) {
        String item = (StringUtils.isBlank(provinceCode) ? "" : provinceCode);
        List<DropDownVo> dropDownList;
        Object redisObj = null;
        //redis????????????????????????redis?????????
        if (StringUtils.isNotBlank(item)) {
            redisObj = redisHelper.hget(redisKeyByProvinceCode, item);
        } else {
            //??????????????????
            redisObj = redisHelper.get(redisKey);
        }
        if (redisObj != null) {
            dropDownList = JsonUtils.json2List(redisObj.toString(), DropDownVo.class);
            return dropDownList;
        } else {
            synchronized (this) {
                if (StringUtils.isNotBlank(item)) {
                    redisObj = redisHelper.hget(redisKeyByProvinceCode, item);
                } else {
                    //??????????????????
                    redisObj = redisHelper.get(redisKey);
                }
                if (redisObj != null) {
                    dropDownList = JsonUtils.json2List(redisObj.toString(), DropDownVo.class);
                    return dropDownList;
                } else {
                    //redis????????????????????????????????????????????????????????????redis??????
                    Map map=new HashMap();
                    if (!CommonEnum.SYS_REGION_ROOT_NODE.getCode().equals(provinceCode)) {
                        map.put("provinceCode", provinceCode);
                    }
                    dropDownList = directoriesMapper.getRusult(map);
                    if (StringUtils.isNotBlank(item)) {
                        redisHelper.hset(redisKeyByProvinceCode, item, JsonUtils.obj2json(dropDownList));
                    } else {
                        //??????????????????
                        redisHelper.set(redisKey, JsonUtils.obj2json(dropDownList));
                    }
                    return dropDownList;
                }
            }
        }

    }

    @Override
    public List<DropDownVo> getOldName() {
        List<DropDownVo> dropDownList;
        if (redisHelper.hasKey(redisKey)) {
            //redis????????????????????????redis?????????
            Object redisObj = redisHelper.get(redisKey);
            dropDownList = JsonUtils.json2List(redisObj == null ? "" : redisObj.toString(), DropDownVo.class);
            return dropDownList;
        } else {
            synchronized (this) {
                if (redisHelper.hasKey(redisKey)) {
                    //redis????????????????????????redis?????????
                    Object redisObj = redisHelper.get(redisKey);
                    dropDownList = JsonUtils.json2List(redisObj == null ? "" : redisObj.toString(), DropDownVo.class);
                    return dropDownList;
                }
                //redis????????????????????????????????????????????????????????????redis??????
                dropDownList = directoriesMapper.getOldName();
                redisHelper.set(redisKey, JsonUtils.obj2json(dropDownList));
                return dropDownList;
            }
        }
//        List<DropDownVo> result = directoriesMapper.getOldName();
//        return result;
    }

    private void checkCodeAndCategory(String code, String category) {
        if (!code.startsWith("1") && !code.startsWith("2") && !code.startsWith("3")
                && !code.startsWith("4") && !code.startsWith("5") && !code.startsWith("6")) {
            throw new SofnException("?????????????????????????????????1-6?????????");
        } else {
            if (code.startsWith("1") && !category.equals("1")) {
                throw new SofnException("???1?????????????????????????????????????????????????????????");
            } else if (code.startsWith("2") && !category.equals("2")) {
                throw new SofnException("???2?????????????????????????????????????????????????????????");
            } else if (code.startsWith("3") && !category.equals("3")) {
                throw new SofnException("???3?????????????????????????????????????????????????????????");
            } else if (code.startsWith("4") && !category.equals("4")) {
                throw new SofnException("???4?????????????????????????????????????????????????????????");
            } else if (code.startsWith("5") && !category.equals("5")) {
                throw new SofnException("???5???????????????????????????????????????????????????????????????");
            } else if (code.startsWith("6") && !category.equals("6")) {
                throw new SofnException("???6??????????????????????????????????????????????????????????????????");
            }
        }

    }
}
