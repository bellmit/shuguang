package com.sofn.sys.web.integration;
import java.io.IOException;
import java.net.Socket;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import com.sofn.sys.model.Auth;
import com.sofn.sys.vo.LoginVo;
import com.sofn.sys.web.integration.IntegrationTokenService.IntegrationTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/IntegrationToken")
@Api(tags = "整合 获取token")
public class GetTokenController {
    //private static final Logger logger = LoggerFactory.getLogger(GetTokenController.class);

    @Value("${bam.domain}")
    private String bamDomain;
    @Value("${bam.clientId}")
    private String clientId;
    @Value("${bam.clientSecret}")
    private String clientSecret;

    @Autowired
    private IntegrationTokenService integrationTokenService;

    @RequestMapping(value = "/getTokenAndUserInfo",method=RequestMethod.GET)
    public String getTokenAndUserInfo( @RequestParam(required = true) @ApiParam(value = "code") String code,
                                       @RequestParam(name="state")  String state,
                                       HttpServletResponse response) throws Exception{

        //getToken

        String getTokenUrl = "http://" + bamDomain + "/idp/oauth2/getToken?client_id=" + clientId +
                "&grant_type=authorization_code&code=" + code + "&client_secret=" + clientSecret;
        //String getTokenUrl = "http://" + bamDomain + "/idp/oauth2/getToken";
        HttpClient client = new HttpClient();
        JSONObject jsonToken = new JSONObject();
        jsonToken.put("client_id", clientId);
        jsonToken.put("grant_type", "authorization_code");
        jsonToken.put("code", code);
        jsonToken.put("client_secret", clientSecret);
        String getTokenRespone = client.postJson(getTokenUrl, "");
        System.out.println("getTokenRespone is:"+getTokenRespone);
        JSONObject getTokenResponseJson = new JSONObject(getTokenRespone);
        String access_token =getTokenResponseJson.getString("access_token");
        String uid = getTokenResponseJson.getString("uid");

        //getUserInfo
        String getUserInfoUrl ="http://" + bamDomain + "/idp/oauth2/getUserInfo?access_token=" + access_token +
                "&client_id=" + clientId + "&uid=" + uid;
        HttpClient httpClient = new HttpClient();
        String responseString = httpClient.getJson(getUserInfoUrl);
        System.out.println("responseString is:"+responseString);
        JSONObject getUserInfoResponseJson = new JSONObject(responseString);

        String username = getUserInfoResponseJson.getString("loginName");

        LoginVo loginVo = new LoginVo();

        return username;
    }


    /**
     *  loginVo   登录对象
     *isApp   如果不是App登录会进行验证码校验
     *type   因有的系统不走支撑平台的登录，而是使用系统内置的用户直接进行登录，需要加上类别，以免导致token被正常流程给识别，如share系统
     *    最终返回消息
     */
    public Auth OutTokenAndUserInfo(LoginVo loginVo){
        Auth token = integrationTokenService.execuLogin(loginVo, false, null);
        return token;
    }

}
