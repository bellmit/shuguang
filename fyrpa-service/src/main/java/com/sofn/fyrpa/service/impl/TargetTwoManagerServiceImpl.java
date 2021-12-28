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
            return Result.error("新增数据异常，新增指标失败！");
        }
        List<TargetOneManager> isExistTargetOne = targetOneManagerMapper.selectTargetOneManagerByNameList(targetOneManagerAddVo.getTargetName().replace(" ",""));
        if(isExistTargetOne != null && !isExistTargetOne.isEmpty()){
            return Result.error("已有相同指标，请重新输入指标名称");
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
            return Result.ok("新增成功");
        }

        return Result.error("新增失败");
    }

    @Transactional
    @Override
    public Result updateTargetOneManager(TargetOneManagerEditVo targetOneManagerEditVo) {
        List<TargetOneManager> isExistTargetOne = targetOneManagerMapper.selectTargetOneManagerByNameList(targetOneManagerEditVo.getTargetName().replace(" ",""));
        if(isExistTargetOne != null && !isExistTargetOne.isEmpty()){
            return Result.error("已有相同指标，请重新输入指标名称");
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
                    this.targetTwoManagerMapper.update(null, updateWrapper); // 修改二级指标表里的一级指标名称
                }
                return Result.ok("编辑成功");
            } else {
                throw new SofnException("修改操作失败");
            }
        }
        return Result.error("编辑失败");
    }

    @Transactional
    @Override
    public Result addTargetTwoManager(TargetTwoManagerAddVo targetTwoManagerAddVo) {
        TargetOneManager targetOneManager = this.targetOneManagerMapper.selectTargetOneManagerByName(targetTwoManagerAddVo.getIsTargetName());
        List<TargetTwoManager> isTargetTwoManagers = this.targetTwoManagerMapper.selectTargetTwoManagerByName(targetTwoManagerAddVo.getTargetName().replace(" ",""));
        if(isTargetTwoManagers != null && !isTargetTwoManagers.isEmpty()){
            return Result.error("已有相同指标，请重新输入指标名称");
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
                return Result.ok("添加成功");
            }
        } else {
            return Result.error(String.format("找不到一级指标[%s]", targetTwoManagerAddVo.getIsTargetName()));
        }
        return Result.error("添加失败");
    }

    @Transactional
    @Override
    public Result updateTargetTwoManager(TargetTwoManagerEditVo targetTwoManagerEditVo) {
        List<TargetTwoManager> isTargetTwoManagers = this.targetTwoManagerMapper.selectTargetTwoManagerByName(targetTwoManagerEditVo.getTargetName().replace(" ",""));
        if(isTargetTwoManagers != null && !isTargetTwoManagers.isEmpty()){
            return Result.error("已有相同指标，请重新输入指标名称");
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
                    return Result.ok("编辑成功");
                } else {
                    throw new SofnException("编辑失败");
                }

            }
        }
          return Result.error("编辑失败");
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
                    return Result.ok("更新成功");
                }
            }
            if (targetTwoManager.getStatus().equals("1")) {
                targetTwoManager.setStatus(StatusEnum.IS_TY.getKey());
                UpdateWrapper<TargetTwoManager> wrapper = new UpdateWrapper<>();
                wrapper.eq("id", targetTwoManager.getId());
                int i = this.targetTwoManagerMapper.update(targetTwoManager, wrapper);
                if (i != 0) {
                    return Result.ok("更新成功");
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
                        return Result.ok("更新成功");
                    }
                }

                if (targetOneManager.getStatus().equals("1")) {
                    targetOneManager.setStatus(StatusEnum.IS_TY.getKey());
                    UpdateWrapper<TargetOneManager> wrapper = new UpdateWrapper<>();
                    wrapper.eq("id", targetOneManager.getId());
                    int i = this.targetOneManagerMapper.update(targetOneManager, wrapper);
                    if (i != 0) {
                        return Result.ok("更新成功");
                    }
                }
            }

        return Result.error("更新失败");
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
