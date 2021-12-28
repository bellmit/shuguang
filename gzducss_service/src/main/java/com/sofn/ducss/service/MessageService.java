package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.Message;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.message.MessagePageParam;
import com.sofn.ducss.vo.message.MessageParam;
import com.sofn.ducss.vo.message.MessageVo;
import com.sofn.ducss.vo.message.NoticePageParam;

/**
 * 站内消息接口类
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface MessageService  extends IService<Message> {

    /**
     * 部级新增通知
     *
     * @param messageParam          新增消息实体
     * @return boolean 布尔类型
     */
    boolean addOrSendNotice(MessageParam messageParam);


    /**
     * 部级编辑通知（未下发）
     *
     * @param messageParam          新增消息实体
     * @return boolean 布尔类型
     */
    boolean editNotice(MessageParam messageParam);


    /**
     * 部级删除通知（未下发时）
     *
     * @param id          通知id
     * @return boolean 布尔类型
     */
    boolean delNotice(String id);


    /**
     * 部级下级通知
     *
     * @param id          部级通知id
     * @return boolean 布尔类型
     */
    boolean sendNotice(String id);


    /**
     * 部级通知列表
     *
     * @param noticePageParam          通知分页实体
     * @return boolean 布尔类型
     */
    PageUtils<MessageVo> getNotices(NoticePageParam noticePageParam);

    /**
     * 各级查看通知,上报消息,审核列表
     *
     * @param messageParam         各级查看通知,上报消息,审核分页实体
     * @return boolean 布尔类型
     */
    PageUtils<MessageVo> getMessageAndNotice(MessagePageParam messageParam);


    /**
     * 各级查看(通知,上报消息,审核消息)
     *
     * @param id         通知,上报消息,审核消息id
     * @return boolean 布尔类型
     */
    Boolean seeMessage(String id);

    /**
     * 新增下级上报上级上报消息
     *
     * @param status        操作状态
     * @param opinion  意见
     * @param text  内容
     * @param areaId  这条通过或退回数据所对应的区域id
     * @return boolean 布尔类型
     */
    Boolean addMessageByProcess(String status,String opinion,String text,String areaId);

    /**
     * 查询当前用户有几条未读消息
     *
     * @return Integer 个数
     */
    Integer getUnReadMessageOrNoticeNum();
}