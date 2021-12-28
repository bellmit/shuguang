package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.utils.*;
import com.sofn.sys.mapper.SysBaseDataMapper;
import com.sofn.sys.mapper.SysBaseDataTypeMapper;
import com.sofn.sys.mapper.SysSubSystemBaseDataOwnMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysBaseDataType;
import com.sofn.sys.model.SysSubSystemBaseDataOwn;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysBaseDataTypeService;
import com.sofn.sys.vo.SysBaseDataVo2;
import com.sofn.sys.vo.SysBaseTypeAndDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service(value = "sysBaseDataServiceType")
@Slf4j
public class SysBaseDataTypeServiceImpl extends ServiceImpl<SysBaseDataTypeMapper, SysBaseDataType> implements SysBaseDataTypeService {
    @Autowired
    private SysBaseDataTypeMapper sysBaseDataTypeDao;
    @Autowired
    private SysBaseDataMapper sysBaseDataDao;
    @Autowired
    private SysSubsystemMapper sysSubsystemDao;
    @Autowired
    private SysSubSystemBaseDataOwnMapper sysSubSystemBaseDataOwnMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<SysBaseDataType> getBaseDataTypeByName(String typename, String typevalue) {
        List<SysBaseDataType> resList = sysBaseDataTypeDao.getBaseDataTypeByName(typename,typevalue);
        System.out.println(resList.size());
        return resList;
    }

    @Override
    public List<SysBaseDataType> getBaseDataTypeByName1(String typename,String typevalue) {
        List<SysBaseDataType> resList = sysBaseDataTypeDao.getBaseDataTypeByName1(typename,typevalue);
        System.out.println(resList.size());
        return resList;
    }

