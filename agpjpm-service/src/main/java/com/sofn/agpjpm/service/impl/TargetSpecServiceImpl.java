package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.sofn.agpjpm.constants.Constants;
import com.sofn.agpjpm.mapper.TargetSpecMapper;
import com.sofn.agpjpm.model.TargetSpec;
import com.sofn.agpjpm.service.PictureAttService;
import com.sofn.agpjpm.service.TargetSpecService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.sysapi.SysFileApi;
import com.sofn.agpjpm.util.ApiUtil;
import com.sofn.agpjpm.util.FileUtil;
import com.sofn.agpjpm.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

import java.util.stream.Collectors;


/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 15:48
 */
@Service("targetSpecService")
public class TargetSpecServiceImpl implements TargetSpecService {
    @Autowired
    private TargetSpecMapper targetSpecMapper;
    @Autowired
    private PictureAttService pictureAttrService;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private JzbApi jzbApi;
    @Autowired
    private SqlSessionFactory sessionFactory;
    @Autowired
    private SysFileApi sysFileApi;



    private String handleFileInfo(List<PictureAttForm> PictureAttForms, SpeciesSurveyVo speciesSurveyVo,SpeciesMonitorVo speciesMonitorVo, String fileUse) {
        if (!CollectionUtils.isEmpty(PictureAttForms)) {
            List<PictureAttVo> PictureAttVos = new ArrayList<>(PictureAttForms.size());
            StringBuilder ids = new StringBuilder();
            for (PictureAttForm paForm : PictureAttForms) {
                PictureAttVo PictureAttVo =new PictureAttVo();
//               ???????????? ????????????_????????????
                if (speciesSurveyVo!=null){
                    paForm.setSourceId(speciesSurveyVo.getId());
                    paForm.setFileUse(fileUse);
                   PictureAttVo = pictureAttrService.save(paForm, Constants.FILE_SOURCE_SURVEY_SPEC);
                    ids.append("," + PictureAttVo.getFileId());
                }
//            ???????????? ????????????????????????(???????????????_????????????
//                if (speciesMonitorVo!=null){
//                    paForm.setSourceId(speciesMonitorVo.getMonitorId());
//                    paForm.setFileUse(fileUse);
//                    PictureAttVo = pictureAttrService.save(paForm, Constants.FILE_SOURCE_MONITOR_SPEC);
//                    ids.append("," + PictureAttVo.getFileId());
//                }

                PictureAttVos.add(PictureAttVo);
            }
//              ???????????? ????????????_???????????? ????????????plant???community???habitat
            if (speciesSurveyVo!=null) {
                if ("plant".equals(fileUse)) {
                    speciesSurveyVo.setPlant(PictureAttVos);
                } else if ("community".equals(fileUse)) {
                    speciesSurveyVo.setCommunity(PictureAttVos);
                } else if ("habitat".equals(fileUse)) {
                    speciesSurveyVo.setHabitat(PictureAttVos);
                }
            }
//             ???????????? ????????????????????????(???????????????_????????????
            if(speciesMonitorVo!=null){
                if ("plant".equals(fileUse)) {
//                    speciesMonitorVo.setPlant(PictureAttVos);
                } else if ("community".equals(fileUse)) {
//                    speciesMonitorVo.setCommunity(PictureAttVos);
                }
            }


            return ids.toString().substring(1);
        }
        return "";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SpeciesSurveyForm speciesSurveyForm, SpeciesMonitorForm speciesMonitorForm, String sourceId) {

        TargetSpec tar= new TargetSpec();
        StringBuilder ids = new StringBuilder();
        tar.setId(IdUtil.getUUId());

        tar.preInsert();
        //        ???save?????????????????????
        if (speciesMonitorForm==null){
            BeanUtils.copyProperties(speciesSurveyForm,tar);
            tar.setSourceId(sourceId);
            tar.setId(IdUtil.getUUId());

            Result<List<DropDownWithLatinVo>>dropList =Result.ok(getDropList()) ;
            Map<String, DropDownWithLatinVo> map = ApiUtil.getResultObjMap(dropList);
            DropDownWithLatinVo dropDownWithLatinVo = map.get(tar.getSpecId());
            if (dropDownWithLatinVo!=null){
                tar.setSpecName(dropDownWithLatinVo.getName());
                tar.setIsSystem("N");
            }else {
                if (tar.getSpecId().length()>50){
                    throw new SofnException("????????????????????????????????????50");
                }
                tar.setSpecName(tar.getSpecId());
                tar.setSpecId(IdUtil.getUUId());
                tar.setIsSystem("Y");
            }

            tar.setSpecSource(Constants.SPEC_SOURCE_SURVEY);
            targetSpecMapper.insert(tar);
            SpeciesSurveyVo specimenVo = SpeciesSurveyVo.entity2Vo(tar);
            String idsPlant = this.handleFileInfo(speciesSurveyForm.getPlant(), specimenVo,null ,"plant");
            if (StringUtils.hasText(idsPlant)) {
                ids.append("," + idsPlant);
            }
            String idsCommunity = this.handleFileInfo(speciesSurveyForm.getCommunity(), specimenVo, null,"community");
            if (StringUtils.hasText(idsCommunity)) {
                ids.append("," + idsCommunity);
            }
            String idsHabitat = this.handleFileInfo(speciesSurveyForm.getHabitat(), specimenVo,null, "habitat");
            if (StringUtils.hasText(idsHabitat)) {
                ids.append("," + idsHabitat);
            }
            if (StringUtils.hasText(ids.toString())) {
                fileUtil.activationFile(ids.toString().substring(1));
            }

        }


    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param
     * @param specSource
     * @param cls
     * @return
     */
    @Override
    public <T> List<T> getBySourceId(String sourceId, String specSource,Class<T> cls) {
        List<T> dataList = Lists.newArrayList();
        if (specSource.equals(Constants.SPEC_SOURCE_SURVEY)) {
            QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("source_id", sourceId).eq("del_flag","N");
            List<TargetSpec> targetSpecs = targetSpecMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(targetSpecs)) {
                for (TargetSpec tar :
                        targetSpecs) {
                    dataList.add((T) getById(tar.getId(), Constants.SPEC_SOURCE_SURVEY));
                }
            }
            return dataList;
        }else{
            QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("source_id", sourceId);
            List<TargetSpec> targetSpecs = targetSpecMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(targetSpecs)) {
                for (TargetSpec tar :
                        targetSpecs) {
                    dataList.add((T) getById(tar.getId(), Constants.SPEC_SOURCE_MONITOR));
                }
            }
            return dataList;
        }

    }

