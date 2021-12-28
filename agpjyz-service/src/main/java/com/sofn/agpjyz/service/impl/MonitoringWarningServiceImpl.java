package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.MonitoringWarningMapper;
import com.sofn.agpjyz.model.MonitoringWarning;
import com.sofn.agpjyz.model.Threshold;
import com.sofn.agpjyz.service.MonitoringWarningService;
import com.sofn.agpjyz.service.ThresholdService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.util.ApiUtil;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.AgricultureSpeciesVo;
import com.sofn.agpjyz.vo.exportBean.ExportMwBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监测预警模型构建与管理模块服务类
 **/
@Service(value = "monitoringWarningServer")
public class MonitoringWarningServiceImpl implements MonitoringWarningService {


    @Autowired
    private MonitoringWarningMapper mwMapper;
    @Autowired
    private ThresholdService thresholdServer;
    @Autowired
    private JzbApi jzbApi;

    @Override
    public MonitoringWarning save(MonitoringWarning entity) {
        Map map =new HashMap();
        map.put("plantId",entity.getPlantId());
        map.put("testType",entity.getTestType());
        map.put("protectId",entity.getProtectId());
        map.put("indexId",entity.getIndexId());
        MonitoringWarning monitoringWarning = mwMapper.listByParamsTwo(map);
        if (monitoringWarning!=null){
            throw new SofnException("新增当前检测类型的保护点的植物的指标分类已存在,请检查");
        }
        entity.setId(IdUtil.getUUId());
        String plantId = entity.getPlantId();
        entity.setPlantValue(ApiUtil.getResultMap1(jzbApi.listForSpecies()).get(plantId));
        String protectId = entity.getProtectId();
        entity.setProtectValue((ApiUtil.getResultMap(jzbApi.listForProtectPoints("")).get(protectId)));
        if ("1".equals(entity.getIndexId())){
            entity.setIndexValue("种群数量");
        }else{
            entity.setIndexValue("受损面积");
        }
        mwMapper.insert(entity);
        List<Threshold> thresholdList = entity.getThresholdList();
        if (!CollectionUtils.isEmpty(thresholdList)) {
            for (Threshold threshold : thresholdList) {
                threshold.setWarningId(entity.getId());
//                数字转比较运算符
                numberToComparison(threshold);
                thresholdServer.save(threshold);
            }
        }
        return entity;
    }

    @Override
    public void delete(String id) {
        mwMapper.deleteById(id);
        thresholdServer.deleteByWarningId(id);
    }

    @Override
    public void update(MonitoringWarning entity) {
        Map map =new HashMap();
        map.put("plantId",entity.getPlantId());
        map.put("testType",entity.getTestType());
        map.put("protectId",entity.getProtectId());
        map.put("indexId",entity.getIndexId());
        map.put("id",entity.getId());
        MonitoringWarning monitoringWarning = mwMapper.listByParamsThree(map);
        if (monitoringWarning!=null){
            throw new SofnException("修改为当前保护点下的检测类型为此植物的指标分类的已存在,请检查");
        }
        String plantId = entity.getPlantId();
        entity.setPlantValue(ApiUtil.getResultMap1(jzbApi.listForSpecies()).get(plantId));
        String protectId = entity.getProtectId();
        entity.setProtectValue((ApiUtil.getResultMap(jzbApi.listForProtectPoints("")).get(protectId)));
        if ("1".equals(entity.getIndexId())){
            entity.setIndexValue("种群数量");
        }else{
            entity.setIndexValue("受损面积");
        }
        mwMapper.updateById(entity);
        thresholdServer.deleteByWarningId(entity.getId());
        List<Threshold> thresholdList = entity.getThresholdList();
        if (!CollectionUtils.isEmpty(thresholdList)) {
            for (Threshold threshold : thresholdList) {
                threshold.setWarningId(entity.getId());
//                数字转比较运算符
                numberToComparison(threshold);
                thresholdServer.save(threshold);
            }
        }
    }

    @Override
    public MonitoringWarning get(String id) {
        MonitoringWarning entity = mwMapper.selectById(id);
        List<Threshold> thresholds = thresholdServer.listByWarningId(id);
        thresholds.forEach(threshold -> {
            comparisonToNumber(threshold);
        });
        entity.setThresholdList(thresholds);
        return entity;
    }

    @Override
    public PageUtils<MonitoringWarning> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<MonitoringWarning> fmList = mwMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo(fmList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<MonitoringWarning> mwList = mwMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(mwList)) {
            List<ExportMwBean> exportList = new ArrayList<>(mwList.size());
            mwList.forEach(o -> {
                ExportMwBean etb = new ExportMwBean();
                BeanUtils.copyProperties(o, etb);
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
        return    mwMapper.list(params);
    }

    /**
     *将数字转换为比较运算符
     * @param threshold
     * @return
     */
    private Threshold numberToComparison(Threshold threshold){
        switch (threshold.getCase1()){
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
        switch (threshold.getCase2()){
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
        return  threshold;
    }

    /**
     *将比较运算符转换为数字
     * @param threshold
     * @return
     */
    private Threshold comparisonToNumber(Threshold threshold){
        switch (threshold.getCase1()){
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
        switch (threshold.getCase2()){
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
        return  threshold;
    }

}
