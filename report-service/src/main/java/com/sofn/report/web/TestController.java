package com.sofn.report.web;

import com.sofn.common.email.EmailService;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.report.api.SysApi;
import com.sofn.report.api.SysRegionForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/8/3 9:20
 *@Version 1.0
 **/
@Slf4j
@Api(value = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  SysApi sysApi;

  @Autowired
  EmailService emailService;

  @ApiOperation(value = "test feign request")
  @GetMapping("/feign")
  public Result testFeign(){
//    Result<SysRegionForm> result = sysApi.getSysRegionByName("成都市");
    Result<String> result = sysApi.getFileRealPath("568febe5a9c1438fabd4e903e6577e3e");
    System.out.println(result.toString());
    return Result.ok(result);
  }

  @ApiOperation(value = "test feign request")
  @GetMapping("/sendEmail")
  public void testSendEmail(){
    List<SysFileManageVo> fileList = new ArrayList<>();
    SysFileManageVo vo = new SysFileManageVo();
    vo.setFileName("1111111111111.xlsx");
//    vo.setFilePath("http://10.0.50.101/group1/M00/01/36/CgAyZV8r5_-AW-nVAACcgbRnWtI11.xlsx");
    vo.setFilePath("http://218.89.222.117:9012/group1/M00/01/36/CgAyZV8s4WCAB0evAACcgbRnWtI39.xlsx");
    fileList.add(vo);
    try {
      emailService.sendAttachmentsMail("345412682@qq.com","测试发送附件","测试linux发送附件",fileList);
    } catch (MessagingException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
  }

}
