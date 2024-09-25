package com.neu.smty.api.base;

import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.entity.SysUser;
import com.neu.common.core.domain.model.LoginUser;
import com.neu.common.utils.ServletUtils;
import com.neu.framework.web.service.TokenService;
import com.neu.system.service.ISysUserService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Api base controller
 * @author: Daniel
 * 2021/4/6 6:36
 */
public class ApiBaseController extends BaseController {


    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected TokenService tokenService;
    @Autowired
    private ISysUserService userService;


    /**
     * 获取登录用户信息
     */
    protected SysUser getUser() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return userService.selectUserById(loginUser.getUser().getUserId());
    }

    /**
     * 获取登录用户userId
     */
    protected Long getUserId() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return loginUser.getUser().getUserId();
    }


    protected String getURL() {

        String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();

        String url = "http://" + request.getServerName();
        if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
            url = url + ":" + null2Int(Integer.valueOf(request.getServerPort())) + contextPath;
        else {
            url = url + contextPath;
        }
        return url;

    }

    private int null2Int(Object s) {
        int v = 0;
        if (s != null)
            try {
                v = Integer.parseInt(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

}
