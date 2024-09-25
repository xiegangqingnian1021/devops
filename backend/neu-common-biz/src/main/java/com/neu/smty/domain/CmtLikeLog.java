package com.neu.smty.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 点赞日志对象 cmt_like_log
 * 
 * @author neusoft
 * @date 2021-08-04
 */
@ApiModel("点赞日志")
public class CmtLikeLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 业务主键 */
    @ApiModelProperty("业务主键")
    @Excel(name = "业务主键")
    private Long bizId;

    /** 业务类型 */
    @ApiModelProperty("业务类型")
    @Excel(name = "业务类型")
    private String bizType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBizId(Long bizId) 
    {
        this.bizId = bizId;
    }

    public Long getBizId() 
    {
        return bizId;
    }
    public void setBizType(String bizType) 
    {
        this.bizType = bizType;
    }

    public String getBizType() 
    {
        return bizType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bizId", getBizId())
            .append("bizType", getBizType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
