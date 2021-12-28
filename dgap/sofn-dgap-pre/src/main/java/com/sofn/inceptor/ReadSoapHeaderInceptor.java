package com.sofn.inceptor;

import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapWsErrorLog;
import com.sofn.model.generator.TDgapWsLog;
import com.sofn.service.TDgapCheckResourceApplicationService;
import com.sofn.service.TDgapWsLogService;
import com.sofn.util.Constant;
import com.sofn.util.SessionHelper;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.invoker.MethodDispatcher;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpSession;
import javax.xml.soap.SOAPException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ReadSoapHeaderInceptor extends AbstractPhaseInterceptor<SoapMessage> {

    @Autowired
    private TDgapCheckResourceApplicationService tDgapCheckResourceApplicationService;

    @Autowired
    private TDgapWsLogService tDgapWsLogService;

    private static final Logger logger = LoggerFactory.getLogger(ReadSoapHeaderInceptor.class.getName());
    public static final String xml_password_el = "auth_password";
    public ReadSoapHeaderInceptor() {
        // 指定该拦截器在哪个阶段被激发
        super(Phase.PRE_INVOKE);
    }

    // 处理消息
    public void handleMessage(SoapMessage message) {
        logger.info("==================SoapMessage =" + message);
        // 获取SOAP消息的全部头
        List<Header> headers = message.getHeaders();
        TDgapWsLog tDgapWsLog = null;

        //检测心跳的方法不需要验证用户信息
        Exchange exchange = message.getExchange();
        BindingOperationInfo bop = exchange.get(BindingOperationInfo.class);

        MethodDispatcher md = (MethodDispatcher) exchange.get(Service.class)
                .get(MethodDispatcher.class.getName());
        Method method = md.getMethod(bop);

        if(method.getName().equals(Constant.HEART_BEAT_MESSAGE_METHOD)){
            //心跳方法,不验证权限
            return;
        }

        String serviceName = message.getExchange().getDestination().getAddress().getAddress().getValue();

        if (null == headers || headers.size() !=1) {
            throw new Fault(new SOAPException("SOAP消息头格式不对哦！"));
        }
        for (Header soapHeader : headers) {
            // 取出SOAP的Header元素
            Element element = (Element) soapHeader.getObject();
            logger.info("ELEMENT =" + element.toString());
            //XMLUtils.printDOM(element);
            NodeList pwdNodes = element.getElementsByTagName(xml_password_el);


            String authCode = pwdNodes.item(0).getTextContent();
            Map<String,Object> map= tDgapCheckResourceApplicationService.getTdgapResourceApplication(authCode);

            if(map!=null && map.get("WSDL_URL").toString().indexOf(serviceName)>=1){
                //授权码正确
            }else{
                //授权码错误
                initLoginErrorLog(method.getName());
                SOAPException soapException = new SOAPException("不存在该授权码");
                logger.error(" 认证失败");
                throw new Fault(soapException);
            }
            String userId = map.get("USER_ID").toString();
            String resourceId = map.get("WS_ID").toString();

            tDgapWsLog=createWsLog(resourceId,userId,method.getName());
            //将日志加入到session当中
            HttpSession session = SessionHelper.getSession();
            session.setAttribute(Constant.LOGBEAN,tDgapWsLog);
            logger.info("############ 打印帐号信息 ##############");
            logger.info("userId" + "=" + userId);
            logger.info("resourceId=" + resourceId);
            logger.info("############————————##############");

        }
    }

    /**
     *
     * @param resourceId
     * @param userId
     */
    private TDgapWsLog createWsLog(String resourceId, String userId,String methodName){
        TDgapWsLog tDgapWsLog = new TDgapWsLog();
        tDgapWsLog.setWebServiceId(resourceId);
        tDgapWsLog.setMethodName(methodName);
        tDgapWsLog.setInvokeStartDate(new Date());
        tDgapWsLog.setCreateTime(new Date());
        tDgapWsLog.setCallerUser(userId);
        return tDgapWsLog;
    }

    private void initLoginErrorLog(String methodName){

        TDgapWsErrorLog tDgapWsLog = new TDgapWsErrorLog();
        tDgapWsLog.setWebServiceId("1111111111111");
        tDgapWsLog.setMethodName(methodName);
        tDgapWsLog.setCreateTime(new Date());
        tDgapWsLog.setCallerUser("error_user");
        tDgapWsLog.setErrorType(Constants.WSErrorType.AUTH_ERROR.getErrorCode()+"");
        tDgapWsLog.setErrorDesc(Constants.WSErrorType.AUTH_ERROR.getErrorInnerDesc());
        tDgapWsLog.setErrorDate(new Date());
        tDgapWsLog.setErrorDate(new Date());
        tDgapWsLogService.insertWsErrLog(tDgapWsLog);
    }
}