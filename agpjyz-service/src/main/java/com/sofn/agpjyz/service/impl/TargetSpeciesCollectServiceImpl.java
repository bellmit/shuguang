package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.TargetSpeciesCollectMapper;
import com.sofn.agpjyz.model.MonitoringWarning;
import com.sofn.agpjyz.model.TargetSpecies;
import com.sofn.agpjyz.model.ThreatFactor;
import com.sofn.agpjyz.model.Threshold;
import com.sofn.agpjyz.service.MonitoringWarningService;
import com.sofn.agpjyz.service.TargetSpeciesCollectService;
import com.sofn.agpjyz.service.ThreatFactorCollectService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.*;
import com.sofn.agpjyz.vo.exportBean.ExportTsBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.xiaoleilu.hutool.date.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 目标物种基础信息收集模块服务类
 *
 * @Author yumao
 * @Date 2020/2/25 17:20
 **/
@Service(value = "targetSpeciesCollectService")
public class TargetSpeciesCollectServiceImpl implements TargetSpeciesCollectService {

    @Autowired
    private TargetSpeciesCollectMapper tscMapper;
    @Autowired
    private MonitoringWarningService mwService;
    @Autowired
    ThreatFactorCollectService threatFactorCollectService;
    @Autowired
    private JzbApi jzbApi;

