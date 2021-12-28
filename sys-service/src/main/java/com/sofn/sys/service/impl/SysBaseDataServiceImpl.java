package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.test.testimport.ImportBaseDataBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.BaseData;
import com.sofn.common.utils.*;
import com.sofn.sys.mapper.SysBaseDataMapper;
import com.sofn.sys.mapper.SysBaseDataTypeMapper;
import com.sofn.sys.mapper.SysSubSystemBaseDataOwnMapper;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysBaseDataType;
import com.sofn.sys.service.SysBaseDataService;
import com.sofn.sys.vo.Excel.ImportBaseDataVo;
import com.sofn.sys.vo.SysBaseDataAndTypeVo;
import com.sofn.sys.vo.SysBaseDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zqc
 */
@Service(value = "sysBaseDataService")
@Slf4j
public class SysBaseDataServiceImpl extends ServiceImpl<SysBaseDataMapper, SysBaseData> implements SysBaseDataService {
    @Autowired
    private SysBaseDataMapper sysBaseDataDao;
    @Autowired
    private SysBaseDataTypeMapper sysBaseDataTypeDao;
    @Autowired
    private SysSubSystemBaseDataOwnMapper sysSubSystemBaseDataOwnMapper;


    @Override
    public PageUtils<HashMap<String, Object>> getBaseDataInfo(String typeKeyName, Integer pageNo, Integer pageSize) {

        if (pageNo != null && pageSize != null) {
            PageHelper.offsetPage(pageNo, pageSize);
        }

        if (typeKeyName == null) {
            typeKeyName = "";
        }

        List<HashMap<String, Object>> baseDataListBaseData = Lists.newArrayList();
        /* 根据字典值查询字典列表 */
        List<SysBaseData> sysBaseDatas = sysBaseDataDao.getBaseDataByName(typeKeyName);
        /* 按照字典类型分组 */
        List<SysBaseDataType> baseDataTypes = new ArrayList<>();
        for (SysBaseData sysBaseData : sysBaseDatas) {
            SysBaseDataType sysBaseDataType = sysBaseData.getSysBaseDataType();
            if (!baseDataTypes.contains(sysBaseDataType)) {
                baseDataTypes.add(sysBaseDataType);
            }
        }
        for (SysBaseDataType sysBaseDataType : baseDataTypes) {
            HashMap<String, Object> res = new HashMap<>();
            List<SysBaseData> subList = new ArrayList<>();
            String BaseDataTypeID = sysBaseDataType.getId();

            for (SysBaseData sysBaseData : sysBaseDatas) {
                SysBaseDataType sysBaseDataType1 = sysBaseData.getSysBaseDataType();
                String BaseDataTypeID1 = sysBaseDataType1.getId();
                if (BaseDataTypeID.equals(BaseDataTypeID1)) {
                    subList.add(sysBaseData);
                }
            }

            List<SysBaseDataType> types = sysBaseDataTypeDao.getBaseDataTypeById(BaseDataTypeID);
            if (types == null || types.size() == 0) {
                throw new SofnException("数据异常：当前ID为" + BaseDataTypeID + "的字典类型已经被删除！");
            }

            SysBaseDataType sysBaseDataType1 = types.get(0);
            String BaseDataTypeId = sysBaseDataType1.getId();
            String BaseDataTypeName = sysBaseDataType1.getTypename();
            String BaseDataTypeValue = sysBaseDataType1.getTypevalue();

            res.put("id", BaseDataTypeId);
            res.put("typename", BaseDataTypeName);
            res.put("typevalue", BaseDataTypeValue);
            res.put("data", subList);
            baseDataListBaseData.add(res);
        }


        //1、查询出当前页的类型；2、根据类型分别查询出字典；3、组装结果集

//        List<SysBaseData> BaseDataGroup = sysBaseDataDao.getBaseDataType(BaseDatatypeid);
        List<SysBaseDataType> BaseDataTypeGroup = sysBaseDataTypeDao.getBaseDataTypeByName(typeKeyName, "");
        PageInfo<SysBaseDataType> pageInfo = new PageInfo<SysBaseDataType>(BaseDataTypeGroup);
        PageInfo<HashMap<String, Object>> pageInfo2 = new PageInfo<HashMap<String, Object>>();
        BeanUtils.copyProperties(pageInfo, pageInfo2);
        List<SysBaseDataType> list = pageInfo.getList();
        List<HashMap<String, Object>> BaseDataList = Lists.newArrayList();

        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> res = new HashMap<>();
            SysBaseDataType sysBaseDataType = BaseDataTypeGroup.get(i);
            String BaseDataTypeId = sysBaseDataType.getId();
            String BaseDataTypeName = sysBaseDataType.getTypename();
            String BaseDataTypeValue = sysBaseDataType.getTypevalue();
            List<SysBaseData> subBaseData = sysBaseDataDao.getBaseDataByValueAndType(BaseDataTypeId, "", "", null);
            res.put("id", BaseDataTypeId);
            res.put("typename", BaseDataTypeName);
            res.put("typevalue", BaseDataTypeValue);
            res.put("data", subBaseData);
            BaseDataList.add(res);
        }

