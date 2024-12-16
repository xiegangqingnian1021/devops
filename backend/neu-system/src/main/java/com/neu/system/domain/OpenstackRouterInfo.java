package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 路由信息对象 openstack_router_info
 * 
 * @author neuedu
 * @date 2024-12-16
 */
@ApiModel("路由信息")
public class OpenstackRouterInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 路由编号 */
    @ApiModelProperty("路由编号")
    @Excel(name = "路由编号")
    private String routerId;

    /** 路由名称 */
    @ApiModelProperty("路由名称")
    @Excel(name = "路由名称")
    private String routerName;

    /** 路由介绍 */
    @ApiModelProperty("路由介绍")
    @Excel(name = "路由介绍")
    private String routerDescription;

    /** 租户编号 */
    @ApiModelProperty("租户编号")
    @Excel(name = "租户编号")
    private String projectId;

    public void setRouterId(String routerId) 
    {
        this.routerId = routerId;
    }

    public String getRouterId() 
    {
        return routerId;
    }
    public void setRouterName(String routerName) 
    {
        this.routerName = routerName;
    }

    public String getRouterName() 
    {
        return routerName;
    }
    public void setRouterDescription(String routerDescription) 
    {
        this.routerDescription = routerDescription;
    }

    public String getRouterDescription() 
    {
        return routerDescription;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("routerId", getRouterId())
            .append("routerName", getRouterName())
            .append("routerDescription", getRouterDescription())
            .append("projectId", getProjectId())
            .toString();
    }
}
