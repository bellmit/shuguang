package com.sofn.common.email;

import com.sofn.common.exception.SofnException;
import com.sofn.common.fastdfs.FastDFSUtils;
import com.sofn.common.fileutil.SysFileManageVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 *@Description 邮件服务
 *@Author quzhijie
 *@Date 2020/5/29 10:36
 *@Version 1.0
 **/
@Service
public class EmailService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${spring.mail.username}")
  private String from;

  @Autowired
  private JavaMailSender mailSender;

  /**
   * 简单文本邮件
   * @param to 接收者邮件
   * @param subject 邮件主题
   * @param contnet 邮件内容
   */
  public void sendSimpleMail(String to, String subject, String contnet){

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(contnet);
    message.setFrom(from);

    mailSender.send(message);
  }

  /**
   * HTML 文本邮件
   * @param to 接收者邮件
   * @param subject 邮件主题
   * @param contnet HTML内容
   * @throws MessagingException
   */
  public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(contnet, true);
    helper.setFrom(from);

    mailSender.send(message);
  }

  /**
   * 附件邮件
   * @param to 接收者邮件
   * @param subject 邮件主题
   * @param contnet HTML内容
   * @param filePaths 附件路径
   * @throws MessagingException
   */
  public void sendAttachmentsMail(String to, String subject, String contnet,
                                  List<SysFileManageVo> filePaths) throws MessagingException {
    logger.error("send mail start-2 .......");
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(contnet, true);
    helper.setFrom(from);

    if (!CollectionUtils.isEmpty(filePaths)) {
      logger.error("send mail start-3 .......");
      for (SysFileManageVo file : filePaths) {
        if (file == null || StringUtils.isBlank(file.getFilePath())) {
          continue;
        }

        if (file.getFilePath().toLowerCase().startsWith("http")){
          UrlResource urlResource = null;
          try {
            urlResource = new UrlResource(new URL(file.getFilePath()));
            helper.addAttachment(file.getFileName(), urlResource);
          } catch (MalformedURLException e) {
            logger.error("send mail start-3 .......");
            logger.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
            throw new SofnException("send faild", e);
          }
        }else {
          FileSystemResource fileResource = new FileSystemResource(new File(file.getFilePath()));
          helper.addAttachment(file.getFileName(), fileResource);
        }
      }
    }

    logger.error("send mail start-4 .......");
    mailSender.send(message);
    logger.error("send mail start-5 .......");
  }

  /**
   * 图片邮件
   * @param to 接收者邮件
   * @param subject 邮件主题
   * @param contnet HTML内容
   * @param rscPath 图片路径
   * @param rscId 图片ID
   * @throws MessagingException
   */
  public void sendInlinkResourceMail(String to, String subject, String contnet,
                                     String rscPath, String rscId) {
    logger.info("发送静态邮件开始: {},{},{},{},{}", to, subject, contnet, rscPath, rscId);

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = null;

    try {
      helper = new MimeMessageHelper(message, true);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(contnet, true);
      helper.setFrom(from);

      FileSystemResource res = new FileSystemResource(new File(rscPath));
      helper.addInline(rscId, res);
      mailSender.send(message);
      logger.info("发送静态邮件成功!");

    } catch (MessagingException e) {
      logger.info("发送静态邮件失败: ", e);
    }

  }
}
