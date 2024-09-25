package com.neu.smty.api.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.config.NeuConfig;
import com.neu.common.constant.HttpStatus;
import com.neu.common.constant.UserConstants;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysUser;
import com.neu.common.core.domain.model.LoginUser;
import com.neu.common.core.redis.RedisCache;
import com.neu.common.utils.SecurityUtils;
import com.neu.common.utils.ServletUtils;
import com.neu.common.utils.StringUtils;
import com.neu.common.utils.file.FileUploadUtils;
import com.neu.framework.config.ServerConfig;
import com.neu.framework.web.service.TokenService;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.api.vo.LoginForm;
import com.neu.smty.api.vo.PwdForm;
import com.neu.smty.api.vo.UserVo;
import com.neu.smty.constant.Constant;
import com.neu.system.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/api/user")
public class UserApi extends ApiBaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ServerConfig serverConfig;

    @ApiOperation("查询个人基本信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "userInfo", value = "返回用户信息数据", dataTypeClass = UserVo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping("/getInfo")
    public AjaxResult getInfo4UserVo() {
        SysUser user = this.getUser();
        UserVo userVo = this.assembleUserVo(user);
        return AjaxResult.success().put("userInfo", userVo);
    }


    @ApiOperation("修改个人基本信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PutMapping
    public AjaxResult edit(@RequestBody UserVo userVo) {
        // 1、装配系统用户
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        userVo.setUserId(loginUser.getUser().getUserId());
        SysUser user = this.assembelSysUser(userVo);

        // 2、校验手机号码与邮箱唯一
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        // 3、更新用户
        return toAjax(userService.updateUser(user));
    }

    @ApiOperation("更改电话号码")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PutMapping("/changeTel")
    public AjaxResult changeTel(@RequestBody LoginForm form) {
        // 1、验证码校验
        String verifyCode = redisCache.getCacheObject(Constant.CODE_KEY + form.getUsername());
        if (StringUtils.isEmpty(verifyCode)) {
            throw new RuntimeException("验证码无效");
        }
        if (!verifyCode.equals(form.getCode())) {
            throw new RuntimeException("验证码错误");
        }
        SysUser user = userService.selectUserById(this.getUserId());
        // 2、装配系统用户
        SysUser update = new SysUser();
        update.setUserId(user.getUserId());
        update.setUserName(form.getUsername());
        update.setPhonenumber(form.getUsername());
        // 3、判断手机号是否唯一
        if (StringUtils.isNotEmpty(update.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        // 4、更新用户
        return toAjax(userService.updateUser(update));
    }


    @ApiOperation("修改用户密码")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PutMapping("/resetPwd")
    public AjaxResult updatePwd(@RequestBody PwdForm pwdForm) {
        String oldPassword = pwdForm.getOldPassword();
        String newPassword = pwdForm.getNewPassword();
        if (oldPassword == null) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入旧密码！");
        }
        if (newPassword == null) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入新密码！");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser = userService.selectUserById(this.getUserId());
        String userName = sysUser.getUserName();
        String password = sysUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    @ApiOperation("设置新密码")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PutMapping("/setPwd")
    public AjaxResult setPwd(@RequestBody LoginForm loginForm) {
        String newPassword = loginForm.getPassword();
        if (StringUtils.isBlank(newPassword)) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "参数错误，请传入新密码！");
        }
        if (userService.resetUserPwd(loginForm.getUsername(), SecurityUtils.encryptPassword(newPassword)) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("设置新密码失败");
    }

    @ApiOperation("头像上传")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码"),
            @DynamicParameter(name = "msg", value = "消息内容"),
            @DynamicParameter(name = "fileName", value = "文件名"),
            @DynamicParameter(name = "url", value = "文件访问地址"),
    })
    @PostMapping("/avatar")
    public AjaxResult avatar(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            // 上传文件路径
            String filePath = NeuConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            if (userService.updateUserAvatar(loginUser.getUsername(), url)) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("fileName", fileName);
                ajax.put("url", url);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(fileName);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    // 根据SysUser装配UserVo
    private UserVo assembleUserVo(SysUser user) {
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUserName(user.getUserName());
        userVo.setNickName(user.getNickName());
        userVo.setEmail(user.getEmail());
        userVo.setAvatar(user.getAvatar());
        userVo.setPhonenumber(user.getPhonenumber());
        userVo.setSex(user.getSex());
        userVo.setIdCard(user.getIdCard());
        userVo.setRealName(user.getRealName());
        userVo.setAddress(user.getAddress());
        userVo.setRegisterPlace(user.getRegisterPlace());
        userVo.setBirthday(user.getBirthday());
        userVo.setNativePlace(user.getNativePlace());
        userVo.setNation(user.getNation());
        userVo.setDegree(user.getDegree());
        userVo.setMarriage(user.getMarriage());
        userVo.setCareer(user.getCareer());
        userVo.setOrganization(user.getOrganization());
        userVo.setContacts(user.getContacts());
        userVo.setContactsPhone(user.getContactsPhone());
        return userVo;
    }

    // 根据UserVo装配SysUser
    private SysUser assembelSysUser(UserVo userVo) {
        SysUser user = new SysUser();
        user.setUserId(userVo.getUserId());
        user.setAvatar(userVo.getAvatar());
        user.setNickName(userVo.getNickName());
        user.setSex(userVo.getSex());
        user.setIdCard(userVo.getIdCard());
        user.setPhonenumber(userVo.getPhonenumber());
        user.setEmail(userVo.getEmail());
        user.setRealName(userVo.getRealName());
        user.setAddress(userVo.getAddress());
        user.setRegisterPlace(userVo.getRegisterPlace());
        user.setBirthday(userVo.getBirthday());
        user.setNativePlace(userVo.getNativePlace());
        user.setNation(userVo.getNation());
        user.setDegree(userVo.getDegree());
        user.setMarriage(userVo.getMarriage());
        user.setCareer(userVo.getCareer());
        user.setOrganization(userVo.getOrganization());
        user.setContacts(userVo.getContacts());
        user.setContactsPhone(userVo.getContactsPhone());
        return user;
    }


}
