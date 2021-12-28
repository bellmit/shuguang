package com.sofn.agsjdm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agsjdm.enums.RiskEnum;
import com.sofn.agsjdm.mapper.MonitoringWarningMapper;
import com.sofn.agsjdm.model.MonitoringWarning;
import com.sofn.agsjdm.model.Threshold;
import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.service.MonitoringWarningService;
import com.sofn.agsjdm.service.ThresholdService;
import com.sofn.agsjdm.util.AgsjdmRedisUtils;
import com.sofn.agsjdm.util.ExportUtil;
import com.sofn.agsjdm.vo.exportBean.ExportMwBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:23
 */
@Service(value = "monitoringWarningServer")
public class MonitoringWarningServiceImpl implements MonitoringWarningService {


    @Resource
    private MonitoringWarningMapper mwMapper;
    @Resource
    private ThresholdService thresholdServer;
    @Resource
    private InformationManagementService informationManagementService;
    /**
     * 新增
     *
     * @param entity
     */
    @Override
    @Transactional
    public MonitoringWarning save(MonitoringWarning entity) {
        //防止重复提交
        AgsjdmRedisUtils.checkReSubmit("MonitoringWarningSave", 5L);
        Map map = new HashMap();
        map.put("wetlandId", entity.getWetlandId());
        map.put("chineseName", entity.getChineseName());
        map.put("testType", entity.getTestType());
        map.put("indexId", entity.getIndexId());
        MonitoringWarning monitoringWarning = mwMapper.listByParamsTwo(map);
        if (monitoringWarning != null) {
            throw new SofnException("新增当前检测类型的湿地区的物种的指标分类已存在,请检查");
        }
        entity.setId(IdUtil.getUUId());
        if ("1".equals(entity.getIndexId())) {
            entity.setIndexValue("污染面积");
        } else {
            entity.setIndexValue("种群数量");
        }
        entity.setOperatorTime(new Date());
        mwMapper.insert(entity);
        List<Threshold> thresholdList = entity.getThresholdList();
        if (!CollectionUtils.isEmpty(thresholdList)) {
//            this.validThreshold(thresholdList);
            for (Threshold threshold : thresholdList) {
//          条件转换
                numberToComparison(threshold);
                threshold.setWarningId(entity.getId());
                thresholdServer.save(threshold);
            }
        }
        return entity;
    }

    private void validThreshold(List<Threshold> thresholdList) {
        thresholdList = thresholdList.stream().sorted(Comparator.comparing(Threshold::getRiskLevel)).
                collect(Collectors.toList());
        Double max = null;
        Boolean equal = false;
        for (Threshold threshold : thresholdList) {
            if (("1".equals(threshold.getCase1()) || "3".equals(threshold.getCase1())) &&
                    ("1".equals(threshold.getCase2()) || "3".equals(threshold.getCase2()))) {
                throw new SofnException("条件1，2不能同时大于或者大于等于！");
            } else if (("2".equals(threshold.getCase1()) || "4".equals(threshold.getCase1())) &&
                    ("2".equals(threshold.getCase2()) || "4".equals(threshold.getCase2()))) {
                throw new SofnException("条件1，2不能同时小于或者小于等于！");
            }
//            Double maxTemp = 0d;
            Double minTemp = 0d;
            if (threshold.getCase1Value().equals(threshold.getCase2Value())) {
                throw new SofnException("风险等级(" + RiskEnum.getVal(threshold.getRiskLevel()) + ")值有误,两个条件数值不能相同");
            } else if (("1".equals(threshold.getCase1()) || "3".equals(threshold.getCase1()))) {
                minTemp = threshold.getCase1Value();
                if (threshold.getCase2Value() < minTemp) {
                    throw new SofnException(this.getSofnExceptionStr(threshold.getRiskLevel()));
                }
                if (Objects.nonNull(max)) {
                    if (minTemp < max || (equal && minTemp.equals(max))) {
                        throw new SofnException(this.getSofnExceptionStr(threshold.getRiskLevel()));
                    }
                }
                if ("4".equals(threshold.getCase2())) {
                    equal = true;
                }
                max = threshold.getCase2Value();
            } else if (("2".equals(threshold.getCase1()) || "4".equals(threshold.getCase1()))) {
                minTemp = threshold.getCase2Value();
                if (threshold.getCase1Value() < minTemp) {
                    throw new SofnException(this.getSofnExceptionStr(threshold.getRiskLevel()));
                }
                if (Objects.nonNull(max)) {
                    if (minTemp < max || (equal && minTemp.equals(max))) {
                        throw new SofnException(this.getSofnExceptionStr(threshold.getRiskLevel()));
                    }
                }
                if ("4".equals(threshold.getCase1())) {
                    equal = true;
                }
                max = threshold.getCase1Value();
            }
        }
    }

    private String getSofnExceptionStr(String riskLevel) {
        return "风险等级(" + RiskEnum.getVal(riskLevel) + ")值有误,请检查!";
    }


