package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Feedback;
import com.sofn.fdpi.vo.FeedbackInfoVo;

import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/30 09:08
 */
public interface FeedbackService extends IService<Feedback> {
    /**部门
     * 获取反馈信息列表(分页)
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<Feedback> getFeedbackListByPage(Map<String,Object> map, int pageNo, int pageSize);

    /**
     * 获取公众反馈信息
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<Feedback> ListPage(Map<String,Object> map, int pageNo, int pageSize);

    /**
     * 获取详细反馈信息
     * @param id
     * @return
     */
    Feedback getFeedbackInfo(String id);

    /**
     * 部门
     * @param feedbackInfoVo
     * @return
     */
    int saveFeedback(FeedbackInfoVo feedbackInfoVo);

    /**
     * 公众
     * @param feedbackInfoVo
     * @return
     */
    int savePublic(FeedbackInfoVo feedbackInfoVo);
    int del(String id);

    int update(FeedbackInfoVo feedbackInfoVo);

    void advice(Map map);
}
