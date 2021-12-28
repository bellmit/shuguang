package com.sofn.web.pre;

import com.sofn.core.base.BaseController;
import com.sofn.model.generator.TDgapWsDailyStat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.endpoint.ServerRegistry;
import org.apache.cxf.service.model.EndpointInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "ws查询服务", description = "查询服务器上运行的ws")
@RequestMapping(value = "/wsRegistry")
public class WsRegistryController extends BaseController {

    @ApiOperation(value = "查询服务器上运行的web服务")
    @RequestMapping(value = "/listWsdls")
    public Object list() {
        List<String> list = new ArrayList<String>();
        System.out.println("begin of getAllWebServiceAddresses");
        //Look up for all available endpoints registered on the bus
        Bus bus = CXFBusFactory.getDefaultBus();
        ServerRegistry serverRegistry = bus.getExtension(ServerRegistry.class);
        List<Server> servers = serverRegistry.getServers();

        for (Server server : servers) {
            EndpointInfo x = server.getEndpoint().getEndpointInfo();
            //server.getDestination();
            //server.getEndpoint().getService().getServiceInfos()
            String address = x.getAddress();
            System.out.println("address:"+address);
            list.add(address);
        }
        System.out.println("end of getAllWebServiceAddresses");

        return setSuccessModelMap(new ModelMap(), list);
    }

}
