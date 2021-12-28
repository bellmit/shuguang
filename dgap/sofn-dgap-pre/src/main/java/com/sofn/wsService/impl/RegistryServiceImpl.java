package com.sofn.wsService.impl;

import com.sofn.ws.RegistryService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.endpoint.ServerRegistry;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.cxf.transport.http.DestinationRegistry;
import org.apache.cxf.transport.http.HTTPTransportFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by weiqiang on 2016/11/14.
 */
@WebService(name = "registry", serviceName = "registryService")
public class RegistryServiceImpl implements ApplicationContextAware, RegistryService {

    private HTTPTransportFactory transportFactory;
    @Resource
    private WebServiceContext context;

    @Override
    public List<W3CEndpointReference> getAllWebServices() {
        System.out.println("begin of getAllWebServices");
        //transportFactory = new HTTPTransportFactory(CXFUtil.getBus());
        try{
            List<W3CEndpointReference> endpointReferences = new ArrayList<>();
            DestinationRegistry registry = transportFactory.getRegistry();

            for (AbstractHTTPDestination destination : registry
                    .getDestinations()) {
                EndpointInfo endpointInfo = destination.getEndpointInfo();
                String address = endpointInfo.getAddress();
                W3CEndpointReference endpointReference = new W3CEndpointReferenceBuilder()
                        .address(address).build();
                endpointReferences.add(endpointReference);
            }

            System.out.println("end of getAllWebServices");
            return endpointReferences;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getAllWebServiceAddresses() {
        List<String> list = new ArrayList<String>();
        //Look up for all available endpoints registered on the bus
        Bus bus = CXFBusFactory.getDefaultBus();
        ServerRegistry serverRegistry = bus.getExtension(ServerRegistry.class);
        List<Server> servers = serverRegistry.getServers();

        ServletContext servletContext =
                (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        servletContext.getContextPath();
        HttpServletRequest request =(HttpServletRequest) context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
        String requestUrl = request.getRequestURL().toString();
        //http://localhost:11080/sofn-dgap-pre/ws/registry
        System.out.println("requestUrl:"+requestUrl);

        //if the incoming message has a namespace contained "2007/03/21", we redirect the message
        //to the new version of service on endpoint "local://localhost:9027/SoapContext/version2/SoapPort"
        for (Server server : servers) {
            EndpointInfo x = server.getEndpoint().getEndpointInfo();
            //server.getDestination();
            //server.getEndpoint().getService().getServiceInfos()
            String prefix=requestUrl.substring(0,requestUrl.lastIndexOf('/'));
            String address = x.getAddress();
            System.out.println("address:"+address);
            String wsdlUrl = prefix +"/"+ address + "?wsdl";
            list.add(wsdlUrl);
        }
        return list;
    }


    @Override
    @WebMethod(exclude = true)
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /*transportFactory = (HTTPTransportFactory) applicationContext
                .getBean(HTTPTransportFactory.class.getCanonicalName());*/
    }
}
