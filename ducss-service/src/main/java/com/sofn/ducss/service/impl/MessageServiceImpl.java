package com.sofn.ducss.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.*;
import com.sofn.ducss.mapper.MessageMapper;
import com.sofn.ducss.model.Message;
import com.sofn.ducss.service.MessageService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysUserForm;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.vo.message.MessagePageParam;
import com.sofn.ducss.vo.message.MessageParam;
import com.sofn.ducss.vo.message.MessageVo;
import com.sofn.ducss.vo.message.NoticePageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 站内消息实现类
 *
 * @author jiangtao
 * @date 2020/10/28
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SysApi sysApi;

    @Transactional( rollbackFor = Exception.class )
    @Override
    public boolean addOrSendNotice(MessageParam messageParam) {
        //根据操作类型判断是保存还是下发
        String operationType = messageParam.getOperationType();
        Message message = new Message();
        BeanUtils.copyProperties(messageParam, message);
        message.setMessageType(MessageTypeEnum.CREATE_NOTICE.getCode());
        message.setCreateBy(UserUtil.getLoginUserId());
        message.setCreateTime(LocalDateTime.now());
        if (OperationTypeEnum.SAVE.getCode().equals(operationType)) {
            message.setSendStatus(OperationTypeEnum.SAVE.getCode());
            return save(message);
        } else if (OperationTypeEnum.SEND.getCode().equals(operationType)) {
            //获取当前操作人的姓名
            if (UserUtil.getLoginUser() == null){
                throw new SofnException("当前用户数据异常");
            }
            String nickName = UserUtil.getLoginUser().getNickname();
            message.setIssuedPerson(nickName);
            message.setSendTime(new Date());
            message.setSendStatus(OperationTypeEnum.SEND.getCode());
            boolean b = save(message);
            if (b) {
                //下发站内通知,获取指定类型或区域的人员信息
                return sendNoticeByCondition(message);
            }
            //
            return b;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean editNotice(MessageParam messageParam) {
        //判断部级是否下发消息
        Message message = getById(messageParam.getId());
        if (OperationTypeEnum.SEND.getCode().equals(message.getSendStatus())) {
            throw new SofnException("当下消息已下发,无法下发");
        }
        message.setSendObject(messageParam.getSendObject());
        message.setText(messageParam.getText());
        if (SendObjectEnum.CUSTOM.getCode().equals(message.getSendObject())) {
            message.setUserLevel(messageParam.getUserLevel());
            message.setUserLevel(messageParam.getUserLevel());
        }
        message.setUpdateBy(UserUtil.getLoginUserId());
        message.setUpdateTime(LocalDateTime.now());
        //获取消息类型
        if (OperationTypeEnum.SEND.getCode().equals(messageParam.getOperationType())) {
            //获取当前操作人的姓名
            if (UserUtil.getLoginUser() == null){
                throw new SofnException("当前用户数据异常");
            }
            String nickName = UserUtil.getLoginUser().getNickname();
            message.setIssuedPerson(nickName);
            message.setSendStatus(OperationTypeEnum.SEND.getCode());
            message.setSendTime(new Date());
            boolean b = updateById(message);
            if (b){
                //下发站内通知,获取指定类型或区域的人员信息
                return sendNoticeByCondition(message);
            }
            return false;
        } else {
            return updateById(message);
        }
    }

    @Override
    public boolean delNotice(String id) {
        //未下发才能删除,已下发无法删除
        Message message = getById(id);
        if (OperationTypeEnum.SEND.getCode().equals(message.getSendStatus())) {
            throw new SofnException("当下消息已下发,无法删除");
        }
        return baseMapper.deleteById(id) > 0;
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public boolean sendNotice(String id) {
        Message message = getById(id);
        if (OperationTypeEnum.SEND.getCode().equals(message.getSendStatus())) {
            throw new SofnException("当下消息已下发,无法再次下发");
        }
        //获取当前操作人的姓名
        if (UserUtil.getLoginUser() == null){
            throw new SofnException("当前用户数据异常");
        }
        String nickName = UserUtil.getLoginUser().getNickname();
        message.setIssuedPerson(nickName);
        message.setSendStatus(OperationTypeEnum.SEND.getCode());
        message.setSendTime(new Date());
        updateById(message);
        return sendNoticeByCondition(message);
    }

    /**
     * 部级通知列表
     *
     * @param message 新增消息实体
     * @return boolean 布尔类型
     */
    private boolean sendNoticeByCondition(Message message) {
        //获取当前操作人的姓名
        if (UserUtil.getLoginUser() == null){
            throw new SofnException("当前用户数据异常");
        }
        String nickName = UserUtil.getLoginUser().getNickname();
        //下发站内通知,获取指定类型或区域的人员信息
        String orgLevel = "";
        //自定义时传区域集合
        List<String> areaCodeList = new ArrayList<>();
        //根据类型不同调用返回不同数据
        if (SendObjectEnum.ALL_MINISTRY.getCode().equals(message.getSendObject())) {
            //全体部级用户
            orgLevel = RegionLevel.MINISTRY.getCode();
        }
        //全体省级用户
        if (SendObjectEnum.ALL_PROVINCE.getCode().equals(message.getSendObject())) {
            orgLevel = RegionLevel.PROVINCE.getCode();
        }
        //全体市级用户
        if (SendObjectEnum.ALL_CITY.getCode().equals(message.getSendObject())) {
            orgLevel = RegionLevel.CITY.getCode();
        }
        //全体县级用户
        if (SendObjectEnum.ALL_COUNTY.getCode().equals(message.getSendObject())) {
            orgLevel = RegionLevel.COUNTY.getCode();
        }
        //自定义
        if (SendObjectEnum.CUSTOM.getCode().equals(message.getSendObject())) {
            orgLevel = message.getUserLevel();
            String[] ids = message.getUserAreaId().split(",");
            areaCodeList = Arrays.asList(ids);
        }
        Result<List<SysUserForm>> result = new Result<>();
        List<SysUserForm> userFormList = new ArrayList<>();
        if (areaCodeList.size() > 0) {
            List<List<String>> partition = Lists.partition(areaCodeList, 100);
            for (List<String> list : partition) {
                userFormList.addAll(sysApi.getUserByOrgInfoAndAppId(list, orgLevel, Constants.APPID).getData());
            }
            result.setData(userFormList);
        } else {
            result = sysApi.getUserByOrgInfoAndAppId(areaCodeList, orgLevel, Constants.APPID);
        }
        //获取符合条件的用户信息
        ArrayList<Message> messages = new ArrayList<>();
        if (result != null && result.getData() != null) {
            for (SysUserForm sysUserForm : result.getData()) {
                //10条新增一下
                Message userMessage = new Message();
                userMessage.setText(message.getText());
                userMessage.setUserId(sysUserForm.getId());
                userMessage.setIssuedPerson(UserUtil.getLoginUserName());
                userMessage.setSendTime(new Date());
                userMessage.setMessageType(MessageTypeEnum.NOTICE.getCode());
                userMessage.setStatus(MessageStatusEnum.UNREAD.getCode());
                userMessage.setIssuedPerson(nickName);
                messages.add(userMessage);
            }
            boolean batch = saveBatch(messages, 500);
        }
        LogUtil.addLog(LogEnum.LOG_TYPE_MESSAGE_DISTRIBUTION.getCode(), "下发-<"+message.getText()+">");
        return true;
    }

    @Override
    public PageUtils<MessageVo> getNotices(NoticePageParam messageParam) {
        //处理时间
        if (messageParam.getEndTime() != null ){
            //时间加24小时
            long time = messageParam.getEndTime().getTime() + 86399999L;
            messageParam.setEndTime(new Date(time));
        }
        PageHelper.offsetPage(messageParam.getPageNo(), messageParam.getPageSize());
        List<MessageVo> list = messageMapper.getMinistryNotices(messageParam);
        PageInfo<MessageVo> pageList = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageList);
    }

    @Override
    public PageUtils<MessageVo> getMessageAndNotice(MessagePageParam messageParam) {
        //处理时间
        if (messageParam.getEndTime() != null ){
            //时间加24小时
            long time = messageParam.getEndTime().getTime() + 86399999L;
            messageParam.setEndTime(new Date(time));
        }
        //获取当前登录用户id
        String userId = UserUtil.getLoginUserId();
        messageParam.setUserId(userId);
        List<MessageVo> messageAndNotice = new ArrayList<>();
        PageHelper.offsetPage(messageParam.getPageNo(), messageParam.getPageSize());
        if (MessageTypeEnum.NOTICE.getCode().equals(messageParam.getMessageType())){
             messageAndNotice = messageMapper.getNotices(messageParam);
        } else {
             messageAndNotice = messageMapper.getMessages(messageParam);
        }
        PageInfo<MessageVo> pageList = new PageInfo<>(messageAndNotice);
        return PageUtils.getPageUtils(pageList);
    }

    @Override
    public Boolean seeMessage(String id) {
        Message message = getById(id);
        message.setStatus(MessageStatusEnum.READ.getCode());
        return updateById(message);

    }

    @Override
    public Boolean addMessageByProcess(String status, String opinion, String text, String areaId) {
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        //获取当前操作人的姓名
        if (UserUtil.getLoginUser() == null){
            throw new SofnException("当前用户数据异常");
        }
        String nickName = UserUtil.getLoginUser().getNickname();
        //如果是上报,撤回操作,就向上级管理员发送上报消息
        if (AuditStatusEnum.REPORTED.getCode().equals(status) || AuditStatusEnum.WITHDRAWN.getCode().equals(status)) {
            //获取当前登录用户上级
            List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
            String parentId = "";
            String orgLevel = "";
            //自定义时传区域集合
            List<String> areaCodeList = new ArrayList<>();
            //根据类型不同调用返回不同数据
            if (orgData.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                //县发给市
                orgLevel = RegionLevel.CITY.getCode();
                parentId = list.get(1);
            }
            //市发给省
            if (orgData.getOrganizationLevel().equals(RegionLevel.CITY.getCode())) {
                orgLevel = RegionLevel.PROVINCE.getCode();
                parentId = list.get(0);
            }
            //省发给部
            if (orgData.getOrganizationLevel().equals(RegionLevel.PROVINCE.getCode())) {
                orgLevel = RegionLevel.MINISTRY.getCode();
                parentId = "100000";
            }
            areaCodeList.add(parentId);
            Result<List<SysUserForm>> result = new Result<>();
            if (areaCodeList.size() > 0) {
                result = sysApi.getUserByOrgInfoAndAppId(areaCodeList, orgLevel, Constants.APPID);
            } else {
                result = sysApi.getUserByOrgInfoAndAppId(areaCodeList, orgLevel, Constants.APPID);
            }
            //获取符合条件的用户信息
            ArrayList<Message> messages = new ArrayList<>();
            if (result != null && result.getData() != null) {
                for (SysUserForm sysUserForm : result.getData()) {
                    //10条新增一下
                    Message userMessage = new Message();
                    userMessage.setUserId(sysUserForm.getId());
                    userMessage.setAuditStatus(status);
                    userMessage.setText(text);
                    userMessage.setMessageType(MessageTypeEnum.REPORT_MESSAGE.getCode());
                    userMessage.setStatus(MessageStatusEnum.UNREAD.getCode());
                    userMessage.setAuditPerson(nickName);
                    userMessage.setCreateTime(LocalDateTime.now());
                    userMessage.setCreateBy(UserUtil.getLoginUserId());
                    messages.add(userMessage);
                }
                boolean batch = saveBatch(messages, 500);
                return batch;
            }    //如果是审核通过,退回,则向下级发送审核通过或是退回消息
        } else if (AuditStatusEnum.RETURNED.getCode().equals(status) || AuditStatusEnum.PASSED.getCode().equals(status)) {
            //根据级别获取不同上级列表
            String orgLevel = "";
            //自定义时传区域集合
            List<String> areaCodeList = new ArrayList<>();
            //当前退回,通过数据区域id
            areaCodeList.add(areaId);
            //根据类型不同调用返回不同数据
            if (orgData.getOrganizationLevel().equals(RegionLevel.CITY.getCode())) {
                //市发给县
                orgLevel = RegionLevel.COUNTY.getCode();
            }
            //省发给市
            if (orgData.getOrganizationLevel().equals(RegionLevel.PROVINCE.getCode())) {
                orgLevel = RegionLevel.CITY.getCode();
            }
            //部发给省
            if (orgData.getOrganizationLevel().equals(RegionLevel.MINISTRY.getCode())) {
                orgLevel = RegionLevel.PROVINCE.getCode();
            }
            Result<List<SysUserForm>> result = new Result<>();
            if (areaCodeList.size() > 0) {
                result = sysApi.getUserByOrgInfoAndAppId(areaCodeList, orgLevel, Constants.APPID);
            } else {
                result = sysApi.getUserByOrgInfoAndAppId(areaCodeList, orgLevel, Constants.APPID);
            }
            //获取符合条件的用户信息
            ArrayList<Message> messages = new ArrayList<>();
            if (result != null && result.getData() != null) {
                for (SysUserForm sysUserForm : result.getData()) {
                    //10条新增一下
                    Message userMessage = new Message();
                    userMessage.setUserId(sysUserForm.getId());
                    userMessage.setAuditStatus(status);
                    userMessage.setText(text);
                    userMessage.setAuditOpinion(opinion);
                    userMessage.setMessageType(MessageTypeEnum.AUDIT_MESSAGE.getCode());
                    userMessage.setStatus(MessageStatusEnum.UNREAD.getCode());
                    userMessage.setAuditPerson(nickName);
                    userMessage.setCreateTime(LocalDateTime.now());
                    userMessage.setCreateBy(UserUtil.getLoginUserId());
                    messages.add(userMessage);
                }
                return saveBatch(messages, 500);
            }
        }
        return false;
    }

    @Override
    public Integer getUnReadMessageOrNoticeNum() {
        //获取当前用户有多少条未读消息
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",UserUtil.getLoginUserId());
        ArrayList<String> list = new ArrayList<>();
        list.add(MessageTypeEnum.NOTICE.getCode());
        list.add(MessageTypeEnum.REPORT_MESSAGE.getCode());
        list.add(MessageTypeEnum.AUDIT_MESSAGE.getCode());
        map.put("messageTypes",list);
        map.put("status",MessageStatusEnum.UNREAD.getCode());
        return  messageMapper.getUnReadMessageOrNoticeNum(map);
    }
}