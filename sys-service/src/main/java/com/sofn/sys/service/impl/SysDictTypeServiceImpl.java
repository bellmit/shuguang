package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import com.sofn.sys.mapper.SysDictMapper;
import com.sofn.sys.mapper.SysDictTypeMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.model.SysDictType;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysDictTypeService;
import com.sofn.sys.vo.SysDictForm;
import com.sofn.sys.vo.SysDictTypeAndValueVo;
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
        // 先删除分类下的所有字典数据
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

        // 再删除分类
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
         1、根据subsystemid,查询出子系统的父id集合IDs;
         2、从当前id集合中remove父id字典集合;
         3、保存只属于当前子系统的dict。
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
         先删除当前节点已有的字典，然后再存入前台传入的字典。
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
         1、根据subsystemid,查询出子系统的父id的字典集合;
         2、查出当前subsystemid的字典集合;
         3、合并所有的dict集合并返回。
         */
        List<SysDictType> sysParentDictTypeList = getDictParentBySubSystem(subsystemid);
        List<SysDictType> sysCurDictTypeList = getDictCurNodeBySubSystem(subsystemid);
        sysParentDictTypeList.addAll(sysCurDictTypeList);

        return sysParentDictTypeList;
    }

    @Override
    public List<SysDictType> getDictParentBySubSystem(String subsystemid) {
        /*
         1、查询subsystemid的所有父节点集合IDs;
         2、遍历IDs,分别查询出当前节点的dicts;
         3、合并所有的dicts
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
            throw new SofnException("当前子系统父节点不存在，请重新选择子系统");
        }

        return resList;
    }

    @Override
    public List<SysDictType> getDictCurNodeBySubSystem(String subsystemid) {
        /*
         1、查询当前节点的dict集合
         */
        List<SysDictType> sysDictTypeList = sysDictTypeDao.getDictCurNodeBySubSystem(subsystemid);
        return sysDictTypeList;
    }

    @Override
    public PageUtils<SysDictTypeAndValueVo> getDictTypeAndValueByPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        // 1. 分页查询字典类型 查询条件只对类型有用
        if(pageNo != null && pageSize != null){
            PageHelper.offsetPage(pageNo,pageSize);
        }
        List<SysDictTypeAndValueVo> dictTypeListByKeyword = sysDictTypeDao.getDictTypeListByKeyword(params);
        PageInfo<SysDictTypeAndValueVo> sysDictTypeAndValueVoPageInfo = new PageInfo<>(dictTypeListByKeyword);
        // 2. 根据type找出所有的value
        List<SysDictTypeAndValueVo> list = sysDictTypeAndValueVoPageInfo.getList();
        if(!CollectionUtils.isEmpty(list)){
            List<SysDict> sysDicts = sysDictDao.selectList(new QueryWrapper<SysDict>()
                    .in("DICTTYPEID", list.stream().map(SysDictTypeAndValueVo::getId).collect(Collectors.toList()))
            );
            list.forEach(item->{
                if(item != null &&  !CollectionUtils.isEmpty(sysDicts)){
                    List<SysDict> collect = sysDicts.stream().filter(e -> item.getId().equals(e.getDicttypeid()) && !BoolUtils.Y.equals(e.getDelFlag())).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(collect)){
                        // 将持久对象转为Vo对象
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
