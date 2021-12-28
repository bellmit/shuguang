package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Question;
import com.sofn.fdpi.vo.AnswerVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/9 10:53
 */
public interface QuestionMapper extends BaseMapper<Question> {
    List<Question> getQuestionList(Map<String,Object> map);
    /*int deleteQuestion(String id);*/
    int answerQuestion(AnswerVo questionFrom);
}
