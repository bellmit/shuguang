package com.sofn.gatewayservice;

import com.sofn.common.config.UriConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayServiceApplicationTests {

    @Autowired
    UriConfig uriConfig;

    @Test
    public void contextLoads() {
      System.out.println(uriConfig.toString());
    }

}
