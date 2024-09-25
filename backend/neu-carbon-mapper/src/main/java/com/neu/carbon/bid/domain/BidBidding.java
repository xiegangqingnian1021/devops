package com.neu.carbon.bid.domain;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 招投标对象 bid_bidding
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("招投标")
public class BidBidding extends BaseEntity
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
    private String bidTitle;

    /** 招投标内容 */
    @ApiModelProperty("招投标内容")
    @Excel(name = "招投标内容")
    private String bidContent;

    /** 发布时间 */
    @ApiModelProperty(value="发布时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bidPublishDatetime;

    /** 发布者 */
    @ApiModelProperty("发布者")
    @Excel(name = "发布者")
    private String bidPublisher;

    /** 发布分类  1招标预告 2招标公告 3 中标公告 4企业采购 */
    @ApiModelProperty("发布分类  1招标预告 2招标公告 3 中标公告 4企业采购")
    @Excel(name = "发布分类  1招标预告 2招标公告 3 中标公告 4企业采购")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Integer category;

    /** 开始时间 */
    @ApiModelProperty(value="开始时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @ApiModelProperty(value="结束时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 状态 待开标 0 资质审核 1 已过期 2 开标结果 3 */
    @ApiModelProperty("状态 待开标 0 资质审核 1 已过期 2 开标结果 3")
    @Excel(name = "状态 待开标 0 资质审核 1 已过期 2 开标结果 3")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Integer status;
    @ApiModelProperty("上传文件")
    private List<BidBiddingFiles> filesList;

    @ApiModelProperty("查询开始")
    private Date fromDate;

    @ApiModelProperty("查询结束")
    private Date toDate;

    @ApiModelProperty(hidden = true)
    private String enterpriseId;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<BidBiddingFiles> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<BidBiddingFiles> filesList) {
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
    public void setBidTitle(String bidTitle) 
    {
        this.bidTitle = bidTitle;
    }

    public String getBidTitle() 
    {
        return bidTitle;
    }
    public void setBidContent(String bidContent) 
    {
        this.bidContent = bidContent;
    }

    public String getBidContent() 
    {
        return bidContent;
    }
    public void setBidPublishDatetime(Date bidPublishDatetime) 
    {
        this.bidPublishDatetime = bidPublishDatetime;
    }

    public Date getBidPublishDatetime() 
    {
        return bidPublishDatetime;
    }
    public void setBidPublisher(String bidPublisher) 
    {
        this.bidPublisher = bidPublisher;
    }

    public String getBidPublisher() 
    {
        return bidPublisher;
    }
    public void setCategory(Integer category) 
    {
        this.category = category;
    }

    public Integer getCategory() 
    {
        return category;
    }
    public void setBeginDate(Date beginDate) 
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() 
    {
        return beginDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
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
            .append("bidTitle", getBidTitle())
            .append("bidContent", getBidContent())
            .append("bidPublishDatetime", getBidPublishDatetime())
            .append("bidPublisher", getBidPublisher())
            .append("category", getCategory())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("status", getStatus())
            .toString();
    }
}