    @Override
    public void saveBaseDataType(SysBaseDataType sysBaseDataType) {
        sysBaseDataType.setCreateTime(new Date());
        sysBaseDataType.setCreateUserId(UserUtil.getLoginUserId());
        sysBaseDataTypeDao.saveBaseDataType(sysBaseDataType);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delBaseDataType(String id) {
        SysBaseDataType sysBaseDataType = sysBaseDataTypeDao.selectById(id);
        List<SysBaseData> sysBaseDataList = sysBaseDataDao.getBaseDataListByTypeId(id);
        // 先删除分类下的所有基础数据
        if(sysBaseDataList != null && sysBaseDataList.size()>0) {
            List<String> idList = new ArrayList<>();
            sysBaseDataList.forEach(item -> {
                if (item == null || StringUtils.isBlank(item.getId())) {
                    return;
                }

                idList.add(item.getId());
            });

            if (idList.size() > 0){
                sysBaseDataDao.deleteBaseDataInfo(idList);
            }
        }

        // 删除基础数据类型分配表中的数据
        sysBaseDataTypeDao.delSubSystemBaseDataByType(id);

        // 删除基础数据分配表中的数据
        sysSubSystemBaseDataOwnMapper.delete(new UpdateWrapper<SysSubSystemBaseDataOwn>().eq("BASE_DATA_TYPE_ID",id));

        if (sysBaseDataType != null){
            // 再删除分类
            sysBaseDataTypeDao.delBaseDataType(id);
            // 清除缓存
            BaseDataUtils.deleteCacheByType(sysBaseDataType.getTypevalue());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateBaseDataType(String id,String typename,String typevalue) {
        Date updateTime = new Date();
        String updateUserId = UserUtil.getLoginUserId();
        SysBaseDataType sysBaseDataType = sysBaseDataTypeDao.selectById(id);
        sysBaseDataTypeDao.updateBaseDataType(id,typename,typevalue,updateTime,updateUserId);
        // 删除缓存
        if (sysBaseDataType != null) {
            BaseDataUtils.deleteCacheByType(sysBaseDataType.getTypevalue());
        }
    }

    @Override
    public List<SysBaseDataType> getBaseDataTypeById(String BaseDatatypeid) {
        List<SysBaseDataType> sysBaseDataTypeList = sysBaseDataTypeDao.getBaseDataTypeById(BaseDatatypeid);
        return sysBaseDataTypeList;
    }

    @Override
    public void saveSubSystemBaseData(String subsystemid, String baseDataids) {
        /*
         1、根据subsystemid,查询出子系统的父id集合IDs;
         2、从当前id集合中remove父id字典集合;
         3、保存只属于当前子系统的BaseData。
         */

        Set<String> baseDataIds = new HashSet<>(IdUtil.getIdsByStr(baseDataids));
        /*List<SysBaseDataType> sysParentBaseDataTypeList = getBaseDataParentBySubSystem(subsystemid);
        for(SysBaseDataType sysBaseDataType : sysParentBaseDataTypeList){
            String baseDataId = sysBaseDataType.getId();
            if(baseDataIds.contains(baseDataId)){
                baseDataIds.remove(baseDataId);
            }
        }*/

        List<SysBaseDataType> sysCurBaseDataTypeList = getBaseDataCurNodeBySubSystem(subsystemid);
        /*
         先删除当前节点已有的字典，然后再存入前台传入的字典。
         */
        for(SysBaseDataType sysBaseDataType : sysCurBaseDataTypeList){
            String BaseDataTypeId = sysBaseDataType.getId();
            sysBaseDataTypeDao.delSubSystemBaseData(BaseDataTypeId,subsystemid);
        }

        for(String baseDataID : baseDataIds){
            String subid = IdUtil.getUUId();
            sysBaseDataTypeDao.saveSubSystemBaseData(subid,subsystemid,baseDataID);
        }
    }

    @Override
    public List<SysBaseDataType> getBaseDataBySubSystem(String subsystemid) {
        /*
         1、根据subsystemid,查询出子系统的父id的字典集合;
         2、查出当前subsystemid的字典集合;
         3、合并所有的BaseData集合并返回。
         */
        List<SysBaseDataType> sysParentBaseDataTypeList = getBaseDataParentBySubSystem(subsystemid);
        List<SysBaseDataType> sysCurBaseDataTypeList = getBaseDataCurNodeBySubSystem(subsystemid);
        sysParentBaseDataTypeList.addAll(sysCurBaseDataTypeList);

        return sysParentBaseDataTypeList;
    }

    @Override
    public List<SysBaseDataType> getBaseDataParentBySubSystem(String subsystemid) {
        /*
         1、查询subsystemid的所有父节点集合IDs;
         2、遍历IDs,分别查询出当前节点的BaseDatas;
         3、合并所有的BaseDatas
         */
        List<SysBaseDataType> resList = new ArrayList<>();
        List<SysSubsystem> sysSubsystemList = sysSubsystemDao.getParentIdsById(subsystemid);
        if(sysSubsystemList.size()>0){
            SysSubsystem sysSubsystem = sysSubsystemList.get(0);
            String subID = sysSubsystem.getParentIds();
            String[] subIDs = subID.split("//");

            for(int i=0;i<subIDs.length-1;i++){
                if(!"1".equals(subIDs[i])){
                    List<SysBaseDataType> sysBaseDataTypeList = sysBaseDataTypeDao.getBaseDataCurNodeBySubSystem(subIDs[i]);
                    resList.addAll(sysBaseDataTypeList);
                }
            }
        }else {
            log.warn("子系统{}不存在",subsystemid);
           return Lists.newArrayList();
        }

        return resList;
    }

    @Override
    public List<SysBaseDataType> getBaseDataCurNodeBySubSystem(String subsystemid) {
        /*
         1、查询当前节点的BaseData集合
         */
        List<SysBaseDataType> sysBaseDataTypeList = sysBaseDataTypeDao.getBaseDataCurNodeBySubSystem(subsystemid);
        return sysBaseDataTypeList;
    }

    @Override
    public PageUtils<SysBaseTypeAndDataVo> getSysBaseDataByPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        if(pageNo != null && pageSize != null){
            PageHelper.offsetPage(pageNo,pageSize);
        }
        // 1. 分页查询基础数据类型
        List<SysBaseDataType> sysBaseDataTypes =  sysBaseDataTypeDao.getBaseDataTypeByKeyword(params);
        PageInfo<SysBaseDataType> pageInfo = new PageInfo<>(sysBaseDataTypes);
        List<SysBaseDataType> list = pageInfo.getList();
        List<SysBaseData> sysBaseData = null;
        // 转为VO对象
        PageInfo<SysBaseTypeAndDataVo> sysBaseTypeAndDataVoPageInfo = new PageInfo<>();
        List<SysBaseTypeAndDataVo> sysBaseTypeAndDataVos = Lists.newArrayList();
        BeanUtils.copyProperties(pageInfo,sysBaseTypeAndDataVoPageInfo);
        if(!CollectionUtils.isEmpty(list)){
            sysBaseData = sysBaseDataDao.getBaseDataByBaseTypeIds(list.stream().map(SysBaseDataType::getId).collect(Collectors.toList()));
            List<SysBaseTypeAndDataVo> collect = list.stream().map(item -> SysBaseTypeAndDataVo.getSysBaseTypeAndDataVo(item)).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(sysBaseData)){
                List<SysBaseDataVo2> collect1 = sysBaseData.stream()
                        .filter(item->!BoolUtils.Y.equals(item.getDelFlag()))
                        .map(item -> SysBaseDataVo2.getSysBaseDataVo2(item))
                        .collect(Collectors.toList());
                collect.forEach(e->{
                      if(e!= null&&!CollectionUtils.isEmpty(collect1)){
                          List<SysBaseDataVo2> collect2 = collect1.stream().filter(item -> e.getId().equals(item.getBaseTypeId())).collect(Collectors.toList());
                          e.setSysBaseDataVo2List(collect2);
                      }

                });
            }
            sysBaseTypeAndDataVoPageInfo.setList(collect);
        }



        return PageUtils.getPageUtils(sysBaseTypeAndDataVoPageInfo);
    }

    @Override
    public PageUtils<SysBaseDataType> getBaseTypeByPage(String keyword, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String,Object> params = Maps.newHashMap();
        params.put("keyword", keyword);
        List<SysBaseDataType> baseDataTypeByKeyword = sysBaseDataTypeDao.getBaseDataTypeByKeyword(params);
        PageInfo<SysBaseDataType> pageInfo = new PageInfo<>(baseDataTypeByKeyword);
        return PageUtils.getPageUtils(pageInfo);
    }
}
