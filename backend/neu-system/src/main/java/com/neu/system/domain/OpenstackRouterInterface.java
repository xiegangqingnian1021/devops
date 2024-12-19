package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 路由接口对象 openstack_router_interface
 * 
 * @author neuedu
 * @date 2024-12-17
 */
@ApiModel("路由接口")
public class OpenstackRouterInterface extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @ApiModelProperty("序号")
    private Long id;

    /** 路由编号 */
    @ApiModelProperty("路由编号")
    @Excel(name = "路由编号")
    private String routerId;

    /** 是否网关 */
    @ApiModelProperty("是否网关")
    @Excel(name = "是否网关")
    private Long isGateway;

    /** 网络编号 */
    @ApiModelProperty("网络编号")
    @Excel(name = "网络编号")
    private String netId;

    /** 用户名 */
    private String userName;

    /** 身份密码 */
    private String userPwd;

    /** 租户ID */
    private String projectId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRouterId(String routerId) 
    {
        this.routerId = routerId;
    }

    public String getRouterId() 
    {
        return routerId;
    }
    public void setIsGateway(Long isGateway) 
    {
        this.isGateway = isGateway;
    }

    public Long getIsGateway() 
    {
        return isGateway;
    }
    public void setNetId(String netId) 
    {
        this.netId = netId;
    }

    public String getNetId() 
    {
        return netId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("routerId", getRouterId())
            .append("isGateway", getIsGateway())
            .append("netId", getNetId())
            .toString();
    }
}
