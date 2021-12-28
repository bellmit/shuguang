package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.sofn.common.utils.BoolUtils;
import com.sofn.fdpi.mapper.SturgeonSubMapper;
import com.sofn.fdpi.model.SturgeonSub;
import com.sofn.fdpi.service.SturgeonSignboardDomesticService;
import com.sofn.fdpi.service.SturgeonSubService;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SturgeonSubForm;
import com.sofn.fdpi.vo.SturgeonSubVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("sturgeonSubService")
public class SturgeonSubServiceImpl implements SturgeonSubService {

    @Resource
    private SturgeonSubMapper sturgeonSubMapper;

    @Resource
    private SturgeonSignboardDomesticService ssdService;

    @Override
    public SturgeonSubVo save(SturgeonSubForm form, String applyType) {
        SturgeonSub entity = new SturgeonSub();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        Integer start = Integer.valueOf(entity.getStartNum());
        Integer end = Integer.valueOf(entity.getEndNum());
        if ("2".equals(applyType)) {
            entity.setStartNum(String.format("%08d", start));
            entity.setEndNum(String.format("%08d", end));
            ssdService.validSignboard(entity);
        }

        sturgeonSubMapper.insert(entity);
        return SturgeonSubVo.entity2Vo(entity);
    }

    @Override
    public List<SturgeonSubVo> listBySturgeonId(String sturgeonId) {
        QueryWrapper<SturgeonSub> wrapper = new QueryWrapper<>();
        wrapper.eq("STURGEON_ID", sturgeonId).eq("DEL_FLAG", BoolUtils.N).
                orderByAsc("CASE_NUM", "START_NUM");
        List<SturgeonSub> sturgeonSubs = sturgeonSubMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(sturgeonSubs)) {
            List<SturgeonSubVo> sturgeonSubVos = Lists.newArrayListWithCapacity(sturgeonSubs.size());
            for (SturgeonSub sturgeonSub : sturgeonSubs) {
                sturgeonSubVos.add(SturgeonSubVo.entity2Vo(sturgeonSub));
            }
            return sturgeonSubVos;
        }
        return null;
    }

    @Override
    public void deleteBySturgeonId(String sturgeonId) {
        UpdateWrapper<SturgeonSub> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("STURGEON_ID", sturgeonId).eq("DEL_FLAG", BoolUtils.N);
        SturgeonSub entity = new SturgeonSub();
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        sturgeonSubMapper.update(entity, updateWrapper);
    }

    @Override
    @Transactional
    public Integer update(String sturgeonId, String applyType, List<SturgeonSubForm> forms) {
        List<SturgeonSubVo> sturgeonSubVos = this.listBySturgeonId(sturgeonId);
        //原有物种id列表
        List<String> oldIds = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(sturgeonSubVos)) {
            oldIds.addAll(sturgeonSubVos.stream().map(t -> t.getId()).collect(Collectors.toList()));
        }
        //现有物种id列表
        List<String> newIds = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(forms)) {
            newIds.addAll(forms.stream().map(t -> t.getId()).collect(Collectors.toList()));
        }
        oldIds.removeAll(newIds);
        //删除已在页面删掉的物种
        this.deleteByIds(oldIds);

        //循环比较旧数据和新数据,有则更新删除状态为N, 没有则需新增
        Integer labelSum = SturgeonServiceImpl.mergeLabelS(SturgeonSubVo.forms2Vos(forms)).size();//forms.size();
        if (!CollectionUtils.isEmpty(forms)) {
            for (SturgeonSubForm form : forms) {
                String id = form.getId();
                //没有ID,直接新增数据
                if (StringUtils.isEmpty(id)) {
                    form.setSturgeonId(sturgeonId);
                    this.save(form, applyType);
                }
                //有ID,更新数据
                else {
                    SturgeonSub entity = new SturgeonSub();
                    BeanUtils.copyProperties(form, entity);
                    if ("2".equals(applyType)) {
                        entity.setStartNum(String.format("%08d", Integer.valueOf(entity.getStartNum())));
                        entity.setEndNum(String.format("%08d", Integer.valueOf(entity.getEndNum())));
                    }
                    entity.setSturgeonId(sturgeonId);
                    entity.preUpdate();
//                    sturgeonSignboardService.validRrepeat2(SturgeonSubVo.entity2Vo(entity),
//                            Integer.valueOf(entity.getStartNum()), Integer.valueOf(entity.getEndNum()));
                    sturgeonSubMapper.updateById(entity);
                }
                labelSum += Integer.parseInt(form.getEndNum()) -
                        Integer.parseInt(form.getStartNum()) + 1;
            }
        }
        return labelSum;
    }

    @Override
    public List<SelectVo> listSignboardCode(String applyId) {
        return sturgeonSubMapper.listSignboardCodeByApplyId(applyId);
    }

    @Override
    public int delBySturgeonIds(List<String> sturgeonIds) {
        if (!CollectionUtils.isEmpty(sturgeonIds)) {
            QueryWrapper<SturgeonSub> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("sturgeon_id", sturgeonIds);
            return sturgeonSubMapper.delete(queryWrapper);
        }
        return 0;
    }

    private void deleteByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            //逻辑删除物种
            SturgeonSub sturgeonSub = new SturgeonSub();
            sturgeonSub.setDelFlag(BoolUtils.Y);
            UpdateWrapper<SturgeonSub> updateWrapper = new UpdateWrapper();
            updateWrapper.in("id", ids);
            sturgeonSubMapper.update(sturgeonSub, updateWrapper);
        }
    }
}
