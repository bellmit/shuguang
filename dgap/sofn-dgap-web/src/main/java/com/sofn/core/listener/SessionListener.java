package com.sofn.core.listener;

import com.sofn.core.Constants;
import com.sofn.core.util.RedisUtil;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会话监听器
 *
 * @author sofn
 * @version $Id: SessionListener.java, v 0.1 2014年3月28日 上午9:06:12 sofn Exp
 */
public class SessionListener implements HttpSessionListener {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** 获取在线用户数量 */
  public static Long getAllUserNumber() {
    String v = (String) RedisUtil.get(Constants.ALLUSER_NUMBER);
    if (v != null) {
      return Long.valueOf(v);
    }
    return 0L;
  }

  @Override
  public void sessionCreated(HttpSessionEvent httpSessionEvent) {}

  @Override
  public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {}
}
