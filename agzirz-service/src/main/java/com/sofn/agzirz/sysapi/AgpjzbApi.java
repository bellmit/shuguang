package com.sofn.agzirz.sysapi;

import com.sofn.agzirz.sysapi.bean.DropDownVo;
import com.sofn.agzirz.sysapi.bean.EmergencyOrgVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 用于文件激活相关
 */
@FeignClient(
        value = "agpjzb-service",
        configuration = FeignConfiguration.class
)
public interface AgpjzbApi {

    /**
     * 下拉列表，获取应急机构列表
     * @return
     */
    @GetMapping("/dropDownList/listForEmergencyOrg")
    Result<List<DropDownVo>> listForEmergencyOrg();
}
