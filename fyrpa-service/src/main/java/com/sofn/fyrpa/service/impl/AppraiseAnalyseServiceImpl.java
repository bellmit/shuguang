package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.enums.DeleteEnum;
import com.sofn.fyrpa.mapper.AppraiseAnalyseMapper;
import com.sofn.fyrpa.mapper.AquaticResourcesProtectionInfoMapper;
import com.sofn.fyrpa.mapper.TargetOneManagerMapper;
import com.sofn.fyrpa.mapper.TargetTwoManagerMapper;
import com.sofn.fyrpa.model.*;
import com.sofn.fyrpa.service.AppraiseAnalyseService;
import com.sofn.fyrpa.service.TargetOneManagerEpochService;
import com.sofn.fyrpa.service.TargetTwoManagerEpochService;
import com.sofn.fyrpa.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AppraiseAnalyseServiceImpl extends ServiceImpl<AppraiseAnalyseMapper,AppraiseAnalyse> implements AppraiseAnalyseService {
    @Autowired
    private AppraiseAnalyseMapper appraiseAnalyseMapper;

    @Autowired
    private AquaticResourcesProtectionInfoMapper aquaticResourcesProtectionInfoMapper;

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
    public Result add( List<AppraiseAnalyse> appraiseAnalyseList) {
        for (int i = 0; i <appraiseAnalyseList.size() ; i++) {
            appraiseAnalyseList.get(i).setCreateTime(new Date());
            appraiseAnalyseList.get(i).setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
        }

        boolean b= this.saveBatch(appraiseAnalyseList);
        if(b==true){
            Integer sum = this.appraiseAnalyseMapper.selectSum(appraiseAnalyseList.get(0).getResourcesProtectionId());
            QueryWrapper<AquaticResourcesProtectionInfo> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",appraiseAnalyseList.get(0).getResourcesProtectionId());
            wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            wrapper1.eq("is_flag","-1");
            AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);
            if(aquaticResourcesProtectionInfo!=null){
                aquaticResourcesProtectionInfo.setTotalScore(sum);
                aquaticResourcesProtectionInfo.setIsFlag("1");
                this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
            }
            return Result.ok("添加成功");
        }

        return null;

    }

    @Override
    public Result selectResourceAnalyseList(Integer pageNo, Integer pageSize, String name, String submitTime, Double startTotalScore, Double endTotalScore, String basinOrSeaArea) {
        Page<ResourceAppraiseAnalyseVoList>page=new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }

        IPage<ResourceAppraiseAnalyseVoList> resourceAppraiseAnalyseVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectResourceAnalyseList(page, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea);
        List<ResourceAppraiseAnalyseVoList> resourceAppraiseAnalyseVoList = resourceAppraiseAnalyseVoListIPage.getRecords();

            for (int i = 0; i <resourceAppraiseAnalyseVoList.size() ; i++) {
               Integer j =Integer.valueOf((resourceAppraiseAnalyseVoList.get(i).getSubmitTime()))-1;
                Integer lastTotalScore = this.aquaticResourcesProtectionInfoMapper.selectLastYearScore(j.toString() , resourceAppraiseAnalyseVoList.get(i).getName());
                if(lastTotalScore==null){
                    resourceAppraiseAnalyseVoList.get(i).setLastTotalScore(0);
                }else{
                    resourceAppraiseAnalyseVoList.get(i).setLastTotalScore(lastTotalScore);
                }

            }
            return Result.ok(resourceAppraiseAnalyseVoListIPage);

    }

    @Override
    public Result selectListByTimeSort(Integer pageNo, Integer pageSize, String name, String submitTime, Double startTotalScore, Double endTotalScore, String basinOrSeaArea, String sort) {
        Page<ResourceAppraiseAnalyseVoList>page=new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }
       if(!StringUtils.isEmpty(sort)){
        if(sort.equals("1")){
            IPage<ResourceAppraiseAnalyseVoList> resourceAppraiseAnalyseVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectListByTimeDesc(page, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea);
            return Result.ok(resourceAppraiseAnalyseVoListIPage);
        }

        if(sort.equals("-1")){
            IPage<ResourceAppraiseAnalyseVoList> resourceAppraiseAnalyseVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectListByTimeAsc(page, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea);
            return Result.ok(resourceAppraiseAnalyseVoListIPage);
        }
       }
        return null;
    }

    @Override
    public Result selectListByScoreSort(Integer pageNo, Integer pageSize, String name, String submitTime, Double startTotalScore, Double endTotalScore, String basinOrSeaArea, String sort) {
        Page<ResourceAppraiseAnalyseVoList>page=new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }
        if(!StringUtils.isEmpty(sort)) {
            if (sort.equals("1")) {
                IPage<ResourceAppraiseAnalyseVoList> resourceAppraiseAnalyseVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectListByScoreDesc(page, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea);
                return Result.ok(resourceAppraiseAnalyseVoListIPage);
            }

            if (sort.equals("-1")) {
                IPage<ResourceAppraiseAnalyseVoList> resourceAppraiseAnalyseVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectListByScoreAsc(page, name, submitTime, startTotalScore, endTotalScore, basinOrSeaArea);
                return Result.ok(resourceAppraiseAnalyseVoListIPage);
            }
        }
        return null;
    }

    @Override
    public Result selectAnalyseList() {
        List<AppraiseAnalyseVoList>appraiseAnalyseVoLists = new ArrayList<>();
        List<TargetOneManager> targetOneManagerList = this.targetOneManagerMapper.selectTargetOneManagerList();
        for (int i = 0; i <targetOneManagerList.size() ; i++) {
            List<TargetTwoManager> targetTwoManagerList1 = this.targetTwoManagerMapper.selectTargetTwoListByOneId(targetOneManagerList.get(i).getId());
            if(!CollectionUtils.isEmpty(targetTwoManagerList1)){
                AppraiseAnalyseVoList analyseVoList = new AppraiseAnalyseVoList();
                analyseVoList.setTargetOneName(targetOneManagerList.get(i).getTargetName());
                analyseVoList.setTargetOneId(targetOneManagerList.get(i).getId());

                List<TargetTwoManager> targetTwoManagerList = this.targetTwoManagerMapper.selectTargetTwoListByOneId(targetOneManagerList.get(i).getId());
                List<TargetTwoManagerVo> targetTwoManagerVoList = new ArrayList<>();
                for (int j = 0; j <targetTwoManagerList.size() ; j++) {
                    if(!CollectionUtils.isEmpty(targetTwoManagerList)) {
                        TargetTwoManagerVo targetTwoManagerVo = new TargetTwoManagerVo();
                        targetTwoManagerVo.setTargetTwoId(targetTwoManagerList.get(j).getId());
                        targetTwoManagerVo.setTargetTwoName(targetTwoManagerList.get(j).getTargetName());
                        targetTwoManagerVo.setReferenceValue(targetTwoManagerList.get(j).getReferenceValue());
                        targetTwoManagerVo.setScoreValue(targetTwoManagerList.get(j).getScoreValue());
                        targetTwoManagerVoList.add(targetTwoManagerVo);
                        analyseVoList.setTargetTwoManagerVoList(targetTwoManagerVoList);
                    }
                }
                appraiseAnalyseVoLists.add(analyseVoList);
            }
        }
        return Result.ok(appraiseAnalyseVoLists);
    }


    @Override
    public Result selectAppraiseAnalyseDetails(String resourceId) {
        List<AppraiseAnalyseVoList2> appraiseAnalyseVoList2List = new ArrayList<>();
        List<AppraiseAnalyseDetailsVo> appraiseAnalyseDetailsVoList = this.appraiseAnalyseMapper.selectAppraiseAnalyseDetails2(resourceId);

        for (AppraiseAnalyseDetailsVo appraiseAnalyseDetailsVo : appraiseAnalyseDetailsVoList) {
            if (appraiseAnalyseDetailsVo.getAppraiseTime().before(appraiseAnalyseDetailsVo.getTargetUpdateTime())) {
                TargetOneManagerEpoch targetOneManagerEpoch = targetOneManagerEpochService.getEpochByTime(appraiseAnalyseDetailsVo.getTargetOneId(), appraiseAnalyseDetailsVo.getAppraiseTime());
                if (Objects.isNull(targetOneManagerEpoch)) {
                    log.warn("一级指标[{}, {}]历史数据丢失", appraiseAnalyseDetailsVo.getTargetOneId(), appraiseAnalyseDetailsVo.getTargetOneName());
                } else {
                    appraiseAnalyseDetailsVo.setTargetOneName(targetOneManagerEpoch.getTargetName());
                    appraiseAnalyseDetailsVo.setTargetUpdateTime(targetOneManagerEpoch.getCreateTime());
                }
            }
            AppraiseAnalyseVoList2 appraiseAnalyseVoList2 = new AppraiseAnalyseVoList2();
            appraiseAnalyseVoList2.setTargetOneId(appraiseAnalyseDetailsVo.getTargetOneId());
            appraiseAnalyseVoList2.setTargetOneName(appraiseAnalyseDetailsVo.getTargetOneName());
            List<AppraiseAnalyseDetailsVo> appraiseAnalyseDetailsVoList2 = this.appraiseAnalyseMapper.selectAppraiseAnalyseDetails(appraiseAnalyseDetailsVo.getTargetOneId(), resourceId);
            appraiseAnalyseDetailsVoList2.parallelStream().filter(vo -> vo.getAppraiseTime().before(vo.getTargetUpdateTime()))
                    .forEach(vo -> {
                        TargetTwoManagerEpoch targetTwoManagerEpoch = targetTwoManagerEpochService.getEpochByTime(vo.getTargetTwoId(), vo.getAppraiseTime());
                        if (Objects.isNull(targetTwoManagerEpoch)) {
                            log.warn("二级指标[{}, {}]历史数据丢失", vo.getTargetTwoId(), vo.getTargetTwoName());
                        } else if (ObjectUtils.notEqual(targetTwoManagerEpoch.getTargetOneManagerId(), appraiseAnalyseDetailsVo.getTargetOneId())) {
                            log.error("历史数据指标 [{}, {}]-[{}, {}] 错误", appraiseAnalyseDetailsVo.getTargetOneId(),
                                    appraiseAnalyseDetailsVo.getTargetOneName(), targetTwoManagerEpoch.getTargetId(), targetTwoManagerEpoch.getTargetName());
                        } else {
                            vo.setTargetTwoName(targetTwoManagerEpoch.getTargetName());
                            vo.setScoreValue(targetTwoManagerEpoch.getScoreValue());
                            vo.setReferenceValue(targetTwoManagerEpoch.getReferenceValue());
                            vo.setTargetUpdateTime(targetTwoManagerEpoch.getCreateTime());
                        }
                    });
            appraiseAnalyseVoList2.setAppraiseAnalyseDetailsVoList(appraiseAnalyseDetailsVoList2);
            appraiseAnalyseVoList2List.add(appraiseAnalyseVoList2);
        }
        return Result.ok(appraiseAnalyseVoList2List);
    }
}
