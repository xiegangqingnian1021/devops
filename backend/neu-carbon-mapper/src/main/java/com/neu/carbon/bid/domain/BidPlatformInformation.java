package com.neu.carbon.bid.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台动态对象 bid_platform_information
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("平台动态")
public class BidPlatformInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 招投标标题 */
    @ApiModelProperty("招投标标题")
    @Excel(name = "招投标标题")
    private String title;

    /** 招投标内容 */
    @ApiModelProperty("招投标内容")
    @Excel(name = "招投标内容")
    private String content;

    /** 发布时间 */
    @ApiModelProperty(value="发布时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishDatetime;

    /** 发布者 */
    @ApiModelProperty("发布者")
    @Excel(name = "发布者")
    private String publisher;

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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPublishDatetime(Date publishDatetime) 
    {
        this.publishDatetime = publishDatetime;
    }

    public Date getPublishDatetime() 
    {
        return publishDatetime;
    }
    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public String getPublisher() 
    {
        return publisher;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("publishDatetime", getPublishDatetime())
            .append("publisher", getPublisher())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
