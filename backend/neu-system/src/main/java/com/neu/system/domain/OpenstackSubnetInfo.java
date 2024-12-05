package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 子网管理对象 openstack_subnet_info
 * 
 * @author neuedu
 * @date 2024-12-05
 */
@ApiModel("子网管理")
public class OpenstackSubnetInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 子网ID */
    @ApiModelProperty("子网ID")
    @Excel(name = "子网ID")
    private String subnetId;

    /** 子网名称 */
    @ApiModelProperty("子网名称")
    @Excel(name = "子网名称")
    private String subnetName;

    /** 所属网络 */
    @ApiModelProperty("所属网络")
    @Excel(name = "所属网络")
    private String networkId;

    /** 所属租户 */
    @ApiModelProperty("所属租户")
    @Excel(name = "所属租户")
    private String projectId;

    /** 子网范围 */
    @ApiModelProperty("子网范围")
    @Excel(name = "子网范围")
    private String subnetRange;

    /** 子网网关 */
    @ApiModelProperty("子网网关")
    private String gateway;

    public void setSubnetId(String subnetId) 
    {
        this.subnetId = subnetId;
    }

    public String getSubnetId() 
    {
        return subnetId;
    }
    public void setSubnetName(String subnetName) 
    {
        this.subnetName = subnetName;
    }

    public String getSubnetName() 
    {
        return subnetName;
    }
    public void setNetworkId(String networkId) 
    {
        this.networkId = networkId;
    }

    public String getNetworkId() 
    {
        return networkId;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setSubnetRange(String subnetRange) 
    {
        this.subnetRange = subnetRange;
    }

    public String getSubnetRange() 
    {
        return subnetRange;
    }
    public void setGateway(String gateway) 
    {
        this.gateway = gateway;
    }

    public String getGateway() 
    {
        return gateway;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("subnetId", getSubnetId())
            .append("subnetName", getSubnetName())
            .append("networkId", getNetworkId())
            .append("projectId", getProjectId())
            .append("subnetRange", getSubnetRange())
            .append("gateway", getGateway())
            .toString();
    }
}
