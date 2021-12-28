package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.excel.ExcelExportUtil;
import com.sofn.ducss.excel.test.testimport.ImportDictBean;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.SysDictMapper;
import com.sofn.ducss.mapper.SysDictTypeMapper;
import com.sofn.ducss.mapper.SysDictionaryMapper;
import com.sofn.ducss.model.SysDictionary;
import com.sofn.ducss.service.SysDictService;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.model.SysDictType;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.SysDictAllVo;
import com.sofn.ducss.vo.SysDictVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
@Service(value = "sysDictService")
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper sysDictDao;
    @Autowired
    private SysDictTypeMapper sysDictTypeDao;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Override
    public PageUtils<HashMap<String, Object>> getDictInfo(String typekeyname, Integer pageNo, Integer pageSize) {

        if (pageNo != null && pageSize != null) {
            PageHelper.offsetPage(pageNo, pageSize);
        }


        /* 1、typekeyname 为字典类型name时候；2、typekeyname为字典name的时候 */

        if (typekeyname == null) {
            typekeyname = "";
        }

        List<HashMap<String, Object>> dictListDict = new ArrayList<>();
        /* 根据字典值查询字典列表 */
        List<SysDict> sysDicts = sysDictDao.getDictByName(typekeyname);
        /* 按照字典类型分组 */
        List<SysDictType> dictTypes = new ArrayList<>();
        for (SysDict sysDict : sysDicts) {
            SysDictType sysDictType = sysDict.getSysDictType();
            String dictTypeID = sysDictType.getId();
            if (!dictTypes.contains(sysDictType)) {
                dictTypes.add(sysDictType);
            }
        }
        for (SysDictType sysDictType : dictTypes) {
            HashMap<String, Object> res = new HashMap<>();
            List<SysDict> subList = new ArrayList<>();
            String dictTypeID = sysDictType.getId();

            for (SysDict sysDict : sysDicts) {
                SysDictType sysDictType1 = sysDict.getSysDictType();
                String dictTypeID1 = sysDictType1.getId();
                if (dictTypeID.equals(dictTypeID1)) {
                    subList.add(sysDict);
                }
            }

            List<SysDictType> types = sysDictTypeDao.getDictTypeById(dictTypeID);
            if (types == null || types.size() == 0) {
                continue;
            }

            SysDictType sysDictType1 = types.get(0);
            String dictTypeId = sysDictType1.getId();
            String dictTypeName = sysDictType1.getTypename();
            String dictTypeValue = sysDictType1.getTypevalue();

            res.put("id", dictTypeId);
            res.put("typename", dictTypeName);
            res.put("typevalue", dictTypeValue);
            res.put("data", subList);
            dictListDict.add(res);
        }


        //1、查询出当前页的类型；2、根据类型分别查询出字典；3、组装结果集

//        List<SysDict> dictGroup = sysDictDao.getDictType(dicttypeid);
        List<SysDictType> dictTypeGroup = sysDictTypeDao.getDictTypeByName(typekeyname, "");
        PageInfo<SysDictType> pageInfo = new PageInfo<SysDictType>(dictTypeGroup);
        PageInfo<HashMap<String, Object>> pageInfo2 = new PageInfo<HashMap<String, Object>>();
        BeanUtils.copyProperties(pageInfo, pageInfo2);
        List<SysDictType> list = pageInfo.getList();
        List<HashMap<String, Object>> dictList = Lists.newArrayList();

        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> res = new HashMap<>();
            SysDictType sysDictType = dictTypeGroup.get(i);
            String dictTypeId = sysDictType.getId();
            String dictTypeName = sysDictType.getTypename();
            String dictTypeValue = sysDictType.getTypevalue();
            List<SysDict> subDict = sysDictDao.getDictByValueAndType(dictTypeId, "", "");
            res.put("id", dictTypeId);
            res.put("typename", dictTypeName);
            res.put("typevalue", dictTypeValue);
            res.put("data", subDict);
            dictList.add(res);
        }

        /*dictList.removeAll(dictListDict);
        dictList.addAll(dictListDict);*/
        for (HashMap<String, Object> resMap : dictListDict) {
            if (!dictList.contains(resMap)) {
                dictList.add(resMap);
            }
        }

        pageInfo2.setList(dictList);
