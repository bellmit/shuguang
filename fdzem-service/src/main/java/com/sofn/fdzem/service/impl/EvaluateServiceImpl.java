package com.sofn.fdzem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graphbuilder.struc.Stack;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.mapper.EvaluateMapper;
import com.sofn.fdzem.mapper.EvaluateVoMapper;
import com.sofn.fdzem.mapper.IndexMapper;
import com.sofn.fdzem.mapper.MonitoringStationTaskMapper;
import com.sofn.fdzem.model.Evaluate;
import com.sofn.fdzem.model.Index;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.EvaluateSerevice;
import com.sofn.fdzem.vo.EvaluateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/19 16:11
 * Description:
 * Version: V1.0
 */
@Service
public class EvaluateServiceImpl implements EvaluateSerevice {
    @Autowired
    EvaluateVoMapper evaluateVoMapper;
    @Autowired
    EvaluateMapper evaluateMapper;
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private MonitoringStationTaskMapper monitoringStationTaskMapper;
    @Override
    public List<Index> getIndices() {

        QueryWrapper<Index> queryWrapper = new QueryWrapper<>();
        //queryWrapper.select("parent_id", "id", "consult_value", "score");
        queryWrapper.isNull("parent_id");
        queryWrapper.eq("state", 1);
        List<Index> maps = indexMapper.selectList(queryWrapper);
        Double score = .0;
        for (Index map : maps) {
            QueryWrapper<Index> query = new QueryWrapper<>();
            // query.select("parent_id", "id", "consult_value", "score");
            query.isNotNull("parent_id");
            query.eq("parent_id", map.getId());
            query.eq("state", 1);
            List<Index> list = indexMapper.selectList(query);
            for (Index index : list) {
                score += index.getScore();
            }
            map.setIndexList(list);
        }
        if(!maps.isEmpty()){
            Index index = new Index();
            index.setIndexName("合计");
            //index.setScore(indexMapper.selectScore());
            index.setScore(score);
            maps.add(index);
        }
        return maps;
    }

    @Override
    public PageUtils<EvaluateVo> listPage(Integer pageNum, Integer pageSize, String organizationName, String submitYear, Double lowestScore, Double highestScore,
                                          String fileId, String isAcs) {
//        Page<MonitoringStationTask> page = new Page<>();
//        page.setSize(pageSize);
//        page.setCurrent(pageNum);
        //确定页码
        if (pageNum < 1) {
            pageNum = 1;
        }
        //PageHelper.startPage(pageNum, pageSize);
        pageNum = (pageNum - 1) * pageSize;
        List<EvaluateVo> EvaluateVos = evaluateVoMapper.selectEvaluateVoPage(organizationName, submitYear, lowestScore, highestScore, fileId, isAcs, pageNum, pageSize);

        //如果没有其他条件，就再查数据进行整合
        /*if (lowestScore == null && highestScore == null && EvaluateVos.size() < pageSize) {
            //PageHelper.startPage(pageNum, pageSize);
            List<EvaluateVo> EvaluateVolist = evaluateVoMapper.selectEvaluateVoList(organizationName,pageNum,pageSize);
            EvaluateVos = this.listUtil(EvaluateVos, EvaluateVolist);
        }*/
        EvaluateVos.forEach(ev -> {
            // 这里的数据全是评论分的，都是带年份来查的
            //ev.setArea(ev.getProvince() + "-" + ev.getProvinceCity() + "-" + ev.getCountyTown());
            ev.setState(1);
            if (ev.getScore() == null || Double.parseDouble(ev.getScore()) == .0) {
                ev.setScore("未评分");
//                ev.setSubmitYear(submitYear);
                ev.setState(0);
            }
        });
        //Page<EvaluateVo> page = (Page<EvaluateVo>) EvaluateVos;
        PageInfo<EvaluateVo> pageInfo = new PageInfo(EvaluateVos);
        return new PageUtils<EvaluateVo>(EvaluateVos, (int) pageInfo.getTotal(), pageSize, pageNum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(String id, String score, String date) {
        //查询是否已经评过分
        int flay = evaluateMapper.selectCount(new QueryWrapper<Evaluate>().eq("monitor_id", id).
                eq("submit_year", date));
        if (flay != 0)
            throw new SofnException("这个监测站已评过分了");
        Double scoreSum = .0;
        //解析前端传来的json
        JSONArray jsonArray = JSON.parseArray(score);
        Optional.ofNullable(jsonArray).orElseThrow(() -> new SofnException("请传入正确的json格式"));
        for (int i = 0; i < jsonArray.size(); i++) {
            String getsco = jsonArray.getJSONObject(i).get("getScore").toString();
            String sco = jsonArray.getJSONObject(i).get("score").toString();
            if (Double.parseDouble(sco) < Double.parseDouble(getsco))
                throw new SofnException("得分不能大于分值");
            if (i == jsonArray.size() - 1) {
                scoreSum += Double.parseDouble(getsco);
            }
        }
        Evaluate evaluate = new Evaluate();
        evaluate.setMonitorId(id);
        evaluate.setSubmitYear(date);
        evaluate.setScoreRecord(score);
        evaluate.setScore(scoreSum);
        evaluateMapper.insert(evaluate);
    }

    @Override
    public String getById(String id, String date) {

        return evaluateMapper.selectScore(id, date);
    }

    /**
     * 两个集合去重，优先删除listB
     *
     * @param listA
     * @param listB
     * @return
     */
    public static List<EvaluateVo> listUtil(List<EvaluateVo> listA, List<EvaluateVo> listB) {
        listA.forEach(a -> {
            Iterator<EvaluateVo> iterator = listB.iterator();
            while (iterator.hasNext()) {
                EvaluateVo next = iterator.next();
                if (next.getId().equals(a.getId())) {
                    iterator.remove();
                }
            }
        });
        listA.addAll(listB);
        return listA;
    }
}
