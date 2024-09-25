package com.neu.smty.api.common;

import cn.hutool.core.util.RandomUtil;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.constant.Constants;
import com.neu.common.constant.HttpStatus;
import com.neu.common.constant.UserConstants;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysUser;
import com.neu.common.core.domain.model.LoginBody;
import com.neu.common.core.domain.model.LoginUser;
import com.neu.common.core.redis.RedisCache;
import com.neu.common.utils.SecurityUtils;
import com.neu.common.utils.StringUtils;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.api.vo.LoginForm;
import com.neu.smty.api.vo.RegisterBody;
import com.neu.smty.constant.Constant;
import com.neu.smty.service.LoginService;
import com.neu.system.mapper.SysUserMapper;
import com.neu.system.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Api(tags = "通用模块用户登录注册接口")
@RestController
@RequestMapping("/api")
public class LoginRegisterApi extends ApiBaseController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysUserMapper userMapper;

    @ApiOperation("获取验证码")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "valid_code", value = "验证码"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping("/code")
    public AjaxResult getCode(LoginForm loginBody) {
        String cacheKey = Constant.CODE_KEY + loginBody.getUsername();
        // 生成验证码，并存入Redis
        String code = String.valueOf(RandomUtil.randomInt(100000, 999999));
        redisCache.setCacheObject(cacheKey, code, 60, TimeUnit.SECONDS);
        return AjaxResult.success().put(Constant.CODE, code);
    }

    @ApiOperation("密码登录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "token", value = "token"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("密码登录")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "token", value = "token"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/code_login")
    public AjaxResult loginWithCode(@RequestBody LoginForm loginBody) {
        // 1、参数校验
        if (StringUtils.isBlank(loginBody.getUsername())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入用户名！");
        }
        if (StringUtils.isBlank(loginBody.getCode())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入验证码！");
        }

        SysUser user = userMapper.selectUserByUserName(loginBody.getUsername());
        if (StringUtils.isNull(user)) {
            throw new UsernameNotFoundException("手机号不存在");
        }

        String cacheKey = Constant.CODE_KEY + loginBody.getUsername();
        String verifyCode = redisCache.getCacheObject(cacheKey);
        if (StringUtils.isEmpty(verifyCode)) {
            throw new RuntimeException("验证码已过期，请重新发送！");
        }
        if (!verifyCode.equals(loginBody.getCode())) {
            throw new RuntimeException("验证码不合法！");
        }
        // 生成令牌
        LoginUser loginUser = new LoginUser(user, null);
        String token = tokenService.createToken(loginUser);
        return AjaxResult.success().put(Constants.TOKEN, token);
    }


    @ApiOperation("用户注册")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        // 1、参数校验
        if (StringUtils.isBlank(user.getUsername())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入用户名！");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入密码！");
        }
        if (StringUtils.isBlank(user.getCode())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入验证码！");
        }
        // 2、验证码校验
        String verifyCode = redisCache.getCacheObject(Constant.CODE_KEY + user.getUsername());
        if (StringUtils.isEmpty(verifyCode)) {
            throw new RuntimeException("验证码无效");
        }
        if (!verifyCode.equals(user.getCode())) {
            throw new RuntimeException("验证码无效");
        }
        // 3、校验用户名唯一性
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUsername()))) {
            return AjaxResult.error("新增用户'" + user.getUsername() + "'失败，账号已存在");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserName(user.getUsername());
        sysUser.setPhonenumber(user.getUsername());
        // 3、初始化电子钱包金额默认1000
        sysUser.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
//        user.setBalance(new BigDecimal(Constant.USER_BALANCE_INIT));
        sysUser.setCreateTime(new Date());
        sysUser.setCreateBy(user.getUsername());
        int r = userMapper.insertUser(sysUser);
        if (r > 0) {
            redisCache.deleteObject(Constant.CODE_KEY + user.getUsername());
            return AjaxResult.success();
        }
        return AjaxResult.error("注册失败！");
    }


    @ApiOperation("检查用户是否存在")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "isPass", value = "true / false"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/user/check")
    public AjaxResult check(@RequestBody LoginForm loginForm) {
        // 1、参数校验
        if (StringUtils.isBlank(loginForm.getUsername())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入用户名！");
        }
        if (StringUtils.isBlank(loginForm.getCode())) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入验证码！");
        }

        SysUser user = userMapper.selectUserByUserName(loginForm.getUsername());
        if (StringUtils.isNull(user)) {
            throw new UsernameNotFoundException("手机号不存在");
        }


        String cacheKey = Constant.CODE_KEY + loginForm.getUsername();
        String verifyCode = redisCache.getCacheObject(cacheKey);
        if (StringUtils.isEmpty(verifyCode)) {
            throw new RuntimeException("验证码已过期，请重新发送！");
        }
        if (!verifyCode.equals(loginForm.getCode())) {
            throw new RuntimeException("验证码有误！");
        }

        return AjaxResult.success().put("isPass", true);
    }


}
