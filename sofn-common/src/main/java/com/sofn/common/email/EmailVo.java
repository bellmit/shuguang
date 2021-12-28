package com.sofn.common.email;

import com.sofn.common.fileutil.SysFileManageVo;
import jdk.internal.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.List;

/**
 *@Description 邮件
 *@Author quzhijie
 *@Date 2020/5/28 16:51
 *@Version 1.0
 **/
@Data
public class EmailVo {
  /**
   * 目标邮箱
   */
  private String to;
  /**
   * 邮件标题
   */
  private String title;
  /**
   * 邮件HTML内容
   */
  private String content;
  /**
   * 附件
   */
  private List<SysFileManageVo> files;
}
