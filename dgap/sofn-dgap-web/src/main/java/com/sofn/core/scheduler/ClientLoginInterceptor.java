package com.sofn.core.scheduler;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * author tianjie
 *
 * @date 2016/10/28
 */
public class ClientLoginInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

  private static final Logger logger =
      LoggerFactory.getLogger(ClientLoginInterceptor.class.getName());
  private String username;
  private String password;

  public static final String xml_userID_el = "auth_userID";
  public static final String xml_password_el = "auth_password";

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 创建一个新的实例 ClientLoginInterceptor.
   *
   * @param username
   * @param password
   */
  public ClientLoginInterceptor(String username, String password) {
    super(Phase.PREPARE_SEND);
    this.username = username;
    this.password = password;
  }
  /* (non-Javadoc)
   * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
   */
  public void handleMessage(SoapMessage soap) throws Fault {
    // TODO Auto-generated method stub
    List<Header> headers = soap.getHeaders();

    Document doc = DOMUtils.createDocument();

    Element auth = doc.createElement("auth_authentication");
    Element username = doc.createElement(xml_userID_el);
    Element password = doc.createElement(xml_password_el);

    username.setTextContent(this.username);
    password.setTextContent(this.password);
    auth.appendChild(username);
    auth.appendChild(password);

    headers.add(0, new Header(new QName("http://gd.chinamobile.com//authentication"), auth));
  }
}
