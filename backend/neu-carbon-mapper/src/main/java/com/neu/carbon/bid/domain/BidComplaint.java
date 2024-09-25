package com.neu.carbon.bid.domain;

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
 * 违规投诉对象 bid_complaint
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("违规投诉")
public class BidComplaint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 企业主键 */
    @ApiModelProperty("企业主键")
    @Excel(name = "企业主键")
    private Long enterpriseId;

    /** 投诉标题 */
    @ApiModelProperty("投诉标题")
    @Excel(name = "投诉标题")
    private String title;

    /** 投诉信息 */
    @ApiModelProperty("投诉信息")
    @Excel(name = "投诉信息")
    private String content;

    /** 联系人 */
    @ApiModelProperty("联系人")
    @Excel(name = "联系人")
    private String contact;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    @Excel(name = "联系电话")
    private String contactTel;

    /** 状态 0 正在处理中 1处理完成 */
    @ApiModelProperty("状态 0 正在处理中 1处理完成")
    @Excel(name = "状态 0 正在处理中 1处理完成")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Integer status;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    /**
     * 企业信息
     */
    private BidEnterprise bidEnterprise;

    public BidEnterprise getBidEnterprise() {
        return bidEnterprise;
    }

    public void setBidEnterprise(BidEnterprise bidEnterprise) {
        this.bidEnterprise = bidEnterprise;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
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
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setContactTel(String contactTel) 
    {
        this.contactTel = contactTel;
    }

    public String getContactTel() 
    {
        return contactTel;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("enterpriseId", getEnterpriseId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("contact", getContact())
            .append("contactTel", getContactTel())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
