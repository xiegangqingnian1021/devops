package com.neu.carbon.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目维护对象 mes_project
 * 
 * @author neuedu
 * @date 2022-07-07
 */
@ApiModel("项目维护")
public class MesProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 项目名 */
    @ApiModelProperty("项目名")
    @Excel(name = "项目名")
    private String name;

    /** 负责人 */
    @ApiModelProperty("负责人")
    @Excel(name = "负责人")
    private String leader;

    /** 开始日期 */
    @ApiModelProperty(value="开始日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @ApiModelProperty(value="结束日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 项目介绍 */
    @ApiModelProperty("项目介绍")
    //@Excel(name = "项目介绍")
    private String description;

    /** 项目状态 */
    @ApiModelProperty("项目状态")
    @Excel(name = "项目状态", dictType="project_status")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("leader", getLeader())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("description", getDescription())
            .append("status", getStatus())
            .toString();
    }
}