        /*BaseDataList.removeAll(BaseDataListBaseData);
        BaseDataList.addAll(BaseDataListBaseData);*/
        for (HashMap<String, Object> resMap : baseDataListBaseData) {
            if (!BaseDataList.contains(resMap)) {
                BaseDataList.add(resMap);
            }
        }

        pageInfo2.setList(BaseDataList);
//        pageInfo.setTotal(BaseDataTypeGroup.size());
        return PageUtils.getPageUtils(pageInfo2);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveBaseDataInfo(SysBaseDataVo sysBaseDataVo) {

        SysBaseData sysBaseData = SysBaseDataVo.getSysBaseData(sysBaseDataVo);
        sysBaseData.preInsert();

        sysBaseDataDao.saveBaseDataInfo(sysBaseData);
        // 删除缓存
        if (sysBaseData.getSysBaseDataType() != null) {
            SysBaseDataType sysBaseDataType = sysBaseDataTypeDao.selectById(sysBaseData.getSysBaseDataType().getId());
            if (sysBaseDataType != null) {
                BaseDataUtils.deleteCacheByType(sysBaseDataType.getTypevalue());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateBaseDataInfo(String id, String enable) {
        Date updateTime = new Date();
        String updateUserId = UserUtil.getLoginUserId();
        sysBaseDataDao.updateBaseDataInfo(id, enable, updateTime, updateUserId);

        List<SysBaseData> sysBaseDataList = sysBaseDataDao.getBaseDataById(Lists.newArrayList(id));
        if (sysBaseDataList.size() > 0) {
            SysBaseData sysBaseData = sysBaseDataList.get(0);
            SysBaseDataType sysBaseDataType = sysBaseData.getSysBaseDataType();
            // 删除缓存
            if (sysBaseDataType != null) {
                BaseDataUtils.deleteCacheByType(sysBaseDataType.getTypevalue());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBaseDataInfo(String id) {

        // id,id
        List<String> ids = IdUtil.getIdsByStr(id);
        if (CollectionUtils.isEmpty(ids)) {
            throw new SofnException("请选择要删除的数据");
        }
        List<SysBaseData> sysBaseDataList = sysBaseDataDao.getBaseDataById(ids);
        if (sysBaseDataList.size() > 0) {
            sysBaseDataList.forEach(item -> {
                // 使用老逻辑 ，不想改了， 应该不会出现大数据量的删除
                SysBaseDataType sysBaseDataType = item.getSysBaseDataType();
                if (sysBaseDataType != null) {
                    SysBaseDataType baseDataType = sysBaseDataTypeDao.selectById(sysBaseDataType.getId());
                    // 删除缓存
                    if (baseDataType != null) {
                        BaseDataUtils.deleteCacheByType(baseDataType.getTypevalue());
                    }
                }
            });
        }
        sysBaseDataDao.deleteBaseDataInfo(ids);
        // 删除基础数据分配表中的数据
        sysSubSystemBaseDataOwnMapper.deleteByBaseDataIds(ids);
      //删除字典数据后删除缓存
      BaseDataUtils.clearAllCache();
    }

    @Override
    public List<SysBaseData> getBaseDataByName(String baseDataName) {
        List<SysBaseData> res = sysBaseDataDao.getBaseDataByName(baseDataName);
        return res;
    }

    @Override
    public List<SysBaseData> getBaseDataByValueAndType(String baseDataTypeId, String baseDataName, String baseDataCode, String id) {
        List<SysBaseData> res = sysBaseDataDao.getBaseDataByValueAndType(baseDataTypeId, baseDataName, baseDataCode, id);
        return res;
    }

    @Override
    public void exportBaseData(String filePath) {
        List<SysBaseData> res = sysBaseDataDao.getBaseDataByValueAndType("", "", "", null);
        if (!CollectionUtils.isEmpty(res)) {
            List<SysBaseDataVo> sysBaseDataVos = Lists.newArrayList();
            res.forEach((sysBaseData -> {
                SysBaseDataVo sysBaseDataVo = SysBaseDataVo.getSysBaseDataVo(sysBaseData);
                sysBaseDataVos.add(sysBaseDataVo);
            }));
            System.out.println(res.size());
            log.info("开始生成文件");
            ExcelExportUtil.createExcel(filePath, ImportBaseDataBean.class, sysBaseDataVos);
            log.info("结束生成文件");
        }

    }

    @Override
    public List<SysBaseData> getBaseDataById(String id) {
        return sysBaseDataDao.getBaseDataById(Lists.newArrayList(id));
    }

    @Override
    public List<SysBaseData> getBaseDataNameByValueAndType(String typename, String baseDataCode) {
        return sysBaseDataDao.getBaseDataNameByValueAndType(typename, baseDataCode);
    }

    @Override
    public List<SysBaseData> getBaseDataListByType(String typeValue) {
        return sysBaseDataDao.getBaseDataListByType(typeValue);
    }

    /**
     * 根据appid、type和key获取基础数据
     *
     * @param type 基础数据类型
     * @param key  基础数据标签
     * @return 基础数据
     */
    @Override
    public BaseData getByAppIdAndTypeAndKey(String appId, String type, String key) {
        List<BaseData> list = getByAppIdAndType(appId, type);
        if (list != null && list.size() > 0) {
            for (BaseData baseData : list) {
                if (baseData == null) {
                    continue;
                }

                if (key.equals(baseData.getBaseDataCode())) {
                    return baseData;
                }
            }
        }

        return null;
    }

    /**
     * 根据appid、type和value获取基础数据
     *
     * @param type  基础数据类型
     * @param value 基础数据值
     * @return 基础数据
     */
    @Override
    public BaseData getByAppIdAndTypeAndValue(String appId, String type, String value) {
        List<BaseData> list = getByAppIdAndType(appId, type);
        if (list != null && list.size() > 0) {
            for (BaseData baseData : list) {
                if (baseData == null) {
                    continue;
                }

                if (value.equals(baseData.getBaseDataName())) {
                    return baseData;
                }
            }
        }

        return null;
    }

    /**
     * 根据appid、type获取基础数据
     *
     * @param appId 系统appID
     * @param type  基础数据类型
     * @return 基础数据列表
     */
    @Override
    public List<BaseData> getByAppIdAndType(String appId, String type) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(appId)) {
            return null;
        }

        List<BaseData> list = BaseDataUtils.getByType(appId, type);
        return list;
    }

    @Override
    public List<BaseData> getBaseDataCodeByNames(String appId, String baseDataTypeCode, String baseDataNames) {
        if (StringUtils.isBlank(appId)) {
            throw new SofnException("系统代码必传");
        }
        if (StringUtils.isBlank(baseDataTypeCode)) {
            throw new SofnException("基础数据类型代码必传");
        }
        if (StringUtils.isBlank(baseDataNames)) {
            return Lists.newArrayList();
        }
        List<String> names = IdUtil.getIdsByStr(baseDataNames);
        if (CollectionUtils.isEmpty(names)) {
            return Lists.newArrayList();
        }

        return sysBaseDataDao.getBaseDataCodeByNames(appId, baseDataTypeCode, names);
    }

    @Override
    public PageUtils<SysBaseData> getSysBaseDataByPage(String keyword, String baseDataTypeCode, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyword", keyword);
        params.put("baseDataTypeCode", baseDataTypeCode);
        List<SysBaseData> baseDataCodeByParams = sysBaseDataDao.getBaseDataCodeByParams(params);
        PageInfo<SysBaseData> pageInfo = new PageInfo<>(baseDataCodeByParams);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public void updateBaseDataInfoById(SysBaseData sysBaseData) {
        sysBaseDataDao.updateBaseDataInfoById(sysBaseData);
      //修改字典数据后删除缓存
      BaseDataUtils.clearAllCache();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importBaseData(List<ImportBaseDataVo> importBaseDataVos) {
        if (CollectionUtils.isEmpty(importBaseDataVos)) {
            return;
        }
        // 找出是否存在数据库中没有的type， 找到了就抛出异常
        Set<String> allTypes = importBaseDataVos.stream().map(item-> item.getTypeCode().trim()).collect(Collectors.toSet());
        if (allTypes.size() > 1) {
            throw new SofnException("一次只能导入一个类型的数据");
        }
        List<SysBaseDataType> baseDataTypeByCodes = sysBaseDataTypeDao.getBaseDataTypeByCodes(Lists.newArrayList(allTypes));
        if (CollectionUtils.isEmpty(baseDataTypeByCodes)) {
            throw new SofnException("数据字典类型代码不存在");
        }

        // 判断是否重复    名字和代码都不能重复
        // 判断文件里是否有重复的内容
        Set<String> importNames = importBaseDataVos.stream().map(ImportBaseDataVo::getDataName).collect(Collectors.toSet());
        Set<String> importCodes = importBaseDataVos.stream().map(ImportBaseDataVo::getDataCode).collect(Collectors.toSet());
        if (importCodes.size() != importBaseDataVos.size()) {
            List<String> repeatCode = getRepeteElement(importBaseDataVos, "");
            if (!CollectionUtils.isEmpty(repeatCode)) {
                throw new SofnException("文件中代码重复，重复的代码为：" + repeatCode);
            }
        }
        if (importNames.size() != importBaseDataVos.size()) {
            List<String> repeatCode = getRepeteElement(importBaseDataVos, "NAME");
            if (!CollectionUtils.isEmpty(repeatCode)) {
                throw new SofnException("文件中名称重复，重复的名称为：" + repeatCode);
            }
        }

        // 找出数据库中重复的
        String typeId = baseDataTypeByCodes.get(0).getId();
        List<SysBaseData> baseDataByBaseTypeIds = sysBaseDataDao.getBaseDataByBaseTypeIds(Lists.newArrayList(typeId));
        if (!CollectionUtils.isEmpty(baseDataByBaseTypeIds)) {
            List<String> baseDataCodes = baseDataByBaseTypeIds.stream().map(SysBaseData::getBaseDatacode).collect(Collectors.toList());
            List<String> baseDataNames = baseDataByBaseTypeIds.stream().map(SysBaseData::getBaseDataname).collect(Collectors.toList());
            // 找出重复的
            List<String> repeatNames = importNames.stream().filter(baseDataNames::contains).collect(Collectors.toList());
            List<String> repeatCodes = importCodes.stream().filter(baseDataCodes::contains).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(repeatNames) || !CollectionUtils.isEmpty(repeatCodes)) {
                throw new SofnException("导入失败，已经存在的名称:" + repeatNames + "， 已经存在的代码:" + repeatCodes);
            }
        }

        // 生成基础数据对象然后批量新增
        List<SysBaseData> collect1 = importBaseDataVos.stream().map(item -> {
            SysBaseData sysBaseData = new SysBaseData();
            sysBaseData.preInsert();
            sysBaseData.setId(IdUtil.getUUId());
            sysBaseData.setEnable("启用".equals(item.getEnable()) ? BoolUtils.Y : BoolUtils.N);
            sysBaseData.setRemark(item.getRemark());
            sysBaseData.setBasedatatypeid(typeId);
            sysBaseData.setBaseDataname(item.getDataName());
            sysBaseData.setBaseDatacode(item.getDataCode());
            return sysBaseData;
        }).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(collect1)){
            // 批量新增
            sysBaseDataDao.batchSaveBaseDataInfo(collect1);
        }

    }

    @Override
    public PageUtils<SysBaseDataAndTypeVo> getSysBaseDataAndType(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<SysBaseDataAndTypeVo> sysBaseDataAndType = sysBaseDataDao.getSysBaseDataAndType(params);
        PageInfo<SysBaseDataAndTypeVo> pageInfo = new PageInfo<>(sysBaseDataAndType);
        return PageUtils.getPageUtils(pageInfo);
    }


    /**
     * 找出重复的内容
     *
     * @param importBaseDataVos 导入的数据
     * @param type              NAME
     * @return List<String>
     */
    private List<String> getRepeteElement(List<ImportBaseDataVo> importBaseDataVos, String type) {

        Map<String, Integer> maps = Maps.newHashMap();
        importBaseDataVos.forEach(item -> {
            Integer num;
            if ("NAME".equals(type)) {
                num = maps.get(item.getDataName());
            } else {
                num = maps.get(item.getDataCode());
            }
            if (num == null) {
                num = 1;
            } else {
                num += 1;
            }
            if ("NAME".equals(type)) {
                maps.put(item.getDataName(), num);
            } else {
                maps.put(item.getDataCode(), num);
            }
        });
        List<String> repeatCode = Lists.newArrayList();
        maps.forEach((k, v) -> {
            if (v > 1) {
                repeatCode.add(k);
            }
        });
        return repeatCode;
    }


}
