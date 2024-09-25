package com.neu.demo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 演示对象 demo_user
 * 
 * @author neusoft
 * @date 2021-08-20
 */
@ApiModel("演示")
public class DemoUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @Excel(name = "姓名")
    private String name;

    /** 部门 */
    @ApiModelProperty("部门")
    @Excel(name = "部门")
    private Long deptId;

    /** 状态 */
    @ApiModelProperty("状态")
    @Excel(name = "状态")
    private String status;

    /** 系统用户 */
    @ApiModelProperty("系统用户")
    @Excel(name = "系统用户")
    private Long userId;

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
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("deptId", getDeptId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .toString();
    }
}