//        pageInfo.setTotal(dictTypeGroup.size());
        return PageUtils.getPageUtils(pageInfo2);
    }

    @Override
    public void saveDictInfo(SysDictVo sysDictVo) {
        SysDict sysDict = SysDictVo.getSysDict(sysDictVo);
        Date now = new Date();
        String loginUserId = UserUtil.getLoginUserId();
        sysDict.setCreateTime(now);
        sysDict.setCreateUserId(loginUserId);
        sysDict.setUpdateTime(now);
        sysDict.setUpdateUserId(loginUserId);
        sysDict.setDelFlag("Y");

        sysDictDao.saveDictInfo(sysDict);
        SysDictType dictType = sysDictTypeDao.selectById(sysDictVo.getSysDictTypeVo().getId());
        DictUtils.deleteCacheByType(dictType.getTypevalue());

        LogUtil.addLog(LogEnum.LOG_TYPE_STRAW_TYPE_ADD.getCode(), "作物新增-" + sysDictVo.getDictname());
    }

    @Override
    public void updateDictInfo(String id, String enable) {
        Date updateTime = new Date();
        String updateUserId = UserUtil.getLoginUserId();
        sysDictDao.updateDictInfo(id, enable, updateTime, updateUserId);

        List<SysDict> sysDictList = sysDictDao.getDictById(id);
        if (sysDictList.size() > 0) {
            SysDict sysDict = sysDictList.get(0);
            SysDictType sysDictType = sysDict.getSysDictType();
            SysDictType dictType = sysDictTypeDao.selectById(sysDictType.getId());
            DictUtils.deleteCacheByType(dictType.getTypevalue());

            if ("N".equals(enable)) {
                LogUtil.addLog(LogEnum.LOG_TYPE_STRAW_TYPE_DISABLE.getCode(), "停用-" + sysDict.getDictname());
            } else if ("Y".equals(enable)) {
                LogUtil.addLog(LogEnum.LOG_TYPE_STRAW_TYPE_ENABLE.getCode(), "启用-" + sysDict.getDictname());
            }
        }
    }

    @Override
    public void deleteDictInfo(String id) {
        // 先清空缓存
        List<SysDict> sysDictList = sysDictDao.getDictById(id);
        if (sysDictList.size() > 0) {
            SysDict sysDict = sysDictList.get(0);
            SysDictType sysDictType = sysDict.getSysDictType();
            SysDictType dictType = sysDictTypeDao.selectById(sysDictType.getId());
            DictUtils.deleteCacheByType(dictType.getTypevalue());

            LogUtil.addLog(LogEnum.LOG_TYPE_STRAW_TYPE_DELETE.getCode(), "删除-" + sysDict.getDictname());
        }

        // id,id
        List<String> ids = IdUtil.getIdsByStr(id);
        if (!CollectionUtils.isEmpty(ids)) {
            sysDictDao.deleteDictInfo(ids);
        }
    }

    @Override
    public List<SysDict> getDictByName(String dictname) {
        List<SysDict> res = sysDictDao.getDictByName(dictname);
        return res;
    }

    @Override
    public List<SysDict> getDictByValueAndType(String dicttypeid, String dictname, String dictcode) {
        List<SysDict> res = sysDictDao.getDictByValueAndType(dicttypeid, dictname, dictcode);
        return res;
    }

    @Override
    public void exportDict(String filePath) {
        List<SysDict> res = sysDictDao.getDictByValueAndType("", "", "");
        if (!CollectionUtils.isEmpty(res)) {
            List<SysDictVo> sysDictVos = Lists.newArrayList();
            res.forEach((sysDict -> {
                SysDictVo sysDictVo = SysDictVo.getSysDictVo(sysDict);
                sysDictVos.add(sysDictVo);
            }));
            System.out.println(res.size());
            log.info("开始生成文件");
            ExcelExportUtil.createExcel(filePath, ImportDictBean.class, sysDictVos);
            log.info("结束生成文件");
        }

    }

    @Override
    public List<SysDict> getDictById(String id) {
        List<SysDict> sysDictList = sysDictDao.getDictById(id);
        return sysDictList;
    }

    @Override
    public List<SysDict> getDictNameByValueAndType(String typename, String dictcode) {
        List<SysDict> sysDictList = sysDictDao.getDictNameByValueAndType(typename, dictcode);
        return sysDictList;
    }

    @Override
    public List<SysDict> getDictListByType(String typevalue) {
        List<SysDict> sysDictList = sysDictDao.getDictListByType(typevalue, null);
        return sysDictList;
    }

    @Override
    public List<SysDict> getDictListByTypeAndName(String typevalue, String keyword) {
        List<SysDict> sysDictList = sysDictDao.getDictListByType(typevalue, keyword);
        return sysDictList;
    }

    @Override
    public List<SysDictAllVo> getSysDictAllList() {
        List<SysDictAllVo> dictAllVoList = new ArrayList<>();
        SysDictAllVo sysDictAllVos = new SysDictAllVo();
        sysDictAllVos.setId("1");
        sysDictAllVos.setTitle("全部作物");
        sysDictAllVos.setValue("all_type");
        dictAllVoList.add(sysDictAllVos);
        List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(null);
        SysDictAllVo sysDictAllVo = null;
        for (int i = 0; i < list.size(); i++) {
            sysDictAllVo = new SysDictAllVo();
            sysDictAllVo.setId(i + 2 + "");
            sysDictAllVo.setTitle(list.get(i).getDictValue());
            sysDictAllVo.setValue(list.get(i).getDictKey());
            dictAllVoList.add(sysDictAllVo);
        }
        return dictAllVoList;
    }

    @Override
    public SysDict getAllByName(String dicttypeid, String dictname) {
        return sysDictDao.getAllByName(dicttypeid, dictname);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String saveOrUpdateDict(SysDictVo sysDictVo, SysDict dictDb) {
        String id = sysDictVo.getId();
        String remark = sysDictVo.getRemark();
        String enable = sysDictVo.getEnable();
        if (StringUtils.isBlank(id)) {
            if (dictDb != null && "Y".equals(dictDb.getDelFlag())) {
                throw new SofnException("该作物名称已存在");
            }
            if (dictDb == null) {
                sysDictVo.setDictcode(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_STR));
                saveDictInfo(sysDictVo);
            } else {
                dictDb.setDelFlag("Y");
                dictDb.setRemark(remark == null ? " " : remark);
                dictDb.setEnable(enable);
                dictDb.setCreateTime(new Date());
                dictDb.setCreateUserId(UserUtil.getLoginUserId());
                updateById(dictDb);
            }
            LogUtil.addLog(LogEnum.LOG_TYPE_STRAW_TYPE_EDIT.getCode(), "新增-" + sysDictVo.getDictname());
            return "保存成功";
        } else {
            if (dictDb != null && "Y".equals(dictDb.getDelFlag()) && !dictDb.getId().equals(id)) {
                throw new SofnException("该作物名称已存在");
            }
            if (dictDb == null) {
                SysDict sysDict = SysDictVo.getSysDict(sysDictVo);
                sysDict.setUpdateTime(new Date());
                sysDict.setUpdateUserId(UserUtil.getLoginUserId());
                updateById(sysDict);
            } else {
                dictDb.setDelFlag("Y");
                dictDb.setRemark(remark == null ? " " : remark);
                dictDb.setEnable(enable);
                dictDb.setUpdateTime(new Date());
                dictDb.setUpdateUserId(UserUtil.getLoginUserId());
                updateById(dictDb);
                if (!dictDb.getId().equals(id)) {
                    deleteDictInfo(id);
                }
            }
            LogUtil.addLog(LogEnum.LOG_TYPE_STRAW_TYPE_EDIT.getCode(), "编辑-" + sysDictVo.getDictname());
            return "修改成功";
        }
    }

}
