package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sofn.common.email.EmailService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.IsEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.mapper.QuestionMapper;
import com.sofn.fdpi.model.Question;
import com.sofn.fdpi.model.TbComp;
import com.sofn.fdpi.model.TbUsers;
import com.sofn.fdpi.service.QuestionService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.service.TbUsersService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/9 11:15
 */
@Deprecated
@Slf4j
@Service
public class QuestionServiceImpl extends BaseService<QuestionMapper, Question>implements QuestionService {
    @Autowired
    QuestionMapper qMapper;
    @Autowired
    private TbUsersService tbUsersService;
    @Autowired
    @Lazy
    private TbCompService tbCompService;
    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired(required = false)
    private EmailService emailService;
    @Override
    public PageUtils<Question> getQuestionList(Map<String, Object> map, int pageNo, int pageSize){

        String sysOrgLevelId = null;
        try {
            sysOrgLevelId = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery().getData().getSysOrgLevelId();
        } catch (Exception e) {
            throw  new SofnException("?????????????????????????????????");
        }
//      ??????????????????
        Boolean caseOne=OrganizationLevelEnum.COMPANY_ORG_LEVEL.getId().equals(sysOrgLevelId);
//       ????????????????????????
        Boolean caseTwo=OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId()
                .equals(sysOrgLevelId)
                || OrganizationLevelEnum.DIRECT_AND_MINISTRY_ORG_LEVEL.getId()
                .equals(sysOrgLevelId);
        Boolean caseThree=OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId()
                .equals(sysOrgLevelId)
                || OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId()
                .equals(sysOrgLevelId);
        if(caseOne){
            TbUsers byId = tbUsersService.getById(UserUtil.getLoginUserId());
            if (byId==null){
                throw new SofnException("?????????????????????????????????");
            }
            String compId = byId.getCompId();
            map.put("compId",compId);
        }
        if (caseTwo){
            String orgId= UserUtil.getLoginUserOrganizationId();
            map.put("orgId",orgId);
        }
        if (caseThree){
            Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
            String provinceId = sysRegionInfoByOrgId.getData().getProvince();
            map.put("provinceId",provinceId);
        }
        PageHelper.offsetPage(pageNo,pageSize);
        List<Question> questionList = qMapper.getQuestionList(map);
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        return PageUtils.getPageUtils(pageInfo);
    }
    @Transactional
    @Override
    public String saveQuestion(QuestionVoOne questionVo){
        //??????????????????????????????????????????????????????
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("QUE_DESC", questionVo.getQueDesc()).eq("QUE_PERSON",questionVo.getQuePerson());
        List<Question> questions = qMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(questions)){
            throw new SofnException("????????????????????????????????????");
        }
        if(questionVo.getQueAdjunct()!=null&&questionVo.getQueAdjunct()!=""){
            if (questionVo.getQueFileName()==null||"".equals(questionVo.getQueFileName())){
                throw new SofnException("?????????????????????????????????");
            }
            activeFile(questionVo.getQueAdjunct());
            }
            Question question = questionVo.convertToModel(Question.class);
            question.preInsert();
            question.setQueStatus(Integer.parseInt(IsEnum.IS_N.getKey()));
            question.setQFrom(IsEnum.IS_N.getKey());

            boolean save = this.save(question);
            if (save) {
                return "1";
            }
            return "??????????????????";

    }


    @Override
    public Question getQuestion(String id){
        return qMapper.selectById(id);
    }
    /**
     * ????????????
     * @param questionFrom
     * @return
     */
    @Override
    public int answerQuestion(AnswerVo questionFrom) {
      log.error("send mail start .......");
        int i=0;
        String fileId = questionFrom.getFileId();
        Question question = qMapper.selectById(questionFrom.getId());
        if ("0".equals(question.getQFrom())){
            if (fileId!=null&&!"".equals(fileId)){
                activeFile(fileId);
                Result<SysFileManageVo> oneFile = sysRegionApi.getOneFile(fileId);
                List<SysFileManageVo> sysFileList=new ArrayList<>();
                sysFileList.add(oneFile.getData());
                ExecutorService exec = Executors.newFixedThreadPool(1);
                exec.execute(()->{
                    try {
                        emailService.sendAttachmentsMail(question.getQueEmail(),"????????????",questionFrom.getAnswer(),sysFileList);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
                exec.shutdown();
                i= qMapper.answerQuestion(questionFrom);

            }else{
                i= qMapper.answerQuestion(questionFrom);
                try {
                    if (!StringUtils.isEmpty(question.getQueEmail())){
                        emailService.sendHtmlMail(question.getQueEmail(),"????????????",questionFrom.getAnswer());
                    }
                } catch (MessagingException e) {
                    log.error("question",e.getMessage());
                    e.printStackTrace();
                }
            }
        }else {
            i= qMapper.answerQuestion(questionFrom);
            if (fileId!=null&&!"".equals(fileId)) {
                activeFile(fileId);
            }
        }



        return i;
    }

    @Override
    public String saveQuestionWithToken(QuestionVoOne questionVo) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("QUE_DESC", questionVo.getQueDesc()).eq("QUE_PERSON",questionVo.getQuePerson());
//        ??????
        List<Question> questions = qMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(questions)){
            throw new SofnException("????????????????????????????????????");
        }
//        ???????????????????????????
        if(questionVo.getQueAdjunct()!=null&&questionVo.getQueAdjunct()!=""){
            activeFile(questionVo.getQueAdjunct());
        }
        Question question = questionVo.convertToModel(Question.class);
        question.preInsert();
        question.setQueStatus(Integer.parseInt(IsEnum.IS_N.getKey()));
 //        ???????????????????????????id???
        String loginUserId = UserUtil.getLoginUserId();
        question.setQFrom(IsEnum.IS_Y.getKey());
//         ??????????????????????????????????????????id
        TbUsers byId = tbUsersService.getById(loginUserId);
        if(byId==null){
            throw new SofnException("??????????????????????????????????????????????????????????????????????????????");
        }
//        ???????????????id ????????? ??????????????????????????????????????????
            String compId = byId.getCompId();
            question.setCompId(compId);
            TbComp byId1 = tbCompService.getById(compId);
            question.setProvinceId(byId1.getCompProvince());
            question.setDireclyId(byId1.getDireclyId());

        boolean save = this.save(question);
        if (save) {
            return "1";
        }
            return "0";
    }
     private void activeFile(String id){
         SysFileManageForm sfmf = new SysFileManageForm();
         sfmf.setIds(id);
         sfmf.setInterfaceNum("hidden");
         sfmf.setSystemId(Constants.SYSTEM_ID);
         try {
             Result<List<SysFileVo>> result = sysRegionApi.activationFile(sfmf);
             if (result.getData().size()<1) {
                 throw new SofnException("??????????????????!?????????????????????????????????");
             }
         } catch (Exception e) {
             throw new SofnException("??????????????????!?????????????????????????????????");
         }
     }

}
