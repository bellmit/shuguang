package com.sofn.common.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * 邮件事件监听
 */
@Component
public class EmailListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmailService emailService;

    @Async
    @EventListener
    public void handleEvent(EmailVo emailVo) {
      logger.error("email", emailVo.toString());
      try {
        emailService.sendAttachmentsMail(emailVo.getTo(),emailVo.getTitle(),emailVo.getContent(),emailVo.getFiles());
      } catch (MessagingException e) {
        logger.error("email", "email send failed");
        e.printStackTrace();
      }
    }

}
