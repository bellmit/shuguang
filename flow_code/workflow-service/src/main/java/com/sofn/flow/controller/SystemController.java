package com.sofn.flow.controller;

import com.sofn.flow.util.AutoParamUtil;
import com.sofn.flow.util.ReturnJsonUtil;
import com.sofn.flow.vo.Result;
import com.sofn.flow.vo.SysParamVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统配置接口
 * @author heyongjie
 * @date 2020/5/29 13:55
 */
@RestController
public class SystemController {

    @RequestMapping(value = "/getSystemParam", method = RequestMethod.GET)
    public void getSystemParam(HttpServletResponse response) {
        List<SysParamVo> sysParamVos = AutoParamUtil.getSysParamVos();
        ReturnJsonUtil.returnJson(response, Result.ok(sysParamVos));
    }

}
