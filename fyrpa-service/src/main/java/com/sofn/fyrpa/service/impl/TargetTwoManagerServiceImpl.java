package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.enums.DeleteEnum;
import com.sofn.fyrpa.enums.StatusEnum;
import com.sofn.fyrpa.mapper.TargetOneManagerMapper;
import com.sofn.fyrpa.mapper.TargetTwoManagerMapper;
import com.sofn.fyrpa.model.TargetOneManager;
import com.sofn.fyrpa.model.TargetOneManagerEpoch;
import com.sofn.fyrpa.model.TargetTwoManager;
import com.sofn.fyrpa.model.TargetTwoManagerEpoch;
import com.sofn.fyrpa.service.TargetOneManagerEpochService;
import com.sofn.fyrpa.service.TargetTwoManagerEpochService;
import com.sofn.fyrpa.service.TargetTwoManagerService;
import com.sofn.fyrpa.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TargetTwoManagerServiceImpl extends ServiceImpl<TargetTwoManagerMapper, TargetTwoManager> implements TargetTwoManagerService {
    @Autowired
    private TargetTwoManagerMapper targetTwoManagerMapper;

    @Autowired
    private TargetOneManagerMapper targetOneManagerMapper;

    @Lazy
    @Autowired
    private TargetOneManagerEpochService targetOneManagerEpochService;

    @Lazy
    @Autowired
    private TargetTwoManagerEpochService targetTwoManagerEpochService;

    @Transactional
    @Override
    public Result addTargetOneManager(TargetOneManagerAddVo targetOneManagerAddVo) {
        if(targetOneManagerAddVo == null || StringUtils.isEmpty(targetOneManagerAddVo.getTargetName())){
            return Result.error("??????????????????????????????????????????");
        }
        List<TargetOneManager> isExistTargetOne = targetOneManagerMapper.selectTargetOneManagerByNameList(targetOneManagerAddVo.getTargetName().replace(" ",""));
        if(isExistTargetOne != null && !isExistTargetOne.isEmpty()){
            return Result.error("????????????????????????????????????????????????");
        }
        TargetOneManager targetOneManager = new TargetOneManager();
        targetOneManager.setTargetType(targetOneManagerAddVo.getTargetType());
        targetOneManager.setTargetName(targetOneManagerAddVo.getTargetName());
        targetOneManager.setAddPerson(targetOneManagerAddVo.getAddPerson());
        targetOneManager.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
        targetOneManager.setCreateTime(new Date());
        targetOneManager.setStatus(StatusEnum.IS_QY.getKey());
        targetOneManager.setUpdateTime(new Date());
        int result = this.targetOneManagerMapper.insert(targetOneManager);
        if(result!=0){
            return Result.ok("????????????");
        }

        return Result.error("????????????");
    }

    @Transactional
    @Override
    public Result updateTargetOneManager(TargetOneManagerEditVo targetOneManagerEditVo) {
        List<TargetOneManager> isExistTargetOne = targetOneManagerMapper.selectTargetOneManagerByNameList(targetOneManagerEditVo.getTargetName().replace(" ",""));
        if(isExistTargetOne != null && !isExistTargetOne.isEmpty()){
            return Result.error("????????????????????????????????????????????????");
        }
        TargetOneManager targetOneManager = this.targetOneManagerMapper.selectTargetOneManagerById(targetOneManagerEditVo.getId());
        if(targetOneManager!=null){
            TargetOneManagerEpoch epoch = new TargetOneManagerEpoch();
            epoch.setTargetId(targetOneManager.getId());
            epoch.setTargetName(targetOneManager.getTargetName());
            epoch.setCreateTime(targetOneManager.getUpdateTime());
            epoch.setAddPerson(targetOneManager.getAddPerson());

            targetOneManager.setTargetType(targetOneManagerEditVo.getTargetType());
            targetOneManager.setTargetName(targetOneManagerEditVo.getTargetName());
            targetOneManager.setAddPerson(targetOneManagerEditVo.getAddPerson());
            targetOneManager.setUpdateTime(new Date());
            if ((!targetOneManagerEpochService.fastForwardInUse(targetOneManager.getId())
                        || targetOneManagerEpochService.save(epoch)) &&
                this.targetOneManagerMapper.updateById(targetOneManager) == 1) {
                if (StringUtils.isNotBlank(targetOneManagerEditVo.getTargetName())) {
                    LambdaUpdateWrapper<TargetTwoManager> updateWrapper = new LambdaUpdateWrapper<TargetTwoManager>()
                            .eq(TargetTwoManager::getTargetOneManagerId, targetOneManagerEditVo.getId())
                            .set(TargetTwoManager::getIsTargetName, targetOneManagerEditVo.getTargetName());
                    this.targetTwoManagerMapper.update(null, updateWrapper); // ?????????????????????????????????????????????
                }
                return Result.ok("????????????");
            } else {
                throw new SofnException("??????????????????");
            }
        }
        return Result.error("????????????");
    }

    @Transactional
    @Override
    public Result addTargetTwoManager(TargetTwoManagerAddVo targetTwoManagerAddVo) {
        TargetOneManager targetOneManager = this.targetOneManagerMapper.selectTargetOneManagerByName(targetTwoManagerAddVo.getIsTargetName());
        List<TargetTwoManager> isTargetTwoManagers = this.targetTwoManagerMapper.selectTargetTwoManagerByName(targetTwoManagerAddVo.getTargetName().replace(" ",""));
        if(isTargetTwoManagers != null && !isTargetTwoManagers.isEmpty()){
            return Result.error("????????????????????????????????????????????????");
        }
        if(targetOneManager!=null){
            TargetTwoManager targetTwoManager = new TargetTwoManager();
            targetTwoManager.setTargetType(targetTwoManagerAddVo.getTargetType());
            targetTwoManager.setTargetName(targetTwoManagerAddVo.getTargetName());
            targetTwoManager.setAddPerson(targetTwoManagerAddVo.getAddPerson());
            targetTwoManager.setReferenceValue(targetTwoManagerAddVo.getReferenceValue());
            targetTwoManager.setScoreValue(targetTwoManagerAddVo.getScoreValue());
            targetTwoManager.setCreateTime(new Date());
            targetTwoManager.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            targetTwoManager.setStatus(StatusEnum.IS_QY.getKey());
            targetTwoManager.setTargetOneManagerId(targetOneManager.getId());
            targetTwoManager.setIsTargetName(targetOneManager.getTargetName());
            targetTwoManager.setUpdateTime(new Date());
            int result = this.targetTwoManagerMapper.insert(targetTwoManager);
            if(result != 0){
                return Result.ok("????????????");
            }
        } else {
            return Result.error(String.format("?????????????????????[%s]", targetTwoManagerAddVo.getIsTargetName()));
        }
        return Result.error("????????????");
    }

    @Transactional
    @Override
    public Result updateTargetTwoManager(TargetTwoManagerEditVo targetTwoManagerEditVo) {
        List<TargetTwoManager> isTargetTwoManagers = this.targetTwoManagerMapper.selectTargetTwoManagerByName(targetTwoManagerEditVo.getTargetName().replace(" ",""));
        if(isTargetTwoManagers != null && !isTargetTwoManagers.isEmpty()){
            return Result.error("????????????????????????????????????????????????");
        }
        TargetTwoManager targetTwoManager = this.targetTwoManagerMapper.selectTargetTwoManagerById(targetTwoManagerEditVo.getId());
        TargetOneManager targetOneManager = this.targetOneManagerMapper.selectTargetOneManagerByName(targetTwoManagerEditVo.getIsTargetName());

        if(targetTwoManager !=null){
            if(targetOneManager!=null){
                TargetTwoManagerEpoch epoch = new TargetTwoManagerEpoch();
                epoch.setTargetId(targetTwoManager.getId());
                epoch.setTargetName(targetTwoManager.getTargetName());
                epoch.setReferenceValue(targetTwoManager.getReferenceValue());
                epoch.setScoreValue(targetTwoManager.getScoreValue());
                epoch.setAddPerson(targetTwoManager.getAddPerson());
                epoch.setTargetOneManagerId(targetTwoManager.getTargetOneManagerId());
                epoch.setCreateTime(targetTwoManager.getUpdateTime());

                targetTwoManager.setTargetType(targetTwoManagerEditVo.getTargetType());
                targetTwoManager.setTargetName(targetTwoManagerEditVo.getTargetName());
                targetTwoManager.setAddPerson(targetTwoManagerEditVo.getAddPerson());
                targetTwoManager.setReferenceValue(targetTwoManagerEditVo.getReferenceValue());
                targetTwoManager.setScoreValue(targetTwoManagerEditVo.getScoreValue());
                targetTwoManager.setTargetOneManagerId(targetOneManager.getId());
                targetTwoManager.setIsTargetName(targetOneManager.getTargetName());
                targetTwoManager.setUpdateTime(new Date());
                if((!this.targetTwoManagerEpochService.fastForwardInUse(targetTwoManager.getId())
                                || this.targetTwoManagerEpochService.save(epoch)) &&
                   this.targetTwoManagerMapper.updateById(targetTwoManager) > 0){
                    return Result.ok("????????????");
                } else {
                    throw new SofnException("????????????");
                }

            }
        }
          return Result.error("????????????");
    }

    @Override
    public IPage<TargetManagerListVo> selectListData(String targetName, String startTime, String endTime, Integer pageNo, Integer pageSize,String targetType) {
        Page<TargetManagerListVo> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }

        return this.targetTwoManagerMapper.selectListData(page, targetName, startTime, endTime,targetType);
    }

    @Transactional
    @Override
    public Result startAndstopTarget(String id) {
        TargetTwoManager targetTwoManager = this.targetTwoManagerMapper.selectById(id);
        if(targetTwoManager !=null) {
            if (targetTwoManager.getStatus().equals("-1")) {
                targetTwoManager.setStatus(StatusEnum.IS_QY.getKey());
                UpdateWrapper<TargetTwoManager> wrapper = new UpdateWrapper();
                wrapper.eq("id", targetTwoManager.getId());
                int i = this.targetTwoManagerMapper.update(targetTwoManager, wrapper);
                if (i != 0) {
                    return Result.ok("????????????");
                }
            }
            if (targetTwoManager.getStatus().equals("1")) {
                targetTwoManager.setStatus(StatusEnum.IS_TY.getKey());
                UpdateWrapper<TargetTwoManager> wrapper = new UpdateWrapper<>();
                wrapper.eq("id", targetTwoManager.getId());
                int i = this.targetTwoManagerMapper.update(targetTwoManager, wrapper);
                if (i != 0) {
                    return Result.ok("????????????");
                }
            }
        }
            TargetOneManager targetOneManager = this.targetOneManagerMapper.selectById(id);
            if (targetOneManager != null) {
                if (targetOneManager.getStatus().equals("-1")) {
                    targetOneManager.setStatus(StatusEnum.IS_QY.getKey());
                    UpdateWrapper<TargetOneManager> wrapper = new UpdateWrapper<>();
                    wrapper.eq("id", targetOneManager.getId());
                    int i = this.targetOneManagerMapper.update(targetOneManager, wrapper);
                    if (i != 0) {
                        return Result.ok("????????????");
                    }
                }

                if (targetOneManager.getStatus().equals("1")) {
                    targetOneManager.setStatus(StatusEnum.IS_TY.getKey());
                    UpdateWrapper<TargetOneManager> wrapper = new UpdateWrapper<>();
                    wrapper.eq("id", targetOneManager.getId());
                    int i = this.targetOneManagerMapper.update(targetOneManager, wrapper);
                    if (i != 0) {
                        return Result.ok("????????????");
                    }
                }
            }

        return Result.error("????????????");
    }

    @Override
    public Result selectTargetTwoManagerById(String id) {
        TargetTwoManager targetTwoManager = this.targetTwoManagerMapper.selectTargetTwoManagerById(id);
        return Result.ok(targetTwoManager);
    }

    @Override
    public Result selectTargetOneManagerById(String id) {
        TargetOneManager targetOneManager = this.targetOneManagerMapper.selectTargetOneManagerById(id);
        return Result.ok(targetOneManager);
    }

    @Override
    public Result selectTargetOneManagerList() {
        List<TargetOneManager> targetOneManagerList = this.targetOneManagerMapper.selectTargetOneManagerList();
        return Result.ok(targetOneManagerList);
    }
}
