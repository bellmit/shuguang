package com.sofn.sys.web.integration;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/IntegrationLogin")
@Api(tags = "整合 登录")
public class IntegrationLoginController {

    @Value("${bam.domain}")
    private String bamDomain;
    @Value("${bam.clientId}")
    private String clientId;
    @Value("${server.ip}")
    private String serverIP;

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public void redirect(HttpServletResponse response) throws IOException{

        String url = "http://" + bamDomain + "/idp/oauth2/authorize?client_id=" + clientId +
                "&redirect_uri=http://"+serverIP+"/getTokenAndUserInfo&response_type=code&state=123456";

        response.sendRedirect(url);
    }
}
