package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Question;
import com.sofn.fdpi.vo.AnswerVo;
import com.sofn.fdpi.vo.QuestionFrom;
import com.sofn.fdpi.vo.QuestionVoOne;

import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/9 11:11
 */
@Deprecated
public interface QuestionService extends IService<Question> {

    PageUtils<Question> getQuestionList(Map<String, Object> map, int pageNo, int pageSize);

    String saveQuestion(QuestionVoOne questionVo);



    Question getQuestion(String id);

    /**
     * 解答问题
     * @param questionFrom
     * @return
     */
    int answerQuestion( AnswerVo questionFrom);

    String saveQuestionWithToken(QuestionVoOne questionVo);

}