    /**
     * 新增
     */
    @Override
    public TargetSpeciesVo save(TargetSpeciesForm form) {
        TargetSpecies entity = new TargetSpecies();
        BeanUtils.copyProperties(form, entity);
        entity.setId(IdUtil.getUUId());
        entity.setInputerTime(new DateTime());
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        this.validRepeat(
                form.getProtectId(), form.getSpecId(), DateUtils.format(entity.getInputerTime(), "yyyy"), "");
        entity.setInputer(inputer);
        tscMapper.insert(entity);
        TargetSpeciesVo vo = new TargetSpeciesVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    //验证重复
    private void validRepeat(String protectId, String specId, String year, String id) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("protectId", protectId);
        params.put("specId", specId);
        params.put("year", year);
        if (StringUtils.hasText(id)) {
            params.put("id", id);
        }
        List<TargetSpecies> list = tscMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(list)) {
            throw new SofnException("该年该保护点已存在相同数据,不可以新增/修改");
        }
    }
    /**
     * 删除
     */
    @Override
    public void delete(String id) {
        tscMapper.deleteById(id);
    }

    @Override
    public void update(TargetSpeciesForm form) {
        String id = form.getId();
        TargetSpecies entity = tscMapper.selectById(id);
        if (Objects.isNull(entity)) {
            throw new SofnException("待修改数据不存在");
        }
        entity.setInputerTime(new DateTime());
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputer(inputer);
        this.validRepeat(
                form.getProtectId(), form.getSpecId(), DateUtils.format(entity.getInputerTime(), "yyyy"), id);
        BeanUtils.copyProperties(form, entity);
        tscMapper.updateById(entity);
        if(entity.getAmount()==null){
            tscMapper.updateAmount(entity.getId());
        }

    }

    /**
     * 详情
     */
    @Override
    public TargetSpeciesVo get(String id) {
        TargetSpeciesVo vo = new TargetSpeciesVo();
        TargetSpecies ts = tscMapper.selectById(id);
        if (!Objects.isNull(ts)) {
            BeanUtils.copyProperties(ts, vo);
            return vo;
        }
        return null;
    }

    /**
     * 分页查询
     */
    @Override
    public PageUtils<TargetSpeciesVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<TargetSpecies> tsList = tscMapper.listByParams(params);
        PageInfo<TargetSpecies> tsPageInfo = new PageInfo<>(tsList);
        if(!CollectionUtils.isEmpty(tsList)){
            List<TargetSpeciesVo> tsvList = new ArrayList<>(tsList.size());
            for(TargetSpecies ts : tsList){
                tsvList.add(TargetSpeciesVo.entity2Vo(ts));
            }
            PageInfo<TargetSpeciesVo> tsvPageInfo = new PageInfo<>(tsvList);
            tsvPageInfo.setPageSize(pageSize);
            tsvPageInfo.setTotal(tsPageInfo.getTotal());
            tsvPageInfo.setPageNum(tsPageInfo.getPageNum());
            return PageUtils.getPageUtils(tsvPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(tsList));
    }

    /**
     * 导出
     */
    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<TargetSpecies> tsList = tscMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<ExportTsBean> exportList = new ArrayList<>(tsList.size());
            tsList.forEach(o -> {
                ExportTsBean etb = new ExportTsBean();
                BeanUtils.copyProperties(o, etb);
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ExportTsBean.class, exportList, response, "目标物种基础信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

    @Override
    public List<TargetSpeciesVo> listByName(String Name) {
        List<TargetSpecies> targetSpecies = tscMapper.listByName(Name);
        List<TargetSpeciesVo> tsvList = new ArrayList<>(targetSpecies.size());
        if (!CollectionUtils.isEmpty(targetSpecies)) {
            for (TargetSpecies ts : targetSpecies) {
                tsvList.add(TargetSpeciesVo.entity2Vo(ts));
            }
        }
        return tsvList;
    }

    /**
     * 获取年份
     *
     * @return
     */
    @Override
    public List<YearVo> getYear() {
        List<YearVo> year = tscMapper.getYear();
        return year;
    }

    /**
     *
     */
    @Override
    public AnalysisVo getResult(Map map) {


        AnalysisVo m=new AnalysisVo();
        Double i1=0.0;
        Map maps=new HashMap(4);
        maps.put("protectId",map.get("protectId"));
        maps.put("plantId",map.get("specId"));
        maps.put("indexId",map.get("indexId"));
        maps.put("testType",map.get("testType"));
        TargetSpecies byParams = tscMapper.getByParams(map);
        if (byParams != null) {
            String specId =(String) map.get("specId");
            Result<AgricultureSpeciesVo> agricultureSpeciesVoResult = jzbApi.get(specId);
//        1大于2小于3大于等于4小于等于5等于6不等于
            if("1".equals(map.get("indexId"))){
//                Double amount = byParams.getAmount();
//                i1= new Double(amount).intValue();
                if (byParams.getAmount()==null){
                    throw  new SofnException("基础数据，数量为空");
                }
                i1=byParams.getAmount();
                m.setIndexId("1");
            }
            if("2".equals(map.get("indexId"))){
                ThreatFactor p = threatFactorCollectService.getNum((String) map.get("protectId"));
                if (p!=null){
                    if (p.getExcavation()==null){
                        throw  new SofnException("威胁因素，采挖受损面积为空");
                    }
                    i1=p.getExcavation();
//                    i1= new Double(p.getExcavation()).intValue();
                    m.setIndexId("2");
                }else {
                    throw  new SofnException("威胁因素基础信息未录入信息");
                }

            }
            m.setSpecId(specId);
            m.setSpecValue(byParams.getSpecValue());
            m.setProtectId((String)map.get("protectId"));
            if (agricultureSpeciesVoResult.getData()!=null){
                m.setAddrList(agricultureSpeciesVoResult.getData().getAddrList());
            }
            MonitoringWarning monitoringWarning = mwService.listByParams(maps);
            if (monitoringWarning!=null){
                List<Threshold> thresholdList = monitoringWarning.getThresholdList();
                for (Threshold th:
                        thresholdList) {
                    String case1 = th.getCase1();
                    Double case1Value = th.getCase1Value();
                    String case2 = th.getCase2();
                    Double case2Value = th.getCase2Value();
                    boolean caseOne=false;
                    boolean caseTwo=false;
                    Boolean[] bs1=new Boolean[6];
                    bs1[0]="1".equals(case1)&&i1>case1Value;
                    bs1[1]="2".equals(case1)&&i1<case1Value;
                    bs1[2]="3".equals(case1)&&(i1>case1Value||i1.equals(case1Value));
                    bs1[3]="4".equals(case1)&&(i1<case1Value||i1.equals(case1Value));
                    bs1[4]="5".equals(case1)&&(i1.equals(case1Value));
                    bs1[5]="6".equals(case1)&&(!i1.equals(case1Value));
                    for (int j = 0; j < bs1.length; j++) {
                        if (bs1[j]){
                            caseOne=true;
                            break;
                        }
                    }
                    if(!case2.equals("")&&case2!=null){
                        Boolean[] bs2=new Boolean[6];
                        bs2[0]="1".equals(case2)&&i1>case2Value;
                        bs2[1]="2".equals(case2)&&i1<case2Value;
                        bs2[2]="3".equals(case2)&&(i1>case2Value||i1.equals(case2Value));
                        bs2[3]="4".equals(case2)&&(i1<case2Value||i1.equals(case2Value));
                        bs2[4]="5".equals(case2)&&(i1.equals(case2Value));
                        bs2[5]="6".equals(case2)&&(!i1.equals(case2Value));
                        for (int k = 0; k < bs2.length; k++) {
                            if (bs2[k]){
                                caseTwo=true;
                                break;
                            }
                        }
                        if (caseOne&&caseTwo){
                            m.setRiskLevel(th.getRiskLevel());
                            m.setColorMark(th.getColorMark());
                            m.setMessage("0");
                            return m;
                        }
                    }else {
                        if (caseOne){
                            m.setRiskLevel(th.getRiskLevel());
                            m.setColorMark(th.getColorMark());
                            m.setMessage("0");
                            return  m;
                        }

                    }
                }
                m.setMessage("1");
                return m;
            }else {
                throw  new SofnException("当前物种未设置单前查询条件的阀值");
            }

        }else {
            throw  new SofnException("该保护点当前年份的目标物种基础信息未录入");
        }

    }

}
