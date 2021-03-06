package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.SysDictMapper;
import com.sofn.ducss.mapper.SysDictTypeMapper;
import com.sofn.ducss.mapper.SysSubsystemMapper;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.model.SysDictType;
import com.sofn.ducss.model.SysSubsystem;
import com.sofn.ducss.service.SysDictTypeService;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.SysDictForm;
import com.sofn.ducss.vo.SysDictTypeAndValueVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * zhouqingchun
 * 20190613
 */
@SuppressWarnings("ALL")
@Service(value = "sysDictTypeService")
public class SysDictTypeServiceImpl  extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeDao;
    @Autowired
    private SysDictMapper sysDictDao;
    @Autowired
    private SysSubsystemMapper sysSubsystemDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<SysDictType> getDictTypeByName(String typename,String typevalue) {
        List<SysDictType> resList = sysDictTypeDao.getDictTypeByName(typename,typevalue);
        System.out.println(resList.size());
        return resList;
    }

    @Override
    public List<SysDictType> getDictTypeByName1(String typename,String typevalue) {
        List<SysDictType> resList = sysDictTypeDao.getDictTypeByName1(typename,typevalue);
        System.out.println(resList.size());
        return resList;
    }

    @Override
    public void saveDictType(SysDictType sysDictType) {
        sysDictType.setCreateTime(new Date());
        sysDictType.setCreateUserId(UserUtil.getLoginUserId());
        sysDictTypeDao.saveDictType(sysDictType);
        DictUtils.deleteCacheByType(sysDictType.getTypevalue());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delDictType(String id) {
        SysDictType dictType = sysDictTypeDao.selectById(id);
        List<SysDict> sysDictList = sysDictDao.getDictListByTypeId(id);
        // ???????????????????????????????????????
        if(sysDictList != null && sysDictList.size()>0) {
            List<String> idList = new ArrayList<>();
            sysDictList.forEach(item -> {
                if (item == null || StringUtils.isBlank(item.getId())) {
                    return;
                }

                idList.add(item.getId());
            });

            if (idList.size() > 0){
                sysDictDao.deleteDictInfo(idList);
            }
        }

        // ???????????????
        sysDictTypeDao.delDictType(id);
        if (dictType != null) {
          DictUtils.deleteCacheByType(dictType.getTypevalue());
        }
    }

    @Override
    public void updateDictType(String id,String typename,String typevalue) {
        Date updateTime = new Date();
        String updateUserId = UserUtil.getLoginUserId();
        sysDictTypeDao.updateDictType(id,typename,typevalue,updateTime,updateUserId);

        SysDictType dictType = sysDictTypeDao.selectById(id);
        if (dictType != null) {
          DictUtils.deleteCacheByType(dictType.getTypevalue());
        }
    }

    @Override
    public List<SysDictType> getDictTypeById(String dicttypeid) {
        List<SysDictType> sysDictTypeList = sysDictTypeDao.getDictTypeById(dicttypeid);
        return sysDictTypeList;
    }

    @Deprecated
    @Override
    public void saveSubSystemDict(String subsystemid, String dictids) {
        /*
         1?????????subsystemid,????????????????????????id??????IDs;
         2????????????id?????????remove???id????????????;
         3????????????????????????????????????dict???
         */
        List<SysDictType> sysParentDictTypeList = getDictParentBySubSystem(subsystemid);
        List<String> dictIDs = IdUtil.getIdsByStr(dictids);
        for(SysDictType sysDictType : sysParentDictTypeList){
            String dictID = sysDictType.getId();
            if(dictIDs.contains(dictID)){
                dictIDs.remove(dictID);
            }
        }

        List<SysDictType> sysCurDictTypeList = getDictCurNodeBySubSystem(subsystemid);
        /*
         ??????????????????????????????????????????????????????????????????????????????
         */
        for(SysDictType sysDictType : sysCurDictTypeList){
            String dictTypeId = sysDictType.getId();
            sysDictTypeDao.delSubSystemDict(dictTypeId,subsystemid);
        }
        for(String dictID : dictIDs){
            String subid = IdUtil.getUUId();
            sysDictTypeDao.saveSubSystemDict(subid,subsystemid,dictID);
        }
    }

    @Override
    public List<SysDictType> getDictBySubSystem(String subsystemid) {
        /*
         1?????????subsystemid,????????????????????????id???????????????;
         2???????????????subsystemid???????????????;
         3??????????????????dict??????????????????
         */
        List<SysDictType> sysParentDictTypeList = getDictParentBySubSystem(subsystemid);
        List<SysDictType> sysCurDictTypeList = getDictCurNodeBySubSystem(subsystemid);
        sysParentDictTypeList.addAll(sysCurDictTypeList);

        return sysParentDictTypeList;
    }

    @Override
    public List<SysDictType> getDictParentBySubSystem(String subsystemid) {
        /*
         1?????????subsystemid????????????????????????IDs;
         2?????????IDs,??????????????????????????????dicts;
         3??????????????????dicts
         */
        List<SysDictType> resList = new ArrayList<>();
        List<SysSubsystem> sysSubsystemList = sysSubsystemDao.getParentIdsById(subsystemid);
        if(sysSubsystemList.size()>0){
            SysSubsystem sysSubsystem = sysSubsystemList.get(0);
            String subID = sysSubsystem.getParentIds();
            String[] subIDs = subID.split("//");

            for(int i=0;i<subIDs.length-1;i++){
                if(!"1".equals(subIDs[i])){
                    List<SysDictType> sysDictTypeList = sysDictTypeDao.getDictCurNodeBySubSystem(subIDs[i]);
                    resList.addAll(sysDictTypeList);
                }
            }

            /*List<SysDictType> curList = sysDictTypeDao.getDictCurNodeBySubSystem(subsystemid);
            resList.addAll(curList);*/
        }else {
            throw new SofnException("????????????????????????????????????????????????????????????");
        }

        return resList;
    }

    @Override
    public List<SysDictType> getDictCurNodeBySubSystem(String subsystemid) {
        /*
         1????????????????????????dict??????
         */
        List<SysDictType> sysDictTypeList = sysDictTypeDao.getDictCurNodeBySubSystem(subsystemid);
        return sysDictTypeList;
    }

    @Override
    public PageUtils<List<SysDictTypeAndValueVo>> getDictTypeAndValueByPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        // 1. ???????????????????????? ??????????????????????????????
        if(pageNo != null && pageSize != null){
            PageHelper.offsetPage(pageNo,pageSize);
        }
        List<SysDictTypeAndValueVo> dictTypeListByKeyword = sysDictTypeDao.getDictTypeListByKeyword(params);
        PageInfo<SysDictTypeAndValueVo> sysDictTypeAndValueVoPageInfo = new PageInfo<>(dictTypeListByKeyword);
        // 2. ??????type???????????????value
        List<SysDictTypeAndValueVo> list = sysDictTypeAndValueVoPageInfo.getList();
        if(!CollectionUtils.isEmpty(list)){
            List<SysDict> sysDicts = sysDictDao.selectList(new QueryWrapper<SysDict>()
                    .in("DICTTYPEID", list.stream().map(SysDictTypeAndValueVo::getId).collect(Collectors.toList()))
            );
            list.forEach(item->{
                if(item != null &&  !CollectionUtils.isEmpty(sysDicts)){
                    List<SysDict> collect = sysDicts.stream().filter(e -> item.getId().equals(e.getDicttypeid()) && !BoolUtils.Y.equals(e.getDelFlag())).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(collect)){
                        // ?????????????????????Vo??????
                        List<SysDictForm> collect1 = collect.stream().map(e -> {
                            SysDictForm sysDictForm = new SysDictForm();
                            BeanUtils.copyProperties(e, sysDictForm);
                            return sysDictForm;
                        }).collect(Collectors.toList());
                        item.setSysDictForm(collect1);
                    }
                }

            });
        }
        sysDictTypeAndValueVoPageInfo.setList(list);
        return PageUtils.getPageUtils(sysDictTypeAndValueVoPageInfo);
    }
}
