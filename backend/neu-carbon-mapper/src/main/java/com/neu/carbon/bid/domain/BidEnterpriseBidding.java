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

import java.util.List;

/**
 * 企业投标对象 bid_enterprise_bidding
 *
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("企业投标")
public class BidEnterpriseBidding extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 企业主键
     */
    @ApiModelProperty("企业主键")
    @Excel(name = "企业主键")
    private Long enterpriseId;

    /**
     * 招标主键
     */
    @ApiModelProperty("招标主键")
    @Excel(name = "招标主键")
    private Long bidId;

    /**
     * 状态 待开标 0 资质审核 1 已过期 2 开标结果 3
     */
    @ApiModelProperty("状态 待开标 0 资质审核 1 已过期 2 开标结果 3")
    @Excel(name = "状态 待开标 0 资质审核 1 已过期 2 开标结果 3")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Integer status;

    /**
     * 投标企业
     */
    @ApiModelProperty(hidden = true)
    private BidEnterprise enterprise;

    /**
     *
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;
    /**
     *
     */
    @ApiModelProperty(value = "投标标题")
    private String bidTitle;

    /**
     * 投标项目
     */
    @ApiModelProperty(hidden = true)
    private BidBidding bidBidding;

    /**
     * 投标文件
     */
    @ApiModelProperty(hidden = true)
    private List<BidEnterpriseBidfile> filesList;

    public String getEnterpriseName() {
        if (enterprise != null) {
            return enterprise.getName();
        }
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getBidTitle() {
        if (this.bidBidding != null) {
            return bidBidding.getBidTitle();
        }
        return bidTitle;
    }

    public void setBidTitle(String bidTitle) {
        this.bidTitle = bidTitle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }


    public BidEnterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(BidEnterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setBidBidding(BidBidding bidBidding) {
        this.bidBidding = bidBidding;
    }

    public List<BidEnterpriseBidfile> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<BidEnterpriseBidfile> filesList) {
        this.filesList = filesList;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("enterpriseId", getEnterpriseId())
                .append("bidId", getBidId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("status", getStatus())
                .toString();
    }

    public BidBidding getBidBidding() {
        return bidBidding;
    }
}
