package com.sofn.fdzem.feign;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.fdzem.vo.SysSystemFunction;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 使用Feign 调用sys服务
 *
 * @author chenlf
 * @date 2019/9/16 14:26
 */
@FeignClient(
		value = "sys-service",
		configuration = FeignConfiguration.class
)
public interface OrgFunctionFeign {

	/**
	 * 根据机构Id查询机构职能
	 * @param orgId
	 * @return
	 */
	@GetMapping("/orgFunction/getByOrgId")
	Result<List<SysSystemFunction>> getByOrgId(@NotBlank @ApiParam(value = "机构ID") @RequestParam("orgId") String orgId);

}
