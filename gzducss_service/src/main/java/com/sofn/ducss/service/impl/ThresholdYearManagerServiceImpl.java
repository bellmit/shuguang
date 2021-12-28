package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.ThresholdEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.ThresholdYearManagerMapper;
import com.sofn.ducss.model.ThresholdValueManager;
import com.sofn.ducss.model.ThresholdYearManager;
import com.sofn.ducss.service.ThresholdValueManagerService;
import com.sofn.ducss.service.ThresholdYearManagerService;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.ThresholdYearManagerVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ThresholdYearManagerServiceImpl implements ThresholdYearManagerService {

    @Autowired
    private ThresholdYearManagerMapper thresholdYearManagerMapper;

    @Autowired
    private ThresholdValueManagerService thresholdValueManagerService;


    /**
     *
     * 检查年份是否重复
     * @param year  年份
     */
    private void checkYearIsRepeat(String year,String id){
        Integer count =  thresholdYearManagerMapper.getCountByYear(year,id);
        if(count > 0){
            throw new SofnException("年份重复");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ThresholdYearManagerVo thresholdYearManagerVo) {
        // 判断年度是否大于当前年度
        if (thresholdYearManagerVo == null) {
            throw new SofnException("年度信息为空");
        }
        checkYear(thresholdYearManagerVo);
        checkYearIsRepeat(thresholdYearManagerVo.getYear(), null);
        ThresholdYearManager thresholdYearManager = ThresholdYearManagerVo.getThresholdYearManager(thresholdYearManagerVo);
        if(!ThresholdEnum.YEAR_MANAGER_IS_ADD_Y.getCode().equals(thresholdYearManagerVo.getIsAdd()) &&
                !ThresholdEnum.YEAR_MANAGER_IS_ADD_N.getCode().equals(thresholdYearManagerVo.getIsAdd()) ){
            throw new SofnException("是否新增字段的值只能是1或者2");
        }
        if(ThresholdEnum.YEAR_MANAGER_IS_ADD_Y.getCode().equals(thresholdYearManagerVo.getIsAdd())){
            thresholdYearManager.setOddYear("");
        }else {
            insertValue(thresholdYearManager.getOddYear(), thresholdYearManager.getYear());
        }
        thresholdYearManager.setId(IdUtil.getUUId());
        thresholdYearManagerMapper.insert(thresholdYearManager);
        LogUtil.addLog(LogEnum.LOG_TYPE_THRESHOLD_ADD.getCode(), "新增-"+thresholdYearManager.getYear()+"年阈值");
    }


    private void insertValue(String oldYear, String newYear){

        thresholdValueManagerService.deleteByYear(newYear);
        List<ThresholdValueManager> info = thresholdValueManagerService.getInfo(oldYear);
        if(CollectionUtils.isEmpty(info)) {
            log.warn("数据年度{}对应的阈值不存在", oldYear);
            throw new SofnException("数据年度"+oldYear+"对应的阈值不存在");
        }
        List<ThresholdValueManager> collect = info.stream().map(item -> {
            item.setId(IdUtil.getUUId());
            item.setYear(newYear);
            return item;
        }).collect(Collectors.toList());
        thresholdValueManagerService.batchSave(collect);
    }

    /**
     * 检查日期
     * @param thresholdYearManagerVo  ThresholdYearManagerVo
     */
    private void checkYear(ThresholdYearManagerVo thresholdYearManagerVo) {
        try {
//            String currentYear = DateUtil.format(new Date(), "yyyy");
            String editYear = thresholdYearManagerVo.getYear();
            try{
                Integer.parseInt(thresholdYearManagerVo.getYear());
                if(StringUtils.isNotBlank(thresholdYearManagerVo.getOddYear())){
                    Integer.parseInt(thresholdYearManagerVo.getOddYear());
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new SofnException("年度只能是数字");
            }

//            if (Integer.parseInt(currentYear) < Integer.parseInt(editYear)) {
//                throw new SofnException("阈值年度不能大于当前年度");
//            }
            // 如果不是新增数据，则数据年度必填
            if (!ThresholdEnum.YEAR_MANAGER_IS_ADD_Y.getCode().equals(thresholdYearManagerVo.getIsAdd())) {
                if (StringUtils.isBlank(thresholdYearManagerVo.getYear())) {
                    throw new SofnException("数据年度必填");
                }
                if(editYear.equals(thresholdYearManagerVo.getOddYear())){
                    throw new SofnException("数据年度不能是当前要添加的年度");
                }

                // 往年年度只能小于当前年度
                String oddYear = thresholdYearManagerVo.getOddYear();
                if(StringUtils.isBlank(oddYear)){
                    throw new SofnException("如果不是新增数据那么数据年度不能为空");
                }
//                if (Integer.parseInt(currentYear) <= Integer.parseInt(oddYear)) {
//                    throw new SofnException("数据年度只能小于当前年度");
//                }
                // 查看往年年度数据是否存在
                Integer count = thresholdYearManagerMapper.getCountByYear(oddYear,null);
                if(count == null || count <= 0 ){
                    throw new SofnException("无当前数据年度数据");
                }
            }
        } catch (Exception e) {
            if( e instanceof SofnException){
                throw e;
            }
            throw new SofnException("传入的日期有问题");
        }
    }

    @Override
    public void update(ThresholdYearManagerVo thresholdYearManagerVo) {
        // 判断年度是否大于当前年度
        if (thresholdYearManagerVo == null) {
            throw new SofnException("年度信息为空");
        }

        // 之前是2020
        // 解决 改成2019 并且数据年度为2020  之前的没删 所以能够查询到
        ThresholdYearManager thresholdYearManager1 = thresholdYearManagerMapper.selectById(thresholdYearManagerVo.getId());
        if(thresholdYearManager1 == null){
            throw new SofnException("当前编辑信息不存在");
        }
        checkYear(thresholdYearManagerVo);
        checkYearIsRepeat(thresholdYearManagerVo.getYear(), thresholdYearManagerVo.getId());
        if(!ThresholdEnum.YEAR_MANAGER_IS_ADD_Y.getCode().equals(thresholdYearManagerVo.getIsAdd())){
            if(thresholdYearManager1.getYear().equals(thresholdYearManagerVo.getOddYear())){
                throw new SofnException("阈值年度管理：【当前年度】为：" + thresholdYearManager1.getYear() + "， 数据年度不能为当前年度");
            }
        }

        if(!ThresholdEnum.YEAR_MANAGER_IS_ADD_Y.getCode().equals(thresholdYearManagerVo.getIsAdd())){
            insertValue(thresholdYearManagerVo.getOddYear(),thresholdYearManagerVo.getYear() );
        }else {
            thresholdValueManagerService.deleteByYear(thresholdYearManagerVo.getYear());
        }

        ThresholdYearManager thresholdYearManager = ThresholdYearManagerVo.getThresholdYearManager(thresholdYearManagerVo);


        thresholdYearManagerMapper.updateById(thresholdYearManager);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        ThresholdYearManager thresholdYearManager = thresholdYearManagerMapper.selectById(id);
        if(thresholdYearManager == null){
            throw new SofnException("没有查询到记录");
        }
        thresholdYearManagerMapper.deleteById(id);
        thresholdValueManagerService.deleteByYear(thresholdYearManager.getYear());
        LogUtil.addLog(LogEnum.LOG_TYPE_THRESHOLD_DELETE.getCode(), "删除-"+thresholdYearManager.getYear()+"年阈值");
    }

    @Override
    public PageUtils<ThresholdYearManagerVo> getList(String year, Integer pageNo, Integer pageSize) {
        if(pageSize == null || pageSize <=0){
            throw new SofnException("请输入分页信息");
        }
        PageHelper.offsetPage(pageNo,pageSize);
        QueryWrapper<ThresholdYearManager> thresholdYearManagerQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isBlank(year)){
            thresholdYearManagerQueryWrapper.eq("year", year);
        }
        thresholdYearManagerQueryWrapper.orderByDesc("year");

        List<ThresholdYearManager> yearList = thresholdYearManagerMapper.selectList(thresholdYearManagerQueryWrapper);
        PageInfo<ThresholdYearManager> pageInfo = new PageInfo<>(yearList);

        PageInfo<ThresholdYearManagerVo> pageInfo2 = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,pageInfo2);
        List<ThresholdYearManager> list = pageInfo.getList();
        if(!CollectionUtils.isEmpty(list)){
            pageInfo2.setList(list.stream().map(ThresholdYearManagerVo::getThresholdYearManagerVo).collect(Collectors.toList()));
        }
        return PageUtils.getPageUtils(pageInfo2);
    }

    @Override
    public List<String> getHaveYear() {
        return thresholdYearManagerMapper.getHaveYear();
    }


}