//    @Override
//    public List<SpeciesSurveyVo> getBySurveyId(String sourceId) {
//        List<SpeciesSurveyVo> sp=new ArrayList<>();
//        QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("source_id", sourceId);
//        List<TargetSpec> targetSpecs= targetSpecMapper.selectList(queryWrapper);
//        if (!CollectionUtils.isEmpty(targetSpecs)) {
//            for (TargetSpec tar :
//                    targetSpecs) {
//                SpeciesSurveyVo byId =(SpeciesSurveyVo) getById(tar.getId(),Constants.SPEC_SOURCE_SURVEY);
//                sp.add(byId);
//            }
//        }
//        return sp;
//    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBySurveyId(String sourceId) {
        QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId);
        List<TargetSpec> targetSpecs= targetSpecMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(targetSpecs)) {
            for (TargetSpec tar :
                    targetSpecs) {
                delete(tar.getId());
            }
        }
    }

    /**
     * @param id ????????????id
     */
    @Override
    public void delete(String id) {
//        ????????????????????????
        targetSpecMapper.updateForDelete(id);
        pictureAttrService.deleteBySourceId(id);
    }

    /**
     * @param speciesSurveyForm
     * @param speciesMonitorForm
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SpeciesSurveyForm speciesSurveyForm, SpeciesMonitorForm speciesMonitorForm,String sourceId) {
//        ???????????????????????????_????????????
        if (speciesMonitorForm == null) {
            String id = speciesSurveyForm.getId();
            QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
            TargetSpec speciesS = targetSpecMapper.selectOne(queryWrapper);
//            ??????id??????????????????
            if (speciesSurveyForm.getId()==null) {
                save(speciesSurveyForm, null, sourceId);
            } else {
                SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
                BeanUtils.copyProperties(speciesSurveyForm, speciesS);
                //          ??????????????????
                speciesS.setSpecSource(Constants.SPEC_SOURCE_SURVEY);
                speciesS.preUpdate();
                speciesS.setCreateTime(new Date());
                Result<List<DropDownWithLatinVo>>dropList =Result.ok(getDropList()) ;
                Map<String, DropDownWithLatinVo> map = ApiUtil.getResultObjMap(dropList);
                DropDownWithLatinVo dropDownWithLatinVo = map.get(speciesSurveyForm.getSpecId());
                if (dropDownWithLatinVo!=null){
                    speciesS.setSpecName(dropDownWithLatinVo.getName());
                    speciesS.setSpecId(speciesSurveyForm.getSpecId());
                    speciesS.setIsSystem("N");
                }else {
                    if (speciesSurveyForm.getSpecId().length()>50){
                        throw new SofnException("????????????????????????????????????50");
                    }
                    speciesS.setSpecName(speciesSurveyForm.getSpecId());
                    speciesS.setSpecId(IdUtil.getUUId());
                    speciesS.setIsSystem("Y");
                }
//                speciesS.setSpecName(speciesS.getSpecId());
                speciesS.setSourceId(sourceId);
                targetSpecMapper.updateById(speciesS);
                pictureAttrService.updateSourceId(id, speciesSurveyForm.getPlant(), Constants.FILE_SOURCE_SURVEY_SPEC, "plant");
                pictureAttrService.updateSourceId(id, speciesSurveyForm.getCommunity(), Constants.FILE_SOURCE_SURVEY_SPEC, "community");
                pictureAttrService.updateSourceId(id, speciesSurveyForm.getHabitat(), Constants.FILE_SOURCE_SURVEY_SPEC, "habitat");
                session.commit();

            }

        }

    }
//    /**
//     * @param id ??????id
//     * @return
//     */
//    @Override
//    public SpeciesSurveyVo getByIdforSurvey(String id) {
//        SpeciesSurveyVo sp=new SpeciesSurveyVo();
//        QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", id).eq("del_flag","N");
//        TargetSpec targetSpec = targetSpecMapper.selectOne(queryWrapper);
//        BeanUtils.copyProperties(targetSpec,sp);
//        List<PictureAttVo> pictureAttVos = pictureAttrService.listBySourceId(id);
//        if (!CollectionUtils.isEmpty(pictureAttVos)) {
//            sp.setPlant(pictureAttVos.stream().
//                    filter(vo -> "plant".equals(vo.getFileUse())).collect(Collectors.toList()));
//            sp.setCommunity(pictureAttVos.stream().
//                    filter(vo -> "community".equals(vo.getFileUse())).collect(Collectors.toList()));
//            sp.setHabitat(pictureAttVos.stream().
//                    filter(vo -> "habitat".equals(vo.getFileUse())).collect(Collectors.toList()));
//        }
//        return sp;
//    }



    @Override
    public Object getById(String id, String specSource) {
//        ??????????????????????????????_????????????
//        if (specSource.equals(Constants.SPEC_SOURCE_MONITOR)){
//            SpeciesMonitorVo sp=new SpeciesMonitorVo();
//            QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("id", id);
//            TargetSpec targetSpec = targetSpecMapper.selectOne(queryWrapper);
//            BeanUtils.copyProperties(targetSpec,sp);
//            List<PictureAttVo> pictureAttVos = pictureAttrService.listBySourceId(id);
//            if (!CollectionUtils.isEmpty(pictureAttVos)) {
////                sp.setPlant(pictureAttVos.stream().
////                        filter(vo -> "plant".equals(vo.getFileUse())).collect(Collectors.toList()));
////                sp.setCommunity(pictureAttVos.stream().
////                        filter(vo -> "community".equals(vo.getFileUse())).collect(Collectors.toList()));
////                sp.setHabitat(pictureAttVos.stream().
////                        filter(vo -> "habitat".equals(vo.getFileUse())).collect(Collectors.toList()));
//            }
//            return sp;
//        }else{
//          ?????????????????????????????????_????????????
            SpeciesSurveyVo sp=new SpeciesSurveyVo();
            QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id).eq("del_flag","N");
            TargetSpec targetSpec = targetSpecMapper.selectOne(queryWrapper);
            BeanUtils.copyProperties(targetSpec,sp);
            if (targetSpec.getAmount()!=null){
                int end=targetSpec.getAmount().toString().indexOf(".");
                sp.setAmount(targetSpec.getAmount().toString().substring(0,end));
            }
            if (targetSpec.getArea()!=null){
                sp.setArea(targetSpec.getArea().toString());
            }


            Result<List<DropDownWithLatinVo>>dropList =Result.ok(getDropList()) ;
            Map<String, DropDownWithLatinVo> map = ApiUtil.getResultObjMap(dropList);
             DropDownWithLatinVo dropDownWithLatinVo = map.get(sp.getSpecId());
            if (dropDownWithLatinVo!=null){
                sp.setAttrName(dropDownWithLatinVo.getAttrName());
                sp.setFamilyName(dropDownWithLatinVo.getFamilyName());
                sp.setSpecName(dropDownWithLatinVo.getName());
                sp.setLatinName(dropDownWithLatinVo.getLatinName());
//                Boolean isDisabled =true;
//        //      ??????????????????????????????????????????id????????????????????? isDisabled???true???????????????????????????????????? false????????????????????????
//                sp.setIsDisabled(isDisabled);
            }else {
                sp.setSpecId("");
                sp.setAttrName("");
                sp.setFamilyName("");
                sp.setSpecName("");
                sp.setLatinName("");
//                Boolean isDisabled =false;
//                sp.setIsDisabled(isDisabled);
            }

            List<PictureAttVo> pictureAttVos = pictureAttrService.listBySourceId(id);
            if (!CollectionUtils.isEmpty(pictureAttVos)) {
                sp.setPlant(pictureAttVos.stream().
                        filter(vo -> "plant".equals(vo.getFileUse())).collect(Collectors.toList()));
                sp.setCommunity(pictureAttVos.stream().
                        filter(vo -> "community".equals(vo.getFileUse())).collect(Collectors.toList()));
                sp.setHabitat(pictureAttVos.stream().
                        filter(vo -> "habitat".equals(vo.getFileUse())).collect(Collectors.toList()));
            }
