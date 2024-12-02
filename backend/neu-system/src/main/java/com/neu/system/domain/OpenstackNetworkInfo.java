package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 网络管理对象 openstack_network_info
 * 
 * @author neuedu
 * @date 2024-12-02
 */
@ApiModel("网络管理")
public class OpenstackNetworkInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 网络ID */
    @ApiModelProperty("网络ID")
    @Excel(name = "网络ID")
    private String networkId;

    /** 网络名称 */
    @ApiModelProperty("网络名称")
    @Excel(name = "网络名称")
    private String networkName;

    /** 所属项目 */
    @ApiModelProperty("所属项目")
    @Excel(name = "所属项目")
    private String projectId;

    /** 是否内网 */
    @ApiModelProperty("是否内网")
    @Excel(name = "是否内网")
    private Long isInternal;

    public void setNetworkId(String networkId)
    {
        this.networkId = networkId;
    }

    public String getNetworkId() 
    {
        return networkId;
    }
    public void setNetworkName(String networkName) 
    {
        this.networkName = networkName;
    }

    public String getNetworkName() 
    {
        return networkName;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setIsInternal(Long isInternal) 
    {
        this.isInternal = isInternal;
    }

    public Long getIsInternal() 
    {
        return isInternal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("networkId", getNetworkId())
            .append("networkName", getNetworkName())
            .append("projectId", getProjectId())
            .append("isInternal", getIsInternal())
            .toString();
    }
}
