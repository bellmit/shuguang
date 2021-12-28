package com.sofn.agzirdd.sysapi;

import com.sofn.agzirdd.sysapi.bean.DropDownVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description: 获取下拉框相关
 * @Author: chlf
 * @Date: 2020\4\1
 */
@FeignClient(
        value = "agpjzb-service",
//        url = "http://10.0.50.103:7999/agpjzb",
        configuration = FeignConfiguration.class
)
public interface SysDropDownApi {

    /**
     * 获取指标分类下拉框
     */
    @GetMapping(value = "/dropDownList/listForIndexType",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<DropDownVo>> listForIndexType();

    /**
     * 获取植被类型下拉框
     */
    @GetMapping(value = "/dropDownList/listForVegetationType",produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<DropDownVo>> listForVegetationType();


}
