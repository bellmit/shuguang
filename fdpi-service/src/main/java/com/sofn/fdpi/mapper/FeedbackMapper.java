package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Feedback;

import java.util.List;
import java.util.Map;

/**
 * @Description: 反馈信息Mapper
 * @Auther: Bay
 * @Date: 2019/12/28 09:44
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
    /**
     * 获取反馈信息
     * @param map
     * @return
     */
    List<Feedback> getFeedbackList(Map<String,Object> map);
    List<Feedback> listPublic(Map<String,Object> map);
    int  del(String id);
    void advice(Map map);
    String getDireclyId(String compName);
}
