package com.sofn.ahhrm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.ahhrm.Api.AhhdpApi;
import com.sofn.ahhrm.mapper.ThresholdMapper;
import com.sofn.ahhrm.model.Threshold;
import com.sofn.ahhrm.model.ThresholdSub;
import com.sofn.ahhrm.service.ThresholdService;
import com.sofn.ahhrm.service.ThresholdSubService;
import com.sofn.ahhrm.util.ApiUtil;
import com.sofn.ahhrm.util.RedisUserUtil;
import com.sofn.ahhrm.vo.DropDownVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:27
 */
@Service("thresholdService")
public class ThresholdServiceImpl implements ThresholdService {
    @Autowired
    private ThresholdMapper thresholdMapper;
    @Autowired
    private ThresholdSubService thresholdSubService;
    @Autowired
    private AhhdpApi ahhdpApi;
    @Transactional
    @Override
    public void save(Threshold threshold) {
        Map map=new HashMap<>();
        map.put("variety",threshold.getVariety());
        map.put("indexPar",threshold.getIndexPar());
//        判断当前品种名和指标参数的阀值是否存在
        Threshold repeat = thresholdMapper.existThreshold(map);
        if (repeat==null){
            threshold.setId(IdUtil.getUUId());
            thresholdMapper.insert(threshold);
            List<ThresholdSub> thresholdSubList = threshold.getThresholdSubList();
            if (!CollectionUtils.isEmpty(thresholdSubList)) {
                for (ThresholdSub t : thresholdSubList) {
                    t.setThresholdId(threshold.getId());
//                    将数值转为比较符
                    numberToComparison(t);
//                    将风险等级转为数值
                    gradeToNumber(t);
                    thresholdSubService.save(t);
                }
            }
        }else {
            throw  new SofnException("当前品种已设置阈值");
        }


    }

    @Override
    public void delete(String id) {
        thresholdMapper.deleteById(id);
        thresholdSubService.del(id);
    }
    @Transactional
    @Override
    public void update(Threshold threshold) {
        Map map=new HashMap<>();
        map.put("variety",threshold.getVariety());
        map.put("indexPar",threshold.getIndexPar());
        map.put("id",threshold.getId());
//        判断修改后的品种名和指标参数是否已存在
        Threshold th = thresholdMapper.repeatThreshold(map);
        if (th==null){
            thresholdSubService.del(threshold.getId());
            thresholdMapper.updateById(threshold);
            List<ThresholdSub> thresholdSubList = threshold.getThresholdSubList();
            if (!CollectionUtils.isEmpty(thresholdSubList)) {
                for (ThresholdSub t : thresholdSubList) {
                    t.setThresholdId(threshold.getId());
//                    将数值转为比较符
                    numberToComparison(t);
//                    将风险等级转为数值
                    gradeToNumber(t);
                    thresholdSubService.save(t);
                }
            }
        }else {
            throw  new SofnException("当前品种已设置阈值");
        }

    }

    @Override
    public Threshold getOne(String id) {
        Threshold threshold = thresholdMapper.selectById(id);
        List<ThresholdSub> bythresholdId = thresholdSubService.getBythresholdId(id);
        Map<String, String> varietyNameMap = ApiUtil.getResultMap(ahhdpApi.listForAllName());
        threshold.setVarietyName(varietyNameMap.get(threshold.getVariety()));
        bythresholdId.forEach(o->{
//            将数值转为中文的风险等级
            NumberTograde(o);
//            将比较符转为数值给前端
            comparisonToNumber(o);
        });
        threshold.setThresholdSubList(bythresholdId);
        return threshold;
    }

    @Override
    public PageUtils<Threshold> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, String> varietyNameMap = ApiUtil.getResultMap(ahhdpApi.listForAllName());
        List<Threshold> thresholdList = thresholdMapper.listByParams(params);
        for (Threshold th:
        thresholdList) {
            th.setVariety(varietyNameMap.get(th.getVariety()));
        }
        return PageUtils.getPageUtils(new PageInfo(thresholdList));
    }

    @Override
    public List<DropDownVo> listName() {
        Map<String, String> varietyNameMap = ApiUtil.getResultMap(ahhdpApi.listForAllName());
        List<DropDownVo> dropDownVos = thresholdMapper.listName();
        if(!CollectionUtils.isEmpty(dropDownVos)){
            for (DropDownVo d:
                    dropDownVos) {
                d.setName(varietyNameMap.get(d.getId()));
            }
        }

        return dropDownVos;
    }

    /**
     *将中文转为数字便于 查询时用风险等级排序
     * @return
     */

    private ThresholdSub gradeToNumber(ThresholdSub th){
        switch (th.getGrade()){
            case "无危险":
                th.setGrade("0");
                break;
            case "濒危":
                th.setGrade("1");
                break;
            case "濒临灭绝":
                th.setGrade("2");
                break;
            case "灭绝":
                th.setGrade("3");
                break;
            default:
                break;
        }
        return  th;
    }

    /**
     * 将数值代表的风险等级中文传回给前端
     * @param th
     * @return
     */
    private ThresholdSub NumberTograde(ThresholdSub th){
        switch (th.getGrade()){
            case "0":
                th.setGrade("无危险");
                break;
            case "1":
                th.setGrade("濒危");
                break;
            case "2":
                th.setGrade("濒临灭绝");
                break;
            case "3":
                th.setGrade("灭绝");
                break;
            default:
                break;
        }
        return  th;
    }
    /**
     *将数字转换为比较运算符
     * @param threshold
     * @return
     */
    private ThresholdSub numberToComparison(ThresholdSub threshold){
        switch (threshold.getCondition1()){
            case "1":
                threshold.setCondition1(">");
                break;
            case "2":
                threshold.setCondition1("<");
                break;
            case "3":
                threshold.setCondition1(">=");
                break;
            case "4":
                threshold.setCondition1("<=");
                break;
            case "5":
                threshold.setCondition1("=");
                break;
            case "6":
                threshold.setCondition1("!=");
                break;
            default:
                break;

        }
        switch (threshold.getCondition2()){
            case "1":
                threshold.setCondition2(">");
                break;
            case "2":
                threshold.setCondition2("<");
                break;
            case "3":
                threshold.setCondition2(">=");
                break;
            case "4":
                threshold.setCondition2("<=");
                break;
            case "5":
                threshold.setCondition2("=");
                break;
            case "6":
                threshold.setCondition2("!=");
                break;
            default:
                break;

        }
        return  threshold;
    }

    /**
     *将比较运算符转换为数字
     * @param threshold
     * @return
     */
    private ThresholdSub comparisonToNumber(ThresholdSub threshold){
        switch (threshold.getCondition1()){
            case ">":
                threshold.setCondition1("1");
                break;
            case "<":
                threshold.setCondition1("2");
                break;
            case ">=":
                threshold.setCondition1("3");
                break;
            case "<=":
                threshold.setCondition1("4");
                break;
            case "=":
                threshold.setCondition1("5");
                break;
            case "!=":
                threshold.setCondition1("6");
                break;
            default:
                break;

        }
        switch (threshold.getCondition2()){
            case ">":
                threshold.setCondition2("1");
                break;
            case "<":
                threshold.setCondition2("2");
                break;
            case ">=":
                threshold.setCondition2("3");
                break;
            case "<=":
                threshold.setCondition2("4");
                break;
            case "=":
                threshold.setCondition2("5");
                break;
            case "!=":
                threshold.setCondition2("6");
                break;
            default:
                break;

        }
        return  threshold;
    }
}
