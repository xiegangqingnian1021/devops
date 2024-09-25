package com.neu.carbon.bid.domain;

import java.util.Date;
import java.util.List;

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
 * 政策法规对象 bid_policylaw
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("政策法规")
public class BidPolicylaw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 标题 */
    @ApiModelProperty("标题")
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @ApiModelProperty("内容")
    @Excel(name = "内容")
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
    @ApiModelProperty("上传附件")
    private List<BidPolicylawFiles> filesList;

    public List<BidPolicylawFiles> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<BidPolicylawFiles> filesList) {
        this.filesList = filesList;
    }

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
