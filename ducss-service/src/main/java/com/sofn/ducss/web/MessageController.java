package com.sofn.ducss.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.ducss.service.MessageService;
import com.sofn.ducss.vo.message.MessagePageParam;
import com.sofn.ducss.vo.message.NoticePageParam;
import com.sofn.ducss.vo.message.MessageParam;
import com.sofn.ducss.vo.message.MessageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * message
 * @author jiangtao
 * @date 2020-10-28
 */
@Api(tags = "消息通知")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {



    @Autowired
    private MessageService messageService;

    @SofnLog("部级新增或下发通知")
    @ApiOperation(value = "部级新增或下发通知")
    @PostMapping(value = "/addNotice")
    public Result<Boolean> addOrSendNotice(@Validated @RequestBody MessageParam param) {
        Boolean b = messageService.addOrSendNotice(param);
        if (b){
            return Result.ok("新增通知成功");
        } else{
            return Result.error("新增通知失败");
        }
    }


    @SofnLog("编辑通知")
    @ApiOperation(value = "编辑通知")
    @PutMapping(value = "/editnotice")
    public Result<Boolean> editnotice(@Validated @RequestBody MessageParam param) {
        Boolean b = messageService.editNotice(param);
        if (b){
            return Result.ok("编辑成功");
        } else{
            return Result.error("编辑失败");
        }
    }

    @SofnLog("删除通知")
    @ApiOperation(value = "删除通知")
    @DeleteMapping(value = "/del/{id}")
    public Result<Boolean> deleteInvestigation(@PathVariable("id") String id) {
        boolean b = messageService.delNotice(id);
        if (b){
            return Result.ok("删除成功");
        } else{
            return Result.error("删除失败");
        }
    }

    @SofnLog(value = "部级通知列表")
    @ApiOperation(value = "部级通知列表")
    @PostMapping("/getNotices")
    public Result<PageUtils<MessageVo>> getNotices(@RequestBody NoticePageParam pageParam) {
        // 获取分页内容
        PageUtils<MessageVo> notices = messageService.getNotices(pageParam);
        return Result.ok(notices);
    }

    @SofnLog(value = "部级下发通知")
    @ApiOperation(value = "部级下发通知")
    @PutMapping(value = "/ministrySend/{id}")
    public  Result<Boolean>  ministrySend(@PathVariable("id") String id) {
        boolean b = messageService.sendNotice(id);
        return Result.ok(b);
    }

    @SofnLog(value = "各级查看通知,上报消息,审核列表")
    @ApiOperation(value = "各级查看通知,上报消息,审核列表")
    @PostMapping(value = "/getMessageAndNotice")
    public  Result<PageUtils<MessageVo>>  getMessageAndNotice(@RequestBody MessagePageParam messagePageParam) {
        PageUtils<MessageVo> messageAndNotice = messageService.getMessageAndNotice(messagePageParam);
        return Result.ok(messageAndNotice);
    }

    @SofnLog(value = "各级查看(通知,上报消息,审核消息)")
    @ApiOperation(value = "各级查看(通知,上报消息,审核消息)")
    @PutMapping(value = "/seeMessage/{id}")
    public  Result<Boolean>  seeMessage(@PathVariable("id") String id) {
        Boolean b = messageService.seeMessage(id);
        return Result.ok(b);
    }

    @SofnLog(value = "获取当前用户有几条未读消息")
    @ApiOperation(value = "获取当前用户有几条未读消息")
    @GetMapping(value = "/getUnReadMessageOrNoticeNum")
    public  Result<Integer>  getUnReadMessageOrNoticeNum() {
        Integer num = messageService.getUnReadMessageOrNoticeNum();
        return Result.ok(num);
    }
}