package com.sofn.ducss.model;

import lombok.Data;

@Data
public class SysCaptcha {

  /**
   * 验证码文字
   */
  private String text;

  /**
   * 验证码图片base64编码
   */
  private String captchaBase64;

}
