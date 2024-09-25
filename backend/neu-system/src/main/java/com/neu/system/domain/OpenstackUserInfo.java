package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息对象 openstack_user_info
 * 
 * @author neuedu
 * @date 2024-09-25
 */
@ApiModel("用户信息")
public class OpenstackUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户编号 */
    @ApiModelProperty("用户编号")
    @Excel(name = "用户编号")
    private String userId;

    /** 用户名称 */
    @ApiModelProperty("用户名称")
    @Excel(name = "用户名称")
    private String userName;

    /** 用户邮箱 */
    @ApiModelProperty("用户邮箱")
    @Excel(name = "用户邮箱")
    private String userEmail;

    /** 登录密码 */
    @ApiModelProperty("登录密码")
    private String userPwd;

    /** 是否启用 */
    @ApiModelProperty("是否启用")
    @Excel(name = "是否启用")
    private Long userEnable;

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserEmail(String userEmail) 
    {
        this.userEmail = userEmail;
    }

    public String getUserEmail() 
    {
        return userEmail;
    }
    public void setUserPwd(String userPwd) 
    {
        this.userPwd = userPwd;
    }

    public String getUserPwd() 
    {
        return userPwd;
    }
    public void setUserEnable(Long userEnable) 
    {
        this.userEnable = userEnable;
    }

    public Long getUserEnable() 
    {
        return userEnable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userEmail", getUserEmail())
            .append("userPwd", getUserPwd())
            .append("userEnable", getUserEnable())
            .toString();
    }
}
