package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 租户信息对象 project_info
 * 
 * @author neuedu
 * @date 2024-09-23
 */
@ApiModel("租户信息")
public class ProjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目编号 */
    @ApiModelProperty("项目编号")
    @Excel(name = "项目编号")
    private String projectId;

    /** 项目名称 */
    @ApiModelProperty("项目名称")
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目描述 */
    @ApiModelProperty("项目描述")
    @Excel(name = "项目描述")
    private String projectDescription;

    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectDescription(String projectDescription) 
    {
        this.projectDescription = projectDescription;
    }

    public String getProjectDescription() 
    {
        return projectDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectDescription", getProjectDescription())
            .toString();
    }
}
