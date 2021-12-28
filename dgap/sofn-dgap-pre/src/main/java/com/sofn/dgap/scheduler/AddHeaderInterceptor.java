package com.sofn.dgap.scheduler;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * Created by mpeng on 2018/9/6.
 */
public class AddHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private String userName;
    private String passWord;

    public AddHeaderInterceptor(String username, String password) {
        super(Phase.PREPARE_SEND);
        this.userName = username;
        this.passWord = password;
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> header = message.getHeaders();

        // 创建Document对象
        Document document = DOMUtils.createDocument();
        Element element = document.createElement("authHeader");

        // 配置服务端Head信息的用户名和密码
        Element userNameElement = document.createElement("userName");
        userNameElement.setTextContent(userName);
        Element passWordElement = document.createElement("passWord");
        passWordElement.setTextContent(passWord);

        element.appendChild(userNameElement);
        element.appendChild(passWordElement);
        header.add(new Header(new QName(""), element));

    }

}
