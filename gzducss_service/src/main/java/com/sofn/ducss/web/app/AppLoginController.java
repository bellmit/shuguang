package com.sofn.ducss.web.app;

import com.sofn.ducss.config.YmlConfig;
import com.sofn.ducss.constants.Constants;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.model.Auth;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysOrgService;
import com.sofn.ducss.service.SysRegionService;
import com.sofn.ducss.service.SysUserService;
import com.sofn.ducss.util.RedisHelper;
import com.sofn.ducss.vo.LoginVo;
import com.sofn.ducss.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author quzj
 */
@Slf4j
@Api(tags = "APP登录相关接口")
@RestController
@RequestMapping("/app/user")
public class AppLoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private YmlConfig ymlConfig;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<Auth> login(@Validated @RequestBody @ApiParam(name = "登录信息对象", value = "传入json格式", required = true) LoginVo loginVo, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        // 执行登录
        Auth auth = sysUserService.execuLogin(loginVo, true,null);
        return Result.ok(auth);
    }



    @ApiOperation("退出登录")
    @GetMapping("/signout")
    public Result logout() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isBlank(token)){
            throw new SofnException("token为空");
        }

        if (redisHelper.hasKey(token)){
            Object userNameObj = redisHelper.hget(token, Constants.UserSession.userName);
            String userName = userNameObj==null?null:userNameObj.toString();
            if (StringUtils.isNotBlank(userName)) {
                redisHelper.hdel(Constants.UserSession.userNameToken, userName);
                redisHelper.del(token);
                return Result.ok();
            }
        }else {
            // token过期直接返回成功
            return Result.ok();
        }

        return Result.error();
    }

}
