package com.sofn.sys.web;

import com.google.code.kaptcha.Producer;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.LogType;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.common.web.BaseController;
import com.sofn.sys.config.ProxyUser;
import com.sofn.sys.model.Auth;
import com.sofn.sys.model.SysCaptcha;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.LoginVo;
import com.sofn.sys.vo.SysCaptchaVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author quzj
 */
@Slf4j
@Api(tags = "登录相关接口")
@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    SysRegionService sysRegionService;

    @Autowired
    private Producer producer;
    @Autowired
    private ProxyUser proxyUser;

    @Resource
    private RedissonClient redissonClient;

    @SofnLog(type = "查询", value = "获取验证码")
    @ApiOperation(value = "获取验证码")
    @GetMapping("/captcha")
    public Result<SysCaptchaVo> captcha(HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成新验证码概率
        float ratio = 0.2f;
        // 验证码仓库最大大小
        long repoMaxSize = 100;

        // 随机生成一个一位数，概率内将生成新的验证码
        int num = new Random().nextInt(10);
        boolean ratioPass = num%10 < ratio*10;
        log.error("num : "+num+", ratioPass:"+ratioPass);

        // 从仓库中获取验证码
        String captchaInfo = SysCacheUtils.getCaptchaFromRepo();
        SysCaptcha sysCaptcha = JsonUtils.json2obj(captchaInfo, SysCaptcha.class);

        // 概率内或仓库中未获取到验证码就重新生成
        if (ratioPass || StringUtils.isBlank(captchaInfo)) {
          RLock lock = redissonClient.getLock("sys-captcha");
          lock.lock();
          try {
            // 生成新验证码还需满足仓库数量小于最大数量，并发下会出现大于最大数量的情况
            long repoSize = SysCacheUtils.getCaptchaRepoSize();
            log.error("repoSize : "+repoSize);
            if (repoSize < repoMaxSize) {
              // 生成文字验证码
              String text = producer.createText();
              log.info("captcha text: " + text);

              // 生成图片验证码
              BufferedImage image = producer.createImage(text);
              String captchaBase64 = encodeBase64ImgCode(image);

              sysCaptcha = new SysCaptcha();
              sysCaptcha.setText(text);
              sysCaptcha.setCaptchaBase64(captchaBase64);

              // 验证码添加到仓库
              SysCacheUtils.addCaptchaToRepo(JsonUtils.obj2json(sysCaptcha));
            }else {
              SysCacheUtils.popCaptchaFromRepo();
            }
          }finally {
            lock.unlock();
          }
        }

        // 验证码id
        String id = IdUtil.getUUId();
        // 缓存验证码
        SysCacheUtils.cacheCaptchaById(id, sysCaptcha.getText());

        return Result.ok(new SysCaptchaVo(id, sysCaptcha.getCaptchaBase64()));
    }

    @SofnLog(type = "登录", value = "web用户登录")
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<Auth> login(@Validated @RequestBody @ApiParam(name = "登录信息对象", value = "传入json格式", required = true) LoginVo loginVo, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        Auth auth = sysUserService.execuLogin(loginVo, false,null);
        return Result.ok(auth);
    }

    @GetMapping("/replaceToken")
    public Result<String> replaceToken() {
      if (proxyUser == null) {
        throw new NullPointerException("proxy user is null");
      }

      LoginVo loginVo = new LoginVo();
      loginVo.setUsername(proxyUser.getUsername());
      loginVo.setPassword(proxyUser.getPassword());
      loginVo.setRememberMe(BoolUtils.Y);
      Auth auth = sysUserService.execuLogin(loginVo, true, JWTToken.SIGN_TYPE_SHARE);
      return Result.ok(auth.getToken(),"success");
    }


    @SofnLog("退出登录")
    @ApiOperation("退出登录")
    @GetMapping("/signout")
    public Result<String> logout() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isBlank(token)) {
            throw new SofnException("token为空");
        }

        return SysCacheUtils.clearUserCache(token) ? Result.ok("退出登录成功") : Result.error("退出登录失败！");
    }

    /**
     * 验证码转base64字符串
     */
    private String encodeBase64ImgCode(BufferedImage image) throws ServletException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", out);
        byte[] b = out.toByteArray();
        String imgString = Base64.encodeBase64String(b);
        return "data:image/JPEG;base64," + imgString;
    }
}