    @Override
    public void delete(String id) {
        mwMapper.deleteById(id);
        thresholdServer.deleteByWarningId(id);
    }

    @Override
    @Transactional
    public void update(MonitoringWarning entity) {
        Map map = new HashMap();
        map.put("wetlandId", entity.getWetlandId());
        map.put("testType", entity.getTestType());
        map.put("chineseName", entity.getChineseName());
        map.put("indexId", entity.getIndexId());
        map.put("id", entity.getId());
        MonitoringWarning monitoringWarning = mwMapper.listByParamsThree(map);
        if (monitoringWarning != null) {
            throw new SofnException("修改为当前保护点下的检测类型为此植物的指标分类的已存在,请检查");
        }
        entity.setOperatorTime(new Date());
        mwMapper.updateById(entity);
        thresholdServer.deleteByWarningId(entity.getId());
        List<Threshold> thresholdList = entity.getThresholdList();
        if (!CollectionUtils.isEmpty(thresholdList)) {
            for (Threshold threshold : thresholdList) {
                threshold.setWarningId(entity.getId());
//                                将123456 转为比较符
                numberToComparison(threshold);
                thresholdServer.save(threshold);
            }
        }
    }

    @Override
    public MonitoringWarning get(String id) {
        MonitoringWarning entity = mwMapper.selectById(id);
        Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
        entity.setWetlandName(wetlandNameMap.get(entity.getWetlandId()));
        List<Threshold> thresholds = thresholdServer.listByWarningId(id);
//        将比较符转为123456 给前端
        thresholds.forEach(threshold -> {
            comparisonToNumber(threshold);
        });
        entity.setThresholdList(thresholds);
        return entity;
    }

    @Override
    public PageUtils<MonitoringWarning> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
        List<MonitoringWarning> fmList = mwMapper.listByParams(params);
        for (MonitoringWarning f : fmList) {
            f.setWetlandName(wetlandNameMap.get(f.getWetlandId()));
        }
        return PageUtils.getPageUtils(new PageInfo(fmList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<MonitoringWarning> mwList = mwMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(mwList)) {
            List<ExportMwBean> exportList = new ArrayList<>(mwList.size());
            Map<String, String> wetlandNameMap =informationManagementService.getPointMap();
            mwList.forEach(o -> {
                ExportMwBean etb = new ExportMwBean();
                BeanUtils.copyProperties(o, etb);
                String testType = etb.getTestType();
                if ("0".equals(testType) || "1".equals(testType)) {
                    etb.setTestType("0".equals(testType) ? "生物生境监测" : "生物分布监测");
                }
                etb.setWetlandName(wetlandNameMap.get(o.getWetlandId()));
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ExportMwBean.class, exportList, response, "监测预警模型构建与管理信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

    /**
     * @param params
     */
    @Override
    public MonitoringWarning listByParams(Map<String, Object> params) {
        return mwMapper.list(params);
    }

    /**
     * 将数字转换为比较运算符
     *
     * @param threshold
     * @return
     */
    private Threshold numberToComparison(Threshold threshold) {
        switch (threshold.getCase1()) {
            case "1":
                threshold.setCase1(">");
                break;
            case "2":
                threshold.setCase1("<");
                break;
            case "3":
                threshold.setCase1(">=");
                break;
            case "4":
                threshold.setCase1("<=");
                break;
            case "5":
                threshold.setCase1("=");
                break;
            case "6":
                threshold.setCase1("!=");
                break;
            default:
                break;

        }
        switch (threshold.getCase2()) {
            case "1":
                threshold.setCase2(">");
                break;
            case "2":
                threshold.setCase2("<");
                break;
            case "3":
                threshold.setCase2(">=");
                break;
            case "4":
                threshold.setCase2("<=");
                break;
            case "5":
                threshold.setCase2("=");
                break;
            case "6":
                threshold.setCase2("!=");
                break;
            default:
                break;

        }
        return threshold;
    }

    /**
     * 将比较运算符转换为数字
     *
     * @param threshold
     * @return
     */
    private Threshold comparisonToNumber(Threshold threshold) {
        switch (threshold.getCase1()) {
            case ">":
                threshold.setCase1("1");
                break;
            case "<":
                threshold.setCase1("2");
                break;
            case ">=":
                threshold.setCase1("3");
                break;
            case "<=":
                threshold.setCase1("4");
                break;
            case "=":
                threshold.setCase1("5");
                break;
            case "!=":
                threshold.setCase1("6");
                break;
            default:
                break;

        }
        switch (threshold.getCase2()) {
            case ">":
                threshold.setCase2("1");
                break;
            case "<":
                threshold.setCase2("2");
                break;
            case ">=":
                threshold.setCase2("3");
                break;
            case "<=":
                threshold.setCase2("4");
                break;
            case "=":
                threshold.setCase2("5");
                break;
            case "!=":
                threshold.setCase2("6");
                break;
            default:
                break;

        }
        return threshold;
    }
}
