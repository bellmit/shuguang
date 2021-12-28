package com.sofn.web.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.sys.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @DubboReference
    private static ProviderService providerService;

    @GetMapping("/test")
    public String test() {
        return "Test " + providerService.hello();
//        return "";
    }

}

