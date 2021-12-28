package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.Message;
import com.sofn.ducss.vo.message.MessagePageParam;
import com.sofn.ducss.vo.message.MessageVo;
import com.sofn.ducss.vo.message.NoticePageParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 消息实体类映射
 *
 * @author jiangtao
 * @date 2020/10/28
 */
@Component
public interface MessageMapper extends BaseMapper<Message> {


    /**
     * 获取部级通知列表
     *
     * @param   noticePageParam 分页参数
     * @return  List<Message>  部级消息集合
     */
    List<MessageVo> getMinistryNotices(NoticePageParam noticePageParam);

    /**
     * 各级查看通知
     *
     * @param messageParam         各级查看通知,上报消息,审核分页实体
     * @return boolean 布尔类型
     */

    List<MessageVo> getNotices(MessagePageParam messageParam);

    /**
     * 各级查看通知
     *
     * @param messageParam         各级查看通知,上报消息,审核分页实体
     * @return boolean 布尔类型
     */
    List<MessageVo> getMessages(MessagePageParam messageParam);

    /**
     * 获取当前用户有多少条未读消息
     *
     * @param map  请求参数
     * @return Integer 数字
     */
    Integer getUnReadMessageOrNoticeNum(Map<String,Object> map);
}
