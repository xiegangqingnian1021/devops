package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 租户用户对象 openstack_project_user
 * 
 * @author neuedu
 * @date 2024-10-31
 */
@ApiModel("租户用户")
public class OpenstackProjectUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String id;

    /** 用户编号 */
    @ApiModelProperty("用户编号")
    @Excel(name = "用户编号")
    private String userId;

    /** 项目编号 */
    @ApiModelProperty("项目编号")
    @Excel(name = "项目编号")
    private String projectId;

    /** 角色名称 */
    @ApiModelProperty("角色名称")
    @Excel(name = "角色名称")
    private String roleName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("projectId", getProjectId())
            .append("roleName", getRoleName())
            .toString();
    }
}
