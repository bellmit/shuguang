package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.broadcast.BroadcastEventPublisher;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BaseDataUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.event.bean.BaseDataAllotEvent;
import com.sofn.sys.mapper.SysBaseDataMapper;
import com.sofn.sys.mapper.SysBaseDataTypeMapper;
import com.sofn.sys.mapper.SysSubSystemBaseDataOwnMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysBaseDataType;
import com.sofn.sys.model.SysSubSystemBaseDataOwn;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.SysBaseTypeAndValueService;
import com.sofn.sys.util.RegionUtils;
import com.sofn.sys.vo.SysBaseTypeAndValueVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2020/2/28 15:24
 */
@Service
@Slf4j
public class SysBaseTypeAndValueServiceImpl implements SysBaseTypeAndValueService {

    /**
     * 基础数据类型
     */
    private final static String BASE_ENUM_TYPE = "TYPE";

    /**
     * 基础数据值
     */
    private final static String BASE_ENUM_VALUE = "VALUE";

    /**
     * 连接串之间的分隔符
     */
    private final static String SPLIT = "_";

    @Autowired
    private SysBaseDataTypeMapper sysBaseDataTypeMapper;

    @Autowired
    private SysBaseDataMapper sysBaseDataMapper;

    @Autowired
    private SysSubSystemBaseDataOwnMapper sysSubSystemBaseDataOwnMapper;

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;

    @Autowired
    private BroadcastEventPublisher broadcastEventPublisher;

    @Override
    public List<SysBaseTypeAndValueVo> getAllBaseTypeAndValue() {
        // 1. 查询所有的基础数据信息
        List<SysBaseDataType> sysBaseDataTypes = sysBaseDataTypeMapper.selectList(new QueryWrapper<SysBaseDataType>());
        if(!CollectionUtils.isEmpty(sysBaseDataTypes)){
            sysBaseDataTypes = sysBaseDataTypes.stream().filter(item -> !"N".equals(item.getEnable())).collect(Collectors.toList());
        }

        // 2. 查询所有的数据字典值
        List<SysBaseData> sysBaseDatas = sysBaseDataMapper.getBaseDataInfo();
        if(!CollectionUtils.isEmpty(sysBaseDatas)){
            // 只显示启用的
            sysBaseDatas =  sysBaseDatas.stream().filter(item ->  !"N".equals(item.getEnable())).collect(Collectors.toList());
        }
        // 3. 按字典类别ID构建第二层 数据字典值数据
        Map<String, List<SysBaseTypeAndValueVo>> maps = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(sysBaseDatas) && !CollectionUtils.isEmpty(sysBaseDataTypes)) {
            sysBaseDatas.forEach(item -> {
                if (item != null) {
                    SysBaseDataType sysBaseDataType = item.getSysBaseDataType();
                    if (sysBaseDataType != null) {
                        String id = sysBaseDataType.getId();
                        List<SysBaseTypeAndValueVo> sysBaseTypeAndValueVos = Lists.newArrayList();
                        if (maps.containsKey(id)) {
                            sysBaseTypeAndValueVos = maps.get(id);
                        }
                        if (CollectionUtils.isEmpty(sysBaseTypeAndValueVos)) {
                            sysBaseTypeAndValueVos = Lists.newArrayList();
                        }
                        SysBaseTypeAndValueVo sysBaseTypeAndValueVo = new SysBaseTypeAndValueVo();
                        // 数据字典值的ID组成为 BASE_ENUM +   baseTypeId +  id
                        sysBaseTypeAndValueVo.setId(BASE_ENUM_VALUE + SPLIT + id + SPLIT + item.getId());
                        sysBaseTypeAndValueVo.setNodeName(item.getBaseDataname());
                        sysBaseTypeAndValueVo.setNodeCode(item.getBaseDatacode());
                        sysBaseTypeAndValueVos.add(sysBaseTypeAndValueVo);
                        maps.put(id, sysBaseTypeAndValueVos);
                    }
                }

            });
        }