//            ???????????????????????????
            Result<List<DropDownWithLatinVo>> listResult = jzbApi.listForSpecies();
            List<String> collect = listResult.getData().stream().map(DropDownWithLatinVo::getId).collect(Collectors.toList());
            Boolean isDisabled = collect.contains(sp.getSpecId());
            sp.setIsDisabled(isDisabled);
             return sp;
//        }

    }



    @Override
    public List<DropDownWithLatinVo> getDropList() {
//        c????????????????????????????????????
        Result<List<DropDownWithLatinVo>> listResult = jzbApi.listForSpecies();
        List<DropDownWithLatinVo> data = listResult.getData();
//        ??????????????????????????????????????????????????????ID
        List<String> list1 = targetSpecMapper.getList();
//
        if (!CollectionUtils.isEmpty(list1)) {
            for (int i = 0; i < list1.size(); i++) {
//                ????????????id???????????????????????????????????????????????????????????????????????????
                DropDownWithLatinVo bySpecId = targetSpecMapper.getBySpecId(list1.get(i));
                if (bySpecId!=null&&!list1.get(i).isEmpty()){
                    bySpecId.setName(bySpecId.getName());
                    data.add( bySpecId);
                }
            }
        }
        return data;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBySourceId(List<SpeciesSurveyForm> speciesList, String id) {
        List<SpeciesSurveyVo> bySourceId = getBySourceId(id, Constants.SPEC_SOURCE_SURVEY, SpeciesSurveyVo.class);

//        List<String> list=new ArrayList<>();
//        for (SpeciesSurveyVo by:
//             bySourceId) {
//            list.add(by.getId());
//        }
//        ?????????????????????????????????id
        List<String> list = bySourceId.stream().map(SpeciesSurveyVo::getId).collect(Collectors.toList());
//        ???????????????????????????id
        List<String> list2=new ArrayList<>();
        if (!CollectionUtils.isEmpty(speciesList)) {
            for (SpeciesSurveyForm s:
                    speciesList){
                if (s.getId()!=null){
                    list2.add(s.getId());
                }
            update(s,null,id);
            }
        }
//        ?????????????????????
        list.removeAll(list2);
        for (String s:
             list) {
            delete(s);
        }
    }

}
