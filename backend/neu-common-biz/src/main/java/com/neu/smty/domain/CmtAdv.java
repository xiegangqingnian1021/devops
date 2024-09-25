package com.neu.smty.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 广告信息对象 cmt_adv
 * 
 * @author Daniel
 * @date 2021-07-11
 */
@ApiModel("广告信息")
public class CmtAdv extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 广告标题 */
    @ApiModelProperty("广告标题")
    @Excel(name = "广告标题")
    private String title;

    /** 广告图片 */
    @ApiModelProperty("广告图片")
    @Excel(name = "广告图片")
    private String image;

    /** 业务模块 */
    @ApiModelProperty("业务模块")
    @Excel(name = "业务模块")
    private String servModule;

    /** 跳转详情ID */
    @ApiModelProperty("跳转详情ID")
    @Excel(name = "跳转详情ID")
    private Long targetId;

    /** 排序 */
    @ApiModelProperty("排序")
    @Excel(name = "排序")
    private Long sort;

    /** 类型 */
    @ApiModelProperty("类型")
    @Excel(name = "类型")
    private String type;

    /** 状态 */
    @ApiModelProperty("状态")
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setServModule(String servModule) 
    {
        this.servModule = servModule;
    }

    public String getServModule() 
    {
        return servModule;
    }
    public void setTargetId(Long targetId) 
    {
        this.targetId = targetId;
    }

    public Long getTargetId() 
    {
        return targetId;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
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
            .append("title", getTitle())
            .append("image", getImage())
            .append("servModule", getServModule())
            .append("targetId", getTargetId())
            .append("sort", getSort())
            .append("type", getType())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
