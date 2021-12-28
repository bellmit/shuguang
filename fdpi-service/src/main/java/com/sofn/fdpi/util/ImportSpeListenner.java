package com.sofn.fdpi.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.vo.ImportExcel;
import com.sofn.fdpi.vo.SpeExcel;
import com.sofn.fdpi.vo.SpeNameLevelVo;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-04 13:04
 */
public class ImportSpeListenner extends AnalysisEventListener<SpeExcel> {
    private SpeService speService;
    private Spe sp;
    private Map<String, String> map1;
    private List<String> codes;

    List<Spe> speList = new ArrayList<>();
    public ImportSpeListenner(SpeService speService, Spe sp,Map<String, String> map1,List<String> codes) {
        this.speService = speService;
        this.sp = sp;
        this.map1=map1;
        this.codes = codes;
    }
    @Override
    public void invoke(SpeExcel speExcel, AnalysisContext analysisContext) {
        String index=speExcel.getId();
        checkDataFormat(speExcel,index);
        String code = String.format("%04d",Integer.valueOf(speExcel.getSpeCode())); ;
        String speName = speExcel.getSpeName();
        if (map1.containsKey(speName.trim())){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }else {
            if ("0000".equals(code) && !"鲟鱼子酱".equals(speName)) {
//                throw new SofnException("编号0000只能是鲟鱼子酱");
            }
            map1.put(speExcel.getSpeName().trim(),"");
//            codes.add(code);
        }
        Spe sp=new Spe();
        BeanUtils.copyProperties(speExcel,sp);
        sp.preInsert();
        sp.setSpeCode(code);
        sp.setId(IdUtil.getUUId());
        sp.setCreateTime(new Date());
        sp.setSpeName(sp.getSpeName().trim());
        zhuanHuan(sp);
        speList.add(sp);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        speService.saveSpeBatch(speList);
    }
    void zhuanHuan(Spe sp) {
//        转为字典值
        SysRegionApi bean = SpringContextHolder.getBean(SysRegionApi.class);
        List<SysDict>  identify= bean.getDictListByType("identify").getData();
        List<SysDict>  pedigree= bean.getDictListByType("pedigree").getData();
        List<SysDict>  proLevel= bean.getDictListByType("proLevel").getData();
        List<SysDict>  cites= bean.getDictListByType("cites").getData();
        Map<String,String> mapforidentify= Maps.newHashMap();
        Map<String,String> mapforpedigree= Maps.newHashMap();
        Map<String,String> mapforproLevel= Maps.newHashMap();
        Map<String,String> mapforcites= Maps.newHashMap();
        if (!CollectionUtil.isEmpty(identify)){
            identify.forEach(o->{
                mapforidentify.put(o.getDictname(),o.getDictcode());
            });
        }
        if (!CollectionUtil.isEmpty(pedigree)){
            pedigree.forEach(o->{
                mapforpedigree.put(o.getDictname(),o.getDictcode());
            });
        }
        if (!CollectionUtil.isEmpty(proLevel)){
            proLevel.forEach(o->{
                mapforproLevel.put(o.getDictname(),o.getDictcode());
            });
        }
        if (!CollectionUtil.isEmpty(cites)){
            cites.forEach(o->{
                mapforcites.put(o.getDictname(),o.getDictcode());
            });
        }
        if (mapforidentify.containsKey(sp.getIdentify())){
            sp.setIdentify(mapforidentify.get(sp.getIdentify()));
        }
        if (mapforpedigree.containsKey(sp.getPedigree())){
            sp.setPedigree(mapforpedigree.get(sp.getPedigree()));
        }
        if (mapforproLevel.containsKey(sp.getProLevel())){
            sp.setProLevel(mapforproLevel.get(sp.getProLevel()));
        }
        if (mapforcites.containsKey(sp.getCites())){
            sp.setCites(mapforcites.get(sp.getCites()));
        }

    }
    private void checkDataFormat(SpeExcel speExcel, String index){
        if (speExcel.getId()==null){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getSpeName()==null||speExcel.getSpeName().length()>20){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getLatinName()!=null&&speExcel.getLatinName().length()>40){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getLocalName()!=null&&speExcel.getLocalName().length()>10){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getIntro()!=null&&speExcel.getIntro().length()>500){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getHabit()!=null&&speExcel.getHabit().length()>500){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getIdentify()==null){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getPedigree()==null){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getProLevel()==null){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }
        if (speExcel.getCites()==null){
            throw  new SofnException("第"+index+"行数据，导入失败，请检查后提交");
        }


    }
}