        // 4. 构建第一层数据类型数据    有值的数据字典才能分配！没值的不显示
        List<SysBaseTypeAndValueVo> sysBaseTypeAndValueVoList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(sysBaseDataTypes)) {
            sysBaseDataTypes.forEach(item -> {
                if (item != null) {
                    List<SysBaseTypeAndValueVo> sysBaseTypeAndValueVos = maps.get(item.getId());
                    if(!CollectionUtils.isEmpty(sysBaseTypeAndValueVos)){
                        SysBaseTypeAndValueVo sysBaseTypeAndValueVo = new SysBaseTypeAndValueVo();
                        // 数据类型ID为BASE_ENUM+ id
                        sysBaseTypeAndValueVo.setId(BASE_ENUM_TYPE + SPLIT + item.getId());
                        sysBaseTypeAndValueVo.setNodeCode(item.getTypevalue());
                        sysBaseTypeAndValueVo.setNodeName(item.getTypename());
                        sysBaseTypeAndValueVo.setChildren(sysBaseTypeAndValueVos);
                        sysBaseTypeAndValueVoList.add(sysBaseTypeAndValueVo);
                    }

                }
            });

        }
        return sysBaseTypeAndValueVoList;
    }

    @Override
    public List<String> getSubSystemHaveBaseTypeAndValueId(String appId) {
        // 1. 判断子系统是否存在
        if (!StringUtils.isBlank(appId)) {

            List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>().eq("APP_ID", appId));
            if (CollectionUtils.isEmpty(sysSubsystems)) {
                return Lists.newArrayList();
            }

            List<SysSubsystem> collect = sysSubsystems.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)) {
                return Lists.newArrayList();
            }
            SysSubsystem sysSubsystem = collect.get(0);
            if (sysSubsystem == null) {
                return Lists.newArrayList();
            }
        }
        List<SysSubSystemBaseDataOwn> sysSubSystemBaseDataOwns = sysSubSystemBaseDataOwnMapper.selectList(new QueryWrapper<SysSubSystemBaseDataOwn>().eq("APP_ID", appId));
        Set<String> typeIds = Sets.newHashSet();
        Set<String> valueIds = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(sysSubSystemBaseDataOwns)) {
            sysSubSystemBaseDataOwns.forEach(item -> {
                if (item != null) {
                    // 数据字典值的ID组成为 BASE_ENUM +   baseTypeId +  id
                    // 数据类型ID为BASE_ENUM+ id
                    typeIds.add(BASE_ENUM_TYPE + SPLIT + item.getBaseDataTypeId());
                    valueIds.add(BASE_ENUM_VALUE + SPLIT + item.getBaseDataTypeId() + SPLIT + item.getBaseDataId());
                }
            });
        }
        List<String> subSystemHaveBaseTypeAndValueId = Lists.newArrayList();
        subSystemHaveBaseTypeAndValueId.addAll(typeIds);
        subSystemHaveBaseTypeAndValueId.addAll(valueIds);
        return subSystemHaveBaseTypeAndValueId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSubSystemAndBaseDataRelationship(List<String> ids, String appId) {
        // 使用批量新增   这里统一添加
        if (!StringUtils.isBlank(appId)) {
            List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>().eq("APP_ID", appId));
            if (CollectionUtils.isEmpty(sysSubsystems)) {
                return ;
            }
            List<SysSubsystem> collect = sysSubsystems.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)) {
                return ;
            }
            SysSubsystem sysSubsystem = collect.get(0);
            if (sysSubsystem == null) {
                return ;
            }
        }

        Map<String, List<SysBaseData>> groupAllotMap = new HashMap<>();

        // 1. 将ID按照规则拆分
        // 数据字典值的ID组成为 BASE_ENUM +   baseTypeId +  id
        // 数据类型ID为BASE_ENUM+ id
        List<SysSubSystemBaseDataOwn> sysSubSystemBaseDataOwns = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ids)) {
            ids.forEach(item-> {
                if(!StringUtils.isBlank(item)){
                    try{
                        List<String> elements = Lists.newArrayList(item.split(SPLIT));
                        if(BASE_ENUM_TYPE.equals(elements.get(0))){
                            // 字典类型


                        }else if(BASE_ENUM_VALUE.equals(elements.get(0))){
                            List<SysBaseData> baseDataList = sysBaseDataMapper.getBaseDataById(Lists.newArrayList(elements.get(2)));
                            if (!CollectionUtils.isEmpty(baseDataList)){
                              SysBaseData sysBaseData = baseDataList.get(0);
                              if (sysBaseData.getSysBaseDataType() != null && StringUtils.isNotBlank(sysBaseData.getSysBaseDataType().getId())){
                                SysBaseDataType sysBaseDataType = sysBaseDataTypeMapper.selectById(sysBaseData.getSysBaseDataType().getId());
                                String typeValue = sysBaseDataType.getTypevalue();
                                groupAllotMap.computeIfAbsent(typeValue, k -> new ArrayList<>());
                                groupAllotMap.get(typeValue).add(sysBaseData);
                              }
                            }

                            // 字典值
                            SysSubSystemBaseDataOwn sysSubSystemBaseDataOwn = new SysSubSystemBaseDataOwn();
                            sysSubSystemBaseDataOwn.setAppId(appId);
                            sysSubSystemBaseDataOwn.setBaseDataId(elements.get(2));
                            sysSubSystemBaseDataOwn.setBaseDataTypeId(elements.get(1));
                            sysSubSystemBaseDataOwn.setId(IdUtil.getUUId());
                            sysSubSystemBaseDataOwns.add(sysSubSystemBaseDataOwn);
                        }else{
                            log.warn("传入的ID不合法:传入的ID为:{}" + item);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        throw new SofnException("传入值不合法，传入的ID为" + item);
                    }
                }
            });
        }
        // 2. 将当前系统的所有关系全部清除
        Map<String, Object> columnMap = Maps.newHashMap();
        columnMap.put("APP_ID",appId);
        sysSubSystemBaseDataOwnMapper.deleteByMap(columnMap);
        // 3. 批量新增
        if(!CollectionUtils.isEmpty(sysSubSystemBaseDataOwns)){
            sysSubSystemBaseDataOwnMapper.batchSave(sysSubSystemBaseDataOwns);
        }

        // 4. 清除缓存
        BaseDataUtils.clearAllCache();

        // 5. 如果字典类型是虚拟区划，发送一个广播
        if (groupAllotMap.containsKey(RegionUtils.dict_value)){
          BaseDataAllotEvent event = new BaseDataAllotEvent();
          event.setAppId(appId);
          broadcastEventPublisher.send(event);
        }
    }
}
