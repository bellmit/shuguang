package com.sofn.fdpi.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.AnimalProlevelEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.enums.SpeCategoryEnum;
import com.sofn.fdpi.mapper.SpeMapper;

import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.service.FileManageService;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.ExportUtil;
import com.sofn.fdpi.util.ImportSpeListenner;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Auther:
 * @Date: 2019/11/29 15:05
 * @Description:
 */
@Service(value = "speService")
@Slf4j
public class SpeServiceImpl extends BaseService<SpeMapper, Spe> implements SpeService {
    @Resource
    private SpeMapper speMapper;
    @Resource
    SysRegionApi sysRegionApi;
    @Resource
    FileManageService fileManageService;
    @Resource
    private RedisHelper redisHelper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(List<SpeExcel> importList) {
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        Object redisObj = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES);
        Map<String, String> map = Maps.newHashMap();
        if (Objects.nonNull(redisObj)) {
            List<SpeNameLevelVo> dropDownVos = JsonUtils.json2List(redisObj.toString(), SpeNameLevelVo.class);
            if (dropDownVos != null) {
                dropDownVos.forEach(o -> {
                    map.put(o.getSpeName(), o.getId());
                });
            }
        }
        List<Spe> speList = Lists.newArrayListWithCapacity(importList.size());
        List<SelectVo> speCategorylist = SpeCategoryEnum.getSelect();
        for (int i = 0; i < importList.size(); i++) {
            SpeExcel speExcel = importList.get(i);
            this.checkDataFormat(speExcel, map);
            Spe sp = new Spe();
            BeanUtils.copyProperties(speExcel, sp);
            sp.preInsert();
            sp.setSpeCode(String.format("%04d", Integer.valueOf(speExcel.getSpeCode().trim())));
            sp.setId(IdUtil.getUUId());
            sp.setCreateTime(new Date());
            sp.setSpeName(sp.getSpeName().trim());
            sp.setSpeType(this.getSpeType(speCategorylist, sp.getSpeType()));
            speList.add(sp);
        }
        this.saveSpeBatch(speList);
        this.saveNameredis();
        return "";
    }

    private String getSpeType(List<SelectVo> speCategorylist, String speType) {
        for (SelectVo s : speCategorylist) {
            if (s.getVal().equals(speType)) {
                return s.getKey();
            }
        }
        throw new SofnException("暂未划分物种类型（" + speType + "）请检查后再提交");
    }

    private void checkDataFormat(SpeExcel speExcel, Map<String, String> map) {
        String index = speExcel.getId();
        if (speExcel.getId() == null) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getSpeName() == null || speExcel.getSpeName().length() > 20) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getLatinName() != null && speExcel.getLatinName().length() > 40) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getLocalName() != null && speExcel.getLocalName().length() > 10) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getIntro() != null && speExcel.getIntro().length() > 500) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getHabit() != null && speExcel.getHabit().length() > 500) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getIdentify() == null) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getPedigree() == null) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getProLevel() == null) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (speExcel.getCites() == null) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        }
        if (map.containsKey(speExcel.getSpeName().trim())) {
            throw new SofnException("第" + index + "行数据，导入失败，请检查后提交");
        } else {
            map.put(speExcel.getSpeName().trim(), "");
        }


    }

    /**
     * 根据id获取物种信息
     *
     * @param
     */

    @Override
    public Spe getSpeBySpeId(String Id) {
        Spe speById = speMapper.selectById(Id);
        List<FileManage> list = fileManageService.list(Id);
        if (list != null && list.size() > 0) {
            speById.setFiles(list);
        }
        return speById;
    }


    @Override
    public PageUtils<Spe> listSpeByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Spe> speList = speMapper.getSpeByPage(params);
        for (Spe s :
                speList) {
            List<FileManage> list = fileManageService.list(s.getId());
            s.setFiles(list);
        }

        PageInfo<Spe> spePageInfo = new PageInfo<>(speList);
        PageUtils pageUtils = PageUtils.getPageUtils(spePageInfo);
        return pageUtils;
    }

    @Override
    public int updateSpeInfo(SpeInfo speInfo) {
        RedisUserUtil.validReSubmit("fdpi_spe_update");
//        开始绑定证书的物种信息 不可修改
//        List<Papers> papersSpe = speMapper.getPapersSpe(speInfo.getId());
//        if (!CollectionUtils.isEmpty(papersSpe)) {
//            throw new SofnException("当前物种已绑定或正在绑定证书，不可修改");
//        }
        //验证'0000'只能是鲟鱼子酱
        this.validCodeAndName(speInfo.getSpeCode(), speInfo.getSpeName());
        //验证编号重复
        this.validCode(speInfo.getId(), speInfo.getSpeCode());
        int i = 0;
        Map map = new HashMap();
        map.put("id", speInfo.getId());
        map.put("speName", speInfo.getSpeName().trim());
//
        Spe s = speMapper.speName(map);
        if (s == null) {
            Spe spe = speMapper.selectById(speInfo.getId());
            String speName = spe.getSpeName();
//            speInfo.getSpeName()
//            fileManageService.del(speInfo.getId());
            Spe speModel = speInfo.getSpeModel(speInfo);
            speModel.preUpdate();
            speInfo.setSpeName(speInfo.getSpeName().trim());
            this.updateFile(speInfo);
            i = speMapper.updateById(speModel);

        }

//        redisHelper.del(Constants.REDIS_KEY_ALL_SPECIES);
        this.saveNameredis();
        return i;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteSpeInfo(String id) {
        Spe spe = this.getById(id);
        if (spe == null) {
            return "该物种不存在!";
        }
        List<Papers> papersSpe = speMapper.getPapersSpe(id);
        if (!CollectionUtils.isEmpty(papersSpe)) {
            throw new SofnException("当前物种已绑定或正在绑定证书，不可删除");
        }
        //先删除物种图片信息
        List<FileManageVo> list = fileManageService.listBySourceId(id);
        ArrayList<String> fileIds = null;
        if (!CollectionUtils.isEmpty(list)) {
            fileIds = new ArrayList<>(list.size());
            for (FileManageVo vo :
                    list) {
                fileIds.add(vo.getId());
            }
        }
        if (!CollectionUtils.isEmpty(fileIds)) {
            fileManageService.deleteBatchIds(fileIds);
            StringBuilder builder = new StringBuilder();
            for (String ids : fileIds
            ) {
                builder.append("," + ids);
            }
            try {
                Result<String> result = sysRegionApi.batchDeleteFile(builder.substring(0));
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("支撑平台文件删除失败!");
                }
            } catch (Exception e) {
                throw new SofnException("支撑平台文件删除失败!");
            }

        }
        //删除物种信息
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        map.put("updateTime", new Date());
        map.put("updateUserId", UserUtil.getLoginUserId());
        int deleteSpeInfo = speMapper.deleteSpeInfo(map);
//        redisHelper.del(Constants.REDIS_KEY_ALL_SPECIES);
        this.saveNameredis();
        if (deleteSpeInfo == 1) {
            return "1";
        }
        throw new SofnException("删除物种信息失败");


    }

    /**
     * 根据物种名字获取物种信息
     * wuXY
     * 2019-12-30 16:23:33
     *
     * @param speName 物种名字
     * @return Spe物种对象
     */
    @Override
    public Spe getSpeciesByName(String speName) {
        return speMapper.getSpeciesByName(speName);
    }

    //添加物种
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCheckingSpe(SpeInfo speInfo) {
        //  验证要保存的物种信息是否已经存在
        RedisUserUtil.validReSubmit("fdpi_spe_save");
        this.validCodeAndName(speInfo.getSpeCode(), speInfo.getSpeName());
        //验证编号是否重复
        this.validCode(null, speInfo.getSpeCode());
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("SPE_NAME", speInfo.getSpeName().trim());
        map.put("DEL_FLAG", "N");
        List<Spe> speList = speMapper.selectByMap(map);
        if (speList.size() < 1) {
            //更新文件
            speInfo.setId(IdUtil.getUUId());
            if (speInfo.getFiles() != null && speInfo.getFiles().size() >= 1) {
                this.updateFile(speInfo);
            }
            //新增
            Spe s = new Spe();
            BeanUtils.copyProperties(speInfo, s);
            s.setDelFlag("N");
            s.setSpeName(speInfo.getSpeName().trim());
            s.setCreateUserId(UserUtil.getLoginUserId());
            s.setUpdateTime(new Date());
            s.setCreateTime(new Date());
            int insert = speMapper.insert(s);
//            redisHelper.del(Constants.REDIS_KEY_ALL_SPECIES);
            this.saveNameredis();
            if (insert == 1) {
                return "1";
            }
        } else {
            return "该物种已存在";
        }
        throw new SofnException("新增物种失败");

    }

    //验证编号是否重复
    private void validCode(String id, String speCode) {
//        QueryWrapper<Spe> queryWrapper = new QueryWrapper<>();
//        if (StringUtils.hasText(id))
//            queryWrapper.ne("ID", id);
//        queryWrapper.eq("spe_code", speCode);
//        queryWrapper.eq("del_flag",BoolUtils.N);
//        if (speMapper.selectCount(queryWrapper) > 0) {
//            throw new SofnException("该物种编号已存在");
//        }
//        ;
    }

    //验证'0000'只能是鲟鱼子酱
    private void validCodeAndName(String code, String name) {
        if ("0000".equals(code) && !"鲟鱼子酱".equals(name)) {
//            throw new SofnException("编号0000只能是鲟鱼子酱");
        }
    }

    private void updateFile(SpeInfo form) {
        String id = form.getId();
        //先根据源ID查出所有文件,并获取文件ID列表
        List<FileManageVo> fmvList = fileManageService.listBySourceId(id);
        List<String> oldFileIds = null;
        if (!CollectionUtils.isEmpty(fmvList)) {
            oldFileIds = new ArrayList<>(fmvList.size());
            for (FileManageVo vo : fmvList) {
                oldFileIds.add(vo.getId());
            }
        }

        List<FileManageForm> files = form.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            StringBuilder ids = new StringBuilder();
            for (FileManageForm fileManageForm : files) {
                String fileId = fileManageForm.getId();
                if (CollectionUtils.isEmpty(oldFileIds)) {
                    ids.append("," + fileId);
                    fileManageService.insertFileMange(fileManageForm, "", id);
                } else {
                    if (oldFileIds.contains(fileId)) {
                        oldFileIds.remove(fileId);
                    } else {
                        ids.append("," + fileId);
                        fileManageService.insertFileMange(fileManageForm, "", id);
                    }
                }
            }
            //激活系统文件
            if (ids.length() > 0) {
                SysFileManageForm sfmf = new SysFileManageForm();
                sfmf.setIds(ids.substring(1));
                sfmf.setInterfaceNum("hidden");
                sfmf.setSystemId(Constants.SYSTEM_ID);
                try {
                    Result<List<SysFileVo>> result = sysRegionApi.activationFile(sfmf);
                    if (result.getData().size() < 1) {
                        throw new SofnException("激活文件失败!请检查文件是否上传成功");
                    }
                } catch (Exception e) {
                    throw new SofnException("激活文件失败!请检查文件是否上传成功");
                }
            }
        }

        if (!CollectionUtils.isEmpty(oldFileIds)) {
            // 本系统文件删除
            fileManageService.deleteBatchIds(oldFileIds);
            //支撑平台文件删除
            StringBuilder sysDelIds = new StringBuilder();
            for (String sysDelId : oldFileIds) {
                sysDelIds.append("," + sysDelId);
            }
            try {
                Result<String> result = sysRegionApi.batchDeleteFile(sysDelIds.substring(1));
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("支撑平台文件删除文件失败!");
                }
            } catch (Exception e) {
                throw new SofnException("支撑平台连接失败!");
            }
        }
    }

    @Override
    public List<SpeNameLevelVo> getSecondLevel() {
        String proLevel = AnimalProlevelEnum.COUNTRY_LEVEL_ONE.getId();
        try {
            Result<SysOrgAndRegionVo> sysOrgInfoForApproveAndQuery = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
            String sysOrgLevelId = sysOrgInfoForApproveAndQuery.getData().getSysOrgLevelId();
            if (OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId)) {
                proLevel = AnimalProlevelEnum.COUNTRY_LEVEL_TWO.getId();
            }
        } catch (Exception e) {
            throw new SofnException("根据机构id获取机构信息失败");
        }
        return speMapper.getSecondLevel(proLevel);
    }

    /**
     * 获取物种名下拉
     *
     * @return
     */
    @Override
    public List<SpeNameLevelVo> getSpeciesName(String type) {

        Object redisObj = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES);
        List<SpeNameLevelVo> speciesName;
        if (Objects.isNull(redisObj)) {
            speciesName = speMapper.getSpeciesName();
            redisHelper.set(Constants.REDIS_KEY_ALL_SPECIES, JsonUtils.obj2json(speciesName));
        } else {
            speciesName = JsonUtils.json2List(redisObj.toString(), SpeNameLevelVo.class);

        }
        if ("0".equals(type)) {
            //去除特殊物种
            speciesName = this.removeSpecialSpe(speciesName);
        }
        return speciesName;
    }

    private List<SpeNameLevelVo> removeSpecialSpe(List<SpeNameLevelVo> speciesName) {
        List<String> specialSpes = Arrays.asList("西非海牛", "灰海豚", "短鳍领航鲸", "星点水龟", "中华草龟", "印度黑龟");
        return speciesName.stream().filter(s -> !specialSpes.contains(s.getSpeName())).collect(Collectors.toList());
    }

    /**
     * 通过物种名和保护级别查看当前物种是否存在
     *
     * @param map
     * @return
     */
    @Override
    public Spe getSpe(Map map) {
        return speMapper.getSpe(map);
    }

    /**
     * 获取所有的物种数据，返回一个map
     * wXY
     * 2020-7-30 18:45:17
     *
     * @return map
     */
    @Override
    public Map<String, String> getMapForAllSpecies() {
        //放后需要做缓存
        Map<String, String> map;
        Object allSpeciesFirst = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES_FOR_SELECT);
        if (!Objects.isNull(allSpeciesFirst)) {
            map = JsonUtils.json2obj(allSpeciesFirst.toString(), Map.class);
        } else {
            //不存在，加锁
            synchronized (this) {
                Object allSpeciesSecond = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES_FOR_SELECT);
                if (!Objects.isNull(allSpeciesSecond)) {
                    map = JsonUtils.json2obj(allSpeciesFirst.toString(), Map.class);
                } else {
                    //从数据库中获取数据
                    map = speMapper.getMapForAllSpecies();
                    redisHelper.set(Constants.REDIS_KEY_ALL_SPECIES_FOR_SELECT, JsonUtils.obj2json(map));
                }
            }
        }
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String importData(MultipartFile file, SpeService speService) {
        RedisUserUtil.validReSubmit("fdpi_spe_import", 15L);
        log.info("开始结束");
        Map<String, String> map1 = Maps.newHashMap();
        List<String> codes = Lists.newArrayList();
        RedisHelper redisHelper = (RedisHelper) SpringContextHolder.getBean(RedisHelper.class);
        Object redisObj = redisHelper.get(Constants.REDIS_KEY_ALL_SPECIES);
//        如果缓存不为空  ---因为如果系统内一开始不存在物种直接导入是存在缓存为空的情况的
        if (redisObj != null) {
            List<SpeNameLevelVo> dropDownVos = JsonUtils.json2List(redisObj.toString(), SpeNameLevelVo.class);
            if (dropDownVos != null) {
                dropDownVos.forEach(o -> {
                    map1.put(o.getSpeName(), o.getId());
                    codes.add(o.getSpeCode());
                });
            }

        }
        Spe sp = new Spe();
        try {
            InputStream is = null;
            try {
                is = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EasyExcel.read(is, SpeExcel.class, new ImportSpeListenner(speService, sp, map1, codes)).sheet().doRead();
//            导入物种成功后重设缓存
            this.saveNameredis();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException(e.getMessage());
        }
//        System.out.println(new Date());
//        return "";
        return null;
    }

    //    @Async("asyncServiceExecutor")
    @Override
    public void saveSpeBatch(List<Spe> speList) {
        this.saveBatch(speList, speList.size() > 1000 ? 500 : 200);
    }

    @Override
    public void saveNameredis() {
        redisHelper.del(Constants.REDIS_KEY_ALL_SPECIES);
        List<SpeNameLevelVo> speciesName = speMapper.getSpeciesName();
        if (!CollectionUtils.isEmpty(speciesName)) {
            redisHelper.set(Constants.REDIS_KEY_ALL_SPECIES, JsonUtils.obj2json(speciesName));
        }


    }

    @Override
    public Spe getSpeByCode(String speCode) {
        QueryWrapper<Spe> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("spe_code", speCode);
        List<Spe> spes = speMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(spes)) {
            return null;
        } else {
            return spes.get(0);
        }
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        QueryWrapper<Spe> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N);
        queryWrapper.like(Objects.nonNull(params.get("speName")), "spe_name", params.get("speName"));
        queryWrapper.eq(Objects.nonNull(params.get("proLevel")), "pro_level", params.get("proLevel"));
        queryWrapper.eq(Objects.nonNull(params.get("cites")), "cites", params.get("cites"));
        queryWrapper.eq(Objects.nonNull(params.get("identify")), "identify", params.get("identify"));
        queryWrapper.eq(Objects.nonNull(params.get("pedigree")), "pedigree", params.get("pedigree"));
        queryWrapper.orderByAsc("spe_code");
        List<Spe> list = speMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<SpeExcel> exportList = new ArrayList<>(list.size());
            int i = 1;
            for (Spe spe : list) {
                spe.setId(String.valueOf(i++));
                exportList.add(SpeExcel.entity2Excel(spe));
            }
            try {
                ExportUtil.createExcel(SpeExcel.class, exportList, response, "重点保护物种物种管理信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

    @Override
    public String getSpeTypeByName(String speName) {
        QueryWrapper<Spe> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("spe_name", speName.trim());
        queryWrapper.select("id", "spe_type");
        List<Spe> list = speMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0).getSpeType();
        }
        return "";
    }
}
